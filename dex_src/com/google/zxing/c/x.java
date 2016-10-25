package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.c;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.m;
import com.google.zxing.n;
import com.google.zxing.o;
import com.google.zxing.p;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;
import java.util.Map;

// compiled from: UPCEANReader.java
public abstract class x extends q {
    static final int[] b;
    static final int[] c;
    static final int[][] d;
    static final int[][] e;
    private final StringBuilder a;
    private final w f;
    private final l g;

    protected abstract int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws j;

    abstract BarcodeFormat b();

    static {
        b = new int[]{1, 1, 1};
        c = new int[]{1, 1, 1, 1, 1};
        d = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        e = new int[20][];
        System.arraycopy(d, 0, e, 0, XZBDevice.Stop);
        for (int i = 10; i < 20; i++) {
            int[] iArr = d[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            e[i] = iArr2;
        }
    }

    protected x() {
        this.a = new StringBuilder(20);
        this.f = new w();
        this.g = new l();
    }

    static int[] a(a aVar) throws j {
        int[] iArr = new int[b.length];
        int i = 0;
        int[] iArr2 = null;
        boolean z = false;
        while (!z) {
            Arrays.fill(iArr, 0, b.length, 0);
            iArr2 = a(aVar, i, false, b, iArr);
            int i2 = iArr2[0];
            i = iArr2[1];
            int i3 = i2 - (i - i2);
            if (i3 >= 0) {
                z = aVar.a(i3, i2);
            }
        }
        return iArr2;
    }

    public n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j, c, e {
        return a(i, aVar, a(aVar), (Map) map);
    }

    public n a(int i, a aVar, int[] iArr, Map<DecodeHintType, ?> map) throws j, c, e {
        p pVar;
        if (map == null) {
            pVar = null;
        } else {
            pVar = (p) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        if (pVar != null) {
            o oVar = new o(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i);
        }
        StringBuilder stringBuilder = this.a;
        stringBuilder.setLength(0);
        int a = a(aVar, iArr, stringBuilder);
        if (pVar != null) {
            o oVar2 = new o((float) a, (float) i);
        }
        int[] a2 = a(aVar, a);
        if (pVar != null) {
            o oVar3 = new o(((float) (a2[0] + a2[1])) / 2.0f, (float) i);
        }
        int i2 = a2[1];
        int i3 = (i2 - a2[0]) + i2;
        if (i3 >= aVar.b || !aVar.a(i2, i3)) {
            throw j.a();
        }
        String toString = stringBuilder.toString();
        if (toString.length() < 8) {
            throw e.a();
        } else if (a(toString)) {
            Object obj;
            int i4;
            float f = ((float) (iArr[1] + iArr[0])) / 2.0f;
            float f2 = ((float) (a2[1] + a2[0])) / 2.0f;
            BarcodeFormat b = b();
            n nVar = new n(toString, null, new o[]{new o(f, (float) i), new o(f2, (float) i)}, b);
            Object obj2;
            try {
                n a3 = this.f.a(i, aVar, a2[1]);
                nVar.a(ResultMetadataType.UPC_EAN_EXTENSION, a3.a);
                nVar.a(a3.e);
                Object obj3 = a3.c;
                obj2 = nVar.c;
                if (obj2 == null) {
                    nVar.c = obj3;
                } else if (obj3 != null) {
                    if (obj3.length > 0) {
                        Object obj4 = new Object[(obj2.length + obj3.length)];
                        System.arraycopy(obj2, 0, obj4, 0, obj2.length);
                        System.arraycopy(obj3, 0, obj4, obj2.length, obj3.length);
                        nVar.c = obj4;
                    }
                }
                i3 = a3.a.length();
            } catch (m e) {
                obj2 = null;
            }
            if (map == null) {
                Object[] objArr = null;
            } else {
                a2 = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            if (a2 != null) {
                obj = null;
                for (int i5 : a2) {
                    if (i3 == i5) {
                        obj = 1;
                        break;
                    }
                }
                if (obj == null) {
                    throw j.a();
                }
            }
            if (b == BarcodeFormat.EAN_13 || b == BarcodeFormat.UPC_A) {
                l lVar = this.g;
                lVar.a();
                int parseInt = Integer.parseInt(toString.substring(0, XZBDevice.DOWNLOAD_LIST_FAILED));
                int size = lVar.a.size();
                for (i4 = 0; i4 < size; i4++) {
                    int[] iArr2 = (int[]) lVar.a.get(i4);
                    a = iArr2[0];
                    if (parseInt < a) {
                        break;
                    }
                    if (iArr2.length == 1) {
                        i2 = a;
                    } else {
                        i2 = iArr2[1];
                    }
                    if (parseInt <= i2) {
                        String str = (String) lVar.b.get(i4);
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    nVar.a(ResultMetadataType.POSSIBLE_COUNTRY, obj);
                }
            }
            return nVar;
        } else {
            throw c.a();
        }
    }

    boolean a(String str) throws e {
        return a((CharSequence) str);
    }

    static boolean a(CharSequence charSequence) throws e {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = length - 2;
        int i2 = 0;
        while (i >= 0) {
            int charAt = charSequence.charAt(i) - 48;
            if (charAt >= 0 && charAt <= 9) {
                i2 += charAt;
                i -= 2;
            }
            throw e.a();
        }
        i2 *= 3;
        i = length - 1;
        while (i >= 0) {
            length = charSequence.charAt(i) - 48;
            if (length >= 0 && length <= 9) {
                i2 += length;
                i -= 2;
            }
            throw e.a();
        }
        return i2 % 10 == 0;
    }

    int[] a(a aVar, int i) throws j {
        return a(aVar, i, false, b);
    }

    static int[] a(a aVar, int i, boolean z, int[] iArr) throws j {
        return a(aVar, i, z, iArr, new int[iArr.length]);
    }

    private static int[] a(a aVar, int i, boolean z, int[] iArr, int[] iArr2) throws j {
        int length = iArr.length;
        int i2 = aVar.b;
        int d = z ? aVar.d(i) : aVar.c(i);
        boolean z2 = z;
        int i3 = 0;
        for (int i4 = d; i4 < i2; i4++) {
            int i5;
            if ((aVar.a(i4) ^ i5) != 0) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (a(iArr2, iArr, 0.7f) < 0.48f) {
                    return new int[]{d, i4};
                } else {
                    d += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, XZBDevice.DOWNLOAD_LIST_RECYCLE, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                if (i5 == 0) {
                    i5 = 1;
                } else {
                    i5 = 0;
                }
            }
        }
        throw j.a();
    }

    static int a(a aVar, int[] iArr, int i, int[][] iArr2) throws j {
        a(aVar, i, iArr);
        float f = 0.48f;
        int i2 = -1;
        int length = iArr2.length;
        int i3 = 0;
        while (i3 < length) {
            float a = a(iArr, iArr2[i3], 0.7f);
            if (a < f) {
                i2 = i3;
            } else {
                a = f;
            }
            i3++;
            f = a;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw j.a();
    }
}
