package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

// compiled from: UidEncryptionUtils.java
public class c {
    private static final String a;

    static {
        a = c.class.getSimpleName();
    }

    @SuppressLint({"TrulyRandom"})
    public static String a(String str) {
        if (str == null || a.d.equals(str)) {
            return null;
        }
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger("C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B", 16), new BigInteger("010001", 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return new String(Base64.encode(a(str.getBytes(GameManager.DEFAULT_CHARSET), 1, instance), 0));
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e);
            return null;
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e2);
            return null;
        } catch (Throwable e22) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e22);
            return null;
        } catch (Throwable e222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e222);
            return null;
        } catch (Throwable e2222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e2222);
            return null;
        } catch (Throwable e22222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e22222);
            return null;
        } catch (Throwable e222222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Erorr in uid encryption.", e222222);
            return null;
        }
    }

    private static byte[] a(byte[] bArr, int i, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2 = new byte[0];
        int length = bArr.length;
        byte[] bArr3 = bArr2;
        bArr2 = new byte[64];
        int i2 = 0;
        while (i2 < length) {
            if (i2 > 0 && i2 % 64 == 0) {
                int i3;
                bArr2 = a(bArr3, cipher.doFinal(bArr2));
                if (i2 + 64 > length) {
                    i3 = length - i2;
                } else {
                    i3 = 64;
                }
                byte[] bArr4 = bArr2;
                bArr2 = new byte[i3];
                bArr3 = bArr4;
            }
            bArr2[i2 % 64] = bArr[i2];
            i2++;
        }
        return a(bArr3, cipher.doFinal(bArr2));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        Object obj = new Object[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }
}
