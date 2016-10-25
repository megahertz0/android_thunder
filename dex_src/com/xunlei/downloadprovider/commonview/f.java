package com.xunlei.downloadprovider.commonview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: TitleBar.java
public final class f {
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public ViewGroup f;
    public ImageView g;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public ProgressBar l;
    public View m;
    public ImageView n;
    public ImageView o;
    public DownloadEntranceView p;
    protected View q;
    protected View r;

    static {
        a = R.id.titlebar_left;
        b = R.id.titlebar_title;
        c = R.id.titlebar_right;
        d = R.id.titlebar_flower;
        e = R.id.titlebar_right_1;
    }

    public f(Activity activity) {
        this.f = (ViewGroup) activity.findViewById(R.id.common_title_bar);
        this.g = (ImageView) activity.findViewById(R.id.titlebar_left);
        this.h = (TextView) activity.findViewById(R.id.titlebar_left_tv);
        this.i = (TextView) activity.findViewById(R.id.titlebar_title);
        this.j = (TextView) activity.findViewById(R.id.titlebar_right);
        this.k = (TextView) activity.findViewById(R.id.titlebar_right_1);
        this.l = (ProgressBar) activity.findViewById(R.id.titlebar_flower);
        this.m = activity.findViewById(R.id.titlebar_right_container);
        this.n = (ImageView) activity.findViewById(R.id.titlebar_right_iv);
        this.o = (ImageView) activity.findViewById(R.id.titlebar_right_1_iv);
        this.p = (DownloadEntranceView) activity.findViewById(R.id.titlebar_download_entrance);
        this.q = activity.findViewById(R.id.white_button_line);
        this.r = activity.findViewById(R.id.xreader_common_divide);
        this.g.setOnClickListener(new g(this, activity));
        b();
    }

    public f(View view) {
        this.f = (ViewGroup) view.findViewById(R.id.common_title_bar);
        this.g = (ImageView) view.findViewById(R.id.titlebar_left);
        this.h = (TextView) view.findViewById(R.id.titlebar_left_tv);
        this.i = (TextView) view.findViewById(R.id.titlebar_title);
        this.m = view.findViewById(R.id.titlebar_right_container);
        this.j = (TextView) view.findViewById(R.id.titlebar_right);
        this.k = (TextView) view.findViewById(R.id.titlebar_right_1);
        this.l = (ProgressBar) view.findViewById(R.id.titlebar_flower);
        this.n = (ImageView) view.findViewById(R.id.titlebar_right_iv);
        this.o = (ImageView) view.findViewById(R.id.titlebar_right_1_iv);
        this.p = (DownloadEntranceView) view.findViewById(R.id.titlebar_download_entrance);
        this.q = view.findViewById(R.id.white_button_line);
        this.r = view.findViewById(R.id.xreader_common_divide);
        b();
    }

    public final void a() {
        if (this.q != null) {
            this.q.setVisibility(XZBDevice.Wait);
        }
        if (this.r != null) {
            this.r.setVisibility(XZBDevice.Wait);
        }
    }

    private void b() {
        this.i.setMaxWidth((b.t() * 2) / 3);
    }
}
