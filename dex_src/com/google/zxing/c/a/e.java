package com.google.zxing.c.a;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.common.a;
import com.google.zxing.j;
import com.google.zxing.n;
import com.google.zxing.o;
import com.google.zxing.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// compiled from: RSS14Reader.java
public final class e extends a {
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private static final int[] j;
    private static final int[] k;
    private static final int[] l;
    private static final int[][] m;
    private final List<d> n;
    private final List<d> o;

    static {
        g = new int[]{1, 10, 34, 70, 126};
        h = new int[]{4, 20, 48, 81};
        i = new int[]{0, 161, 961, 2015, 2715};
        j = new int[]{0, 336, 1036, 1516};
        k = new int[]{8, 6, 4, 3, 1};
        l = new int[]{2, 4, 6, 8};
        m = new int[][]{new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    }

    public e() {
        this.n = new ArrayList();
        this.o = new ArrayList();
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j {
        a(this.n, a(aVar, false, i, map));
        aVar.c();
        a(this.o, a(aVar, true, i, map));
        aVar.c();
        int size = this.n.size();
        for (int i2 = 0; i2 < size; i2++) {
            d dVar = (d) this.n.get(i2);
            if (dVar.d > 1) {
                int size2 = this.o.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    d dVar2 = (d) this.o.get(i3);
                    if (dVar2.d > 1) {
                        boolean z;
                        int i4 = (dVar.b + (dVar2.b * 16)) % 79;
                        int i5 = (dVar.c.a * 9) + dVar2.c.a;
                        if (i5 > 72) {
                            i5--;
                        }
                        if (i5 > 8) {
                            i5--;
                        }
                        if (i4 == i5) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            String valueOf = String.valueOf((4537077 * ((long) dVar.a)) + ((long) dVar2.a));
                            StringBuilder stringBuilder = new StringBuilder(14);
                            for (i2 = 13 - valueOf.length(); i2 > 0; i2--) {
                                stringBuilder.append('0');
                            }
                            stringBuilder.append(valueOf);
                            i5 = 0;
                            i3 = 0;
                            while (i3 < 13) {
                                i2 = stringBuilder.charAt(i3) - 48;
                                if ((i3 & 1) == 0) {
                                    i2 *= 3;
                                }
                                i3++;
                                i5 = i2 + i5;
                            }
                            i2 = 10 - (i5 % 10);
                            if (i2 == 10) {
                                i2 = 0;
                            }
                            stringBuilder.append(i2);
                            o[] oVarArr = dVar.c.c;
                            o[] oVarArr2 = dVar2.c.c;
                            return new n(String.valueOf(stringBuilder.toString()), null, new o[]{oVarArr[0], oVarArr[1], oVarArr2[0], oVarArr2[1]}, BarcodeFormat.RSS_14);
                        }
                    }
                }
                continue;
            }
        }
        throw j.a();
    }

