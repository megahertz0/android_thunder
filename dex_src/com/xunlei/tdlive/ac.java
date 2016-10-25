package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerActivity.java
class ac implements OnClickListener {
    final /* synthetic */ LivePlayerActivity a;

    ac(LivePlayerActivity livePlayerActivity) {
        this.a = livePlayerActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 1) {
            XLog.e("LivePlayerActivity", "\u4e3b\u52a8\u5173\u95ed\u623f\u95f4");
            this.a.e.a(true);
            this.a.b("\u4e3b\u52a8\u5173\u95ed\u623f\u95f4");
        }
    }
}
