package com.taobao.accs.utl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

// compiled from: Taobao
public class g extends ByteArrayInputStream {
    public g(byte[] bArr) {
        super(bArr);
    }

    public int a() {
        return read() & 255;
    }

    public int b() {
        return (a() << 8) | a();
    }

    public String a(int i) throws IOException {
        byte[] bArr = new byte[i];
        int read = read(bArr);
        if (read == i) {
            return new String(bArr, "utf-8");
        }
        throw new IOException(new StringBuilder("read len not match. ask for ").append(i).append(" but read for ").append(read).toString());
    }

    public byte[] c() throws IOException {
        byte[] bArr = new byte[available()];
        read(bArr);
        return bArr;
    }
}
