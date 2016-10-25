package u.aly;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

// compiled from: TCompactProtocol.java
public class an extends bu {
    private static final ca d;
    private static final br f;
    private static final byte[] g;
    byte[] a;
    byte[] b;
    byte[] c;
    private x h;
    private short i;
    private br j;
    private Boolean k;
    private final long l;
    private byte[] m;

    // compiled from: TCompactProtocol.java
    public static class a implements bx {
        private final long a;

        public a() {
            this.a = -1;
        }

        public final bu a(ci ciVar) {
            return new an(ciVar, this.a);
        }
    }

    static {
        d = new ca(BuildConfig.VERSION_NAME);
        f = new br(BuildConfig.VERSION_NAME, (byte) 0, (short) 0);
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

    public an(ci ciVar, long j) {
        super(ciVar);
        this.h = new x();
        this.i = (short) 0;
        this.j = null;
        this.k = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.m = new byte[1];
        this.c = new byte[1];
        this.l = j;
    }

    public final void r() {
        this.h.a = -1;
        this.i = (short) 0;
    }

    public final void a() throws ac {
        this.h.a(this.i);
        this.i = (short) 0;
    }

    public final void b() throws ac {
        this.i = this.h.a();
    }

    public final void a(br brVar) throws ac {
        if (brVar.b == 2) {
            this.j = brVar;
        } else {
            a(brVar, (byte) -1);
        }
    }

    private void a(br brVar, byte b) throws ac {
        if (b == -1) {
            b = g[brVar.b];
        }
        if (brVar.c <= this.i || brVar.c - this.i > 15) {
            b(b);
            a(brVar.c);
        } else {
            b((byte) (((brVar.c - this.i) << 4) | b));
        }
        this.i = brVar.c;
    }

    public final void c() throws ac {
        b((byte) 0);
    }

    public final void a(bt btVar) throws ac {
        if (btVar.c == 0) {
            b((byte) 0);
            return;
        }
        b(btVar.c);
        b((byte) ((g[btVar.a] << 4) | g[btVar.b]));
    }

    public final void a(bs bsVar) throws ac {
        byte b = bsVar.a;
        int i = bsVar.b;
        if (i <= 14) {
            b((byte) (g[b] | (i << 4)));
            return;
        }
        b((byte) (g[b] | 240));
        b(i);
    }

    public final void a(boolean z) throws ac {
        byte b = (byte) 1;
        if (this.j != null) {
            br brVar = this.j;
            if (!z) {
                b = (byte) 2;
            }
            a(brVar, b);
            this.j = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        b(b);
    }

    public final void a(byte b) throws ac {
        b(b);
    }

    public final void a(short s) throws ac {
        b(c((int) s));
    }

    public final void a(int i) throws ac {
        b(c(i));
    }

    public final void a(long j) throws ac {
        long j2 = (j << 1) ^ (j >> 63);
        int i = 0;
        while ((-128 & j2) != 0) {
            int i2 = i + 1;
            this.b[i] = (byte) ((int) ((127 & j2) | 128));
            j2 >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.b[i] = (byte) ((int) j2);
        this.e.b(this.b, 0, i3);
    }

    public final void a(double d) throws ac {
        byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        long doubleToLongBits = Double.doubleToLongBits(d);
        bArr[0] = (byte) ((int) (doubleToLongBits & 255));
        bArr[1] = (byte) ((int) ((doubleToLongBits >> 8) & 255));
        bArr[2] = (byte) ((int) ((doubleToLongBits >> 16) & 255));
        bArr[3] = (byte) ((int) ((doubleToLongBits >> 24) & 255));
        bArr[4] = (byte) ((int) ((doubleToLongBits >> 32) & 255));
        bArr[5] = (byte) ((int) ((doubleToLongBits >> 40) & 255));
        bArr[6] = (byte) ((int) ((doubleToLongBits >> 48) & 255));
        bArr[7] = (byte) ((int) ((doubleToLongBits >> 56) & 255));
        this.e.a(bArr);
    }

    public final void a(String str) throws ac {
        try {
            byte[] bytes = str.getBytes(CharsetConvert.UTF_8);
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new ac("UTF-8 not supported!");
        }
    }

    public final void a(ByteBuffer byteBuffer) throws ac {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i, int i2) throws ac {
        b(i2);
        this.e.b(bArr, i, i2);
    }

    private void b(int i) throws ac {
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

    private static int c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private void b(byte b) throws ac {
        this.m[0] = b;
        this.e.a(this.m);
    }

    public final ca d() throws ac {
        this.h.a(this.i);
        this.i = (short) 0;
        return d;
    }

    public final void e() throws ac {
        this.i = this.h.a();
    }

    public final br f() throws ac {
        byte k = k();
        if (k == null) {
            return f;
        }
        short s = (short) ((k & 240) >> 4);
        if (s == (short) 0) {
            s = l();
        } else {
            s = (short) (s + this.i);
        }
        br brVar = new br(BuildConfig.VERSION_NAME, c((byte) (k & 15)), s);
        int i = k & 15;
        if (i == 1 || i == 2) {
            i = 1;
        } else {
            Object obj = null;
        }
        if (obj != null) {
            this.k = ((byte) (k & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.i = brVar.c;
        return brVar;
    }

    public final bt g() throws ac {
        int t = t();
        int k = t == 0 ? 0 : k();
        return new bt(c((byte) (k >> 4)), c((byte) (k & 15)), t);
    }

    public final bs h() throws ac {
        byte k = k();
        int i = (k >> 4) & 15;
        if (i == 15) {
            i = t();
        }
        return new bs(c(k), i);
    }

    public final bz i() throws ac {
        return new bz(h());
    }

    public final boolean j() throws ac {
        if (this.k == null) {
            return k() == (byte) 1;
        } else {
            boolean booleanValue = this.k.booleanValue();
            this.k = null;
            return booleanValue;
        }
    }

    public final byte k() throws ac {
        if (this.e.c() > 0) {
            byte b = this.e.a()[this.e.b()];
            this.e.a(1);
            return b;
        }
        this.e.a(this.c, 1);
        return this.c[0];
    }

    public final short l() throws ac {
        return (short) e(t());
    }

    public final int m() throws ac {
        return e(t());
    }

    public final long n() throws ac {
        long j = null;
        long j2 = 0;
        if (this.e.c() < 10) {
            while (true) {
                byte k = k();
                j2 |= ((long) (k & 127)) << j;
                if ((k & 128) != 128) {
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
        return (j2 >>> 1) ^ (-(j2 & 1));
    }

    public final double o() throws ac {
        byte[] bArr = new byte[8];
        this.e.a(bArr, SpdyProtocol.PUBKEY_SEQ_ADASH);
        return Double.longBitsToDouble((((long) bArr[0]) & 255) | ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)));
    }

    public final String p() throws ac {
        int t = t();
        d(t);
        if (t == 0) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            if (this.e.c() >= t) {
                String str = new String(this.e.a(), this.e.b(), t, CharsetConvert.UTF_8);
                this.e.a(t);
                return str;
            }
            byte[] bArr;
            if (t == 0) {
                bArr = new byte[0];
            } else {
                bArr = new byte[t];
                this.e.a(bArr, t);
            }
            return new String(bArr, CharsetConvert.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new ac("UTF-8 not supported!");
        }
    }

    public final ByteBuffer q() throws ac {
        int t = t();
        d(t);
        if (t == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[t];
        this.e.a(bArr, t);
        return ByteBuffer.wrap(bArr);
    }

    private void d(int i) throws bv {
        if (i < 0) {
            throw new bv(new StringBuilder("Negative length: ").append(i).toString());
        } else if (this.l != -1 && ((long) i) > this.l) {
            throw new bv(new StringBuilder("Length exceeded max allowed: ").append(i).toString());
        }
    }

    private int t() throws ac {
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
            byte k = k();
            i2 |= (k & 127) << i;
            if ((k & 128) != 128) {
                return i2;
            }
            i += 7;
        }
    }

    private static int e(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private static byte c(byte b) throws bv {
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
                throw new bv(new StringBuilder("don't know what type: ").append((byte) (b & 15)).toString());
        }
    }
}
