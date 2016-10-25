package android.support.design.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

@TargetApi(21)
// compiled from: FloatingActionButtonLollipop.java
final class ac extends x {
    private final Interpolator n;
    private InsetDrawable o;

    ac(VisibilityAwareImageButton visibilityAwareImageButton, ah ahVar) {
        super(visibilityAwareImageButton, ahVar);
        this.n = visibilityAwareImageButton.isInEditMode() ? null : AnimationUtils.loadInterpolator(this.k.getContext(), 17563661);
    }

    final void a(ColorStateList colorStateList, Mode mode, int i, int i2) {
        Drawable layerDrawable;
        this.b = DrawableCompat.wrap(j());
        DrawableCompat.setTintList(this.b, colorStateList);
        if (mode != null) {
            DrawableCompat.setTintMode(this.b, mode);
        }
        if (i2 > 0) {
            this.d = a(i2, colorStateList);
            layerDrawable = new LayerDrawable(new Drawable[]{this.d, this.b});
        } else {
            this.d = null;
            layerDrawable = this.b;
        }
        this.c = new RippleDrawable(ColorStateList.valueOf(i), layerDrawable, null);
        this.e = this.c;
        this.l.a(this.c);
    }

    final void a(int i) {
        if (this.c instanceof RippleDrawable) {
            ((RippleDrawable) this.c).setColor(ColorStateList.valueOf(i));
        } else {
            super.a(i);
        }
    }

    public final void a(float f) {
        this.k.setElevation(f);
        if (this.l.b()) {
            h();
        }
    }

    final void b(float f) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(h, a(ObjectAnimator.ofFloat(this.k, "translationZ", new float[]{f})));
        stateListAnimator.addState(i, a(ObjectAnimator.ofFloat(this.k, "translationZ", new float[]{f})));
        stateListAnimator.addState(j, a(ObjectAnimator.ofFloat(this.k, "translationZ", new float[]{0.0f})));
        this.k.setStateListAnimator(stateListAnimator);
        if (this.l.b()) {
            h();
        }
    }

    public final float a() {
        return this.k.getElevation();
    }

    final void e() {
        h();
    }

    final void b(Rect rect) {
        if (this.l.b()) {
            this.o = new InsetDrawable(this.c, rect.left, rect.top, rect.right, rect.bottom);
            this.l.a(this.o);
            return;
        }
        this.l.a(this.c);
    }

    final void a(int[] iArr) {
    }

    final void b() {
    }

    final boolean f() {
        return false;
    }

    private Animator a(Animator animator) {
        animator.setInterpolator(this.n);
        return animator;
    }

    final j i() {
        return new k();
    }

    final void a(Rect rect) {
        if (this.l.b()) {
            float a = this.l.a();
            float elevation = this.k.getElevation() + this.g;
            int ceil = (int) Math.ceil((double) ag.b(elevation, a, false));
            int ceil2 = (int) Math.ceil((double) ag.a(elevation, a, false));
            rect.set(ceil, ceil2, ceil, ceil2);
            return;
        }
        rect.set(0, 0, 0, 0);
    }
}
