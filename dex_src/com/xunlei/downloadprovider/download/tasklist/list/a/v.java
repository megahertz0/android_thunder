package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;

// compiled from: TaskAdCardViewHolder.java
final class v implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ r b;

    v(r rVar, b bVar) {
        this.b = rVar;
        this.a = bVar;
    }

    public final void onClick(View view) {
        String str;
        r.a(this.b).h.f.remove(LOAD_TAG.LOAD_LIST_AD);
        r.a(this.b).a(r.b(this.b));
        String a = h.a(r.a(this.b).a());
        if (this.a.r) {
            str = "moren";
        } else {
            str = "shangwu";
        }
        a.c(a, str, this.a.c.b, this.b.d(), this.a.m);
        h.a[r.a(this.b).a()] = true;
        r.c(this.b);
    }
}
