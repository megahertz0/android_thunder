package com.xunlei.common.lixian.a.a;

import com.xunlei.common.encrypt.CharsetConvert;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

final class f implements Callable {
    private MessageDigest a;
    private final ByteBuffer b;

    f(ByteBuffer byteBuffer) {
        this.a = null;
        try {
            this.a = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.b = ByteBuffer.allocate(byteBuffer.remaining());
        byteBuffer.mark();
        this.b.put(byteBuffer);
        this.b.clear();
        byteBuffer.reset();
    }

    private String a() {
        this.a.reset();
        this.a.update(this.b.array());
        return new String(this.a.digest(), CharsetConvert.ISO_8859_1);
    }

    public final /* synthetic */ Object call() {
        this.a.reset();
        this.a.update(this.b.array());
        return new String(this.a.digest(), CharsetConvert.ISO_8859_1);
    }
}
