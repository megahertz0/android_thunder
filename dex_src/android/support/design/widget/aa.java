package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.R;
import android.view.ViewTreeObserver.OnPreDrawListener;

// compiled from: FloatingActionButtonImpl.java
abstract class aa {
    static final int[] h;
    static final int[] i;
    static final int[] j;
    private final Rect a;
    Drawable b;
    Drawable c;
    j d;
    Drawable e;
    float f;
    float g;
    final VisibilityAwareImageButton k;
    final ah l;
    OnPreDrawListener m;

    // compiled from: FloatingActionButtonImpl.java
    static interface a {
    }

    abstract float a();

    abstract void a(float f);

    abstract void a(int i);

    abstract void a(ColorStateList colorStateList);

    abstract void a(ColorStateList colorStateList, Mode mode, int i, int i2);

    abstract void a(Mode mode);

    abstract void a(Rect rect);

    abstract void a(int[] iArr);

    abstract void b();

    abstract void b(float f);

    abstract void c();

    abstract void d();

    abstract void e();

    static {
        h = new int[]{16842919, 16842910};
        i = new int[]{16842908, 16842910};
        j = new int[0];
    }

    aa(VisibilityAwareImageButton visibilityAwareImageButton, ah ahVar) {
        this.a = new Rect();
        this.k = visibilityAwareImageButton;
        this.l = ahVar;
    }

    final void c(float f) {
        if (this.f != f) {
            this.f = f;
            a(f);
        }
    }

    final void h() {
        Rect rect = this.a;
        a(rect);
        b(rect);
        this.l.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    void b(Rect rect) {
    }

    boolean f() {
        return false;
    }

    final j a(int i, ColorStateList colorStateList) {
        Resources resources = this.k.getResources();
        j i2 = i();
        int color = resources.getColor(R.color.design_fab_stroke_top_outer_color);
        int color2 = resources.getColor(R.color.design_fab_stroke_top_inner_color);
        int color3 = resources.getColor(R.color.design_fab_stroke_end_inner_color);
        int color4 = resources.getColor(R.color.design_fab_stroke_end_outer_color);
        i2.e = color;
        i2.f = color2;
        i2.g = color3;
        i2.h = color4;
        float f = (float) i;
        if (i2.d != f) {
            i2.d = f;
            i2.a.setStrokeWidth(f * 1.3333f);
            i2.i = true;
            i2.invalidateSelf();
        }
        i2.a(colorStateList);
        return i2;
    }

    j i() {
        return new j();
    }

    void g() {
    }

    static GradientDrawable j() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(-1);
        return gradientDrawable;
    }
}
