package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: TaskAdCardViewHolder.java
final class ad implements OnClickListener {
    final /* synthetic */ r a;

    ad(r rVar) {
        this.a = rVar;
    }

    public final void onClick(View view) {
        r.a(this.a).h.f.remove(LOAD_TAG.LOAD_LIST_AD);
        r.a(this.a).a(r.b(this.a));
        a.c(h.a(r.a(this.a).a()), "baidu", "baidu", this.a.d(), BuildConfig.VERSION_NAME);
        h.a[r.a(this.a).a()] = true;
        r.c(this.a);
    }
}
