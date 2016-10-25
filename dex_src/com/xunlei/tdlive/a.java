package com.xunlei.tdlive;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.app.statistic.c;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetReportLogRequest;
import com.xunlei.tdlive.protocol.XLLiveGetReportLogRequest.GetReportLogResp;
import com.xunlei.tdlive.protocol.XLLiveReportLogRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;

// compiled from: CollectService.java
public class a implements JsonCallBack, ObjectCallBack, Runnable {
    private static Context a;
    private static a b;
    private Context c;
    private Thread d;
    private SQLiteOpenHelper e;
    private float f;
    private float g;
    private float h;
    private float i;
    private String j;
    private String k;
    private String l;
    private String m;
    private XLLiveReportLogRequest n;
    private XLLiveGetReportLogRequest o;
    private GetReportLogResp p;
    private volatile Looper q;
    private volatile b r;

    public static void a(Context context, String str) {
        if (context != null) {
            a = context.getApplicationContext();
        }
        if (a != null && str != null && str.length() > 0) {
            a(a, new Intent(a, a.class).setAction("com.xunlei.tdlive.sdk.CollectService.ACTION_INIT").putExtra("report_url", str));
        }
    }

    public static void a() {
        if (a != null) {
            b(a, new Intent(a, a.class));
        }
    }

    public static void a(String str, String str2) {
        if (a != null) {
            a(a, new Intent(a, a.class).setAction("com.xunlei.tdlive.sdk.CollectService.ACTION_SET_PARAM").putExtra("userid", str).putExtra("sessionid", str2));
        }
    }

    public static void a(String str) {
        if (a != null) {
            a(a, new Intent(a, a.class).setAction("com.xunlei.tdlive.sdk.CollectService.ACTION_REPORT").putExtra("data_type", "error").putExtra(SocialConstants.PARAM_SEND_MSG, str));
        }
    }

    public static void a(String str, int i, int i2, int i3, int i4) {
        if (a != null) {
            a(a, new Intent(a, a.class).setAction("com.xunlei.tdlive.sdk.CollectService.ACTION_REPORT").putExtra("data_type", "playerstream").putExtra("roomid", str).putExtra("fps", i).putExtra(IXAdRequestInfo.WIDTH, i2).putExtra(IXAdRequestInfo.HEIGHT, i3).putExtra("bitrate", i4));
        }
    }

