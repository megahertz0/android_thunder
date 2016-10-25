package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.a.a;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.model.p;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;

// compiled from: RecordPageView.java
final class w extends Thread {
    final /* synthetic */ RecordPageView a;

    w(RecordPageView recordPageView) {
        this.a = recordPageView;
    }

    public final void run() {
        List arrayList = new ArrayList();
        if (RecordPageView.d(this.a).equals("favor")) {
            Object<b> b = a.a().d.b();
            if (!d.a(b)) {
                for (b bVar : b) {
                    arrayList.add(new t(bVar));
                }
            }
        } else {
            BrothersApplication.a();
            Object<o> b2 = p.a().b();
            if (!d.a(b2)) {
                for (o oVar : b2) {
                    arrayList.add(new t(oVar));
                }
            }
        }
        RecordPageView.e(this.a).obtainMessage(25000, arrayList).sendToTarget();
    }
}
