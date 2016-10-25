package com.xunlei.downloadprovider.web;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.UserFeedBackUmActivity;

// compiled from: DetailPageBrowserActivity.java
final class p implements OnClickListener {
    final /* synthetic */ DetailPageBrowserActivity a;

    p(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.a = detailPageBrowserActivity;
    }

    public final void onClick(View view) {
        this.a.startActivity(new Intent(this.a, UserFeedBackUmActivity.class));
    }
}
