package com.xiaomi.push.log;

import java.io.File;
import java.util.Date;

class c extends b$b {
    File a;
    final /* synthetic */ int b;
    final /* synthetic */ Date c;
    final /* synthetic */ Date d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ boolean g;
    final /* synthetic */ b h;

    c(b bVar, int i, Date date, Date date2, String str, String str2, boolean z) {
        this.h = bVar;
        this.b = i;
        this.c = date;
        this.d = date2;
        this.e = str;
        this.f = str2;
        this.g = z;
        super(bVar);
    }

    public void b() {
        if (com.xiaomi.channel.commonutils.file.c.d()) {
            try {
                File file = new File(b.a(this.h).getExternalFilesDir(null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    a aVar = new a();
                    aVar.a(this.b);
                    this.a = aVar.a(b.a(this.h), this.c, this.d, file);
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void c() {
        if (this.a != null && this.a.exists()) {
            b.b(this.h).add(new b$c(this.h, this.e, this.f, this.a, this.g));
        }
        b.a(this.h, 0);
    }
}
