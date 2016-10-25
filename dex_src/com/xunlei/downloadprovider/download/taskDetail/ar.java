package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: TaskDetailDeleteDialog.java
final class ar implements OnClickListener {
    final /* synthetic */ ao a;

    ar(ao aoVar) {
        this.a = aoVar;
    }

    public final void onClick(View view) {
        if (ao.b(this.a) != null) {
            ao.b(this.a).onClick(view);
        }
        this.a.dismiss();
    }
}
