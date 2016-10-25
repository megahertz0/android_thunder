package com.xunlei.downloadprovider.commonview;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: TitleBar.java
final class g implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ f b;

    g(f fVar, Activity activity) {
        this.b = fVar;
        this.a = activity;
    }

    public final void onClick(View view) {
        try {
            this.a.onBackPressed();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