    private static void a(Collection<d> collection, d dVar) {
        if (dVar != null) {
            Object obj;
            for (d dVar2 : collection) {
                if (dVar2.a == dVar.a) {
                    dVar2.d++;
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                collection.add(dVar);
            }
        }
    }

    public final void a() {
        this.n.clear();
        this.o.clear();
    }

    private d a(a aVar, boolean z, int i, Map<DecodeHintType, ?> map) {
        int i2 = 0;
        try {
            boolean z2;
            int[] iArr = this.a;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            int i3 = aVar.b;
            Object obj = null;
            while (i2 < i3) {
                z2 = !aVar.a(i2) ? 1 : null;
                if (z == z2) {
                    break;
                }
                i2++;
            }
            int i4 = i2;
            boolean z3 = z2;
            int i5 = 0;
            while (i4 < i3) {
                int i6;
                int i7;
                if ((aVar.a(i4) ^ i6) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                    i7 = i6;
                    i6 = i2;
                    i2 = i7;
                } else {
                    int i8;
                    Object obj2;
                    if (i5 != 3) {
                        i8 = i2;
                        i2 = i5 + 1;
                    } else if (b(iArr)) {
                        p pVar;
                        int[] iArr2 = new int[]{i2, i4};
                        boolean a = aVar.a(iArr2[0]);
                        i5 = iArr2[0] - 1;
                        while (i5 >= 0 && (aVar.a(i5) ^ a) != 0) {
                            i5--;
                        }
                        int i9 = i5 + 1;
                        i5 = iArr2[0] - i9;
                        obj2 = this.a;
                        System.arraycopy(obj2, 0, obj2, 1, obj2.length - 1);
                        obj2[0] = i5;
                        i2 = a(obj2, m);
                        i4 = iArr2[1];
                        if (z) {
                            i8 = (aVar.b - 1) - i9;
                            i4 = (aVar.b - 1) - i4;
                        } else {
                            i8 = i9;
                        }
                        c cVar = new c(i2, new int[]{i9, iArr2[1]}, i8, i4, i);
                        if (map == null) {
                            pVar = null;
                        } else {
                            pVar = (p) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                        }
                        if (pVar != null) {
                            float f = ((float) (iArr2[0] + iArr2[1])) / 2.0f;
                            if (z) {
                                f = ((float) (aVar.b - 1)) - f;
                            }
                            o oVar = new o(f, (float) i);
                        }
                        b a2 = a(aVar, cVar, true);
                        b a3 = a(aVar, cVar, false);
                        return new d((a2.a * 1597) + a3.a, a2.b + (a3.b * 4), cVar);
                    } else {
                        i8 = (iArr[0] + iArr[1]) + i2;
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = 0;
                        iArr[3] = 0;
                        i2 = i5 - 1;
                    }
                    iArr[i2] = 1;
                    obj = i6 == 0 ? 1 : null;
                    i6 = i8;
                    Object obj3 = obj;
                    i5 = i2;
                    obj2 = obj3;
                }
                i4++;
                i7 = i2;
                i2 = i6;
                i6 = i7;
            }
            throw j.a();
        } catch (j e) {
            return null;
        }
    }

    private b a(a aVar, c cVar, boolean z) throws j {
        int i;
        int length;
        int i2;
        int i3;
        int[] iArr = this.b;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        if (z) {
            b(aVar, cVar.b[0], iArr);
        } else {
            a(aVar, cVar.b[1] + 1, iArr);
            i = 0;
            for (length = iArr.length - 1; i < length; length--) {
                int i4 = iArr[i];
                iArr[i] = iArr[length];
                iArr[length] = i4;
                i++;
            }
        }
        length = z ? R.styleable.Toolbar_titleMarginBottom : XZBDevice.Delete;
        float a = ((float) a(iArr)) / ((float) length);
        int[] iArr2 = this.e;
        int[] iArr3 = this.f;
        float[] fArr = this.c;
        float[] fArr2 = this.d;
        for (i = 0; i < iArr.length; i++) {
            float f = ((float) iArr[i]) / a;
            i4 = (int) (0.5f + f);
            if (i4 <= 0) {
                i4 = 1;
            } else if (i4 > 8) {
                i4 = XZBDevice.Wait;
            }
            i2 = i / 2;
            if ((i & 1) == 0) {
                iArr2[i2] = i4;
                fArr[i2] = f - ((float) i4);
            } else {
                iArr3[i2] = i4;
                fArr2[i2] = f - ((float) i4);
            }
        }
        int a2 = a(this.e);
        int a3 = a(this.f);
        i2 = (a2 + a3) - length;
        if ((a2 & 1) == (z ? 1 : null)) {
            int i5 = 1;
        } else {
            Object obj = null;
        }
        if ((a3 & 1) == 1) {
            i3 = 1;
        } else {
            Object obj2 = null;
        }
        Object obj3 = null;
        Object obj4 = null;
        Object obj5 = null;
        Object obj6 = null;
        if (z) {
            if (a2 > 12) {
                obj4 = 1;
            } else if (a2 < 4) {
                obj3 = 1;
            }
            if (a3 > 12) {
                obj6 = 1;
            } else if (a3 < 4) {
                obj5 = 1;
            }
        } else {
            if (a2 > 11) {
                obj4 = 1;
            } else if (a2 < 5) {
                obj3 = 1;
            }
            if (a3 > 10) {
                obj6 = 1;
            } else if (a3 < 4) {
                obj5 = 1;
            }
        }
        if (i2 == 1) {
            if (obj != null) {
                if (obj2 != null) {
                    throw j.a();
                }
                obj4 = 1;
            } else if (obj2 == null) {
                throw j.a();
            } else {
                obj6 = 1;
            }
        } else if (i2 == -1) {
            if (obj != null) {
                if (obj2 != null) {
                    throw j.a();
                }
                obj3 = 1;
            } else if (obj2 == null) {
                throw j.a();
            } else {
                obj5 = 1;
            }
        } else if (i2 != 0) {
            throw j.a();
        } else if (obj != null) {
            if (obj2 == null) {
                throw j.a();
            } else if (a2 < a3) {
                obj3 = 1;
                obj6 = 1;
            } else {
                obj4 = 1;
                obj5 = 1;
            }
        } else if (obj2 != null) {
            throw j.a();
        }
        if (obj3 != null) {
            if (obj4 != null) {
                throw j.a();
            }
            a(this.e, this.c);
        }
        if (obj4 != null) {
            b(this.e, this.c);
        }
        if (obj5 != null) {
            if (obj6 != null) {
                throw j.a();
            }
            a(this.f, this.c);
        }
        if (obj6 != null) {
            b(this.f, this.d);
        }
        length = iArr2.length - 1;
        int i6 = 0;
        i3 = 0;
        while (length >= 0) {
            i = (i6 * 9) + iArr2[length];
            length--;
            i6 = i;
            i3 = iArr2[length] + i3;
        }
        i = 0;
        length = 0;
        for (i4 = iArr3.length - 1; i4 >= 0; i4--) {
            i = (i * 9) + iArr3[i4];
            length += iArr3[i4];
        }
        i = (i * 3) + i6;
        if (z) {
            if ((i3 & 1) != 0 || i3 > 12 || i3 < 4) {
                throw j.a();
            }
            length = (12 - i3) / 2;
            i4 = k[length];
            return new b(((f.a(iArr2, i4, false) * g[length]) + f.a(iArr3, 9 - i4, true)) + i[length], i);
        } else if ((length & 1) != 0 || length > 10 || length < 4) {
            throw j.a();
        } else {
            length = (10 - length) / 2;
            i4 = l[length];
            return new b((f.a(iArr2, i4, true) + (f.a(iArr3, 9 - i4, false) * h[length])) + j[length], i);
        }
    }
}
