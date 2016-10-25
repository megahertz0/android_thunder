package com.xunlei.downloadprovider.member.payment.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

// compiled from: PayOpenFragment.java
final class ag implements AnimatorUpdateListener {
    final /* synthetic */ View a;
    final /* synthetic */ PayOpenFragment b;

    ag(PayOpenFragment payOpenFragment, View view) {
        this.b = payOpenFragment;
        this.a = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        LayoutParams layoutParams = this.a.getLayoutParams();
        layoutParams.height = intValue;
        this.a.setLayoutParams(layoutParams);
    }
}
