package com.xunlei.downloadprovider.filemanager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: FileManagerBaseActivity.java
final class d implements OnClickListener {
    final /* synthetic */ FileManagerBaseActivity a;

    d(FileManagerBaseActivity fileManagerBaseActivity) {
        this.a = fileManagerBaseActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.g.obtainMessage(FileManagerBaseActivity.a).sendToTarget();
        dialogInterface.dismiss();
    }
}
