package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.ah.a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: HistoryCommentItemViewHolder.java
final class x implements OnClickListener {
    final /* synthetic */ v a;

    x(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (v.a(this.a) != null) {
            a a = v.a(this.a);
            View view2 = this.a.itemView;
            a.a(SimpleLog.LOG_LEVEL_DEBUG, v.b(this.a));
        }
    }
}
