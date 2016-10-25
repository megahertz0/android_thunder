package com.qq.e.comm.util;

import android.util.Base64;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class a {
    private PublicKey a;
    private final boolean b;

    static final class a {
        public static final a a;

        static {
            a = new a();
        }
    }

    private a() {
        boolean z;
        try {
            this.a = b("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKta2b5Vw5YkWHCAj4rJCwS227\r/35FZ29e4I6pS2B8zSq2RgBpXUuMg7oZF1Qt3x0iyg8PeyblyNeCRB6gIMehFThe\r1Y7m1FaQyaZp+CJYOTLM4/THKp9UndrEgJ/5a83vP1375YCV2lMvWARrNlBep4RN\rnESUJhQz58Gr/F39TwIDAQAB");
            z = true;
        } catch (Throwable th) {
            z = false;
        }
        this.b = z;
    }

    public static a a() {
        return a.a;
    }

    private String a(String str) {
        if (this.a != null) {
            byte[] decode = Base64.decode(str, 0);
            try {
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, this.a);
                return new String(instance.doFinal(decode), GameManager.DEFAULT_CHARSET).trim();
            } catch (Throwable th) {
                GDTLogger.e("ErrorWhileVerifySigNature", th);
            }
        }
        return null;
    }

    private static PublicKey b(String str) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("\u516c\u94a5\u975e\u6cd5");
        } catch (NullPointerException e3) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
        }
    }

    public final boolean a(String str, String str2) {
        return b(str, Md5Util.encode(str2));
    }

    public final boolean b(String str, String str2) {
        if (StringUtil.isEmpty(str2)) {
            return false;
        }
        if (!this.b) {
            return true;
        }
        String a = a(str);
        boolean equals = str2.equals(a);
        GDTLogger.d(new StringBuilder("Verify Result").append(equals).append("src=").append(str2).append(" & target=").append(a).toString());
        return equals;
    }
}
