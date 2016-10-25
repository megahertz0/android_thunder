package com.xunlei.downloadprovider.task;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ThunderTask.java
final class b implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ ThunderTask b;

    b(ThunderTask thunderTask, a aVar) {
        this.b = thunderTask;
        this.a = aVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        this.b.onCreateTask(false, this.a.i.a);
    }
}
