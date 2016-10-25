package com.xunlei.downloadprovider.vod;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.uc.addon.sdk.remote.Tabs;

// compiled from: PopupSeekTimeWindow.java
public class e extends PopupWindow {
    private static final String c;
    View a;
    TextView b;
    private LayoutInflater d;
    private Drawable e;

    static {
        c = e.class.getSimpleName();
    }

    public e(Context context) {
        super(context);
        this.a = null;
        this.d = null;
        this.e = null;
        this.b = null;
        if (context != null) {
            this.d = (LayoutInflater) context.getSystemService("layout_inflater");
        }
        if (this.d != null) {
            this.a = this.d.inflate(2130969050, null);
            this.b = (TextView) this.a.findViewById(2131757220);
        }
        if (this.e == null) {
            setBackgroundDrawable(new BitmapDrawable());
        } else {
            setBackgroundDrawable(this.e);
        }
        setTouchable(true);
        setOutsideTouchable(true);
        setFocusable(true);
        setTouchInterceptor(new f(this));
        setWindowLayoutMode(Tabs.TAB_CREATE_REACH_MAX_COUNT, Tabs.TAB_CREATE_REACH_MAX_COUNT);
        this.a.setLayoutParams(new LayoutParams(-2, -2));
        this.a.measure(Tabs.TAB_CREATE_REACH_MAX_COUNT, Tabs.TAB_CREATE_REACH_MAX_COUNT);
        setContentView(this.a);
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
    }
}
