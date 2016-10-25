package com.xiaomi.smack;

import android.os.SystemClock;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.x;
import com.xiaomi.smack.packet.f;
import com.xiaomi.smack.util.i;
import com.xiaomi.stats.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class l extends a {
    private volatile long A;
    private int B;
    public Exception q;
    protected Socket r;
    String s;
    i t;
    g u;
    private String v;
    private String w;
    private String x;
    private XMPushService y;
    private final String z;

    public l(XMPushService xMPushService, b bVar) {
        super(xMPushService, bVar);
        this.q = null;
        this.s = null;
        this.v = null;
        this.w = a.d;
        this.z = "<pf><p>t:%1$d</p></pf>";
        this.A = 0;
        this.y = xMPushService;
    }

    private void a(b bVar) {
        a(bVar.f(), bVar.e());
    }

    private void a(Exception exception) {
        if (SystemClock.elapsedRealtime() - this.A < 300000) {
            if (d.d(this.y)) {
                this.B++;
                if (this.B >= 2) {
                    String c = c();
                    b.a(new StringBuilder("max short conn time reached, sink down current host:").append(c).toString());
                    a(c, 0, exception);
                } else {
                    return;
                }
            }
            return;
        }
        this.B = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r17, int r18) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.l.a(java.lang.String, int):void");
        /*
        this = this;
        r4 = 0;
        r2 = 0;
        r0 = r16;
        r0.q = r2;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r2 = new java.lang.StringBuilder;
        r5 = "get bucket for host : ";
        r2.<init>(r5);
        r0 = r17;
        r2 = r2.append(r0);
        r2 = r2.toString();
        r2 = com.xiaomi.channel.commonutils.logger.b.e(r2);
        r5 = r2.intValue();
        r2 = r16.c(r17);
        r5 = java.lang.Integer.valueOf(r5);
        com.xiaomi.channel.commonutils.logger.b.a(r5);
        if (r2 == 0) goto L_0x0037;
    L_0x0032:
        r3 = 1;
        r3 = r2.a(r3);
    L_0x0037:
        r5 = r3.isEmpty();
        if (r5 == 0) goto L_0x0042;
    L_0x003d:
        r0 = r17;
        r3.add(r0);
    L_0x0042:
        r6 = 0;
        r0 = r16;
        r0.A = r6;
        r0 = r16;
        r5 = r0.y;
        r10 = com.xiaomi.channel.commonutils.network.d.f(r5);
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = r3.iterator();
    L_0x0059:
        r3 = r12.hasNext();
        if (r3 == 0) goto L_0x023c;
    L_0x005f:
        r3 = r12.next();
        r3 = (java.lang.String) r3;
        r14 = java.lang.System.currentTimeMillis();
        r0 = r16;
        r5 = r0.b;
        r5 = r5 + 1;
        r0 = r16;
        r0.b = r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r6 = "begin to connect to ";
        r5.<init>(r6);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r5 = r5.append(r3);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r5 = r16.v();	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r0 = r16;
        r0.r = r5;	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r0 = r16;
        r5 = r0.r;	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r6 = 0;
        r5.bind(r6);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r0 = r18;
        r5 = com.xiaomi.network.Host.b(r3, r0);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r0 = r16;
        r6 = r0.r;	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r7 = 8000; // 0x1f40 float:1.121E-41 double:3.9525E-320;
        r6.connect(r5, r7);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r5 = "tcp connected";
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r0 = r16;
        r5 = r0.r;	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r6 = 1;
        r5.setTcpNoDelay(r6);	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r0 = r16;
        r0.x = r3;	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r16.y();	 Catch:{ IOException -> 0x0110, p -> 0x017d, Throwable -> 0x01e8 }
        r9 = 1;
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r4 = r4 - r14;
        r0 = r16;
        r0.c = r4;	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r0 = r16;
        r0.m = r10;	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        if (r2 == 0) goto L_0x00d3;
    L_0x00ca:
        r0 = r16;
        r4 = r0.c;	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r6 = 0;
        r2.b(r3, r4, r6);	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
    L_0x00d3:
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r0 = r16;
        r0.A = r4;	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r5 = "connected to ";
        r4.<init>(r5);	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r4 = r4.append(r3);	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r5 = " in ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r0 = r16;
        r6 = r0.c;	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r4 = r4.append(r6);	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
        com.xiaomi.channel.commonutils.logger.b.a(r4);	 Catch:{ IOException -> 0x0236, p -> 0x0233, Throwable -> 0x022f }
    L_0x00fd:
        r2 = com.xiaomi.network.HostManager.getInstance();
        r2.persist();
        if (r9 != 0) goto L_0x022c;
    L_0x0106:
        r2 = new com.xiaomi.smack.p;
        r3 = r11.toString();
        r2.<init>(r3);
        throw r2;
    L_0x0110:
        r8 = move-exception;
        r9 = r4;
    L_0x0112:
        if (r2 == 0) goto L_0x011e;
    L_0x0114:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x022d }
        r4 = r4 - r14;
        r6 = 0;
        r2.b(r3, r4, r6, r8);	 Catch:{ all -> 0x022d }
    L_0x011e:
        r0 = r16;
        r0.q = r8;	 Catch:{ all -> 0x022d }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x022d }
        r5 = "SMACK: Could not connect to:";
        r4.<init>(r5);	 Catch:{ all -> 0x022d }
        r4 = r4.append(r3);	 Catch:{ all -> 0x022d }
        r4 = r4.toString();	 Catch:{ all -> 0x022d }
        com.xiaomi.channel.commonutils.logger.b.d(r4);	 Catch:{ all -> 0x022d }
        r4 = "SMACK: Could not connect to ";
        r4 = r11.append(r4);	 Catch:{ all -> 0x022d }
        r4 = r4.append(r3);	 Catch:{ all -> 0x022d }
        r5 = " port:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x022d }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x022d }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x022d }
        r5 = r8.getMessage();	 Catch:{ all -> 0x022d }
        r4 = r4.append(r5);	 Catch:{ all -> 0x022d }
        r5 = "\n";
        r4.append(r5);	 Catch:{ all -> 0x022d }
        if (r9 != 0) goto L_0x0179;
    L_0x0164:
        r0 = r16;
        r4 = r0.q;
        com.xiaomi.stats.g.a(r3, r4);
        r0 = r16;
        r3 = r0.y;
        r3 = com.xiaomi.channel.commonutils.network.d.f(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 == 0) goto L_0x00fd;
    L_0x0179:
        r3 = r9;
    L_0x017a:
        r4 = r3;
        goto L_0x0059;
    L_0x017d:
        r8 = move-exception;
        r9 = r4;
    L_0x017f:
        if (r2 == 0) goto L_0x018b;
    L_0x0181:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x022d }
        r4 = r4 - r14;
        r6 = 0;
        r2.b(r3, r4, r6, r8);	 Catch:{ all -> 0x022d }
    L_0x018b:
        r0 = r16;
        r0.q = r8;	 Catch:{ all -> 0x022d }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x022d }
        r5 = "SMACK: Could not connect to:";
        r4.<init>(r5);	 Catch:{ all -> 0x022d }
        r4 = r4.append(r3);	 Catch:{ all -> 0x022d }
        r4 = r4.toString();	 Catch:{ all -> 0x022d }
        com.xiaomi.channel.commonutils.logger.b.d(r4);	 Catch:{ all -> 0x022d }
        r4 = "SMACK: Could not connect to ";
        r4 = r11.append(r4);	 Catch:{ all -> 0x022d }
        r4 = r4.append(r3);	 Catch:{ all -> 0x022d }
        r5 = " port:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x022d }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x022d }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x022d }
        r5 = r8.getMessage();	 Catch:{ all -> 0x022d }
        r4 = r4.append(r5);	 Catch:{ all -> 0x022d }
        r5 = "\n";
        r4.append(r5);	 Catch:{ all -> 0x022d }
        if (r9 != 0) goto L_0x0179;
    L_0x01d1:
        r0 = r16;
        r4 = r0.q;
        com.xiaomi.stats.g.a(r3, r4);
        r0 = r16;
        r3 = r0.y;
        r3 = com.xiaomi.channel.commonutils.network.d.f(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 != 0) goto L_0x0179;
    L_0x01e6:
        goto L_0x00fd;
    L_0x01e8:
        r5 = move-exception;
    L_0x01e9:
        r6 = new java.lang.Exception;	 Catch:{ all -> 0x0212 }
        r7 = "abnormal exception";
        r6.<init>(r7, r5);	 Catch:{ all -> 0x0212 }
        r0 = r16;
        r0.q = r6;	 Catch:{ all -> 0x0212 }
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ all -> 0x0212 }
        if (r4 != 0) goto L_0x0239;
    L_0x01fa:
        r0 = r16;
        r5 = r0.q;
        com.xiaomi.stats.g.a(r3, r5);
        r0 = r16;
        r3 = r0.y;
        r3 = com.xiaomi.channel.commonutils.network.d.f(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 != 0) goto L_0x0239;
    L_0x020f:
        r9 = r4;
        goto L_0x00fd;
    L_0x0212:
        r2 = move-exception;
        r9 = r4;
    L_0x0214:
        if (r9 != 0) goto L_0x022b;
    L_0x0216:
        r0 = r16;
        r4 = r0.q;
        com.xiaomi.stats.g.a(r3, r4);
        r0 = r16;
        r3 = r0.y;
        r3 = com.xiaomi.channel.commonutils.network.d.f(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 == 0) goto L_0x00fd;
    L_0x022b:
        throw r2;
    L_0x022c:
        return;
    L_0x022d:
        r2 = move-exception;
        goto L_0x0214;
    L_0x022f:
        r4 = move-exception;
        r5 = r4;
        r4 = r9;
        goto L_0x01e9;
    L_0x0233:
        r8 = move-exception;
        goto L_0x017f;
    L_0x0236:
        r8 = move-exception;
        goto L_0x0112;
    L_0x0239:
        r3 = r4;
        goto L_0x017a;
    L_0x023c:
        r9 = r4;
        goto L_0x00fd;
        */
    }

    private void a(String str, long j, Exception exception) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(b.b(), false);
        if (fallbacksByHost != null) {
            fallbacksByHost.b(str, j, 0, exception);
            HostManager.getInstance().persist();
        }
    }

    private synchronized void y() {
        z();
        this.t = new i(this);
        this.u = new g(this);
        if (this.o.g()) {
            a(this.i.c(), null);
            if (this.i.d() != null) {
                b(this.i.d(), null);
            }
        }
        this.p.a((int) XZBDevice.Stop);
        this.p.h();
        b.a("begin to openstream...");
        this.t.c();
        this.u.b();
    }

    private void z() {
        try {
            this.j = new BufferedReader(new InputStreamReader(this.r.getInputStream(), GameManager.DEFAULT_CHARSET), 4096);
            this.k = new BufferedWriter(new OutputStreamWriter(this.r.getOutputStream(), GameManager.DEFAULT_CHARSET));
            if (this.j != null && this.k != null) {
                g();
            }
        } catch (Throwable e) {
            throw new p("Error to init reader and writer", e);
        }
    }

    public void a(int i, Exception exception) {
        this.y.a(new n(this, 2, i, exception));
    }

    public synchronized void a(x.b bVar) {
        new k().a(bVar, s(), this);
    }

    public void a(com.xiaomi.smack.packet.d dVar) {
        if (this.t != null) {
            this.t.a(dVar);
            return;
        }
        throw new p("the writer is null.");
    }

    public void a(f fVar, int i, Exception exception) {
        b(fVar, i, exception);
        if ((exception != null || i == 18) && this.A != 0) {
            a(exception);
        }
    }

    public synchronized void a(String str, String str2) {
        com.xiaomi.smack.packet.d fVar = new f(f.b.b);
        fVar.l(str);
        fVar.n(str2);
        if (this.t != null) {
            this.t.a(fVar);
        }
    }

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        for (com.xiaomi.smack.packet.d dVar : dVarArr) {
            a(dVar);
        }
    }

    protected synchronized void b(f fVar, int i, Exception exception) {
        if (o() != 2) {
            a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i, exception);
            this.l = a.d;
            if (this.u != null) {
                this.u.c();
                this.u.d();
                this.u = null;
            }
            if (this.t != null) {
                try {
                    this.t.b();
                } catch (Throwable e) {
                    b.a(e);
                }
                this.t.a();
                this.t = null;
            }
            try {
                this.r.close();
            } catch (Throwable th) {
            }
            if (this.j != null) {
                try {
                    this.j.close();
                } catch (Throwable th2) {
                }
                this.j = null;
            }
            if (this.k != null) {
                try {
                    this.k.close();
                } catch (Throwable th3) {
                }
                this.k = null;
            }
            this.d = 0;
            this.e = 0;
        }
    }

    public void b(String str) {
        this.w = str;
    }

    Fallback c(String str) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(str, false);
        if (!fallbacksByHost.b()) {
            i.a(new o(this, str));
        }
        this.f = 0;
        try {
            byte[] address = InetAddress.getByName(fallbacksByHost.f).getAddress();
            this.f = address[0] & 255;
            this.f |= (address[1] << 8) & 65280;
            this.f |= (address[2] << 16) & 16711680;
            this.f = ((address[3] << 24) & -16777216) | this.f;
        } catch (UnknownHostException e) {
        }
        return fallbacksByHost;
    }

    public String c() {
        return this.x;
    }

    public void n() {
        if (this.t != null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.t.d();
            this.y.a(new m(this, 13, currentTimeMillis), 10000);
            return;
        }
        throw new p("the packetwriter is null.");
    }

    public String s() {
        return this.l;
    }

    public synchronized void t() {
        try {
            if (i() || h()) {
                b.a("WARNING: current xmpp has connected");
            } else {
                a(0, 0, null);
                a(this.o);
            }
        } catch (Throwable e) {
            throw new p(e);
        } catch (Throwable th) {
        }
    }

    public String u() {
        String str = a.d;
        String c = g.c();
        c = c != null ? new StringBuilder("<q>").append(c).append("</q>").toString() : a.d;
        return String.format(this.w, new Object[]{str, c});
    }

    public Socket v() {
        return new Socket();
    }

    public void w() {
        this.d = SystemClock.elapsedRealtime();
    }

    public void x() {
        this.e = SystemClock.elapsedRealtime();
    }
}
