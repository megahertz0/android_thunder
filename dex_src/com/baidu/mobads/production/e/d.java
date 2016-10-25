package com.baidu.mobads.production.e;

import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class d implements Runnable {
    final /* synthetic */ LayoutParams a;
    final /* synthetic */ View b;
    final /* synthetic */ c c;

    d(c cVar, LayoutParams layoutParams, View view) {
        this.c = cVar;
        this.a = layoutParams;
        this.b = view;
    }

    public void run() {
        this.a.addRule(XZBDevice.Success);
        this.a.rightMargin = 0;
        this.a.topMargin = this.b.getTop();
        this.c.g.setLayoutParams(this.a);
    }
}
