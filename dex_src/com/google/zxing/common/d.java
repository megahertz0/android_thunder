package com.google.zxing.common;

import java.util.List;

// compiled from: DecoderResult.java
public final class d {
    public final byte[] a;
    public final String b;
    public final List<byte[]> c;
    public final String d;
    public Integer e;
    public Integer f;
    public Object g;
    public final int h;
    public final int i;

    public d(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public d(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.a = bArr;
        this.b = str;
        this.c = list;
        this.d = str2;
        this.h = i2;
        this.i = i;
    }
}
