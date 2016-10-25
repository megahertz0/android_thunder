package com.xunlei.tdlive;

import com.xunlei.tdlive.protocol.XLLiveFollowRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.user.f.b;
import com.xunlei.tdlive.usercenter.ah;

// compiled from: LivePublishEndingActivity.java
class cw implements b {
    final /* synthetic */ LivePublishEndingActivity a;

    cw(LivePublishEndingActivity livePublishEndingActivity) {
        this.a = livePublishEndingActivity;
    }

    public void a(boolean z) {
        if (z) {
            new XLLiveFollowRequest(f.a().k(), f.a().l(), this.a.b, true).send(new cx(this));
            ah.a((int) R.styleable.Toolbar_contentInsetEnd, true, this.a.b);
        }
    }
}
