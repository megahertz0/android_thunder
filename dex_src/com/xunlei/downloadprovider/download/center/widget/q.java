package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.PopupWindow;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.xlui.widget.KeyLinearLayout;

// compiled from: DownloadMenuPopWindow.java
public class q extends PopupWindow {
    public View f;
    public int g;
    public int h;

    public q(Context context) {
        super(context);
        setFocusable(true);
        setAnimationStyle(R.style.PopupTopAnim);
        setBackgroundDrawable(new BitmapDrawable());
    }

    public void setContentView(View view) {
        super.setContentView(view);
        if (view instanceof KeyLinearLayout) {
            ((KeyLinearLayout) view).setKeyInterceptor(new r(this));
            view.setOnTouchListener(new s(this));
        }
    }
}
