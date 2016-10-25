package com.xunlei.tdlive.usercenter;

import android.view.View;
import android.widget.PopupWindow.OnDismissListener;

// compiled from: UserListFragment2.java
class ae implements OnDismissListener {
    final /* synthetic */ View a;
    final /* synthetic */ y b;

    ae(y yVar, View view) {
        this.b = yVar;
        this.a = view;
    }

    public void onDismiss() {
        this.a.setSelected(false);
    }
}