    public static void a(a aVar, boolean z) {
        if (aVar != null && a != null) {
            if (z) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.xunlei.tdlive.sdk.CollectService.CALLBACK_MONITOR_PSI");
                intentFilter.addAction("com.xunlei.tdlive.sdk.CollectService.CALLBACK_MONITOR_SPD");
                try {
                    a.registerReceiver(aVar, intentFilter);
                    return;
                } catch (Exception e) {
                }
            }
            try {
                a.unregisterReceiver(aVar);
            } catch (Exception e2) {
            }
        }
    }

    public static void b(String str) {
        if (a != null) {
            Context context = a;
            Intent action = new Intent(a, a.class).setAction("com.xunlei.tdlive.sdk.CollectService.ACTION_MONITOR_ROOM");
            String str2 = "roomid";
            if (str == null) {
                str = com.umeng.a.d;
            }
            a(context, action.putExtra(str2, str));
        }
    }

    private static void a(Context context, Intent intent) {
        if (b == null) {
            a aVar = new a(context.getApplicationContext());
            b = aVar;
            aVar.c();
        }
        b.a(intent, 0, 0);
    }

    private static void b(Context context, Intent intent) {
        if (b != null) {
            b.d();
            b = null;
        }
    }

    private a(Context context) {
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = com.umeng.a.d;
        this.k = com.umeng.a.d;
        this.l = com.umeng.a.d;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new GetReportLogResp();
        this.c = context;
    }

    public Context b() {
        return this.c.getApplicationContext();
    }

    public void c() {
        HandlerThread handlerThread = new HandlerThread("CollectService[Hanlder]");
        handlerThread.start();
        this.q = handlerThread.getLooper();
        this.r = new b(this, this.q);
        this.e = new b(this, b(), "collectservice.db", null, 102);
        this.d = new Thread(this, "CollectService[Speed]");
        this.d.start();
    }

    public void d() {
        this.q.quit();
        this.d.interrupt();
        this.e.close();
    }

    public int a(Intent intent, int i, int i2) {
        Message obtainMessage = this.r.obtainMessage();
        obtainMessage.arg1 = i2;
        obtainMessage.obj = intent;
        this.r.sendMessage(obtainMessage);
        return 1;
    }

    protected void a(Intent intent) {
        if (intent == null) {
            return;
        }
        String str;
        String str2;
        if (intent.getAction().equals("com.xunlei.tdlive.sdk.CollectService.ACTION_INIT")) {
            str = this.k;
            str2 = this.l;
            String stringExtra = intent.getStringExtra("report_url");
            this.j = stringExtra;
            this.n = new XLLiveReportLogRequest(str, str2, stringExtra, this);
        } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.CollectService.ACTION_SET_PARAM")) {
            str = intent.getStringExtra("userid");
            this.k = str;
            str2 = intent.getStringExtra("sessionid");
            this.l = str2;
            this.n = new XLLiveReportLogRequest(str, str2, this.j, this);
        } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.CollectService.ACTION_MONITOR_ROOM")) {
            if (intent.getStringExtra("roomid") == null) {
                this.o = null;
            } else {
                this.o = new XLLiveGetReportLogRequest(this.k, this.l, intent.getStringExtra("roomid"));
            }
        } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.CollectService.ACTION_REPORT")) {
            JsonWrapper jsonWrapper = new JsonWrapper("{}");
            jsonWrapper.putString(c.a, ac.b());
            jsonWrapper.putString("total_down_speed", this.f);
            jsonWrapper.putString("total_up_speed", this.g);
            jsonWrapper.putString("process_down_speed", this.h);
            jsonWrapper.putString("process_up_speed", this.i);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str3 : extras.keySet()) {
                    Object obj = extras.get(str3);
                    if (obj != null) {
                        jsonWrapper.putObject(str3, obj);
                    }
                }
            }
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SocializeConstants.JSON_DATA, jsonWrapper.toString());
                this.e.getWritableDatabase().insert("CollectService", null, contentValues);
            } catch (Exception e) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.a.run():void");
        /*
        this = this;
        r10 = 0;
        r21 = android.os.Process.myUid();
        r8 = android.net.TrafficStats.getUidRxBytes(r21);
        r6 = android.net.TrafficStats.getUidTxBytes(r21);
        r4 = android.net.TrafficStats.getTotalRxBytes();
        r2 = android.net.TrafficStats.getTotalTxBytes();
    L_0x0016:
        r12 = 1;
        r18 = r10 + r12;
        r10 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        java.lang.Thread.sleep(r10);	 Catch:{ InterruptedException -> 0x01bf }
        r16 = android.net.TrafficStats.getUidRxBytes(r21);
        r14 = android.net.TrafficStats.getUidTxBytes(r21);
        r12 = android.net.TrafficStats.getTotalRxBytes();
        r10 = android.net.TrafficStats.getTotalTxBytes();
        r4 = r12 - r4;
        r4 = (float) r4;
        r5 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r4 = r4 / r5;
        r0 = r24;
        r0.f = r4;
        r2 = r10 - r2;
        r2 = (float) r2;
        r3 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r2 = r2 / r3;
        r0 = r24;
        r0.g = r2;
        r2 = r16 - r8;
        r2 = (float) r2;
        r3 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r2 = r2 / r3;
        r0 = r24;
        r0.h = r2;
        r2 = r14 - r6;
        r2 = (float) r2;
        r3 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r2 = r2 / r3;
        r0 = r24;
        r0.i = r2;
        r2 = "CollectService";
        r3 = new java.lang.StringBuilder;
        r4 = "down speed:";
        r3.<init>(r4);
        r0 = r24;
        r4 = r0.f;
        r3 = r3.append(r4);
        r4 = ", up speed:";
        r3 = r3.append(r4);
        r0 = r24;
        r4 = r0.g;
        r3 = r3.append(r4);
        r4 = ", uid down speed:";
        r3 = r3.append(r4);
        r0 = r24;
        r4 = r0.h;
        r3 = r3.append(r4);
        r4 = ", uid up speed:";
        r3 = r3.append(r4);
        r0 = r24;
        r4 = r0.i;
        r3 = r3.append(r4);
        r4 = ", tick:";
        r3 = r3.append(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        com.xunlei.tdlive.util.XLog.d(r2, r3);
        r0 = r24;
        r2 = r0.o;
        if (r2 == 0) goto L_0x00c4;
    L_0x00b1:
        r0 = r24;
        r2 = r0.o;
        r2 = r2.tryLock();
        if (r2 == 0) goto L_0x00c4;
    L_0x00bb:
        r0 = r24;
        r2 = r0.o;
        r0 = r24;
        r2.send(r0);
    L_0x00c4:
        r0 = r24;
        r2 = r0.f;
        r0 = r24;
        r3 = r0.g;
        r0 = r24;
        r4 = r0.h;
        r0 = r24;
        r5 = r0.i;
        r0 = r24;
        r0.a(r2, r3, r4, r5);
        r24.e();
        r2 = 10;
        r2 = r18 % r2;
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x01b6;
    L_0x00e6:
        r0 = r24;
        r2 = r0.e;
        if (r2 == 0) goto L_0x01b6;
    L_0x00ec:
        r0 = r24;
        r2 = r0.n;
        if (r2 == 0) goto L_0x01b6;
    L_0x00f2:
        r0 = r24;
        r2 = r0.m;
        if (r2 != 0) goto L_0x01b6;
    L_0x00f8:
        r20 = 0;
        r22 = new com.xunlei.tdlive.modal.JsonWrapper;	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r2 = "[]";
        r0 = r22;
        r0.<init>(r2);	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r23 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r23.<init>();	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r2 = "(";
        r0 = r23;
        r0.append(r2);	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r0 = r24;
        r2 = r0.e;	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r2 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
        r3 = "CollectService";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x01c6, all -> 0x01af }
    L_0x0126:
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        if (r3 == 0) goto L_0x0170;
    L_0x012c:
        r3 = r22.getLength();	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 >= r4) goto L_0x0170;
    L_0x0134:
        r3 = "_id";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = "data";
        r4 = r2.getColumnIndex(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = r2.getString(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r5 = new com.xunlei.tdlive.modal.JsonWrapper;	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r5.<init>(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r0 = r22;
        r0.put(r5);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r0 = r23;
        r3 = r0.append(r3);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = ", ";
        r3.append(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        goto L_0x0126;
    L_0x0161:
        r3 = move-exception;
    L_0x0162:
        if (r2 == 0) goto L_0x01b6;
    L_0x0164:
        r2.close();
        r2 = r10;
        r4 = r12;
        r6 = r14;
        r8 = r16;
        r10 = r18;
        goto L_0x0016;
    L_0x0170:
        r3 = "0)";
        r0 = r23;
        r0.append(r3);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r3 = r22.getLength();	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        if (r3 <= 0) goto L_0x01a1;
    L_0x017e:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = "DELETE FROM CollectService WHERE _id IN";
        r3.<init>(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = r23.toString();	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r0 = r24;
        r0.m = r3;	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r0 = r24;
        r3 = r0.n;	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r4 = r22.toString();	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
        r3.send(r4);	 Catch:{ Exception -> 0x0161, all -> 0x01c1 }
    L_0x01a1:
        if (r2 == 0) goto L_0x01b6;
    L_0x01a3:
        r2.close();
        r2 = r10;
        r4 = r12;
        r6 = r14;
        r8 = r16;
        r10 = r18;
        goto L_0x0016;
    L_0x01af:
        r2 = move-exception;
    L_0x01b0:
        if (r20 == 0) goto L_0x01b5;
    L_0x01b2:
        r20.close();
    L_0x01b5:
        throw r2;
    L_0x01b6:
        r2 = r10;
        r4 = r12;
        r6 = r14;
        r8 = r16;
        r10 = r18;
        goto L_0x0016;
    L_0x01bf:
        r2 = move-exception;
        return;
    L_0x01c1:
        r3 = move-exception;
        r20 = r2;
        r2 = r3;
        goto L_0x01b0;
    L_0x01c6:
        r2 = move-exception;
        r2 = r20;
        goto L_0x0162;
        */
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            try {
                this.e.getWritableDatabase().execSQL(this.m);
            } catch (Exception e) {
                XLog.e("CollectService", new StringBuilder("delete ").append(this.m).append(" error, msg:").append(e.toString()).toString());
            }
            this.m = null;
        }
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && obj != null) {
            this.p = (GetReportLogResp) obj;
            e();
        }
    }

    private void a(float f, float f2, float f3, float f4) {
        b().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.CollectService.CALLBACK_MONITOR_SPD").putExtra("tdp", f).putExtra("tup", f2).putExtra("udp", f3).putExtra("uup", f4));
    }

    private void e() {
        String[] strArr = new String[]{"UNK", "UNK", "2G", "3G", "4G", "UNK", "UNK", "UNK", "UNK", UtilityImpl.NET_TYPE_WIFI};
        b().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.CollectService.CALLBACK_MONITOR_PSI").putExtra(SocialConstants.PARAM_SEND_MSG, new StringBuilder("\u4e3b\u64ad:").append(this.p.data.cmid).append(", APP:").append(this.p.data.appver).append(", \u623f\u95f4:").append(this.p.data.roomid).append("\r\n\u7cfb\u7edf:").append(this.p.data.os).append(SocializeConstants.OP_OPEN_PAREN).append(this.p.data.osver).append("), \u578b\u53f7:").append(this.p.data.model).append("\r\n\u7f51\u7edc:").append(strArr[this.p.data.net]).append(", \u4e0a\u884c:").append(ac.a((long) this.p.data.up_speed, 1)).append(", \u4e0b\u884c:").append(ac.a((long) this.p.data.down_speed, 1)).append("\r\n\u6d41:").append(new String[]{"\u672a\u77e5", "\u6c34\u6676", "\u7f51\u5bbf"}[this.p.data.stream_type]).append(", W:").append(this.p.data.w).append(", H:").append(this.p.data.h).append(", FPS:").append(this.p.data.fps).append(", Bitrate:").append(this.p.data.bitrate).append("\r\n\r\n").toString() + "\u89c2\u4f17:" + this.k + ", APP:" + ac.d() + "\r\n\u7cfb\u7edf:android(" + ac.c() + "), \u578b\u53f7:" + ac.a(false) + "\r\n\u7f51\u7edc:" + ac.f() + ", \u4e0a\u884c:" + ac.a((long) ((int) this.g), 1) + ", \u4e0b\u884c:" + ac.a((long) ((int) this.f), 1) + "\r\n"));
    }
}
