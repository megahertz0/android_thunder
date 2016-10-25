package com.google.zxing.common.a;

import com.google.zxing.j;
import com.google.zxing.o;

// compiled from: WhiteRectangleDetector.java
public final class b {
    private final com.google.zxing.common.b a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public b(com.google.zxing.common.b bVar) throws j {
        this(bVar, 10, bVar.a / 2, bVar.b / 2);
    }

    public b(com.google.zxing.common.b bVar, int i, int i2, int i3) throws j {
        this.a = bVar;
        this.b = bVar.b;
        this.c = bVar.a;
        int i4 = i / 2;
        this.d = i2 - i4;
        this.e = i2 + i4;
        this.g = i3 - i4;
        this.f = i4 + i3;
        if (this.g < 0 || this.d < 0 || this.f >= this.b || this.e >= this.c) {
            throw j.a();
        }
    }

    public final o[] a() throws j {
        int i;
        int i2 = this.d;
        int i3 = this.e;
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        Object obj5 = null;
        Object obj6 = null;
        int i4 = this.f;
        int i5 = this.g;
        int i6 = i3;
        i3 = i2;
        i2 = 1;
        Object obj7;
        while (obj7 != null) {
            boolean z = true;
            Object obj8 = obj3;
            obj3 = null;
            Object obj9 = obj8;
            while (true) {
                if ((!z && obj9 != null) || i6 >= this.c) {
                    break;
                }
                z = a(i5, i4, i6, false);
                if (z) {
                    obj3 = 1;
                    i6++;
                    int i7 = 1;
                } else if (obj9 == null) {
                    i6++;
                }
            }
            if (i6 >= this.c) {
                obj = 1;
                i2 = i6;
                i = i5;
                i5 = i3;
                i3 = i4;
                i4 = i;
                break;
            }
            z = true;
            obj8 = obj4;
            obj4 = obj3;
            obj3 = obj8;
            while (true) {
                if ((!z && obj3 != null) || i4 >= this.b) {
                    break;
                }
                z = a(i3, i6, i4, true);
                if (z) {
                    obj4 = 1;
                    i4++;
                    int i8 = 1;
                } else if (obj3 == null) {
                    i4++;
                }
            }
            if (i4 >= this.b) {
                obj = 1;
                i2 = i6;
                i = i5;
                i5 = i3;
                i3 = i4;
                i4 = i;
                break;
            }
            z = true;
            obj8 = obj5;
            obj5 = obj4;
            obj4 = obj8;
            while (true) {
                if ((!z && obj4 != null) || i3 < 0) {
                    break;
                }
                z = a(i5, i4, i3, false);
                if (z) {
                    obj5 = 1;
                    i3--;
                    int i9 = 1;
                } else if (obj4 == null) {
                    i3--;
                }
            }
            if (i3 < 0) {
                obj = 1;
                i2 = i6;
                i = i5;
                i5 = i3;
                i3 = i4;
                i4 = i;
                break;
            }
            z = true;
            while (true) {
                if ((!z && obj6 != null) || i5 < 0) {
                    break;
                }
                z = a(i3, i6, i5, true);
                if (z) {
                    i5--;
                    int i10 = 1;
                    int i11 = 1;
                } else if (obj6 == null) {
                    i5--;
                }
            }
            if (i5 < 0) {
                obj = 1;
                i2 = i6;
                i = i5;
                i5 = i3;
                i3 = i4;
                i4 = i;
                break;
            }
            if (obj5 != null) {
                obj2 = 1;
            }
            obj7 = obj5;
            obj5 = obj4;
            obj4 = obj3;
            obj3 = obj9;
        }
        i2 = i6;
        i = i5;
        i5 = i3;
        i3 = i4;
        i4 = i;
        if (obj != null || r0 == null) {
            throw j.a();
        }
        int i12;
        i11 = i2 - i5;
        o oVar = null;
        for (i12 = 1; i12 < i11; i12++) {
            oVar = a((float) i5, (float) (i3 - i12), (float) (i5 + i12), (float) i3);
            if (oVar != null) {
                break;
            }
        }
        o oVar2 = oVar;
        if (oVar2 == null) {
            throw j.a();
        }
        oVar = null;
        for (i12 = 1; i12 < i11; i12++) {
            oVar = a((float) i5, (float) (i4 + i12), (float) (i5 + i12), (float) i4);
            if (oVar != null) {
                break;
            }
        }
        o oVar3 = oVar;
        if (oVar3 == null) {
            throw j.a();
        }
        oVar = null;
        for (i12 = 1; i12 < i11; i12++) {
            oVar = a((float) i2, (float) (i4 + i12), (float) (i2 - i12), (float) i4);
            if (oVar != null) {
                break;
            }
        }
        o oVar4 = oVar;
        if (oVar4 == null) {
            throw j.a();
        }
        oVar = null;
        for (i12 = 1; i12 < i11; i12++) {
            oVar = a((float) i2, (float) (i3 - i12), (float) (i2 - i12), (float) i3);
            if (oVar != null) {
                break;
            }
        }
        if (oVar == null) {
            throw j.a();
        }
        float f = oVar.a;
        float f2 = oVar.b;
        float f3 = oVar2.a;
        float f4 = oVar2.b;
        float f5 = oVar4.a;
        float f6 = oVar4.b;
        float f7 = oVar3.a;
        float f8 = oVar3.b;
        if (f < ((float) this.c) / 2.0f) {
            return new o[]{new o(f7 - 1.0f, f8 + 1.0f), new o(f3 + 1.0f, f4 + 1.0f), new o(f5 - 1.0f, f6 - 1.0f), new o(f + 1.0f, f2 - 1.0f)};
        }
        return new o[]{new o(f7 + 1.0f, f8 + 1.0f), new o(f3 + 1.0f, f4 - 1.0f), new o(f5 - 1.0f, f6 + 1.0f), new o(f - 1.0f, f2 - 1.0f)};
    }

    private o a(float f, float f2, float f3, float f4) {
        int a = a.a(a.a(f, f2, f3, f4));
        float f5 = (f3 - f) / ((float) a);
        float f6 = (f4 - f2) / ((float) a);
        for (int i = 0; i < a; i++) {
            int a2 = a.a((((float) i) * f5) + f);
            int a3 = a.a((((float) i) * f6) + f2);
            if (this.a.a(a2, a3)) {
                return new o((float) a2, (float) a3);
            }
        }
        return null;
    }

    private boolean a(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.a.a(i, i3)) {
                    return true;
                }
                i++;
            }
        } else {
            while (i <= i2) {
                if (this.a.a(i3, i)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
}
