package com.xunlei.c;

public final class a {

    public static final class a {
        protected static final byte[] a;
        protected static final byte[] b;

        static {
            a = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
            b = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70};
        }

        public static char[] a(byte[] bArr) {
            if (bArr != null) {
                return a(bArr, bArr.length, a);
            }
            throw new IllegalArgumentException();
        }

        private static char[] a(byte[] bArr, int i, byte[] bArr2) {
            int i2 = 0;
            if (bArr == null || i < 0 || bArr.length <= 0) {
                throw new IllegalArgumentException();
            }
            char[] cArr = new char[(i * 2)];
            int i3 = 0;
            while (i2 < i + 0) {
                int i4 = i3 + 1;
                cArr[i3] = (char) bArr2[(bArr[i2] >> 4) & 15];
                i3 = i4 + 1;
                cArr[i4] = (char) bArr2[bArr[i2] & 15];
                i2++;
            }
            return cArr;
        }

        private static int a(char c) {
            if ('0' <= c && c <= '9') {
                return c - 48;
            }
            if ('a' > c || c > 'f') {
                return ('A' > c || c > 'F') ? -1 : (c + 10) - 65;
            } else {
                return (c + 10) - 97;
            }
        }

        public static byte[] a(byte[] bArr, int i) {
            if (bArr == null || i < 0 || bArr.length <= 0) {
                throw new IllegalArgumentException();
            }
            byte[] bArr2 = new byte[(bArr.length / 2)];
            int i2 = 0;
            int i3 = 0;
            while (i2 < (i + 0) - 1) {
                int a = a((char) bArr[i2]);
                int a2 = a((char) bArr[i2 + 1]);
                if (a < 0) {
                    a = 0;
                }
                if (a2 < 0) {
                    a2 = 0;
                }
                a2 = (a2 & 15) | ((a << 4) & 240);
                a = i3 + 1;
                bArr2[i3] = (byte) a2;
                i2 += 2;
                i3 = a;
            }
            return bArr2;
        }
    }
}
