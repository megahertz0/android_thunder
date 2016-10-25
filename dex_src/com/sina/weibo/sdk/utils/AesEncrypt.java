package com.sina.weibo.sdk.utils;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesEncrypt {
    public static String Encrypt(String str) {
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, generateKey("Stark"));
            return enBase64(instance.doFinal(str.getBytes("utf-8")));
        } catch (Exception e) {
            LogUtil.e("Encrypt", e.getMessage());
            return null;
        }
    }

    public static String Decrypt(String str) {
        try {
            byte[] deBase64 = deBase64(str);
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, generateKey("Stark"));
            try {
                return new String(instance.doFinal(deBase64), "utf-8");
            } catch (Exception e) {
                LogUtil.e("Decrypt", e.getMessage());
                return null;
            }
        } catch (Exception e2) {
            LogUtil.e("Decrypt", e2.getMessage());
            return null;
        }
    }

    protected static Key generateKey(String str) {
        Key key = null;
        try {
            String substring = MD5.hexdigest(str).substring(XZBDevice.DOWNLOAD_LIST_RECYCLE, R.styleable.Toolbar_collapseIcon);
            if (substring == null) {
                LogUtil.v("Decrypt", "Key\u4e3a\u7a7anull");
                return null;
            } else if (substring.length() != 16) {
                LogUtil.v("Decrypt", "Key\u957f\u5ea6\u4e0d\u662f16\u4f4d");
                return null;
            } else {
                SecretKeySpec secretKeySpec = new SecretKeySpec(substring.getBytes("utf-8"), "AES");
                return secretKeySpec;
            }
        } catch (Exception e) {
            LogUtil.e("generateKey", e.getMessage());
            return key;
        }
    }

    private static String enBase64(byte[] bArr) {
        return new String(Base64.encode(bArr));
    }

    private static byte[] deBase64(String str) {
        return Base64.decode(str.getBytes());
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println(new StringBuilder("\u89e3\u5bc6\u540e\u7684\u5b57\u4e32\u662f\uff1a").append(Decrypt("u7FFIamNkcuNHMwmAa+VaIrn96etbed19m6VA0iWPoJB8tcFkKkLIgLCScynfkClC9/IRHmXbKa4+ySqhnIjK7P3TgRj0n9KGsLQIVxiT/h2HWmUZj9Bum4EJuSjfSJBVjf/78GWl4zw2EQBOTZyF/9En8JLeLv1YO1tzDwKP3+5S8nnmvV26sjHFAhyRNifFZqUAxMoO1j02c48G/C7vYisBIahWr+vGRmlpxliexdbmR3CKKTSmTmT7jGWVhv5dP5DPcYKEpchtuRnR/rrjI0Nhj3Jcz8gPCegdi+sC+nLij0IjxFf1N5xvyl8CTkbLAF/QjyHq2Y0UU1pOq+oyy88TWp/rym9+vLP5NTT+VObXbduol8UQg0PeRVuTsx4iHHROb4i+Pc5DEVAWsX6Cnn2z7B9gKph0y6JLIddzmUqyeBTzZ3rJEAW/h4JrN9X8nI3HES88cHdQn9YXP7XQrlP6qhy7Z7TKpPda44tWJhR4kZLxflu9pMkCwr2upkDquCgqdgCmeD6vzBd80U0AZphXxFw6GGrFTbkYYbRcmBfrlg5ypEyXoaDujnS15s/6y8Wkmfge/6T6L5t+CHgarkSuNUl0yrZ32R8P9wMDMKs+nzy50pkRugEUFbG8C202SN7DyexqBguwEN4QFqZh2j9vs5kpdfzNYVIxfnuivbriWvNZvRmesdrxnvmaYJH0ssWD5XzJrOeUdzJvqqzjXh8kCw90NuaIMh+5eF2c8MrP81Jh+82kdlmrD0fybdOPap5RRIe/UAIqcOIChbyjO6iDQ6G/ChShzHGwbxxQsQ22tdmOC+tJq4kAqk5Y9uPtyEV7r38nyBsfiyDWMgweIxnCfvDxIe5frXIE+OKziNGeUXuJf6FYc1Kex/ASjdE")).toString());
    }
}
