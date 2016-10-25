package com.xunlei.tdlive;

import com.xunlei.tdlive.d.a.a;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerActivity.java
class ag implements a {
    long a;
    final /* synthetic */ LivePlayerActivity b;

    ag(LivePlayerActivity livePlayerActivity) {
        this.b = livePlayerActivity;
        this.a = 1;
    }

    public void a(int i) {
    }

    public void a() {
    }

    public void a(long j, int i, int i2, int i3, int i4, String str, int i5) {
        this.b.b(j, i, i2, i3, i4, str, i5);
    }

    public void a(long j, long j2) {
        XLog.e("LivePlayerActivity", new StringBuilder("onPlayBufferingLongTime interval:").append(j).append(", count:").append(j2).append(", tick:").append(this.a).toString());
        long j3 = this.a;
        this.a = 1 + j3;
        if (j3 % 3 == 0) {
            this.b.showToast("\u7f51\u7edc\u592a\u632b~", 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
        }
    }
}
