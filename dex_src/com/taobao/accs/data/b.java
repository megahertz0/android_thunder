package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.antibrush.AntiBrush;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message.ReqType;
import com.taobao.accs.flowcontrol.FlowControl;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.ut.monitor.TrafficsMonitor;
import com.taobao.accs.ut.statistics.d;
import com.taobao.accs.ut.statistics.e;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.g;
import com.taobao.accs.utl.h;
import com.tencent.connect.dataprovider.ErrorCode;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: Taobao
public class b {
    private static b f;
    public ConcurrentHashMap<String, ScheduledFuture<?>> a;
    public int b;
    protected TrafficsMonitor c;
    public FlowControl d;
    public AntiBrush e;
    private ConcurrentHashMap<String, Message> g;
    private boolean h;
    private Context i;
    private d j;
    private Message k;
    private LinkedHashMap<String, String> l;
    private Runnable m;

    static {
        f = null;
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (f == null) {
                f = new b(context);
            }
            bVar = f;
        }
        return bVar;
    }

    public static void a() {
        f = null;
    }

    private b(Context context) {
        this.g = new ConcurrentHashMap();
        this.a = new ConcurrentHashMap();
        this.h = false;
        this.l = new MessageHandler$1(this);
        this.m = new d(this);
        this.i = context;
        this.c = new TrafficsMonitor(this.i);
        this.d = new FlowControl(this.i);
        this.e = new AntiBrush(this.i);
        i();
        h();
    }

    public void a(byte[] bArr) throws IOException {
        a(bArr, null);
    }

    public void a(byte[] bArr, String str) throws IOException {
        int i = 0;
        g gVar = new g(bArr);
        try {
            int a = gVar.a();
            int i2 = (a & 240) >> 4;
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("MessageHandler", new StringBuilder("version:").append(i2).toString(), new Object[0]);
            }
            a &= 15;
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("MessageHandler", new StringBuilder("compress:").append(a).toString(), new Object[0]);
            }
            gVar.a();
            int b = gVar.b();
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("MessageHandler", new StringBuilder("totalLen:").append(b).toString(), new Object[0]);
            }
            while (i < b) {
                int b2 = gVar.b();
                i += 2;
                if (b2 > 0) {
                    byte[] bArr2 = new byte[b2];
                    gVar.read(bArr2);
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d("MessageHandler", new StringBuilder("buf len:").append(bArr2.length).toString(), new Object[0]);
                    }
                    i += bArr2.length;
                    a(a, bArr2, str, i2);
                } else {
                    throw new IOException("data format error");
                }
            }
            gVar.close();
        } catch (Throwable th) {
            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, a.d, MessageService.MSG_DB_NOTIFY_REACHED, this.b + th.toString());
            ALog.e("MessageHandler", a.d, th, new Object[0]);
            gVar.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r26, byte[] r27, java.lang.String r28, int r29) throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.data.b.a(int, byte[], java.lang.String, int):void");
        /*
        this = this;
        r5 = new com.taobao.accs.utl.g;
        r0 = r27;
        r5.<init>(r0);
        r4 = r5.b();
        r0 = (long) r4;
        r18 = r0;
        r4 = com.taobao.accs.utl.ALog.Level.D;
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x0016:
        r4 = "MessageHandler";
        r6 = new java.lang.StringBuilder;
        r7 = "flag:";
        r6.<init>(r7);
        r0 = r18;
        r7 = (int) r0;
        r7 = java.lang.Integer.toHexString(r7);
        r6 = r6.append(r7);
        r6 = r6.toString();
        r7 = 0;
        r7 = new java.lang.Object[r7];
        com.taobao.accs.utl.ALog.d(r4, r6, r7);
    L_0x0036:
        r4 = r5.a();
        r16 = r5.a(r4);
        r4 = com.taobao.accs.utl.ALog.Level.D;
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);
        if (r4 == 0) goto L_0x0061;
    L_0x0046:
        r4 = "MessageHandler";
        r6 = new java.lang.StringBuilder;
        r7 = "target:";
        r6.<init>(r7);
        r0 = r16;
        r6 = r6.append(r0);
        r6 = r6.toString();
        r7 = 0;
        r7 = new java.lang.Object[r7];
        com.taobao.accs.utl.ALog.d(r4, r6, r7);
    L_0x0061:
        r4 = r5.a();
        r20 = r5.a(r4);
        r4 = com.taobao.accs.utl.ALog.Level.D;
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);
        if (r4 == 0) goto L_0x008c;
    L_0x0071:
        r4 = "MessageHandler";
        r6 = new java.lang.StringBuilder;
        r7 = "source:";
        r6.<init>(r7);
        r0 = r20;
        r6 = r6.append(r0);
        r6 = r6.toString();
        r7 = 0;
        r7 = new java.lang.Object[r7];
        com.taobao.accs.utl.ALog.d(r4, r6, r7);
    L_0x008c:
        r4 = r5.a();	 Catch:{ Exception -> 0x020a }
        r21 = r5.a(r4);	 Catch:{ Exception -> 0x020a }
        r4 = com.taobao.accs.utl.ALog.Level.D;
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);
        if (r4 == 0) goto L_0x00b7;
    L_0x009c:
        r4 = "MessageHandler";
        r6 = new java.lang.StringBuilder;
        r7 = "dataId:";
        r6.<init>(r7);
        r0 = r21;
        r6 = r6.append(r0);
        r6 = r6.toString();
        r7 = 0;
        r7 = new java.lang.Object[r7];
        com.taobao.accs.utl.ALog.d(r4, r6, r7);
    L_0x00b7:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r20;
        r4 = r4.append(r0);
        r0 = r21;
        r4 = r4.append(r0);
        r22 = r4.toString();
        r8 = 0;
        r9 = 0;
        r4 = r5.available();
        if (r4 <= 0) goto L_0x00e5;
    L_0x00d4:
        r4 = 2;
        r0 = r29;
        if (r0 != r4) goto L_0x00df;
    L_0x00d9:
        r0 = r25;
        r9 = r0.a(r5);
    L_0x00df:
        if (r26 != 0) goto L_0x025b;
    L_0x00e1:
        r8 = r5.c();
    L_0x00e5:
        r5.close();
        if (r8 != 0) goto L_0x02e8;
    L_0x00ea:
        r4 = "MessageHandler";
        r5 = "oriData is null";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.d(r4, r5, r6);	 Catch:{ Exception -> 0x030f }
    L_0x00f6:
        r4 = 15;
        r4 = r18 >> r4;
        r6 = 1;
        r4 = r4 & r6;
        r4 = (int) r4;	 Catch:{ Exception -> 0x030f }
        r23 = com.taobao.accs.data.Message.b.a(r4);	 Catch:{ Exception -> 0x030f }
        r4 = 13;
        r4 = r18 >> r4;
        r6 = 3;
        r4 = r4 & r6;
        r4 = (int) r4;	 Catch:{ Exception -> 0x030f }
        r7 = com.taobao.accs.data.Message.ReqType.valueOf(r4);	 Catch:{ Exception -> 0x030f }
        r4 = 12;
        r4 = r18 >> r4;
        r10 = 1;
        r4 = r4 & r10;
        r10 = (int) r4;	 Catch:{ Exception -> 0x030f }
        r4 = 11;
        r4 = r18 >> r4;
        r12 = 1;
        r4 = r4 & r12;
        r4 = (int) r4;	 Catch:{ Exception -> 0x030f }
        r24 = com.taobao.accs.data.Message.a.a(r4);	 Catch:{ Exception -> 0x030f }
        r4 = 6;
        r4 = r18 >> r4;
        r12 = 1;
        r4 = r4 & r12;
        r4 = (int) r4;	 Catch:{ Exception -> 0x030f }
        r5 = 1;
        if (r4 != r5) goto L_0x034a;
    L_0x012c:
        r4 = 1;
        r17 = r4;
    L_0x012f:
        r4 = "MessageHandler";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r5.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.b;	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = " dataId:";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x030f }
        r6 = " type:";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = com.taobao.accs.data.Message.b.b(r23);	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = " reqType:";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = r7.name();	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = " resType:";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = com.taobao.accs.data.Message.a.b(r24);	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x030f }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.i(r4, r5, r6);	 Catch:{ Exception -> 0x030f }
        r4 = 1;
        r0 = r23;
        if (r0 != r4) goto L_0x01ec;
    L_0x0188:
        r4 = com.taobao.accs.data.Message.ReqType.ACK;	 Catch:{ Exception -> 0x030f }
        if (r7 == r4) goto L_0x0190;
    L_0x018c:
        r4 = com.taobao.accs.data.Message.ReqType.RES;	 Catch:{ Exception -> 0x030f }
        if (r7 != r4) goto L_0x01ec;
    L_0x0190:
        r0 = r25;
        r4 = r0.g;	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r5 = r4.remove(r0);	 Catch:{ Exception -> 0x030f }
        r5 = (com.taobao.accs.data.Message) r5;	 Catch:{ Exception -> 0x030f }
        if (r5 == 0) goto L_0x035a;
    L_0x019e:
        r4 = "MessageHandler";
        r6 = "reqMessage not null";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.d(r4, r6, r11);	 Catch:{ Exception -> 0x030f }
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r4 = 1;
        if (r10 != r4) goto L_0x01c0;
    L_0x01af:
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x034f }
        r6 = new java.lang.String;	 Catch:{ Exception -> 0x034f }
        r6.<init>(r8);	 Catch:{ Exception -> 0x034f }
        r4.<init>(r6);	 Catch:{ Exception -> 0x034f }
        r6 = "code";
        r6 = r4.getInt(r6);	 Catch:{ Exception -> 0x034f }
    L_0x01c0:
        r4 = r5.getNetPermanceMonitor();	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x01cd;
    L_0x01c6:
        r4 = r5.getNetPermanceMonitor();	 Catch:{ Exception -> 0x030f }
        r4.onRecAck();	 Catch:{ Exception -> 0x030f }
    L_0x01cd:
        r4 = com.taobao.accs.data.Message.ReqType.RES;	 Catch:{ Exception -> 0x030f }
        if (r7 != r4) goto L_0x0353;
    L_0x01d1:
        r4 = r25;
        r4.a(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x030f }
    L_0x01d6:
        r10 = new com.taobao.accs.ut.monitor.TrafficsMonitor$a;	 Catch:{ Exception -> 0x030f }
        r11 = r5.serviceId;	 Catch:{ Exception -> 0x030f }
        r12 = anet.channel.GlobalAppRuntimeInfo.isAppBackground();	 Catch:{ Exception -> 0x030f }
        r0 = r27;
        r4 = r0.length;	 Catch:{ Exception -> 0x030f }
        r14 = (long) r4;	 Catch:{ Exception -> 0x030f }
        r13 = r28;
        r10.<init>(r11, r12, r13, r14);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r0.a(r10);	 Catch:{ Exception -> 0x030f }
    L_0x01ec:
        if (r23 != 0) goto L_0x03d0;
    L_0x01ee:
        r4 = com.taobao.accs.data.Message.ReqType.RES;	 Catch:{ Exception -> 0x030f }
        if (r7 != r4) goto L_0x03d0;
    L_0x01f2:
        r0 = r25;
        r4 = r0.g;	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r4 = r4.remove(r0);	 Catch:{ Exception -> 0x030f }
        r4 = (com.taobao.accs.data.Message) r4;	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x0383;
    L_0x0200:
        r0 = r25;
        r1 = r27;
        r2 = r28;
        r0.a(r4, r8, r1, r2);	 Catch:{ Exception -> 0x030f }
    L_0x0209:
        return;
    L_0x020a:
        r4 = move-exception;
        r6 = "MessageHandler";
        r7 = new java.lang.StringBuilder;
        r8 = "dataId read error ";
        r7.<init>(r8);
        r8 = r4.toString();
        r7 = r7.append(r8);
        r7 = r7.toString();
        r8 = 0;
        r8 = new java.lang.Object[r8];
        com.taobao.accs.utl.ALog.e(r6, r7, r8);
        r5.close();
        r5 = "accs";
        r6 = "send_fail";
        r7 = "";
        r8 = "1";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = r25;
        r10 = r0.b;
        r9 = r9.append(r10);
        r10 = "data id read error";
        r9 = r9.append(r10);
        r4 = r4.toString();
        r4 = r9.append(r4);
        r4 = r4.toString();
        com.taobao.accs.utl.b.a(r5, r6, r7, r8, r4);
        goto L_0x0209;
    L_0x025b:
        r4 = 1;
        r0 = r26;
        if (r0 != r4) goto L_0x00e5;
    L_0x0260:
        r6 = new java.util.zip.GZIPInputStream;
        r6.<init>(r5);
        r7 = new java.io.ByteArrayOutputStream;
        r7.<init>();
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4 = new byte[r4];	 Catch:{ Exception -> 0x0279 }
    L_0x026e:
        r10 = r6.read(r4);	 Catch:{ Exception -> 0x0279 }
        if (r10 <= 0) goto L_0x02d1;
    L_0x0274:
        r11 = 0;
        r7.write(r4, r11, r10);	 Catch:{ Exception -> 0x0279 }
        goto L_0x026e;
    L_0x0279:
        r4 = move-exception;
        r10 = "MessageHandler";
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02e0 }
        r12 = "uncompress data error ";
        r11.<init>(r12);	 Catch:{ all -> 0x02e0 }
        r12 = r4.toString();	 Catch:{ all -> 0x02e0 }
        r11 = r11.append(r12);	 Catch:{ all -> 0x02e0 }
        r11 = r11.toString();	 Catch:{ all -> 0x02e0 }
        r12 = 0;
        r12 = new java.lang.Object[r12];	 Catch:{ all -> 0x02e0 }
        com.taobao.accs.utl.ALog.e(r10, r11, r12);	 Catch:{ all -> 0x02e0 }
        r10 = "accs";
        r11 = "send_fail";
        r12 = "";
        r13 = "1";
        r14 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02e0 }
        r14.<init>();	 Catch:{ all -> 0x02e0 }
        r0 = r25;
        r15 = r0.b;	 Catch:{ all -> 0x02e0 }
        r14 = r14.append(r15);	 Catch:{ all -> 0x02e0 }
        r15 = " uncompress data error ";
        r14 = r14.append(r15);	 Catch:{ all -> 0x02e0 }
        r4 = r4.toString();	 Catch:{ all -> 0x02e0 }
        r4 = r14.append(r4);	 Catch:{ all -> 0x02e0 }
        r4 = r4.toString();	 Catch:{ all -> 0x02e0 }
        com.taobao.accs.utl.b.a(r10, r11, r12, r13, r4);	 Catch:{ all -> 0x02e0 }
        r6.close();	 Catch:{ Exception -> 0x02ce }
        r7.close();	 Catch:{ Exception -> 0x02ce }
        goto L_0x00e5;
    L_0x02ce:
        r4 = move-exception;
        goto L_0x00e5;
    L_0x02d1:
        r8 = r7.toByteArray();	 Catch:{ Exception -> 0x0279 }
        r6.close();	 Catch:{ Exception -> 0x02dd }
        r7.close();	 Catch:{ Exception -> 0x02dd }
        goto L_0x00e5;
    L_0x02dd:
        r4 = move-exception;
        goto L_0x00e5;
    L_0x02e0:
        r4 = move-exception;
        r6.close();	 Catch:{ Exception -> 0x0679 }
        r7.close();	 Catch:{ Exception -> 0x0679 }
    L_0x02e7:
        throw r4;
    L_0x02e8:
        r4 = com.taobao.accs.utl.ALog.Level.D;	 Catch:{ Exception -> 0x030f }
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x00f6;
    L_0x02f0:
        r4 = "MessageHandler";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r6 = "oriData:";
        r5.<init>(r6);	 Catch:{ Exception -> 0x030f }
        r6 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x030f }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.d(r4, r5, r6);	 Catch:{ Exception -> 0x030f }
        goto L_0x00f6;
    L_0x030f:
        r4 = move-exception;
        r5 = "MessageHandler";
        r6 = r4.toString();
        r7 = 0;
        r7 = new java.lang.Object[r7];
        com.taobao.accs.utl.ALog.e(r5, r6, r7);
        r5 = "accs";
        r6 = "send_fail";
        r7 = "";
        r8 = "1";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = r25;
        r10 = r0.b;
        r9 = r9.append(r10);
        r10 = r4.toString();
        r9 = r9.append(r10);
        r9 = r9.toString();
        com.taobao.accs.utl.b.a(r5, r6, r7, r8, r9);
        r4.printStackTrace();
        goto L_0x0209;
    L_0x034a:
        r4 = 0;
        r17 = r4;
        goto L_0x012f;
    L_0x034f:
        r4 = move-exception;
        r6 = -3;
        goto L_0x01c0;
    L_0x0353:
        r0 = r25;
        r0.a(r5, r6, r9);	 Catch:{ Exception -> 0x030f }
        goto L_0x01d6;
    L_0x035a:
        r4 = "MessageHandler";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r5.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.b;	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = " data ack/res reqMessage is null,";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x030f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x030f }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.e(r4, r5, r6);	 Catch:{ Exception -> 0x030f }
        goto L_0x01ec;
    L_0x0383:
        r4 = "MessageHandler";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r5.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.b;	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r6 = " contorl ACK reqMessage is null";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x030f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x030f }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.e(r4, r5, r6);	 Catch:{ Exception -> 0x030f }
        r4 = com.taobao.accs.utl.ALog.Level.D;	 Catch:{ Exception -> 0x030f }
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x03d0;
    L_0x03b2:
        r4 = "MessageHandler";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r6 = "Message not handled, body:";
        r5.<init>(r6);	 Catch:{ Exception -> 0x030f }
        r6 = new java.lang.String;	 Catch:{ Exception -> 0x030f }
        r6.<init>(r8);	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x030f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x030f }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.d(r4, r5, r6);	 Catch:{ Exception -> 0x030f }
    L_0x03d0:
        r4 = 1;
        r0 = r23;
        if (r0 != r4) goto L_0x0209;
    L_0x03d5:
        r4 = com.taobao.accs.data.Message.ReqType.DATA;	 Catch:{ Exception -> 0x030f }
        if (r7 != r4) goto L_0x0209;
    L_0x03d9:
        if (r16 == 0) goto L_0x0209;
    L_0x03db:
        r4 = "\\|";
        r0 = r16;
        r6 = r0.split(r4);	 Catch:{ Exception -> 0x030f }
        if (r6 == 0) goto L_0x0209;
    L_0x03e6:
        r4 = r6.length;	 Catch:{ Exception -> 0x030f }
        r5 = 2;
        if (r4 < r5) goto L_0x0209;
    L_0x03ea:
        r4 = "MessageHandler";
        r5 = "onPush";
        r7 = 0;
        r7 = new java.lang.Object[r7];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.d(r4, r5, r7);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x0403;
    L_0x03fc:
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        r4.commitUT();	 Catch:{ Exception -> 0x030f }
    L_0x0403:
        r4 = new com.taobao.accs.ut.statistics.d;	 Catch:{ Exception -> 0x030f }
        r4.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r0.j = r4;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x030f }
        r5 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x030f }
        r4.c = r5;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.i;	 Catch:{ Exception -> 0x030f }
        r5 = 1;
        r5 = r6[r5];	 Catch:{ Exception -> 0x030f }
        r4 = com.taobao.accs.utl.UtilityImpl.packageExist(r4, r5);	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x0636;
    L_0x0427:
        r4 = r6.length;	 Catch:{ Exception -> 0x030f }
        r5 = 3;
        if (r4 < r5) goto L_0x04d5;
    L_0x042b:
        r4 = 2;
        r4 = r6[r4];	 Catch:{ Exception -> 0x030f }
        r5 = r4;
    L_0x042f:
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        r4.e = r5;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r1 = r22;
        r4 = r0.c(r1);	 Catch:{ Exception -> 0x030f }
        if (r4 == 0) goto L_0x04d9;
    L_0x043f:
        r4 = "MessageHandler";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r6.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r7 = r0.b;	 Catch:{ Exception -> 0x030f }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x030f }
        r7 = " msg duplicate";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x030f }
        r6 = r6.toString();	 Catch:{ Exception -> 0x030f }
        r7 = 0;
        r7 = new java.lang.Object[r7];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.e(r4, r6, r7);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        r6 = 1;
        r4.h = r6;	 Catch:{ Exception -> 0x030f }
    L_0x046d:
        r4 = 1;
        r0 = r24;
        if (r0 != r4) goto L_0x0209;
    L_0x0472:
        r4 = "MessageHandler";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r6.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r7 = r0.b;	 Catch:{ Exception -> 0x030f }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x030f }
        r7 = " try to send ack dataId ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x030f }
        r6 = r6.toString();	 Catch:{ Exception -> 0x030f }
        r7 = 0;
        r7 = new java.lang.Object[r7];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.e(r4, r6, r7);	 Catch:{ Exception -> 0x030f }
        r13 = 0;
        r0 = r18;
        r4 = (int) r0;	 Catch:{ Exception -> 0x030f }
        r14 = (short) r4;	 Catch:{ Exception -> 0x030f }
        r10 = r16;
        r11 = r20;
        r12 = r21;
        r15 = r28;
        r16 = r9;
        r4 = com.taobao.accs.data.Message.buildPushAck(r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.i;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r7 = r0.b;	 Catch:{ Exception -> 0x030f }
        r6 = com.taobao.accs.net.a.a(r6, r7);	 Catch:{ Exception -> 0x030f }
        r7 = 1;
        r6.b(r4, r7);	 Catch:{ Exception -> 0x030f }
        r4 = r4.dataId;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r0.a(r4, r5);	 Catch:{ Exception -> 0x030f }
        if (r17 == 0) goto L_0x0209;
    L_0x04c5:
        r4 = "accs";
        r5 = "ack";
        r6 = "";
        r8 = 0;
        com.taobao.accs.utl.b.a(r4, r5, r6, r8);	 Catch:{ Exception -> 0x030f }
        goto L_0x0209;
    L_0x04d5:
        r4 = 0;
        r5 = r4;
        goto L_0x042f;
    L_0x04d9:
        r0 = r25;
        r1 = r22;
        r0.d(r1);	 Catch:{ Exception -> 0x030f }
        r4 = "MessageHandler";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r7.<init>();	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r10 = r0.b;	 Catch:{ Exception -> 0x030f }
        r7 = r7.append(r10);	 Catch:{ Exception -> 0x030f }
        r10 = " try deliver msg to ";
        r7 = r7.append(r10);	 Catch:{ Exception -> 0x030f }
        r10 = 1;
        r10 = r6[r10];	 Catch:{ Exception -> 0x030f }
        r7 = r7.append(r10);	 Catch:{ Exception -> 0x030f }
        r10 = "/";
        r7 = r7.append(r10);	 Catch:{ Exception -> 0x030f }
        r7 = r7.append(r5);	 Catch:{ Exception -> 0x030f }
        r7 = r7.toString();	 Catch:{ Exception -> 0x030f }
        r10 = 0;
        r10 = new java.lang.Object[r10];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.i(r4, r7, r10);	 Catch:{ Exception -> 0x030f }
        r11 = new android.content.Intent;	 Catch:{ Exception -> 0x030f }
        r4 = "com.taobao.accs.intent.action.RECEIVE";
        r11.<init>(r4);	 Catch:{ Exception -> 0x030f }
        r4 = 1;
        r4 = r6[r4];	 Catch:{ Exception -> 0x030f }
        r11.setPackage(r4);	 Catch:{ Exception -> 0x030f }
        r4 = "command";
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r11.putExtra(r4, r7);	 Catch:{ Exception -> 0x030f }
        r4 = r6.length;	 Catch:{ Exception -> 0x030f }
        r7 = 3;
        if (r4 < r7) goto L_0x0536;
    L_0x052d:
        r4 = "serviceId";
        r7 = 2;
        r7 = r6[r7];	 Catch:{ Exception -> 0x030f }
        r11.putExtra(r4, r7);	 Catch:{ Exception -> 0x030f }
    L_0x0536:
        r4 = "";
        r7 = r6.length;	 Catch:{ Exception -> 0x030f }
        r10 = 4;
        if (r7 < r10) goto L_0x0546;
    L_0x053d:
        r4 = 3;
        r4 = r6[r4];	 Catch:{ Exception -> 0x030f }
        r6 = "userInfo";
        r11.putExtra(r6, r4);	 Catch:{ Exception -> 0x030f }
    L_0x0546:
        r6 = "data";
        r11.putExtra(r6, r8);	 Catch:{ Exception -> 0x030f }
        r6 = "dataId";
        r0 = r21;
        r11.putExtra(r6, r0);	 Catch:{ Exception -> 0x030f }
        r6 = "packageName";
        r0 = r25;
        r7 = r0.i;	 Catch:{ Exception -> 0x030f }
        r7 = r7.getPackageName();	 Catch:{ Exception -> 0x030f }
        r11.putExtra(r6, r7);	 Catch:{ Exception -> 0x030f }
        r6 = "host";
        r0 = r28;
        r11.putExtra(r6, r0);	 Catch:{ Exception -> 0x030f }
        r6 = "bizAck";
        r0 = r17;
        r11.putExtra(r6, r0);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r0.a(r9, r11);	 Catch:{ Exception -> 0x030f }
        if (r17 == 0) goto L_0x0588;
    L_0x0579:
        r0 = r18;
        r6 = (int) r0;	 Catch:{ Exception -> 0x030f }
        r15 = (short) r6;	 Catch:{ Exception -> 0x030f }
        r10 = r25;
        r12 = r20;
        r13 = r16;
        r14 = r28;
        r10.a(r11, r12, r13, r14, r15);	 Catch:{ Exception -> 0x030f }
    L_0x0588:
        r0 = r25;
        r6 = r0.i;	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.data.e.a(r6, r11);	 Catch:{ Exception -> 0x030f }
        r10 = com.taobao.accs.utl.UTMini.getInstance();	 Catch:{ Exception -> 0x030f }
        r11 = 66001; // 0x101d1 float:9.2487E-41 double:3.2609E-319;
        r12 = "MsgToBussPush";
        r13 = "commandId=101";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r7 = "serviceId=";
        r6.<init>(r7);	 Catch:{ Exception -> 0x030f }
        r6 = r6.append(r5);	 Catch:{ Exception -> 0x030f }
        r7 = " dataId=";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x030f }
        r14 = r6.toString();	 Catch:{ Exception -> 0x030f }
        r6 = 212; // 0xd4 float:2.97E-43 double:1.047E-321;
        r15 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x030f }
        r10.commitEvent(r11, r12, r13, r14, r15);	 Catch:{ Exception -> 0x030f }
        r6 = "accs";
        r7 = "to_buss";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r11 = "1commandId=101serviceId=";
        r10.<init>(r11);	 Catch:{ Exception -> 0x030f }
        r10 = r10.append(r5);	 Catch:{ Exception -> 0x030f }
        r10 = r10.toString();	 Catch:{ Exception -> 0x030f }
        r12 = 0;
        com.taobao.accs.utl.b.a(r6, r7, r10, r12);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.j;	 Catch:{ Exception -> 0x030f }
        r0 = r21;
        r6.b = r0;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.j;	 Catch:{ Exception -> 0x030f }
        r6.i = r4;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.j;	 Catch:{ Exception -> 0x030f }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r7.<init>();	 Catch:{ Exception -> 0x030f }
        if (r8 != 0) goto L_0x0634;
    L_0x05f6:
        r4 = 0;
    L_0x05f7:
        r4 = r7.append(r4);	 Catch:{ Exception -> 0x030f }
        r4 = r4.toString();	 Catch:{ Exception -> 0x030f }
        r6.f = r4;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r6 = r0.i;	 Catch:{ Exception -> 0x030f }
        r6 = com.taobao.accs.utl.UtilityImpl.getDeviceId(r6);	 Catch:{ Exception -> 0x030f }
        r4.a = r6;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.j;	 Catch:{ Exception -> 0x030f }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x030f }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x030f }
        r4.d = r6;	 Catch:{ Exception -> 0x030f }
        r10 = new com.taobao.accs.ut.monitor.TrafficsMonitor$a;	 Catch:{ Exception -> 0x030f }
        r12 = anet.channel.GlobalAppRuntimeInfo.isAppBackground();	 Catch:{ Exception -> 0x030f }
        r0 = r27;
        r4 = r0.length;	 Catch:{ Exception -> 0x030f }
        r14 = (long) r4;	 Catch:{ Exception -> 0x030f }
        r11 = r5;
        r13 = r28;
        r10.<init>(r11, r12, r13, r14);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r0.a(r10);	 Catch:{ Exception -> 0x030f }
        goto L_0x046d;
    L_0x0634:
        r4 = r8.length;	 Catch:{ Exception -> 0x030f }
        goto L_0x05f7;
    L_0x0636:
        r4 = "MessageHandler";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f }
        r7 = "package ";
        r5.<init>(r7);	 Catch:{ Exception -> 0x030f }
        r7 = 1;
        r7 = r6[r7];	 Catch:{ Exception -> 0x030f }
        r5 = r5.append(r7);	 Catch:{ Exception -> 0x030f }
        r7 = " not exist, unbind it";
        r5 = r5.append(r7);	 Catch:{ Exception -> 0x030f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x030f }
        r7 = 0;
        r7 = new java.lang.Object[r7];	 Catch:{ Exception -> 0x030f }
        com.taobao.accs.utl.ALog.e(r4, r5, r7);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r4 = r0.i;	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r5 = r0.b;	 Catch:{ Exception -> 0x030f }
        r4 = com.taobao.accs.net.a.a(r4, r5);	 Catch:{ Exception -> 0x030f }
        r0 = r25;
        r5 = r0.i;	 Catch:{ Exception -> 0x030f }
        r7 = 1;
        r6 = r6[r7];	 Catch:{ Exception -> 0x030f }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r5 = com.taobao.accs.data.Message.buildUnbindApp(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x030f }
        r6 = 1;
        r4.b(r5, r6);	 Catch:{ Exception -> 0x030f }
        goto L_0x0209;
    L_0x0679:
        r5 = move-exception;
        goto L_0x02e7;
        */
    }

    private void a(Message message, byte[] bArr, byte[] bArr2, String str) {
        int i = ErrorCode.FileNotExist;
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("MessageHandler", new StringBuilder("parse Json:").append(jSONObject.toString()).toString(), new Object[0]);
            }
            i = jSONObject.getInt(Constants.KEY_HTTP_CODE);
            if (i == 200) {
                String string;
                switch (message.command.intValue()) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        jSONObject = jSONObject.getJSONObject(SocializeConstants.JSON_DATA);
                        UtilityImpl.setDeviceToken(this.i, com.taobao.accs.utl.d.a(jSONObject, Constants.KEY_DEVICE_TOKEN, null));
                        UtilityImpl.utdidChanged();
                        UtilityImpl.saveUtdid(this.i);
                        if (jSONObject != null) {
                            JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_PACKAGE_NAMES);
                            if (jSONArray != null) {
                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                    string = jSONArray.getString(i2);
                                    if (UtilityImpl.packageExist(this.i, string)) {
                                        com.taobao.accs.client.b.a(this.i).a(message.m);
                                    } else {
                                        ALog.e("MessageHandler", "unbind app", Constants.KEY_ELECTION_PKG, string);
                                        com.taobao.accs.net.a.a(this.i, 0).b(Message.buildUnbindApp(this.i, string, null, null, null), true);
                                    }
                                }
                            }
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        com.taobao.accs.client.b.a(this.i).b(message.m);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        com.taobao.accs.client.b.a(this.i).a(message.m, message.userinfo);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        com.taobao.accs.client.b a = com.taobao.accs.client.b.a(this.i);
                        String str2 = message.m;
                        string = message.userinfo;
                        a.e(str2);
                        break;
                }
            } else if (message.command.intValue() == 3 && i == 300) {
                com.taobao.accs.client.b.a(this.i).b(message.m);
            }
        } catch (Throwable th) {
            ALog.e("MessageHandler", "handleControlMessage", th, new Object[0]);
            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, "handleControlMessage", a.d, this.b + th.toString());
        }
        a(message, i, null, bArr, null);
        a(new TrafficsMonitor.a(message.serviceId, GlobalAppRuntimeInfo.isAppBackground(), str, (long) bArr2.length));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<com.taobao.accs.base.TaoBaseService.ExtHeaderType, java.lang.String> a(com.taobao.accs.utl.g r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.data.b.a(com.taobao.accs.utl.g):java.util.Map<com.taobao.accs.base.TaoBaseService$ExtHeaderType, java.lang.String>");
        /*
        this = this;
        r0 = 0;
        r2 = 0;
        if (r13 != 0) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r4 = r13.b();	 Catch:{ Exception -> 0x0079 }
        r1 = com.taobao.accs.utl.ALog.Level.D;	 Catch:{ Exception -> 0x0079 }
        r1 = com.taobao.accs.utl.ALog.isPrintLog(r1);	 Catch:{ Exception -> 0x0079 }
        if (r1 == 0) goto L_0x002a;
    L_0x0011:
        r1 = "MessageHandler";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0079 }
        r5 = "extHeaderLen:";
        r3.<init>(r5);	 Catch:{ Exception -> 0x0079 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0079 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0079 }
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0079 }
        com.taobao.accs.utl.ALog.d(r1, r3, r5);	 Catch:{ Exception -> 0x0079 }
    L_0x002a:
        r1 = r2;
    L_0x002b:
        if (r1 >= r4) goto L_0x0004;
    L_0x002d:
        r3 = r13.b();	 Catch:{ Exception -> 0x0079 }
        r1 = r1 + 2;
        r5 = 64512; // 0xfc00 float:9.04E-41 double:3.1873E-319;
        r5 = r5 & r3;
        r5 = r5 >> 10;
        r3 = r3 & 1023;
        r5 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.valueOf(r5);	 Catch:{ Exception -> 0x0079 }
        r6 = r13.a(r3);	 Catch:{ Exception -> 0x0079 }
        r3 = r3 + r1;
        if (r5 == 0) goto L_0x008e;
    L_0x0046:
        if (r0 != 0) goto L_0x008c;
    L_0x0048:
        r1 = new java.util.HashMap;	 Catch:{ Exception -> 0x0079 }
        r1.<init>();	 Catch:{ Exception -> 0x0079 }
    L_0x004d:
        r1.put(r5, r6);	 Catch:{ Exception -> 0x0087 }
    L_0x0050:
        r0 = com.taobao.accs.utl.ALog.Level.D;	 Catch:{ Exception -> 0x0087 }
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);	 Catch:{ Exception -> 0x0087 }
        if (r0 == 0) goto L_0x0076;
    L_0x0058:
        r0 = "MessageHandler";
        r7 = "";
        r8 = 4;
        r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x0087 }
        r9 = 0;
        r10 = "extHeaderType";
        r8[r9] = r10;	 Catch:{ Exception -> 0x0087 }
        r9 = 1;
        r8[r9] = r5;	 Catch:{ Exception -> 0x0087 }
        r5 = 2;
        r9 = "value";
        r8[r5] = r9;	 Catch:{ Exception -> 0x0087 }
        r5 = 3;
        r8[r5] = r6;	 Catch:{ Exception -> 0x0087 }
        com.taobao.accs.utl.ALog.d(r0, r7, r8);	 Catch:{ Exception -> 0x0087 }
    L_0x0076:
        r0 = r1;
        r1 = r3;
        goto L_0x002b;
    L_0x0079:
        r1 = move-exception;
    L_0x007a:
        r3 = "MessageHandler";
        r4 = "parseExtHeader";
        r2 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.e(r3, r4, r1, r2);
        goto L_0x0004;
    L_0x0087:
        r0 = move-exception;
        r11 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x007a;
    L_0x008c:
        r1 = r0;
        goto L_0x004d;
    L_0x008e:
        r1 = r0;
        goto L_0x0050;
        */
    }

    public void a(Message message, int i) {
        a(message, i, null, null, null);
    }

    public void a(Message message, int i, Map<ExtHeaderType, String> map) {
        a(message, i, null, null, (Map) map);
    }

    public void a(Message message, int i, ReqType reqType, byte[] bArr, Map<ExtHeaderType, String> map) {
        if (message.command == null || message.getType() < 0 || message.getType() == 2) {
            ALog.d("MessageHandler", "onError, skip ping/ack", new Object[0]);
            return;
        }
        byte[] bArr2;
        int i2;
        if (message.cunstomDataId != null) {
            this.a.remove(message.cunstomDataId);
        }
        if (this.e.checkAntiBrush(message.host, map)) {
            i = com.taobao.accs.ErrorCode.SERVIER_ANTI_BRUSH;
            bArr = null;
            map = null;
            reqType = null;
        }
        int a = this.d.a(r15, message.serviceId);
        if (a != 0) {
            Object obj = a == 2 ? com.taobao.accs.ErrorCode.SERVIER_HIGH_LIMIT : a == 3 ? com.taobao.accs.ErrorCode.SERVIER_HIGH_LIMIT_BRUSH : com.taobao.accs.ErrorCode.SERVIER_LOW_LIMIT;
            bArr2 = null;
            map = null;
            reqType = null;
            i2 = a;
        } else {
            bArr2 = bArr;
            i2 = i;
        }
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("MessageHandler", new StringBuilder("onResult command:").append(message.command).append(" erorcode:").append(i2).toString(), new Object[0]);
        }
        if (message.command.intValue() == 102) {
            return;
        }
        if (message.command.intValue() == 105) {
            AccsAbstractDataListener listener = GlobalClientInfo.getInstance(this.i).getListener(com.taobao.accs.internal.b.ELECTION_SERVICE_ID);
            if (listener != null) {
                listener.onResponse(com.taobao.accs.internal.b.ELECTION_SERVICE_ID, message.cunstomDataId, i2, bArr2, null);
                return;
            } else {
                ALog.e("MessageHandler", "onResult election listener null", new Object[0]);
                return;
            }
        }
        if (message.isCancel) {
            ALog.e("MessageHandler", this.b + " message is cancel! command:" + message.command, new Object[0]);
        } else if (!b(i2) || message.command.intValue() == 100 || message.retryTimes > Message.CONTROL_MAX_RETRY_TIMES) {
            ALog.d("MessageHandler", "prepare send broadcast", new Object[0]);
            Intent c = c(message);
            c.putExtra(Constants.KEY_ERROR_CODE, i2);
            ReqType valueOf = ReqType.valueOf((message.f >> 13) & 3);
            if (reqType == ReqType.RES || valueOf == ReqType.REQ) {
                c.putExtra(Constants.KEY_SEND_TYPE, Constants.SEND_TYPE_RES);
            }
            if (i2 == 200) {
                c.putExtra(SocializeConstants.JSON_DATA, bArr2);
            }
            a(r15, c);
            e.a(this.i, c);
            if (!TextUtils.isEmpty(message.serviceId)) {
                UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss0", new StringBuilder("commandId=").append(message.command).toString(), new StringBuilder("serviceId=").append(message.serviceId).append(" errorCode=").append(i2).append(" dataId=").append(message.dataId).toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_TO_BUSS, new StringBuilder("1commandId=").append(message.command).append("serviceId=").append(message.serviceId).toString(), 0.0d);
            }
        } else {
            message.startSendTime = System.currentTimeMillis();
            message.retryTimes++;
            com.taobao.accs.net.a.a(this.i, 0).b(message, true);
        }
        NetPerformanceMonitor netPermanceMonitor = message.getNetPermanceMonitor();
        if (netPermanceMonitor != null) {
            netPermanceMonitor.onToBizDate();
            String toString = message.host == null ? null : message.host.toString();
            if (i2 == 200) {
                netPermanceMonitor.setRet(true);
                if (message.retryTimes > 0) {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, "succ", 0.0d);
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, new StringBuilder("succ_").append(message.retryTimes).toString(), 0.0d);
                } else {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQUEST, toString);
                }
            } else {
                if (message.retryTimes > 0) {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, new StringBuilder("fail\uff3f").append(i2).toString(), 0.0d);
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, MsgConstant.KEY_FAIL, 0.0d);
                } else if (i2 != -13) {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQUEST, toString, UtilityImpl.int2String(i2), this.b + message.serviceId + message.timeout);
                }
                netPermanceMonitor.setRet(false);
                netPermanceMonitor.setFailReason(i2);
            }
            AppMonitor.getInstance().commitStat(message.getNetPermanceMonitor());
        }
        b(message, i2);
    }

    private boolean b(int i) {
        return i == -1 || i == -9 || i == -10 || i == -11;
    }

    public void b() {
        ALog.d("MessageHandler", "onSendPing", new Object[0]);
        synchronized (b.class) {
            this.h = true;
        }
    }

    public void c() {
        ALog.d("MessageHandler", "onRcvPing", new Object[0]);
        synchronized (b.class) {
            this.h = false;
        }
    }

    public boolean d() {
        return this.h;
    }

    public void a(Message message) {
        if (!(this.k == null || message.cunstomDataId == null || message.serviceId == null || this.k.cunstomDataId != message.cunstomDataId || this.k.serviceId != message.serviceId)) {
            UTMini.getInstance().commitEvent(UT.EVENT_ID, "SEND_REPEAT", message.serviceId, message.cunstomDataId, Long.valueOf(Thread.currentThread().getId()));
        }
        if (message.getType() != -1 && message.getType() != 2 && !message.isAck) {
            this.g.put(message.getDataId(), message);
        }
    }

    public void a(int i) {
        this.h = false;
        String[] strArr = (String[]) this.g.keySet().toArray(new String[0]);
        if (strArr != null && strArr.length > 0) {
            ALog.d("MessageHandler", "onNetworkFail", new Object[0]);
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Message message = (Message) this.g.remove(strArr[i2]);
                if (message != null) {
                    a(message, i);
                }
            }
        }
    }

    public void b(Message message) {
        if (this.g.keySet() != null && this.g.keySet().size() > 0) {
            for (String str : this.g.keySet()) {
                Message message2 = (Message) this.g.get(str);
                if (!(message2 == null || message2.command == null || !message2.getPackageName().equals(message.getPackageName()))) {
                    switch (message.command.intValue()) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            if (message2.command.intValue() == 1 || message2.command.intValue() == 2) {
                                message2.isCancel = true;
                            }
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            if (message2.command.intValue() == 3 || message2.command.intValue() == 4) {
                                message2.isCancel = true;
                            }
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        case R.styleable.Toolbar_contentInsetEnd:
                            if (message2.command.intValue() == 5 || message2.command.intValue() == 6) {
                                message2.isCancel = true;
                            }
                            break;
                    }
                }
                if (message2 != null && message2.isCancel) {
                    ALog.e("MessageHandler", "cancelControlMessage", IntentUtil.AGOO_COMMAND, message2.command);
                }
            }
        }
    }

    public int e() {
        return this.g.size();
    }

    public Collection<Message> f() {
        return this.g.values();
    }

    public Message a(String str) {
        return (Message) this.g.get(str);
    }

    public Message b(String str) {
        return !TextUtils.isEmpty(str) ? (Message) this.g.remove(str) : null;
    }

    private boolean c(String str) {
        return !TextUtils.isEmpty(str) && this.l.containsKey(str);
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str) && !this.l.containsKey(str)) {
            this.l.put(str, str);
            j();
        }
    }

    private void i() {
        try {
            File file = new File(this.i.getDir(h.NAMESPACE, 0), Constants.SHARED_MESSAGE_ID_FILE);
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        this.l.put(readLine, readLine);
                    } else {
                        bufferedReader.close();
                        return;
                    }
                }
            }
            ALog.d("MessageHandler", "message file not exist", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void j() {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.i.getDir(h.NAMESPACE, 0), Constants.SHARED_MESSAGE_ID_FILE));
            fileWriter.write(a.d);
            for (String str : this.l.keySet()) {
                fileWriter.append(str + "\r\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Intent c(Message message) {
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(message.m);
        intent.putExtra(IntentUtil.AGOO_COMMAND, message.command);
        intent.putExtra(Constants.KEY_SERVICE_ID, message.serviceId);
        intent.putExtra(Constants.KEY_USER_ID, message.userinfo);
        if (message.command != null && message.command.intValue() == 100) {
            intent.putExtra(Constants.KEY_DATA_ID, message.cunstomDataId);
        }
        return intent;
    }

    private void a(Map<ExtHeaderType, String> map, Intent intent) {
        if (map != null && intent != null) {
            ExtHeaderType[] values = ExtHeaderType.values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                ExtHeaderType extHeaderType = values[i];
                String str = (String) map.get(extHeaderType);
                if (!TextUtils.isEmpty(str)) {
                    intent.putExtra(extHeaderType.toString(), str);
                }
            }
        }
    }

    private void a(Intent intent, String str, String str2, String str3, short s) {
        if (intent != null) {
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra(SocialConstants.PARAM_SOURCE, str);
            }
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra(Constants.KEY_TARGET, str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                intent.putExtra(com.taobao.accs.internal.b.ELECTION_KEY_HOST, str3);
            }
            intent.putExtra(Constants.KEY_FLAGS, s);
        }
    }

    public d g() {
        return this.j;
    }

    private void b(Message message, int i) {
        if (message != null) {
            String deviceId = UtilityImpl.getDeviceId(this.i);
            String str = System.currentTimeMillis();
            boolean z = true;
            if (i != 200) {
                z = false;
            }
            switch (message.command.intValue()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    com.taobao.accs.ut.statistics.a aVar = new com.taobao.accs.ut.statistics.a();
                    aVar.a = deviceId;
                    aVar.b = str;
                    aVar.c = z;
                    aVar.a(i);
                    aVar.commitUT();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    com.taobao.accs.ut.statistics.b bVar = new com.taobao.accs.ut.statistics.b();
                    bVar.a = deviceId;
                    bVar.b = str;
                    bVar.c = z;
                    bVar.e = message.userinfo;
                    bVar.a(i);
                    bVar.commitUT();
                default:
                    break;
            }
        }
    }

    private void a(String str, String str2) {
        e eVar = new e();
        eVar.a = UtilityImpl.getDeviceId(this.i);
        eVar.c = str;
        eVar.d = System.currentTimeMillis();
        eVar.f = a.d;
        eVar.e = str2;
        eVar.b = a.d;
        eVar.commitUT();
    }

    public void h() {
        try {
            com.taobao.accs.common.a.a().execute(this.m);
        } catch (Throwable th) {
            ALog.e("MessageHandler", "restoreTraffics", th, new Object[0]);
        }
    }

    public void a(TrafficsMonitor.a aVar) {
        try {
            com.taobao.accs.common.a.a().execute(new c(this, aVar));
        } catch (Throwable th) {
            ALog.e("MessageHandler", "addTrafficsInfo", th, new Object[0]);
        }
    }
}
