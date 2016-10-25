package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import android.util.Base64InputStream;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.request.Request;
import anet.channel.request.Request.Builder;
import anet.channel.request.Request.Method;
import anet.channel.session.c;
import anet.channel.statist.AmdcStatistic;
import anet.channel.statist.StatObject;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.d;
import com.alipay.sdk.data.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
import org.json.JSONTokener;

// compiled from: Taobao
class b {
    b() {
    }

    static Map a(Map map) {
        Set<String> set = (Set) map.remove(a.HOSTS);
        if (set == null || set.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : set) {
            stringBuilder.append(str).append(' ');
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        map.put(a.DOMAIN, stringBuilder.toString());
        return d.a(map);
    }

    static List<IConnStrategy> a() {
        List<IConnStrategy> list = Collections.EMPTY_LIST;
        if (NetworkStatusHelper.f()) {
            return list;
        }
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(a.a());
        ListIterator listIterator = connStrategyListByHost.listIterator();
        while (listIterator.hasNext()) {
            if (!((IConnStrategy) listIterator.next()).getConnType().equals(ConnType.HTTP)) {
                listIterator.remove();
            }
        }
        return connStrategyListByHost;
    }

    public static void b(Map map) {
        Map a = a(map);
        if (a != null) {
            List a2 = a();
            int i = 0;
            while (i < 3) {
                IConnStrategy iConnStrategy = i != 2 ? a2.isEmpty() ? null : (IConnStrategy) a2.remove(0) : null;
                int a3 = a(new HashMap(a), iConnStrategy, i);
                if (iConnStrategy != null) {
                    StrategyCenter.getInstance().notifyConnEvent(a.a(), iConnStrategy, a3 == 0 ? EventType.CONNECTED : EventType.CONNECT_FAIL, null);
                }
                if (a3 != 0 && a3 != 2) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static String c(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(HttpConstant.HTTP);
        stringBuilder.append(HttpConstant.SCHEME_SPLIT);
        stringBuilder.append(a.a());
        stringBuilder.append(a.serverPath);
        Map hashMap = new HashMap();
        hashMap.put(Constants.SP_KEY_APPKEY, map.remove(Constants.SP_KEY_APPKEY));
        hashMap.put(IXAdRequestInfo.V, map.remove(IXAdRequestInfo.V));
        hashMap.put(org.android.agoo.common.b.KEY_DEVICE_TOKEN, map.remove(org.android.agoo.common.b.KEY_DEVICE_TOKEN));
        hashMap.put(com.tencent.connect.common.Constants.PARAM_PLATFORM, map.remove(com.tencent.connect.common.Constants.PARAM_PLATFORM));
        stringBuilder.append('?');
        stringBuilder.append(StringUtils.encodeQueryParams(hashMap, "utf-8"));
        return stringBuilder.toString();
    }

    public static int a(Map<String, String> map, IConnStrategy iConnStrategy, int i) {
        Request request;
        Throwable th;
        String message;
        try {
            Request build = new Builder().setMethod(Method.POST).setUrl(c(map)).setParams(map).addHeader(HttpConstant.CONNECTION, "close").addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP).setRedirectEnable(false).setConnectTimeout(a.d).setReadTimeout(a.d).build();
            if (iConnStrategy != null) {
                build.setDnsOptimize(iConnStrategy.getIp(), iConnStrategy.getPort());
            }
            c.a a = c.a(build, null);
            if (a == null || a.a < 0) {
                a("-1000", "request fail", build, i, 1);
                return 1;
            }
            int i2 = a.a;
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.DispatchCore", new StringBuilder("amdc response. code: ").append(i2).toString(), null, "\nheaders", a.c);
            }
            if (i2 != 200) {
                int i3 = (i2 == 302 || i2 == 307) ? 2 : 1;
                a(String.valueOf(i2), "response code not 200", build, i, i3);
                return i3;
            }
            String b = d.b(a.c, "x-am-code");
            if (com.tencent.connect.common.Constants.DEFAULT_UIN.equals(b)) {
                Object trim = d.b(a.c, "x-am-sign").trim();
                if (TextUtils.isEmpty(trim)) {
                    a("-1001", "response sign is empty", build, i, 1);
                    return 1;
                }
                String a2 = a(a.b);
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.DispatchCore", "amdc response body", null, "\nbody", a2);
                }
                if (a2 == null) {
                    a("-1002", "read answer error", build, i, 1);
                    return 1;
                } else if (!a(trim, a2)) {
                    ALog.d("awcn.DispatchCore", "check ret sign failed", null, new Object[0]);
                    a("-1003", "check sign failed", build, i, 1);
                    return 1;
                } else if (a(a2)) {
                    a(b, "request success", build, i, 0);
                    return 0;
                } else {
                    a("-1004", "resolve answer failed", build, i, 1);
                    return 1;
                }
            }
            if ("1007".equals(b) || "1008".equals(b)) {
                i3 = 2;
            } else {
                i3 = 1;
            }
            a(b, new StringBuilder("return code: ").append(b).toString(), build, i, i3);
            return i3;
        } catch (Throwable th2) {
            request = null;
            th = th2;
            message = th.getMessage();
            if (TextUtils.isEmpty(message)) {
                message = th.toString();
            }
            a("-1000", message, request, i, 1);
            return 1;
        }
    }

    static boolean a(String str, String str2) {
        return d.a(str2).equals(str);
    }

    static String a(byte[] bArr) {
        InputStream inputStream;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        try {
            InputStream base64InputStream = new Base64InputStream(new ByteArrayInputStream(bArr), 0);
            try {
                anet.channel.a.a a = a.a.a((int) JsInterface.MSG_JS_COLLECT_WEBSITE);
                while (a.a(base64InputStream) != -1) {
                    a.a(byteArrayOutputStream);
                }
                a.d();
                String str = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                try {
                    base64InputStream.close();
                    return str;
                } catch (IOException e) {
                    return str;
                }
            } catch (IOException e2) {
                inputStream = base64InputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (base64InputStream != null) {
                    try {
                        base64InputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th2;
            }
        } catch (IOException e5) {
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th2 = th3;
            base64InputStream = null;
            if (base64InputStream != null) {
                base64InputStream.close();
            }
            throw th2;
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(str).nextValue();
            if (jSONObject == null) {
                HttpDispatcher.getInstance().fireEvent(new DispatchEvent(0, null));
                return false;
            }
            HttpDispatcher.getInstance().fireEvent(new DispatchEvent(1, jSONObject));
            return true;
        } catch (Throwable e) {
            ALog.e("awcn.DispatchCore", "answerJsonResolve exception", null, e, new Object[0]);
            HttpDispatcher.getInstance().fireEvent(new DispatchEvent(0, null));
            return false;
        }
    }

    static void a(String str, String str2, Request request, int i, int i2) {
        if (i2 != 1 || i == 2) {
            StatObject amdcStatistic = new AmdcStatistic();
            amdcStatistic.errorCode = str;
            amdcStatistic.errorMsg = str2;
            if (request != null) {
                amdcStatistic.host = request.getHost();
                amdcStatistic.url = request.getUrlString();
            }
            amdcStatistic.retryTimes = i;
            AppMonitor.getInstance().commitStat(amdcStatistic);
        }
    }
}
