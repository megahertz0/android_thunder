package com.xunlei.common.encrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.logging.impl.SimpleLog;

public final class RSATools {
    private static String RSA = null;
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    static {
        RSA = "RSA";
    }

    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    public static KeyPair generateRSAKeyPair(int i) {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(RSA);
            instance.initialize(i);
            return instance.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PublicKey getPublicKey(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static PrivateKey getPrivateKey(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static PublicKey getPublicKey(String str, String str2) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(RSA).generatePublic(new RSAPublicKeySpec(new BigInteger(str), new BigInteger(str2)));
    }

    public static PrivateKey getPrivateKey(String str, String str2) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(RSA).generatePrivate(new RSAPublicKeySpec(new BigInteger(str), new BigInteger(str2)));
    }

    public static PublicKey loadPublicKey(String str) throws Exception {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(Base64.decode(str)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("\u516c\u94a5\u975e\u6cd5");
        } catch (NullPointerException e3) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
        }
    }

    public static PrivateKey loadPrivateKey(String str) throws Exception {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("\u79c1\u94a5\u975e\u6cd5");
        } catch (NullPointerException e3) {
            throw new Exception("\u79c1\u94a5\u6570\u636e\u4e3a\u7a7a");
        }
    }

    public static PublicKey loadPublicKey(InputStream inputStream) throws Exception {
        try {
            return loadPublicKey(readKey(inputStream));
        } catch (IOException e) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u6d41\u8bfb\u53d6\u9519\u8bef");
        } catch (NullPointerException e2) {
            throw new Exception("\u516c\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
        }
    }

    public static PrivateKey loadPrivateKey(InputStream inputStream) throws Exception {
        try {
            return loadPrivateKey(readKey(inputStream));
        } catch (IOException e) {
            throw new Exception("\u79c1\u94a5\u6570\u636e\u8bfb\u53d6\u9519\u8bef");
        } catch (NullPointerException e2) {
            throw new Exception("\u79c1\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
        }
    }

    private static String readKey(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            if (readLine.charAt(0) != '-') {
                stringBuilder.append(readLine);
                stringBuilder.append('\r');
            }
        }
    }

    public static byte[] encryptData(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher instance = Cipher.getInstance(RSA);
            instance.init(1, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decryptData(byte[] bArr, PrivateKey privateKey) {
        try {
            Cipher instance = Cipher.getInstance(RSA);
            instance.init(SimpleLog.LOG_LEVEL_DEBUG, privateKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static String sign(String str, String str2, String str3) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str2)));
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initSign(generatePrivate);
            instance.update(str.getBytes(str3));
            return Base64.encode(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sign(String str, PrivateKey privateKey, String str2) {
        try {
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initSign(privateKey);
            instance.update(str.getBytes(str2));
            return Base64.encode(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verify(String str, String str2, String str3, String str4) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str3)));
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initVerify(generatePublic);
            instance.update(str.getBytes(str4));
            return instance.verify(Base64.decode(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verify(String str, String str2, PublicKey publicKey, String str3) {
        try {
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initVerify(publicKey);
            instance.update(str.getBytes(str3));
            return instance.verify(Base64.decode(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void printPublicKeyInfo(PublicKey publicKey) {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
        System.out.println("----------RSAPublicKey----------");
        System.out.println(new StringBuilder("Modulus.length=").append(rSAPublicKey.getModulus().bitLength()).toString());
        System.out.println(new StringBuilder("Modulus=").append(rSAPublicKey.getModulus().toString()).toString());
        System.out.println(new StringBuilder("PublicExponent.length=").append(rSAPublicKey.getPublicExponent().bitLength()).toString());
        System.out.println(new StringBuilder("PublicExponent=").append(rSAPublicKey.getPublicExponent().toString()).toString());
    }

    public static void printPrivateKeyInfo(PrivateKey privateKey) {
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println(new StringBuilder("Modulus.length=").append(rSAPrivateKey.getModulus().bitLength()).toString());
        System.out.println(new StringBuilder("Modulus=").append(rSAPrivateKey.getModulus().toString()).toString());
        System.out.println(new StringBuilder("PrivateExponent.length=").append(rSAPrivateKey.getPrivateExponent().bitLength()).toString());
        System.out.println(new StringBuilder("PrivatecExponent=").append(rSAPrivateKey.getPrivateExponent().toString()).toString());
    }
}
