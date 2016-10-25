package com.xunlei.tdlive;

import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: LiveGiftDialog.java
class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public void run() {
        if (this.a.b.getTag() != null) {
            this.a.c.a((JsonWrapper) this.a.b.getTag());
        }
    }
}
