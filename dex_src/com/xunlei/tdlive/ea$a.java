package com.xunlei.tdlive;

import android.view.MotionEvent;
import com.xunlei.tdlive.base.l;
import com.xunlei.tdlive.control.TouchDetectorFrameLayout.a;

// compiled from: SDKLiveListFragment.java
private class ea$a implements a {
    final /* synthetic */ ea a;

    private ea$a(ea eaVar) {
        this.a = eaVar;
    }

    public boolean a(MotionEvent motionEvent) {
        return ea.f(this.a).getCount() >= 2 ? b(motionEvent) : false;
    }

    private boolean b(MotionEvent motionEvent) {
        if (ea.i(this.a) == null || ea.j(this.a) == null) {
            return false;
        }
        ea.i(this.a).a(motionEvent);
        if (ea.k(this.a) == null) {
            ea.a(this.a, new l());
        }
        return ea.k(this.a).a(motionEvent, ea.l(this.a), true);
    }
}
