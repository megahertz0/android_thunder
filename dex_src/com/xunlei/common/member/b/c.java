package com.xunlei.common.member.b;

import android.text.TextUtils;

// compiled from: XLAlipayAuthResult.java
public final class c {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    public c(String str) {
        if (!TextUtils.isEmpty(str)) {
            int indexOf;
            String[] split = str.split(";");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String str2 = split[i];
                if (str2.startsWith("resultStatus=")) {
                    this.a = a(str2, "resultStatus");
                }
                if (str2.startsWith("result=")) {
                    this.b = a(str2, "result");
                    String str3 = "result" + "={";
                    indexOf = str2.indexOf("&sign_type");
                    if (indexOf == -1) {
                        indexOf = str2.lastIndexOf("}");
                    }
                    this.i = str2.substring(str3.length() + str2.indexOf(str3), indexOf);
                }
                if (str2.startsWith("memo=")) {
                    this.c = a(str2, "memo");
                }
            }
            String[] split2 = this.b.split("&");
            int length2 = split2.length;
            for (indexOf = 0; indexOf < length2; indexOf++) {
                String str4 = split2[indexOf];
                if (str4.startsWith("alipay_open_id=")) {
                    this.f = a(str4);
                }
                if (str4.startsWith("auth_code=")) {
                    this.e = a(str4);
                }
                if (str4.startsWith("result_code=")) {
                    this.d = a(str4);
                }
                if (str4.startsWith("sign_type=")) {
                    this.g = a(str4);
                }
                if (str4.startsWith("sign=")) {
                    this.h = str4.substring(str4.indexOf("=\"") + 2, str4.length() - 1);
                }
            }
        }
    }

    public final String toString() {
        return new StringBuilder("resultStatus={").append(this.a).append("};memo={").append(this.c).append("};result={").append(this.b).append("}").toString();
    }

    private String g() {
        return this.g;
    }

    public final String a() {
        return this.h;
    }

    public final String b() {
        return this.a;
    }

    private String h() {
        return this.c;
    }

    public final String c() {
        return this.i;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf("}"));
    }

    private static String a(String str) {
        String str2 = str.split("=\"")[1];
        return str2.substring(0, str2.lastIndexOf("\""));
    }

    private static String b(String str) {
        return str.substring(str.indexOf("=\"") + 2, str.length() - 1);
    }

    private static String b(String str, String str2) {
        String str3 = str2 + "={";
        int indexOf = str.indexOf("&sign_type");
        if (indexOf == -1) {
            indexOf = str.lastIndexOf("}");
        }
        return str.substring(str3.length() + str.indexOf(str3), indexOf);
    }
}
