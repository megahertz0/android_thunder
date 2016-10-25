package com.xunlei.downloadprovider.homepage.relax.d;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: RelaxItemAdaper.java
final class d implements OnClickListener {
    final /* synthetic */ a$b a;
    final /* synthetic */ com.xunlei.downloadprovider.model.protocol.b.d b;
    final /* synthetic */ a c;

    d(a aVar, a$b com_xunlei_downloadprovider_homepage_relax_d_a_b, com.xunlei.downloadprovider.model.protocol.b.d dVar) {
        this.c = aVar;
        this.a = com_xunlei_downloadprovider_homepage_relax_d_a_b;
        this.b = dVar;
    }

    public final void onClick(View view) {
        a.a(this.c, this.a);
        a.a(this.c).a(this.b);
    }
}
