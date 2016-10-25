package com.xunlei.common.lixian.a;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class b extends InputStream {
    private byte[] a;
    private ByteArrayInputStream b;
    private byte[] c;

    public b(byte[] bArr) {
        this.a = null;
        this.b = null;
        this.c = new byte[8];
        this.a = bArr;
        this.b = new ByteArrayInputStream(bArr);
    }

    private int k() {
        return d() & 255;
    }

    private int l() {
        return c() & 65535;
    }

    private b[] m() {
        int a = a();
        if (a < 0) {
            return null;
        }
        b[] bVarArr = new b[a];
        for (int i = 0; i < a; i++) {
            bVarArr[i] = h();
        }
        return bVarArr;
    }

    public final int a() {
        int i = 0;
        Arrays.fill(this.c, (byte) 0);
        this.b.read(this.c, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        int i2 = 0;
        while (i < 4) {
            i2 |= (this.c[i] & 255) << (i << 3);
            i++;
        }
        return i2;
    }

    public final String a(String str) {
        int a = a();
        if (a == 0) {
            return BuildConfig.VERSION_NAME;
        }
        byte[] bArr = new byte[a];
        if (this.b.read(bArr, 0, a) == a) {
            return new String(bArr, str);
        }
        throw new IOException("\u8bfb\u6570\u636e\u5f02\u5e38");
    }

    public final long b() {
        int i = 0;
        Arrays.fill(this.c, (byte) 0);
        this.b.read(this.c, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        long j = 0;
        while (i < 8) {
            j |= ((long) (this.c[i] & 255)) << (i << 3);
            i++;
        }
        return j;
    }

    public final short c() {
        int i = 0;
        Arrays.fill(this.c, (byte) 0);
        this.b.read(this.c, 0, SimpleLog.LOG_LEVEL_DEBUG);
        short s = (short) 0;
        while (i < 2) {
            s = (short) (s | (((short) (this.c[i] & 255)) << (i << 3)));
            i++;
        }
        return s;
    }

    public final void close() {
        super.close();
        this.b.close();
    }

    public final byte d() {
        Arrays.fill(this.c, (byte) 0);
        this.b.read(this.c, 0, 1);
        byte b = (byte) 0;
        for (int i = 0; i <= 0; i++) {
            b = (byte) (((byte) this.c[0]) | 0);
        }
        return b;
    }

    public final String e() {
        return a(CharsetConvert.UTF_8);
    }

    public final byte[] f() {
        int a = a();
        if (a == 0) {
            return new byte[0];
        }
        if (a < 0) {
            return null;
        }
        byte[] bArr = new byte[a];
        if (this.b.read(bArr, 0, a) == a) {
            return bArr;
        }
        throw new IOException("\u8bfb\u6570\u636e\u5f02\u5e38");
    }

    public final byte[] g() {
        int available = this.b.available();
        byte[] bArr = new byte[available];
        return this.b.read(bArr, 0, available) == available ? bArr : null;
    }

    public final b h() {
        int a = a();
        byte[] bArr = new byte[a];
        if (a == 0) {
            return new b(bArr);
        }
        if (this.b.read(bArr, 0, a) == a) {
            return new b(bArr);
        }
        throw new IOException("\u8bfb\u6570\u636e\u5f02\u5e38");
    }

    public final List i() {
        List linkedList = new LinkedList();
        int a = a();
        for (int i = 0; i < a; i++) {
            linkedList.add(h());
        }
        return linkedList;
    }

    public final byte[] j() {
        return this.a;
    }

    public final int read() {
        return this.b.read();
    }
}
