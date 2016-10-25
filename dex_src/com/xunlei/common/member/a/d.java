package com.xunlei.common.member.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.AES;
import com.xunlei.common.encrypt.Base64;
import java.io.UnsupportedEncodingException;

// compiled from: XLAutoLoginParcel.java
public class d {
    private static final String e = "user-aes-key";
    public final int a;
    public final String b;
    public final String c;
    public final String d;

    // compiled from: XLAutoLoginParcel.java
    public enum a {
        ;

        private static int[] a() {
            return (int[]) d.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = new int[]{1, 2, 3};
        }
    }

    public d(int i, String str, String str2, String str3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final boolean a() {
        if (this.a != 0) {
            return (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.d)) ? false : true;
        } else {
            return false;
        }
    }

    public static d a(Context context) {
        d dVar = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("xl-acc-auto-login", 0);
        int i = sharedPreferences.getInt(ParamKey.UID, 0);
        if (i != 0) {
            String string = sharedPreferences.getString("epsw", com.umeng.a.d);
            String string2 = sharedPreferences.getString("pcs", com.umeng.a.d);
            String string3 = sharedPreferences.getString("lkey", com.umeng.a.d);
            if (sharedPreferences.getInt("pecd", 0) == 1) {
                string = b(string);
                string2 = b(string2);
            }
            if (sharedPreferences.getInt("kecd", 0) == 1) {
                string3 = b(string3);
            }
            dVar = new d(i, string, string2, string3);
        }
        XLLog.v("XLAutoLoginParcel", new StringBuilder("retrieveAutoLoginParcel uid = ").append(i).toString());
        return dVar;
    }

    public static void a(d dVar, Context context, int i) {
        Editor edit = context.getSharedPreferences("xl-acc-auto-login", 0).edit();
        edit.putInt(ParamKey.UID, dVar.a);
        if (a.a == i || a.c == i) {
            edit.putString("epsw", a(dVar.b));
            edit.putString("pcs", a(dVar.c));
            edit.putInt("pecd", 1);
        }
        if (a.b == i || a.c == i) {
            edit.putString("lkey", a(dVar.d));
            edit.putInt("kecd", 1);
        }
        edit.commit();
        XLLog.v("XLAutoLoginParcel", new StringBuilder("saveAutoLoginParcel uid = ").append(dVar.a).toString());
    }

    public static void a(Context context, int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("xl-acc-auto-login", 0);
        int i2 = sharedPreferences.getInt(ParamKey.UID, 0);
        if (i2 != 0) {
            Editor edit = sharedPreferences.edit();
            if (a.c == i) {
                edit.putInt(ParamKey.UID, 0);
            }
            if (a.a == i || a.c == i) {
                edit.putString("epsw", com.umeng.a.d);
                edit.putString("pcs", com.umeng.a.d);
            }
            if (a.b == i || a.c == i) {
                edit.putString("lkey", com.umeng.a.d);
            }
            edit.commit();
        }
        XLLog.v("XLAutoLoginParcel", new StringBuilder("removeAutoLoginParcel uid = ").append(i2).toString());
    }

    private static String a(String str) {
        return !TextUtils.isEmpty(str) ? Base64.encode(AES.encrypt(str, e)) : com.umeng.a.d;
    }

    private static String b(String str) {
        String str2 = com.umeng.a.d;
        if (!TextUtils.isEmpty(str)) {
            try {
                return new String(AES.decrypt(Base64.decode(str), e), GameManager.DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static String b() {
        return VERSION.RELEASE;
    }

    public static String c() {
        return Build.MODEL;
    }

    public static String d() {
        String toLowerCase = Build.MANUFACTURER.toLowerCase();
        String toLowerCase2 = Build.MODEL.toLowerCase();
        if (toLowerCase2.startsWith(toLowerCase)) {
            return c(toLowerCase2);
        }
        Object c = c(toLowerCase);
        return TextUtils.isEmpty(c) ? c(toLowerCase2) : c + " " + c(toLowerCase2);
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        char[] toCharArray = str.toCharArray();
        String str2 = com.umeng.a.d;
        int length = toCharArray.length;
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            char c = toCharArray[i2];
            Object obj;
            if (obj == null || !Character.isLetter(c)) {
                if (Character.isWhitespace(c)) {
                    i = 1;
                }
                str2 = str2 + c;
            } else {
                str2 = str2 + Character.toUpperCase(c);
                obj = null;
            }
        }
        return str2;
    }
}
