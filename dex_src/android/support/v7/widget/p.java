package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;

// compiled from: AppCompatBackgroundHelper.java
final class p {
    private final View a;
    private final r b;
    private ck c;
    private ck d;
    private ck e;

    p(View view, r rVar) {
        this.a = view;
        this.b = rVar;
    }

    final void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, R.styleable.ViewBackgroundHelper, i, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
            ColorStateList b = this.b.b(this.a.getContext(), obtainStyledAttributes.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1));
            if (b != null) {
                b(b);
            }
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
            ViewCompat.setBackgroundTintList(this.a, obtainStyledAttributes.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
            ViewCompat.setBackgroundTintMode(this.a, ao.a(obtainStyledAttributes.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1)));
        }
        obtainStyledAttributes.recycle();
    }

    final void a(int i) {
        b(this.b != null ? this.b.b(this.a.getContext(), i) : null);
    }

    final void a(ColorStateList colorStateList) {
        if (this.d == null) {
            this.d = new ck();
        }
        this.d.a = colorStateList;
        this.d.d = true;
        c();
    }

    final ColorStateList a() {
        return this.d != null ? this.d.a : null;
    }

    final void a(Mode mode) {
        if (this.d == null) {
            this.d = new ck();
        }
        this.d.b = mode;
        this.d.c = true;
        c();
    }

    final Mode b() {
        return this.d != null ? this.d.b : null;
    }

    final void c() {
        boolean z = false;
        Drawable background = this.a.getBackground();
        if (background != null) {
            if (VERSION.SDK_INT == 21) {
                if (this.e == null) {
                    this.e = new ck();
                }
                ck ckVar = this.e;
                ckVar.a = null;
                ckVar.d = false;
                ckVar.b = null;
                ckVar.c = false;
                ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.a);
                if (backgroundTintList != null) {
                    ckVar.d = true;
                    ckVar.a = backgroundTintList;
                }
                Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.a);
                if (backgroundTintMode != null) {
                    ckVar.c = true;
                    ckVar.b = backgroundTintMode;
                }
                if (ckVar.d || ckVar.c) {
                    r.a(background, ckVar, this.a.getDrawableState());
                    z = true;
                }
                if (z) {
                    return;
                }
            }
            if (this.d != null) {
                r.a(background, this.d, this.a.getDrawableState());
            } else if (this.c != null) {
                r.a(background, this.c, this.a.getDrawableState());
            }
        }
    }

    final void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.c == null) {
                this.c = new ck();
            }
            this.c.a = colorStateList;
            this.c.d = true;
        } else {
            this.c = null;
        }
        c();
    }
}
