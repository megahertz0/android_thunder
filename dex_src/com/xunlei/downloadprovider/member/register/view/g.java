package com.xunlei.downloadprovider.member.register.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: VerifyCodeDialog.java
final class g implements OnClickListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        if (e.c(this.a) != null) {
            e.c(this.a).a();
            e.d(this.a).setVisibility(0);
        }
    }
}
