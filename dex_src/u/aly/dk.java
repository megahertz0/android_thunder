package u.aly;

import com.umeng.analytics.h.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// compiled from: Sender.java
final class dk implements b {
    final /* synthetic */ dj a;

    dk(dj djVar) {
        this.a = djVar;
    }

    public final void a(File file) {
    }

    public final boolean b(File file) {
        Throwable th;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                int i;
                byte[] b = u.b(fileInputStream);
                u.c(fileInputStream);
                byte[] a = this.a.b.a(b);
                if (a == null) {
                    i = 1;
                } else {
                    i = this.a.a(a);
                }
                if (i == 2 && this.a.a.d()) {
                    this.a.a.c();
                }
                return this.a.e || i != 1;
            } catch (Throwable th2) {
                th = th2;
                u.c(fileInputStream);
                throw th;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public final void c(File file) {
        this.a.a.b();
    }
}
