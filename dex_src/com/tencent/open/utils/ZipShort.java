package com.tencent.open.utils;

// compiled from: ProGuard
public final class ZipShort implements Cloneable {
    private int a;

    public ZipShort(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipShort(byte[] bArr, int i) {
        this.a = (bArr[i + 1] << 8) & 65280;
        this.a += bArr[i] & 255;
    }

    public ZipShort(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipShort) && this.a == ((ZipShort) obj).getValue();
    }

    public final byte[] getBytes() {
        return new byte[]{(byte) (this.a & 255), (byte) ((this.a & 65280) >> 8)};
    }

    public final int getValue() {
        return this.a;
    }

    public final int hashCode() {
        return this.a;
    }
}
