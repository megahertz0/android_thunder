package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.util.k;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    public static final String a = "\"&";
    public static final String b = "&";
    public static final String c = "bizcontext=\"";
    public static final String d = "bizcontext=";
    public static final String e = "\"";
    public static final String f = "appkey";
    public static final String g = "ty";
    public static final String h = "sv";
    public static final String i = "an";
    public static final String j = "setting";
    public static final String k = "av";
    public static final String l = "sdk_start_time";
    public static final String m = "UTF-8";
    private String n;
    private String o;
    private Context p;

    public a(Context context) {
        this.n = com.umeng.a.d;
        this.o = com.umeng.a.d;
        this.p = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.n = packageInfo.versionName;
            this.o = packageInfo.packageName;
            this.p = context.getApplicationContext();
        } catch (Exception e) {
        }
    }

    public final String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        return (!str.contains(a) ? 1 : null) != null ? c(str) : d(str);
    }

    private static boolean b(String str) {
        return !str.contains(a);
    }

    private String c(String str) {
        try {
            String a = a(str, b, d);
            if (TextUtils.isEmpty(a)) {
                return str + b + b(d, com.umeng.a.d);
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + b(a, d, com.umeng.a.d) + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    private String d(String str) {
        try {
            String a = a(str, a, c);
            if (TextUtils.isEmpty(a)) {
                return str + b + b(c, e);
            }
            if (!a.endsWith(e)) {
                a = a + e;
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + b(a, c, e) + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    private static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str4;
        String[] split = str.split(str2);
        int i = 0;
        while (i < split.length) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                str4 = split[i];
                break;
            }
            i++;
        }
        str4 = null;
        return str4;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + a(com.umeng.a.d, com.umeng.a.d) + str2;
    }

    public final String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f, com.alipay.sdk.cons.a.c);
            jSONObject.put(g, "and_lite");
            jSONObject.put(h, com.alipay.sdk.cons.a.f);
            if (!(this.o.contains(j) && k.c(this.p))) {
                jSONObject.put(i, this.o);
            }
            jSONObject.put(k, this.n);
            jSONObject.put(l, System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            return com.umeng.a.d;
        }
    }

    private String b(String str, String str2, String str3) throws JSONException, UnsupportedEncodingException {
        String substring = str.substring(str2.length());
        JSONObject jSONObject = new JSONObject(substring.substring(0, substring.length() - str3.length()));
        if (!jSONObject.has(f)) {
            jSONObject.put(f, com.alipay.sdk.cons.a.c);
        }
        if (!jSONObject.has(g)) {
            jSONObject.put(g, "and_lite");
        }
        if (!jSONObject.has(h)) {
            jSONObject.put(h, com.alipay.sdk.cons.a.f);
        }
        if (!jSONObject.has(i)) {
            if (!(this.o.contains(j) && k.c(this.p))) {
                jSONObject.put(i, this.o);
            }
        }
        if (!jSONObject.has(k)) {
            jSONObject.put(k, this.n);
        }
        if (!jSONObject.has(l)) {
            jSONObject.put(l, System.currentTimeMillis());
        }
        return str2 + jSONObject.toString() + str3;
    }
}
