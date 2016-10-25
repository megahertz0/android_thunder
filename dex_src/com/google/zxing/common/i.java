package com.google.zxing.common;

import com.google.zxing.f;
import com.google.zxing.j;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import java.lang.reflect.Array;

// compiled from: HybridBinarizer.java
public final class i extends g {
    private b b;

    public i(f fVar) {
        super(fVar);
    }

    public final b a() throws j {
        if (this.b != null) {
            return this.b;
        }
        f fVar = this.a;
        int i = fVar.a;
        int i2 = fVar.b;
        if (i < 40 || i2 < 40) {
            this.b = super.a();
        } else {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            int i12;
            byte[] a = fVar.a();
            int i13 = i >> 3;
            if ((i & 7) != 0) {
                i3 = i13 + 1;
            } else {
                i3 = i13;
            }
            i13 = i2 >> 3;
            if ((i2 & 7) != 0) {
                i4 = i13 + 1;
            } else {
                i4 = i13;
            }
            int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i4, i3});
            for (int i14 = 0; i14 < i4; i14++) {
                i5 = i14 << 3;
                i6 = i2 - 8;
                if (i5 <= i6) {
                    i6 = i5;
                }
                int i15 = 0;
                while (i15 < i3) {
                    i7 = i15 << 3;
                    i5 = i - 8;
                    if (i7 <= i5) {
                        i5 = i7;
                    }
                    i8 = 0;
                    i9 = VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX;
                    i7 = 0;
                    i10 = 0;
                    i11 = (i6 * i) + i5;
                    while (i10 < 8) {
                        int i16 = 0;
                        while (i16 < 8) {
                            i5 = a[i11 + i16] & 255;
                            i12 = i8 + i5;
                            if (i5 < i9) {
                                i8 = i5;
                            } else {
                                i8 = i9;
                            }
                            if (i5 <= i7) {
                                i5 = i7;
                            }
                            i16++;
                            i9 = i8;
                            i7 = i5;
                            i8 = i12;
                        }
                        if (i7 - i9 > 24) {
                            i5 = i11 + i;
                            i11 = i10 + 1;
                            i10 = i8;
                            while (i11 < 8) {
                                i8 = i10;
                                for (i10 = 0; i10 < 8; i10++) {
                                    i8 += a[i5 + i10] & 255;
                                }
                                i11++;
                                i5 += i;
                                i10 = i8;
                            }
                        } else {
                            i5 = i11;
                            i11 = i10;
                            i10 = i8;
                        }
                        i8 = i11 + 1;
                        i11 = i5 + i;
                        int i17 = i8;
                        i8 = i10;
                        i10 = i17;
                    }
                    i5 = i8 >> 6;
                    if (i7 - i9 <= 24) {
                        i7 = i9 / 2;
                        if (i14 > 0 && i15 > 0) {
                            i5 = ((iArr[i14 - 1][i15] + (iArr[i14][i15 - 1] * 2)) + iArr[i14 - 1][i15 - 1]) / 4;
                            if (i9 < i5) {
                            }
                        }
                        i5 = i7;
                    }
                    iArr[i14][i15] = i5;
                    i15++;
                }
            }
            b bVar = new b(i, i2);
            for (i11 = 0; i11 < i4; i11++) {
                i5 = i11 << 3;
                i6 = i2 - 8;
                if (i5 > i6) {
                    i7 = i6;
                } else {
                    i7 = i5;
                }
                for (i8 = 0; i8 < i3; i8++) {
                    i5 = i8 << 3;
                    i6 = i - 8;
                    if (i5 <= i6) {
                        i6 = i5;
                    }
                    i9 = a(i8, i3 - 3);
                    i12 = a(i11, i4 - 3);
                    i10 = 0;
                    for (i5 = Tabs.TAB_CREATE_REACH_MAX_COUNT; i5 <= 2; i5++) {
                        int[] iArr2 = iArr[i12 + i5];
                        i10 += iArr2[i9 + 2] + (((iArr2[i9 - 2] + iArr2[i9 - 1]) + iArr2[i9]) + iArr2[i9 + 1]);
                    }
                    i12 = i10 / 25;
                    i9 = 0;
                    i10 = (i7 * i) + i6;
                    while (i9 < 8) {
                        for (i5 = 0; i5 < 8; i5++) {
                            if ((a[i10 + i5] & 255) <= i12) {
                                bVar.b(i6 + i5, i7 + i9);
                            }
                        }
                        i9++;
                        i10 += i;
                    }
                }
            }
            this.b = bVar;
        }
        return this.b;
    }

    private static int a(int i, int i2) {
        if (i < 2) {
            return 2;
        }
        return i <= i2 ? i : i2;
    }
}
