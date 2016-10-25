package org.apache.thrift;

import java.io.ByteArrayOutputStream;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.transport.a;

public class g {
    private final ByteArrayOutputStream a;
    private final a b;
    private f c;

    public g() {
        this(new org.apache.thrift.protocol.a.a());
    }

    public g(h hVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new a(this.a);
        this.c = hVar.a(this.b);
    }

    public byte[] a(b bVar) {
        this.a.reset();
        bVar.b(this.c);
        return this.a.toByteArray();
    }
}
