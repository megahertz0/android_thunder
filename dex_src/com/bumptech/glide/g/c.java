package com.bumptech.glide.g;

import com.bumptech.glide.load.b;
import com.sina.weibo.sdk.component.GameManager;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

// compiled from: StringSignature.java
public final class c implements b {
    private final String a;

    public c(String str) {
        if (str == null) {
            throw new NullPointerException("Signature cannot be null!");
        }
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((c) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.a.getBytes(GameManager.DEFAULT_CHARSET));
    }

    public final String toString() {
        return new StringBuilder("StringSignature{signature='").append(this.a).append('\'').append('}').toString();
    }
}
