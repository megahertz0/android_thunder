package com.xunlei.downloadprovider.qrcode.view;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ScancodeResultWarningView.java
public final class d {
    public View a;
    public ImageView b;
    public TextView c;
    private AnimationDrawable d;

    public d(View view) {
        this.a = view;
    }

    public final void a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.b.setBackgroundResource(2131034134);
                this.d = (AnimationDrawable) this.b.getBackground();
                this.d.start();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.b.setBackgroundResource(R.drawable.dlg_icon_fail);
            default:
                break;
        }
    }

    public final void b(int i) {
        if (!(i == 0 || this.d == null)) {
            this.d.stop();
        }
        this.a.setVisibility(i);
    }

    public final void a(String str) {
        this.c.setText(str);
    }
}
