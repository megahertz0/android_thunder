package u.aly;

import java.util.ArrayList;
import java.util.List;

// compiled from: AbstractIdTracker.java
public abstract class a {
    final String a;
    List<az> b;
    ba c;
    private final int d;
    private final int e;

    public abstract String b();

    public a(String str) {
        this.d = 10;
        this.e = 20;
        this.a = str;
    }

    public final boolean a() {
        return this.c == null || this.c.c <= 20;
    }

    public final void a(bb bbVar) {
        this.c = (ba) bbVar.a.get(this.a);
        List<az> list = bbVar.b;
        if (list != null && list.size() > 0) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            for (az azVar : list) {
                if (this.a.equals(azVar.a)) {
                    this.b.add(azVar);
                }
            }
        }
    }
}
