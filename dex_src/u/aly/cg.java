package u.aly;

import java.io.InputStream;
import java.io.OutputStream;

// compiled from: TIOStreamTransport.java
public final class cg extends ci {
    protected InputStream a;
    protected OutputStream b;

    protected cg() {
        this.a = null;
        this.b = null;
    }

    public cg(OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.b = outputStream;
    }

    public final int a(byte[] bArr, int i, int i2) throws cj {
        if (this.a == null) {
            throw new cj("Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new cj((byte) 0);
        } catch (Throwable e) {
            throw new cj(e);
        }
    }

    public final void b(byte[] bArr, int i, int i2) throws cj {
        if (this.b == null) {
            throw new cj("Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new cj(e);
        }
    }
}
