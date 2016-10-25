package com.bumptech.glide.h;

import java.util.Queue;

// compiled from: ByteArrayPool.java
public final class a {
    private static final a b;
    private final Queue<byte[]> a;

    static {
        b = new a();
    }

    public static a a() {
        return b;
    }

    private a() {
        this.a = h.a(0);
    }

    public final byte[] b() {
        byte[] bArr;
        synchronized (this.a) {
            bArr = (byte[]) this.a.poll();
        }
        return bArr == null ? new byte[65536] : bArr;
    }

    public final boolean a(byte[] bArr) {
        boolean z = false;
        if (bArr.length == 65536) {
            synchronized (this.a) {
                if (this.a.size() < 32) {
                    Object obj = 1;
                    this.a.offer(bArr);
                }
            }
        }
        return z;
    }
}
