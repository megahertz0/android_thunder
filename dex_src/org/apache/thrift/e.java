package org.apache.thrift;

import org.apache.thrift.protocol.a.a;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.transport.c;

public class e {
    private final f a;
    private final c b;

    public e() {
        this(new a());
    }

    public e(h hVar) {
        this.b = new c();
        this.a = hVar.a(this.b);
    }

    public void a(b bVar, byte[] bArr) {
        this.b.a(bArr);
        bVar.a(this.a);
        this.a.y();
    }
}
