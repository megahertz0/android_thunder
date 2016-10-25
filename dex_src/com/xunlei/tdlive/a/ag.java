package com.xunlei.tdlive.a;

import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: VisitorHListAdapter.java
class ag implements OnClickListener {
    final /* synthetic */ t a;
    final /* synthetic */ int b;
    final /* synthetic */ ae c;

    ag(ae aeVar, t tVar, int i) {
        this.c = aeVar;
        this.a = tVar;
        this.b = i;
    }

    public void onClick(View view) {
        if (this.c.c != null) {
            this.c.c.onItemClick(null, this.a.itemView, this.b, this.c.getItemId(this.b));
        }
    }
}
