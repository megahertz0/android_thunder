package com.inmobi.ads;

import android.graphics.Point;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.util.Locale;

// compiled from: NativeStrandAssetStyle.java
class t {
    protected b a;
    protected a b;
    protected float c;
    protected String d;
    protected String e;
    private Point f;
    private Point g;

    // compiled from: NativeStrandAssetStyle.java
    enum a {
        BORDER_CORNER_STYLE_CURVED("curved"),
        BORDER_CORNER_STYLE_STRAIGHT("straight");
        private final String c;

        static {
            String str = "curved";
            a = new a("BORDER_CORNER_STYLE_CURVED", 0, "curved");
            str = "straight";
            b = new a("BORDER_CORNER_STYLE_STRAIGHT", 1, "straight");
            d = new a[]{a, b};
        }

        private a(String str) {
            this.c = str;
        }
    }

    // compiled from: NativeStrandAssetStyle.java
    enum b {
        BORDER_STROKE_STYLE_NONE(IXAdSystemUtils.NT_NONE),
        BORDER_STROKE_STYLE_LINE("line");
        private final String c;

        static {
            String str = IXAdSystemUtils.NT_NONE;
            a = new b("BORDER_STROKE_STYLE_NONE", 0, IXAdSystemUtils.NT_NONE);
            str = "line";
            b = new b("BORDER_STROKE_STYLE_LINE", 1, "line");
            d = new b[]{a, b};
        }

        private b(String str) {
            this.c = str;
        }
    }

    t() {
        this.f = new Point(0, 0);
        this.g = new Point(0, 0);
        this.a = b.a;
        this.b = a.b;
        this.c = 10.0f;
        this.d = "#ff000000";
        this.e = "#00000000";
    }

    public t(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2) {
        this.f = new Point(i3, i4);
        this.g = new Point(i, i2);
        this.a = bVar;
        this.b = aVar;
        this.c = 10.0f;
        if (str.length() == 0) {
            str = "#ff000000";
        }
        this.d = str;
        if (str2.length() == 0) {
            str2 = "#00000000";
        }
        this.e = str2;
    }

    public Point a() {
        return this.f;
    }

    public Point b() {
        return this.g;
    }

    public b c() {
        return this.a;
    }

    public a d() {
        return this.b;
    }

    public float e() {
        return this.c;
    }

    public String f() {
        return this.d.toLowerCase(Locale.US);
    }

    public String g() {
        return this.e.toLowerCase(Locale.US);
    }
}
