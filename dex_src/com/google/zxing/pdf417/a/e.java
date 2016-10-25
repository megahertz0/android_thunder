package com.google.zxing.pdf417.a;

import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.d;
import com.google.zxing.pdf417.c;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.android.spdy.SpdyAgent;

// compiled from: DecodedBitStreamParser.java
final class e {
    private static final char[] a;
    private static final char[] b;
    private static final Charset c;
    private static final BigInteger[] d;

    // compiled from: DecodedBitStreamParser.java
    private enum a {
        ;

        public static int[] a() {
            return (int[]) g.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = 6;
            g = new int[]{a, b, c, d, e, f};
        }
    }

    static {
        a = new char[]{';', '<', '>', '@', '[', '\\', ']', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};
        b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', '+', '%', '*', '=', '^'};
        c = Charset.forName("ISO-8859-1");
        BigInteger[] bigIntegerArr = new BigInteger[16];
        d = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        d[1] = valueOf;
        for (int i = XZBDevice.DOWNLOAD_LIST_RECYCLE; i < d.length; i++) {
            d[i] = d[i - 1].multiply(valueOf);
        }
    }

    static d a(int[] iArr, String str) throws com.google.zxing.e {
        StringBuilder stringBuilder = new StringBuilder(iArr.length * 2);
        Charset charset = c;
        int i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        int i2 = iArr[1];
        c cVar = new c();
        while (i < iArr[0]) {
            switch (i2) {
                case XLErrorCode.OAUTH_RURI_INVALID:
                    i2 = a(iArr, i, stringBuilder);
                    break;
                case 901:
                case 924:
                    i2 = a(i2, iArr, charset, i, stringBuilder);
                    break;
                case 902:
                    i2 = b(iArr, i, stringBuilder);
                    break;
                case 913:
                    i2 = i + 1;
                    stringBuilder.append((char) iArr[i]);
                    break;
                case 922:
                case 923:
                    throw com.google.zxing.e.a();
                case 925:
                    i2 = i + 1;
                    break;
                case 926:
                    i2 = i + 2;
                    break;
                case 927:
                    i2 = i + 1;
                    charset = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i]).name());
                    break;
                case 928:
                    i2 = a(iArr, i, cVar);
                    break;
                default:
                    i2 = a(iArr, i - 1, stringBuilder);
                    break;
            }
            if (i2 < iArr.length) {
                i = i2 + 1;
                i2 = iArr[i2];
            } else {
                throw com.google.zxing.e.a();
            }
        }
        if (stringBuilder.length() == 0) {
            throw com.google.zxing.e.a();
        }
        d dVar = new d(null, stringBuilder.toString(), null, str);
        dVar.g = cVar;
        return dVar;
    }

    private static int a(int[] iArr, int i, c cVar) throws com.google.zxing.e {
        if (i + 2 > iArr[0]) {
            throw com.google.zxing.e.a();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        cVar.a = Integer.parseInt(a(iArr2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
        StringBuilder stringBuilder = new StringBuilder();
        int a = a(iArr, i, stringBuilder);
        cVar.b = stringBuilder.toString();
        if (iArr[a] == 923) {
            i2 = a + 1;
            int[] iArr3 = new int[(iArr[0] - i2)];
            int i3 = 0;
            a = i2;
            i2 = 0;
            while (a < iArr[0] && r0 == 0) {
                int i4 = a + 1;
                int i5 = iArr[a];
                if (i5 < 900) {
                    a = i3 + 1;
                    iArr3[i3] = i5;
                    i3 = a;
                    a = i4;
                } else {
                    switch (i5) {
                        case 922:
                            cVar.d = true;
                            a = i4 + 1;
                            boolean z = true;
                            break;
                        default:
                            throw com.google.zxing.e.a();
                    }
                }
            }
            cVar.c = Arrays.copyOf(iArr3, i3);
            return a;
        } else if (iArr[a] != 922) {
            return a;
        } else {
            cVar.d = true;
            return a + 1;
        }
    }

    private static int a(int[] iArr, int i, StringBuilder stringBuilder) {
        int i2;
        int i3;
        int[] iArr2 = new int[((iArr[0] - i) * 2)];
        int[] iArr3 = new int[((iArr[0] - i) * 2)];
        int i4 = 0;
        Object obj = null;
        while (i < iArr[0] && r0 == null) {
            i2 = i + 1;
            i3 = iArr[i];
            if (i3 < 900) {
                iArr2[i4] = i3 / 30;
                iArr2[i4 + 1] = i3 % 30;
                i4 += 2;
                i = i2;
            } else {
                switch (i3) {
                    case XLErrorCode.OAUTH_RURI_INVALID:
                        i3 = i4 + 1;
                        iArr2[i4] = 900;
                        i4 = i3;
                        i = i2;
                        break;
                    case 901:
                    case 902:
                    case 922:
                    case 923:
                    case 924:
                    case 928:
                        i = i2 - 1;
                        obj = 1;
                        break;
                    case 913:
                        iArr2[i4] = 913;
                        i = i2 + 1;
                        iArr3[i4] = iArr[i2];
                        i4++;
                        break;
                    default:
                        i = i2;
                        break;
                }
            }
        }
        i2 = a.a;
        i3 = a.a;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = iArr2[i5];
            char c = '\u0000';
            int i7;
            switch (f.a[i2 - 1]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (i6 < 26) {
                        c = (char) (i6 + 65);
                    } else if (i6 == 26) {
                        c = ' ';
                    } else if (i6 == 27) {
                        i2 = a.b;
                    } else if (i6 == 28) {
                        i2 = a.c;
                    } else if (i6 == 29) {
                        i7 = i2;
                        i2 = a.f;
                        i3 = i7;
                    } else if (i6 == 913) {
                        stringBuilder.append((char) iArr3[i5]);
                    } else if (i6 == 900) {
                        i2 = a.a;
                    }
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (i6 < 26) {
                        c = (char) (i6 + 97);
                    } else if (i6 == 26) {
                        c = ' ';
                    } else if (i6 == 27) {
                        i7 = i2;
                        i2 = a.e;
                        i3 = i7;
                    } else if (i6 == 28) {
                        i2 = a.c;
                    } else if (i6 == 29) {
                        i7 = i2;
                        i2 = a.f;
                        i3 = i7;
                    } else if (i6 == 913) {
                        stringBuilder.append((char) iArr3[i5]);
                    } else if (i6 == 900) {
                        i2 = a.a;
                    }
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (i6 < 25) {
                        c = b[i6];
                    } else if (i6 == 25) {
                        i2 = a.d;
                    } else if (i6 == 26) {
                        c = ' ';
                    } else if (i6 == 27) {
                        i2 = a.b;
                    } else if (i6 == 28) {
                        i2 = a.a;
                    } else if (i6 == 29) {
                        i7 = i2;
                        i2 = a.f;
                        i3 = i7;
                    } else if (i6 == 913) {
                        stringBuilder.append((char) iArr3[i5]);
                    } else if (i6 == 900) {
                        i2 = a.a;
                    }
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    if (i6 < 29) {
                        c = a[i6];
                    } else if (i6 == 29) {
                        i2 = a.a;
                    } else if (i6 == 913) {
                        stringBuilder.append((char) iArr3[i5]);
                    } else if (i6 == 900) {
                        i2 = a.a;
                    }
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (i6 < 26) {
                        c = (char) (i6 + 65);
                        i2 = i3;
                    } else if (i6 == 26) {
                        c = ' ';
                        i2 = i3;
                    } else if (i6 == 900) {
                        i2 = a.a;
                    }
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    if (i6 < 29) {
                        c = a[i6];
                        i2 = i3;
                    } else if (i6 == 29) {
                        i2 = a.a;
                    } else if (i6 == 913) {
                        stringBuilder.append((char) iArr3[i5]);
                        i2 = i3;
                    } else if (i6 == 900) {
                        i2 = a.a;
                    }
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
                default:
                    if (c == '\u0000') {
                        stringBuilder.append(c);
                    }
                    break;
            }
            i2 = i3;
            if (c == '\u0000') {
                stringBuilder.append(c);
            }
        }
        return i;
    }

    private static int a(int i, int[] iArr, Charset charset, int i2, StringBuilder stringBuilder) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3;
        int i4;
        if (i == 901) {
            i3 = 0;
            long j = 0;
            int[] iArr2 = new int[6];
            Object obj = null;
            i4 = i2 + 1;
            int i5 = iArr[i2];
            while (i4 < iArr[0] && r3 == null) {
                int i6 = i3 + 1;
                iArr2[i3] = i5;
                j = (j * 900) + ((long) i5);
                i3 = i4 + 1;
                i5 = iArr[i4];
                if (i5 == 900 || i5 == 901 || i5 == 902 || i5 == 924 || i5 == 928 || i5 == 923 || i5 == 922) {
                    i4 = i3 - 1;
                    obj = 1;
                    i3 = i6;
                } else if (i6 % 5 != 0 || i6 <= 0) {
                    i4 = i3;
                    i3 = i6;
                } else {
                    for (i4 = 0; i4 < 6; i4++) {
                        byteArrayOutputStream.write((byte) ((int) (j >> ((5 - i4) * 8))));
                    }
                    j = 0;
                    int i7 = i3;
                    i3 = 0;
                    i4 = i7;
                }
            }
            if (i4 == iArr[0] && i5 < 900) {
                int i8 = i3 + 1;
                iArr2[i3] = i5;
                i3 = i8;
            }
            for (i5 = 0; i5 < i3; i5++) {
                byteArrayOutputStream.write((byte) iArr2[i5]);
            }
            i2 = i4;
        } else if (i == 924) {
            int i9 = 0;
            long j2 = 0;
            Object obj2 = null;
            while (i2 < iArr[0] && r4 == null) {
                i4 = i2 + 1;
                i3 = iArr[i2];
                if (i3 < 900) {
                    i9++;
                    j2 = (j2 * 900) + ((long) i3);
                    i2 = i4;
                } else if (i3 == 900 || i3 == 901 || i3 == 902 || i3 == 924 || i3 == 928 || i3 == 923 || i3 == 922) {
                    i2 = i4 - 1;
                    obj2 = 1;
                } else {
                    i2 = i4;
                }
                if (i9 % 5 == 0 && i9 > 0) {
                    for (i9 = 0; i9 < 6; i9++) {
                        byteArrayOutputStream.write((byte) ((int) (j2 >> ((5 - i9) * 8))));
                    }
                    j2 = 0;
                    i9 = 0;
                }
            }
        }
        stringBuilder.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i2;
    }

    private static int b(int[] iArr, int i, StringBuilder stringBuilder) throws com.google.zxing.e {
        int[] iArr2 = new int[15];
        int i2 = 0;
        int i3 = 0;
        while (i < iArr[0] && r0 == 0) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i4 == iArr[0]) {
                i2 = 1;
            }
            if (i5 < 900) {
                iArr2[i3] = i5;
                i3++;
                i = i4;
            } else if (i5 == 900 || i5 == 901 || i5 == 924 || i5 == 928 || i5 == 923 || i5 == 922) {
                i = i4 - 1;
                i2 = 1;
            } else {
                i = i4;
            }
            if ((i3 % 15 == 0 || i5 == 902 || r0 != 0) && i3 > 0) {
                stringBuilder.append(a(iArr2, i3));
                i3 = 0;
            }
        }
        return i;
    }

    private static String a(int[] iArr, int i) throws com.google.zxing.e {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(d[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String toString = bigInteger.toString();
        if (toString.charAt(0) == '1') {
            return toString.substring(1);
        }
        throw com.google.zxing.e.a();
    }
}
