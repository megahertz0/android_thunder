package com.google.zxing.c.a;

import android.support.v4.widget.ExploreByTouchHelper;
import com.google.zxing.c.q;
import com.google.zxing.j;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;

// compiled from: AbstractRSSReader.java
public abstract class a extends q {
    public final int[] a;
    public final int[] b;
    public final float[] c;
    public final float[] d;
    public final int[] e;
    public final int[] f;

    public a() {
        this.a = new int[4];
        this.b = new int[8];
        this.c = new float[4];
        this.d = new float[4];
        this.e = new int[(this.b.length / 2)];
        this.f = new int[(this.b.length / 2)];
    }

    public static int a(int[] iArr, int[][] iArr2) throws j {
        for (int i = 0; i < iArr2.length; i++) {
            if (a(iArr, iArr2[i], 0.45f) < 0.2f) {
                return i;
            }
        }
        throw j.a();
    }

    public static int a(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i2 += iArr[i];
            i++;
        }
        return i2;
    }

    public static void a(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    public static void b(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    public static boolean b(int[] iArr) {
        int i = iArr[0] + iArr[1];
        float f = ((float) i) / ((float) ((iArr[2] + i) + iArr[3]));
        if (f < 0.7916667f || f > 0.89285713f) {
            return false;
        }
        int i2;
        int i3 = InMobiClientPositioning.NO_REPEAT;
        Object obj = ExploreByTouchHelper.INVALID_ID;
        int length = iArr.length;
        int i4 = 0;
        while (i4 < length) {
            i = iArr[i4];
            if (i > i2) {
                i2 = i;
            }
            if (i >= i3) {
                i = i3;
            }
            i4++;
            i3 = i;
        }
        return i2 < i3 * 10;
    }
}
