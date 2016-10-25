package com.xunlei.downloadprovider.homepage.recommend;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ShortTimeVideoListActivity.java
final class c implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ ShortTimeVideoListActivity b;

    c(ShortTimeVideoListActivity shortTimeVideoListActivity, Dialog dialog) {
        this.b = shortTimeVideoListActivity;
        this.a = dialog;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        a.e();
    }
}
