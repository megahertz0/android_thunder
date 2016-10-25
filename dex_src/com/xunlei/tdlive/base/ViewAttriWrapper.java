package com.xunlei.tdlive.base;

import android.view.View;

public class ViewAttriWrapper {
    private View a;

    public ViewAttriWrapper(View view) {
        this.a = view;
    }

    public int getWidth() {
        return this.a.getLayoutParams().width;
    }

    public void setWidth(int i) {
        this.a.getLayoutParams().width = i;
        this.a.requestLayout();
    }
}
