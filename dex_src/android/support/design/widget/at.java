package android.support.design.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// compiled from: StateListAnimator.java
final class at {
    final ArrayList<a> a;
    a b;
    Animation c;
    WeakReference<View> d;
    private AnimationListener e;

    // compiled from: StateListAnimator.java
    static class a {
        final int[] a;
        final Animation b;

        private a(int[] iArr, Animation animation) {
            this.a = iArr;
            this.b = animation;
        }
    }

    at() {
        this.a = new ArrayList();
        this.b = null;
        this.c = null;
        this.e = new au(this);
    }

    public final void a(int[] iArr, Animation animation) {
        a aVar = new a(animation, (byte) 0);
        animation.setAnimationListener(this.e);
        this.a.add(aVar);
    }

    final View a() {
        return this.d == null ? null : (View) this.d.get();
    }
}
