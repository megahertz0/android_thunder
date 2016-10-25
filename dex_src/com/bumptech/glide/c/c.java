package com.bumptech.glide.c;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: NeuQuant.java
final class c {
    protected int a;
    protected byte[] b;
    protected int c;
    protected int d;
    protected int[][] e;
    protected int[] f;
    protected int[] g;
    protected int[] h;
    protected int[] i;

    public c(byte[] bArr, int i, int i2) {
        this.f = new int[256];
        this.g = new int[256];
        this.h = new int[256];
        this.i = new int[32];
        this.b = bArr;
        this.c = i;
        this.d = i2;
        this.e = new int[256][];
        for (int i3 = 0; i3 < 256; i3++) {
            this.e[i3] = new int[4];
            int[] iArr = this.e[i3];
            int i4 = (i3 << 12) / 256;
            iArr[2] = i4;
            iArr[1] = i4;
            iArr[0] = i4;
            this.h[i3] = 256;
            this.g[i3] = 0;
        }
    }

    public final byte[] a() {
        int i;
        byte[] bArr = new byte[768];
        int[] iArr = new int[256];
        for (i = 0; i < 256; i++) {
            iArr[this.e[i][3]] = i;
        }
        int i2 = 0;
        for (i = 0; i < 256; i++) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            bArr[i2] = (byte) this.e[i3][0];
            int i5 = i4 + 1;
            bArr[i4] = (byte) this.e[i3][1];
            i2 = i5 + 1;
            bArr[i5] = (byte) this.e[i3][2];
        }
        return bArr;
    }

    public final void b() {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < 256) {
            int[] iArr = this.e[i3];
            int i4 = iArr[1];
            int i5 = i3;
            for (int i6 = i3 + 1; i6 < 256; i6++) {
                int[] iArr2 = this.e[i6];
                if (iArr2[1] < i4) {
                    i4 = iArr2[1];
                    i5 = i6;
                }
            }
            int[] iArr3 = this.e[i5];
            if (i3 != i5) {
                i5 = iArr3[0];
                iArr3[0] = iArr[0];
                iArr[0] = i5;
                i5 = iArr3[1];
                iArr3[1] = iArr[1];
                iArr[1] = i5;
                i5 = iArr3[2];
                iArr3[2] = iArr[2];
                iArr[2] = i5;
                i5 = iArr3[3];
                iArr3[3] = iArr[3];
                iArr[3] = i5;
            }
            if (i4 != i2) {
                this.f[i2] = (i + i3) >> 1;
                for (i5 = i2 + 1; i5 < i4; i5++) {
                    this.f[i5] = i3;
                }
                i5 = i4;
                i4 = i3;
            } else {
                i4 = i;
                i5 = i2;
            }
            i3++;
            i = i4;
            i2 = i5;
        }
        this.f[i2] = (i + 255) >> 1;
        for (i4 = i2 + 1; i4 < 256; i4++) {
            this.f[i4] = 255;
        }
    }

    public final void c() {
        int i;
        if (this.c < 1509) {
            this.d = 1;
        }
        this.a = ((this.d - 1) / 3) + 30;
        byte[] bArr = this.b;
        int i2 = this.c;
        int i3 = this.c / (this.d * 3);
        int i4 = i3 / 100;
        for (i = 0; i < 32; i++) {
            this.i[i] = (((1024 - (i * i)) * 256) / 1024) * 1024;
        }
        if (this.c < 1509) {
            i = XZBDevice.DOWNLOAD_LIST_FAILED;
        } else if (this.c % 499 != 0) {
            i = 1497;
        } else if (this.c % 491 != 0) {
            i = 1473;
        } else if (this.c % 487 != 0) {
            i = 1461;
        } else {
            i = 1509;
        }
        int i5 = 0;
        int i6 = i4;
        int i7 = 1024;
        int i8 = 32;
        int i9 = 2048;
        int i10 = 0;
        while (i10 < i3) {
            int i11 = (bArr[i5 + 0] & 255) << 4;
            int i12 = (bArr[i5 + 1] & 255) << 4;
            int i13 = (bArr[i5 + 2] & 255) << 4;
            Object obj = InMobiClientPositioning.NO_REPEAT;
            Object obj2 = InMobiClientPositioning.NO_REPEAT;
            int i14 = -1;
            int i15 = -1;
            int i16 = 0;
            while (i16 < 256) {
                int i17;
                int[] iArr = this.e[i16];
                i4 = iArr[0] - i11;
                if (i4 < 0) {
                    i4 = -i4;
                }
                int i18 = iArr[1] - i12;
                if (i18 < 0) {
                    i18 = -i18;
                }
                i18 += i4;
                i4 = iArr[2] - i13;
                if (i4 < 0) {
                    i4 = -i4;
                }
                i18 += i4;
                if (i18 < i17) {
                    i4 = i18;
                    i17 = i16;
                } else {
                    i4 = i17;
                    i17 = i14;
                }
                i18 -= this.g[i16] >> 12;
                if (i18 < r7) {
                    i14 = i16;
                } else {
                    i18 = r7;
                    i14 = i15;
                }
                int i19 = this.h[i16] >> 10;
                int[] iArr2 = this.h;
                iArr2[i16] = iArr2[i16] - i19;
                iArr2 = this.g;
                iArr2[i16] = (i19 << 10) + iArr2[i16];
                i16++;
                i19 = i18;
                i15 = i14;
                i14 = i17;
                i17 = i4;
            }
            int[] iArr3 = this.h;
            iArr3[i14] = iArr3[i14] + 64;
            iArr3 = this.g;
            iArr3[i14] = iArr3[i14] - 65536;
            iArr3 = this.e[i15];
            iArr3[0] = iArr3[0] - (((iArr3[0] - i11) * i7) / 1024);
            iArr3[1] = iArr3[1] - (((iArr3[1] - i12) * i7) / 1024);
            iArr3[2] = iArr3[2] - (((iArr3[2] - i13) * i7) / 1024);
            if (i8 != 0) {
                i4 = i15 - i8;
                if (i4 < -1) {
                    i19 = -1;
                } else {
                    i19 = i4;
                }
                i4 = i15 + i8;
                if (i4 > 256) {
                    i4 = AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
                }
                i18 = 1;
                i14 = i15 - 1;
                i16 = i15 + 1;
                while (true) {
                    if (i16 >= i4 && i14 <= i19) {
                        break;
                    }
                    i17 = i18 + 1;
                    i15 = this.i[i18];
                    if (i16 < i4) {
                        i18 = i16 + 1;
                        int[] iArr4 = this.e[i16];
                        try {
                            iArr4[0] = iArr4[0] - (((iArr4[0] - i11) * i15) / 262144);
                            iArr4[1] = iArr4[1] - (((iArr4[1] - i12) * i15) / 262144);
                            iArr4[2] = iArr4[2] - (((iArr4[2] - i13) * i15) / 262144);
                        } catch (Exception e) {
                        }
                    } else {
                        i18 = i16;
                    }
                    if (i14 > i19) {
                        i16 = i14 - 1;
                        int[] iArr5 = this.e[i14];
                        try {
                            iArr5[0] = iArr5[0] - (((iArr5[0] - i11) * i15) / 262144);
                            iArr5[1] = iArr5[1] - (((iArr5[1] - i12) * i15) / 262144);
                            iArr5[2] = iArr5[2] - ((i15 * (iArr5[2] - i13)) / 262144);
                            i14 = i16;
                            i16 = i18;
                            i18 = i17;
                        } catch (Exception e2) {
                            i14 = i16;
                            i16 = i18;
                            i18 = i17;
                        }
                    } else {
                        i16 = i18;
                        i18 = i17;
                    }
                }
            }
            i4 = i5 + i;
            if (i4 >= i2) {
                i16 = i4 - this.c;
            } else {
                i16 = i4;
            }
            i15 = i10 + 1;
            if (i6 == 0) {
                i4 = 1;
            } else {
                i4 = i6;
            }
            if (i15 % i4 == 0) {
                i14 = i7 - (i7 / this.a);
                i19 = i9 - (i9 / 30);
                i18 = i19 >> 6;
                if (i18 <= 1) {
                    i18 = 0;
                }
                for (i17 = 0; i17 < i18; i17++) {
                    this.i[i17] = ((((i18 * i18) - (i17 * i17)) * 256) / (i18 * i18)) * i14;
                }
                i5 = i16;
                i6 = i4;
                i7 = i14;
                i8 = i18;
                i9 = i19;
                i10 = i15;
            } else {
                i5 = i16;
                i6 = i4;
                i10 = i15;
            }
        }
    }

    public final int a(int i, int i2, int i3) {
        int i4 = this.f[i2];
        int i5 = -1;
        int i6 = 1000;
        int i7 = i4 - 1;
        int i8 = i4;
        while (true) {
            if (i8 >= 256 && i7 < 0) {
                return i5;
            }
            int[] iArr;
            int i9;
            if (i8 < 256) {
                iArr = this.e[i8];
                i9 = iArr[1] - i2;
                if (i9 >= i6) {
                    i8 = i6;
                    i4 = 256;
                    i6 = i5;
                } else {
                    i4 = i8 + 1;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    i8 = iArr[0] - i;
                    if (i8 < 0) {
                        i8 = -i8;
                    }
                    i8 += i9;
                    if (i8 < i6) {
                        i9 = iArr[2] - i3;
                        if (i9 < 0) {
                            i9 = -i9;
                        }
                        i8 += i9;
                        if (i8 < i6) {
                            i6 = iArr[3];
                        }
                    }
                    i8 = i6;
                    i6 = i5;
                }
            } else {
                i4 = i8;
                i8 = i6;
                i6 = i5;
            }
            if (i7 >= 0) {
                iArr = this.e[i7];
                i9 = i2 - iArr[1];
                if (i9 >= i8) {
                    i5 = i6;
                    i7 = -1;
                    i6 = i8;
                    i8 = i4;
                } else {
                    i7--;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    i5 = iArr[0] - i;
                    if (i5 < 0) {
                        i5 = -i5;
                    }
                    i5 += i9;
                    if (i5 < i8) {
                        i9 = iArr[2] - i3;
                        if (i9 < 0) {
                            i9 = -i9;
                        }
                        i9 += i5;
                        if (i9 < i8) {
                            i5 = iArr[3];
                            i8 = i4;
                            i6 = i9;
                        }
                    }
                }
            }
            i5 = i6;
            i6 = i8;
            i8 = i4;
        }
    }

    public final void d() {
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.e[i];
            iArr[0] = iArr[0] >> 4;
            iArr = this.e[i];
            iArr[1] = iArr[1] >> 4;
            iArr = this.e[i];
            iArr[2] = iArr[2] >> 4;
            this.e[i][3] = i;
        }
    }
}
