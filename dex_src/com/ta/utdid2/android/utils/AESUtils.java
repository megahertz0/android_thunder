package com.ta.utdid2.android.utils;

import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    public static final String TAG = "AESUtils";
    public static byte[] t;

    static {
        t = new byte[]{(byte) 48, (byte) 48, (byte) 49, (byte) 55, (byte) 68, (byte) 67, (byte) 49, (byte) 66, (byte) 69, (byte) 50, (byte) 50, (byte) 53, (byte) 56, (byte) 53, (byte) 53, (byte) 52, (byte) 67, (byte) 70, (byte) 48, (byte) 50, (byte) 67, (byte) 53, (byte) 55, (byte) 66, (byte) 55, (byte) 56, (byte) 69, (byte) 55, (byte) 52, (byte) 48, (byte) 65, (byte) 53};
    }

    public static String encrypt(String str, String str2) {
        byte[] encrypt;
        try {
            encrypt = encrypt(getRawKey(str.getBytes()), str2.getBytes());
        } catch (Exception e) {
            encrypt = null;
        }
        return encrypt != null ? toHex(encrypt) : null;
    }

    public static String decrypt(String str, String str2) {
        try {
            return new String(decrypt(getRawKey(str.getBytes()), toByte(str2)));
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] getRawKey(byte[] bArr) throws Exception {
        return toByte(new String(t, 0, 32));
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    public static String toHex(String str) {
        return toHex(str.getBytes());
    }

    public static String fromHex(String str) {
        return new String(toByte(str));
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), R.styleable.Toolbar_titleMarginBottom).byteValue();
        }
        return bArr;
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return a.d;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
    }
}
