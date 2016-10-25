package com.xunlei.downloadprovider.download.tasklist.list.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m$a;
import com.xunlei.downloadprovider.xlui.widget.ZHTextView;

// compiled from: BasicADCardViewHolder.java
public abstract class a extends f implements m$a {
    public ViewGroup a;
    public NetworkImageView b;
    public ZHTextView c;
    public RatingBar d;
    public TextView e;
    public View f;
    public TextView g;
    public TextView h;

    public static View a(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_task_card_template_basic_ad_card, viewGroup, false);
    }

    public a(View view) {
        super(view);
        this.a = (ViewGroup) view.findViewById(R.id.cardContainer);
        if (this.a == null && (view instanceof ViewGroup)) {
            this.a = (ViewGroup) view;
        }
        View view2 = this.a;
        this.b = (NetworkImageView) view2.findViewById(R.id.iconImageView);
        this.c = (ZHTextView) view2.findViewById(R.id.titleTextView);
        this.f = view2.findViewById(R.id.closeButton);
        this.g = (TextView) view2.findViewById(R.id.actionButton);
        this.h = (TextView) view2.findViewById(R.id.tagView);
        this.d = (RatingBar) view2.findViewById(R.id.score_rb);
        this.e = (TextView) view2.findViewById(R.id.install_count);
    }
}
