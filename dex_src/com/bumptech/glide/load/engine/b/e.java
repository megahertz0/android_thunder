package com.bumptech.glide.load.engine.b;

import com.bumptech.glide.a.a;
import com.bumptech.glide.a.a.c;
import com.bumptech.glide.load.b;
import java.io.File;
import java.io.IOException;

// compiled from: DiskLruCacheWrapper.java
public final class e implements a {
    private static e a;
    private final c b;
    private final k c;
    private final File d;
    private final int e;
    private a f;

    static {
        a = null;
    }

    public static synchronized a a(File file, int i) {
        a aVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e(file, i);
            }
            aVar = a;
        }
        return aVar;
    }

    private e(File file, int i) {
        this.b = new c();
        this.d = file;
        this.e = i;
        this.c = new k();
    }

    private synchronized a a() throws IOException {
        if (this.f == null) {
            this.f = a.a(this.d, (long) this.e);
        }
        return this.f;
    }

    public final File a(b bVar) {
        try {
            c a = a().a(this.c.a(bVar));
            return a != null ? a.a[0] : null;
        } catch (IOException e) {
            return null;
        }
    }

    public final void a(b bVar, a.b bVar2) {
        a aVar;
        String a = this.c.a(bVar);
        c cVar = this.b;
        synchronized (cVar) {
            aVar = (a) cVar.a.get(bVar);
            if (aVar == null) {
                aVar = cVar.b.a();
                cVar.a.put(bVar, aVar);
            }
            aVar.b++;
        }
        aVar.a.lock();
        try {
            a.a b = a().b(a);
            if (b != null) {
                if (bVar2.a(b.a())) {
                    b.d.a(b, true);
                    b.c = true;
                }
                b.c();
            }
            this.b.a(bVar);
        } catch (IOException e) {
            this.b.a(bVar);
        } catch (Throwable th) {
        }
    }

    public final void b(b bVar) {
        try {
            a().c(this.c.a(bVar));
        } catch (IOException e) {
        }
    }
}
