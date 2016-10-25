package com.xunlei.downloadprovider.frame.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: HistoryCommentItemFragment.java
final class h extends BroadcastReceiver {
    final /* synthetic */ HistoryCommentItemFragment a;

    h(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("action_comment_zan_state_changed".contentEquals(intent.getAction())) {
            HistoryCommentItemFragment.b();
            HistoryCommentItemFragment.a(this.a, intent);
        }
    }
}
