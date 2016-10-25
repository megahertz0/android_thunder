package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.a;
import com.xunlei.downloadprovider.download.tasklist.list.a.q;
import com.xunlei.downloadprovider.download.tasklist.list.a.q$a;
import java.util.List;

// compiled from: ThunderLoader.java
final class r implements b<String> {
    final /* synthetic */ a a;
    final /* synthetic */ q b;

    r(q qVar, a aVar) {
        this.b = qVar;
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        q$a a = q.a((String) obj);
        if (a != null) {
            int i = a.a;
            if (i == 0) {
                List<com.xunlei.downloadprovider.download.tasklist.list.a.b.b> list = a.b;
                if (list != null) {
                    for (com.xunlei.downloadprovider.download.tasklist.list.a.b.b bVar : list) {
                        if (bVar.c.a == 1175) {
                            if (bVar.r) {
                                a.a().d = bVar;
                            } else {
                                a.a().g = bVar;
                            }
                        } else if (bVar.c.a == 1176) {
                            if (bVar.r) {
                                a.a().e = bVar;
                            } else {
                                a.a().h = bVar;
                            }
                        } else if (bVar.c.a == 1177) {
                            if (bVar.r) {
                                a.a().f = bVar;
                            } else {
                                a.a().i = bVar;
                            }
                        }
                    }
                }
            } else if (this.a != null) {
                this.a.a(i);
                return;
            } else {
                return;
            }
        }
        if (this.a != null) {
            this.a.a(null);
        }
    }
}
