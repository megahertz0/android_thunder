package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.R;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.StateSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import com.taobao.accs.common.Constants;
import java.lang.ref.WeakReference;

// compiled from: FloatingActionButtonEclairMr1.java
class u extends aa {
    ag a;
    private int n;
    private at o;
    private boolean p;

    // compiled from: FloatingActionButtonEclairMr1.java
    private abstract class a extends Animation {
        private float b;
        private float c;

        protected abstract float a();

        private a() {
        }

        public void reset() {
            super.reset();
            this.b = u.this.j;
            this.c = a() - this.b;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            ag agVar = u.this;
            agVar.a(this.b + (this.c * f), agVar.h);
        }
    }

    // compiled from: FloatingActionButtonEclairMr1.java
    private class b extends a {
        private b() {
            super((byte) 0);
        }

        protected final float a() {
            return u.this.f + u.this.g;
        }
    }

    // compiled from: FloatingActionButtonEclairMr1.java
    private class c extends a {
        private c() {
            super((byte) 0);
        }

        protected final float a() {
            return u.this.f;
        }
    }

    u(VisibilityAwareImageButton visibilityAwareImageButton, ah ahVar) {
        super(visibilityAwareImageButton, ahVar);
        this.n = visibilityAwareImageButton.getResources().getInteger(17694720);
        this.o = new at();
        at atVar = this.o;
        View a = atVar.a();
        if (a != visibilityAwareImageButton) {
            if (a != null) {
                View a2 = atVar.a();
                int size = atVar.a.size();
                for (int i = 0; i < size; i++) {
                    if (a2.getAnimation() == ((a) atVar.a.get(i)).b) {
                        a2.clearAnimation();
                    }
                }
                atVar.d = null;
                atVar.b = null;
                atVar.c = null;
            }
            if (visibilityAwareImageButton != null) {
                atVar.d = new WeakReference(visibilityAwareImageButton);
            }
        }
        this.o.a(h, a(new b()));
        this.o.a(i, a(new b()));
        this.o.a(j, a(new c()));
    }

    void a(ColorStateList colorStateList, Mode mode, int i, int i2) {
        Drawable[] drawableArr;
        this.b = DrawableCompat.wrap(j());
        DrawableCompat.setTintList(this.b, colorStateList);
        if (mode != null) {
            DrawableCompat.setTintMode(this.b, mode);
        }
        this.c = DrawableCompat.wrap(j());
        DrawableCompat.setTintList(this.c, b(i));
        if (i2 > 0) {
            this.d = a(i2, colorStateList);
            drawableArr = new Drawable[]{this.d, this.b, this.c};
        } else {
            this.d = null;
            drawableArr = new Drawable[]{this.b, this.c};
        }
        this.e = new LayerDrawable(drawableArr);
        this.a = new ag(this.k.getResources(), this.e, this.l.a(), this.f, this.f + this.g);
        ag agVar = this.a;
        agVar.k = false;
        agVar.invalidateSelf();
        this.l.a(this.a);
    }

    final void a(ColorStateList colorStateList) {
        if (this.b != null) {
            DrawableCompat.setTintList(this.b, colorStateList);
        }
        if (this.d != null) {
            this.d.a(colorStateList);
        }
    }

    final void a(Mode mode) {
        if (this.b != null) {
            DrawableCompat.setTintMode(this.b, mode);
        }
    }

    void a(int i) {
        if (this.c != null) {
            DrawableCompat.setTintList(this.c, b(i));
        }
    }

    float a() {
        return this.f;
    }

    void a(float f) {
        if (this.a != null) {
            this.a.a(f, this.g + f);
            h();
        }
    }

    void b(float f) {
        if (this.a != null) {
            ag agVar = this.a;
            agVar.a(agVar.j, this.f + f);
            h();
        }
    }

    void a(int[] iArr) {
        a aVar;
        at atVar = this.o;
        int size = atVar.a.size();
        for (int i = 0; i < size; i++) {
            a aVar2 = (a) atVar.a.get(i);
            if (StateSet.stateSetMatches(aVar2.a, iArr)) {
                aVar = aVar2;
                break;
            }
        }
        aVar = null;
        if (aVar != atVar.b) {
            View a;
            if (!(atVar.b == null || atVar.c == null)) {
                a = atVar.a();
                if (a != null && a.getAnimation() == atVar.c) {
                    a.clearAnimation();
                }
                atVar.c = null;
            }
            atVar.b = aVar;
            a = (View) atVar.d.get();
            if (aVar != null && a != null && a.getVisibility() == 0) {
                atVar.c = aVar.b;
                a = atVar.a();
                if (a != null) {
                    a.startAnimation(atVar.c);
                }
            }
        }
    }

    void b() {
        at atVar = this.o;
        if (atVar.c != null) {
            View a = atVar.a();
            if (a != null && a.getAnimation() == atVar.c) {
                a.clearAnimation();
            }
        }
    }

    void c() {
        if (!this.p && this.k.getVisibility() == 0) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.k.getContext(), R.anim.design_fab_out);
            loadAnimation.setInterpolator(a.c);
            loadAnimation.setDuration(Constants.ST_UPLOAD_MAX_COUNT);
            loadAnimation.setAnimationListener(new v(this));
            this.k.startAnimation(loadAnimation);
        }
    }

    void d() {
        if (this.k.getVisibility() != 0 || this.p) {
            this.k.clearAnimation();
            this.k.a(0, false);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.k.getContext(), R.anim.design_fab_in);
            loadAnimation.setDuration(Constants.ST_UPLOAD_MAX_COUNT);
            loadAnimation.setInterpolator(a.d);
            loadAnimation.setAnimationListener(new w(this));
            this.k.startAnimation(loadAnimation);
        }
    }

    void e() {
    }

    void a(Rect rect) {
        this.a.getPadding(rect);
    }

    private Animation a(Animation animation) {
        animation.setInterpolator(a.b);
        animation.setDuration((long) this.n);
        return animation;
    }

    private static ColorStateList b(int i) {
        r0 = new int[3][];
        int[] iArr = new int[]{i, i, h};
        iArr[1] = i;
        r0[2] = new int[0];
        iArr[2] = 0;
        return new ColorStateList(r0, iArr);
    }
}
