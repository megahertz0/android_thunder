package com.xunlei.tdlive.play.a;

import android.view.View;
import com.xunlei.tdlive.user.f.b;

// compiled from: BaseNormalScreenLayoutPresenter.java
class j implements b {
    final /* synthetic */ View a;
    final /* synthetic */ i b;

    j(i iVar, View view) {
        this.b = iVar;
        this.a = view;
    }

    public void a(boolean z) {
        if (z && this.b.a.d != null) {
            c.a(this.b.a, this.b.a.d.a.b, !this.a.isSelected());
        }
    }
}
