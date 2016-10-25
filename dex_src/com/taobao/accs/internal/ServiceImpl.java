package com.taobao.accs.internal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.alipay.sdk.util.k;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.b.a;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.Message.ReqType;
import com.taobao.accs.data.b;
import com.taobao.accs.data.e;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.open.SocialConstants;
import com.uc.addon.sdk.remote.Tabs;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.service.IMessageService.Stub;

// compiled from: Taobao
public class ServiceImpl extends b {
    private static a i;
    private Service a;
    private Context b;
    private com.taobao.accs.net.a c;
    private b d;
    private com.taobao.accs.client.b e;
    private HandlerThread f;
    private long g;
    private long h;
    private String j;
    private ScheduledThreadPoolExecutor k;
    private final Stub l;

    static /* synthetic */ void d() {
    }

    public ServiceImpl(Service service) {
        super(service);
        this.a = null;
        this.c = null;
        this.j = UtilityImpl.NET_TYPE_UNKNOWN;
        this.l = new Stub() {
            public boolean ping() throws RemoteException {
                return true;
            }

            public void probe() throws RemoteException {
                ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao begin......messageServiceBinder [probe]", new Object[0]);
                ServiceImpl.this.k.execute(new h(this));
            }
        };
        this.a = service;
        this.b = service.getApplicationContext();
    }

