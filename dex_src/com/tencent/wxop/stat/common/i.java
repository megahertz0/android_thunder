package com.tencent.wxop.stat.common;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class i extends h {
    private static final int[] c;
    private static final int[] d;
    private int e;
    private int f;
    private final int[] g;

    static {
        c = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        d = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    }

    public i(int i, byte[] bArr) {
        this.a = bArr;
        this.g = (i & 8) == 0 ? c : d;
        this.e = 0;
        this.f = 0;
    }

    public boolean a(byte[] bArr, int i, int i2, boolean z) {
        if (this.e == 6) {
            return false;
        }
        int i3 = i2 + i;
        int i4 = this.e;
        int i5 = this.f;
        int i6 = 0;
        byte[] bArr2 = this.a;
        int[] iArr = this.g;
        int i7 = i4;
        i4 = i;
        while (i4 < i3) {
            if (i7 == 0) {
                while (i4 + 4 <= i3) {
                    i5 = (((iArr[bArr[i4] & 255] << 18) | (iArr[bArr[i4 + 1] & 255] << 12)) | (iArr[bArr[i4 + 2] & 255] << 6)) | iArr[bArr[i4 + 3] & 255];
                    if (i5 >= 0) {
                        bArr2[i6 + 2] = (byte) i5;
                        bArr2[i6 + 1] = (byte) (i5 >> 8);
                        bArr2[i6] = (byte) (i5 >> 16);
                        i6 += 3;
                        i4 += 4;
                    } else if (i4 >= i3) {
                        i4 = i5;
                        if (z) {
                            switch (i7) {
                                case SpdyAgent.ACCS_ONLINE_SERVER:
                                    this.e = 6;
                                    return false;
                                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                    i5 = i6 + 1;
                                    bArr2[i6] = (byte) (i4 >> 4);
                                    i6 = i5;
                                    this.e = i7;
                                    this.b = i6;
                                    return true;
                                case XZBDevice.DOWNLOAD_LIST_FAILED:
                                    i5 = i6 + 1;
                                    bArr2[i6] = (byte) (i4 >> 10);
                                    i6 = i5 + 1;
                                    bArr2[i5] = (byte) (i4 >> 2);
                                    this.e = i7;
                                    this.b = i6;
                                    return true;
                                case XZBDevice.DOWNLOAD_LIST_ALL:
                                    this.e = 6;
                                    return false;
                                default:
                                    this.e = i7;
                                    this.b = i6;
                                    return true;
                            }
                        }
                        this.e = i7;
                        this.f = i4;
                        this.b = i6;
                        return true;
                    }
                }
                if (i4 >= i3) {
                    i4 = i5;
                    if (z) {
                        switch (i7) {
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                this.e = 6;
                                return false;
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                i5 = i6 + 1;
                                bArr2[i6] = (byte) (i4 >> 4);
                                i6 = i5;
                                this.e = i7;
                                this.b = i6;
                                return true;
                            case XZBDevice.DOWNLOAD_LIST_FAILED:
                                i5 = i6 + 1;
                                bArr2[i6] = (byte) (i4 >> 10);
                                i6 = i5 + 1;
                                bArr2[i5] = (byte) (i4 >> 2);
                                this.e = i7;
                                this.b = i6;
                                return true;
                            case XZBDevice.DOWNLOAD_LIST_ALL:
                                this.e = 6;
                                return false;
                            default:
                                this.e = i7;
                                this.b = i6;
                                return true;
                        }
                    }
                    this.e = i7;
                    this.f = i4;
                    this.b = i6;
                    return true;
                }
            }
            i = i4 + 1;
            i4 = iArr[bArr[i4] & 255];
            switch (i7) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (i4 >= 0) {
                        i7++;
                        i5 = i4;
                        i4 = i;
                    } else {
                        if (i4 != -1) {
                            this.e = 6;
                            return false;
                        }
                        i4 = i;
                    }
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (i4 >= 0) {
                        i5 = (i5 << 6) | i4;
                        i7++;
                        i4 = i;
                    } else {
                        if (i4 != -1) {
                            this.e = 6;
                            return false;
                        }
                        i4 = i;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (i4 >= 0) {
                        i5 = (i5 << 6) | i4;
                        i7++;
                        i4 = i;
                    } else if (i4 == -2) {
                        i4 = i6 + 1;
                        bArr2[i6] = (byte) (i5 >> 4);
                        i7 = 4;
                        i6 = i4;
                        i4 = i;
                    } else {
                        if (i4 != -1) {
                            this.e = 6;
                            return false;
                        }
                        i4 = i;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (i4 >= 0) {
                        i5 = (i5 << 6) | i4;
                        bArr2[i6 + 2] = (byte) i5;
                        bArr2[i6 + 1] = (byte) (i5 >> 8);
                        bArr2[i6] = (byte) (i5 >> 16);
                        i6 += 3;
                        i7 = 0;
                        i4 = i;
                    } else if (i4 == -2) {
                        bArr2[i6 + 1] = (byte) (i5 >> 2);
                        bArr2[i6] = (byte) (i5 >> 10);
                        i6 += 2;
                        i7 = 5;
                        i4 = i;
                    } else {
                        if (i4 != -1) {
                            this.e = 6;
                            return false;
                        }
                        i4 = i;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    if (i4 == -2) {
                        i7++;
                        i4 = i;
                    } else {
                        if (i4 != -1) {
                            this.e = 6;
                            return false;
                        }
                        i4 = i;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (i4 != -1) {
                        this.e = 6;
                        return false;
                    }
                    i4 = i;
                    break;
                default:
                    i4 = i;
                    break;
            }
        }
        i4 = i5;
        if (z) {
            this.e = i7;
            this.f = i4;
            this.b = i6;
            return true;
        }
        switch (i7) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.e = 6;
                return false;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i5 = i6 + 1;
                bArr2[i6] = (byte) (i4 >> 4);
                i6 = i5;
                this.e = i7;
                this.b = i6;
                return true;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                i5 = i6 + 1;
                bArr2[i6] = (byte) (i4 >> 10);
                i6 = i5 + 1;
                bArr2[i5] = (byte) (i4 >> 2);
                this.e = i7;
                this.b = i6;
                return true;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.e = 6;
                return false;
            default:
                this.e = i7;
                this.b = i6;
                return true;
        }
    }
}
