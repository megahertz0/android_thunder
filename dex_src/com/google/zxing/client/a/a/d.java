package com.google.zxing.client.a.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import com.google.zxing.client.a.b;

// compiled from: HimCameraManager.java
public final class d {
    private static final String j;
    private static d k;
    public a a;
    public final b b;
    public Camera c;
    public Rect d;
    public Rect e;
    public boolean f;
    public boolean g;
    public final e h;
    public final a i;
    private final Context l;

    // compiled from: HimCameraManager.java
    public static interface a {
        Rect a(Point point);
    }

    static {
        j = d.class.getSimpleName();
    }

    public static void a(Context context) {
        if (k == null) {
            k = new d(context);
        }
    }

    public static void a() {
        k = null;
    }

    public static d b() {
        return k;
    }

    private d(Context context) {
        this.l = context;
        this.b = new b(context);
        this.h = new e(this.b);
        this.i = new a(this.b);
    }

    public final void c() {
        if (this.c != null) {
            try {
                Object obj;
                if (Build.MODEL.toLowerCase().contains("x10i")) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    this.c.cancelAutoFocus();
                }
            } catch (Exception e) {
            }
        }
    }

    public final void a(Handler handler) {
        if (this.c != null && this.g) {
            this.h.a(handler, 1);
            this.c.setOneShotPreviewCallback(this.h);
        }
    }

    public final Rect d() {
        Point point = this.b.b;
        if (this.d == null && this.a != null) {
            this.d = this.a.a(point);
        }
        return this.d;
    }

    public final b a(byte[] bArr, int i, int i2) {
        Rect rect;
        if (this.e == null) {
            rect = new Rect(d());
            Point point = this.b.c;
            rect.top = 3;
            rect.left = 3;
            rect.right = point.x - 10;
            rect.bottom = point.y - 10;
            this.e = rect;
        }
        rect = this.e;
        if (rect == null) {
            return null;
        }
        return new b(bArr, i, i2, rect.left, rect.top, rect.width(), rect.height());
    }
}
