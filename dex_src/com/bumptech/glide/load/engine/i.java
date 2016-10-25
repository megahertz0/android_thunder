package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.b;
import com.sina.weibo.sdk.component.GameManager;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

// compiled from: OriginalKey.java
final class i implements b {
    private final String a;
    private final b b;

    public i(String str, b bVar) {
        this.a = str;
        this.b = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        if (this.a.equals(iVar.a)) {
            return this.b.equals(iVar.b);
        } else {
            return false;
        }
    }

    public final int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public final void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.a.getBytes(GameManager.DEFAULT_CHARSET));
        this.b.a(messageDigest);
    }
}
