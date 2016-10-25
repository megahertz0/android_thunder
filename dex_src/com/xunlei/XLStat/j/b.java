package com.xunlei.XLStat.j;

import java.util.Arrays;

public class b {
    public static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >>> 24) & 255)};
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[7 - i] = (byte) ((int) ((j >> (64 - ((i + 1) * 8))) & 255));
        }
        return bArr;
    }

    public static byte[] a(short s) {
        int i = 0;
        byte[] bArr = new byte[2];
        Arrays.fill(bArr, (byte) 0);
        while (i < 2) {
            bArr[i] = new Integer(s & 255).byteValue();
            s = (short) (s >> 8);
            i++;
        }
        return bArr;
    }

    public static short a(byte[] bArr) {
        return (short) (((short) (bArr[0] & 255)) | ((short) (((short) (bArr[1] & 255)) << 8)));
    }
}
