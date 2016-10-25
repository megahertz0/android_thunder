package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: RegisterActivity.java
class dy implements OnClickListener {
    final /* synthetic */ dw a;

    dy(dw dwVar) {
        this.a = dwVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 1) {
            dialogInterface.dismiss();
            this.a.a.finish();
        }
    }
}
