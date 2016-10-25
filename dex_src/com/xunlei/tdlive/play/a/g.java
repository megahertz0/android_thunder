package com.xunlei.tdlive.play.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.play.view.u;
import com.xunlei.tdlive.play.view.u.a;

// compiled from: BaseNormalScreenLayoutPresenter.java
class g implements OnClickListener {
    final /* synthetic */ c a;

    g(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (this.a.b != null && this.a.d != null) {
            if (this.a.e()) {
                a a = u.a(this.a.d);
                a.a = this.a.c;
                a.h = this.a.k;
                this.a.b.a(a);
                return;
            }
            ah.a aVar = new ah.a();
            aVar.g = false;
            aVar.b = this.a.d.a.a;
            aVar.e = this.a.d.a.d;
            aVar.c = 0;
            aVar.f = this.a.d.a.b;
            aVar.a = this.a.d.a.c;
            aVar.i = true;
            aVar.j = this.a.c;
            aVar.h = new h(this);
            this.a.b.a(aVar);
        }
    }
}
