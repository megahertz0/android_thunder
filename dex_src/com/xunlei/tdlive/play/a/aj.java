package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.play.a.q.a;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.play.view.b;
import com.xunlei.tdlive.play.view.u;
import com.xunlei.tdlive.util.q;

// compiled from: ReplayDialogPresenter.java
class aj implements a {
    final /* synthetic */ aa a;

    aj(aa aaVar) {
        this.a = aaVar;
    }

    public void a(u.a aVar) {
        u uVar = new u(this.a.f());
        b.a aVar2 = new b.a();
        aVar2.a(this.a.g());
        aVar2.a(aa.k(this.a).f());
        aVar2.a((Object) aVar);
        uVar.a(aVar2);
        uVar.b();
        a(aVar.b);
    }

    public void a(ah.a aVar) {
        aa.a(this.a, aVar);
    }

    public void a(boolean z) {
        aa.a(this.a).a(z);
    }

    public void a(int i) {
    }

    private void a(String str) {
        q.a aVar = new q.a();
        aVar.a("userid", str);
        q.a("userinfo", "live-room", null, aVar.a());
    }
}
