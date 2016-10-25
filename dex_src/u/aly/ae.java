package u.aly;

import java.io.ByteArrayOutputStream;
import u.aly.an.a;

// compiled from: TSerializer.java
public final class ae {
    private final ByteArrayOutputStream a;
    private final cg b;
    private bu c;

    public ae() {
        this(new a());
    }

    private ae(bx bxVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new cg(this.a);
        this.c = bxVar.a(this.b);
    }

    public final byte[] a(y yVar) throws ac {
        this.a.reset();
        yVar.b(this.c);
        return this.a.toByteArray();
    }
}
