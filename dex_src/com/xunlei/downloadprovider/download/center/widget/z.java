package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;

// compiled from: DownloadTaskMoreOperationDialog.java
final class z implements OnClickListener {
    final /* synthetic */ v a;

    z(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        long j;
        a.c("delete", a.a(this.a.i), this.a.k);
        if (this.a.i.c || this.a.i.mDownloadedSize == 0) {
            j = 1;
        } else {
            j = this.a.i.mDownloadedSize;
        }
        if (this.a.i.mTaskStatus == 8) {
            this.a.j = new t(this.a.getContext(), 1, 0, j, "press");
        } else {
            this.a.j = new t(this.a.getContext(), 0, 1, j, "press");
        }
        if (!(this.a.i == null || this.a.i.mTaskStatus == 8)) {
            this.a.j.b(null);
        }
        this.a.j.d = new aa(this);
        this.a.j.e = new ab(this);
        this.a.j.show();
        this.a.dismiss();
    }
}
