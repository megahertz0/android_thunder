package com.umeng.message.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import com.umeng.common.UmengMessageDeviceConfig;

// compiled from: MessageConstants.java
public class a {
    public static String a;
    public static Uri b;
    public static Uri c;
    public static Uri d;
    public static Uri e;
    public static Uri f;
    public static Uri g;
    public static Uri h;
    public static Uri i;
    public static Uri j;
    private static Context k;
    private static a l;
    private static String m;

    // compiled from: MessageConstants.java
    public static class a implements BaseColumns {
        public static final String a = "/MessageStores/";
        public static final String b = "/MsgTemps/";
        public static final String c = "/MsgAlias/";
        public static final String d = "/MsgAliasDeleteAll/";
        public static final String e = "/MsgLogStores/";
        public static final String f = "/MsgLogIdTypeStores/";
        public static final String g = "/MsgLogStoreForAgoos/";
        public static final String h = "/MsgLogIdTypeStoreForAgoos/";
        public static final String i = "/MsgConfigInfos/";
        public static final String j = "vnd.android.cursor.dir/vnd.umeng.message";
        public static final String k = "vnd.android.cursor.item/vnd.umeng.message";
        private static final String l = "content://";

        private a() {
        }
    }

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
    }

    private a() {
    }

    public static a a(Context context) {
        k = context;
        if (l == null) {
            l = new a();
            m = UmengMessageDeviceConfig.getPackageName(context);
            a = m + ".umeng.message";
            b = Uri.parse(new StringBuilder("content://").append(a).append(a.a).toString());
            c = Uri.parse(new StringBuilder("content://").append(a).append(a.b).toString());
            d = Uri.parse(new StringBuilder("content://").append(a).append(a.c).toString());
            e = Uri.parse(new StringBuilder("content://").append(a).append(a.d).toString());
            f = Uri.parse(new StringBuilder("content://").append(a).append(a.e).toString());
            g = Uri.parse(new StringBuilder("content://").append(a).append(a.f).toString());
            h = Uri.parse(new StringBuilder("content://").append(a).append(a.g).toString());
            i = Uri.parse(new StringBuilder("content://").append(a).append(a.h).toString());
            j = Uri.parse(new StringBuilder("content://").append(a).append(a.i).toString());
        }
        return l;
    }
}
