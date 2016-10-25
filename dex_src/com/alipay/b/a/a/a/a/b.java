package com.alipay.b.a.a.a.a;

import com.alipay.b.a.a.a.a;
import com.sina.weibo.sdk.component.GameManager;
import java.security.MessageDigest;

public final class b {
    public static String a(String str) {
        try {
            if (a.a(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes(GameManager.DEFAULT_CHARSET));
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
