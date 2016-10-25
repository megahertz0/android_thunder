package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class DownloadCenterSelectFileTitleView extends FrameLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private b e;
    private a f;
    private boolean g;
    private Animation h;
    private Animation i;

    public static interface b {
        void a(boolean z);
    }

    public DownloadCenterSelectFileTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = true;
        this.h = null;
        this.i = null;
        a(context);
    }

    public DownloadCenterSelectFileTitleView(Context context) {
        super(context);
        this.g = true;
        this.h = null;
        this.i = null;
        a(context);
    }

    public DownloadCenterSelectFileTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = true;
        this.h = null;
        this.i = null;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(2130968732, this);
        this.a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(R.id.cancel);
        this.c = (TextView) findViewById(2131755760);
        this.c.setOnClickListener(new g(this));
        this.d = (TextView) findViewById(2131755761);
        this.d.setOnClickListener(new h(this));
        setOnClickListener(new i(this));
        this.h = AnimationUtils.loadAnimation(context, 2131034206);
        this.i = AnimationUtils.loadAnimation(context, 2131034207);
        this.h.setAnimationListener(new j(this));
        this.i.setAnimationListener(new k(this));
    }

    public void setTitle(String str) {
        this.a.setText(str);
    }

    public final void a(boolean z) {
        if (z) {
            this.c.setVisibility(0);
            this.d.setVisibility(XZBDevice.Wait);
            return;
        }
        this.c.setVisibility(XZBDevice.Wait);
        this.d.setVisibility(0);
    }

    public void setCancelListener(OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }

    public void setShowListener(a aVar) {
        this.f = aVar;
    }

    public void setSelectAllListener(b bVar) {
        this.e = bVar;
    }

    public final void b(boolean z) {
        this.a.setText(getResources().getString(2131231313));
        a(true);
        setVisibility(0);
        if (z) {
            startAnimation(this.h);
        }
    }

    public final void c(boolean z) {
        if (z) {
            startAnimation(this.i);
        } else {
            setVisibility(XZBDevice.Wait);
        }
    }
}
