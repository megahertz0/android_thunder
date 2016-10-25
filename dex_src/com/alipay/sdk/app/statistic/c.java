package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.tid.b;
import com.alipay.sdk.util.h;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class c {
    public static final String A = "CheckClientExistEx";
    public static final String B = "CheckClientSignEx";
    public static final String C = "GetInstalledAppEx";
    public static final String D = "partner";
    public static final String E = "out_trade_no";
    public static final String F = "trade_no";
    public static final String a = "net";
    public static final String b = "biz";
    public static final String c = "cp";
    public static final String d = "auth";
    public static final String e = "third";
    public static final String f = "GetApdidEx";
    public static final String g = "GetApdidNull";
    public static final String h = "GetApdidTimeout";
    public static final String i = "GetUtdidEx";
    public static final String j = "GetPackageInfoEx";
    public static final String k = "NotIncludeSignatures";
    public static final String l = "GetInstalledPackagesEx";
    public static final String m = "GetPublicKeyFromSignEx";
    public static final String n = "H5PayNetworkError";
    public static final String o = "H5AuthNetworkError";
    public static final String p = "SSLError";
    public static final String q = "H5PayDataAnalysisError";
    public static final String r = "H5AuthDataAnalysisError";
    public static final String s = "PublicKeyUnmatch";
    public static final String t = "ClientBindFailed";
    public static final String u = "TriDesEncryptError";
    public static final String v = "TriDesDecryptError";
    public static final String w = "ClientBindException";
    public static final String x = "SaveTradeTokenError";
    public static final String y = "ClientBindServiceFailed";
    public static final String z = "BindWaitTimeoutEx";
    String G;
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P;

    public c(Context context) {
        this.O = a.d;
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        this.G = String.format("123456789,%s", new Object[]{format});
        this.I = a(context);
        format = a(com.alipay.sdk.cons.a.e);
        String a = a(com.alipay.sdk.cons.a.f);
        this.J = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{format, a});
        format = a(b.a().a);
        a = a(com.alipay.sdk.sys.b.a().c());
        this.K = String.format("%s,%s,-,-,-", new Object[]{format, a});
        format = a(com.alipay.sdk.util.a.d(context));
        a = anet.channel.strategy.dispatch.a.ANDROID;
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String str = SocializeConstants.OP_DIVIDER_MINUS;
        String a4 = a(com.alipay.sdk.util.a.a(context).a());
        String a5 = a(com.alipay.sdk.util.a.b(context).p);
        String a6 = a(com.alipay.sdk.util.a.a(context).b());
        this.L = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{format, a, a2, a3, str, a4, a5, "gw", a6});
        this.M = SocializeConstants.OP_DIVIDER_MINUS;
        this.N = SocializeConstants.OP_DIVIDER_MINUS;
        this.P = SocializeConstants.OP_DIVIDER_MINUS;
    }

    private boolean a() {
        return TextUtils.isEmpty(this.O);
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    private void a(String str, String str2, Throwable th, String str3) {
        a(str, str2, a(th), str3);
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = a.d;
        if (!TextUtils.isEmpty(this.O)) {
            str5 = str5 + "^";
        }
        this.O += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, a(str3), str4}));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, SocializeConstants.OP_DIVIDER_MINUS);
    }

    static String a(String str) {
        return TextUtils.isEmpty(str) ? a.d : str.replace("[", "\u3010").replace("]", "\u3011").replace(SocializeConstants.OP_OPEN_PAREN, "\uff08").replace(SocializeConstants.OP_CLOSE_PAREN, "\uff09").replace(MiPushClient.ACCEPT_TIME_SEPARATOR, "\uff0c").replace(SocializeConstants.OP_DIVIDER_MINUS, "=").replace("^", "~");
    }

    static String a(Throwable th) {
        if (th == null) {
            return a.d;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" \u300b ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " \u300b ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    private String b(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(this.O)) {
            return a.d;
        }
        String str3;
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split != null) {
            int length = split.length;
            str3 = null;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase(D)) {
                        split2[1].replace(h.f, a.d);
                    } else if (split2[0].equalsIgnoreCase(E)) {
                        str3 = split2[1].replace(h.f, a.d);
                    } else if (split2[0].equalsIgnoreCase(F)) {
                        str2 = split2[1].replace(h.f, a.d);
                    }
                }
            }
        } else {
            str3 = null;
        }
        str2 = a(str2);
        String a = a(a(str3));
        this.H = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{this.G, this.H, this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P});
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String b() {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        return String.format("123456789,%s", new Object[]{format});
    }

    private static String c(String str) {
        String str2;
        String str3 = null;
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split != null) {
            int length = split.length;
            str2 = null;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase(D)) {
                        split2[1].replace(h.f, a.d);
                    } else if (split2[0].equalsIgnoreCase(E)) {
                        str2 = split2[1].replace(h.f, a.d);
                    } else if (split2[0].equalsIgnoreCase(F)) {
                        str3 = split2[1].replace(h.f, a.d);
                    }
                }
            }
        } else {
            str2 = null;
        }
        str3 = a(str3);
        String a = a(a(str2));
        return String.format("%s,%s,-,%s,-,-,-", new Object[]{str3, str2, a});
    }

    private static String a(Context context) {
        String str = SocializeConstants.OP_DIVIDER_MINUS;
        String str2 = SocializeConstants.OP_DIVIDER_MINUS;
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }

    private static String c() {
        String a = a(com.alipay.sdk.cons.a.e);
        String a2 = a(com.alipay.sdk.cons.a.f);
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{a, a2});
    }

    private static String d() {
        String a = a(b.a().a);
        String a2 = a(com.alipay.sdk.sys.b.a().c());
        return String.format("%s,%s,-,-,-", new Object[]{a, a2});
    }

    private static String b(Context context) {
        String a = a(com.alipay.sdk.util.a.d(context));
        String str = anet.channel.strategy.dispatch.a.ANDROID;
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String str2 = SocializeConstants.OP_DIVIDER_MINUS;
        String a4 = a(com.alipay.sdk.util.a.a(context).a());
        String a5 = a(com.alipay.sdk.util.a.b(context).p);
        String a6 = a(com.alipay.sdk.util.a.a(context).b());
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{a, str, a2, a3, str2, a4, a5, "gw", a6});
    }
}
