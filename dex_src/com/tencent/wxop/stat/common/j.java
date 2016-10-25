package com.tencent.wxop.stat.common;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class j extends h {
    static final /* synthetic */ boolean g;
    private static final byte[] h;
    private static final byte[] i;
    int c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    private final byte[] j;
    private int k;
    private final byte[] l;

    static {
        g = !g.class.desiredAssertionStatus();
        h = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        i = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    }

    public j(int i, byte[] bArr) {
        boolean z = true;
        this.a = bArr;
        this.d = (i & 1) == 0;
        this.e = (i & 2) == 0;
        if ((i & 4) == 0) {
            z = false;
        }
        this.f = z;
        this.l = (i & 8) == 0 ? h : i;
        this.j = new byte[2];
        this.c = 0;
        this.k = this.e ? R.styleable.Toolbar_collapseContentDescription : -1;
    }

    public boolean a(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        byte[] bArr2 = this.l;
        byte[] bArr3 = this.a;
        int i7 = 0;
        int i8 = this.k;
        int i9 = i2 + i;
        switch (this.c) {
            case SpdyAgent.ACCS_TEST_SERVER:
                i3 = -1;
                i4 = i;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (i + 2 <= i9) {
                    i4 = i + 1;
                    i = i4 + 1;
                    i5 = (((this.j[0] & 255) << 16) | ((bArr[i] & 255) << 8)) | (bArr[i4] & 255);
                    this.c = 0;
                    i3 = i5;
                    i4 = i;
                }
                i3 = -1;
                i4 = i;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (i + 1 <= i9) {
                    i4 = i + 1;
                    i5 = (((this.j[0] & 255) << 16) | ((this.j[1] & 255) << 8)) | (bArr[i] & 255);
                    this.c = 0;
                    i3 = i5;
                }
                i3 = -1;
                i4 = i;
                break;
            default:
                i3 = -1;
                i4 = i;
                break;
        }
        if (i3 != -1) {
            bArr3[0] = bArr2[(i3 >> 18) & 63];
            bArr3[1] = bArr2[(i3 >> 12) & 63];
            bArr3[2] = bArr2[(i3 >> 6) & 63];
            i5 = XZBDevice.DOWNLOAD_LIST_ALL;
            bArr3[3] = bArr2[i3 & 63];
            i8--;
            if (i8 == 0) {
                if (this.f) {
                    i5 = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                    bArr3[4] = (byte) 13;
                }
                i7 = i5 + 1;
                bArr3[i5] = (byte) 10;
                i6 = 19;
            } else {
                i6 = i8;
                i7 = 4;
            }
        } else {
            i6 = i8;
        }
        while (i4 + 3 <= i9) {
            i5 = (((bArr[i4] & 255) << 16) | ((bArr[i4 + 1] & 255) << 8)) | (bArr[i4 + 2] & 255);
            bArr3[i7] = bArr2[(i5 >> 18) & 63];
            bArr3[i7 + 1] = bArr2[(i5 >> 12) & 63];
            bArr3[i7 + 2] = bArr2[(i5 >> 6) & 63];
            bArr3[i7 + 3] = bArr2[i5 & 63];
            i8 = i4 + 3;
            i4 = i7 + 4;
            i5 = i6 - 1;
            if (i5 == 0) {
                if (this.f) {
                    i5 = i4 + 1;
                    bArr3[i4] = (byte) 13;
                } else {
                    i5 = i4;
                }
                i7 = i5 + 1;
                bArr3[i5] = (byte) 10;
                i4 = i8;
                i6 = 19;
            } else {
                i6 = i5;
                i7 = i4;
                i4 = i8;
            }
        }
        if (z) {
            if (i4 - this.c == i9 - 1) {
                if (this.c > 0) {
                    i8 = 1;
                    i5 = this.j[0];
                } else {
                    i8 = i4 + 1;
                    i5 = bArr[i4];
                    i4 = i8;
                    i8 = 0;
                }
                i3 = (i5 & 255) << 4;
                this.c -= i8;
                i8 = i7 + 1;
                bArr3[i7] = bArr2[(i3 >> 6) & 63];
                i5 = i8 + 1;
                bArr3[i8] = bArr2[i3 & 63];
                if (this.d) {
                    i8 = i5 + 1;
                    bArr3[i5] = (byte) 61;
                    i5 = i8 + 1;
                    bArr3[i8] = (byte) 61;
                }
                if (this.e) {
                    if (this.f) {
                        i8 = i5 + 1;
                        bArr3[i5] = (byte) 13;
                        i5 = i8;
                    }
                    i8 = i5 + 1;
                    bArr3[i5] = (byte) 10;
                    i5 = i8;
                }
                i7 = i5;
            } else if (i4 - this.c == i9 - 2) {
                if (this.c > 1) {
                    i8 = 1;
                    i5 = this.j[0];
                } else {
                    i8 = i4 + 1;
                    i5 = bArr[i4];
                    i4 = i8;
                    i8 = 0;
                }
                int i10 = (i5 & 255) << 10;
                if (this.c > 0) {
                    i3 = i8 + 1;
                    i5 = this.j[i8];
                    i8 = i3;
                } else {
                    i3 = i4 + 1;
                    i5 = bArr[i4];
                    i4 = i3;
                }
                i5 = ((i5 & 255) << 2) | i10;
                this.c -= i8;
                i8 = i7 + 1;
                bArr3[i7] = bArr2[(i5 >> 12) & 63];
                i3 = i8 + 1;
                bArr3[i8] = bArr2[(i5 >> 6) & 63];
                i8 = i3 + 1;
                bArr3[i3] = bArr2[i5 & 63];
                if (this.d) {
                    i5 = i8 + 1;
                    bArr3[i8] = (byte) 61;
                } else {
                    i5 = i8;
                }
                if (this.e) {
                    if (this.f) {
                        i8 = i5 + 1;
                        bArr3[i5] = (byte) 13;
                        i5 = i8;
                    }
                    i8 = i5 + 1;
                    bArr3[i5] = (byte) 10;
                    i5 = i8;
                }
                i7 = i5;
            } else if (this.e && i7 > 0 && i6 != 19) {
                if (this.f) {
                    i5 = i7 + 1;
                    bArr3[i7] = (byte) 13;
                } else {
                    i5 = i7;
                }
                i7 = i5 + 1;
                bArr3[i5] = (byte) 10;
            }
            if (!g && this.c != 0) {
                throw new AssertionError();
            } else if (!(g || r1 == i9)) {
                throw new AssertionError();
            }
        } else if (i4 == i9 - 1) {
            r0 = this.j;
            i8 = this.c;
            this.c = i8 + 1;
            r0[i8] = bArr[i4];
        } else if (i4 == i9 - 2) {
            r0 = this.j;
            i8 = this.c;
            this.c = i8 + 1;
            r0[i8] = bArr[i4];
            r0 = this.j;
            i8 = this.c;
            this.c = i8 + 1;
            r0[i8] = bArr[i4 + 1];
        }
        this.b = i7;
        this.k = i6;
        return true;
    }
}
