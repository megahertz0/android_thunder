package com.inmobi.rendering.mraid;

import java.util.Locale;

// compiled from: MediaPlayerProperties.java
public class f {
    public String a;
    public String b;
    public String c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;

    public f() {
        this.a = "anonymous";
        this.b = "fullscreen";
        this.c = "exit";
        this.d = true;
        this.e = true;
        this.f = false;
        this.g = false;
    }

    public boolean a() {
        return "fullscreen".equals(this.b.toLowerCase(Locale.ENGLISH));
    }

    public boolean b() {
        return "exit".equals(this.c.toLowerCase(Locale.ENGLISH));
    }
}
