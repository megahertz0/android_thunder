package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.widget.PopupWindow.OnDismissListener;

// compiled from: BaseWindowHelper.java
class c implements OnDismissListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void onDismiss() {
        if (((Activity) this.a.b.get()) != null) {
            this.a.a(0);
        }
    }
}
