package com.xunlei.common.pay.a;

import com.xunlei.xiazaibao.BuildConfig;

// compiled from: XLPayResult.java
public final class d {
    public String a;
    public String b;
    private String c;

    public d(String str) {
        this.a = BuildConfig.VERSION_NAME;
        this.b = BuildConfig.VERSION_NAME;
        this.c = BuildConfig.VERSION_NAME;
        try {
            for (String str2 : str.split(";")) {
                if (str2.startsWith("resultStatus")) {
                    this.a = a(str2, "resultStatus");
                }
                if (str2.startsWith("result")) {
                    this.b = a(str2, "result");
                }
                if (str2.startsWith("memo")) {
                    this.c = a(str2, "memo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        return new StringBuilder("resultStatus : ").append(this.a).append(", result = ").append(this.b).append(", memo = ").append(this.c).toString();
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.indexOf("}"));
    }
}
