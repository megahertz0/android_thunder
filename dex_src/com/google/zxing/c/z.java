package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: UPCEReader.java
public final class z extends x {
    private static final int[] a;
    private static final int[][] f;
    private final int[] g;

    static {
        a = new int[]{1, 1, 1, 1, 1, 1};
        f = new int[][]{new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};
    }

    public z() {
        this.g = new int[4];
    }

    protected final int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws j {
        int i;
        int[] iArr2 = this.g;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = aVar.b;
        int i3 = iArr[1];
        int i4 = 0;
        int i5 = 0;
        while (i4 < 6 && i3 < i2) {
            int a = a(aVar, iArr2, i3, e);
            stringBuilder.append((char) ((a % 10) + 48));
            int i6 = i3;
            for (i = 0; i < iArr2.length; i++) {
                i6 += iArr2[i];
            }
            if (a >= 10) {
                i5 |= 1 << (5 - i4);
            }
            i4++;
            i3 = i6;
        }
        for (i6 = 0; i6 <= 1; i6++) {
            for (i = 0; i < 10; i++) {
                if (i5 == f[i6][i]) {
                    stringBuilder.insert(0, (char) (i6 + 48));
                    stringBuilder.append((char) (i + 48));
                    return i3;
                }
            }
        }
        throw j.a();
    }

    protected final int[] a(a aVar, int i) throws j {
        return a(aVar, i, true, a);
    }

    protected final boolean a(String str) throws e {
        char[] cArr = new char[6];
        str.getChars(1, R.styleable.Toolbar_contentInsetLeft, cArr, 0);
        StringBuilder stringBuilder = new StringBuilder(12);
        stringBuilder.append(str.charAt(0));
        char c = cArr[5];
        switch (c) {
            case R.styleable.AppCompatTheme_homeAsUpIndicator:
            case R.styleable.AppCompatTheme_actionButtonStyle:
            case R.styleable.AppCompatTheme_buttonBarStyle:
                stringBuilder.append(cArr, 0, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                stringBuilder.append(c);
                stringBuilder.append("0000");
                stringBuilder.append(cArr, XZBDevice.DOWNLOAD_LIST_RECYCLE, XZBDevice.DOWNLOAD_LIST_FAILED);
                break;
            case R.styleable.AppCompatTheme_buttonBarButtonStyle:
                stringBuilder.append(cArr, 0, XZBDevice.DOWNLOAD_LIST_FAILED);
                stringBuilder.append("00000");
                stringBuilder.append(cArr, XZBDevice.DOWNLOAD_LIST_FAILED, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                break;
            case R.styleable.AppCompatTheme_selectableItemBackground:
                stringBuilder.append(cArr, 0, XZBDevice.DOWNLOAD_LIST_ALL);
                stringBuilder.append("00000");
                stringBuilder.append(cArr[4]);
                break;
            default:
                stringBuilder.append(cArr, 0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                stringBuilder.append("0000");
                stringBuilder.append(c);
                break;
        }
        stringBuilder.append(str.charAt(R.styleable.Toolbar_contentInsetLeft));
        return super.a(stringBuilder.toString());
    }

    final BarcodeFormat b() {
        return BarcodeFormat.UPC_E;
    }
}
