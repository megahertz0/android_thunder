package com.xunlei.downloadprovider.web.record;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.a.a;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.model.p;
import java.util.ArrayList;
import java.util.List;

// compiled from: FavorAndHistroyActivity.java
final class q implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    q(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        RecordPageView c = this.a.h;
        List arrayList = new ArrayList();
        if (c.a.equals("favor")) {
            for (t tVar : c.d) {
                arrayList.add(((b) tVar.b).c);
            }
            a.a().d.b(arrayList);
        } else {
            for (t tVar2 : c.d) {
                arrayList.add(((o) tVar2.b).b);
            }
            BrothersApplication.a();
            p.a().a(arrayList);
        }
        c.c();
        c.a(false, null);
        c.b();
        this.a.q.dismiss();
        this.a.q = null;
        if (this.a.h.getTabType().equals("favor") && this.a.t != null) {
            this.a.t.setVisibility(0);
        }
    }
}
