package com.google.zxing.qrcode.decoder;

import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;

// compiled from: FormatInformation.java
final class f {
    private static final int[][] c;
    private static final int[] d;
    final ErrorCorrectionLevel a;
    final byte b;

    static {
        c = new int[][]{new int[]{21522, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
        d = new int[]{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
    }

    private f(int i) {
        this.a = ErrorCorrectionLevel.forBits((i >> 3) & 3);
        this.b = (byte) (i & 7);
    }

    static int a(int i, int i2) {
        int i3 = i ^ i2;
        return d[(i3 >>> 28) & 15] + ((((((d[i3 & 15] + d[(i3 >>> 4) & 15]) + d[(i3 >>> 8) & 15]) + d[(i3 >>> 12) & 15]) + d[(i3 >>> 16) & 15]) + d[(i3 >>> 20) & 15]) + d[(i3 >>> 24) & 15]);
    }

    static f b(int i, int i2) {
        f c = c(i, i2);
        return c != null ? c : c(i ^ 21522, i2 ^ 21522);
    }

    private static f c(int i, int i2) {
        int i3;
        Object obj = InMobiClientPositioning.NO_REPEAT;
        int[][] iArr = c;
        int length = iArr.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int[] iArr2 = iArr[i4];
            int i6 = iArr2[0];
            if (i6 != i && i6 != i2) {
                int i7;
                int a = a(i, i6);
                if (a < i3) {
                    i3 = iArr2[1];
                } else {
                    a = i3;
                    i3 = i5;
                }
                if (i != i2) {
                    i5 = a(i2, i6);
                    if (i5 < a) {
                        i3 = iArr2[1];
                        i4++;
                        i7 = i3;
                        i3 = i5;
                        i5 = i7;
                    }
                }
                i5 = a;
                i4++;
                i7 = i3;
                i3 = i5;
                i5 = i7;
            }
            return new f(iArr2[1]);
        }
        return i3 <= 3 ? new f(i5) : null;
    }

    public final int hashCode() {
        return (this.a.ordinal() << 3) | this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.a == fVar.a && this.b == fVar.b;
    }
}
