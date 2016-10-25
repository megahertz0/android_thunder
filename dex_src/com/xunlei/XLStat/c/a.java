package com.xunlei.XLStat.c;

public class a {
    private static String a;

    static {
        a = "Security";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r3, byte[] r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.XLStat.c.a.a(byte[], byte[]):byte[]");
        /*
        r0 = r4.length;
        r1 = 16;
        if (r0 == r1) goto L_0x0009;
    L_0x0005:
        r0 = 0;
        r0 = new byte[r0];
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ NoSuchAlgorithmException -> 0x0021, NoSuchPaddingException -> 0x0027, InvalidKeyException -> 0x002c, IllegalBlockSizeException -> 0x0031, BadPaddingException -> 0x0036 }
        r1 = "AES";
        r0.<init>(r4, r1);	 Catch:{ NoSuchAlgorithmException -> 0x0021, NoSuchPaddingException -> 0x0027, InvalidKeyException -> 0x002c, IllegalBlockSizeException -> 0x0031, BadPaddingException -> 0x0036 }
        r1 = "AES";
        r1 = javax.crypto.Cipher.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x0021, NoSuchPaddingException -> 0x0027, InvalidKeyException -> 0x002c, IllegalBlockSizeException -> 0x0031, BadPaddingException -> 0x0036 }
        r2 = 1;
        r1.init(r2, r0);	 Catch:{ NoSuchAlgorithmException -> 0x0021, NoSuchPaddingException -> 0x0027, InvalidKeyException -> 0x002c, IllegalBlockSizeException -> 0x0031, BadPaddingException -> 0x0036 }
        r0 = r1.doFinal(r3);	 Catch:{ NoSuchAlgorithmException -> 0x0021, NoSuchPaddingException -> 0x0027, InvalidKeyException -> 0x002c, IllegalBlockSizeException -> 0x0031, BadPaddingException -> 0x0036 }
        goto L_0x0008;
    L_0x0021:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0025:
        r0 = 0;
        goto L_0x0008;
    L_0x0027:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0025;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0025;
    L_0x0031:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0025;
    L_0x0036:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0025;
        */
    }
}
