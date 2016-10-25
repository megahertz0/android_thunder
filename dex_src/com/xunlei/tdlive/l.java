package com.xunlei.tdlive;

import android.view.View;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.user.f.b;

// compiled from: LiveGiftDialog.java
class l implements b {
    final /* synthetic */ boolean a;
    final /* synthetic */ View b;
    final /* synthetic */ i c;

    l(i iVar, boolean z, View view) {
        this.c = iVar;
        this.a = z;
        this.b = view;
    }

    public void a(boolean z) {
        if (this.a) {
            if (this.b.getTag() != null) {
                this.c.a((JsonWrapper) this.b.getTag());
            }
        } else if (z) {
            this.c.f.b(new m(this));
        }
    }
}
