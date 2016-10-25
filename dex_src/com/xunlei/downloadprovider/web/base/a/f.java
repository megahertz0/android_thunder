package com.xunlei.downloadprovider.web.base.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.web.base.model.t;

// compiled from: CommentEmptyViewHolder.java
public final class f extends af {
    private final TextView a;

    public f(View view) {
        super(view);
        this.a = (TextView) view.findViewById(R.id.tv_empty_comment);
        Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.ic_shafa);
        int a = g.a(view.getContext(), 72.0f);
        drawable.setBounds(0, 0, a, a);
        this.a.setCompoundDrawables(null, drawable, null, null);
        this.a.setVisibility(0);
    }

    public final void a(t tVar) {
    }
}
