package com.xunlei.tdlive;

import android.widget.PopupWindow.OnDismissListener;

// compiled from: LivePlayerDialog.java
class ca implements OnDismissListener {
    final /* synthetic */ au a;

    ca(au auVar) {
        this.a = auVar;
    }

    public void onDismiss() {
        au.i(this.a, false);
    }
}
