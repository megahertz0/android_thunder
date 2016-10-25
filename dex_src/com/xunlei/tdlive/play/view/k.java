package com.xunlei.tdlive.play.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: GiftReminder.java
class k implements AnimationListener {
    final /* synthetic */ GiftReminder a;

    k(GiftReminder giftReminder) {
        this.a = giftReminder;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a.h != null) {
            this.a.h.onGiftReminderViewState(this.a);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
