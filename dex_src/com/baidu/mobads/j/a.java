package com.baidu.mobads.j;

import android.text.TextUtils;
import com.baidu.mobads.interfaces.utils.IBase64;

public class a implements IBase64 {
    private static final byte[] a;
    private static final byte[] b;

    static {
        a = new byte[]{(byte) 48, (byte) 75, (byte) 97, (byte) 106, (byte) 68, (byte) 55, (byte) 65, (byte) 90, (byte) 99, (byte) 70, (byte) 50, (byte) 81, (byte) 110, (byte) 80, (byte) 114, (byte) 53, (byte) 102, (byte) 119, (byte) 105, (byte) 72, (byte) 82, (byte) 78, (byte) 121, (byte) 103, (byte) 109, (byte) 117, (byte) 112, (byte) 85, (byte) 84, (byte) 73, (byte) 88, (byte) 120, (byte) 54, (byte) 57, (byte) 66, (byte) 87, (byte) 98, (byte) 45, (byte) 104, (byte) 77, (byte) 67, (byte) 71, (byte) 74, (byte) 111, (byte) 95, (byte) 86, (byte) 56, (byte) 69, (byte) 115, (byte) 107, (byte) 122, (byte) 49, (byte) 89, (byte) 100, (byte) 118, (byte) 76, (byte) 51, (byte) 52, (byte) 108, (byte) 101, (byte) 116, (byte) 113, (byte) 83, (byte) 79};
        b = new byte[128];
        for (int i = 0; i < a.length; i++) {
            b[a[i]] = (byte) i;
        }
    }

    public String encode(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        int length = str.getBytes().length % 3;
        while (length > 0 && length < 3) {
            str = str + "$";
            length++;
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[((bytes.length / 3) * 4)];
        length = 0;
        while (length < bytes.length) {
            bArr[i] = a[(bytes[length] & 252) >> 2];
            bArr[i + 1] = a[((bytes[length] & 3) << 4) + ((bytes[length + 1] & 240) >> 4)];
            bArr[i + 2] = a[((bytes[length + 1] & 15) << 2) + ((bytes[length + 2] & 192) >> 6)];
            bArr[i + 3] = a[bytes[length + 2] & 63];
            length += 3;
            i += 4;
        }
        return new String(bArr);
    }

    private byte[] a(String str) {
        byte[] bArr = null;
        int i = 0;
        if (!(b(str) || str == null || str.length() < 4)) {
            byte b;
            byte b2;
            if (str.charAt(str.length() - 2) == '$') {
                bArr = new byte[((((str.length() / 4) - 1) * 3) + 1)];
            } else if (str.charAt(str.length() - 1) == '$') {
                bArr = new byte[((((str.length() / 4) - 1) * 3) + 2)];
            } else {
                bArr = new byte[((str.length() / 4) * 3)];
            }
            int i2 = 0;
            while (i2 < str.length() - 4) {
                b = b[str.charAt(i2)];
                b2 = b[str.charAt(i2 + 1)];
                byte b3 = b[str.charAt(i2 + 2)];
                byte b4 = b[str.charAt(i2 + 3)];
                bArr[i] = (byte) ((b << 2) | (b2 >> 4));
                bArr[i + 1] = (byte) ((b2 << 4) | (b3 >> 2));
                bArr[i + 2] = (byte) ((b3 << 6) | b4);
                i2 += 4;
                i += 3;
            }
            if (str.charAt(str.length() - 2) == '$') {
                bArr[bArr.length - 1] = (byte) ((b[str.charAt(str.length() - 4)] << 2) | (b[str.charAt(str.length() - 3)] >> 4));
            } else if (str.charAt(str.length() - 1) == '$') {
                r1 = b[str.charAt(str.length() - 4)];
                r2 = b[str.charAt(str.length() - 3)];
                b = b[str.charAt(str.length() - 2)];
                bArr[bArr.length - 2] = (byte) ((r1 << 2) | (r2 >> 4));
                bArr[bArr.length - 1] = (byte) ((r2 << 4) | (b >> 2));
            } else {
                r1 = b[str.charAt(str.length() - 4)];
                r2 = b[str.charAt(str.length() - 3)];
                b = b[str.charAt(str.length() - 2)];
                b2 = b[str.charAt(str.length() - 1)];
                bArr[bArr.length - 3] = (byte) ((r1 << 2) | (r2 >> 4));
                bArr[bArr.length - 2] = (byte) ((r2 << 4) | (b >> 2));
                bArr[bArr.length - 1] = (byte) ((b << 6) | b2);
            }
        }
        return bArr;
    }

    public String decodeStr(String str) {
        if (str == null || str.length() < 4) {
            return null;
        }
        try {
            String str2 = new String(a(str));
            while (str2.endsWith("$")) {
                str2 = str2.substring(0, str2.length() - 1);
            }
            return str2;
        } catch (Exception e) {
            return com.umeng.a.d;
        }
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!a((byte) str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean a(byte b) {
        if (b == 36) {
            return true;
        }
        if (b < null || b >= 128) {
            return false;
        }
        return b[b] != -1;
    }
}
