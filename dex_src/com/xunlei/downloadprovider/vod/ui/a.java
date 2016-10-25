package com.xunlei.downloadprovider.vod.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.PopupWindow;

// compiled from: VodPlayerBasePopupWindow.java
public class a extends PopupWindow {
    protected Context a;

    public a(Context context) {
        super(context);
        this.a = context;
    }

    public void setContentView(View view) {
        super.setContentView(view);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(this.a.getResources().getDimensionPixelSize(2131362384));
        setHeight(-1);
        setFocusable(true);
        setAnimationStyle(2131428029);
    }
}
