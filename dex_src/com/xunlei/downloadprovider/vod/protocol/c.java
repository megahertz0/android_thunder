package com.xunlei.downloadprovider.vod.protocol;

import android.os.Handler;
import android.text.TextUtils;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.service.downloads.task.h;
import com.xunlei.downloadprovider.vod.protocol.b.b;
import java.io.File;

// compiled from: DownloadVodUtil.java
public final class c extends a<b> {
    final /* synthetic */ b a;

    public c(b bVar, b bVar2) {
        this.a = bVar;
        super(bVar2);
    }

    public final /* synthetic */ void a(Object obj) {
        b bVar = (b) obj;
        if (bVar != null) {
            com.xunlei.downloadprovider.vod.protocol.b.c cVar = new com.xunlei.downloadprovider.vod.protocol.b.c();
            cVar.a = bVar;
            cVar.c = 0;
            a aVar = new a();
            aVar.a = bVar.a;
            aVar.b = bVar.a;
            Object obj2 = bVar.a;
            cVar.b = aVar;
            if (aVar.a.contains("://127.0.0.1")) {
                b.a(this.a, cVar);
                return;
            }
            if (bVar.a.startsWith("bt://")) {
                obj2 = com.xunlei.downloadprovider.service.downloads.b.c.a(bVar.b, bVar.d, bVar.c, bVar.e);
                aVar.a = obj2;
            }
            d.a();
            long e = d.e(r0);
            if (e > 0) {
                aVar.c = e;
                d.a();
                TaskInfo d = d.d(e);
                if (new File(d.mLocalFileName).exists()) {
                    if (d != null) {
                        d.a();
                        aVar.b = d.c(d.mLocalFileName);
                    }
                    if (TextUtils.isEmpty(aVar.b)) {
                        cVar.c = -1;
                    }
                    b.a(this.a, cVar);
                    return;
                }
                this.a.a(0, d);
                return;
            }
            cVar.c = -1;
            com.xunlei.downloadprovider.service.downloads.task.c cVar2 = new com.xunlei.downloadprovider.service.downloads.task.c(new d(this));
            if (!TextUtils.isEmpty(r0)) {
                cVar2.a(r0, bVar.d, com.umeng.a.d);
            }
            this.a.g = cVar;
            d a = d.a();
            Handler a2 = this.a.b();
            DownloadService a3 = DownloadService.a();
            if (a3 != null) {
                a3.a(cVar2, a2);
            } else {
                DownloadService.a(new h(a, cVar2, a2));
            }
        }
    }
}
