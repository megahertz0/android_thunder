package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: FeedbackActivity.java
class f implements OnClickListener {
    final /* synthetic */ FeedbackActivity a;

    f(FeedbackActivity feedbackActivity) {
        this.a = feedbackActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.finish();
    }
}
