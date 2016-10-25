package org.apache.thrift.protocol;

import com.xunlei.common.encrypt.CharsetConvert;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.f;
import org.apache.thrift.transport.d;

public class l extends a {
    private static int f;
    private static int g;
    private static int h;
    private static int i;
    private static int j;

    public static class a extends org.apache.thrift.protocol.a.a {
        public a() {
            super(false, true);
        }

        public a(boolean z, boolean z2, int i) {
            super(z, z2, i);
        }

        public f a(d dVar) {
            f lVar = new l(dVar, this.a, this.b);
            if (this.c != 0) {
                lVar.c(this.c);
            }
            return lVar;
        }
    }

    static {
        f = 10000;
        g = 10000;
        h = 10000;
        i = 10485760;
        j = 104857600;
    }

    public l(d dVar, boolean z, boolean z2) {
        super(dVar, z, z2);
    }

    public e k() {
        byte r = r();
        byte r2 = r();
        int t = t();
        if (t <= f) {
            return new e(r, r2, t);
        }
        throw new g(3, new StringBuilder("Thrift map size ").append(t).append(" out of range!").toString());
    }

    public d m() {
        byte r = r();
        int t = t();
        if (t <= g) {
            return new d(r, t);
        }
        throw new g(3, new StringBuilder("Thrift list size ").append(t).append(" out of range!").toString());
    }

    public j o() {
        byte r = r();
        int t = t();
        if (t <= h) {
            return new j(r, t);
        }
        throw new g(3, new StringBuilder("Thrift set size ").append(t).append(" out of range!").toString());
    }

    public String w() {
        int t = t();
        if (t > i) {
            throw new g(3, new StringBuilder("Thrift string size ").append(t).append(" out of range!").toString());
        } else if (this.e.c() < t) {
            return b(t);
        } else {
            try {
                String str = new String(this.e.a(), this.e.b(), t, CharsetConvert.UTF_8);
                this.e.a(t);
                return str;
            } catch (UnsupportedEncodingException e) {
                throw new f("JVM DOES NOT SUPPORT UTF-8");
            }
        }
    }

    public ByteBuffer x() {
        int t = t();
        if (t > j) {
            throw new g(3, new StringBuilder("Thrift binary size ").append(t).append(" out of range!").toString());
        }
        d(t);
        if (this.e.c() >= t) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.a(), this.e.b(), t);
            this.e.a(t);
            return wrap;
        }
        byte[] bArr = new byte[t];
        this.e.d(bArr, 0, t);
        return ByteBuffer.wrap(bArr);
    }
}
