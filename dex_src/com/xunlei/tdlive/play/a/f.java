package com.xunlei.tdlive.play.a;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.tdlive.a.ae.d;
import com.xunlei.tdlive.play.view.ah.a;

// compiled from: BaseNormalScreenLayoutPresenter.java
class f implements OnItemClickListener {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.e != null && i < this.a.e.size() && this.a.d != null && this.a.d.a != null) {
            d dVar = (d) this.a.e.get(i);
            if (this.a.b != null && dVar != null) {
                a aVar = new a();
                aVar.a = dVar.c;
                aVar.b = dVar.b;
                aVar.f = dVar.a;
                aVar.c = 0;
                aVar.e = dVar.d;
                aVar.j = this.a.c;
                this.a.b.a(aVar);
            }
        }
    }
}
