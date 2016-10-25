package com.google.zxing.qrcode.b;

import com.google.zxing.EncodeHintType;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.a;
import com.google.zxing.common.b.d;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.h;
import com.google.zxing.qrcode.decoder.h.b;
import com.google.zxing.r;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: Encoder.java
public final class c {
    private static final int[] a;

    static {
        a = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    }

    public static g a(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws r {
        String str2;
        int i;
        int i2;
        int charAt;
        Mode mode;
        int length;
        int i3 = 0;
        if (map == null) {
            str2 = null;
        } else {
            str2 = (String) map.get(EncodeHintType.CHARACTER_SET);
        }
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        if (!"Shift_JIS".equals(str2)) {
            i = 0;
            i2 = 0;
            for (int i4 = 0; i4 < str.length(); i4++) {
                charAt = str.charAt(i4);
                if (charAt < '0' || charAt > '9') {
                    if (a(charAt) == -1) {
                        mode = Mode.BYTE;
                        break;
                    }
                    i = 1;
                } else {
                    i2 = 1;
                }
            }
            if (i != 0) {
                mode = Mode.ALPHANUMERIC;
            } else if (i2 != 0) {
                mode = Mode.NUMERIC;
            } else {
                mode = Mode.BYTE;
            }
        } else if (a(str)) {
            mode = Mode.KANJI;
        } else {
            mode = Mode.BYTE;
        }
        a aVar = new a();
        if (mode == Mode.BYTE && !"ISO-8859-1".equals(str2)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(str2);
            if (characterSetECIByName != null) {
                aVar.b(Mode.ECI.getBits(), XZBDevice.DOWNLOAD_LIST_ALL);
                aVar.b(characterSetECIByName.getValue(), XZBDevice.Wait);
            }
        }
        aVar.b(mode.getBits(), XZBDevice.DOWNLOAD_LIST_ALL);
        a aVar2 = new a();
        int charAt2;
        int a;
        switch (d.a[mode.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                length = str.length();
                while (i3 < length) {
                    charAt2 = str.charAt(i3) - 48;
                    if (i3 + 2 < length) {
                        aVar2.b(((charAt2 * 100) + ((str.charAt(i3 + 1) - 48) * 10)) + (str.charAt(i3 + 2) - 48), XZBDevice.Stop);
                        i3 += 3;
                    } else if (i3 + 1 < length) {
                        aVar2.b((charAt2 * 10) + (str.charAt(i3 + 1) - 48), R.styleable.Toolbar_contentInsetLeft);
                        i3 += 2;
                    } else {
                        aVar2.b(charAt2, XZBDevice.DOWNLOAD_LIST_ALL);
                        i3++;
                    }
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                length = str.length();
                while (i3 < length) {
                    charAt = a(str.charAt(i3));
                    if (charAt == -1) {
                        throw new r();
                    } else if (i3 + 1 < length) {
                        a = a(str.charAt(i3 + 1));
                        if (a == -1) {
                            throw new r();
                        }
                        aVar2.b((charAt * 45) + a, XZBDevice.Success);
                        i3 += 2;
                    } else {
                        aVar2.b(charAt, R.styleable.Toolbar_contentInsetEnd);
                        i3++;
                    }
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                try {
                    byte[] bytes = str.getBytes(str2);
                    charAt2 = bytes.length;
                    while (i3 < charAt2) {
                        aVar2.b(bytes[i3], XZBDevice.Wait);
                        i3++;
                    }
                } catch (Throwable e) {
                    throw new r(e);
                }
            case XZBDevice.DOWNLOAD_LIST_ALL:
                try {
                    byte[] bytes2 = str.getBytes("Shift_JIS");
                    a = bytes2.length;
                    while (i3 < a) {
                        length = ((bytes2[i3] & 255) << 8) | (bytes2[i3 + 1] & 255);
                        if (length >= 33088 && length <= 40956) {
                            length -= 33088;
                        } else if (length < 57408 || length > 60351) {
                            length = -1;
                        } else {
                            length -= 49472;
                        }
                        if (length == -1) {
                            throw new r("Invalid byte sequence");
                        }
                        aVar2.b((length & 255) + ((length >> 8) * 192), XZBDevice.Upload);
                        i3 += 2;
                    }
                } catch (Throwable e2) {
                    throw new r(e2);
                }
            default:
                throw new r(new StringBuilder("Invalid mode: ").append(mode).toString());
        }
        h a2 = a((aVar.b + mode.getCharacterCountBits(h.b(1))) + aVar2.b, errorCorrectionLevel);
        h a3 = a((mode.getCharacterCountBits(a2) + aVar.b) + aVar2.b, errorCorrectionLevel);
        a aVar3 = new a();
        aVar3.a(aVar);
        if (mode == Mode.BYTE) {
            length = aVar2.a();
        } else {
            length = str.length();
        }
        i2 = mode.getCharacterCountBits(a3);
        if (length >= (1 << i2)) {
            throw new r(length + " is bigger than " + ((1 << i2) - 1));
        }
        aVar3.b(length, i2);
        aVar3.a(aVar2);
        b a4 = a3.a(errorCorrectionLevel);
        i2 = a3.c - a4.b();
        a(i2, aVar3);
        a a5 = a(aVar3, a3.c, i2, a4.a());
        g gVar = new g();
        gVar.b = errorCorrectionLevel;
        gVar.a = mode;
        gVar.c = a3;
        i = a3.a();
        b bVar = new b(i, i);
        i = a(a5, errorCorrectionLevel, a3, bVar);
        gVar.d = i;
        f.a(a5, errorCorrectionLevel, a3, i, bVar);
        gVar.e = bVar;
        return gVar;
    }

    private static int a(int i) {
        return i < a.length ? a[i] : -1;
    }

    private static boolean a(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if (i2 < 129 || i2 > 159) {
                    if (i2 < 224 || i2 > 235) {
                        return false;
                    }
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int a(a aVar, ErrorCorrectionLevel errorCorrectionLevel, h hVar, b bVar) throws r {
        Object obj = InMobiClientPositioning.NO_REPEAT;
        int i = -1;
        int i2 = 0;
        while (i2 < 8) {
            int i3;
            f.a(aVar, errorCorrectionLevel, hVar, i2, bVar);
            int a = e.a(bVar, true) + e.a(bVar, false);
            int i4 = 0;
            byte[][] bArr = bVar.a;
            int i5 = bVar.b;
            int i6 = bVar.c;
            int i7 = 0;
            while (i7 < i6 - 1) {
                i3 = i4;
                i4 = 0;
                while (i4 < i5 - 1) {
                    byte b = bArr[i7][i4];
                    if (b == bArr[i7][i4 + 1] && b == bArr[i7 + 1][i4] && b == bArr[i7 + 1][i4 + 1]) {
                        i3++;
                    }
                    i4++;
                }
                i7++;
                i4 = i3;
            }
            a += i4 * 3;
            i4 = 0;
            bArr = bVar.a;
            i5 = bVar.b;
            i6 = bVar.c;
            i7 = 0;
            while (i7 < i6) {
                i3 = i4;
                i4 = 0;
                while (i4 < i5) {
                    byte[] bArr2 = bArr[i7];
                    if (i4 + 6 < i5 && bArr2[i4] == 1 && bArr2[i4 + 1] == null && bArr2[i4 + 2] == 1 && bArr2[i4 + 3] == 1 && bArr2[i4 + 4] == 1 && bArr2[i4 + 5] == null && bArr2[i4 + 6] == 1) {
                        if (e.a(bArr2, i4 - 4, i4) || e.a(bArr2, i4 + 7, i4 + 11)) {
                            i3++;
                        }
                    }
                    if (i7 + 6 < i6 && bArr[i7][i4] == 1 && bArr[i7 + 1][i4] == null && bArr[i7 + 2][i4] == 1 && bArr[i7 + 3][i4] == 1 && bArr[i7 + 4][i4] == 1 && bArr[i7 + 5][i4] == null && bArr[i7 + 6][i4] == 1) {
                        if (e.a(bArr, i4, i7 - 4, i7) || e.a(bArr, i4, i7 + 7, i7 + 11)) {
                            i3++;
                        }
                    }
                    i4++;
                }
                i7++;
                i4 = i3;
            }
            a += i4 * 40;
            i4 = 0;
            bArr = bVar.a;
            i5 = bVar.b;
            i6 = bVar.c;
            i7 = 0;
            while (i7 < i6) {
                bArr2 = bArr[i7];
                i3 = i4;
                for (i4 = 0; i4 < i5; i4++) {
                    if (bArr2[i4] == 1) {
                        i3++;
                    }
                }
                i7++;
                i4 = i3;
            }
            i3 = bVar.c * bVar.b;
            i3 = (((Math.abs((i4 * 2) - i3) * 10) / i3) * 10) + a;
            if (i3 < r7) {
                i = i3;
                i3 = i2;
            } else {
                i3 = i;
                i = r7;
            }
            i2++;
            int i8 = i;
            i = i3;
        }
        return i;
    }

    private static h a(int i, ErrorCorrectionLevel errorCorrectionLevel) throws r {
        for (int i2 = 1; i2 <= 40; i2++) {
            h b = h.b(i2);
            if (b.c - b.a(errorCorrectionLevel).b() >= (i + 7) / 8) {
                return b;
            }
        }
        throw new r("Data too big");
    }

    private static void a(int i, a aVar) throws r {
        int i2 = i * 8;
        if (aVar.b > i2) {
            throw new r(new StringBuilder("data bits cannot fit in the QR Code").append(aVar.b).append(" > ").append(i2).toString());
        }
        int i3;
        for (i3 = 0; i3 < 4 && aVar.b < i2; i3++) {
            aVar.a(false);
        }
        i3 = aVar.b & 7;
        if (i3 > 0) {
            while (i3 < 8) {
                aVar.a(false);
                i3++;
            }
        }
        int a = i - aVar.a();
        for (i3 = 0; i3 < a; i3++) {
            aVar.b((i3 & 1) == 0 ? 236 : R.styleable.Toolbar_maxButtonHeight, XZBDevice.Wait);
        }
        if (aVar.b != i2) {
            throw new r("Bits size does not equal capacity");
        }
    }

    private static a a(a aVar, int i, int i2, int i3) throws r {
        if (aVar.a() != i2) {
            throw new r("Number of bits and data bytes does not match");
        }
        Collection<a> arrayList = new ArrayList(i3);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i4 < i3) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            if (i4 >= i3) {
                throw new r("Block ID too large");
            }
            int i8 = i % i3;
            int i9 = i3 - i8;
            int i10 = i / i3;
            int i11 = i10 + 1;
            int i12 = i2 / i3;
            int i13 = i12 + 1;
            i10 -= i12;
            i11 -= i13;
            if (i10 != i11) {
                throw new r("EC bytes mismatch");
            } else if (i3 != i9 + i8) {
                throw new r("RS blocks mismatch");
            } else {
                if (i != (i8 * (i13 + i11)) + ((i12 + i10) * i9)) {
                    throw new r("Total bytes mismatch");
                }
                if (i4 < i9) {
                    iArr[0] = i12;
                    iArr2[0] = i10;
                } else {
                    iArr[0] = i13;
                    iArr2[0] = i11;
                }
                i8 = iArr[0];
                byte[] bArr = new byte[i8];
                aVar.a(i5 * 8, bArr, i8);
                byte[] a = a(bArr, iArr2[0]);
                arrayList.add(new a(bArr, a));
                i6 = Math.max(i6, i8);
                i4++;
                i5 = iArr[0] + i5;
                i7 = Math.max(i7, a.length);
            }
        }
        if (i2 != i5) {
            throw new r("Data bytes does not match offset");
        }
        int i14;
        a aVar2 = new a();
        for (i14 = 0; i14 < i6; i14++) {
            for (a aVar3 : arrayList) {
                byte[] bArr2 = aVar3.a;
                if (i14 < bArr2.length) {
                    aVar2.b(bArr2[i14], XZBDevice.Wait);
                }
            }
        }
        for (i14 = 0; i14 < i7; i14++) {
            for (a aVar32 : arrayList) {
                bArr2 = aVar32.b;
                if (i14 < bArr2.length) {
                    aVar2.b(bArr2[i14], XZBDevice.Wait);
                }
            }
        }
        if (i == aVar2.a()) {
            return aVar2;
        }
        throw new r(new StringBuilder("Interleaving error: ").append(i).append(" and ").append(aVar2.a()).append(" differ.").toString());
    }

    private static byte[] a(byte[] bArr, int i) {
        int i2 = 0;
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        new d(com.google.zxing.common.b.a.e).a(iArr, i);
        byte[] bArr2 = new byte[i];
        while (i2 < i) {
            bArr2[i2] = (byte) iArr[length + i2];
            i2++;
        }
        return bArr2;
    }
}
