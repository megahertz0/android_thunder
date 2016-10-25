package com.xunlei.downloadprovider.xiazaibao.view;

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

public class XZBDownloadBottomView extends FrameLayout {
    public TextView a;
    public View b;
    public ImageView c;
    public View d;
    public TextView e;
    public ImageView f;
    public View g;
    public TextView h;
    public ImageView i;
    public View j;
    public TextView k;
    public ImageView l;
    public Animation m;
    public Animation n;
    public boolean o;
    private TextView p;

    public XZBDownloadBottomView(Context context) {
        super(context);
        a(context);
    }

    public XZBDownloadBottomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public XZBDownloadBottomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.xzb_download_center_bottom_view, this);
        this.p = (TextView) findViewById(R.id.download_center_bottom_view_title);
        this.a = (TextView) findViewById(R.id.start_task);
        this.c = (ImageView) findViewById(R.id.icon_start);
        this.e = (TextView) findViewById(R.id.pause_tasks);
        this.f = (ImageView) findViewById(R.id.icon_pause);
        this.h = (TextView) findViewById(R.id.delete_tasks);
        this.i = (ImageView) findViewById(R.id.icon_delete);
        this.k = (TextView) findViewById(R.id.save_to_xzb_tasks);
        this.l = (ImageView) findViewById(R.id.icon_save_to_xzb);
        this.b = findViewById(R.id.start_contain);
        this.d = findViewById(R.id.pause_contain);
        this.g = findViewById(R.id.delete_contain);
        this.j = findViewById(R.id.save_to_xzb_contain);
        this.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.m = AnimationUtils.loadAnimation(context, R.anim.delete_bottom_in);
        this.n = AnimationUtils.loadAnimation(context, R.anim.delete_bottom_out);
        this.m.setAnimationListener(new h(this));
        this.n.setAnimationListener(new i(this));
        setOnClickListener(new g(this));
    }

    public void setTitle(String str) {
        this.p.setText(str);
    }

    public void setStoreTasksToXiaZaiBaoVisiable(boolean z) {
        this.j.setVisibility(z ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void setStoreTasksToXiaZaiBaoListener(OnClickListener onClickListener) {
        this.j.setOnClickListener(onClickListener);
    }

    public void setDeleteTasksListener(OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void setPauseTasksListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void setStartTasksListener(OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }

    public final void a() {
        this.b.setClickable(false);
        this.g.setClickable(false);
        this.j.setClickable(false);
        this.d.setClickable(false);
        this.c.setEnabled(false);
        this.f.setEnabled(false);
        this.i.setEnabled(false);
        this.l.setEnabled(false);
        this.a.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
        this.e.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
        this.h.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
        this.k.setTextColor(getResources().getColor(R.color.download_list_bottom_disable));
    }
}
