package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: LiveGiftDialog.java
class o implements OnClickListener {
    final /* synthetic */ i a;

    o(i iVar) {
        this.a = iVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 1) {
            RechargeActivity.a(this.a.getContext(), SimpleLog.LOG_LEVEL_DEBUG);
        }
        dialogInterface.dismiss();
    }
}
