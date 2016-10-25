package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.h;

public final class a {
    public static final String a = "alipay_cashier_statistic_record";
    private static c b;

    public static void a(Context context) {
        if (b == null) {
            b = new c(context);
        }
    }

    private static void b(Context context, String str) {
        new Thread(new b(context, str)).start();
    }

    public static synchronized void a(Context context, String str) {
        String str2 = null;
        synchronized (a.class) {
            if (b != null) {
                c cVar = b;
                if (TextUtils.isEmpty(cVar.O)) {
                    str2 = com.umeng.a.d;
                } else {
                    String str3;
                    String[] split = str.split(com.alipay.sdk.sys.a.b);
                    if (split != null) {
                        int length = split.length;
                        str3 = null;
                        for (int i = 0; i < length; i++) {
                            String[] split2 = split[i].split("=");
                            if (split2 != null && split2.length == 2) {
                                if (split2[0].equalsIgnoreCase(c.D)) {
                                    split2[1].replace(h.f, com.umeng.a.d);
                                } else if (split2[0].equalsIgnoreCase(c.E)) {
                                    str3 = split2[1].replace(h.f, com.umeng.a.d);
                                } else if (split2[0].equalsIgnoreCase(c.F)) {
                                    str2 = split2[1].replace(h.f, com.umeng.a.d);
                                }
                            }
                        }
                    } else {
                        str3 = null;
                    }
                    str2 = c.a(str2);
                    String a = c.a(c.a(str3));
                    cVar.H = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
                    str2 = String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{cVar.G, cVar.H, cVar.I, cVar.J, cVar.K, cVar.L, cVar.M, cVar.N, cVar.O, cVar.P});
                }
                new Thread(new b(context, str2)).start();
                b = null;
            }
        }
    }

    public static void a(String str, Throwable th) {
        if (b != null && th.getClass() != null) {
            b.a(str, th.getClass().getSimpleName(), th);
        }
    }

    private static void a(String str, String str2, Throwable th, String str3) {
        if (b != null) {
            b.a(str, str2, c.a(th), str3);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (b != null) {
            b.a(str, str2, th);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (b != null) {
            b.a(str, str2, str3);
        }
    }
}
