package com.google.zxing.datamatrix.b;

import com.google.zxing.common.h;
import com.google.zxing.j;
import com.google.zxing.o;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

// compiled from: Detector.java
public final class a {
    public final com.google.zxing.common.b a;
    public final com.google.zxing.common.a.b b;

    // compiled from: Detector.java
    private static final class a {
        public final o a;
        public final o b;
        public final int c;

        private a(o oVar, o oVar2, int i) {
            this.a = oVar;
            this.b = oVar2;
            this.c = i;
        }

        public final String toString() {
            return this.a + "/" + this.b + '/' + this.c;
        }
    }

    // compiled from: Detector.java
    private static final class b implements Serializable, Comparator<a> {
        private b() {
        }

        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((a) obj).c - ((a) obj2).c;
        }
    }

    public a(com.google.zxing.common.b bVar) throws j {
        this.a = bVar;
        this.b = new com.google.zxing.common.a.b(bVar);
    }

    public final boolean a(o oVar) {
        return oVar.a >= 0.0f && oVar.a < ((float) this.a.a) && oVar.b > 0.0f && oVar.b < ((float) this.a.b);
    }

    public static int a(o oVar, o oVar2) {
        return com.google.zxing.common.a.a.a(o.a(oVar, oVar2));
    }

    public static void a(Map<o, Integer> map, o oVar) {
        Integer num = (Integer) map.get(oVar);
        map.put(oVar, Integer.valueOf(num == null ? 1 : num.intValue() + 1));
    }

    public static com.google.zxing.common.b a(com.google.zxing.common.b bVar, o oVar, o oVar2, o oVar3, o oVar4, int i, int i2) throws j {
        return h.a().a(bVar, i, i2, 0.5f, 0.5f, ((float) i) - 0.5f, 0.5f, ((float) i) - 0.5f, ((float) i2) - 0.5f, 0.5f, ((float) i2) - 0.5f, oVar.a, oVar.b, oVar4.a, oVar4.b, oVar3.a, oVar3.b, oVar2.a, oVar2.b);
    }

    public final a b(o oVar, o oVar2) {
        Object obj;
        int i;
        int i2 = (int) oVar.a;
        int i3 = (int) oVar.b;
        int i4 = (int) oVar2.a;
        int i5 = (int) oVar2.b;
        if (Math.abs(i5 - i3) > Math.abs(i4 - i2)) {
            int i6 = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            int i7 = i5;
            i5 = i4;
            i4 = i7;
            int i8 = i3;
            i3 = i2;
            i2 = i8;
        }
        int abs = Math.abs(i5 - i3);
        int abs2 = Math.abs(i4 - i2);
        int i9 = (-abs) / 2;
        int i10 = i2 < i4 ? 1 : -1;
        int i11 = i3 < i5 ? 1 : -1;
        int i12 = 0;
        com.google.zxing.common.b bVar = this.a;
        if (obj != null) {
            i = i2;
        } else {
            i = i3;
        }
        boolean a = bVar.a(i, obj != null ? i3 : i2);
        int i13 = i9;
        int i14 = i2;
        while (i3 != i5) {
            com.google.zxing.common.b bVar2 = this.a;
            if (obj != null) {
                i9 = i14;
            } else {
                i9 = i3;
            }
            if (obj != null) {
                i2 = i3;
            } else {
                i2 = i14;
            }
            boolean a2 = bVar2.a(i9, i2);
            if (a2 != a) {
                i12++;
                a = a2;
            }
            i2 = i13 + abs2;
            if (i2 > 0) {
                if (i14 == i4) {
                    break;
                }
                i14 += i10;
                i2 -= abs;
            }
            i3 += i11;
            i13 = i2;
        }
        return new a(oVar2, i12, (byte) 0);
    }
}
