package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.CompoundButton;

// compiled from: AppCompatCompoundButtonHelper.java
final class q {
    ColorStateList a;
    Mode b;
    private final CompoundButton c;
    private final r d;
    private boolean e;
    private boolean f;
    private boolean g;

    q(CompoundButton compoundButton, r rVar) {
        this.a = null;
        this.b = null;
        this.e = false;
        this.f = false;
        this.c = compoundButton;
        this.d = rVar;
    }

    final void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.c.getContext().obtainStyledAttributes(attributeSet, R.styleable.CompoundButton, i, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.CompoundButton_android_button)) {
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CompoundButton_android_button, 0);
            if (resourceId != 0) {
                this.c.setButtonDrawable(this.d.a(this.c.getContext(), resourceId, false));
            }
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CompoundButton_buttonTint)) {
            CompoundButtonCompat.setButtonTintList(this.c, obtainStyledAttributes.getColorStateList(R.styleable.CompoundButton_buttonTint));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CompoundButton_buttonTintMode)) {
            CompoundButtonCompat.setButtonTintMode(this.c, ao.a(obtainStyledAttributes.getInt(R.styleable.CompoundButton_buttonTintMode, -1)));
        }
        obtainStyledAttributes.recycle();
    }

    final void a(ColorStateList colorStateList) {
        this.a = colorStateList;
        this.e = true;
        b();
    }

    final void a(Mode mode) {
        this.b = mode;
        this.f = true;
        b();
    }

    final void a() {
        if (this.g) {
            this.g = false;
            return;
        }
        this.g = true;
        b();
    }

    private void b() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.c);
        if (buttonDrawable == null) {
            return;
        }
        if (this.e || this.f) {
            buttonDrawable = DrawableCompat.wrap(buttonDrawable).mutate();
            if (this.e) {
                DrawableCompat.setTintList(buttonDrawable, this.a);
            }
            if (this.f) {
                DrawableCompat.setTintMode(buttonDrawable, this.b);
            }
            if (buttonDrawable.isStateful()) {
                buttonDrawable.setState(this.c.getDrawableState());
            }
            this.c.setButtonDrawable(buttonDrawable);
        }
    }

    final int a(int i) {
        if (VERSION.SDK_INT >= 17) {
            return i;
        }
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.c);
        return buttonDrawable != null ? i + buttonDrawable.getIntrinsicWidth() : i;
    }
}
