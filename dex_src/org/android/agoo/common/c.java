package org.android.agoo.common;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: Taobao
public final class c {
    private static byte[] a;
    private static ThreadLocal<Cipher> b;
    private static final AlgorithmParameterSpec c;
    private static final SecureRandom d;

    static {
        a = new byte[]{(byte) 82, (byte) 22, (byte) 50, (byte) 44, (byte) -16, (byte) 124, (byte) -40, (byte) -114, (byte) -87, (byte) -40, (byte) 37, (byte) 23, (byte) -56, (byte) 23, (byte) -33, (byte) 75};
        b = new ThreadLocal();
        c = new IvParameterSpec(a);
        d = new SecureRandom();
    }

    public static final byte[] a(byte[] bArr, SecretKeySpec secretKeySpec, byte[] bArr2) throws IllegalArgumentException {
        try {
            return a(secretKeySpec, bArr2, (int) SimpleLog.LOG_LEVEL_DEBUG).doFinal(bArr);
        } catch (Throwable e) {
            throw new IllegalArgumentException(new StringBuilder("AES decrypt error:").append(e.getMessage()).toString(), e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException(new StringBuilder("AES decrypt error:").append(e2.getMessage()).toString(), e2);
        }
    }

    private static final Cipher a(SecretKeySpec secretKeySpec, byte[] bArr, int i) {
        Cipher a = a();
        try {
            a.init(i, secretKeySpec, new IvParameterSpec(bArr), d);
            return a;
        } catch (Throwable e) {
            throw new RuntimeException(new StringBuilder("init Chipher error:").append(e.getMessage()).toString(), e);
        } catch (Throwable e2) {
            throw new RuntimeException(new StringBuilder("init Chipher error:").append(e2.getMessage()).toString(), e2);
        } catch (Throwable e22) {
            throw new RuntimeException(new StringBuilder("init Chipher error:").append(e22.getMessage()).toString(), e22);
        }
    }

    private static final Cipher a() {
        Cipher cipher = (Cipher) b.get();
        if (cipher != null) {
            return cipher;
        }
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            b.set(cipher);
            return cipher;
        } catch (Throwable e) {
            throw new RuntimeException(new StringBuilder("get Chipher error:").append(e.getMessage()).toString(), e);
        } catch (Throwable e2) {
            throw new RuntimeException(new StringBuilder("get Chipher error:").append(e2.getMessage()).toString(), e2);
        }
    }

    public static final byte[] a(byte[] bArr) {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        return instance.digest();
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "HmacSHA1");
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(secretKeySpec);
        return instance.doFinal(bArr2);
    }

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), SpdyProtocol.CUSTOM) << 4) + Character.digit(str.charAt(i + 1), SpdyProtocol.CUSTOM));
        }
        return bArr;
    }
}
