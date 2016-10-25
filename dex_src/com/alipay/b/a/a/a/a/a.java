package com.alipay.b.a.a.a.a;

public final class a {
    private static char[] a;
    private static byte[] b;

    static {
        a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
    }

    public static byte[] a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.getBytes("US-ASCII");
        int length = bytes.length;
        int i = 0;
        while (i < length) {
            while (true) {
                int i2 = i + 1;
                byte b = b[bytes[i]];
                if (i2 >= length || b != (byte) -1) {
                    break;
                }
                i = i2;
            }
            if (b == (byte) -1) {
                break;
            }
            while (true) {
                i = i2 + 1;
                byte b2 = b[bytes[i2]];
                if (i >= length || b2 != (byte) -1) {
                    break;
                }
                i2 = i;
            }
            if (b2 == (byte) -1) {
                break;
            }
            stringBuffer.append((char) ((b << 2) | ((b2 & 48) >>> 4)));
            while (true) {
                i2 = i + 1;
                byte b3 = bytes[i];
                if (b3 == (byte) 61) {
                    return stringBuffer.toString().getBytes("iso8859-1");
                }
                b = b[b3];
                if (i2 >= length || b != (byte) -1) {
                    break;
                }
                i = i2;
            }
            if (b == (byte) -1) {
                break;
            }
            stringBuffer.append((char) (((b2 & 15) << 4) | ((b & 60) >>> 2)));
            while (true) {
                i = i2 + 1;
                byte b4 = bytes[i2];
                if (b4 == (byte) 61) {
                    return stringBuffer.toString().getBytes("iso8859-1");
                }
                b4 = b[b4];
                if (i >= length || b4 != (byte) -1) {
                    break;
                }
                i2 = i;
            }
            if (b4 == (byte) -1) {
                break;
            }
            stringBuffer.append((char) (b4 | ((b & 3) << 6)));
        }
        return stringBuffer.toString().getBytes("iso8859-1");
    }
}
