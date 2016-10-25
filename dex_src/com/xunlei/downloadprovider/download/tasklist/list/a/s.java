package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.ad.common.d.c;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;

// compiled from: TaskAdCardViewHolder.java
final class s implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ r b;

    s(r rVar, b bVar) {
        this.b = rVar;
        this.a = bVar;
    }

    public final void onClick(View view) {
        String str;
        r.a(this.b, this.a);
        String a = h.a(r.a(this.b).a());
        if (this.a.r) {
            str = "moren";
        } else {
            str = "shangwu";
        }
        a.b(a, str, this.a.c.b, this.b.d(), this.a.m);
        com.xunlei.downloadprovider.ad.common.d.a.a aVar = new com.xunlei.downloadprovider.ad.common.d.a.a();
        aVar.b = this.a.c.b;
        aVar.c = this.a.c.a;
        aVar.a = 2;
        com.xunlei.downloadprovider.j.a.a().e().a(new c(aVar, new t(this), new u(this)));
    }
}
