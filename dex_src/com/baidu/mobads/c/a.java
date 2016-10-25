package com.baidu.mobads.c;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.e.d;
import com.baidu.mobads.vo.a.b;
import com.tencent.connect.common.Constants;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.Map;
import org.android.agoo.message.MessageService;

public class a {
    public static volatile String b;
    public static volatile String c;
    private static a d;
    private static boolean f;
    protected final IXAdLogger a;
    private Context e;

    static {
        d = new a();
        b = com.umeng.a.d;
        c = com.umeng.a.d;
        f = false;
    }

    public static a a() {
        return d;
    }

    private a() {
        this.a = m.a().f();
        new Handler(Looper.getMainLooper()).postDelayed(new b(this), 2000);
    }

    public void a(Context context) {
        if (this.e == null) {
            this.e = context;
        }
    }

    public void a(Context context, com.baidu.mobads.command.a aVar) {
        a(context, MessageService.MSG_ACCS_NOTIFY_DISMISS, aVar);
    }

    public void a(com.baidu.mobads.command.a aVar) {
    }

    public void b(Context context, com.baidu.mobads.command.a aVar) {
        a(context, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, aVar);
    }

    public void a(Context context, IXAppInfo iXAppInfo) {
        a(context, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, iXAppInfo);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("temp_for_feed_response_html")) {
            a(str, "400", null);
        } else if (!f) {
            a("temp_for_feed_response_html", "405", b + "___" + c);
            f = true;
        }
    }

    public void a(String str, String str2, String str3) {
        Builder appendQueryParameter = new Builder().appendQueryParameter("stacktrace", str2);
        appendQueryParameter.appendQueryParameter("ad", str3);
        a(str, "404", appendQueryParameter);
    }

    private void a(String str, String str2, Builder builder) {
        IXAdConstants p = m.a().p();
        IXAdCommonUtils m = m.a().m();
        if (builder == null) {
            builder = new Builder();
        }
        try {
            builder.appendQueryParameter(JsInterface.FUNPLAY_AD_TRPE, str2).appendQueryParameter(IXAdRequestInfo.P_VER, "8.27").appendQueryParameter("appsid", p.getAppSid()).appendQueryParameter(IXAdRequestInfo.V, new StringBuilder("android_").append(com.baidu.mobads.a.a.c).append("_4.1.30").toString()).appendQueryParameter(DownloadManager.COLUMN_REASON, str).appendQueryParameter(IXAdRequestInfo.OSV, VERSION.RELEASE).appendQueryParameter(IXAdRequestInfo.BDR, VERSION.SDK_INT).appendQueryParameter(IXAdRequestInfo.BRAND, m.getTextEncoder(Build.BRAND)).appendQueryParameter("pack", p.getAppPackageNameOfPublisher());
        } catch (Throwable e) {
            m.a().f().e(e);
        }
        d dVar = new d("https://mobads-logs.baidu.com/brwhis.log", com.umeng.a.d);
        dVar.a(builder);
        dVar.a(0);
        new com.baidu.mobads.openad.e.a().a(dVar);
    }

    private void a(Context context, String str, com.baidu.mobads.command.a aVar) {
        IXAppInfo a = com.baidu.mobads.command.a.a.a(aVar);
        if (a != null) {
            a(context, str, a);
        }
    }

    private void a(Context context, String str, IXAppInfo iXAppInfo) {
        b bVar = new b(context, iXAppInfo);
        bVar.b = iXAppInfo.getAdId();
        b(a(context, str, bVar.c()));
    }

    private void b(String str) {
        a(1, str);
    }

    private void a(int i, String str) {
        com.baidu.mobads.openad.e.a aVar = new com.baidu.mobads.openad.e.a();
        d dVar = new d(str, com.umeng.a.d);
        dVar.e = i;
        aVar.a(dVar, Boolean.valueOf(true));
    }

    private String a(Context context, String str, Map<String, String> map) {
        try {
            String encodeURIComponent;
            StringBuilder stringBuilder = new StringBuilder(new StringBuilder("type=").append(str).append(com.alipay.sdk.sys.a.b).toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            map.put(MsgConstant.KEY_TS, System.currentTimeMillis());
            IXAdCommonUtils m = m.a().m();
            for (String encodeURIComponent2 : map.keySet()) {
                String str2 = (String) map.get(encodeURIComponent2);
                if (encodeURIComponent2 != null && str2 != null) {
                    encodeURIComponent2 = m.encodeURIComponent(encodeURIComponent2);
                    str2 = m.encodeURIComponent(str2);
                    stringBuilder.append(encodeURIComponent2);
                    stringBuilder.append("=");
                    stringBuilder.append(str2);
                    stringBuilder.append(com.alipay.sdk.sys.a.b);
                    stringBuilder2.append(str2);
                    stringBuilder2.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
            }
            stringBuilder2.append("mobads,");
            encodeURIComponent2 = m.getMD5(stringBuilder2.toString());
            this.a.d(new StringBuilder("ExtraQuery.allValue:").append(stringBuilder2).toString());
            stringBuilder.append(new StringBuilder("vd=").append(encodeURIComponent2).append(com.alipay.sdk.sys.a.b).toString());
            this.a.d(new StringBuilder("ExtraQuery.params:").append(stringBuilder).toString());
            return "https://mobads-logs.baidu.com/dz.zb" + "?" + stringBuilder.toString();
        } catch (Throwable e) {
            this.a.d(e);
            return com.umeng.a.d;
        }
    }
}
