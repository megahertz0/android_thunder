package u.aly;

import android.content.Context;
import u.aly.cm.a;

// compiled from: Defcon.java
public final class n implements di {
    private static n c;
    public int a;
    private final long b;

    static {
        c = null;
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (c == null) {
                c = new n();
                c.a(cm.a(context).c.a());
            }
            nVar = c;
        }
        return nVar;
    }

    private n() {
        this.a = 0;
        this.b = 60000;
    }

    private void a(int i) {
        if (i >= 0 && i <= 3) {
            this.a = i;
        }
    }

    public final boolean a() {
        return this.a != 0;
    }

    public final void a(a aVar) {
        a(aVar.a());
    }
}
