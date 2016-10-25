package com.taobao.accs.net;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.strategy.IConnStrategy;
import anet.channel.util.HttpConstant;
import anet.channel.util.Utils;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.SECURITY_TYPE;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.ut.monitor.SessionMonitor;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.ut.statistics.d;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.e;
import com.taobao.accs.utl.h;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.android.agoo.message.MessageService;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SessionInfo;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.Spdycb;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

// compiled from: Taobao
public class l extends a implements SessionCb, Spdycb {
    private SessionMonitor A;
    private c B;
    private boolean C;
    private String D;
    private boolean E;
    private f F;
    private String G;
    protected ScheduledFuture<?> f;
    protected String g;
    protected int h;
    protected String i;
    protected int j;
    private int k;
    private LinkedList<Message> l;
    private a m;
    private boolean n;
    private String o;
    private String p;
    private String q;
    private SpdyAgent r;
    private SpdySession s;
    private Object t;
    private long u;
    private long v;
    private long w;
    private long x;
    private int y;
    private String z;

    // compiled from: Taobao
    private class a extends Thread {
        public int a;
        long b;

        private a() {
            this.a = 0;
        }

        private void a(boolean z) {
            while (l.this.k != 1) {
                if (UtilityImpl.isNetworkConnected(l.this.b)) {
                    if (z) {
                        this.a = 0;
                    }
                    ALog.e("NetworkThread", l.this.a + " try connect, force = " + z + " failTimes = " + this.a, new Object[0]);
                    if (l.this.k != 1 && this.a >= 4) {
                        l.this.C = true;
                        ALog.e("NetworkThread", l.this.a + " try connect fail 4 times", new Object[0]);
                        return;
                    } else if (l.this.k != 1) {
                        if (l.this.a == 1 && this.a == 0) {
                            ALog.i("NetworkThread", l.this.a + " try connect in app, no sleep", new Object[0]);
                        } else {
                            ALog.i("NetworkThread", l.this.a + " try connect, need sleep", new Object[0]);
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        l.this.D = com.umeng.a.d;
                        if (this.a == 3) {
                            l.this.F.b(l.this.m());
                        }
                        String[] strArr = AccsConfig.ACCS_CHANNEL_IPS[UtilityImpl.getMode(l.this.b)];
                        if (strArr == null || strArr.length <= 0 || this.a != 3) {
                            l.this.b(null);
                        } else {
                            l.this.b(strArr[0]);
                        }
                        l.this.A.setRetryTimes(this.a);
                        if (l.this.k != 1) {
                            this.a++;
                            ALog.e("NetworkThread", l.this.a + " try connect fail, ready for reconnect", new Object[0]);
                            z = false;
                        } else {
                            this.b = System.currentTimeMillis();
                            return;
                        }
                    } else {
                        return;
                    }
                }
                ALog.e("NetworkThread", l.this.a + " Network not available", new Object[0]);
                return;
            }
            if (l.this.k == 1 && System.currentTimeMillis() - this.b > 5000) {
                this.a = 0;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.net.l.a.run():void");
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.taobao.accs.net.l.a.run():void
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
            /*
            this = this;
            r12 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
            r11 = 100;
            r7 = 1;
            r8 = 0;
            r0 = "NetworkThread";
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = com.taobao.accs.net.l.this;
            r2 = r2.a;
            r1 = r1.append(r2);
            r2 = " NetworkThread run";
            r1 = r1.append(r2);
            r1 = r1.toString();
            r2 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.i(r0, r1, r2);
            r0 = 0;
            r13.a = r8;
        L_0x0029:
            r1 = com.taobao.accs.net.l.this;
            r1 = r1.n;
            if (r1 == 0) goto L_0x0124;
        L_0x0031:
            r1 = "NetworkThread";
            r2 = "ready to get message";
            r3 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.d(r1, r2, r3);
            r1 = com.taobao.accs.net.l.this;
            r1 = r1.l;
            monitor-enter(r1);
            r2 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x012a }
            r2 = r2.l;	 Catch:{ all -> 0x012a }
            r2 = r2.size();	 Catch:{ all -> 0x012a }
            if (r2 != 0) goto L_0x0064;
        L_0x004f:
            r2 = "NetworkThread";
            r3 = "no message, wait";
            r4 = 0;
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x011f }
            com.taobao.accs.utl.ALog.d(r2, r3, r4);	 Catch:{ InterruptedException -> 0x011f }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ InterruptedException -> 0x011f }
            r2 = r2.l;	 Catch:{ InterruptedException -> 0x011f }
            r2.wait();	 Catch:{ InterruptedException -> 0x011f }
        L_0x0064:
            r2 = "NetworkThread";
            r3 = "try get message";
            r4 = 0;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x012a }
            com.taobao.accs.utl.ALog.d(r2, r3, r4);	 Catch:{ all -> 0x012a }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x012a }
            r2 = r2.l;	 Catch:{ all -> 0x012a }
            r2 = r2.size();	 Catch:{ all -> 0x012a }
            if (r2 == 0) goto L_0x0095;
        L_0x007c:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x012a }
            r0 = r0.l;	 Catch:{ all -> 0x012a }
            r0 = r0.getFirst();	 Catch:{ all -> 0x012a }
            r0 = (com.taobao.accs.data.Message) r0;	 Catch:{ all -> 0x012a }
            r2 = r0.getNetPermanceMonitor();	 Catch:{ all -> 0x012a }
            if (r2 == 0) goto L_0x0095;
        L_0x008e:
            r2 = r0.getNetPermanceMonitor();	 Catch:{ all -> 0x012a }
            r2.onTakeFromQueue();	 Catch:{ all -> 0x012a }
        L_0x0095:
            r6 = r0;
            monitor-exit(r1);	 Catch:{ all -> 0x012a }
            r0 = com.taobao.accs.net.l.this;
            r0 = r0.n;
            if (r0 == 0) goto L_0x0124;
        L_0x009f:
            if (r6 == 0) goto L_0x0608;
        L_0x00a1:
            r0 = "NetworkThread";
            r1 = "send message not null";
            r2 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.d(r0, r1, r2);
            r0 = r6.getType();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = "NetworkThread";
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2.<init>();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = r3.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = " send:";
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = com.taobao.accs.data.Message.b.b(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = " status:";
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = r3.k;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.toString();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = 0;
            r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.i(r1, r2, r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = 2;
            if (r0 != r1) goto L_0x028f;
        L_0x00ed:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 != r7) goto L_0x013f;
        L_0x00f3:
            r0 = "NetworkThread";
            r1 = "INAPP ping, skip";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = "NetworkThread";
            r1 = "send succ, remove it";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0130 }
            com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Throwable -> 0x0130 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0130 }
            r1 = r0.l;	 Catch:{ Throwable -> 0x0130 }
            monitor-enter(r1);	 Catch:{ Throwable -> 0x0130 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x012d }
            r0 = r0.l;	 Catch:{ all -> 0x012d }
            r0.remove(r6);	 Catch:{ all -> 0x012d }
            monitor-exit(r1);	 Catch:{ all -> 0x012d }
            r0 = r6;
            goto L_0x0029;
        L_0x011f:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x012a }
            monitor-exit(r1);	 Catch:{ all -> 0x012a }
        L_0x0124:
            r0 = com.taobao.accs.net.l.this;
            r0.i();
            return;
        L_0x012a:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x012d:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ Throwable -> 0x0130 }
            throw r0;	 Catch:{ Throwable -> 0x0130 }
        L_0x0130:
            r0 = move-exception;
            r1 = "NetworkThread";
            r2 = " run finally error";
            r3 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
            r0 = r6;
            goto L_0x0029;
        L_0x013f:
            r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.u;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0 - r2;
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.b;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.e.a(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.b();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2 + -1;
            r2 = r2 * 1000;
            r2 = (long) r2;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 >= 0) goto L_0x0163;
        L_0x015f:
            r0 = r6.force;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 == 0) goto L_0x0289;
        L_0x0163:
            r0 = "NetworkThread";
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = "ms:";
            r1.<init>(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r4 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r4 = r4.u;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2 - r4;
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = " force:";
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r6.force;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = 1;
            r13.a(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.s;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 == 0) goto L_0x0287;
        L_0x01a0:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.k;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 != r7) goto L_0x0287;
        L_0x01a8:
            r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.u;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0 - r2;
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.b;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.e.a(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.b();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2 + -1;
            r2 = r2 * 1000;
            r2 = (long) r2;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x03d0;
        L_0x01c8:
            r0 = "NetworkThread";
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1.<init>();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = " onSendPing";
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.i(r0, r1, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.b();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.s;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.submitPing();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.onSendPing();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.u = r2;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = java.lang.System.nanoTime();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.v = r2;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.g();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r7;
        L_0x021a:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0605 }
            r0.l();	 Catch:{ Throwable -> 0x0605 }
            if (r1 != 0) goto L_0x0413;
        L_0x0221:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0404 }
            r0.i();	 Catch:{ Throwable -> 0x0404 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0404 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x0404 }
            if (r0 == 0) goto L_0x023a;
        L_0x022e:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0404 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x0404 }
            r1 = "send fail";
            r0.setCloseReason(r1);	 Catch:{ Throwable -> 0x0404 }
        L_0x023a:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0404 }
            r2 = r0.l;	 Catch:{ Throwable -> 0x0404 }
            monitor-enter(r2);	 Catch:{ Throwable -> 0x0404 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r0 = r0.l;	 Catch:{ all -> 0x0401 }
            r0 = r0.size();	 Catch:{ all -> 0x0401 }
            r0 = r0 + -1;
            r1 = r0;
        L_0x024e:
            if (r1 < 0) goto L_0x03d3;
        L_0x0250:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r0 = r0.l;	 Catch:{ all -> 0x0401 }
            r0 = r0.get(r1);	 Catch:{ all -> 0x0401 }
            r0 = (com.taobao.accs.data.Message) r0;	 Catch:{ all -> 0x0401 }
            if (r0 == 0) goto L_0x0283;
        L_0x025e:
            r3 = r0.command;	 Catch:{ all -> 0x0401 }
            if (r3 == 0) goto L_0x0283;
        L_0x0262:
            r3 = r0.command;	 Catch:{ all -> 0x0401 }
            r3 = r3.intValue();	 Catch:{ all -> 0x0401 }
            if (r3 == r11) goto L_0x0272;
        L_0x026a:
            r3 = r0.command;	 Catch:{ all -> 0x0401 }
            r3 = r3.intValue();	 Catch:{ all -> 0x0401 }
            if (r3 != r12) goto L_0x0283;
        L_0x0272:
            r3 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r4 = -1;
            r3.a(r0, r4);	 Catch:{ all -> 0x0401 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r0 = r0.l;	 Catch:{ all -> 0x0401 }
            r0.remove(r1);	 Catch:{ all -> 0x0401 }
        L_0x0283:
            r0 = r1 + -1;
            r1 = r0;
            goto L_0x024e;
        L_0x0287:
            r1 = r8;
            goto L_0x021a;
        L_0x0289:
            r0 = 0;
            r13.a(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r7;
            goto L_0x021a;
        L_0x028f:
            if (r0 != r7) goto L_0x03a7;
        L_0x0291:
            r0 = 1;
            r13.a(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.k;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 != r7) goto L_0x03a4;
        L_0x029d:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.s;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 == 0) goto L_0x03a4;
        L_0x02a5:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.b;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r5 = r6.build(r0, r1);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r6.setSendTime(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r5.length;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
            if (r0 <= r1) goto L_0x02d2;
        L_0x02bd:
            r0 = r6.command;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.intValue();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
            if (r0 == r1) goto L_0x02d2;
        L_0x02c7:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = -4;
            r0.a(r6, r1);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r7;
            goto L_0x021a;
        L_0x02d2:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.s;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r6.getIntDataId();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            r3 = 0;
            if (r5 != 0) goto L_0x039e;
        L_0x02e1:
            r4 = r8;
        L_0x02e2:
            r0.sendCustomControlFrame(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = "NetworkThread";
            r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.<init>();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = " send data len:";
            r2 = r0.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r5 != 0) goto L_0x03a1;
        L_0x02fe:
            r0 = r8;
        L_0x02ff:
            r0 = r2.append(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = " dataId:";
            r0 = r0.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r6.getDataId();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.e(r1, r0, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.a(r6);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r6.isAck;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 == 0) goto L_0x0368;
        L_0x0327:
            r0 = "NetworkThread";
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1.<init>();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = " sendCFrame end ack";
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = 2;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = 0;
            r4 = "dataId";
            r2[r3] = r4;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = 1;
            r4 = r6.getIntDataId();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2[r3] = r4;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.e(r0, r1, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.e;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r6.getIntDataId();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.put(r1, r6);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
        L_0x0368:
            r0 = r6.getNetPermanceMonitor();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            if (r0 == 0) goto L_0x0375;
        L_0x036e:
            r0 = r6.getNetPermanceMonitor();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.onSendData();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
        L_0x0375:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r6.getDataId();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r6.timeout;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = (long) r2;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.a(r1, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r9 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = new com.taobao.accs.ut.monitor.TrafficsMonitor$a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r6.serviceId;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = anet.channel.GlobalAppRuntimeInfo.isAppBackground();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = r3.m();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r4 = r5.length;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r4 = (long) r4;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0.<init>(r1, r2, r3, r4);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r9.a(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = r7;
            goto L_0x021a;
        L_0x039e:
            r4 = r5.length;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            goto L_0x02e2;
        L_0x03a1:
            r0 = r5.length;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            goto L_0x02ff;
        L_0x03a4:
            r1 = r8;
            goto L_0x021a;
        L_0x03a7:
            r1 = 0;
            r13.a(r1);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r1 = "NetworkThread";
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2.<init>();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = r3.a;	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r3 = " skip msg ";
            r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r2.append(r0);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
            com.taobao.accs.utl.ALog.e(r1, r0, r2);	 Catch:{ Throwable -> 0x0436, all -> 0x0539 }
        L_0x03d0:
            r1 = r7;
            goto L_0x021a;
        L_0x03d3:
            r0 = "NetworkThread";
            r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0401 }
            r1.<init>();	 Catch:{ all -> 0x0401 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r3 = r3.a;	 Catch:{ all -> 0x0401 }
            r1 = r1.append(r3);	 Catch:{ all -> 0x0401 }
            r3 = " network disconnected, wait";
            r1 = r1.append(r3);	 Catch:{ all -> 0x0401 }
            r1 = r1.toString();	 Catch:{ all -> 0x0401 }
            r3 = 0;
            r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0401 }
            com.taobao.accs.utl.ALog.e(r0, r1, r3);	 Catch:{ all -> 0x0401 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0401 }
            r0 = r0.l;	 Catch:{ all -> 0x0401 }
            r0.wait();	 Catch:{ all -> 0x0401 }
            monitor-exit(r2);	 Catch:{ all -> 0x0401 }
            r0 = r6;
            goto L_0x0029;
        L_0x0401:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ Throwable -> 0x0404 }
            throw r0;	 Catch:{ Throwable -> 0x0404 }
        L_0x0404:
            r0 = move-exception;
            r1 = "NetworkThread";
            r2 = " run finally error";
            r3 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
            r0 = r6;
            goto L_0x0029;
        L_0x0413:
            r0 = "NetworkThread";
            r1 = "send succ, remove it";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0404 }
            com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Throwable -> 0x0404 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0404 }
            r1 = r0.l;	 Catch:{ Throwable -> 0x0404 }
            monitor-enter(r1);	 Catch:{ Throwable -> 0x0404 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0433 }
            r0 = r0.l;	 Catch:{ all -> 0x0433 }
            r0.remove(r6);	 Catch:{ all -> 0x0433 }
            monitor-exit(r1);	 Catch:{ all -> 0x0433 }
            r0 = r6;
            goto L_0x0029;
        L_0x0433:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ Throwable -> 0x0404 }
            throw r0;	 Catch:{ Throwable -> 0x0404 }
        L_0x0436:
            r0 = move-exception;
            r1 = r7;
        L_0x0438:
            r2 = "accs";
            r3 = "send_fail";
            r4 = r6.serviceId;	 Catch:{ all -> 0x0600 }
            r5 = "1";
            r9 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0600 }
            r9.<init>();	 Catch:{ all -> 0x0600 }
            r10 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0600 }
            r10 = r10.a;	 Catch:{ all -> 0x0600 }
            r9 = r9.append(r10);	 Catch:{ all -> 0x0600 }
            r10 = r0.toString();	 Catch:{ all -> 0x0600 }
            r9 = r9.append(r10);	 Catch:{ all -> 0x0600 }
            r9 = r9.toString();	 Catch:{ all -> 0x0600 }
            com.taobao.accs.utl.b.a(r2, r3, r4, r5, r9);	 Catch:{ all -> 0x0600 }
            r0.printStackTrace();	 Catch:{ all -> 0x0600 }
            r2 = "NetworkThread";
            r3 = "service connection run";
            r4 = 0;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0600 }
            com.taobao.accs.utl.ALog.e(r2, r3, r0, r4);	 Catch:{ all -> 0x0600 }
            if (r1 != 0) goto L_0x0516;
        L_0x0470:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0507 }
            r0.i();	 Catch:{ Throwable -> 0x0507 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0507 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x0507 }
            if (r0 == 0) goto L_0x0489;
        L_0x047d:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0507 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x0507 }
            r1 = "send fail";
            r0.setCloseReason(r1);	 Catch:{ Throwable -> 0x0507 }
        L_0x0489:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0507 }
            r2 = r0.l;	 Catch:{ Throwable -> 0x0507 }
            monitor-enter(r2);	 Catch:{ Throwable -> 0x0507 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r0 = r0.l;	 Catch:{ all -> 0x0504 }
            r0 = r0.size();	 Catch:{ all -> 0x0504 }
            r0 = r0 + -1;
            r1 = r0;
        L_0x049d:
            if (r1 < 0) goto L_0x04d6;
        L_0x049f:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r0 = r0.l;	 Catch:{ all -> 0x0504 }
            r0 = r0.get(r1);	 Catch:{ all -> 0x0504 }
            r0 = (com.taobao.accs.data.Message) r0;	 Catch:{ all -> 0x0504 }
            if (r0 == 0) goto L_0x04d2;
        L_0x04ad:
            r3 = r0.command;	 Catch:{ all -> 0x0504 }
            if (r3 == 0) goto L_0x04d2;
        L_0x04b1:
            r3 = r0.command;	 Catch:{ all -> 0x0504 }
            r3 = r3.intValue();	 Catch:{ all -> 0x0504 }
            if (r3 == r11) goto L_0x04c1;
        L_0x04b9:
            r3 = r0.command;	 Catch:{ all -> 0x0504 }
            r3 = r3.intValue();	 Catch:{ all -> 0x0504 }
            if (r3 != r12) goto L_0x04d2;
        L_0x04c1:
            r3 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r4 = -1;
            r3.a(r0, r4);	 Catch:{ all -> 0x0504 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r0 = r0.l;	 Catch:{ all -> 0x0504 }
            r0.remove(r1);	 Catch:{ all -> 0x0504 }
        L_0x04d2:
            r0 = r1 + -1;
            r1 = r0;
            goto L_0x049d;
        L_0x04d6:
            r0 = "NetworkThread";
            r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0504 }
            r1.<init>();	 Catch:{ all -> 0x0504 }
            r3 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r3 = r3.a;	 Catch:{ all -> 0x0504 }
            r1 = r1.append(r3);	 Catch:{ all -> 0x0504 }
            r3 = " network disconnected, wait";
            r1 = r1.append(r3);	 Catch:{ all -> 0x0504 }
            r1 = r1.toString();	 Catch:{ all -> 0x0504 }
            r3 = 0;
            r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0504 }
            com.taobao.accs.utl.ALog.e(r0, r1, r3);	 Catch:{ all -> 0x0504 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0504 }
            r0 = r0.l;	 Catch:{ all -> 0x0504 }
            r0.wait();	 Catch:{ all -> 0x0504 }
            monitor-exit(r2);	 Catch:{ all -> 0x0504 }
            r0 = r6;
            goto L_0x0029;
        L_0x0504:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ Throwable -> 0x0507 }
            throw r0;	 Catch:{ Throwable -> 0x0507 }
        L_0x0507:
            r0 = move-exception;
            r1 = "NetworkThread";
            r2 = " run finally error";
            r3 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
            r0 = r6;
            goto L_0x0029;
        L_0x0516:
            r0 = "NetworkThread";
            r1 = "send succ, remove it";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0507 }
            com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Throwable -> 0x0507 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x0507 }
            r1 = r0.l;	 Catch:{ Throwable -> 0x0507 }
            monitor-enter(r1);	 Catch:{ Throwable -> 0x0507 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x0536 }
            r0 = r0.l;	 Catch:{ all -> 0x0536 }
            r0.remove(r6);	 Catch:{ all -> 0x0536 }
            monitor-exit(r1);	 Catch:{ all -> 0x0536 }
            r0 = r6;
            goto L_0x0029;
        L_0x0536:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ Throwable -> 0x0507 }
            throw r0;	 Catch:{ Throwable -> 0x0507 }
        L_0x0539:
            r0 = move-exception;
            r1 = r0;
        L_0x053b:
            if (r7 != 0) goto L_0x05df;
        L_0x053d:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x05d2 }
            r0.i();	 Catch:{ Throwable -> 0x05d2 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x05d2 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x05d2 }
            if (r0 == 0) goto L_0x0556;
        L_0x054a:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x05d2 }
            r0 = r0.A;	 Catch:{ Throwable -> 0x05d2 }
            r2 = "send fail";
            r0.setCloseReason(r2);	 Catch:{ Throwable -> 0x05d2 }
        L_0x0556:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x05d2 }
            r3 = r0.l;	 Catch:{ Throwable -> 0x05d2 }
            monitor-enter(r3);	 Catch:{ Throwable -> 0x05d2 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r0 = r0.l;	 Catch:{ all -> 0x05cf }
            r0 = r0.size();	 Catch:{ all -> 0x05cf }
            r0 = r0 + -1;
            r2 = r0;
        L_0x056a:
            if (r2 < 0) goto L_0x05a3;
        L_0x056c:
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r0 = r0.l;	 Catch:{ all -> 0x05cf }
            r0 = r0.get(r2);	 Catch:{ all -> 0x05cf }
            r0 = (com.taobao.accs.data.Message) r0;	 Catch:{ all -> 0x05cf }
            if (r0 == 0) goto L_0x059f;
        L_0x057a:
            r4 = r0.command;	 Catch:{ all -> 0x05cf }
            if (r4 == 0) goto L_0x059f;
        L_0x057e:
            r4 = r0.command;	 Catch:{ all -> 0x05cf }
            r4 = r4.intValue();	 Catch:{ all -> 0x05cf }
            if (r4 == r11) goto L_0x058e;
        L_0x0586:
            r4 = r0.command;	 Catch:{ all -> 0x05cf }
            r4 = r4.intValue();	 Catch:{ all -> 0x05cf }
            if (r4 != r12) goto L_0x059f;
        L_0x058e:
            r4 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r4 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r5 = -1;
            r4.a(r0, r5);	 Catch:{ all -> 0x05cf }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r0 = r0.l;	 Catch:{ all -> 0x05cf }
            r0.remove(r2);	 Catch:{ all -> 0x05cf }
        L_0x059f:
            r0 = r2 + -1;
            r2 = r0;
            goto L_0x056a;
        L_0x05a3:
            r0 = "NetworkThread";
            r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x05cf }
            r2.<init>();	 Catch:{ all -> 0x05cf }
            r4 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r4 = r4.a;	 Catch:{ all -> 0x05cf }
            r2 = r2.append(r4);	 Catch:{ all -> 0x05cf }
            r4 = " network disconnected, wait";
            r2 = r2.append(r4);	 Catch:{ all -> 0x05cf }
            r2 = r2.toString();	 Catch:{ all -> 0x05cf }
            r4 = 0;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x05cf }
            com.taobao.accs.utl.ALog.e(r0, r2, r4);	 Catch:{ all -> 0x05cf }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05cf }
            r0 = r0.l;	 Catch:{ all -> 0x05cf }
            r0.wait();	 Catch:{ all -> 0x05cf }
            monitor-exit(r3);	 Catch:{ all -> 0x05cf }
        L_0x05ce:
            throw r1;
        L_0x05cf:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ Throwable -> 0x05d2 }
            throw r0;	 Catch:{ Throwable -> 0x05d2 }
        L_0x05d2:
            r0 = move-exception;
            r2 = "NetworkThread";
            r3 = " run finally error";
            r4 = new java.lang.Object[r8];
            com.taobao.accs.utl.ALog.e(r2, r3, r0, r4);
            goto L_0x05ce;
        L_0x05df:
            r0 = "NetworkThread";
            r2 = "send succ, remove it";
            r3 = 0;
            r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x05d2 }
            com.taobao.accs.utl.ALog.d(r0, r2, r3);	 Catch:{ Throwable -> 0x05d2 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ Throwable -> 0x05d2 }
            r2 = r0.l;	 Catch:{ Throwable -> 0x05d2 }
            monitor-enter(r2);	 Catch:{ Throwable -> 0x05d2 }
            r0 = com.taobao.accs.net.l.this;	 Catch:{ all -> 0x05fd }
            r0 = r0.l;	 Catch:{ all -> 0x05fd }
            r0.remove(r6);	 Catch:{ all -> 0x05fd }
            monitor-exit(r2);	 Catch:{ all -> 0x05fd }
            goto L_0x05ce;
        L_0x05fd:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ Throwable -> 0x05d2 }
            throw r0;	 Catch:{ Throwable -> 0x05d2 }
        L_0x0600:
            r0 = move-exception;
            r7 = r1;
            r1 = r0;
            goto L_0x053b;
        L_0x0605:
            r0 = move-exception;
            goto L_0x0438;
        L_0x0608:
            r0 = r6;
            goto L_0x0029;
            */
        }
    }

    protected l(Context context, int i) {
        super(context, i);
        this.k = 3;
        this.l = new LinkedList();
        this.n = true;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = new Object();
        this.y = -1;
        this.z = null;
        this.C = false;
        this.D = com.umeng.a.d;
        this.E = false;
        if (AccsConfig.mSecurityType == SECURITY_TYPE.SECURITY_TAOBAO) {
            this.F = new f(m());
        }
        n();
    }

    public synchronized void a() {
        this.n = true;
        a(this.b);
        if (this.m == null) {
            ALog.i("SpdyConnection", this.a + " start thread", new Object[0]);
            this.m = new a();
            this.m.start();
        }
        a(false, false);
    }

    protected void a(Message message, boolean z) {
        if (!this.n || message == null) {
            ALog.e("SpdyConnection", new StringBuilder("not running or msg null! ").append(this.n).toString(), new Object[0]);
            return;
        }
        try {
            if (com.taobao.accs.common.a.a().getQueue().size() > 1000) {
                throw new RejectedExecutionException(h.NAMESPACE);
            }
            ScheduledFuture schedule = com.taobao.accs.common.a.a().schedule(new m(this, message, z), message.delyTime, TimeUnit.MILLISECONDS);
            if (message.getType() == 1 && message.cunstomDataId != null) {
                if (message.isControlFrame()) {
                    a(message.cunstomDataId);
                }
                this.c.a.put(message.cunstomDataId, schedule);
            }
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().setDeviceId(UtilityImpl.getDeviceId(this.b));
                message.getNetPermanceMonitor().setConnType(this.a);
                message.getNetPermanceMonitor().onEnterQueueData();
            }
        } catch (RejectedExecutionException e) {
            this.c.a(message, (int) ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e("SpdyConnection", this.a + "send queue full count:" + com.taobao.accs.common.a.a().getQueue().size(), new Object[0]);
        } catch (Throwable th) {
            this.c.a(message, (int) com.tencent.connect.dataprovider.ErrorCode.FileNotExist);
            ALog.e("SpdyConnection", this.a + "send error", th, new Object[0]);
        }
    }

    public void f() {
        super.f();
        this.n = false;
        i();
        if (this.A != null) {
            this.A.setCloseReason("shut down");
        }
        synchronized (this.l) {
            try {
                this.l.notifyAll();
            } catch (Exception e) {
            }
        }
        ALog.e("SpdyConnection", this.a + "shut down", new Object[0]);
    }

    public void a(boolean z, boolean z2) {
        ALog.d("SpdyConnection", new StringBuilder("try ping, force:").append(z).toString(), new Object[0]);
        if (this.a == 1) {
            ALog.d("SpdyConnection", "INAPP, skip", new Object[0]);
        } else {
            b(Message.BuildPing(z, (int) (z2 ? (Math.random() * 10.0d) * 1000.0d : 0.0d)), z);
        }
    }

    public void i() {
        ALog.e("SpdyConnection", this.a + " force close!", new Object[0]);
        try {
            this.s.closeSession();
            this.A.setCloseType(1);
        } catch (Exception e) {
        }
        c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
    }

    public c d() {
        int i = 0;
        if (this.B == null) {
            this.B = new c();
        }
        this.B.b = this.a;
        this.B.d = this.l.size();
        this.B.i = UtilityImpl.isNetworkConnected(this.b);
        this.B.f = this.D;
        this.B.a = this.k;
        this.B.c = this.A == null ? false : this.A.getRet();
        this.B.j = b();
        c cVar = this.B;
        if (this.c != null) {
            i = this.c.e();
        }
        cVar.e = i;
        this.B.g = this.p;
        return this.B;
    }

    private void a(Message message) {
        if (message.command != null && this.l.size() != 0) {
            for (int size = this.l.size() - 1; size >= 0; size--) {
                Message message2 = (Message) this.l.get(size);
                if (message2 != null && message2.command != null && message2.getPackageName().equals(message.getPackageName())) {
                    switch (message.command.intValue()) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            if (message2.command.intValue() == 1 || message2.command.intValue() == 2) {
                                this.l.remove(size);
                            }
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            if (message2.command.intValue() == 3 || message2.command.intValue() == 4) {
                                this.l.remove(size);
                            }
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        case R.styleable.Toolbar_contentInsetEnd:
                            if (message2.command.intValue() == 5 || message2.command.intValue() == 6) {
                                this.l.remove(size);
                            }
                            break;
                    }
                    ALog.d("SpdyConnection", new StringBuilder("clearRepeatControlCommand message:").append(message2.command).append("/").append(message2.getPackageName()).toString(), new Object[0]);
                }
            }
            if (this.c != null) {
                this.c.b(message);
            }
        }
    }

