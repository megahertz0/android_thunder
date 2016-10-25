package com.xunlei.downloadprovider.download.tasklist.list.b;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;

// compiled from: BasicCardBannerViewHolder.java
public final class b {
    public View a;
    public View b;
    public TextView c;
    public TextView d;
    public TextView e;
    public ImageView f;

    public b(View view) {
        this.a = view;
        this.b = view.findViewById(R.id.closeButton);
        this.c = (TextView) view.findViewById(R.id.titleTextView);
        this.d = (TextView) view.findViewById(R.id.actionButton);
        this.e = (TextView) view.findViewById(R.id.remainderTimeTextView);
        this.f = (ImageView) view.findViewById(R.id.iconImageView);
    }

    public final void a(CharSequence charSequence) {
        if (this.c != null) {
            this.c.setText(charSequence);
        }
    }

    public final void b(CharSequence charSequence) {
        if (this.d != null) {
            this.d.setText(charSequence);
        }
    }
}
