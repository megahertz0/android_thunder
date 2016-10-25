package com.xunlei.downloadprovider.download.util;

import android.text.TextUtils;

// compiled from: EpisodeUtil.java
public final class c {
    private static b a;

    // compiled from: EpisodeUtil.java
    public static class a {
        public int a;
        public int b;
        public String c;
        public String d;

        public a() {
            this.a = -1;
            this.b = -1;
            this.c = com.umeng.a.d;
            this.d = com.umeng.a.d;
        }
    }

    static {
        a = new b();
    }

    public static a a(String str) {
        b bVar = a;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a aVar = new a();
        a[] aVarArr = new a[]{bVar.e, bVar.d, bVar.f};
        for (int i = 0; i < 3; i++) {
            a a = aVarArr[i].a(str);
            if (a != null) {
                if (a.b > 0 && a.a > 0) {
                    return a;
                }
                if (a.b > 0) {
                    aVar.b = a.b;
                }
                if (a.a > 0) {
                    aVar.a = a.a;
                }
                if (!TextUtils.isEmpty(a.c)) {
                    aVar.c = a.c;
                }
                if (!TextUtils.isEmpty(a.d)) {
                    aVar.d = a.d;
                }
            }
        }
        return aVar;
    }
}
