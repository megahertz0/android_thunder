package com.xunlei.tdlive.usercenter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

// compiled from: ViewHelper.java
final class ai implements OnClickListener {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ OnClickListener b;

    ai(PopupWindow popupWindow, OnClickListener onClickListener) {
        this.a = popupWindow;
        this.b = onClickListener;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        if (this.b != null) {
            this.b.onClick(view);
        }
    }
}
