package com.xunlei.downloadprovider.c.a;

import com.xunlei.downloadprovider.web.base.model.u;
import java.io.Serializable;
import java.util.ArrayList;

// compiled from: CommentInfo.java
public final class c implements Serializable {
    public long a;
    public String b;
    public long c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public long i;
    public String j;
    public String k;
    public boolean l;
    public boolean m;
    public long n;
    public int o;
    public long p;
    public ArrayList<n> q;
    public boolean r;
    public u s;

    public c() {
        this.r = false;
    }

    public final void a(String str) {
        if (str == null) {
            this.b = str;
        } else {
            this.b = str.trim();
        }
    }
}
