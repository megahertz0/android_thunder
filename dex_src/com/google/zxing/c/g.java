package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.c;
import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.n;
import com.google.zxing.o;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;
import java.util.Map;

// compiled from: Code93Reader.java
public final class g extends q {
    private static final char[] a;
    private static final int[] b;
    private static final int c;
    private final StringBuilder d;
    private final int[] e;

    static {
        a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
        int[] iArr = new int[]{276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        b = iArr;
        c = iArr[47];
    }

    public g() {
        this.d = new StringBuilder(20);
        this.e = new int[6];
    }

    private static int a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i++;
            i2 = iArr[i] + i2;
        }
        int i3 = 0;
        i = 0;
        while (i3 < length) {
            int round = Math.round((((float) iArr[i3]) * 9.0f) / ((float) i2));
            if (round > 0 && round <= 4) {
                if ((i3 & 1) == 0) {
                    int i4 = 0;
                    while (i4 < round) {
                        i4++;
                        i = (i << 1) | 1;
                    }
                } else {
                    i <<= round;
                }
                i3++;
            }
            return -1;
        }
        return i;
    }

    private static void a(CharSequence charSequence, int i, int i2) throws c {
        int i3 = 1;
        int i4 = i - 1;
        int i5 = 0;
        while (i4 >= 0) {
            int indexOf = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i4)) * i3) + i5;
            i5 = i3 + 1;
            if (i5 > i2) {
                i5 = 1;
            }
            i4--;
            i3 = i5;
            i5 = indexOf;
        }
        if (charSequence.charAt(i) != a[i5 % 47]) {
            throw c.a();
        }
    }

    public final n a(int i, a aVar, Map<DecodeHintType, ?> map) throws j, c, e {
        int i2 = aVar.b;
        int c = aVar.c(0);
        Arrays.fill(this.e, 0);
        Object obj = this.e;
        int i3 = 0;
        int length = obj.length;
        int i4 = 0;
        for (int i5 = c; i5 < i2; i5++) {
            if ((aVar.a(i5) ^ i3) != 0) {
                obj[i4] = obj[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (a(obj) == c) {
                    i4 = aVar.c(new int[]{c, i5}[1]);
                    int i6 = aVar.b;
                    int[] iArr = this.e;
                    Arrays.fill(iArr, 0);
                    CharSequence charSequence = this.d;
                    charSequence.setLength(0);
                    char c2;
                    do {
                        c = i4;
                        a(aVar, c, iArr);
                        i3 = a(iArr);
                        if (i3 < 0) {
                            throw j.a();
                        }
                        i4 = 0;
                        while (i4 < b.length) {
                            if (b[i4] == i3) {
                                c2 = a[i4];
                                charSequence.append(c2);
                                i3 = c;
                                for (int i7 : iArr) {
                                    i3 += i7;
                                }
                                i4 = aVar.c(i3);
                            } else {
                                i4++;
                            }
                        }
                        throw j.a();
                    } while (c2 != '*');
                    charSequence.deleteCharAt(charSequence.length() - 1);
                    i5 = 0;
                    for (int i72 : iArr) {
                        i5 += i72;
                    }
                    if (i4 == i6 || !aVar.a(i4)) {
                        throw j.a();
                    } else if (charSequence.length() < 2) {
                        throw j.a();
                    } else {
                        i4 = charSequence.length();
                        a(charSequence, i4 - 2, (int) R.styleable.Toolbar_navigationIcon);
                        a(charSequence, i4 - 1, (int) XZBDevice.Delete);
                        charSequence.setLength(charSequence.length() - 2);
                        i6 = charSequence.length();
                        StringBuilder stringBuilder = new StringBuilder(i6);
                        i3 = 0;
                        while (i3 < i6) {
                            char charAt = charSequence.charAt(i3);
                            if (charAt < 'a' || charAt > 'd') {
                                stringBuilder.append(charAt);
                                i4 = i3;
                            } else if (i3 >= i6 - 1) {
                                throw e.a();
                            } else {
                                char charAt2 = charSequence.charAt(i3 + 1);
                                char c3 = '\u0000';
                                switch (charAt) {
                                    case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                            c3 = (char) (charAt2 - 64);
                                        }
                                        throw e.a();
                                    case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                                        if (charAt2 >= 'A' && charAt2 <= 'E') {
                                            c3 = (char) (charAt2 - 38);
                                        } else if (charAt2 >= 'F' && charAt2 <= 'J') {
                                            c3 = (char) (charAt2 - 11);
                                        } else if (charAt2 >= 'K' && charAt2 <= 'O') {
                                            c3 = (char) (charAt2 + 16);
                                        } else if (charAt2 < 'P' || charAt2 > 'S') {
                                            if (charAt2 >= 'T' && charAt2 <= 'Z') {
                                                c3 = '\u007f';
                                            }
                                            throw e.a();
                                        } else {
                                            c3 = (char) (charAt2 + 43);
                                        }
                                        break;
                                    case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                                            c3 = (char) (charAt2 - 32);
                                        } else if (charAt2 == 'Z') {
                                            c3 = ':';
                                        } else {
                                            throw e.a();
                                        }
                                    case R.styleable.AppCompatTheme_buttonStyle:
                                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                            c3 = (char) (charAt2 + 32);
                                        }
                                        throw e.a();
                                }
                                stringBuilder.append(c3);
                                i4 = i3 + 1;
                            }
                            i3 = i4 + 1;
                        }
                        float f = ((float) c) + (((float) i5) / 2.0f);
                        return new n(stringBuilder.toString(), null, new o[]{new o(((float) (r4[1] + r4[0])) / 2.0f, (float) i), new o(f, (float) i)}, BarcodeFormat.CODE_93);
                    }
                } else {
                    c += obj[0] + obj[1];
                    System.arraycopy(obj, XZBDevice.DOWNLOAD_LIST_RECYCLE, obj, 0, length - 2);
                    obj[length - 2] = null;
                    obj[length - 1] = null;
                    i4--;
                }
                obj[i4] = 1;
                i3 = i3 == 0 ? 1 : 0;
            }
        }
        throw j.a();
    }
}
