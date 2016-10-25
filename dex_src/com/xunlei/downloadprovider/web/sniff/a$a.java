package com.xunlei.downloadprovider.web.sniff;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: SniffAnimListAdapter.java
private class a$a implements AnimationListener {
    final /* synthetic */ a a;
    private int b;

    private a$a(a aVar) {
        this.a = aVar;
    }

    static /* synthetic */ int b(a$a com_xunlei_downloadprovider_web_sniff_a_a) {
        int i = com_xunlei_downloadprovider_web_sniff_a_a.b;
        com_xunlei_downloadprovider_web_sniff_a_a.b = i + 1;
        return i;
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        if (this.b > 0) {
            this.b--;
        }
        a.a(this.a);
        if (a.b(this.a) != null) {
            new StringBuilder("mListener.needNotificationAnimCount: ").append(a.b(this.a).a);
        }
        if (a.b(this.a) == null) {
            return;
        }
        if (a.b(this.a).a <= a.c(this.a) || a.c(this.a) >= a.d(this.a)) {
            a.b(this.a).a();
        }
    }
}
