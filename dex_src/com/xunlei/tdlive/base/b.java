package com.xunlei.tdlive.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

// compiled from: ActionSheetDialog.java
class b implements OnCancelListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a.a != null) {
            this.a.a.onClick(dialogInterface, 0);
        }
    }
}
