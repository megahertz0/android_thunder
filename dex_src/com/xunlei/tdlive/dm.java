package com.xunlei.tdlive;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: LoginGuideActivity.java
class dm implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ String b;
    final /* synthetic */ LoginGuideActivity c;

    dm(LoginGuideActivity loginGuideActivity, Dialog dialog, String str) {
        this.c = loginGuideActivity;
        this.a = dialog;
        this.b = str;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivClose) {
            this.a.dismiss();
        } else if (id == R.id.tvDownload) {
            this.c.b(this.b);
            this.a.dismiss();
        }
    }
}
