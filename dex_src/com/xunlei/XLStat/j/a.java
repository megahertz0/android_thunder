package com.xunlei.XLStat.j;

import com.xunlei.common.encrypt.CharsetConvert;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class a {
    private static final byte[] a;
    private static final byte[] b;

    static {
        a = new byte[]{Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, (byte) 62, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, (byte) 64, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE};
        b = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r15) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.XLStat.j.a.a(java.lang.String):byte[]");
        /*
        r14 = 13;
        r13 = 10;
        r12 = 2;
        r5 = 0;
        r3 = 0;
        if (r15 == 0) goto L_0x000f;
    L_0x0009:
        r0 = r15.length();	 Catch:{ Exception -> 0x00bc }
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        r0 = r5;
    L_0x0010:
        return r0;
    L_0x0011:
        r4 = r15.length();	 Catch:{ Exception -> 0x00bc }
        r9 = r15.getBytes();	 Catch:{ Exception -> 0x00bc }
        r0 = r3;
        r1 = r3;
        r2 = r3;
    L_0x001c:
        if (r2 >= r4) goto L_0x0061;
    L_0x001e:
        r6 = r4 - r2;
        if (r6 < r12) goto L_0x002c;
    L_0x0022:
        r6 = r9[r2];	 Catch:{ Exception -> 0x00bc }
        if (r6 != r14) goto L_0x002c;
    L_0x0026:
        r6 = r2 + 1;
        r6 = r9[r6];	 Catch:{ Exception -> 0x00bc }
        if (r6 == r13) goto L_0x005e;
    L_0x002c:
        r6 = r9[r2];	 Catch:{ Exception -> 0x00bc }
        if (r6 == r13) goto L_0x005e;
    L_0x0030:
        r6 = r9[r2];	 Catch:{ Exception -> 0x00bc }
        r7 = 61;
        if (r6 != r7) goto L_0x003c;
    L_0x0036:
        r1 = r1 + 1;
        if (r1 <= r12) goto L_0x003c;
    L_0x003a:
        r0 = r5;
        goto L_0x0010;
    L_0x003c:
        r6 = r9[r2];	 Catch:{ Exception -> 0x00bc }
        r7 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r6 > r7) goto L_0x004c;
    L_0x0042:
        r6 = a;	 Catch:{ Exception -> 0x00bc }
        r7 = r9[r2];	 Catch:{ Exception -> 0x00bc }
        r6 = r6[r7];	 Catch:{ Exception -> 0x00bc }
        r7 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r6 != r7) goto L_0x004e;
    L_0x004c:
        r0 = r5;
        goto L_0x0010;
    L_0x004e:
        r6 = a;	 Catch:{ Exception -> 0x00bc }
        r7 = r9[r2];	 Catch:{ Exception -> 0x00bc }
        r6 = r6[r7];	 Catch:{ Exception -> 0x00bc }
        r7 = 64;
        if (r6 >= r7) goto L_0x005c;
    L_0x0058:
        if (r1 == 0) goto L_0x005c;
    L_0x005a:
        r0 = r5;
        goto L_0x0010;
    L_0x005c:
        r0 = r0 + 1;
    L_0x005e:
        r2 = r2 + 1;
        goto L_0x001c;
    L_0x0061:
        if (r0 != 0) goto L_0x0065;
    L_0x0063:
        r0 = r5;
        goto L_0x0010;
    L_0x0065:
        r0 = r0 * 6;
        r0 = r0 + 7;
        r0 = r0 >> 3;
        r6 = new byte[r0];	 Catch:{ Exception -> 0x00bc }
        r4 = 3;
        r0 = r3;
        r7 = r3;
        r1 = r3;
        r8 = r2;
        r2 = r3;
    L_0x0073:
        if (r8 <= 0) goto L_0x00b9;
    L_0x0075:
        r10 = r9[r7];	 Catch:{ Exception -> 0x00bc }
        if (r10 == r14) goto L_0x00b4;
    L_0x0079:
        r10 = r9[r7];	 Catch:{ Exception -> 0x00bc }
        if (r10 == r13) goto L_0x00b4;
    L_0x007d:
        r10 = a;	 Catch:{ Exception -> 0x00bc }
        r11 = r9[r7];	 Catch:{ Exception -> 0x00bc }
        r10 = r10[r11];	 Catch:{ Exception -> 0x00bc }
        r11 = 64;
        if (r10 != r11) goto L_0x0089;
    L_0x0087:
        r4 = r4 + -1;
    L_0x0089:
        r0 = r0 << 6;
        r10 = a;	 Catch:{ Exception -> 0x00bc }
        r11 = r9[r7];	 Catch:{ Exception -> 0x00bc }
        r10 = r10[r11];	 Catch:{ Exception -> 0x00bc }
        r10 = r10 & 63;
        r0 = r0 | r10;
        r2 = r2 + 1;
        r10 = 4;
        if (r2 != r10) goto L_0x00b4;
    L_0x0099:
        if (r4 <= 0) goto L_0x00a2;
    L_0x009b:
        r2 = r0 >> 16;
        r2 = (byte) r2;	 Catch:{ Exception -> 0x00bc }
        r6[r1] = r2;	 Catch:{ Exception -> 0x00bc }
        r1 = r1 + 1;
    L_0x00a2:
        r2 = 1;
        if (r4 <= r2) goto L_0x00ac;
    L_0x00a5:
        r2 = r0 >> 8;
        r2 = (byte) r2;	 Catch:{ Exception -> 0x00bc }
        r6[r1] = r2;	 Catch:{ Exception -> 0x00bc }
        r1 = r1 + 1;
    L_0x00ac:
        if (r4 <= r12) goto L_0x00c3;
    L_0x00ae:
        r2 = (byte) r0;	 Catch:{ Exception -> 0x00bc }
        r6[r1] = r2;	 Catch:{ Exception -> 0x00bc }
        r1 = r1 + 1;
        r2 = r3;
    L_0x00b4:
        r8 = r8 + -1;
        r7 = r7 + 1;
        goto L_0x0073;
    L_0x00b9:
        r0 = r6;
        goto L_0x0010;
    L_0x00bc:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r5;
        goto L_0x0010;
    L_0x00c3:
        r2 = r3;
        goto L_0x00b4;
        */
    }

    public static Short a(String str, String str2) {
        int i = 0;
        if (str == null) {
            return Short.valueOf((short) -1);
        }
        try {
            byte[] a = a(str);
            if (a == null) {
                return Short.valueOf((short) -1);
            }
            int length = a.length;
            while (i < length && a[i] != null) {
                i++;
            }
            Charset forName = Charset.forName(CharsetConvert.UTF_8);
            ByteBuffer allocate = ByteBuffer.allocate(a.length);
            allocate.put(a);
            allocate.flip();
            String valueOf = String.valueOf(forName.decode(allocate).array(), 0, i);
            if (!valueOf.equals(str2)) {
                return Short.valueOf((short) -1);
            }
            return Short.valueOf((short) (((short) (((short) (a[valueOf.length() + 2] & 255)) << 8)) | ((short) (a[valueOf.length() + 1] & 255))));
        } catch (Exception e) {
            e.printStackTrace();
            return Short.valueOf((short) -1);
        }
    }
}
