package com.xunlei.common.encrypt;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.logging.impl.SimpleLog;

public class RsaEncode {
    public static byte[] encodeUseRSA(byte[] bArr, String str, String str2) {
        try {
            return rsaEncode(getPublicKey(str, str2), bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decodeUseRSA(byte[] bArr, String str, String str2) {
        try {
            return rsaDecode(getPrivateKey(str, str2), bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void generateKeyPair() throws Exception {
        KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
        instance.initialize(1024);
        KeyPair generateKeyPair = instance.generateKeyPair();
        RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) generateKeyPair.getPrivate();
        rSAPublicKey.getPublicExponent();
        rSAPublicKey.getModulus();
        rSAPrivateKey.getPrivateExponent();
        rSAPrivateKey.getModulus();
    }

    public static PublicKey getPublicKey(String str, String str2) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(str, 16), new BigInteger(str2, 16)));
    }

    public static PrivateKey getPrivateKey(String str, String str2) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(new BigInteger(str, 16), new BigInteger(str2, 16)));
    }

    public static byte[] rsaEncode(PublicKey publicKey, byte[] bArr) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/NoPadding");
        instance.init(1, publicKey);
        return instance.doFinal(bArr);
    }

    public static byte[] rsaDecode(PrivateKey privateKey, byte[] bArr) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/NoPadding");
        instance.init(SimpleLog.LOG_LEVEL_DEBUG, privateKey);
        return instance.doFinal(bArr);
    }
}
