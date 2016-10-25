package com.xunlei.downloadprovider.qrcode.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: CameraSplashViewHolder.java
public final class a {
    public View a;
    public ImageView b;
    public TextView c;
    public RelativeLayout d;

    public final void a() {
        this.b.setImageResource(2130837758);
        this.c.setText("\u76f8\u673a\u65e0\u6cd5\u542f\u52a8");
        this.d.setVisibility(0);
        this.a.setVisibility(0);
    }

    public final void b() {
        if (this.a != null) {
            this.a.setVisibility(XZBDevice.Wait);
        }
    }
}
