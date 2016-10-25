package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: TaskDownloadingAdCardViewHolder.java
final class ar implements OnClickListener {
    final /* synthetic */ af a;

    ar(af afVar) {
        this.a = afVar;
    }

    public final void onClick(View view) {
        af.a(this.a).h.f.remove(LOAD_TAG.LOAD_LIST_AD);
        af.a(this.a).a(af.b(this.a));
        a.c(h.a(af.a(this.a).a()), "baidu", "baidu", this.a.d(), BuildConfig.VERSION_NAME);
        h.a[af.a(this.a).a()] = true;
        af.c(this.a);
    }
}
