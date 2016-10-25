package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// compiled from: RequestEncryptionUtils.java
public class b {
    private static final String a;
    private static String b;
    private static String c;
    private static byte[] d;

    static {
        a = b.class.getSimpleName();
        b = "AES";
        c = "AES/CBC/PKCS7Padding";
    }

    public static String a(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, String str2, String str3) {
        try {
            byte[] b = b(str.getBytes(GameManager.DEFAULT_CHARSET), bArr, bArr2);
            byte[] a = a(b, bArr3);
            b = a(b);
            a = a(a);
            return new String(Base64.encode(b(a(a(b(b(a(bArr), a(bArr3)), a(bArr2)), str3, str2)), b(b, a)), XZBDevice.Wait));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint({"TrulyRandom"})
    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Key secretKeySpec = new SecretKeySpec(bArr2, b);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher instance = Cipher.getInstance(c);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
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
        } catch (InvalidAlgorithmParameterException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static byte[] a() {
        byte[] bArr = new byte[16];
        try {
            SecureRandom.getInstance("SHA1PRNG", "Crypto").nextBytes(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e2) {
            e2.printStackTrace();
        }
        return bArr;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr2, 0, bArr2.length, "HmacSHA1");
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] bArr) {
        long length = (long) bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(XZBDevice.Wait);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putLong(length);
        Object array = allocate.array();
        Object obj = new Object[(array.length + bArr.length)];
        System.arraycopy(array, 0, obj, 0, array.length);
        System.arraycopy(bArr, 0, obj, array.length, bArr.length);
        return obj;
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(str2, 16), new BigInteger(str, 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvalidKeyException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return null;
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) {
        Object obj = new Object[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static synchronized byte[] b() {
        byte[] a;
        synchronized (b.class) {
            a aVar = new a();
            if ((System.currentTimeMillis() / 1000) - aVar.c() > 86400) {
                a = a();
                d = a;
                aVar.a(Base64.encodeToString(a, 0));
            } else if (d == null) {
                try {
                    d = Base64.decode(aVar.b(), 0);
                } catch (Throwable e) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "getAesPublicKey failed to decode the cached key.", e);
                    a = a();
                    d = a;
                    aVar.a(Base64.encodeToString(a, 0));
                }
            }
            a = d;
        }
        return a;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Key secretKeySpec = new SecretKeySpec(bArr2, b);
        try {
            Cipher instance = Cipher.getInstance(c);
            instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, secretKeySpec, new IvParameterSpec(bArr3));
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while decrypting response.", e);
            return null;
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while decrypting response.", e2);
            return null;
        } catch (Throwable e22) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while decrypting response.", e22);
            return null;
        } catch (Throwable e222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while decrypting response.", e222);
            return null;
        } catch (Throwable e2222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while decrypting response.", e2222);
            return null;
        } catch (Throwable e22222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while decrypting response.", e22222);
            return null;
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}
