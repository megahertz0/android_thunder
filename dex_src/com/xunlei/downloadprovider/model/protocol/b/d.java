package com.xunlei.downloadprovider.model.protocol.b;

import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.downloadprovider.model.protocol.c.a;
import java.io.Serializable;

// compiled from: FunInfo.java
public final class d implements Serializable {
    public long a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public String h;
    public String i;
    public long j;
    public int k;
    public int l;
    public int m;
    public long n;
    public int o;
    public String p;
    public int q;
    public a r;

    public static String a(int i) {
        return i == 0 ? ShareActivity.KEY_PIC : com.umeng.a.d;
    }

    public final int a() {
        return "gif".equalsIgnoreCase(this.h) ? 1 : 0;
    }

    public static String b(int i) {
        return i > 10000 ? (i / 10000) + "\u4e07" : String.valueOf(i);
    }
}
