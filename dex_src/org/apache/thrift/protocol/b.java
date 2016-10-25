package org.apache.thrift.protocol;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.f;
import org.apache.thrift.transport.d;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public final class b extends f {
    private static final k d;
    private static final c f;
    private static final byte[] g;
    byte[] a;
    byte[] b;
    byte[] c;
    private org.apache.thrift.a h;
    private short i;
    private c j;
    private Boolean k;
    private byte[] l;

    public static class a implements h {
        public f a(d dVar) {
            return new b(dVar);
        }
    }

    static {
        d = new k(BuildConfig.VERSION_NAME);
        f = new c(BuildConfig.VERSION_NAME, (byte) 0, (short) 0);
        byte[] bArr = new byte[16];
        g = bArr;
        bArr[0] = (byte) 0;
        g[2] = (byte) 1;
        g[3] = (byte) 3;
        g[6] = (byte) 4;
        g[8] = (byte) 5;
        g[10] = (byte) 6;
        g[4] = (byte) 7;
        g[11] = (byte) 8;
        g[15] = (byte) 9;
        g[14] = (byte) 10;
        g[13] = (byte) 11;
        g[12] = (byte) 12;
    }

    public b(d dVar) {
        super(dVar);
        this.h = new org.apache.thrift.a(15);
        this.i = (short) 0;
        this.j = null;
        this.k = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.l = new byte[1];
        this.c = new byte[1];
    }

    private long A() {
        long j = null;
        long j2 = 0;
        if (this.e.c() < 10) {
            while (true) {
                byte r = r();
                j2 |= ((long) (r & 127)) << j;
                if ((r & 128) != 128) {
                    break;
                }
                j += 7;
            }
        } else {
            int i;
            byte[] a = this.e.a();
            int b = this.e.b();
            long j3 = 0;
            while (true) {
                byte b2 = a[b + i];
                j2 |= ((long) (b2 & 127)) << j3;
                if ((b2 & 128) != 128) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.e.a(i + 1);
        }
        return j2;
    }

    private long a(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    private void a(c cVar, byte b) {
        if (b == -1) {
            b = e(cVar.b);
        }
        if (cVar.c <= this.i || cVar.c - this.i > 15) {
            b(b);
            a(cVar.c);
        } else {
            d(((cVar.c - this.i) << 4) | b);
        }
        this.i = cVar.c;
    }

    private void a(byte[] bArr, int i, int i2) {
        b(i2);
        this.e.b(bArr, i, i2);
    }

    private void b(byte b) {
        this.l[0] = b;
        this.e.b(this.l);
    }

    private void b(int i) {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.a[i2] = (byte) i;
        this.e.b(this.a, 0, i4);
    }

    private void b(long j) {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.b[i] = (byte) ((int) j);
        this.e.b(this.b, 0, i3);
    }

    private int c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private long c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private boolean c(byte b) {
        int i = b & 15;
        return i == 1 || i == 2;
    }

    private byte d(byte b) {
        switch ((byte) (b & 15)) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return (byte) 0;
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                return MqttWireMessage.MESSAGE_TYPE_CONNACK;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return MqttWireMessage.MESSAGE_TYPE_PUBLISH;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return MqttWireMessage.MESSAGE_TYPE_PUBREL;
            case SimpleLog.LOG_LEVEL_ERROR:
                return MqttWireMessage.MESSAGE_TYPE_SUBSCRIBE;
            case SimpleLog.LOG_LEVEL_FATAL:
                return MqttWireMessage.MESSAGE_TYPE_UNSUBSCRIBE;
            case SimpleLog.LOG_LEVEL_OFF:
                return MqttWireMessage.MESSAGE_TYPE_PUBACK;
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                return MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                return (byte) 15;
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                return MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                return MqttWireMessage.MESSAGE_TYPE_PINGRESP;
            case R.styleable.Toolbar_titleMargins:
                return MqttWireMessage.MESSAGE_TYPE_PINGREQ;
            default:
                throw new g(new StringBuilder("don't know what type: ").append((byte) (b & 15)).toString());
        }
    }

    private long d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private void d(int i) {
        b((byte) i);
    }

    private byte e(byte b) {
        return g[b];
    }

    private byte[] e(int i) {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.e.d(bArr, 0, i);
        return bArr;
    }

    private int f(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private int z() {
        int i = 0;
        int i2;
        if (this.e.c() >= 5) {
            byte[] a = this.e.a();
            int b = this.e.b();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = a[b + i];
                i3 |= (b2 & 127) << i2;
                if ((b2 & 128) == 128) {
                    i2 += 7;
                    i++;
                } else {
                    this.e.a(i + 1);
                    return i3;
                }
            }
        }
        i2 = 0;
        while (true) {
            byte r = r();
            i2 |= (r & 127) << i;
            if ((r & 128) != 128) {
                return i2;
            }
            i += 7;
        }
    }

    public final void a() {
        this.i = this.h.a();
    }

    public final void a(byte b) {
        b(b);
    }

    protected final void a(byte b, int i) {
        if (i <= 14) {
            d((i << 4) | e(b));
            return;
        }
        d(e(b) | 240);
        b(i);
    }

    public final void a(int i) {
        b(c(i));
    }

    public final void a(long j) {
        b(c(j));
    }

    public final void a(String str) {
        try {
            byte[] bytes = str.getBytes(CharsetConvert.UTF_8);
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new f("UTF-8 not supported!");
        }
    }

    public final void a(ByteBuffer byteBuffer) {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset());
    }

    public final void a(c cVar) {
        if (cVar.b == 2) {
            this.j = cVar;
        } else {
            a(cVar, (byte) -1);
        }
    }

    public final void a(d dVar) {
        a(dVar.a, dVar.b);
    }

    public final void a(e eVar) {
        if (eVar.c == 0) {
            d(0);
            return;
        }
        b(eVar.c);
        d((e(eVar.a) << 4) | e(eVar.b));
    }

    public final void a(j jVar) {
        a(jVar.a, jVar.b);
    }

    public final void a(k kVar) {
        this.h.a(this.i);
        this.i = (short) 0;
    }

    public final void a(short s) {
        b(c((int) s));
    }

    public final void a(boolean z) {
        byte b = (byte) 1;
        if (this.j != null) {
            c cVar = this.j;
            if (!z) {
                b = (byte) 2;
            }
            a(cVar, b);
            this.j = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        b(b);
    }

    public final void b() {
    }

    public final void c() {
        b((byte) 0);
    }

    public final void d() {
    }

    public final void e() {
    }

    public final void f() {
    }

    public final k g() {
        this.h.a(this.i);
        this.i = (short) 0;
        return d;
    }

    public final void h() {
        this.i = this.h.a();
    }

    public final c i() {
        byte r = r();
        if (r == null) {
            return f;
        }
        short s = (short) ((r & 240) >> 4);
        c cVar = new c(BuildConfig.VERSION_NAME, d((byte) (r & 15)), s == (short) 0 ? s() : (short) (s + this.i));
        if (c(r)) {
            this.k = ((byte) (r & 15)) == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.i = cVar.c;
        return cVar;
    }

    public final void j() {
    }

    public final e k() {
        int z = z();
        int r = z == 0 ? 0 : r();
        return new e(d((byte) (r >> 4)), d((byte) (r & 15)), z);
    }

    public final void l() {
    }

    public final d m() {
        byte r = r();
        int i = (r >> 4) & 15;
        if (i == 15) {
            i = z();
        }
        return new d(d(r), i);
    }

    public final void n() {
    }

    public final j o() {
        return new j(m());
    }

    public final void p() {
    }

    public final boolean q() {
        if (this.k == null) {
            return r() == (byte) 1;
        } else {
            boolean booleanValue = this.k.booleanValue();
            this.k = null;
            return booleanValue;
        }
    }

    public final byte r() {
        if (this.e.c() > 0) {
            byte b = this.e.a()[this.e.b()];
            this.e.a(1);
            return b;
        }
        this.e.d(this.c, 0, 1);
        return this.c[0];
    }

    public final short s() {
        return (short) f(z());
    }

    public final int t() {
        return f(z());
    }

    public final long u() {
        return d(A());
    }

    public final double v() {
        byte[] bArr = new byte[8];
        this.e.d(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        return Double.longBitsToDouble(a(bArr));
    }

    public final String w() {
        int z = z();
        if (z == 0) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            if (this.e.c() < z) {
                return new String(e(z), CharsetConvert.UTF_8);
            }
            String str = new String(this.e.a(), this.e.b(), z, CharsetConvert.UTF_8);
            this.e.a(z);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new f("UTF-8 not supported!");
        }
    }

    public final ByteBuffer x() {
        int z = z();
        if (z == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[z];
        this.e.d(bArr, 0, z);
        return ByteBuffer.wrap(bArr);
    }

    public final void y() {
        this.h.b();
        this.i = (short) 0;
    }
}