    public void onCreate() {
        super.onCreate();
        GlobalClientInfo.getInstance(this.b);
        com.taobao.accs.client.a.g.incrementAndGet();
        this.d = b.a(this.b);
        this.d.b = 0;
        this.e = com.taobao.accs.client.b.a(this.b);
        this.g = System.currentTimeMillis();
        this.h = this.g;
        this.j = UtilityImpl.getNetworkTypeExt(this.b);
        this.k = new ScheduledThreadPoolExecutor(2);
        if (org.android.agoo.common.b.d(this.b)) {
            a.c(this.b);
            a a = a.a(this.b, (int) Impl.STATUS_LX_VIP_ERROR_START, false);
            i = a;
            if (a != null) {
                i.a();
            }
        }
        try {
            this.k.execute(new g(this));
        } catch (Throwable th) {
            ALog.e("ServiceImpl", new StringBuilder("serviceImpl init task fail:").append(th.toString()).toString(), new Object[0]);
        }
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("ServiceImpl", "ServiceImpl onCreate", "ClassLoader", ServiceImpl.class.getClassLoader().toString(), Constants.KEY_ELECTION_SDKV, Integer.valueOf(Constants.SDK_VERSION_CODE), "procStart", Integer.valueOf(com.taobao.accs.client.a.g.intValue()));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(android.content.Intent r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.internal.ServiceImpl.a(android.content.Intent):int");
        /*
        this = this;
        r10 = 3;
        r6 = 2;
        r1 = 1;
        r4 = 0;
        r0 = r12.b;
        r0 = com.taobao.accs.utl.UtilityImpl.getServiceEnabled(r0);
        if (r0 != 0) goto L_0x001b;
    L_0x000c:
        r0 = "ServiceImpl";
        r1 = "service disabled!";
        r2 = new java.lang.Object[r4];
        com.taobao.accs.utl.ALog.e(r0, r1, r2);
        r12.b(r4);
    L_0x001a:
        return r6;
    L_0x001b:
        r0 = com.taobao.accs.utl.ALog.Level.I;
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);
        if (r0 == 0) goto L_0x003b;
    L_0x0023:
        r0 = "ServiceImpl";
        r2 = new java.lang.StringBuilder;
        r3 = "onHostStartCommand:";
        r2.<init>(r3);
        r2 = r2.append(r13);
        r2 = r2.toString();
        r3 = new java.lang.Object[r4];
        com.taobao.accs.utl.ALog.i(r0, r2, r3);
    L_0x003b:
        r0 = com.taobao.accs.utl.ALog.Level.D;	 Catch:{ Throwable -> 0x007c }
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);	 Catch:{ Throwable -> 0x007c }
        if (r0 == 0) goto L_0x0096;
    L_0x0043:
        if (r13 == 0) goto L_0x0096;
    L_0x0045:
        r2 = r13.getExtras();	 Catch:{ Throwable -> 0x007c }
        if (r2 == 0) goto L_0x0096;
    L_0x004b:
        r0 = r2.keySet();	 Catch:{ Throwable -> 0x007c }
        r3 = r0.iterator();	 Catch:{ Throwable -> 0x007c }
    L_0x0053:
        r0 = r3.hasNext();	 Catch:{ Throwable -> 0x007c }
        if (r0 == 0) goto L_0x0096;
    L_0x0059:
        r0 = r3.next();	 Catch:{ Throwable -> 0x007c }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x007c }
        r4 = "ServiceImpl";
        r5 = "key";
        r7 = 3;
        r7 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x007c }
        r8 = 0;
        r7[r8] = r0;	 Catch:{ Throwable -> 0x007c }
        r8 = 1;
        r9 = " value";
        r7[r8] = r9;	 Catch:{ Throwable -> 0x007c }
        r8 = 2;
        r0 = r2.get(r0);	 Catch:{ Throwable -> 0x007c }
        r7[r8] = r0;	 Catch:{ Throwable -> 0x007c }
        com.taobao.accs.utl.ALog.d(r4, r5, r7);	 Catch:{ Throwable -> 0x007c }
        goto L_0x0053;
    L_0x007c:
        r0 = move-exception;
        r11 = r0;
        r0 = r1;
        r1 = r11;
    L_0x0080:
        r2 = "ServiceImpl";
        r3 = "onHostStartCommand";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x01f0 }
        com.taobao.accs.utl.ALog.e(r2, r3, r1, r4);	 Catch:{ all -> 0x01f0 }
        r1.printStackTrace();	 Catch:{ all -> 0x01f0 }
        r1 = com.taobao.accs.client.a.g;
        r1.incrementAndGet();
    L_0x0094:
        r6 = r0;
        goto L_0x001a;
    L_0x0096:
        r0 = com.taobao.accs.utl.e.c();	 Catch:{ Throwable -> 0x007c }
        if (r0 <= r10) goto L_0x021a;
    L_0x009c:
        r1 = "ServiceImpl";
        r2 = "load SO fail 4 times, don't auto restart";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x01ce }
        com.taobao.accs.utl.ALog.e(r1, r2, r3);	 Catch:{ Throwable -> 0x01ce }
        r1 = "accs";
        r2 = "sofail";
        r0 = com.taobao.accs.utl.UtilityImpl.int2String(r0);	 Catch:{ Throwable -> 0x01ce }
        r4 = 0;
        com.taobao.accs.utl.b.a(r1, r2, r0, r4);	 Catch:{ Throwable -> 0x01ce }
    L_0x00b7:
        if (r13 != 0) goto L_0x0135;
    L_0x00b9:
        r0 = 0;
    L_0x00ba:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x01ce }
        if (r1 != 0) goto L_0x011e;
    L_0x00c0:
        r1 = "org.agoo.android.intent.action.PING_V4";
        r1 = r1.equals(r0);	 Catch:{ Throwable -> 0x01ce }
        if (r1 == 0) goto L_0x011e;
    L_0x00c9:
        r1 = "source";
        r1 = r13.getStringExtra(r1);	 Catch:{ Throwable -> 0x01ce }
        r2 = "ServiceImpl";
        r3 = "org.agoo.android.intent.action.PING_V4,start channel by brothers";
        r4 = 3;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x01ce }
        r5 = 0;
        r7 = "serviceStart";
        r4[r5] = r7;	 Catch:{ Throwable -> 0x01ce }
        r5 = 1;
        r7 = com.taobao.accs.client.a.g;	 Catch:{ Throwable -> 0x01ce }
        r7 = r7.intValue();	 Catch:{ Throwable -> 0x01ce }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Throwable -> 0x01ce }
        r4[r5] = r7;	 Catch:{ Throwable -> 0x01ce }
        r5 = 2;
        r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01ce }
        r8 = "source";
        r7.<init>(r8);	 Catch:{ Throwable -> 0x01ce }
        r7 = r7.append(r1);	 Catch:{ Throwable -> 0x01ce }
        r7 = r7.toString();	 Catch:{ Throwable -> 0x01ce }
        r4[r5] = r7;	 Catch:{ Throwable -> 0x01ce }
        com.taobao.accs.utl.ALog.i(r2, r3, r4);	 Catch:{ Throwable -> 0x01ce }
        r2 = "accs";
        r3 = "startChannel";
        r4 = 0;
        com.taobao.accs.utl.b.a(r2, r3, r1, r4);	 Catch:{ Throwable -> 0x01ce }
        r2 = com.taobao.accs.client.a.d();	 Catch:{ Throwable -> 0x01ce }
        if (r2 == 0) goto L_0x011e;
    L_0x0113:
        r2 = "accs";
        r3 = "createChannel";
        r4 = 0;
        com.taobao.accs.utl.b.a(r2, r3, r1, r4);	 Catch:{ Throwable -> 0x01ce }
    L_0x011e:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x01ce }
        if (r1 == 0) goto L_0x013a;
    L_0x0124:
        r12.e();	 Catch:{ Throwable -> 0x01ce }
        r0 = r12.c;	 Catch:{ Throwable -> 0x01ce }
        r1 = 0;
        r2 = 0;
        r0.a(r1, r2);	 Catch:{ Throwable -> 0x01ce }
        r0 = com.taobao.accs.client.a.g;
        r0.incrementAndGet();
        goto L_0x001a;
    L_0x0135:
        r0 = r13.getAction();	 Catch:{ Throwable -> 0x01ce }
        goto L_0x00ba;
    L_0x013a:
        r12.e();	 Catch:{ Throwable -> 0x01ce }
        r1 = "android.intent.action.PACKAGE_REMOVED";
        r1 = android.text.TextUtils.equals(r0, r1);	 Catch:{ Throwable -> 0x01ce }
        if (r1 != 0) goto L_0x01b5;
    L_0x0146:
        r1 = "android.net.conn.CONNECTIVITY_CHANGE";
        r1 = android.text.TextUtils.equals(r0, r1);	 Catch:{ Throwable -> 0x01ce }
        if (r1 == 0) goto L_0x01bd;
    L_0x014f:
        r0 = r12.b;	 Catch:{ Throwable -> 0x01ce }
        r3 = com.taobao.accs.utl.UtilityImpl.getNetworkTypeExt(r0);	 Catch:{ Throwable -> 0x01ce }
        r0 = r12.b;	 Catch:{ Throwable -> 0x01ce }
        r0 = com.taobao.accs.utl.UtilityImpl.isNetworkConnected(r0);	 Catch:{ Throwable -> 0x01ce }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01ce }
        r2 = "network change:";
        r1.<init>(r2);	 Catch:{ Throwable -> 0x01ce }
        r2 = r12.j;	 Catch:{ Throwable -> 0x01ce }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x01ce }
        r2 = " to ";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x01ce }
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x01ce }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x01ce }
        r2 = "ServiceImpl";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x01ce }
        com.taobao.accs.utl.ALog.i(r2, r1, r4);	 Catch:{ Throwable -> 0x01ce }
        if (r0 == 0) goto L_0x01a5;
    L_0x0183:
        r12.j = r3;	 Catch:{ Throwable -> 0x01ce }
        r0 = r12.c;	 Catch:{ Throwable -> 0x01ce }
        r0.c();	 Catch:{ Throwable -> 0x01ce }
        r0 = r12.c;	 Catch:{ Throwable -> 0x01ce }
        r1 = 1;
        r2 = 0;
        r0.a(r1, r2);	 Catch:{ Throwable -> 0x01ce }
        r0 = com.taobao.accs.utl.UTMini.getInstance();	 Catch:{ Throwable -> 0x01ce }
        r1 = 66001; // 0x101d1 float:9.2487E-41 double:3.2609E-319;
        r2 = "CONNECTIVITY_CHANGE";
        r4 = com.taobao.accs.utl.UtilityImpl.getProxy();	 Catch:{ Throwable -> 0x01ce }
        r5 = "0";
        r0.commitEvent(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01ce }
    L_0x01a5:
        r0 = "unknown";
        r0 = r3.equals(r0);	 Catch:{ Throwable -> 0x01ce }
        if (r0 == 0) goto L_0x01b5;
    L_0x01ae:
        r0 = r12.c;	 Catch:{ Throwable -> 0x01ce }
        r0.c();	 Catch:{ Throwable -> 0x01ce }
        r12.j = r3;	 Catch:{ Throwable -> 0x01ce }
    L_0x01b5:
        r0 = com.taobao.accs.client.a.g;
        r0.incrementAndGet();
        r0 = r6;
        goto L_0x0094;
    L_0x01bd:
        r1 = "android.intent.action.BOOT_COMPLETED";
        r1 = android.text.TextUtils.equals(r0, r1);	 Catch:{ Throwable -> 0x01ce }
        if (r1 == 0) goto L_0x01d3;
    L_0x01c6:
        r0 = r12.c;	 Catch:{ Throwable -> 0x01ce }
        r1 = 1;
        r2 = 0;
        r0.a(r1, r2);	 Catch:{ Throwable -> 0x01ce }
        goto L_0x01b5;
    L_0x01ce:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        goto L_0x0080;
    L_0x01d3:
        r1 = "android.intent.action.USER_PRESENT";
        r1 = android.text.TextUtils.equals(r0, r1);	 Catch:{ Throwable -> 0x01ce }
        if (r1 == 0) goto L_0x01f7;
    L_0x01dc:
        r0 = "ServiceImpl";
        r1 = "action android.intent.action.USER_PRESENT";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01ce }
        com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Throwable -> 0x01ce }
        r0 = r12.c;	 Catch:{ Throwable -> 0x01ce }
        r1 = 1;
        r2 = 0;
        r0.a(r1, r2);	 Catch:{ Throwable -> 0x01ce }
        goto L_0x01b5;
    L_0x01f0:
        r0 = move-exception;
        r1 = com.taobao.accs.client.a.g;
        r1.incrementAndGet();
        throw r0;
    L_0x01f7:
        r1 = "com.taobao.accs.intent.action.COMMAND";
        r1 = r0.equals(r1);	 Catch:{ Throwable -> 0x01ce }
        if (r1 == 0) goto L_0x0204;
    L_0x0200:
        r12.b(r13);	 Catch:{ Throwable -> 0x01ce }
        goto L_0x01b5;
    L_0x0204:
        r1 = "com.taobao.accs.intent.action.START_FROM_AGOO";
        r0 = r0.equals(r1);	 Catch:{ Throwable -> 0x01ce }
        if (r0 == 0) goto L_0x01b5;
    L_0x020d:
        r0 = "ServiceImpl";
        r1 = "ACTION_START_FROM_AGOO";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01ce }
        com.taobao.accs.utl.ALog.i(r0, r1, r2);	 Catch:{ Throwable -> 0x01ce }
        goto L_0x01b5;
    L_0x021a:
        r6 = r1;
        goto L_0x00b7;
        */
    }

    private void b(Intent intent) {
        int intExtra = intent.getIntExtra(IntentUtil.AGOO_COMMAND, -1);
        ALog.i("ServiceImpl", new StringBuilder("command:").append(intExtra).toString(), new Object[0]);
        String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
        String stringExtra2 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
        String stringExtra3 = intent.getStringExtra(Constants.KEY_USER_ID);
        String stringExtra4 = intent.getStringExtra(Constants.KEY_APP_KEY);
        String stringExtra5 = intent.getStringExtra(Constants.KEY_TTID);
        String stringExtra6 = intent.getStringExtra(Constants.KEY_SID);
        String stringExtra7 = intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE);
        if (intExtra == 201) {
            this.c.b(Message.BuildPing(true, 0), true);
            c d = this.c.d();
            if (d != null) {
                d.h = this.g;
                d.commitUT();
            }
        }
        if (intExtra > 0 && !TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra)) {
            Message buildBindApp;
            if (intExtra == 1) {
                buildBindApp = Message.buildBindApp(this.b, stringExtra4, stringExtra, stringExtra5, intent.getStringExtra(Constants.KEY_APP_VERSION), stringExtra6, stringExtra3, stringExtra7);
                if (stringExtra.equals(this.b.getPackageName())) {
                    UtilityImpl.setAppInfo(this.b, stringExtra4, null, stringExtra5);
                }
                if (this.e.c(stringExtra)) {
                    if (!intent.getBooleanExtra(Constants.KEY_FOUCE_BIND, false)) {
                        ALog.i("ServiceImpl", stringExtra + " isAppBinded", new Object[0]);
                        this.d.a(buildBindApp, (int) Impl.STATUS_SUCCESS);
                        return;
                    }
                }
            } else if (intExtra == 2) {
                ALog.e("ServiceImpl", "onHostStartCommand COMMAND_UNBIND_APP", new Object[0]);
                buildBindApp = Message.buildUnbindApp(this.b, stringExtra, stringExtra6, stringExtra3, stringExtra7);
                if (this.e.d(stringExtra)) {
                    ALog.i("ServiceImpl", stringExtra + " isAppUnbinded", new Object[0]);
                    this.d.a(buildBindApp, (int) Impl.STATUS_SUCCESS);
                    return;
                }
            } else if (intExtra == 5) {
                buildBindApp = Message.buildBindService(this.b, stringExtra, stringExtra4, stringExtra2, stringExtra6, stringExtra3, stringExtra7);
            } else if (intExtra == 6) {
                buildBindApp = Message.buildUnbindService(this.b, stringExtra, stringExtra4, stringExtra2, stringExtra6, stringExtra3, stringExtra7);
            } else if (intExtra == 3) {
                buildBindApp = Message.buildBindUser(this.b, stringExtra, stringExtra4, stringExtra6, stringExtra3, stringExtra7);
                if (this.e.b(stringExtra, stringExtra3)) {
                    if (!intent.getBooleanExtra(Constants.KEY_FOUCE_BIND, false)) {
                        ALog.i("ServiceImpl", stringExtra + "/" + stringExtra3 + " isUserBinded", new Object[0]);
                        if (buildBindApp != null) {
                            this.d.a(buildBindApp, (int) Impl.STATUS_SUCCESS);
                            return;
                        }
                        return;
                    }
                }
            } else if (intExtra == 4) {
                buildBindApp = Message.buildUnbindUser(this.b, stringExtra, stringExtra4, stringExtra6, stringExtra3, stringExtra7);
            } else {
                if (intExtra == 100) {
                    ReqType reqType;
                    byte[] byteArrayExtra = intent.getByteArrayExtra(SocializeConstants.JSON_DATA);
                    String stringExtra8 = intent.getStringExtra(Constants.KEY_DATA_ID);
                    String stringExtra9 = intent.getStringExtra(Constants.KEY_TARGET);
                    String stringExtra10 = intent.getStringExtra(Constants.KEY_BUSINESSID);
                    stringExtra6 = intent.getStringExtra(Constants.KEY_EXT_TAG);
                    Object obj = null;
                    try {
                        reqType = (ReqType) intent.getSerializableExtra(Constants.KEY_SEND_TYPE);
                    } catch (Exception e) {
                    }
                    if (byteArrayExtra != null) {
                        URL url;
                        try {
                            URL url2 = new URL(new StringBuilder("https://").append(com.taobao.accs.net.a.b(this.b)).toString());
                        } catch (Exception e2) {
                            url = null;
                        }
                        AccsRequest accsRequest = new AccsRequest(stringExtra3, stringExtra2, byteArrayExtra, stringExtra8, stringExtra9, url, stringExtra10);
                        accsRequest.setTag(stringExtra6);
                        if (reqType == null) {
                            buildBindApp = Message.buildSendData(this.b, stringExtra, stringExtra4, accsRequest, false);
                        } else if (reqType == ReqType.REQ) {
                            buildBindApp = Message.buildRequest(this.b, stringExtra, stringExtra4, accsRequest, false);
                        }
                    }
                } else if (intExtra == 105) {
                    buildBindApp = Message.buildElection(stringExtra, (Map) intent.getSerializableExtra(Constants.KEY_ELECTION_PACKS));
                } else if (intExtra == 106) {
                    intent.setAction(Constants.ACTION_RECEIVE);
                    intent.putExtra(IntentUtil.AGOO_COMMAND, -1);
                    e.a(this.b, intent);
                    return;
                }
                buildBindApp = null;
            }
            if (buildBindApp != null) {
                ALog.d("ServiceImpl", "try send message", new Object[0]);
                if (buildBindApp.getNetPermanceMonitor() != null) {
                    buildBindApp.getNetPermanceMonitor().onSend();
                }
                this.c.b(buildBindApp, true);
                return;
            }
            ALog.e("ServiceImpl", "message is null", new Object[0]);
            this.d.a(Message.buildParameterError(stringExtra, intExtra), (int) Tabs.TAB_CREATE_REACH_MAX_COUNT);
        }
    }

    public IBinder onBind(Intent intent) {
        CharSequence action = intent.getAction();
        ALog.d("ServiceImpl", new StringBuilder("accs probeTaoBao begin......action=").append(action).toString(), new Object[0]);
        if (TextUtils.isEmpty(action) || !TextUtils.equals(action, Constants.ACTION_PING)) {
            return null;
        }
        UTMini.getInstance().commitEvent(UT.EVENT_ID, "probeChannelService", UtilityImpl.getDeviceId(this.b), intent.getStringExtra(SocialConstants.PARAM_SOURCE));
        return this.l;
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }

    public void onDestroy() {
        super.onDestroy();
        ALog.e("ServiceImpl", "Service onDestroy", new Object[0]);
        UtilityImpl.setServiceTime(this.b, Constants.SP_KEY_SERVICE_END, System.currentTimeMillis());
        this.a = null;
        this.b = null;
        b.a();
        if (this.c != null) {
            this.c.f();
        }
        if (this.f != null) {
            this.f.quit();
        }
        Process.killProcess(Process.myPid());
    }

    private final void a(Context context) {
        try {
            ALog.d("ServiceImpl", "ippConnection [bind]", new Object[0]);
            Intent intent = new Intent();
            intent.setAction("org.rome.android.IPP_CALL");
            intent.setClassName(k.b, "org.rome.android.ipp.binder.IppService");
            intent.putExtra(JsInterface.KEY_APK_NAME, context.getPackageName());
            intent.setPackage(k.b);
            context.startService(intent);
        } catch (Throwable th) {
            ALog.e("ServiceImpl", "ippConnection", th, new Object[0]);
        }
    }

    private void b(boolean z) {
        ALog.e("ServiceImpl", new StringBuilder("shouldStopSelf, kill:").append(z).toString(), new Object[0]);
        if (this.a != null) {
            this.a.stopSelf();
        }
        if (z) {
            Process.killProcess(Process.myPid());
        }
    }

    private synchronized void e() {
        if (this.c == null) {
            this.c = com.taobao.accs.net.a.a(this.b, 0);
        }
        this.c.a();
    }

    public void c() {
        ALog.i("ServiceImpl", "startConnect", new Object[0]);
        try {
            e();
            this.c.a(false, false);
        } catch (Throwable th) {
            ALog.e("ServiceImpl", new StringBuilder("tryConnect is error,e=").append(th).toString(), new Object[0]);
        }
    }

    public void a() {
        c();
    }
}
