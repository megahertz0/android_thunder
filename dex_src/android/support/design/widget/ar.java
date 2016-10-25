package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.uc.addon.sdk.remote.EventIds;
import java.lang.ref.WeakReference;

// compiled from: SnackbarManager.java
final class ar {
    private static ar d;
    final Object a;
    b b;
    b c;
    private final Handler e;

    // compiled from: SnackbarManager.java
    static interface a {
    }

    // compiled from: SnackbarManager.java
    private static class b {
        final WeakReference<a> a;
        int b;

        final boolean a(a aVar) {
            return aVar != null && this.a.get() == aVar;
        }
    }

    static ar a() {
        if (d == null) {
            d = new ar();
        }
        return d;
    }

    private ar() {
        this.a = new Object();
        this.e = new Handler(Looper.getMainLooper(), new as(this));
    }

    public final void a(a aVar) {
        synchronized (this.a) {
            if (d(aVar)) {
                this.e.removeCallbacksAndMessages(this.b);
            }
        }
    }

    public final void b(a aVar) {
        synchronized (this.a) {
            if (d(aVar)) {
                b(this.b);
            }
        }
    }

    public final boolean c(a aVar) {
        boolean z;
        synchronized (this.a) {
            z = d(aVar) || e(aVar);
        }
        return z;
    }

    final boolean d(a aVar) {
        return this.b != null && this.b.a(aVar);
    }

    final boolean e(a aVar) {
        return this.c != null && this.c.a(aVar);
    }

    final boolean a(b bVar) {
        if (((a) bVar.a.get()) == null) {
            return false;
        }
        this.e.removeCallbacksAndMessages(bVar);
        return true;
    }

    final void b(b bVar) {
        if (bVar.b != -2) {
            int i = 2750;
            if (bVar.b > 0) {
                i = bVar.b;
            } else if (bVar.b == -1) {
                i = EventIds.EVENT_TRANSLATE;
            }
            this.e.removeCallbacksAndMessages(bVar);
            this.e.sendMessageDelayed(Message.obtain(this.e, 0, bVar), (long) i);
        }
    }
}
