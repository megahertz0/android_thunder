package anet.channel.security;

import android.content.Context;

// compiled from: Taobao
public interface ISecurity {
    public static final String CIPHER_ALGORITHM_AES128 = "ASE128";
    public static final String CIPHER_ALGORITHM_AES256 = "ASE256";
    public static final String SIGN_ALGORITHM_HMAC_SHA1 = "HMAC_SHA1";
    public static final String SIGN_ALGORITHM_MD5 = "MD5";

    byte[] dynamicGetBytes(Context context, String str);

    boolean dynamicPutBytes(Context context, String str, byte[] bArr);

    String sign(Context context, String str, String str2, String str3, String str4);

    String signAuth(Context context, String str, String str2, String str3, String str4);

    byte[] staticDecrypt(Context context, String str, String str2, byte[] bArr, String str3);
}
