package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.n;
import com.google.zxing.o;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: ITFReader.java
public final class m extends q {
    static final int[][] a;
    private static final int[] b;
    private static final int[] d;
    private static final int[] e;
    private int c;

    public m() {
        this.c = -1;
    }

    static {
        b = new int[]{6, 8, 10, 12, 14};
        d = new int[]{1, 1, 1, 1};
        e = new int[]{1, 1, 3};
        a = new int[][]{new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws e, j {
        int[] iArr;
        int i2;
        int[] c = c(aVar, a(aVar), d);
        this.c = (c[1] - c[0]) / 4;
        a(aVar, c[0]);
        int[] b = b(aVar);
        StringBuilder stringBuilder = new StringBuilder(20);
        a(aVar, c[1], b[0], stringBuilder);
        String toString = stringBuilder.toString();
        if (map != null) {
            iArr = (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS);
        } else {
            iArr = null;
        }
        if (iArr == null) {
            iArr = b;
        }
        int length = toString.length();
        int length2 = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length2) {
            int i5 = iArr[i3];
            if (length == i5) {
                i2 = 1;
                break;
            }
            if (i5 <= i4) {
                i5 = i4;
            }
            i3++;
            i4 = i5;
        }
        i2 = 0;
        if (i2 == 0 && length > i4) {
            i2 = 1;
        }
        if (i2 == 0) {
            throw e.a();
        }
        return new n(toString, null, new o[]{new o((float) c[1], (float) i), new o((float) b[0], (float) i)}, BarcodeFormat.ITF);
    }

    private static void a(a aVar, int i, int i2, StringBuilder stringBuilder) throws j {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        int i3 = i;
        while (i3 < i2) {
            int i4;
            a(aVar, i3, iArr);
            for (i4 = 0; i4 < 5; i4++) {
                int i5 = i4 * 2;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            stringBuilder.append((char) (a(iArr2) + 48));
            stringBuilder.append((char) (a(iArr3) + 48));
            i4 = i3;
            for (i3 = 0; i3 < 10; i3++) {
                i4 += iArr[i3];
            }
            i3 = i4;
        }
    }

    private void a(a aVar, int i) throws j {
        int i2 = this.c * 10;
        if (i2 >= i) {
            i2 = i;
        }
        int i3 = i - 1;
        while (i2 > 0 && i3 >= 0 && !aVar.a(i3)) {
            i2--;
            i3--;
        }
        if (i2 != 0) {
            throw j.a();
        }
    }

    private int[] b(a aVar) throws j {
        aVar.c();
        int[] c = c(aVar, a(aVar), e);
        a(aVar, c[0]);
        int i = c[0];
        c[0] = aVar.b - c[1];
        c[1] = aVar.b - i;
        aVar.c();
        return c;
    }

    private static int[] c(a aVar, int i, int[] iArr) throws j {
        int length = iArr.length;
        Object obj = new Object[length];
        int i2 = aVar.b;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (i < i2) {
            if ((aVar.a(i) ^ i5) != 0) {
                obj[i4] = obj[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (a(obj, iArr, 0.78f) < 0.38f) {
                    return new int[]{i3, i};
                } else {
                    i3 += obj[0] + obj[1];
                    System.arraycopy(obj, XZBDevice.DOWNLOAD_LIST_RECYCLE, obj, 0, length - 2);
                    obj[length - 2] = null;
                    obj[length - 1] = null;
                    i4--;
                }
                obj[i4] = 1;
                if (i5 == 0) {
                    i5 = 1;
                } else {
                    i5 = 0;
                }
            }
            i++;
        }
        throw j.a();
    }

    private static int a(int[] iArr) throws j {
        float f = 0.38f;
        int i = -1;
        int length = a.length;
        int i2 = 0;
        while (i2 < length) {
            float a = a(iArr, a[i2], 0.78f);
            if (a < f) {
                i = i2;
            } else {
                a = f;
            }
            i2++;
            f = a;
        }
        if (i >= 0) {
            return i;
        }
        throw j.a();
    }

    private static int a(a aVar) throws j {
        int i = aVar.b;
        int c = aVar.c(0);
        if (c != i) {
            return c;
        }
        throw j.a();
    }
}
