package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.web.base.model.t;

// compiled from: CommentHeaderViewHolder.java
public final class i extends af {
    private final TextView a;
    private String b;

    public i(View view) {
        super(view);
        this.a = (TextView) view.findViewById(R.id.tv_comment_header);
    }

    public final void a(t tVar) {
        if (tVar == null || !(tVar.b instanceof String)) {
            throw new IllegalArgumentException("itemData should be String type");
        }
        this.b = (String) tVar.b;
        this.a.setText(this.b);
    }
}
