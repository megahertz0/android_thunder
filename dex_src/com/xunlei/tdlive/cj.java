package com.xunlei.tdlive;

import com.xunlei.tdlive.play.a.q.a;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.play.view.b;
import com.xunlei.tdlive.play.view.u;
import com.xunlei.tdlive.util.q;

// compiled from: LivePlayerDialog.java
class cj implements a {
    final /* synthetic */ au a;

    cj(au auVar) {
        this.a = auVar;
    }

    public void a(u.a aVar) {
        u uVar = new u(this.a.getOwnerActivity());
        b.a aVar2 = new b.a();
        aVar2.a(this.a.getWindow());
        aVar2.a(au.g(this.a));
        aVar2.a((Object) aVar);
        uVar.a(aVar2);
        uVar.b();
        uVar.a(false);
        q.e("userinfo").a("host").a("hostid", au.h(this.a)).a("userid", aVar.b).a("follow", aVar.h).b(new String[0]);
    }

    public void a(ah.a aVar) {
        au.a(this.a, aVar);
        q.e("userinfo").a(aVar.i ? "host" : "viewerlist").a("hostid", au.h(this.a)).a("userid", aVar.f).a("follow", aVar.g).b(new String[0]);
    }

    public void a(boolean z) {
        au.a(this.a, z);
        if (!au.b(this.a, false)) {
            this.a.onBackPressed();
        }
    }

    public void a(int i) {
        if (!au.i(this.a) && i == 3) {
            au.a(this.a).setVisibility(0);
            au.b(this.a).setText("\u4e3b\u64ad\u6682\u65f6\u79bb\u5f00");
        }
    }
}
