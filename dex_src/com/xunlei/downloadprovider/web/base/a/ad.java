package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.web.base.model.u;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RecommendItemViewHolder.java
final class ad implements OnClickListener {
    final /* synthetic */ u a;
    final /* synthetic */ ac b;

    ad(ac acVar, u uVar) {
        this.b = acVar;
        this.a = uVar;
    }

    public final void onClick(View view) {
        this.b.h.a(this.b.itemView, SimpleLog.LOG_LEVEL_OFF, this.a);
    }
}
