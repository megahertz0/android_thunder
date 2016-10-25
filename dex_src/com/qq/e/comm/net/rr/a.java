package com.qq.e.comm.net.rr;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.util.Base64;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class a {
    private static final byte[] a;
    private static Cipher b;
    private static Cipher c;
    private static byte[] d;

    static class a extends Exception {
        public a(String str, Throwable th) {
            super(str, th);
        }
    }

    public static class b extends Exception {
        public b(String str, Throwable th) {
            super(str, th);
        }
    }

    static {
        a = new byte[]{(byte) 91, (byte) -62};
        b = null;
        c = null;
        d = Base64.decode("4M3PpUC4Vu1uMp+Y0Mxd+vfc6v4ggJAINfgTlH74pis=", 0);
    }

    @SuppressLint({"TrulyRandom"})
    private static synchronized Cipher a() throws a {
        Cipher cipher;
        synchronized (a.class) {
            try {
                if (b != null) {
                    cipher = b;
                } else {
                    cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
                    cipher.init(1, new SecretKeySpec(d, "AES"));
                    b = cipher;
                    cipher = b;
                }
            } catch (Throwable e) {
                throw new a("Fail To Init Cipher", e);
            } catch (Throwable th) {
            }
        }
        return cipher;
    }

    public static byte[] a(byte[] bArr) throws b {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(a);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeByte(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            dataOutputStream.write(c(com.qq.e.comm.a.a(bArr)));
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new b("Exception while packaging byte array", e);
        }
    }

    private static synchronized Cipher b() throws a {
        Cipher cipher;
        synchronized (a.class) {
            try {
                if (c != null) {
                    cipher = c;
                } else {
                    cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
                    cipher.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, new SecretKeySpec(d, "AES"));
                    c = cipher;
                    cipher = c;
                }
            } catch (Throwable e) {
                throw new a("Fail To Init Cipher", e);
            } catch (Throwable th) {
            }
        }
        return cipher;
    }

    @TargetApi(9)
    public static byte[] b(byte[] bArr) throws b {
        if (bArr == null || bArr.length < 4) {
            throw new b("S2SS Package FormatError", null);
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            byte[] bArr2 = new byte[4];
            dataInputStream.read(bArr2);
            if (a[0] == bArr2[0] && a[1] == bArr2[1] && 1 == bArr2[2] && 2 == bArr2[3]) {
                return com.qq.e.comm.a.b(d(Arrays.copyOfRange(bArr, XZBDevice.DOWNLOAD_LIST_ALL, bArr.length)));
            }
            throw new b("S2SS Package Magic/Version FormatError", null);
        } catch (Throwable e) {
            throw new b("Exception while packaging byte array", e);
        }
    }

    private static byte[] c(byte[] bArr) throws a {
        try {
            return a().doFinal(bArr);
        } catch (Throwable e) {
            throw new a("Exception While encrypt byte array", e);
        }
    }

    private static byte[] d(byte[] bArr) throws a {
        try {
            return b().doFinal(bArr);
        } catch (Throwable e) {
            throw new a("Exception While dencrypt byte array", e);
        }
    }
}
