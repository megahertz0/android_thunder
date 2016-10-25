package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.c;
import com.google.zxing.common.k;
import com.google.zxing.e;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

// compiled from: DecodedBitStreamParser.java
final class d {
    private static final char[] a;

    static {
        a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', '.', '/', ':'};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.d a(byte[] r11, com.google.zxing.qrcode.decoder.h r12, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r13, java.util.Map<com.google.zxing.DecodeHintType, ?> r14) throws com.google.zxing.e {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.d.a(byte[], com.google.zxing.qrcode.decoder.h, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.common.d");
        /*
        r0 = new com.google.zxing.common.c;
        r0.<init>(r11);
        r1 = new java.lang.StringBuilder;
        r2 = 50;
        r1.<init>(r2);
        r4 = new java.util.ArrayList;
        r2 = 1;
        r4.<init>(r2);
        r5 = -1;
        r6 = -1;
        r3 = 0;
        r2 = 0;
        r8 = r6;
        r9 = r5;
        r6 = r2;
    L_0x0019:
        r2 = r0.a();	 Catch:{ IllegalArgumentException -> 0x0068 }
        r5 = 4;
        if (r2 >= r5) goto L_0x004c;
    L_0x0020:
        r2 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x0068 }
        r7 = r2;
    L_0x0023:
        r2 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 == r2) goto L_0x012a;
    L_0x0027:
        r2 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 == r2) goto L_0x002f;
    L_0x002b:
        r2 = com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r2) goto L_0x0057;
    L_0x002f:
        r6 = 1;
        r2 = r6;
        r5 = r9;
        r6 = r8;
    L_0x0033:
        r8 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r8) goto L_0x012f;
    L_0x0037:
        r0 = new com.google.zxing.common.d;
        r2 = r1.toString();
        r1 = r4.isEmpty();
        if (r1 == 0) goto L_0x0121;
    L_0x0043:
        r3 = 0;
    L_0x0044:
        if (r13 != 0) goto L_0x0124;
    L_0x0046:
        r4 = 0;
    L_0x0047:
        r1 = r11;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        return r0;
    L_0x004c:
        r2 = 4;
        r2 = r0.a(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = com.google.zxing.qrcode.decoder.Mode.forBits(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r7 = r2;
        goto L_0x0023;
    L_0x0057:
        r2 = com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r2) goto L_0x007e;
    L_0x005b:
        r2 = r0.a();	 Catch:{ IllegalArgumentException -> 0x0068 }
        r5 = 16;
        if (r2 >= r5) goto L_0x006e;
    L_0x0063:
        r0 = com.google.zxing.e.a();	 Catch:{ IllegalArgumentException -> 0x0068 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x0068 }
    L_0x0068:
        r0 = move-exception;
        r0 = com.google.zxing.e.a();
        throw r0;
    L_0x006e:
        r2 = 8;
        r9 = r0.a(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = 8;
        r8 = r0.a(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x007e:
        r2 = com.google.zxing.qrcode.decoder.Mode.ECI;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r2) goto L_0x00c7;
    L_0x0082:
        r2 = 8;
        r2 = r0.a(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r3 = r2 & 128;
        if (r3 != 0) goto L_0x0099;
    L_0x008c:
        r2 = r2 & 127;
    L_0x008e:
        r3 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r3 != 0) goto L_0x00c2;
    L_0x0094:
        r0 = com.google.zxing.e.a();	 Catch:{ IllegalArgumentException -> 0x0068 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x0068 }
    L_0x0099:
        r3 = r2 & 192;
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r3 != r5) goto L_0x00ab;
    L_0x009f:
        r3 = 8;
        r3 = r0.a(r3);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r2 & 63;
        r2 = r2 << 8;
        r2 = r2 | r3;
        goto L_0x008e;
    L_0x00ab:
        r3 = r2 & 224;
        r5 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        if (r3 != r5) goto L_0x00bd;
    L_0x00b1:
        r3 = 16;
        r3 = r0.a(r3);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r2 & 31;
        r2 = r2 << 16;
        r2 = r2 | r3;
        goto L_0x008e;
    L_0x00bd:
        r0 = com.google.zxing.e.a();	 Catch:{ IllegalArgumentException -> 0x0068 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x0068 }
    L_0x00c2:
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x00c7:
        r2 = com.google.zxing.qrcode.decoder.Mode.HANZI;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r2) goto L_0x00e3;
    L_0x00cb:
        r2 = 4;
        r2 = r0.a(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r5 = r7.getCharacterCountBits(r12);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r5 = r0.a(r5);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r10 = 1;
        if (r2 != r10) goto L_0x00de;
    L_0x00db:
        a(r0, r1, r5);	 Catch:{ IllegalArgumentException -> 0x0068 }
    L_0x00de:
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x00e3:
        r2 = r7.getCharacterCountBits(r12);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r0.a(r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r5 = com.google.zxing.qrcode.decoder.Mode.NUMERIC;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r5) goto L_0x00f7;
    L_0x00ef:
        c(r0, r1, r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x00f7:
        r5 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r5) goto L_0x0103;
    L_0x00fb:
        a(r0, r1, r2, r6);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x0103:
        r5 = com.google.zxing.qrcode.decoder.Mode.BYTE;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r5) goto L_0x0110;
    L_0x0107:
        r5 = r14;
        a(r0, r1, r2, r3, r4, r5);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x0110:
        r5 = com.google.zxing.qrcode.decoder.Mode.KANJI;	 Catch:{ IllegalArgumentException -> 0x0068 }
        if (r7 != r5) goto L_0x011c;
    L_0x0114:
        b(r0, r1, r2);	 Catch:{ IllegalArgumentException -> 0x0068 }
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x011c:
        r0 = com.google.zxing.e.a();	 Catch:{ IllegalArgumentException -> 0x0068 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x0068 }
    L_0x0121:
        r3 = r4;
        goto L_0x0044;
    L_0x0124:
        r4 = r13.toString();
        goto L_0x0047;
    L_0x012a:
        r2 = r6;
        r5 = r9;
        r6 = r8;
        goto L_0x0033;
    L_0x012f:
        r8 = r6;
        r9 = r5;
        r6 = r2;
        goto L_0x0019;
        */
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i) throws e {
        if (i * 13 > cVar.a()) {
            throw e.a();
        }
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int a = cVar.a(XZBDevice.Upload);
            a = (a % 96) | ((a / 96) << 8);
            if (a < 959) {
                a += 41377;
            } else {
                a += 42657;
            }
            bArr[i2] = (byte) ((a >> 8) & 255);
            bArr[i2 + 1] = (byte) (a & 255);
            i--;
            i2 += 2;
        }
        try {
            stringBuilder.append(new String(bArr, "GB2312"));
        } catch (UnsupportedEncodingException e) {
            throw e.a();
        }
    }

    private static void b(c cVar, StringBuilder stringBuilder, int i) throws e {
        if (i * 13 > cVar.a()) {
            throw e.a();
        }
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int a = cVar.a(XZBDevice.Upload);
            a = (a % 192) | ((a / 192) << 8);
            if (a < 7936) {
                a += 33088;
            } else {
                a += 49472;
            }
            bArr[i2] = (byte) (a >> 8);
            bArr[i2 + 1] = (byte) a;
            i--;
            i2 += 2;
        }
        try {
            stringBuilder.append(new String(bArr, "SJIS"));
        } catch (UnsupportedEncodingException e) {
            throw e.a();
        }
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws e {
        if (i * 8 > cVar.a()) {
            throw e.a();
        }
        String a;
        Object obj = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            obj[i2] = (byte) cVar.a(XZBDevice.Wait);
        }
        if (characterSetECI == null) {
            a = k.a(obj, map);
        } else {
            a = characterSetECI.name();
        }
        try {
            stringBuilder.append(new String(obj, a));
            collection.add(obj);
        } catch (UnsupportedEncodingException e) {
            throw e.a();
        }
    }

    private static char a(int i) throws e {
        if (i < a.length) {
            return a[i];
        }
        throw e.a();
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i, boolean z) throws e {
        int length = stringBuilder.length();
        while (i > 1) {
            if (cVar.a() < 11) {
                throw e.a();
            }
            int a = cVar.a(XZBDevice.Success);
            stringBuilder.append(a(a / 45));
            stringBuilder.append(a(a % 45));
            i -= 2;
        }
        if (i == 1) {
            if (cVar.a() < 6) {
                throw e.a();
            }
            stringBuilder.append(a(cVar.a(R.styleable.Toolbar_contentInsetEnd)));
        }
        if (z) {
            while (length < stringBuilder.length()) {
                if (stringBuilder.charAt(length) == '%') {
                    if (length >= stringBuilder.length() - 1 || stringBuilder.charAt(length + 1) != '%') {
                        stringBuilder.setCharAt(length, '\u001d');
                    } else {
                        stringBuilder.deleteCharAt(length + 1);
                    }
                }
                length++;
            }
        }
    }

    private static void c(c cVar, StringBuilder stringBuilder, int i) throws e {
        while (i >= 3) {
            if (cVar.a() < 10) {
                throw e.a();
            }
            int a = cVar.a(XZBDevice.Stop);
            if (a >= 1000) {
                throw e.a();
            }
            stringBuilder.append(a(a / 100));
            stringBuilder.append(a((a / 10) % 10));
            stringBuilder.append(a(a % 10));
            i -= 3;
        }
        if (i == 2) {
            if (cVar.a() < 7) {
                throw e.a();
            }
            a = cVar.a(R.styleable.Toolbar_contentInsetLeft);
            if (a >= 100) {
                throw e.a();
            }
            stringBuilder.append(a(a / 10));
            stringBuilder.append(a(a % 10));
        } else if (i != 1) {
        } else {
            if (cVar.a() < 4) {
                throw e.a();
            }
            a = cVar.a(XZBDevice.DOWNLOAD_LIST_ALL);
            if (a >= 10) {
                throw e.a();
            }
            stringBuilder.append(a(a));
        }
    }
}
