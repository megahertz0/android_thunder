package com.xunlei.downloadprovider.homepage;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

// compiled from: HomeFragment.java
final class h implements AnimatorUpdateListener {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ HomeFragment f;
    private IntEvaluator g;

    h(HomeFragment homeFragment, View view, int i, int i2, int i3, int i4) {
        this.f = homeFragment;
        this.a = view;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.g = new IntEvaluator();
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float intValue = ((float) ((Integer) valueAnimator.getAnimatedValue()).intValue()) / 100.0f;
        this.a.getLayoutParams().width = this.g.evaluate(intValue, Integer.valueOf(this.b), Integer.valueOf(this.c)).intValue();
        ((LayoutParams) this.a.getLayoutParams()).setMargins(this.g.evaluate(intValue, Integer.valueOf(this.d), Integer.valueOf(this.e)).intValue(), ((LayoutParams) this.a.getLayoutParams()).topMargin, 0, 0);
        this.a.requestLayout();
        this.f.d();
    }
}
