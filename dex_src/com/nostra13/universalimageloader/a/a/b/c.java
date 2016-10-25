package com.nostra13.universalimageloader.a.a.b;

import anet.channel.security.ISecurity;
import com.xunlei.tdlive.R;
import java.math.BigInteger;
import java.security.MessageDigest;

// compiled from: Md5FileNameGenerator.java
public final class c implements a {
    public final String a(String str) {
        return new BigInteger(a(str.getBytes())).abs().toString(R.styleable.AppCompatTheme_actionModeShareDrawable);
    }

    private static byte[] a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(bArr);
            return instance.digest();
        } catch (Throwable e) {
            com.nostra13.universalimageloader.b.c.a(e);
            return null;
        }
    }
}
