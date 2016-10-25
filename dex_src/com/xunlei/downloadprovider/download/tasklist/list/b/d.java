package com.xunlei.downloadprovider.download.tasklist.list.b;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.xlui.widget.ZHTextView;

// compiled from: BasicPromotionCardViewHolder.java
public class d extends f {
    public ImageView a;
    public ViewGroup b;
    public ZHTextView c;
    public TextView d;
    public View e;
    public TextView f;
    public TextView g;

    public d(View view) {
        super(view);
        this.b = (ViewGroup) view.findViewById(R.id.cardContainer);
        if (this.b == null && (view instanceof ViewGroup)) {
            this.b = (ViewGroup) view;
        }
        View view2 = this.b;
        this.a = (ImageView) view2.findViewById(R.id.iconImageView);
        this.c = (ZHTextView) view2.findViewById(R.id.titleTextView);
        this.d = (TextView) view2.findViewById(R.id.detailTextView);
        this.e = view2.findViewById(R.id.closeButton);
        this.f = (TextView) view2.findViewById(R.id.actionButton);
        this.g = (TextView) view2.findViewById(R.id.tagView);
    }
}
