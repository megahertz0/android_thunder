package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: TaskDetailDeleteDialog.java
final class at implements OnClickListener {
    final /* synthetic */ ao a;

    at(ao aoVar) {
        this.a = aoVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.c.onClick(view);
        }
        this.a.dismiss();
    }
}
