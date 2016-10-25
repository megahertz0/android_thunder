package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ReportActivity.java
final class aq implements OnClickListener {
    final /* synthetic */ ReportActivity a;

    aq(ReportActivity reportActivity) {
        this.a = reportActivity;
    }

    public final void onClick(View view) {
        this.a.onBackPressed();
    }
}
