package com.xunlei.tdlive.aniengine;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

// compiled from: TextSprite.java
public class z extends y {
    private a a;

    // compiled from: TextSprite.java
    static class a extends Drawable {
        Paint a;
        String b;
        float c;
        int d;
        int e;
        int f;
        int g;
        float h;
        Typeface i;

        a() {
            this.a = new Paint();
        }

        public void a(com.xunlei.tdlive.aniengine.a.a aVar) {
            this.a.setTextSize(aVar.a(this.c));
            this.a.setColor(this.d);
            this.a.setShadowLayer(this.h, (float) this.e, (float) this.f, this.g);
            this.a.setTypeface(this.i);
            setBounds(0, 0, (int) this.a.measureText(this.b), (int) this.a.getTextSize());
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a a(float f) {
            this.c = f;
            return this;
        }

        public a a(int i) {
            this.d = i;
            return this;
        }

        public a a(float f, int i, int i2, int i3) {
            this.e = i;
            this.f = i2;
            this.g = i3;
            this.h = f;
            return this;
        }

        public a a(Typeface typeface) {
            this.i = typeface;
            return this;
        }

        public void draw(Canvas canvas) {
            int measureText = (int) this.a.measureText(this.b);
            Rect bounds = getBounds();
            canvas.drawText(this.b, (float) (((bounds.width() / 2) + bounds.left) - (measureText / 2)), (float) ((int) this.a.getTextSize()), this.a);
        }

        public void setAlpha(int i) {
            this.a.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.a.setColorFilter(colorFilter);
        }

        public int getOpacity() {
            return 0;
        }
    }

    public static z a(String str) {
        return new z(str);
    }

    public z(String str) {
        this.a = new a();
        b(str);
    }

    public z b(String str) {
        this.a.a(str);
        return this;
    }

    public z a(Typeface typeface) {
        this.a.a(typeface);
        return this;
    }

    public z b(float f) {
        this.a.a(f);
        return this;
    }

    public z b(int i) {
        this.a.a(i);
        return this;
    }

    public z a(float f, int i, int i2, int i3) {
        this.a.a(f, i, i2, i3);
        return this;
    }

    protected boolean a(com.xunlei.tdlive.aniengine.a.a aVar) {
        if (b() == null) {
            this.a.a(aVar);
            a(this.a);
        }
        return b() != null;
    }
}
