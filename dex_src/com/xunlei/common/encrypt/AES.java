package com.xunlei.common.encrypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.logging.impl.SimpleLog;

public class AES {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] encrypt(byte[] r3, byte[] r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.encrypt.AES.encrypt(byte[], byte[]):byte[]");
        /*
        r0 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r1 = "AES";
        r0.<init>(r4, r1);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r1 = "AES/ECB/NoPadding";
        r1 = javax.crypto.Cipher.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r2 = 1;
        r1.init(r2, r0);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r0 = r1.doFinal(r3);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x001c:
        r0 = 0;
        goto L_0x0017;
    L_0x001e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x0023:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x0028:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x002d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
        */
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, boolean z, boolean z2) {
        try {
            String str = "AES";
            if (z2) {
                str = str + "/ECB";
            }
            if (z) {
                str = str + "/NoPadding";
            }
            Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance(str);
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return null;
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decrypt(byte[] r3, byte[] r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.encrypt.AES.decrypt(byte[], byte[]):byte[]");
        /*
        r0 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r1 = "AES";
        r0.<init>(r4, r1);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r1 = "AES/ECB/NoPadding";
        r1 = javax.crypto.Cipher.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r2 = 2;
        r1.init(r2, r0);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
        r0 = r1.doFinal(r3);	 Catch:{ NoSuchAlgorithmException -> 0x0018, NoSuchPaddingException -> 0x001e, InvalidKeyException -> 0x0023, IllegalBlockSizeException -> 0x0028, BadPaddingException -> 0x002d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x001c:
        r0 = 0;
        goto L_0x0017;
    L_0x001e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x0023:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x0028:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x002d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
        */
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, boolean z, boolean z2) {
        try {
            String str = "AES";
            if (z2) {
                str = str + "/ECB";
            }
            if (z) {
                str = str + "/NoPadding";
            }
            Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance(str);
            instance.init(SimpleLog.LOG_LEVEL_DEBUG, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return null;
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static byte[] getRawKey(int i, byte[] bArr) throws Exception {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        SecureRandom instance2 = SecureRandom.getInstance("SHA1PRNG");
        instance2.setSeed(bArr);
        instance.init(i, instance2);
        return instance.generateKey().getEncoded();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] encrypt(java.lang.String r4, java.lang.String r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.encrypt.AES.encrypt(java.lang.String, java.lang.String):byte[]");
        /*
        r0 = "AES";
        r0 = javax.crypto.KeyGenerator.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r1 = "SHA1PRNG";
        r2 = "Crypto";
        r1 = java.security.SecureRandom.getInstance(r1, r2);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r2 = r5.getBytes();	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r1.setSeed(r2);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0.init(r2, r1);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r0 = r0.generateKey();	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r0 = r0.getEncoded();	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r1 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r2 = "AES";
        r1.<init>(r0, r2);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r0 = "AES";
        r0 = javax.crypto.Cipher.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r2 = "UTF-8";
        r2 = r4.getBytes(r2);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r3 = 1;
        r0.init(r3, r1);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
        r0 = r0.doFinal(r2);	 Catch:{ NoSuchAlgorithmException -> 0x0044, NoSuchPaddingException -> 0x004a, InvalidKeyException -> 0x004f, IllegalBlockSizeException -> 0x0054, BadPaddingException -> 0x0059, UnsupportedEncodingException -> 0x005e, NoSuchProviderException -> 0x0063 }
    L_0x0043:
        return r0;
    L_0x0044:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0048:
        r0 = 0;
        goto L_0x0043;
    L_0x004a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0048;
    L_0x004f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0048;
    L_0x0054:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0048;
    L_0x0059:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0048;
    L_0x005e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0048;
    L_0x0063:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0048;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decrypt(byte[] r3, java.lang.String r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.encrypt.AES.decrypt(byte[], java.lang.String):byte[]");
        /*
        r0 = "AES";
        r0 = javax.crypto.KeyGenerator.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r1 = "SHA1PRNG";
        r2 = "Crypto";
        r1 = java.security.SecureRandom.getInstance(r1, r2);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r2 = r4.getBytes();	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r1.setSeed(r2);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0.init(r2, r1);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r0 = r0.generateKey();	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r0 = r0.getEncoded();	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r1 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r2 = "AES";
        r1.<init>(r0, r2);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r0 = "AES";
        r0 = javax.crypto.Cipher.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r2 = 2;
        r0.init(r2, r1);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
        r0 = r0.doFinal(r3);	 Catch:{ NoSuchAlgorithmException -> 0x003d, NoSuchPaddingException -> 0x0043, InvalidKeyException -> 0x0048, IllegalBlockSizeException -> 0x004d, BadPaddingException -> 0x0052, NoSuchProviderException -> 0x0057 }
    L_0x003c:
        return r0;
    L_0x003d:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0041:
        r0 = 0;
        goto L_0x003c;
    L_0x0043:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0041;
    L_0x0048:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0041;
    L_0x004d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0041;
    L_0x0052:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0041;
    L_0x0057:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0041;
        */
    }

    public static void main(String[] strArr) {
        String str = "test";
        String str2 = "12345678";
        System.out.println(new StringBuilder("\u52a0\u5bc6\u524d\uff1a").append(str).toString());
        System.out.println(new StringBuilder("\u89e3\u5bc6\u540e\uff1a").append(new String(decrypt(encrypt(str.getBytes(), str2.getBytes()), str2.getBytes()))).toString());
    }
}
