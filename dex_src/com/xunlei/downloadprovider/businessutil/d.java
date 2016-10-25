package com.xunlei.downloadprovider.businessutil;

import android.text.TextUtils;
import com.xunlei.xllib.b.g;

// compiled from: XLTaskUrlHelper.java
public final class d {

    // compiled from: XLTaskUrlHelper.java
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public long e;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str) || str == null || !str.contains("gcidtask.xunlei.com")) {
            return null;
        }
        a aVar = new a();
        aVar.a = str;
        aVar.b = a(str, "gcidtask.xunlei.com/", "?fid=");
        String a = a(str, "?fid=", "&tid=");
        String a2 = a(str, "&tid=", "&srcid=");
        String a3 = g.a(a.getBytes());
        if (a3 == null || !a3.equals(a2)) {
            return null;
        }
        String[] split = a.split("\\|");
        aVar.c = split[0];
        if (split.length == 3) {
            aVar.d = split[2];
        } else {
            aVar.d = null;
        }
        aVar.e = Long.parseLong(split[1]);
        return aVar;
    }

    private static String a(String str, String str2, String str3) {
        String str4 = com.umeng.a.d;
        try {
            return str.substring(str.indexOf(str2) + str2.length(), str.indexOf(str3));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return str4;
        }
    }
}
