package com.google.zxing.pdf417.a;

import android.support.v4.widget.AutoScrollHelper;
import com.google.zxing.pdf417.a;
import java.lang.reflect.Array;

// compiled from: PDF417CodewordDecoder.java
final class j {
    private static final float[][] a;

    static {
        a = (float[][]) Array.newInstance(Float.TYPE, new int[]{a.length, 8});
        for (int i = 0; i < a.length; i++) {
            int i2 = a[i];
            int i3 = i2 & 1;
            for (int i4 = 0; i4 < 8; i4++) {
                float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
                while ((i2 & 1) == i3) {
                    f += 1.0f;
                    i2 >>= 1;
                }
                i3 = i2 & 1;
                a[i][(8 - i4) - 1] = f / 17.0f;
            }
        }
    }

    static int a(int[] iArr) {
        int i;
        float a = (float) a.a(iArr);
        int[] iArr2 = new int[8];
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < 17; i++) {
            if (((float) (iArr[i3] + i2)) <= (a / 34.0f) + ((((float) i) * a) / 17.0f)) {
                i2 += iArr[i3];
                i3++;
            }
            iArr2[i3] = iArr2[i3] + 1;
        }
        long j = 0;
        int i4 = 0;
        while (i4 < 8) {
            long j2 = j;
            for (i2 = 0; i2 < iArr2[i4]; i2++) {
                j2 <<= 1;
                if (i4 % 2 == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                j2 |= (long) i3;
            }
            i4++;
            j = j2;
        }
        i2 = (int) j;
        if (a.a(i2) == -1) {
            i2 = -1;
        }
        if (i2 == -1) {
            i3 = a.a(iArr);
            float[] fArr = new float[8];
            for (i2 = 0; i2 < 8; i2++) {
                fArr[i2] = ((float) iArr[i2]) / ((float) i3);
            }
            float f = AutoScrollHelper.NO_MAX;
            i2 = -1;
            i = 0;
            while (i < a.length) {
                float f2 = AutoScrollHelper.RELATIVE_UNSPECIFIED;
                float[] fArr2 = a[i];
                for (int i5 = 0; i5 < 8; i5++) {
                    float f3 = fArr2[i5] - fArr[i5];
                    f2 += f3 * f3;
                    if (f2 >= f) {
                        break;
                    }
                }
                if (f2 < f) {
                    i2 = a[i];
                } else {
                    f2 = f;
                }
                i++;
                f = f2;
            }
        }
        return i2;
    }
}
