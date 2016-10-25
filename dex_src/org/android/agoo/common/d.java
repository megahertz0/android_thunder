package org.android.agoo.common;

import android.text.TextUtils;

// compiled from: Taobao
public final class d {
    public static final int a(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 >> 2;
        int i5 = i3 ^ i2;
        int i6 = 0;
        while (i6 < i4) {
            int i7 = i6 << 2;
            i7 = ((bArr[(i7 + i) + 0] & 255) | (((((bArr[(i + i7) + 3] << 8) | (bArr[(i + i7) + 2] & 255)) << 8) | (bArr[(i + i7) + 1] & 255)) << 8)) * 1540483477;
            i6++;
            i5 = ((i7 ^ (i7 >>> 24)) * 1540483477) ^ (i5 * 1540483477);
        }
        i6 = i2 - (i4 << 2);
        if (i6 != 0) {
            if (i6 >= 3) {
                i5 ^= bArr[(i + i2) - 3] << 16;
            }
            if (i6 >= 2) {
                i5 ^= bArr[(i + i2) - 2] << 8;
            }
            if (i6 > 0) {
                i5 ^= bArr[(i + i2) - 1];
            }
            i5 *= 1540483477;
        }
        i5 = (i5 ^ (i5 >>> 13)) * 1540483477;
        return i5 ^ (i5 >>> 15);
    }

    public static final int a(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            return (int) (Math.random() * ((double) j));
        }
        byte[] bytes = str.getBytes();
        return (int) (((long) Math.abs(a(bytes, 0, bytes.length, Integer.MAX_VALUE))) % j);
    }
}
