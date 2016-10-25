package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FeedVideoItemBottomView extends FrameLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private a f;
    private LinearLayout g;
    private LinearLayout h;
    private RelativeLayout i;
    private RelativeLayout j;
    private ImageView k;
    private ImageView l;

    public void setOnBottomActionBarClickListener(a aVar) {
        this.f = aVar;
    }

    public FeedVideoItemBottomView(Context context) {
        super(context);
        setBottomBarLayout(context);
    }

    public FeedVideoItemBottomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBottomBarLayout(context);
    }

    public FeedVideoItemBottomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setBottomBarLayout(context);
    }

    public ImageView getSubjectIconImageView() {
        return this.a;
    }

    public TextView getClickNiceTextView() {
        return this.d;
    }

    public TextView getCommentNumTextView() {
        return this.c;
    }

    public ImageView getClickNiceImageView() {
        return this.e;
    }

    public TextView getSubjectNameTextView() {
        return this.b;
    }

    public ImageView getHotCommentArrowImage() {
        return this.k;
    }

    public ImageView getImgVthumb() {
        return this.l;
    }

    private void setBottomBarLayout(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2130968752, this);
        this.g = (LinearLayout) inflate.findViewById(2131755883);
        this.a = (ImageView) inflate.findViewById(2131755885);
        this.b = (TextView) inflate.findViewById(2131755887);
        this.h = (LinearLayout) inflate.findViewById(2131755884);
        this.e = (ImageView) inflate.findViewById(2131755894);
        this.d = (TextView) inflate.findViewById(2131755895);
        this.i = (RelativeLayout) inflate.findViewById(2131755890);
        this.c = (TextView) inflate.findViewById(2131755893);
        this.k = (ImageView) findViewById(2131755891);
        this.j = (RelativeLayout) inflate.findViewById(2131755888);
        this.l = (ImageView) inflate.findViewById(2131755886);
        this.g.setOnClickListener(new ak(this));
        this.h.setOnClickListener(new al(this));
        this.i.setOnClickListener(new am(this));
        this.j.setOnClickListener(new an(this));
    }
}
