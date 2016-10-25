package com.xunlei.tdlive;

import android.view.MotionEvent;
import com.xunlei.tdlive.base.l;
import com.xunlei.tdlive.control.TouchDetectorFrameLayout.a;

// compiled from: LiveListFragment.java
private class q$a implements a {
    final /* synthetic */ q a;

    private q$a(q qVar) {
        this.a = qVar;
    }

    public boolean a(MotionEvent motionEvent) {
        return q.d(this.a).getCount() >= 2 ? b(motionEvent) : false;
    }

    private boolean b(MotionEvent motionEvent) {
        if (q.g(this.a) == null || q.h(this.a) == null) {
            return false;
        }
        q.g(this.a).a(motionEvent);
        if (q.i(this.a) == null) {
            q.a(this.a, new l());
        }
        return q.i(this.a).a(motionEvent, q.j(this.a), true);
    }
}
