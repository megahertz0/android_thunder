package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.a;

import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.choiceness.a;

// compiled from: RedEnvelopesHolder.java
public final class b extends t {
    final ImageView a;
    final TextView b;
    final TextView c;

    public b(View view) {
        super(view);
        this.a = (ImageView) view.findViewById(R.id.iv_red_envelopes_item_icon);
        this.b = (TextView) view.findViewById(R.id.tv_red_envelopes_item_title);
        this.c = (TextView) view.findViewById(R.id.tv_red_envelopes_left_time);
    }

    static void a(String str, ImageView imageView) {
        a.a(str, imageView);
    }
}
