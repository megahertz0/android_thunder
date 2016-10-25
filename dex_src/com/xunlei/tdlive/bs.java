package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: LivePlayerDialog.java
class bs implements OnClickListener {
    final /* synthetic */ au a;

    bs(au auVar) {
        this.a = auVar;
    }

    public void onClick(View view) {
        au.a(this.a, false);
        if (!au.b(this.a, true)) {
            this.a.onBackPressed();
        }
    }
}
