package com.google.zxing.b.a;

import com.google.zxing.common.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.DecimalFormat;
import java.text.NumberFormat;

// compiled from: DecodedBitStreamParser.java
public final class b {
    private static final NumberFormat a;
    private static final NumberFormat b;
    private static final String[] c;

    static {
        a = new DecimalFormat("000000000");
        b = new DecimalFormat("000");
        c = new String[]{"\nABCDEFGHIJKLMNOPQRSTUVWXYZ\ufffa\u001c\u001d\u001e\ufffb \ufffc\"#$%&'()*+,-./0123456789:\ufff1\ufff2\ufff3\ufff4\ufff8", "`abcdefghijklmnopqrstuvwxyz\ufffa\u001c\u001d\u001e\ufffb{\ufffc}~\u007f;<=>?[\\]^_ ,./:@!|\ufffc\ufff5\ufff6\ufffc\ufff0\ufff2\ufff3\ufff4\ufff7", "\u00c0\u00c1\u00c2\u00c3\u00c4\u00c5\u00c6\u00c7\u00c8\u00c9\u00ca\u00cb\u00cc\u00cd\u00ce\u00cf\u00d0\u00d1\u00d2\u00d3\u00d4\u00d5\u00d6\u00d7\u00d8\u00d9\u00da\ufffa\u001c\u001d\u001e\u00db\u00dc\u00dd\u00de\u00df\u00aa\u00ac\u00b1\u00b2\u00b3\u00b5\u00b9\u00ba\u00bc\u00bd\u00be\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\ufff7 \ufff9\ufff3\ufff4\ufff8", "\u00e0\u00e1\u00e2\u00e3\u00e4\u00e5\u00e6\u00e7\u00e8\u00e9\u00ea\u00eb\u00ec\u00ed\u00ee\u00ef\u00f0\u00f1\u00f2\u00f3\u00f4\u00f5\u00f6\u00f7\u00f8\u00f9\u00fa\ufffa\u001c\u001d\u001e\ufffb\u00fb\u00fc\u00fd\u00fe\u00ff\u00a1\u00a8\u00ab\u00af\u00b0\u00b4\u00b7\u00b8\u00bb\u00bf\u008a\u008b\u008c\u008d\u008e\u008f\u0090\u0091\u0092\u0093\u0094\ufff7 \ufff2\ufff9\ufff4\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\ufffa\ufffc\ufffc\u001b\ufffb\u001c\u001d\u001e\u001f\u009f\u00a0\u00a2\u00a3\u00a4\u00a5\u00a6\u00a7\u00a9\u00ad\u00ae\u00b6\u0095\u0096\u0097\u0098\u0099\u009a\u009b\u009c\u009d\u009e\ufff7 \ufff2\ufff3\ufff9\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};
    }

    public static d a(byte[] bArr, int i) {
        StringBuilder stringBuilder = new StringBuilder(144);
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                String format;
                if (i == 2) {
                    format = new DecimalFormat("0000000000".substring(0, a(bArr, new byte[]{(byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 31, (byte) 32}))).format((long) a(bArr, new byte[]{(byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 25, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 1, (byte) 2}));
                } else {
                    format = String.valueOf(new char[]{c[0].charAt(a(bArr, new byte[]{(byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 31, (byte) 32})), c[0].charAt(a(bArr, new byte[]{(byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 25, (byte) 26})), c[0].charAt(a(bArr, new byte[]{(byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 19, (byte) 20})), c[0].charAt(a(bArr, new byte[]{(byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 13, (byte) 14})), c[0].charAt(a(bArr, new byte[]{(byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 7, (byte) 8})), c[0].charAt(a(bArr, new byte[]{(byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 1, (byte) 2}))});
                }
                String format2 = b.format((long) a(bArr, new byte[]{(byte) 53, (byte) 54, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 37, (byte) 38}));
                String format3 = b.format((long) a(bArr, new byte[]{(byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 49, (byte) 50, (byte) 51, (byte) 52}));
                stringBuilder.append(a(bArr, XZBDevice.Stop, R.styleable.AppCompatTheme_colorAccent));
                if (stringBuilder.toString().startsWith("[)>\u001e01\u001d")) {
                    stringBuilder.insert(XZBDevice.Pause, format + '\u001d' + format2 + '\u001d' + format3 + '\u001d');
                } else {
                    stringBuilder.insert(0, format + '\u001d' + format2 + '\u001d' + format3 + '\u001d');
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                stringBuilder.append(a(bArr, 1, R.styleable.AppCompatTheme_alertDialogCenterButtons));
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                stringBuilder.append(a(bArr, 1, R.styleable.AppCompatTheme_textAppearanceListItemSmall));
                break;
        }
        return new d(bArr, stringBuilder.toString(), null, String.valueOf(i));
    }

    private static int a(byte[] bArr, byte[] bArr2) {
        if (bArr2.length == 0) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            int i3 = bArr2[i2] - 1;
            if (((1 << (5 - (i3 % 6))) & bArr[i3 / 6]) == 0) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            i += i3 << ((bArr2.length - i2) - 1);
        }
        return i;
    }

    private static String a(byte[] bArr, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = i;
        Object obj = null;
        int i4 = 0;
        int i5 = -1;
        while (i3 < i + i2) {
            int i6;
            int i7;
            char charAt = c[i4].charAt(bArr[i3]);
            Object obj2;
            switch (charAt) {
                case '\ufff0':
                case '\ufff1':
                case '\ufff2':
                case '\ufff3':
                case '\ufff4':
                    i5 = 1;
                    i6 = i4;
                    i4 = i3;
                    i3 = charAt - 65520;
                    i7 = i6;
                    break;
                case '\ufff5':
                    i5 = 2;
                    i7 = i4;
                    i4 = i3;
                    obj2 = null;
                    break;
                case '\ufff6':
                    i5 = 3;
                    i7 = i4;
                    i4 = i3;
                    obj2 = null;
                    break;
                case '\ufff7':
                    i4 = i3;
                    i5 = -1;
                    obj2 = null;
                    break;
                case '\ufff8':
                    i4 = i3;
                    i5 = -1;
                    i3 = 1;
                    break;
                case '\ufff9':
                    i5 = -1;
                    i6 = i4;
                    i4 = i3;
                    i3 = i6;
                    break;
                case '\ufffb':
                    i3++;
                    i3++;
                    i3++;
                    i3++;
                    i3++;
                    stringBuilder.append(a.format((long) (((((bArr[i3] << 24) + (bArr[i3] << 18)) + (bArr[i3] << 12)) + (bArr[i3] << 6)) + bArr[i3])));
                    i6 = i3;
                    i3 = i4;
                    i4 = i6;
                    break;
                default:
                    stringBuilder.append(charAt);
                    i6 = i3;
                    i3 = i4;
                    i4 = i6;
                    break;
            }
            int i8 = i5 - 1;
            if (i5 == 0) {
                i3 = i7;
            }
            i5 = i8;
            i6 = i3;
            i3 = i4 + 1;
            i4 = i6;
        }
        while (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == '\ufffc') {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
