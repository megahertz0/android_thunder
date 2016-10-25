package anet.channel.session;

import android.os.Build.VERSION;
import android.util.Pair;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.d;
import anet.channel.util.e;
import anet.channel.util.f;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.internal.b;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CancellationException;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

// compiled from: Taobao
public class c {

    // compiled from: Taobao
    public static class a {
        public int a;
        public byte[] b;
        public Map<String, List<String>> c;
    }

    private c() {
    }

    public static a a(Request request, RequestCb requestCb) {
        a aVar = new a();
        HttpURLConnection httpURLConnection = null;
        while (request != null && request.getUrl() != null) {
            if (!NetworkStatusHelper.e()) {
                request.rs.sendBeforeTime = System.currentTimeMillis() - request.rs.start;
                a(request, aVar, requestCb, ErrorConstant.ERROR_NO_NETWORK, null);
                break;
            }
            try {
                request.rs.sendBeforeTime = System.currentTimeMillis() - request.rs.start;
                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    ALog.i("awcn.HttpConnector", com.umeng.a.d, request.getSeq(), "request URL", request.getUrlString());
                    ALog.i("awcn.HttpConnector", com.umeng.a.d, request.getSeq(), "request headers", request.getHeaders());
                }
                httpURLConnection = a(request);
                if (httpURLConnection != null) {
                    httpURLConnection.connect();
                    b(httpURLConnection, request);
                    aVar.a = httpURLConnection.getResponseCode();
                    aVar.c = d.a(httpURLConnection.getHeaderFields());
                    ALog.i("awcn.HttpConnector", new StringBuilder("heads:").append(aVar.c).toString(), request.getSeq(), new Object[0]);
                    if (!d.a(request, aVar.a, aVar.c)) {
                        if (aVar.a == 304 || aVar.a == 204 || (aVar.a >= 100 && aVar.a < 200)) {
                            requestCb.onResponseCode(aVar.a, aVar.c);
                        } else {
                            if (!(d.b(aVar.c) || requestCb == null)) {
                                requestCb.onResponseCode(aVar.a, aVar.c);
                            }
                            a(httpURLConnection, request, aVar, requestCb);
                        }
                        request.rs.oneWayTime = System.currentTimeMillis() - request.rs.start;
                        request.rs.ret = true;
                        if (requestCb != null) {
                            requestCb.onFinish(aVar.a, HttpConstant.SUCCESS, request.rs);
                        }
                    } else if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable e) {
                            ALog.e("awcn.HttpConnector", "http disconnect", null, e, new Object[0]);
                        }
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e2) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e2, new Object[0]);
                    }
                }
            } catch (Throwable e22) {
                a(request, aVar, requestCb, ErrorConstant.ERROR_UNKNOWN_HOST_EXCEPTION, e22);
                ALog.e("awcn.HttpConnector", "Unknown Host Exception", request.getSeq(), b.ELECTION_KEY_HOST, request.getHost(), e22);
                NetworkStatusHelper.i();
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e222, new Object[0]);
                    }
                }
            } catch (Throwable e2222) {
                a(request, aVar, requestCb, ErrorConstant.ERROR_SOCKET_TIME_OUT, e2222);
                ALog.e("awcn.HttpConnector", "HTTP Socket Timeout", request.getSeq(), e2222, new Object[0]);
                NetworkStatusHelper.i();
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e22222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e22222, new Object[0]);
                    }
                }
            } catch (Throwable e222222) {
                a(request, aVar, requestCb, ErrorConstant.ERROR_CONN_TIME_OUT, e222222);
                ALog.e("awcn.HttpConnector", "HTTP Connect Timeout", request.getSeq(), e222222, new Object[0]);
                NetworkStatusHelper.i();
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e2222222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e2222222, new Object[0]);
                    }
                }
            } catch (Throwable e22222222) {
                a(request, aVar, requestCb, ErrorConstant.ERROR_CONNECT_EXCEPTION, e22222222);
                ALog.e("awcn.HttpConnector", "HTTP Connect Exception", request.getSeq(), e22222222, new Object[0]);
                NetworkStatusHelper.i();
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e222222222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e222222222, new Object[0]);
                    }
                }
            } catch (Throwable e2222222222) {
                anet.channel.b.b.a().a(XZBDevice.DOWNLOAD_LIST_FAILED, request.getHost());
                a(request, aVar, requestCb, ErrorConstant.ERROR_SSL_ERROR, e2222222222);
                ALog.e("awcn.HttpConnector", "HTTP Connect SSLHandshakeException", request.getSeq(), b.ELECTION_KEY_HOST, request.getHost(), e2222222222);
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e22222222222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e22222222222, new Object[0]);
                    }
                }
            } catch (Throwable e222222222222) {
                anet.channel.b.b.a().a(XZBDevice.DOWNLOAD_LIST_FAILED, request.getHost());
                a(request, aVar, requestCb, ErrorConstant.ERROR_SSL_ERROR, e222222222222);
                ALog.e("awcn.HttpConnector", "connect SSLException", request.getSeq(), b.ELECTION_KEY_HOST, request.getHost(), e222222222222);
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e2222222222222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e2222222222222, new Object[0]);
                    }
                }
            } catch (Throwable e22222222222222) {
                a(request, aVar, requestCb, ErrorConstant.ERROR_REQUEST_CANCEL, e22222222222222);
                ALog.e("awcn.HttpConnector", "HTTP Request Cancel", request.getSeq(), e22222222222222, new Object[0]);
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e222222222222222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e222222222222222, new Object[0]);
                    }
                }
            } catch (Throwable e2222222222222222) {
                message = e2222222222222222.getMessage();
                String message;
                if (message == null || !message.contains("not verified")) {
                    a(request, aVar, requestCb, ErrorConstant.ERROR_EXCEPTION, e2222222222222222);
                } else {
                    anet.channel.b.b.a().a(XZBDevice.DOWNLOAD_LIST_FAILED, request.getHost());
                    a(request, aVar, requestCb, ErrorConstant.ERROR_HOST_NOT_VERIFY_ERROR, e2222222222222222);
                }
                ALog.e("awcn.HttpConnector", "HTTP Connect Exception", request.getSeq(), e2222222222222222, new Object[0]);
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e22222222222222222) {
                        ALog.e("awcn.HttpConnector", "http disconnect", null, e22222222222222222, new Object[0]);
                    }
                }
            }
        }
        if (requestCb != null) {
            requestCb.onFinish(ErrorConstant.ERROR_PARAM_ILLEGAL, ErrorConstant.getErrMsg(ErrorConstant.ERROR_PARAM_ILLEGAL), new RequestStatistic(null, null));
        }
        return aVar;
    }

    private static void a(Request request, a aVar, RequestCb requestCb, int i, Throwable th) {
        String errMsg = ErrorConstant.getErrMsg(i);
        ALog.e("awcn.HttpConnector", "onException", request.getSeq(), Constants.KEY_ERROR_CODE, Integer.valueOf(i), "errMsg", errMsg, SocialConstants.PARAM_URL, request.getUrlString(), b.ELECTION_KEY_HOST, request.getHost());
        if (aVar != null) {
            aVar.a = i;
        }
        request.rs.oneWayTime = System.currentTimeMillis() - request.rs.start;
        if (requestCb != null) {
            requestCb.onFinish(i, errMsg, request.rs);
        }
        if (i != -204) {
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(i, errMsg, request.rs, th));
        }
    }

    private static HttpURLConnection a(Request request) throws IOException {
        Proxy proxy;
        f fVar;
        HttpURLConnection httpURLConnection = null;
        Pair h = NetworkStatusHelper.h();
        if (h != null) {
            proxy = new Proxy(Type.HTTP, new InetSocketAddress((String) h.first, ((Integer) h.second).intValue()));
        } else {
            proxy = null;
        }
        if (NetworkStatusHelper.a().isMobile()) {
            f proxySetting = GlobalAppRuntimeInfo.getProxySetting();
            if (proxySetting != null) {
                proxy = proxySetting.a;
                fVar = proxySetting;
            } else {
                fVar = proxySetting;
            }
        } else {
            fVar = null;
        }
        try {
            URL url = request.getUrl();
            if (proxy != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            try {
                httpURLConnection.setConnectTimeout(request.getConnectTimeout());
                httpURLConnection.setReadTimeout(request.getReadTimeout());
                String toString = request.getMethod().toString();
                httpURLConnection.setRequestMethod(toString);
                if (com.tencent.connect.common.Constants.HTTP_POST.equalsIgnoreCase(toString)) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                }
                httpURLConnection.addRequestProperty(HttpConstant.HOST, request.getHost());
                if (NetworkStatusHelper.c().equals("cmwap")) {
                    httpURLConnection.setRequestProperty(HttpConstant.X_ONLINE_HOST, request.getHost());
                }
                Map headers = request.getHeaders();
                if (headers != null) {
                    for (Entry entry : headers.entrySet()) {
                        httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (!headers.containsKey(HttpConstant.ACCEPT_ENCODING)) {
                    httpURLConnection.addRequestProperty(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
                }
                if (fVar != null) {
                    httpURLConnection.setRequestProperty(HttpConstant.AUTHORIZATION, fVar.a());
                }
                if (url.getProtocol().equalsIgnoreCase(com.alipay.sdk.cons.b.a)) {
                    a(httpURLConnection, request);
                }
                httpURLConnection.setInstanceFollowRedirects(false);
                return httpURLConnection;
            } catch (Exception e) {
                return httpURLConnection;
            }
        } catch (Exception e2) {
            return httpURLConnection;
        }
    }

    private static void a(HttpURLConnection httpURLConnection, Request request) {
        if (Integer.parseInt(VERSION.SDK) < 8) {
            ALog.e("awcn.HttpConnector", "supportHttps", "[supportHttps]Froyo \u4ee5\u4e0b\u7248\u672c\u4e0d\u652f\u6301https", new Object[0]);
            return;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        if (e.a() != null) {
            httpsURLConnection.setSSLSocketFactory(e.a());
        }
        if (e.b() != null) {
            httpsURLConnection.setHostnameVerifier(e.b());
        } else if (request.isHostnameVerifyEnable()) {
            httpsURLConnection.setHostnameVerifier(new d(request));
        } else {
            httpsURLConnection.setHostnameVerifier(e.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
    }

    private static void b(HttpURLConnection httpURLConnection, Request request) {
        if (com.tencent.connect.common.Constants.HTTP_POST.equalsIgnoreCase(request.getMethod().toString())) {
            int postBody;
            long currentTimeMillis = System.currentTimeMillis();
            OutputStream outputStream = null;
            try {
                outputStream = httpURLConnection.getOutputStream();
                postBody = request.postBody(outputStream);
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (Throwable e) {
                        ALog.e("awcn.HttpConnector", "postData", request.getSeq(), e, new Object[0]);
                    }
                }
            } catch (Throwable e2) {
                try {
                    ALog.e("awcn.HttpConnector", "postData error", request.getSeq(), e2, new Object[0]);
                    if (outputStream != null) {
                        try {
                            outputStream.flush();
                            outputStream.close();
                            postBody = 0;
                        } catch (Throwable e22) {
                            ALog.e("awcn.HttpConnector", "postData", request.getSeq(), e22, new Object[0]);
                            postBody = 0;
                        }
                    } else {
                        postBody = 0;
                    }
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.flush();
                            outputStream.close();
                        } catch (Throwable e3) {
                            ALog.e("awcn.HttpConnector", "postData", request.getSeq(), e3, new Object[0]);
                        }
                    }
                }
            }
            request.rs.sendDataSize = (long) postBody;
            request.rs.sendDataTime = System.currentTimeMillis() - currentTimeMillis;
        }
    }

    private static void a(HttpURLConnection httpURLConnection, Request request, a aVar, RequestCb requestCb) throws IOException, CancellationException {
        int i = 0;
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
        } catch (Throwable e) {
            try {
                InputStream errorStream = httpURLConnection.getErrorStream();
            } catch (Throwable e2) {
                Throwable e22;
                ALog.e("awcn.HttpConnector", new StringBuilder("get error stream failed.").append(httpURLConnection.getURL().toString()).toString(), request.getSeq(), e22, new Object[0]);
                errorStream = null;
            }
            ALog.w("awcn.HttpConnector", httpURLConnection.getURL().toString(), null, e, new Object[0]);
            inputStream = errorStream;
        }
        if (inputStream == null) {
            a(request, aVar, requestCb, com.umeng.analytics.social.e.t, null);
            return;
        }
        OutputStream byteArrayOutputStream;
        int c = d.c(aVar.c);
        boolean b = d.b(aVar.c);
        if (b) {
            aVar.c.remove(HttpConstant.CONTENT_ENCODING);
        }
        if (requestCb == null || (b && c <= 1048576)) {
            if (c <= 0) {
                c = JsInterface.MSG_JS_COLLECT_WEBSITE;
            }
            byteArrayOutputStream = new ByteArrayOutputStream(c);
        } else {
            byteArrayOutputStream = null;
        }
        if (b) {
            try {
                errorStream = new GZIPInputStream(inputStream);
            } catch (Throwable th) {
                e22 = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                throw e22;
            }
        }
        errorStream = inputStream;
        anet.channel.a.a aVar2 = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (aVar2 == null) {
                    aVar2 = a.a.a((int) Message.FLAG_RET);
                }
                int a = aVar2.a(errorStream);
                if (a != -1) {
                    if (request.rs.firstDataTime == 0) {
                        request.rs.firstDataTime = System.currentTimeMillis() - request.rs.start;
                    }
                    i += a;
                    if (byteArrayOutputStream != null) {
                        aVar2.a(byteArrayOutputStream);
                    } else {
                        requestCb.onDataReceive(aVar2, false);
                        aVar2 = null;
                    }
                } else {
                    if (byteArrayOutputStream != null) {
                        aVar2.d();
                    } else {
                        requestCb.onDataReceive(aVar2, true);
                    }
                    request.rs.recDataTime = (System.currentTimeMillis() - request.rs.start) - request.rs.firstDataTime;
                    request.rs.recDataSize = (long) i;
                    if (byteArrayOutputStream != null) {
                        aVar.b = byteArrayOutputStream.toByteArray();
                        if (b) {
                            List arrayList = new ArrayList();
                            arrayList.add(String.valueOf(aVar.b.length));
                            aVar.c.put(HttpConstant.CONTENT_LENGTH, arrayList);
                        }
                        if (requestCb != null) {
                            requestCb.onResponseCode(aVar.a, aVar.c);
                            requestCb.onDataReceive(anet.channel.a.a.a(aVar.b), true);
                        }
                    }
                    if (errorStream != null) {
                        try {
                            errorStream.close();
                            return;
                        } catch (IOException e4) {
                        }
                    }
                    return;
                }
            } catch (Throwable th2) {
                inputStream = errorStream;
                e22 = th2;
            }
        }
        throw new CancellationException("task cancelled");
    }
}
