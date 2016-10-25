package com.google.zxing.a.c;

import com.google.zxing.common.a;
import com.google.zxing.common.b;
import com.google.zxing.common.b.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

// compiled from: Encoder.java
public final class c {
    private static final int[] a;

    static {
        a = new int[]{4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
    }

    public static a a(byte[] bArr, int i, int i2) {
        int i3;
        int a;
        int i4;
        boolean z;
        a aVar;
        a a2;
        int i5;
        int i6;
        d dVar = new d(bArr);
        Object singletonList = Collections.singletonList(g.a);
        int i7 = 0;
        while (i7 < dVar.d.length) {
            byte b = i7 + 1 < dVar.d.length ? dVar.d[i7 + 1] : (byte) 0;
            switch (dVar.d[i7]) {
                case XZBDevice.Upload:
                    i3 = b == 10 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 0;
                    break;
                case R.styleable.AppCompatTheme_listDividerAlertDialog:
                    i3 = b == 32 ? XZBDevice.DOWNLOAD_LIST_ALL : 0;
                    break;
                case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight:
                    i3 = b == 32 ? XZBDevice.DOWNLOAD_LIST_FAILED : 0;
                    break;
                case R.styleable.AppCompatTheme_toolbarStyle:
                    i3 = b == 32 ? XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED : 0;
                    break;
                default:
                    i3 = 0;
                    break;
            }
            if (i3 > 0) {
                singletonList = d.a((Iterable) r2, i7, i3);
                i7++;
            } else {
                Collection linkedList = new LinkedList();
                for (g gVar : r2) {
                    dVar.a(gVar, i7, linkedList);
                }
                singletonList = d.a(linkedList);
            }
            i7++;
        }
        a a3 = ((g) Collections.min(r2, new e(dVar))).a(dVar.d);
        int i8 = ((a3.b * i) / 100) + 11;
        int i9 = a3.b + i8;
        int abs;
        if (i2 != 0) {
            boolean z2 = i2 < 0;
            abs = Math.abs(i2);
            if (abs > (z2 ? XZBDevice.DOWNLOAD_LIST_ALL : R.styleable.AppCompatTheme_actionModeCutDrawable)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(i2)}));
            }
            a = a(abs, z2);
            i3 = a[abs];
            i4 = a - (a % i3);
            a a4 = a(a3, i3);
            if (a4.b + i8 > i4) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            } else if (!z2 || a4.b <= i3 * 64) {
                a aVar2 = a4;
                i7 = i3;
                i3 = abs;
                z = z2;
                aVar = aVar2;
            } else {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        }
        i7 = 0;
        aVar = null;
        abs = 0;
        while (abs <= 32) {
            boolean z3 = abs <= 3;
            if (z3) {
                i3 = abs + 1;
            } else {
                i3 = abs;
            }
            i4 = a(i3, z3);
            if (i9 <= i4) {
                if (i7 != a[i3]) {
                    i7 = a[i3];
                    aVar = a(a3, i7);
                }
                int i10 = i4 - (i4 % i7);
                if ((!z3 || r2.b <= i7 * 64) && r2.b + i8 <= i10) {
                    z = z3;
                    a = i4;
                }
            }
            abs++;
        }
        throw new IllegalArgumentException("Data too large for an Aztec code");
        a a5 = a(aVar, a, i7);
        int i11 = aVar.b / i7;
        aVar = new a();
        if (z) {
            aVar.b(i3 - 1, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            aVar.b(i11 - 1, R.styleable.Toolbar_contentInsetEnd);
            a2 = a(aVar, (int) R.styleable.AppCompatTheme_actionModeCloseButtonStyle, (int) XZBDevice.DOWNLOAD_LIST_ALL);
        } else {
            aVar.b(i3 - 1, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            aVar.b(i11 - 1, XZBDevice.Success);
            a2 = a(aVar, (int) R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, (int) XZBDevice.DOWNLOAD_LIST_ALL);
        }
        if (z) {
            i5 = (i3 * 4) + 11;
        } else {
            i5 = (i3 * 4) + 14;
        }
        int[] iArr = new int[i5];
        if (z) {
            for (i7 = 0; i7 < iArr.length; i7++) {
                iArr[i7] = i7;
            }
            i7 = i5;
        } else {
            i7 = (i5 + 1) + ((((i5 / 2) - 1) / 15) * 2);
            i4 = i5 / 2;
            i6 = i7 / 2;
            for (a = 0; a < i4; a++) {
                i8 = (a / 15) + a;
                iArr[(i4 - a) - 1] = (i6 - i8) - 1;
                iArr[i4 + a] = (i8 + i6) + 1;
            }
        }
        b bVar = new b(i7);
        i8 = 0;
        for (i9 = 0; i9 < i3; i9++) {
            a = z ? ((i3 - i9) * 4) + 9 : ((i3 - i9) * 4) + 12;
            for (i6 = 0; i6 < a; i6++) {
                int i12 = i6 * 2;
                for (i4 = 0; i4 < 2; i4++) {
                    if (a5.a((i8 + i12) + i4)) {
                        bVar.b(iArr[(i9 * 2) + i4], iArr[(i9 * 2) + i6]);
                    }
                    if (a5.a((((a * 2) + i8) + i12) + i4)) {
                        bVar.b(iArr[(i9 * 2) + i6], iArr[((i5 - 1) - (i9 * 2)) - i4]);
                    }
                    if (a5.a((((a * 4) + i8) + i12) + i4)) {
                        bVar.b(iArr[((i5 - 1) - (i9 * 2)) - i4], iArr[((i5 - 1) - (i9 * 2)) - i6]);
                    }
                    if (a5.a((((a * 6) + i8) + i12) + i4)) {
                        bVar.b(iArr[((i5 - 1) - (i9 * 2)) - i6], iArr[(i9 * 2) + i4]);
                    }
                }
            }
            i8 = (a * 8) + i8;
        }
        a(bVar, z, i7, a2);
        if (z) {
            a(bVar, i7 / 2, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        } else {
            a(bVar, i7 / 2, (int) R.styleable.Toolbar_contentInsetLeft);
            i4 = 0;
            a = 0;
            while (i4 < (i5 / 2) - 1) {
                for (i6 = (i7 / 2) & 1; i6 < i7; i6 += 2) {
                    bVar.b((i7 / 2) - a, i6);
                    bVar.b((i7 / 2) + a, i6);
                    bVar.b(i6, (i7 / 2) - a);
                    bVar.b(i6, (i7 / 2) + a);
                }
                i4 += 15;
                a += 16;
            }
        }
        a aVar3 = new a();
        aVar3.a = z;
        aVar3.b = i7;
        aVar3.c = i3;
        aVar3.d = i11;
        aVar3.e = bVar;
        return aVar3;
    }

    private static void a(b bVar, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            for (int i4 = i - i3; i4 <= i + i3; i4++) {
                bVar.b(i4, i - i3);
                bVar.b(i4, i + i3);
                bVar.b(i - i3, i4);
                bVar.b(i + i3, i4);
            }
        }
        bVar.b(i - i2, i - i2);
        bVar.b((i - i2) + 1, i - i2);
        bVar.b(i - i2, (i - i2) + 1);
        bVar.b(i + i2, i - i2);
        bVar.b(i + i2, (i - i2) + 1);
        bVar.b(i + i2, (i + i2) - 1);
    }

    private static void a(b bVar, boolean z, int i, a aVar) {
        int i2 = 0;
        int i3 = i / 2;
        int i4;
        if (z) {
            while (i2 < 7) {
                i4 = (i3 - 3) + i2;
                if (aVar.a(i2)) {
                    bVar.b(i4, i3 - 5);
                }
                if (aVar.a(i2 + 7)) {
                    bVar.b(i3 + 5, i4);
                }
                if (aVar.a(20 - i2)) {
                    bVar.b(i4, i3 + 5);
                }
                if (aVar.a(27 - i2)) {
                    bVar.b(i3 - 5, i4);
                }
                i2++;
            }
            return;
        }
        while (i2 < 10) {
            i4 = ((i3 - 5) + i2) + (i2 / 5);
            if (aVar.a(i2)) {
                bVar.b(i4, i3 - 7);
            }
            if (aVar.a(i2 + 10)) {
                bVar.b(i3 + 7, i4);
            }
            if (aVar.a(29 - i2)) {
                bVar.b(i4, i3 + 7);
            }
            if (aVar.a(39 - i2)) {
                bVar.b(i3 - 7, i4);
            }
            i2++;
        }
    }

    private static a a(a aVar, int i, int i2) {
        com.google.zxing.common.b.a aVar2;
        int i3 = aVar.b / i2;
        switch (i2) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                aVar2 = com.google.zxing.common.b.a.d;
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                aVar2 = com.google.zxing.common.b.a.c;
                break;
            case XZBDevice.Wait:
                aVar2 = com.google.zxing.common.b.a.g;
                break;
            case XZBDevice.Stop:
                aVar2 = com.google.zxing.common.b.a.b;
                break;
            case XZBDevice.Fail:
                aVar2 = com.google.zxing.common.b.a.a;
                break;
            default:
                throw new IllegalArgumentException(new StringBuilder("Unsupported word size ").append(i2).toString());
        }
        d dVar = new d(aVar2);
        int i4 = i / i2;
        int[] b = b(aVar, i2, i4);
        dVar.a(b, i4 - i3);
        i4 = i % i2;
        a aVar3 = new a();
        aVar3.b(0, i4);
        int length = b.length;
        for (i4 = 0; i4 < length; i4++) {
            aVar3.b(b[i4], i2);
        }
        return aVar3;
    }

    private static int[] b(a aVar, int i, int i2) {
        int[] iArr = new int[i2];
        int i3 = aVar.b / i;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                int i7;
                if (aVar.a((i4 * i) + i6)) {
                    i7 = 1 << ((i - i6) - 1);
                } else {
                    i7 = 0;
                }
                i5 |= i7;
            }
            iArr[i4] = i5;
        }
        return iArr;
    }

    private static a a(a aVar, int i) {
        a aVar2 = new a();
        int i2 = aVar.b;
        int i3 = (1 << i) - 2;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < i) {
                if (i4 + i5 >= i2 || aVar.a(i4 + i5)) {
                    i6 |= 1 << ((i - 1) - i5);
                }
                i5++;
            }
            if ((i6 & i3) == i3) {
                aVar2.b(i6 & i3, i);
                i6 = i4 - 1;
            } else if ((i6 & i3) == 0) {
                aVar2.b(i6 | 1, i);
                i6 = i4 - 1;
            } else {
                aVar2.b(i6, i);
                i6 = i4;
            }
            i4 = i6 + i;
        }
        return aVar2;
    }

    private static int a(int i, boolean z) {
        return ((z ? R.styleable.AppCompatTheme_colorButtonNormal : 112) + (i * 16)) * i;
    }
}
