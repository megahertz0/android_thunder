package com.xunlei.downloadprovider.search.ui.search;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: SearchActivity.java
final class g implements OnTouchListener {
    final /* synthetic */ SearchActivity a;

    g(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.a();
        }
        return false;
    }
}
