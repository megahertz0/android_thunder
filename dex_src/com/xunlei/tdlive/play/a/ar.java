package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.util.r;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

// compiled from: ReplayProcessPresenter.java
public class ar {
    private a a;
    private b b;
    private r c;
    private final int d;
    private boolean e;

    // compiled from: ReplayProcessPresenter.java
    public static interface a {
        void a(int i);

        int i();

        int j();

        void k();

        void l();

        boolean m();
    }

    public ar() {
        this.d = 1000;
        this.e = true;
        f();
    }

    public int a() {
        return 0;
    }

    public int b() {
        return 0;
    }

    private void e() {
        int i = 0;
        if (this.a != null && this.b != null) {
            boolean m = this.a.m();
            int i2 = this.a.i();
            int j = this.a.j();
            if (this.e) {
                if (!m) {
                    this.e = false;
                }
                this.b.a(i);
                this.b.a(i2, a(i2) + "/" + a(i));
                this.b.a(m);
            }
            i = j;
            this.b.a(i);
            this.b.a(i2, a(i2) + "/" + a(i));
            this.b.a(m);
        }
    }

    private String a(int i) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            String format = simpleDateFormat.format(Integer.valueOf(i));
            return (format == null || format.length() <= 0) ? "00:00:00" : format;
        } catch (Exception e) {
            return "00:00:00";
        }
    }

    private void f() {
        if (this.c == null) {
            this.c = new r(1000, new as(this));
            this.c.a(false);
        }
    }

    public void c() {
        if (this.c != null) {
            this.c.b();
        }
    }

    public void d() {
        if (this.c != null) {
            this.c.c();
        }
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.b = bVar;
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.a = aVar;
        }
    }
}
