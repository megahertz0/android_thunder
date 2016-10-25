package com.xunlei.tdlive.usercenter;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UserCenterActivity.java
class k implements OnScrollListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ UserCenterActivity c;

    k(UserCenterActivity userCenterActivity, View view, View view2) {
        this.c = userCenterActivity;
        this.a = view;
        this.b = view2;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        int top = this.a.getTop();
        int height = this.a.getHeight();
        if (top >= 0 || height <= 0) {
            this.b.setVisibility(XZBDevice.Wait);
            return;
        }
        float f = (0.0f - ((float) top)) / ((float) height);
        this.b.setVisibility(0);
        this.b.setAlpha(f);
    }
}
