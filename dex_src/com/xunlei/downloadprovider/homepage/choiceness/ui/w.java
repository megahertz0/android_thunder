package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.xunlei.downloadprovider.player.a.a;
import com.xunlei.downloadprovider.player.a.b;

// compiled from: HomeChoicenessFragment.java
final class w implements OnTouchListener {
    final /* synthetic */ HomeChoicenessFragment a;

    w(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        a a = this.a.l;
        if (motionEvent.getAction() == 1) {
            new StringBuilder("onScrollTouchUp--scrollState=").append(a.b);
            if (!a.a(a.b) && a.c()) {
                if (a.f == null) {
                    a.a(a.a, a.c, null);
                } else if (a.a(a.f)) {
                    b bVar = a.f;
                    a.f = null;
                    a.a(a.a, a.c, bVar);
                }
            }
        }
        return false;
    }
}
