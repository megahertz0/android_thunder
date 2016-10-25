package com.xunlei.tdlive;

import com.xunlei.tdlive.user.f.b;

// compiled from: LivePlayerDialog.java
class ce implements b {
    final /* synthetic */ au a;

    ce(au auVar) {
        this.a = auVar;
    }

    public void a(boolean z) {
        if (z) {
            this.a.a.showChatInputBar(true);
            au.c(this.a).requestFocus();
            au.d(this.a).toggleSoftInput(0, 0);
            au.c(this.a, true);
        }
    }
}
