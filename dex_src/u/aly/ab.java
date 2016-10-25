package u.aly;

import u.aly.an.a;

// compiled from: TDeserializer.java
public final class ab {
    private final bu a;
    private final ch b;

    public ab() {
        this(new a());
    }

    public ab(bx bxVar) {
        this.b = new ch();
        this.a = bxVar.a(this.b);
    }

    public final void a(y yVar, byte[] bArr) throws ac {
        ch chVar = this.b;
        int length = bArr.length;
        chVar.a = bArr;
        chVar.b = 0;
        chVar.c = length + 0;
        yVar.a(this.a);
        this.b.a = null;
        this.a.r();
    }
}
