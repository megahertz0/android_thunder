package com.xunlei.downloadprovider.web.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.k;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.f;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;
import com.xunlei.downloadprovider.web.base.model.d;
import com.xunlei.downloadprovider.web.base.model.p;

// compiled from: ShortMovieDetailFragment.java
final class be extends k {
    final /* synthetic */ ShortMovieDetailFragment a;

    be(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void a(RecyclerView recyclerView, int i) {
        long j = 0;
        boolean z = true;
        super.a(recyclerView, i);
        if (i == 0 || i == 1) {
            ShortMovieDetailFragment.a(this.a, ShortMovieDetailFragment.f(this.a).j(), ShortMovieDetailFragment.f(this.a).k());
            a aVar = (a) ShortMovieDetailFragment.i(this.a);
            if (!ShortMovieDetailFragment.g(this.a) || ShortMovieDetailFragment.h(this.a)) {
                z = false;
            }
            aVar.c(z);
        }
        if (ShortMovieDetailFragment.j(this.a) != 0 && ShortMovieDetailFragment.k(this.a) != 0 && ShortMovieDetailFragment.l(this.a) && i == 0 && ShortMovieDetailFragment.f(this.a).k() >= ShortMovieDetailFragment.m(this.a).getItemCount() - 2) {
            ShortMovieDetailFragment.n(this.a);
            ShortMovieDetailFragment.m(this.a).a(ShortMovieDetailFragment.o(this.a));
            d p = ShortMovieDetailFragment.p(this.a);
            com.xunlei.downloadprovider.c.a aVar2 = p.a;
            a pVar = new p(p);
            String str = "new";
            String str2 = "loadmore";
            if (aVar2.e != null) {
                f fVar = aVar2.e;
                if (!(fVar.e == null || fVar.e.isEmpty())) {
                    j = ((c) fVar.e.get(fVar.e.size() - 1)).a;
                }
            }
            aVar2.a(pVar, str, str2, j);
        }
    }
}
