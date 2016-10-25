package com.xunlei.downloadprovider.web.base.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.web.base.a.ae.a;
import com.xunlei.downloadprovider.web.base.model.t;

// compiled from: RecommendFooterViewHolder.java
public final class x extends af {
    private final TextView a;
    private final a b;
    private String c;

    public x(View view, a aVar) {
        super(view);
        this.b = aVar;
        this.a = (TextView) view.findViewById(R.id.tv_more_recommend);
        Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.ic_down_arrow_selector);
        drawable.setBounds(0, 0, g.a(view.getContext(), 16.0f), g.a(view.getContext(), 16.0f));
        this.a.setCompoundDrawables(null, null, drawable, null);
        this.a.setOnClickListener(new y(this));
    }

    public final void a(t tVar) {
        if (tVar == null || !(tVar.b instanceof String)) {
            throw new IllegalArgumentException("itemData should be String type");
        }
        this.c = (String) tVar.b;
        this.a.setText(this.c);
    }
}
