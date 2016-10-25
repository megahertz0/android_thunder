package com.google.zxing.c;

import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.c;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.m;
import com.google.zxing.n;
import com.google.zxing.o;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

// compiled from: OneDReader.java
public abstract class q implements l {
    public abstract n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j, c, e;

    public n a(b bVar, Map<DecodeHintType, ?> map) throws j, e {
        try {
            return b(bVar, map);
        } catch (j e) {
            if (map != null) {
                map.containsKey(DecodeHintType.TRY_HARDER);
            }
            throw e;
        }
    }

    public void a() {
    }

    public static void a(a aVar, int i, int[] iArr) throws j {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int i2 = aVar.b;
        if (i >= i2) {
            throw j.a();
        }
        int i3;
        int i4 = !aVar.a(i) ? 1 : 0;
        int i5 = 0;
        while (i < i2) {
            if ((aVar.a(i) ^ i4) == 0) {
                i3 = i5 + 1;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                int i6 = i3;
                i3 = i4 == 0 ? 1 : 0;
                i5 = i6;
            } else {
                iArr[i5] = iArr[i5] + 1;
                i3 = i4;
            }
            i++;
            i4 = i3;
        }
        i3 = i5;
        if (i3 == length) {
            return;
        }
        if (i3 != length - 1 || i != i2) {
            throw j.a();
        }
    }

    public static void b(a aVar, int i, int[] iArr) throws j {
        int length = iArr.length;
        boolean a = aVar.a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (aVar.a(i) != a) {
                length--;
                a = !a;
            }
        }
        if (length >= 0) {
            throw j.a();
        }
        a(aVar, i + 1, iArr);
    }

    public static float a(int[] iArr, int[] iArr2, float f) {
        int i;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < length; i++) {
            i3 += iArr[i];
            i2 += iArr2[i];
        }
        if (i3 < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = ((float) i3) / ((float) i2);
        float f3 = f * f2;
        float f4 = 0.0f;
        for (i = 0; i < length; i++) {
            int i4 = iArr[i];
            float f5 = ((float) iArr2[i]) * f2;
            float f6 = ((float) i4) > f5 ? ((float) i4) - f5 : f5 - ((float) i4);
            if (f6 > f3) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f6;
        }
        return f4 / ((float) i3);
    }

    private n b(b bVar, Map<DecodeHintType, ?> map) throws j {
        int max;
        int i;
        a aVar;
        Map<DecodeHintType, ?> map2;
        int i2;
        int i3;
        int i4;
        int i5 = bVar.a.a.a;
        int i6 = bVar.a.a.b;
        a aVar2 = new a(i5);
        int i7 = i6 >> 1;
        if (map != null) {
            if (map.containsKey(DecodeHintType.TRY_HARDER)) {
                int i8 = 1;
                max = Math.max(1, i6 >> (r3 == null ? XZBDevice.Wait : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
                if (r3 == null) {
                    i = i6;
                } else {
                    Object obj = XZBDevice.Delete;
                }
                aVar = aVar2;
                map2 = map;
                for (i2 = 0; i2 < i; i2++) {
                    i3 = (i2 + 1) / 2;
                    if (((i2 & 1) != 0 ? 1 : null) == null) {
                        i3 = -i3;
                    }
                    i4 = i7 + (i3 * max);
                    if (i4 < 0 || i4 >= i6) {
                        break;
                    }
                    try {
                        aVar = bVar.a.a(i4, aVar);
                        for (int i9 = 0; i9 < 2; i9++) {
                            Map map3;
                            if (i9 == 1) {
                                aVar.c();
                                if (map3 != null && map3.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                                    Map enumMap = new EnumMap(DecodeHintType.class);
                                    enumMap.putAll(map3);
                                    enumMap.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                                    map3 = enumMap;
                                }
                            }
                            try {
                                n a = a(i4, aVar, map3);
                                if (i9 == 1) {
                                    a.a(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
                                    o[] oVarArr = a.c;
                                    if (oVarArr != null) {
                                        oVarArr[0] = new o((((float) i5) - oVarArr[0].a) - 1.0f, oVarArr[0].b);
                                        oVarArr[1] = new o((((float) i5) - oVarArr[1].a) - 1.0f, oVarArr[1].b);
                                    }
                                }
                                return a;
                            } catch (m e) {
                            }
                        }
                        continue;
                    } catch (j e2) {
                    }
                }
                throw j.a();
            }
        }
        Object obj2 = null;
        if (obj2 == null) {
        }
        max = Math.max(1, i6 >> (obj2 == null ? XZBDevice.Wait : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
        if (obj2 == null) {
            Object obj3 = XZBDevice.Delete;
        } else {
            i = i6;
        }
        aVar = aVar2;
        map2 = map;
        while (i2 < i) {
            i3 = (i2 + 1) / 2;
            if ((i2 & 1) != 0) {
            }
            if (((i2 & 1) != 0 ? 1 : null) == null) {
                i3 = -i3;
            }
            i4 = i7 + (i3 * max);
            if (i4 < 0) {
                break;
            }
            break;
            throw j.a();
        }
        throw j.a();
    }
}