    private void b(String str) {
        int i = Constants.PORT;
        if (this.k != 2 && this.k != 1) {
            if (UtilityImpl.isReleaseMode(this.b) || UtilityImpl.isPreviewMode(this.b)) {
                if (this.F == null) {
                    this.F = new f(m());
                }
                List<IConnStrategy> a = this.F.a(m());
                if (a == null || a.size() <= 0) {
                    if (str != null) {
                        this.g = str;
                    } else {
                        this.g = m();
                    }
                    if (System.currentTimeMillis() % 2 == 0) {
                        i = R.styleable.AppCompatTheme_panelMenuListTheme;
                    }
                    this.h = i;
                    b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_DNS, "localdns", 0.0d);
                    ALog.i("SpdyConnection", this.a + " get ip from amdc fail!!", new Object[0]);
                } else {
                    for (IConnStrategy iConnStrategy : a) {
                        if (iConnStrategy != null) {
                            ALog.e("SpdyConnection", this.a + " connect strategys ip:" + iConnStrategy.getIp() + " port:" + iConnStrategy.getPort(), new Object[0]);
                        }
                    }
                    if (this.E) {
                        this.F.b();
                        this.E = false;
                    }
                    IConnStrategy a2 = this.F.a();
                    this.g = a2 == null ? m() : a2.getIp();
                    this.h = a2 == null ? 443 : a2.getPort();
                    b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_DNS, "httpdns", 0.0d);
                    ALog.e("SpdyConnection", this.a + " get ip from amdc succ:" + this.g + ":" + this.h + " originPos:" + this.F.c(), new Object[0]);
                }
                this.o = new StringBuilder("https://").append(this.g).append(":").append(this.h).append("/accs/").toString();
            } else {
                String[] strArr = AccsConfig.ACCS_CHANNEL_IPS[UtilityImpl.getMode(this.b)];
                String m = (strArr == null || strArr.length <= 0) ? m() : strArr[0];
                this.g = m;
                this.h = 443;
                this.o = new StringBuilder("https://").append(this.g).append(":").append(this.h).append("/accs/").toString();
            }
            ALog.e("SpdyConnection", this.a + " connect URL:" + this.o, new Object[0]);
            this.G = String.valueOf(System.currentTimeMillis());
            if (this.A != null) {
                AppMonitor.getInstance().commitStat(this.A);
            }
            this.A = new SessionMonitor();
            this.A.setConnectType(this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp");
            if (this.r != null) {
                try {
                    this.w = System.currentTimeMillis();
                    this.x = System.nanoTime();
                    this.i = UtilityImpl.getProxyHost(this.b);
                    this.j = UtilityImpl.getProxyPort(this.b);
                    this.u = System.currentTimeMillis();
                    this.A.onStartConnect();
                    synchronized (this.t) {
                        try {
                            SessionInfo sessionInfo;
                            if (TextUtils.isEmpty(this.i) || this.j < 0 || !this.C) {
                                ALog.e("SpdyConnection", this.a + " connect normal", new Object[0]);
                                sessionInfo = new SessionInfo(this.g, this.h, m(), null, 0, this.G, this, 4226);
                                this.D = com.umeng.a.d;
                            } else {
                                ALog.e("SpdyConnection", this.a + " connect with proxy:" + this.i + ":" + this.j, new Object[0]);
                                sessionInfo = new SessionInfo(this.g, this.h, m(), this.i, this.j, this.G, this, 4226);
                                this.D = this.i + ":" + this.j;
                            }
                            sessionInfo.setPubKeySeqNum(j());
                            sessionInfo.setConnectionTimeoutMs(a.ACCS_RECEIVE_TIMEOUT);
                            this.s = this.r.createSession(sessionInfo);
                            c((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            this.A.connection_stop_date = 0;
                            this.t.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            this.C = false;
                        }
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            }
        }
    }

    private int j() {
        if (AccsConfig.mSecurityType == SECURITY_TYPE.SECURITY_OFF) {
            Object obj = 1;
        } else {
            int i = 0;
        }
        if (UtilityImpl.isDebugMode(this.b)) {
            return i != 0 ? 0 : 0;
        } else {
            if (GlobalClientInfo.b > 0) {
                return GlobalClientInfo.b;
            }
            return i != 0 ? XZBDevice.DOWNLOAD_LIST_ALL : XZBDevice.DOWNLOAD_LIST_FAILED;
        }
    }

    private void k() {
        if (this.s == null) {
            c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
            return;
        }
        try {
            String imsi = UtilityImpl.getImsi(this.b);
            String str = "null";
            if (imsi == null || imsi.length() <= 5) {
                imsi = str;
            } else {
                imsi = imsi.substring(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            }
            String encode = URLEncoder.encode(UtilityImpl.getDeviceId(this.b));
            String appkey = UtilityImpl.getAppkey(this.b);
            String appsign = UtilityImpl.getAppsign(this.b, appkey, UtilityImpl.getDeviceId(this.b), this.q, this.a);
            String str2 = (this.o + "auth?1=" + encode + "&2=" + appsign + "&3=" + UtilityImpl.getAppkey(this.b) + (this.q == null ? com.umeng.a.d : new StringBuilder("&4=").append(this.q).toString()) + "&5=" + this.a + "&6=" + UtilityImpl.getNetworkType(this.b) + "&7=" + imsi + "&8=212&9=" + System.currentTimeMillis() + "&10=1&11=" + VERSION.SDK_INT + "&12=" + this.b.getPackageName() + "&13=" + UtilityImpl.getAppVersion(this.b) + "&14=" + UtilityImpl.getTtId(this.b) + "&15=" + Build.MODEL + "&16=" + Build.BRAND + "&17=212") + "&19=" + (AccsConfig.mSecurityType == SECURITY_TYPE.SECURITY_OFF ? 0 : 1);
            ALog.e("SpdyConnection", this.a + " auth URL:" + str2, new Object[0]);
            this.p = str2;
            if (a(encode, appkey, appsign)) {
                URL url = new URL(str2);
                SpdyRequest spdyRequest = new SpdyRequest(new URL(str2), com.tencent.connect.common.Constants.HTTP_GET, RequestPriority.DEFAULT_PRIORITY, 80000, 40000);
                spdyRequest.setDomain(m());
                this.s.submitRequest(spdyRequest, new SpdyDataProvider(null), m(), this);
                return;
            }
            ALog.e("SpdyConnection", this.a + " auth param error!", new Object[0]);
            d((int) ErrCode.ERR_BAN);
        } catch (Throwable th) {
            ALog.e("SpdyConnection", this.a + " auth exception ", th, new Object[0]);
            d((int) com.tencent.connect.dataprovider.ErrorCode.PathIsNull);
        }
    }

    private boolean a(String str, String str2, String str3) {
        if (UtilityImpl.isDebugMode(this.b)) {
            return true;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            return true;
        }
        int i;
        int i2;
        c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
        if (TextUtils.isEmpty(str)) {
            i = 1;
        } else if (TextUtils.isEmpty(str2)) {
            i = 2;
        } else if (TextUtils.isEmpty(str3)) {
            i = 3;
        } else {
            i = 1;
        }
        this.A.setFailReason(i);
        this.A.onConnectStop();
        String str4 = this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp";
        if (this.m != null) {
            i2 = this.m.a;
        } else {
            i2 = 0;
        }
        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, new StringBuilder("DISCONNECT ").append(str4).toString(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.p, this.D);
        b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_CONNECT, new StringBuilder("retrytimes:").append(i2).toString(), String.valueOf(i), com.umeng.a.d);
        return false;
    }

    private final Map<String, String> a(Map<String, List<String>> map) {
        Map<String, String> hashMap = new HashMap();
        try {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (!TextUtils.isEmpty(str)) {
                    Object a = a((List) entry.getValue());
                    if (!TextUtils.isEmpty(a)) {
                        if (!str.startsWith(":")) {
                            str = str.toLowerCase(Locale.US);
                        }
                        hashMap.put(str, a);
                        ALog.i("SpdyConnection", new StringBuilder("\theader:").append(str).append(" value:").append(a).toString(), new Object[0]);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return hashMap;
    }

    private final String a(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append((String) list.get(i));
            if (i < size - 1) {
                stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
        }
        return stringBuffer.toString();
    }

    private synchronized void l() {
        if (this.a != 1) {
            this.u = System.currentTimeMillis();
            this.v = System.nanoTime();
            e.a(this.b).a();
        }
    }

    private synchronized void c(int i) {
        try {
            ALog.e("SpdyConnection", this.a + " notifyStatus:" + a(i), new Object[0]);
            if (i == this.k) {
                ALog.i("SpdyConnection", this.a + " ignore notifyStatus", new Object[0]);
            } else {
                this.k = i;
                switch (i) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        e.a(this.b).f();
                        l();
                        if (this.f != null) {
                            this.f.cancel(true);
                        }
                        synchronized (this.t) {
                            try {
                                this.t.notifyAll();
                            } catch (Exception e) {
                            }
                        }
                        synchronized (this.l) {
                            try {
                                this.l.notifyAll();
                            } catch (Exception e2) {
                            }
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (this.f != null) {
                            this.f.cancel(true);
                        }
                        com.taobao.accs.common.a.a().schedule(new n(this, this.G), 120000, TimeUnit.MILLISECONDS);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        l();
                        e.a(this.b).d();
                        synchronized (this.t) {
                            try {
                                this.t.notifyAll();
                            } catch (Exception e3) {
                            }
                        }
                        this.c.a((int) com.tencent.connect.dataprovider.ErrorCode.SdCardNotExist);
                        a(false, true);
                        break;
                }
                ALog.i("SpdyConnection", this.a + " notifyStatus:" + a(i) + " handled", new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    private String m() {
        String str = AccsConfig.ACCS_CHANNEL_HOSTS[UtilityImpl.getMode(this.b)];
        ALog.i("SpdyConnection", this.a + " getDefaultHost:" + str, new Object[0]);
        return str == null ? com.umeng.a.d : str;
    }

    private void n() {
        try {
            SpdyAgent.enableDebug = true;
            this.r = SpdyAgent.getInstance(this.b, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
            if (SpdyAgent.checkLoadSucc()) {
                e.a();
                this.r.setAccsSslCallback(new o(this));
                if (!h.a(false)) {
                    String str = this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp";
                    ALog.d("SpdyConnection", "into--[setTnetLogPath]", new Object[0]);
                    Object tnetLogFilePath = UtilityImpl.getTnetLogFilePath(this.b, str);
                    ALog.d("SpdyConnection", new StringBuilder("config tnet log path:").append(tnetLogFilePath).toString(), new Object[0]);
                    if (!TextUtils.isEmpty(tnetLogFilePath)) {
                        this.r.configLogFile(tnetLogFilePath, UtilityImpl.TNET_FILE_SIZE, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                        return;
                    }
                    return;
                }
                return;
            }
            ALog.e("SpdyConnection", "loadSoFail", new Object[0]);
            e.b();
        } catch (Throwable th) {
            ALog.e("SpdyConnection", "loadSoFail", th, new Object[0]);
        }
    }

    public boolean b() {
        return this.n;
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        int i2;
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("SpdyConnection", new StringBuilder("session cleanUp has exception: ").append(e).toString(), new Object[0]);
            }
        }
        if (this.m != null) {
            i2 = this.m.a;
        } else {
            i2 = 0;
        }
        ALog.e("SpdyConnection", this.a + " spdySessionFailedError, retryTimes:" + i2 + " errorId:" + i, new Object[0]);
        this.C = false;
        this.E = true;
        c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
        this.A.setFailReason(i);
        this.A.onConnectStop();
        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, new StringBuilder("DISCONNECT ").append(this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp").toString(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.p, this.D);
        b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_CONNECT, new StringBuilder("retrytimes:").append(i2).toString(), String.valueOf(i), com.umeng.a.d);
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        this.y = superviseConnectInfo.connectTime;
        int i = superviseConnectInfo.handshakeTime;
        ALog.e("SpdyConnection", this.a + " spdySessionConnectCB sessionConnectInterval:" + this.y + " sslTime:" + i + " reuse:" + superviseConnectInfo.sessionTicketReused, new Object[0]);
        k();
        this.A.setRet(true);
        this.A.onConnectStop();
        this.A.tcp_time = (long) this.y;
        this.A.ssl_time = (long) i;
        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, new StringBuilder("CONNECTED ").append(this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp").append(" ").append(superviseConnectInfo.sessionTicketReused).toString(), String.valueOf(this.y), String.valueOf(i), Integer.valueOf(Constants.SDK_VERSION_CODE), String.valueOf(superviseConnectInfo.sessionTicketReused), this.p, this.D);
        b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_CONNECT, com.umeng.a.d);
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        ALog.e("SpdyConnection", this.a + " spdySessionCloseCallback, errorCode:" + i, new Object[0]);
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("SpdyConnection", new StringBuilder("session cleanUp has exception: ").append(e).toString(), new Object[0]);
            }
        }
        c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
        this.A.onCloseConnect();
        if (this.A.getConCloseDate() > 0 && this.A.getConStopDate() > 0) {
            this.A.getConCloseDate();
            this.A.getConStopDate();
        }
        this.A.setCloseReason(this.A.getCloseReason() + "tnet error:" + i);
        if (superviseConnectInfo != null) {
            this.A.live_time = (long) superviseConnectInfo.keepalive_period_second;
        }
        AppMonitor.getInstance().commitStat(this.A);
        for (Message message : this.c.f()) {
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().setFailReason("session close");
                AppMonitor.getInstance().commitStat(message.getNetPermanceMonitor());
            }
        }
        String str = this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp";
        ALog.d("SpdyConnection", new StringBuilder("spdySessionCloseCallback, conKeepTime:").append(this.A.live_time).append(" connectType:").append(str).toString(), new Object[0]);
        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, new StringBuilder("DISCONNECT CLOSE ").append(str).toString(), Integer.valueOf(i), Long.valueOf(this.A.live_time), Integer.valueOf(Constants.SDK_VERSION_CODE), this.p, this.D);
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        ALog.d("SpdyConnection", new StringBuilder("spdyPingRecvCallback uniId:").append(j).toString(), new Object[0]);
        if (j >= 0) {
            this.c.c();
            e.a(this.b).e();
            e.a(this.b).a();
            this.A.onPingCBReceive();
            if (this.A.ping_rec_times % 2 == 0) {
                UtilityImpl.setServiceTime(this.b, Constants.SP_KEY_SERVICE_END, System.currentTimeMillis());
            }
        }
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        l();
        ALog.e("SpdyConnection", this.a + " onFrame, type:" + i2 + " len:" + bArr.length, new Object[0]);
        String str = com.umeng.a.d;
        if (ALog.isPrintLog(Level.D) && bArr.length < 512) {
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = str;
            for (int i5 = 0; i5 < bArr.length; i5++) {
                str2 = str2 + Integer.toHexString(bArr[i5] & 255) + " ";
            }
            ALog.d("SpdyConnection", str2 + " log time:" + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        }
        if (i2 == 200) {
            try {
                long currentTimeMillis2 = System.currentTimeMillis();
                this.c.a(bArr);
                d g = this.c.g();
                if (g != null) {
                    g.c = String.valueOf(currentTimeMillis2);
                    g.g = this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp";
                    g.commitUT();
                }
            } catch (Throwable th) {
                ALog.e("SpdyConnection", "onDataReceive ", th, new Object[0]);
                UTMini.getInstance().commitEvent(UT.EVENT_ID, "SERVICE_DATA_RECEIVE", UtilityImpl.getStackMsg(th));
            }
            ALog.d("SpdyConnection", "try handle msg", new Object[0]);
            h();
        } else {
            ALog.e("SpdyConnection", this.a + " drop frame len:" + bArr.length, new Object[0]);
        }
        ALog.d("SpdyConnection", "spdyCustomControlFrameRecvCallback", new Object[0]);
    }

    public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
        ALog.d("SpdyConnection", "spdyStreamCloseCallback", new Object[0]);
        if (i != 0) {
            ALog.e("SpdyConnection", "spdyStreamCloseCallback", "statusCode", Integer.valueOf(i));
            d(i);
        }
    }

    public void spdyRequestRecvCallback(SpdySession spdySession, long j, Object obj) {
        ALog.d("SpdyConnection", "spdyRequestRecvCallback", new Object[0]);
    }

    public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
        this.u = System.currentTimeMillis();
        this.v = System.nanoTime();
        try {
            Map a = a((Map) map);
            int parseInt = Integer.parseInt((String) a.get(HttpConstant.STATUS));
            ALog.e("SpdyConnection", this.a + " spdyOnStreamResponse httpStatusCode: " + parseInt, new Object[0]);
            if (parseInt == 200) {
                c(1);
                if (!TextUtils.isEmpty((String) a.get("x-at"))) {
                    this.q = (String) a.get("x-at");
                }
                this.A.auth_time = this.A.connection_stop_date > 0 ? System.currentTimeMillis() - this.A.connection_stop_date : 0;
                UTMini.getInstance().commitEvent((int) UT.EVENT_ID, new StringBuilder("CONNECTED 200 ").append(this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp").toString(), this.p, this.D, Integer.valueOf(Constants.SDK_VERSION_CODE), MessageService.MSG_DB_READY_REPORT);
                b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_AUTH, com.umeng.a.d);
            } else {
                d(parseInt);
            }
        } catch (Exception e) {
            ALog.e("SpdyConnection", new StringBuilder("SpdyConnection").append(e.toString()).toString(), new Object[0]);
            i();
            this.A.setCloseReason("exception");
        }
        ALog.d("SpdyConnection", "spdyOnStreamResponse", new Object[0]);
    }

    private void d(int i) {
        int i2;
        this.q = null;
        i();
        if (this.m != null) {
            i2 = this.m.a;
        } else {
            i2 = 0;
        }
        this.A.setCloseReason(new StringBuilder("code not 200 is").append(i).toString());
        this.E = true;
        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, new StringBuilder("CONNECTED NO 200 ").append(this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp").toString(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.p, this.D);
        b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_AUTH, com.umeng.a.d, String.valueOf(i), com.umeng.a.d);
    }

    public void spdyDataSendCallback(SpdySession spdySession, boolean z, long j, int i, Object obj) {
        ALog.d("SpdyConnection", "spdyDataSendCallback", new Object[0]);
    }

    public void spdyDataRecvCallback(SpdySession spdySession, boolean z, long j, int i, Object obj) {
        ALog.d("SpdyConnection", "spdyDataRecvCallback", new Object[0]);
    }

    public void c() {
        this.C = false;
        this.d = 0;
    }

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
        ALog.w("SpdyConnection", new StringBuilder("bioPingRecvCallback uniId:").append(i).toString(), new Object[0]);
    }

    protected void a(String str, String str2) {
        try {
            c((int) XZBDevice.DOWNLOAD_LIST_ALL);
            i();
            this.A.setCloseReason(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(String str) {
        boolean z;
        synchronized (this.l) {
            for (int size = this.l.size() - 1; size >= 0; size--) {
                Message message = (Message) this.l.get(size);
                if (message != null && message.getType() == 1 && message.cunstomDataId != null && message.cunstomDataId.equals(str)) {
                    this.l.remove(size);
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        return Utils.SecurityGuardGetSslTicket2(this.b, spdySession.getDomain());
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        return Utils.SecurityGuardPutSslTicket2(this.b, spdySession.getDomain(), bArr);
    }

    public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
        ALog.d("SpdyConnection", "spdyDataChunkRecvCB", new Object[0]);
    }

    protected String e() {
        return "SpdyConnection";
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        b(i);
    }

    protected void a(Context context) {
        super.a(context);
        AccsConfig.disableInappKeepAlive();
        GlobalAppRuntimeInfo.setBackground(false);
    }
}
