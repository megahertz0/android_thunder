package com.xunlei.tdlive.usercenter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: SexModifyActivity.java
class j implements OnClickListener {
    final /* synthetic */ SexModifyActivity a;

    j(SexModifyActivity sexModifyActivity) {
        this.a = sexModifyActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 1) {
            this.a.a();
            dialogInterface.dismiss();
        }
    }
}
