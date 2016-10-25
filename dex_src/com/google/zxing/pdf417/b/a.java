package com.google.zxing.pdf417.b;

import com.google.zxing.b;
import com.google.zxing.j;
import com.google.zxing.o;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// compiled from: Detector.java
public final class a {
    private static final int[] a;
    private static final int[] b;
    private static final int[] c;
    private static final int[] d;

    static {
        a = new int[]{0, 4, 1, 5};
        b = new int[]{6, 2, 7, 3};
        c = new int[]{8, 1, 1, 1, 1, 1, 1, 3};
        d = new int[]{7, 1, 1, 3, 1, 1, 1, 2, 1};
    }

    public static b a(b bVar) throws j {
        com.google.zxing.common.b a = bVar.a();
        List a2 = a(a);
        if (a2.isEmpty()) {
            com.google.zxing.common.b d = a.d();
            int i = d.a;
            int i2 = d.b;
            com.google.zxing.common.a aVar = new com.google.zxing.common.a(i);
            com.google.zxing.common.a aVar2 = new com.google.zxing.common.a(i);
            for (i = 0; i < (i2 + 1) / 2; i++) {
                aVar = d.a(i, aVar);
                aVar2 = d.a((i2 - 1) - i, aVar2);
                aVar.c();
                aVar2.c();
                d.b(i, aVar2);
                d.b((i2 - 1) - i, aVar);
            }
            a2 = a(d);
            a = d;
        }
        return new b(a, a2);
    }

    private static List<o[]> a(com.google.zxing.common.b bVar) {
        List<o[]> arrayList = new ArrayList();
        if (bVar.b > 0) {
            int i;
            int i2;
            int i3 = bVar.b;
            int i4 = bVar.a;
            Object obj = new Object[8];
            a(obj, a(bVar, i3, i4, 0, 0, c), a);
            if (obj[4] != null) {
                i = (int) obj[4].a;
                i2 = (int) obj[4].b;
            } else {
                i = 0;
                i2 = 0;
            }
            a(obj, a(bVar, i3, i4, i2, i, d), b);
            if (!(obj[0] == null && obj[3] == null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private static void a(o[] oVarArr, o[] oVarArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            oVarArr[iArr[i]] = oVarArr2[i];
        }
    }

    private static o[] a(com.google.zxing.common.b bVar, int i, int i2, int i3, int i4, int[] iArr) {
        int i5;
        Object obj;
        int i6;
        int i7;
        o[] oVarArr = new o[4];
        int[] iArr2 = new int[iArr.length];
        int i8 = i3;
        while (i8 < i) {
            int[] a = a(bVar, i4, i8, i2, iArr, iArr2);
            int[] iArr3;
            int[] a2;
            if (a != null) {
                iArr3 = a;
                i5 = i8;
                while (i5 > 0) {
                    i8 = i5 - 1;
                    a = a(bVar, i4, i8, i2, iArr, iArr2);
                    if (a == null) {
                        i5 = i8 + 1;
                        break;
                    }
                    iArr3 = a;
                    i5 = i8;
                }
                oVarArr[0] = new o((float) iArr3[0], (float) i5);
                oVarArr[1] = new o((float) iArr3[1], (float) i5);
                obj = 1;
                i6 = i5;
                i5 = i6 + 1;
                if (obj != null) {
                    iArr3 = new int[]{(int) oVarArr[0].a, (int) oVarArr[1].a};
                    i8 = i5;
                    i7 = 0;
                    while (i8 < i) {
                        a2 = a(bVar, iArr3[0], i8, i2, iArr, iArr2);
                        if (a2 != null || Math.abs(iArr3[0] - a2[0]) >= 5 || Math.abs(iArr3[1] - a2[1]) >= 5) {
                            if (i7 <= 25) {
                                break;
                            }
                            i5 = i7 + 1;
                            a2 = iArr3;
                        } else {
                            i5 = 0;
                        }
                        i8++;
                        i7 = i5;
                        iArr3 = a2;
                    }
                    i5 = i8 - (i7 + 1);
                    oVarArr[2] = new o((float) iArr3[0], (float) i5);
                    oVarArr[3] = new o((float) iArr3[1], (float) i5);
                }
                if (i5 - i6 < 10) {
                    for (i5 = 0; i5 < 4; i5++) {
                        oVarArr[i5] = null;
                    }
                }
                return oVarArr;
            }
            i8 += 5;
        }
        obj = null;
        i6 = i8;
        i5 = i6 + 1;
        if (obj != null) {
            iArr3 = new int[]{(int) oVarArr[0].a, (int) oVarArr[1].a};
            i8 = i5;
            i7 = 0;
            while (i8 < i) {
                a2 = a(bVar, iArr3[0], i8, i2, iArr, iArr2);
                if (a2 != null) {
                }
                if (i7 <= 25) {
                    break;
                    i5 = i8 - (i7 + 1);
                    oVarArr[2] = new o((float) iArr3[0], (float) i5);
                    oVarArr[3] = new o((float) iArr3[1], (float) i5);
                } else {
                    i5 = i7 + 1;
                    a2 = iArr3;
                    i8++;
                    i7 = i5;
                    iArr3 = a2;
                }
            }
            i5 = i8 - (i7 + 1);
            oVarArr[2] = new o((float) iArr3[0], (float) i5);
            oVarArr[3] = new o((float) iArr3[1], (float) i5);
        }
        if (i5 - i6 < 10) {
            for (i5 = 0; i5 < 4; i5++) {
                oVarArr[i5] = null;
            }
        }
        return oVarArr;
    }

    private static int[] a(com.google.zxing.common.b bVar, int i, int i2, int i3, int[] iArr, int[] iArr2) {
        int i4;
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int length = iArr.length;
        int i5 = 0;
        while (bVar.a(i, i2) && i > 0) {
            i4 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i--;
            i5 = i4;
        }
        i5 = 0;
        int i6 = 0;
        i4 = i;
        while (i < i3) {
            if ((bVar.a(i, i2) ^ i6) != 0) {
                iArr2[i5] = iArr2[i5] + 1;
            } else {
                if (i5 != length - 1) {
                    i5++;
                } else if (a(iArr2, iArr) < 0.42f) {
                    return new int[]{i4, i};
                } else {
                    i4 += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, XZBDevice.DOWNLOAD_LIST_RECYCLE, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i5--;
                }
                iArr2[i5] = 1;
                if (i6 == 0) {
                    i6 = 1;
                } else {
                    i6 = 0;
                }
            }
            i++;
        }
        if (i5 != length - 1 || a(iArr2, iArr) >= 0.42f) {
            return null;
        }
        return new int[]{i4, i - 1};
    }

    private static float a(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = iArr[i] + i2;
            i++;
            i3 = iArr2[i] + i3;
            i2 = i4;
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f = ((float) i2) / ((float) i3);
        float f2 = 0.8f * f;
        float f3 = 0.0f;
        for (i = 0; i < length; i++) {
            int i5 = iArr[i];
            float f4 = ((float) iArr2[i]) * f;
            float f5 = ((float) i5) > f4 ? ((float) i5) - f4 : f4 - ((float) i5);
            if (f5 > f2) {
                return Float.POSITIVE_INFINITY;
            }
            f3 += f5;
        }
        return f3 / ((float) i2);
    }
}
