package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: ThunderUpdateADServer.java
public final class u implements a {
    public static u a;
    private ArrayList<a> b;
    private boolean c;

    private u() {
        this.c = false;
        this.b = new ArrayList();
    }

    public static u a() {
        if (a == null) {
            a = new u();
        }
        return a;
    }

    final void a(a aVar) {
        if (this.c) {
            aVar.a();
        } else {
            this.b.add(aVar);
        }
    }

    public final void a(List<b> list) {
        this.c = true;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar != null) {
                aVar.a();
            }
        }
        this.b.clear();
    }

    public final void a(int i) {
        String str = "adv_downloadtab_ssp_fail";
        a.a(g.a("android_advertise", str, str).b("errorcode", (long) i).a("net_type", a.a()));
        this.c = true;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar != null) {
                aVar.a();
            }
        }
        this.b.clear();
    }
}
