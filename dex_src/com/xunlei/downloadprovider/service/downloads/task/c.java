package com.xunlei.downloadprovider.service.downloads.task;

import android.os.Handler;
import android.text.TextUtils;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.info.a;

// compiled from: DownloadTask.java
public final class c extends i<TaskInfo> {
    protected a a;
    protected int b;
    public Handler c;

    public c(i.a<TaskInfo> aVar) {
        super(aVar);
    }

    public c() {
        super(null);
    }

    public final a a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final void a(b bVar) {
        if (bVar != null && this.a != null) {
            a.a a = this.a.a();
            a.g = bVar.a;
            a.h = bVar.b;
            a.e = bVar.c;
            a.f = bVar.d;
            if (!TextUtils.isEmpty(bVar.e)) {
                a.d = bVar.e;
            }
        }
    }

    public final boolean a(String str, String str2, String str3, String str4) {
        a aVar = new a(str, com.xunlei.downloadprovider.service.downloads.b.c.a(str2), null, null, str3);
        aVar.i = str4;
        aVar.g = 0;
        a.a aVar2 = new a.a();
        aVar.j = aVar2;
        aVar2.a = false;
        aVar2.b = 2057;
        aVar2.c = 1;
        this.a = aVar;
        this.b = 1;
        return true;
    }

    public final boolean a(String str, String str2, long j, String str3, String str4, boolean z, int i, String str5, int i2) {
        a aVar = new a(str, com.xunlei.downloadprovider.service.downloads.b.c.a(str2), str3, str4, str5);
        aVar.g = j;
        a.a aVar2 = new a.a();
        aVar.j = aVar2;
        aVar2.a = z;
        aVar2.b = i;
        aVar2.c = i2;
        this.a = aVar;
        this.b = 1;
        return true;
    }

    public final boolean a(String str, long j, String str2) {
        a aVar = new a(str, com.xunlei.downloadprovider.service.downloads.b.c.a(null), null, null, str2);
        aVar.g = j;
        a.a aVar2 = new a.a();
        aVar.j = aVar2;
        aVar2.a = false;
        aVar2.b = 0;
        this.a = aVar;
        this.b = 1;
        return true;
    }

    public final boolean a(String str, String str2, String str3, String str4, long j, int i, String str5, int i2) {
        a aVar = new a(str3, str4, j, com.xunlei.downloadprovider.service.downloads.b.c.a(str), str5);
        aVar.b = null;
        aVar.d = str2;
        aVar.g = j;
        a.a aVar2 = new a.a();
        aVar.j = aVar2;
        aVar2.a = false;
        aVar2.b = i;
        aVar2.c = i2;
        this.a = aVar;
        this.b = 2;
        return true;
    }
}
