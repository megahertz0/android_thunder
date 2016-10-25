package u.aly;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TBinaryProtocol.java
public final class am extends bu {
    private static final ca f;
    protected boolean a;
    protected boolean b;
    protected int c;
    protected boolean d;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;

    // compiled from: TBinaryProtocol.java
    public static class a implements bx {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this((byte) 0);
        }

        private a(byte b) {
            this('\u0000');
        }

        private a(char c) {
            this.a = false;
            this.b = true;
            this.a = false;
            this.b = true;
            this.c = 0;
        }

        public final bu a(ci ciVar) {
            bu amVar = new am(ciVar, this.a, this.b);
            if (this.c != 0) {
                amVar.b(this.c);
            }
            return amVar;
        }
    }

    static {
        f = new ca();
    }

    public am(ci ciVar, boolean z, boolean z2) {
        super(ciVar);
        this.a = false;
        this.b = true;
        this.d = false;
        this.g = new byte[1];
        this.h = new byte[2];
        this.i = new byte[4];
        this.j = new byte[8];
        this.k = new byte[1];
        this.l = new byte[2];
        this.m = new byte[4];
        this.n = new byte[8];
        this.a = z;
        this.b = z2;
    }

    public final void a() {
    }

    public final void b() {
    }

    public final void a(br brVar) throws ac {
        a(brVar.b);
        a(brVar.c);
    }

    public final void c() throws ac {
        a((byte) 0);
    }

    public final void a(bt btVar) throws ac {
        a(btVar.a);
        a(btVar.b);
        a(btVar.c);
    }

    public final void a(bs bsVar) throws ac {
        a(bsVar.a);
        a(bsVar.b);
    }

    public final void a(boolean z) throws ac {
        a(z ? (byte) 1 : (byte) 0);
    }

    private void a(byte b) throws ac {
        this.g[0] = b;
        this.e.b(this.g, 0, 1);
    }

    public final void a(short s) throws ac {
        this.h[0] = (byte) ((s >> 8) & 255);
        this.h[1] = (byte) (s & 255);
        this.e.b(this.h, 0, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void a(int i) throws ac {
        this.i[0] = (byte) ((i >> 24) & 255);
        this.i[1] = (byte) ((i >> 16) & 255);
        this.i[2] = (byte) ((i >> 8) & 255);
        this.i[3] = (byte) (i & 255);
        this.e.b(this.i, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public final void a(long j) throws ac {
        this.j[0] = (byte) ((int) ((j >> 56) & 255));
        this.j[1] = (byte) ((int) ((j >> 48) & 255));
        this.j[2] = (byte) ((int) ((j >> 40) & 255));
        this.j[3] = (byte) ((int) ((j >> 32) & 255));
        this.j[4] = (byte) ((int) ((j >> 24) & 255));
        this.j[5] = (byte) ((int) ((j >> 16) & 255));
        this.j[6] = (byte) ((int) ((j >> 8) & 255));
        this.j[7] = (byte) ((int) (255 & j));
        this.e.b(this.j, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void a(double d) throws ac {
        a(Double.doubleToLongBits(d));
    }

    public final void a(String str) throws ac {
        try {
            byte[] bytes = str.getBytes(CharsetConvert.UTF_8);
            a(bytes.length);
            this.e.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new ac("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public final void a(ByteBuffer byteBuffer) throws ac {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.e.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public final ca d() {
        return f;
    }

    public final void e() {
    }

    public final br f() throws ac {
        byte k = k();
        return new br(BuildConfig.VERSION_NAME, k, k == null ? (short) 0 : l());
    }

    public final bt g() throws ac {
        return new bt(k(), k(), m());
    }

    public final bs h() throws ac {
        return new bs(k(), m());
    }

    public final bz i() throws ac {
        return new bz(k(), m());
    }

    public final boolean j() throws ac {
        return k() == (byte) 1;
    }

    public final byte k() throws ac {
        if (this.e.c() > 0) {
            byte b = this.e.a()[this.e.b()];
            this.e.a(1);
            return b;
        }
        a(this.k, 1);
        return this.k[0];
    }

    public final short l() throws ac {
        byte[] bArr = this.l;
        int i = 0;
        if (this.e.c() >= 2) {
            bArr = this.e.a();
            i = this.e.b();
            this.e.a((int) SimpleLog.LOG_LEVEL_DEBUG);
        } else {
            a(this.l, SimpleLog.LOG_LEVEL_DEBUG);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public final int m() throws ac {
        byte[] bArr = this.m;
        int i = 0;
        if (this.e.c() >= 4) {
            bArr = this.e.a();
            i = this.e.b();
            this.e.a((int) MqttConnectOptions.MQTT_VERSION_3_1_1);
        } else {
            a(this.m, MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    public final long n() throws ac {
        byte[] bArr = this.n;
        int i = 0;
        if (this.e.c() >= 8) {
            bArr = this.e.a();
            i = this.e.b();
            this.e.a((int) SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            a(this.n, SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    public final double o() throws ac {
        return Double.longBitsToDouble(n());
    }

    public final String p() throws ac {
        int m = m();
        if (this.e.c() < m) {
            return c(m);
        }
        try {
            String str = new String(this.e.a(), this.e.b(), m, CharsetConvert.UTF_8);
            this.e.a(m);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new ac("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    private String c(int i) throws ac {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.e.a(bArr, i);
            return new String(bArr, CharsetConvert.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new ac("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public final ByteBuffer q() throws ac {
        int m = m();
        d(m);
        if (this.e.c() >= m) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.a(), this.e.b(), m);
            this.e.a(m);
            return wrap;
        }
        byte[] bArr = new byte[m];
        this.e.a(bArr, m);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i) throws ac {
        d(i);
        return this.e.a(bArr, i);
    }

    public final void b(int i) {
        this.c = i;
        this.d = true;
    }

    private void d(int i) throws ac {
        if (i < 0) {
            throw new bv(new StringBuilder("Negative length: ").append(i).toString());
        } else if (this.d) {
            this.c -= i;
            if (this.c < 0) {
                throw new bv(new StringBuilder("Message length exceeded: ").append(i).toString());
            }
        }
    }
}
