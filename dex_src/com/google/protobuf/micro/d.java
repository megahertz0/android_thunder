package com.google.protobuf.micro;

import java.io.IOException;

public abstract class d {
    public abstract int a();

    public abstract d a(a aVar);

    public d a(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    public abstract void a(b bVar);

    public void a(byte[] bArr, int i, int i2) {
        try {
            b a = b.a(bArr, i, i2);
            a(a);
            a.c();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    public boolean a(a aVar, int i) {
        return aVar.b(i);
    }

    public d b(byte[] bArr, int i, int i2) {
        try {
            a a = a.a(bArr, i, i2);
            a(a);
            a.a(0);
            return this;
        } catch (c e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public byte[] b() {
        byte[] bArr = new byte[a()];
        a(bArr, 0, bArr.length);
        return bArr;
    }
}
