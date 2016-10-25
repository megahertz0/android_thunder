package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;

// compiled from: UserFeedBackUmActivity.java
final class br implements OnClickListener {
    final /* synthetic */ UserFeedBackUmActivity a;

    br(UserFeedBackUmActivity userFeedBackUmActivity) {
        this.a = userFeedBackUmActivity;
    }

    public final void onClick(View view) {
        HelpActivity.a(this.a, "file:///android_asset/help/sl_help.html");
    }
}
