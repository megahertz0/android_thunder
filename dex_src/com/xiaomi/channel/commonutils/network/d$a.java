package com.xiaomi.channel.commonutils.network;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class d$a extends FilterInputStream {
    private boolean a;

    public d$a(InputStream inputStream) {
        super(inputStream);
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (!this.a) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                return read;
            }
        }
        this.a = true;
        return -1;
    }
}
