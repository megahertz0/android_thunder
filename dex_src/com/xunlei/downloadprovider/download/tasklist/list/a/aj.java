package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;

// compiled from: TaskDownloadingAdCardViewHolder.java
final class aj implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ af b;

    aj(af afVar, b bVar) {
        this.b = afVar;
        this.a = bVar;
    }

    public final void onClick(View view) {
        String str;
        af.a(this.b).h.f.remove(LOAD_TAG.LOAD_LIST_AD);
        af.a(this.b).a(af.b(this.b));
        String a = h.a(af.a(this.b).a());
        if (this.a.r) {
            str = "moren";
        } else {
            str = "shangwu";
        }
        a.c(a, str, this.a.c.b, this.b.d(), this.a.m);
        h.a[af.a(this.b).a()] = true;
        af.c(this.b);
    }
}
