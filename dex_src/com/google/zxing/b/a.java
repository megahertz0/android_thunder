package com.google.zxing.b;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.b.a.c;
import com.google.zxing.common.d;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.n;
import com.google.zxing.o;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;

// compiled from: MaxiCodeReader.java
public final class a implements l {
    private static final o[] a;
    private final c b;

    public a() {
        this.b = new c();
    }

    static {
        a = new o[0];
    }

    public final n a(b bVar, Map<DecodeHintType, ?> map) throws j, com.google.zxing.c, e {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw j.a();
        }
        int i;
        com.google.zxing.common.b a = bVar.a();
        int i2 = a.a;
        int i3 = a.b;
        int i4 = -1;
        int i5 = -1;
        for (i = 0; i < a.b; i++) {
            int i6 = 0;
            while (i6 < a.c) {
                int i7;
                int i8 = a.d[(a.c * i) + i6];
                if (i8 != 0) {
                    if (i < i3) {
                        i3 = i;
                    }
                    if (i > i5) {
                        i5 = i;
                    }
                    if (i6 * 32 < i2) {
                        i7 = 0;
                        while ((i8 << (31 - i7)) == 0) {
                            i7++;
                        }
                        if ((i6 * 32) + i7 < i2) {
                            i7 = (i6 * 32) + i7;
                            if ((i6 * 32) + 31 > i4) {
                                i2 = R.styleable.AppCompatTheme_actionModeCloseDrawable;
                                while ((i8 >>> i2) == 0) {
                                    i2--;
                                }
                                if ((i6 * 32) + i2 > i4) {
                                    i4 = (i6 * 32) + i2;
                                    i2 = i3;
                                    i3 = i4;
                                    i4 = i5;
                                }
                            }
                            i2 = i3;
                            i3 = i4;
                            i4 = i5;
                        }
                    }
                    i7 = i2;
                    if ((i6 * 32) + 31 > i4) {
                        i2 = R.styleable.AppCompatTheme_actionModeCloseDrawable;
                        while ((i8 >>> i2) == 0) {
                            i2--;
                        }
                        if ((i6 * 32) + i2 > i4) {
                            i4 = (i6 * 32) + i2;
                            i2 = i3;
                            i3 = i4;
                            i4 = i5;
                        }
                    }
                    i2 = i3;
                    i3 = i4;
                    i4 = i5;
                } else {
                    i7 = i2;
                    i2 = i3;
                    i3 = i4;
                    i4 = i5;
                }
                i6++;
                i5 = i4;
                i4 = i3;
                i3 = i2;
                i2 = i7;
            }
        }
        int[] iArr = (i4 - i2 < 0 || i5 - i3 < 0) ? null : new int[]{i2, i3, i4 - i2, i5 - i3};
        if (iArr == null) {
            throw j.a();
        }
        i4 = iArr[0];
        i3 = iArr[1];
        i2 = iArr[2];
        i6 = iArr[3];
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(30, 33);
        for (i5 = 0; i5 < 33; i5++) {
            i8 = i3 + (((i5 * i6) + (i6 / 2)) / 33);
            for (i = 0; i < 30; i++) {
                if (a.a(((((i * i2) + (i2 / 2)) + (((i5 & 1) * i2) / 2)) / 30) + i4, i8)) {
                    bVar2.b(i, i5);
                }
            }
        }
        c cVar = this.b;
        com.google.zxing.b.a.a aVar = new com.google.zxing.b.a.a(bVar2);
        Object obj = new Object[144];
        i6 = aVar.b.b;
        i7 = aVar.b.a;
        i4 = 0;
        while (i4 < i6) {
            int[] iArr2 = com.google.zxing.b.a.a.a[i4];
            i3 = 0;
            while (i3 < i7) {
                i8 = iArr2[i3];
                if (i8 >= 0 && aVar.b.a(i3, i4)) {
                    int i9 = i8 / 6;
                    obj[i9] = (byte) (((byte) (1 << (5 - (i8 % 6)))) | obj[i9]);
                }
                i3++;
            }
            i4++;
        }
        cVar.a(obj, 0, XZBDevice.Stop, XZBDevice.Stop, 0);
        i7 = obj[0] & 15;
        Object obj2;
        switch (i7) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                cVar.a(obj, R.styleable.Toolbar_navigationIcon, R.styleable.AppCompatTheme_colorAccent, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, 1);
                cVar.a(obj, R.styleable.Toolbar_navigationIcon, R.styleable.AppCompatTheme_colorAccent, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                obj2 = new Object[94];
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                cVar.a(obj, R.styleable.Toolbar_navigationIcon, R.styleable.AppCompatTheme_searchViewStyle, R.styleable.AppCompatTheme_dividerHorizontal, 1);
                cVar.a(obj, R.styleable.Toolbar_navigationIcon, R.styleable.AppCompatTheme_searchViewStyle, R.styleable.AppCompatTheme_dividerHorizontal, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                obj2 = new Object[78];
                break;
            default:
                throw e.a();
        }
        System.arraycopy(obj, 0, r0, 0, XZBDevice.Stop);
        System.arraycopy(obj, R.styleable.Toolbar_navigationIcon, r0, XZBDevice.Stop, r0.length - 10);
        d a2 = com.google.zxing.b.a.b.a(r0, i7);
        n nVar = new n(a2.b, a2.a, a, BarcodeFormat.MAXICODE);
        String str = a2.d;
        if (str != null) {
            nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
        }
        return nVar;
    }

    public final void a() {
    }
}
