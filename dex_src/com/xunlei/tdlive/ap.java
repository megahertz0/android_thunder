package com.xunlei.tdlive;

import com.xunlei.tdlive.co.b;

// compiled from: LivePlayerActivity.java
class ap implements b {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ LivePlayerActivity c;

    ap(LivePlayerActivity livePlayerActivity, String str, String str2) {
        this.c = livePlayerActivity;
        this.a = str;
        this.b = str2;
    }

    public void a(String str, String str2) {
        new aq(this, str, str2).onClick(null, 1);
    }

    public void a() {
        LivePlayerActivity.c.a();
        this.c.finish();
    }
}
