package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: FeedbackActivity.java
class g implements OnClickListener {
    final /* synthetic */ FeedbackActivity a;

    g(FeedbackActivity feedbackActivity) {
        this.a = feedbackActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
