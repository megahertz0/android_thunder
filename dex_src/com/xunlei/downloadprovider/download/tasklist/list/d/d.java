package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class d implements OnClickListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.m.equals("afterTrial")) {
            b.c(this.a);
            b.d(this.a);
            return;
        }
        b.c(this.a);
        a.a().b = true;
    }
}
