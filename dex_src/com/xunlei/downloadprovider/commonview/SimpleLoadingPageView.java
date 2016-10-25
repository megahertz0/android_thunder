package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class SimpleLoadingPageView extends LinearLayout implements e {
    View a;
    private GifView b;
    private TextView c;

    public SimpleLoadingPageView(Context context) {
        super(context);
        c();
    }

    public SimpleLoadingPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public SimpleLoadingPageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        setOrientation(1);
        setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.a = LayoutInflater.from(getContext()).inflate(com.xunlei.downloadprovidercommon.R.layout.layout_simple_loading, this);
        this.b = (GifView) this.a.findViewById(com.xunlei.downloadprovidercommon.R.id.gif_view);
        this.c = (TextView) this.a.findViewById(com.xunlei.downloadprovidercommon.R.id.loading_tip);
    }

    public void setTip(String str) {
        if (this.c != null) {
            this.c.setText(str);
        }
    }

    public final void a() {
        setVisibility(0);
        this.b.setPaused(false);
    }

    public final void b() {
        this.b.setPaused(true);
        setVisibility(XZBDevice.Wait);
    }
}
