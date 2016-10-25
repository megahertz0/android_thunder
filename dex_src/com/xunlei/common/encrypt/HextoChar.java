package com.xunlei.common.encrypt;

import org.android.spdy.SpdyProtocol;

public class HextoChar {
    static final byte[] HEX_DATA_MAP;

    static {
        HEX_DATA_MAP = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70};
    }

    public static byte[] byte_to_hex(byte b) {
        byte[] bArr = new byte[2];
        int i = b & 15;
        try {
            bArr[0] = HEX_DATA_MAP[(b & 240) >> 4];
            bArr[1] = HEX_DATA_MAP[i];
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public static byte[] bytes_to_hex(byte[] bArr, int i) {
        int i2 = 0;
        if (bArr == null || i <= 0) {
            return null;
        }
        byte[] bArr2 = new byte[(i << 1)];
        while (i2 < i) {
            try {
                byte[] byte_to_hex = byte_to_hex(bArr[i2]);
                bArr2[i2 * 2] = byte_to_hex[0];
                bArr2[(i2 * 2) + 1] = byte_to_hex[1];
                i2++;
            } catch (RuntimeException e) {
                e.printStackTrace();
                return bArr2;
            }
        }
        return bArr2;
    }

    public static int hex_char_value(byte b) {
        if (b >= 48 && b <= 57) {
            return b - 48;
        }
        if (b < 65 || b > 90) {
            return (b < 97 || b > 122) ? -1 : (b - 97) + 10;
        } else {
            return (b - 65) + 10;
        }
    }

    public static byte[] hex_to_bytes(byte[] bArr, int i) {
        if (i % 2 != 0) {
            return null;
        }
        int i2 = i / 2;
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            try {
                int hex_char_value = hex_char_value(bArr[i3 << 1]);
                if (hex_char_value < 0) {
                    return null;
                }
                int hex_char_value2 = hex_char_value(bArr[(i3 << 1) + 1]);
                if (hex_char_value2 < 0) {
                    return null;
                }
                bArr2[i3] = (byte) ((hex_char_value << 4) | hex_char_value2);
                i3++;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bArr2;
    }

    public static String parseByte2HexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = new StringBuilder("0").append(toHexString).toString();
            }
            stringBuffer.append(toHexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] parseHexStr2Byte(String str) {
        if (str.length() <= 0) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i << 1, (i << 1) + 1), SpdyProtocol.CUSTOM) << 4) + Integer.parseInt(str.substring((i << 1) + 1, (i << 1) + 2), SpdyProtocol.CUSTOM));
        }
        return bArr;
    }
}
