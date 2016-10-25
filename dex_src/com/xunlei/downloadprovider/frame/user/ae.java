package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.c;
import com.xunlei.downloadprovider.R;

// compiled from: HistoryCommentItemViewHolder.java
final class ae extends c {
    final /* synthetic */ v a;

    ae(v vVar) {
        this.a = vVar;
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        v.j(this.a).setImageResource(R.drawable.short_list_item_default_poster);
    }
}
