package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

public class DownloadCenterBottomView extends FrameLayout {
    private TextView a;
    private TextView b;
    private View c;
    private ImageView d;
    private View e;
    private TextView f;
    private ImageView g;
    private View h;
    private TextView i;
    private ImageView j;
    private View k;
    private TextView l;
    private ImageView m;
    private Animation n;
    private Animation o;
    private boolean p;

    public DownloadCenterBottomView(Context context) {
        super(context);
        a(context);
    }

    public DownloadCenterBottomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DownloadCenterBottomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.download_center_bottom_view, this);
        this.a = (TextView) findViewById(R.id.download_center_bottom_view_title);
        this.b = (TextView) findViewById(R.id.start_task);
        this.d = (ImageView) findViewById(R.id.icon_start);
        this.f = (TextView) findViewById(R.id.pause_tasks);
        this.g = (ImageView) findViewById(R.id.icon_pause);
        this.i = (TextView) findViewById(R.id.delete_tasks);
        this.j = (ImageView) findViewById(R.id.icon_delete);
        this.l = (TextView) findViewById(R.id.save_to_xzb_tasks);
        this.m = (ImageView) findViewById(R.id.icon_save_to_xzb);
        this.c = findViewById(R.id.start_contain);
        this.e = findViewById(R.id.pause_contain);
        this.h = findViewById(R.id.delete_contain);
        this.k = findViewById(R.id.save_to_xzb_contain);
        this.n = AnimationUtils.loadAnimation(context, R.anim.delete_bottom_in);
        this.o = AnimationUtils.loadAnimation(context, R.anim.delete_bottom_out);
        this.n.setAnimationListener(new d(this));
        this.o.setAnimationListener(new e(this));
        setOnClickListener(new c(this));
    }

    public final void a() {
        this.p = true;
        setVisibility(0);
        startAnimation(this.n);
        b();
    }

    public final void a(boolean z) {
        this.p = false;
        if (z) {
            startAnimation(this.o);
        } else {
            setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public void setTitle(String str) {
        this.a.setText(str);
    }

    public void setStoreTasksToXiaZaiBaoVisiable(boolean z) {
        this.k.setVisibility(z ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void setStoreTasksToXiaZaiBaoListener(OnClickListener onClickListener) {
        this.k.setOnClickListener(onClickListener);
    }

    public void setDeleteTasksListener(OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    public void setPauseTasksListener(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setStartTasksListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public final void b() {
        this.c.setClickable(false);
        this.h.setClickable(false);
        this.k.setClickable(false);
        this.e.setClickable(false);
        this.d.setEnabled(false);
        this.g.setEnabled(false);
        this.j.setEnabled(false);
        this.m.setEnabled(false);
        this.b.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
        this.f.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
        this.i.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
        this.l.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
    }

    public final void c() {
        this.c.setClickable(true);
        this.e.setClickable(true);
        this.h.setClickable(true);
        this.k.setClickable(true);
        this.d.setEnabled(true);
        this.g.setEnabled(true);
        this.j.setEnabled(true);
        this.m.setEnabled(true);
        this.b.setTextColor(getResources().getColor(R.color.download_list_bottom_enable));
        this.f.setTextColor(getResources().getColor(R.color.download_list_bottom_enable));
        this.i.setTextColor(getResources().getColor(R.color.download_list_bottom_enable));
        this.l.setTextColor(getResources().getColor(R.color.download_list_bottom_enable));
    }
}
