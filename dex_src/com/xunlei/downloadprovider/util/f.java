package com.xunlei.downloadprovider.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: CreateTaskFromClipBoardHandler.java
public final class f implements OnClickListener {
    final /* synthetic */ d a;

    public f(d dVar) {
        this.a = dVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
    }
}
