package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.web.base.a.ae.a;
import com.xunlei.downloadprovider.web.base.model.c;
import com.xunlei.downloadprovider.web.base.model.t;

// compiled from: RecommendHeaderViewHolder.java
public final class z extends af {
    private final TextView a;
    private final CheckBox b;
    private final a c;
    private c d;

    public z(View view, a aVar) {
        super(view);
        this.c = aVar;
        this.a = (TextView) view.findViewById(R.id.tv_recommend_header);
        this.b = (CheckBox) view.findViewById(R.id.ckb_auto_play);
        this.b.setOnCheckedChangeListener(new aa(this));
        this.b.setOnClickListener(new ab(this));
    }

    public final void a(t tVar) {
        if (tVar == null || !(tVar.b instanceof c)) {
            throw new IllegalArgumentException("itemData should be String type");
        }
        this.d = (c) tVar.b;
        this.a.setText(this.d.a);
        this.b.setChecked(this.d.b);
    }
}
