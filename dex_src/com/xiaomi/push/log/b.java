package com.xiaomi.push.log;

import android.content.Context;
import com.xiaomi.channel.commonutils.file.c;
import com.xiaomi.smack.util.i;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class b {
    private static volatile b c;
    private final ConcurrentLinkedQueue<b> a;
    private Context b;

    static {
        c = null;
    }

    private b(Context context) {
        this.a = new ConcurrentLinkedQueue();
        this.b = context;
        this.a.add(new a(this));
        b(0);
    }

    public static b a(Context context) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(context);
                }
            }
        }
        c.b = context;
        return c;
    }

    private void a(long j) {
        b bVar = (b) this.a.peek();
        if (bVar != null && bVar.d()) {
            b(j);
        }
    }

    private void b() {
        if (!c.b() && !c.a()) {
            try {
                File file = new File(this.b.getExternalFilesDir(null) + "/.logcache");
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        file2.delete();
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    private void b(long j) {
        if (!this.a.isEmpty()) {
            i.a(new d(this), j);
        }
    }

    private void c() {
        while (!this.a.isEmpty()) {
            if (!((b) this.a.peek()).e() && this.a.size() <= 6) {
                return;
            }
            com.xiaomi.channel.commonutils.logger.b.c("remove Expired task");
            this.a.remove();
        }
    }

    public void a() {
        c();
        a(0);
    }

    public void a(String str, String str2, Date date, Date date2, int i, boolean z) {
        this.a.add(new c(this, i, date, date2, str, str2, z));
        b(0);
    }
}
