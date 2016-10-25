package org.apache.thrift.transport;

import java.io.InputStream;
import java.io.OutputStream;

public class a extends d {
    protected InputStream a;
    protected OutputStream b;

    protected a() {
        this.a = null;
        this.b = null;
    }

    public a(OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.b = outputStream;
    }

    public int a(byte[] bArr, int i, int i2) {
        if (this.a == null) {
            throw new e(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new e(4);
        } catch (Throwable e) {
            throw new e(0, e);
        }
    }

    public void b(byte[] bArr, int i, int i2) {
        if (this.b == null) {
            throw new e(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new e(0, e);
        }
    }
}
