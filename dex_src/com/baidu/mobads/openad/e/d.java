package com.baidu.mobads.openad.e;

import android.net.Uri.Builder;
import com.baidu.mobads.j.m;

public class d {
    public String a;
    public String b;
    public long c;
    public String d;
    public int e;
    private Builder f;

    public d(String str, String str2) {
        this.c = 0;
        this.d = "text/plain";
        this.e = 1;
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return m.a().i().getFixedString(this.a);
    }

    public Builder b() {
        return this.f;
    }

    public void a(Builder builder) {
        this.f = builder;
    }

    public void a(int i) {
        this.e = i;
    }
}
