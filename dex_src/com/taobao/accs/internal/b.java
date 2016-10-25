package com.taobao.accs.internal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.a.a.a;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.IBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.Constants.Operate;
import com.taobao.accs.ut.monitor.ElectionRateMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.h;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.android.agoo.intent.IntentUtil;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public abstract class b implements IBaseService {
    public static final String ELECTION_KEY_BLACKLIST = "blacklist";
    public static final String ELECTION_KEY_HOST = "host";
    public static final String ELECTION_KEY_SDKVS = "sdkvs";
    public static final String ELECTION_KEY_VERSION = "elversion";
    public static final String ELECTION_SERVICE_ID = "accs_election";
    public static final int ELE_ERROR_EXCEPTION = -901;
    public static final int ELE_ERROR_SERVER = -900;
    public static final int ELE_LIST_NULL = -902;
    private static int e;
    private static boolean f;
    private Context a;
    private boolean b;
    private Map<String, Integer> c;
    private ScheduledThreadPoolExecutor d;
    private Service g;
    private ElectionRateMonitor h;
    private ElectionRateMonitor i;
    private ScheduledFuture<?> j;
    private AccsAbstractDataListener k;
    private a l;
    private boolean m;
    private boolean n;
    private ScheduledFuture<?> o;

    // compiled from: Taobao
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Operate.values().length];
            try {
                a[Operate.TRY_ELECTION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Operate.START_ELECTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Operate.ASK_VERSION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Operate.REPORT_VERSION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[Operate.RESULT_ELECTION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[Operate.PING_ELECTION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public abstract int a(Intent intent);

    public abstract void a();

    static {
        e = 0;
        f = false;
    }

    public b(Service service) {
        this.b = false;
        this.c = null;
        this.g = null;
        this.k = new c(this);
        this.m = false;
        this.n = false;
        this.g = service;
        this.a = service.getApplicationContext();
        this.c = new HashMap();
        this.d = com.taobao.accs.common.a.a();
        AppMonitor.getInstance().register(ElectionRateMonitor.class);
    }

    public void onCreate() {
        ALog.i("ElectionServiceImpl", "onCreate,", Constants.KEY_ELECTION_SDKV, Integer.valueOf(Constants.SDK_VERSION_CODE));
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        CharSequence schemeSpecificPart;
        CharSequence action = intent.getAction();
        CharSequence packageName = this.a.getPackageName();
        ALog.i("ElectionServiceImpl", "onStartCommand begin", JsInterface.KEY_ACTION, action);
        if (h.c()) {
            try {
                if (TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED")) {
                    schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                    boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
                    a a = com.taobao.accs.a.a.a(this.a);
                    CharSequence charSequence = a.a;
                    ALog.w("ElectionServiceImpl", "onstartcommand PACKAGE_REMOVED", Constants.KEY_ELECTION_PKG, schemeSpecificPart, ELECTION_KEY_HOST, charSequence, "replaced", Boolean.valueOf(booleanExtra));
                    if (TextUtils.isEmpty(charSequence) || !TextUtils.equals(charSequence, schemeSpecificPart)) {
                        ALog.i("ElectionServiceImpl", "onstartcommand PACKAGE_REMOVED no need election", new Object[0]);
                    } else if (this.a.getPackageName().equals(com.taobao.accs.a.a.e(this.a))) {
                        a.b = 0;
                        com.taobao.accs.a.a.a(this.a, a);
                        a(this.a, "host removed");
                    } else {
                        ALog.i("ElectionServiceImpl", "onstartcommand PACKAGE_REMOVED no need election", new Object[0]);
                    }
                } else if (TextUtils.equals(action, com.taobao.accs.a.a.b())) {
                    ALog.i("ElectionServiceImpl", "operate is receive", "operate", (Operate) intent.getSerializableExtra("operate"));
                    Intent intent2;
                    switch (AnonymousClass_1.a[((Operate) intent.getSerializableExtra("operate")).ordinal()]) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            d();
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            a(this.a, intent.getStringExtra(DownloadManager.COLUMN_REASON));
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
                            int intExtra = intent.getIntExtra(ELECTION_KEY_VERSION, 0);
                            intent2 = new Intent(com.taobao.accs.a.a.b());
                            intent2.putExtra("operate", Operate.REPORT_VERSION);
                            intent2.putExtra(JsInterface.KEY_APK_NAME, this.a.getPackageName());
                            intent2.setPackage(stringExtra);
                            intent2.setClassName(stringExtra, com.taobao.accs.utl.a.channelService);
                            int i3 = Constants.SDK_VERSION_CODE;
                            if (com.taobao.accs.a.a.a(this.a, this.a.getPackageName(), intExtra)) {
                                intent2.putExtra(Constants.KEY_SDK_VERSION, Constants.SDK_VERSION_CODE);
                            } else {
                                i3 = 0;
                            }
                            this.a.startService(intent2);
                            ALog.i("ElectionServiceImpl", "report", Constants.KEY_ELECTION_SDKV, Integer.valueOf(i3), "from pkg", this.a.getPackageName(), "to pkg", stringExtra);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            if (this.b) {
                                String stringExtra2 = intent.getStringExtra(JsInterface.KEY_APK_NAME);
                                int intExtra2 = intent.getIntExtra(Constants.KEY_SDK_VERSION, 0);
                                e--;
                                if (intExtra2 != 0) {
                                    this.c.put(stringExtra2, Integer.valueOf(intExtra2));
                                }
                                ALog.i("ElectionServiceImpl", "collect info", Constants.KEY_ELECTION_SDKV, Integer.valueOf(intExtra2), "election pkg", stringExtra2, "electionPackCount", Integer.valueOf(e));
                                if (e == 0) {
                                    e();
                                }
                            } else {
                                ALog.e("ElectionServiceImpl", "not electioning, but receive report", new Object[0]);
                            }
                            break;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            schemeSpecificPart = intent.getStringExtra("sudoPack");
                            ALog.i("ElectionServiceImpl", "election result", ELECTION_KEY_HOST, schemeSpecificPart, "curr pkg", packageName);
                            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_ELECTION_PKG_TIMES, schemeSpecificPart, 0.0d);
                            com.taobao.accs.a.a.b(this.a);
                            if (!TextUtils.isEmpty(schemeSpecificPart)) {
                                if (TextUtils.equals(schemeSpecificPart, packageName)) {
                                    a();
                                } else {
                                    a(true);
                                }
                            }
                            break;
                        case R.styleable.Toolbar_contentInsetEnd:
                            schemeSpecificPart = com.taobao.accs.a.a.a(this.a).a;
                            CharSequence stringExtra3 = intent.getStringExtra("pingPack");
                            if (!(TextUtils.isEmpty(schemeSpecificPart) || TextUtils.isEmpty(stringExtra3) || !TextUtils.equals(schemeSpecificPart, packageName))) {
                                ALog.i("ElectionServiceImpl", "host receive ping, and report ping", "to pkg", stringExtra3, ELECTION_KEY_HOST, schemeSpecificPart);
                                intent2 = new Intent(com.taobao.accs.a.a.b());
                                intent2.setPackage(stringExtra3);
                                intent2.setClassName(stringExtra3, com.taobao.accs.utl.a.channelService);
                                intent2.putExtra("operate", Operate.PING_ELECTION);
                                intent2.putExtra("isPing", true);
                                intent2.putExtra("pingPack", stringExtra3);
                                intent2.putExtra(Constants.KEY_SDK_VERSION, Constants.SDK_VERSION_CODE);
                                this.a.startService(intent2);
                                a();
                            }
                            if (TextUtils.equals(stringExtra3, packageName)) {
                                ALog.i("ElectionServiceImpl", "receive host's ping back", ELECTION_KEY_HOST, schemeSpecificPart);
                                f = intent.getBooleanExtra("isPing", false);
                            }
                            break;
                    }
                    return XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
            } catch (Throwable th) {
                ALog.e("ElectionServiceImpl", "onStartCommand", th, new Object[0]);
            }
        } else if (TextUtils.equals(action, com.taobao.accs.a.a.b())) {
            ALog.e("ElectionServiceImpl", "election disabled", new Object[0]);
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        if (TextUtils.equals(action, Constants.ACTION_START_SERVICE)) {
            Object stringExtra4 = intent.getStringExtra(JsInterface.KEY_APK_NAME);
            Object stringExtra5 = intent.getStringExtra(Constants.KEY_APP_KEY);
            String stringExtra6 = intent.getStringExtra(Constants.KEY_TTID);
            GlobalClientInfo.getInstance(this.a).setAppSecret(intent.getStringExtra(Constants.SP_APP_SECRET));
            ALog.i("ElectionServiceImpl", "try to setAppInfo", Constants.SP_KEY_APPKEY, stringExtra5, "appSecret", r4, Constants.KEY_TTID, stringExtra6, Constants.KEY_ELECTION_PKG, stringExtra4);
            if (!(TextUtils.isEmpty(stringExtra4) || TextUtils.isEmpty(stringExtra5) || !stringExtra4.equals(this.a.getPackageName()))) {
                UtilityImpl.setAppInfo(this.a, stringExtra5, null, stringExtra6);
            }
            if (h.c()) {
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            }
        }
        schemeSpecificPart = com.taobao.accs.a.a.a(this.a).a;
        if (TextUtils.isEmpty(schemeSpecificPart) || TextUtils.equals(schemeSpecificPart, this.a.getPackageName()) || !h.c()) {
            ALog.i("ElectionServiceImpl", "deliver to channelservice", "host pkg", schemeSpecificPart);
            return a(intent);
        }
        if (!(this.b || TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED"))) {
            ALog.i("ElectionServiceImpl", "not electioning and not host, stop", new Object[0]);
            a(true);
        }
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }

    public void onDestroy() {
        ALog.e("ElectionServiceImpl", "Service onDestroy", new Object[0]);
        this.a = null;
        this.g = null;
    }

    private String c() {
        int i;
        CharSequence charSequence;
        Object obj;
        Throwable th;
        Throwable th2;
        Object toString;
        ElectionRateMonitor electionRateMonitor;
        ElectionRateMonitor electionRateMonitor2;
        CharSequence charSequence2 = null;
        int i2 = 0;
        try {
            if (this.c == null || this.c.size() <= 0) {
                int i3 = ELE_LIST_NULL;
                try {
                    i = -902;
                    String str = "apps is null";
                    charSequence = null;
                } catch (Throwable th3) {
                    th = th3;
                    i = obj;
                    obj = null;
                    if (this.i != null) {
                        if (TextUtils.isEmpty(obj)) {
                            this.i.errorCode = i;
                            this.i.errorMsg = obj;
                        }
                        electionRateMonitor2 = this.i;
                        if (TextUtils.isEmpty(charSequence2)) {
                            i2 = 1;
                        }
                        electionRateMonitor2.ret = i2;
                        AppMonitor.getInstance().commitStat(this.i);
                    }
                    throw th;
                }
            }
            charSequence = com.taobao.accs.a.a.a(this.a, this.c);
            obj = null;
            i = 0;
            try {
                ALog.i("ElectionServiceImpl", "localElection", ELECTION_KEY_HOST, charSequence);
                if (this.i != null) {
                    if (!TextUtils.isEmpty(obj)) {
                        this.i.errorCode = i;
                        this.i.errorMsg = obj;
                    }
                    electionRateMonitor = this.i;
                    if (!TextUtils.isEmpty(charSequence)) {
                        i2 = 1;
                    }
                    electionRateMonitor.ret = i2;
                    AppMonitor.getInstance().commitStat(this.i);
                    return charSequence;
                }
            } catch (Throwable th4) {
                th2 = th4;
                ALog.e("ElectionServiceImpl", "localElection error", th2, new Object[0]);
                toString = th2.toString();
                obj = this.i;
                if (obj != null) {
                    if (TextUtils.isEmpty(toString)) {
                        this.i.errorCode = -901;
                        this.i.errorMsg = toString;
                    }
                    electionRateMonitor = this.i;
                    if (TextUtils.isEmpty(charSequence)) {
                        i2 = 1;
                    }
                    electionRateMonitor.ret = i2;
                    AppMonitor.getInstance().commitStat(this.i);
                    return charSequence;
                }
                return charSequence;
            }
        } catch (Throwable th5) {
            th = th5;
            obj = null;
            i = 0;
            if (this.i != null) {
                if (TextUtils.isEmpty(obj)) {
                    this.i.errorCode = i;
                    this.i.errorMsg = obj;
                }
                electionRateMonitor2 = this.i;
                if (TextUtils.isEmpty(charSequence2)) {
                    i2 = 1;
                }
                electionRateMonitor2.ret = i2;
                AppMonitor.getInstance().commitStat(this.i);
            }
            throw th;
        }
        return charSequence;
    }

    private void a(Map<String, Integer> map) {
        ALog.i("ElectionServiceImpl", "serverElection start", Constants.KEY_ELECTION_PACKS, map.toString());
        this.h = new ElectionRateMonitor();
        try {
            this.h.type = "server";
            if (this.i != null) {
                this.h.reason = this.i.reason;
            }
            GlobalClientInfo.getInstance(this.a).registerListener(ELECTION_SERVICE_ID, this.k);
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_COMMAND);
            intent.putExtra(JsInterface.KEY_APK_NAME, this.a.getPackageName());
            intent.putExtra(IntentUtil.AGOO_COMMAND, R.styleable.AppCompatTheme_radioButtonStyle);
            intent.putExtra(Constants.KEY_ELECTION_PACKS, (HashMap) map);
            a(intent);
        } catch (Throwable th) {
            this.h.errorCode = -901;
            this.h.errorMsg = th.toString();
        }
    }

    private void d() {
        try {
            if (UtilityImpl.isFirstStart(this.a)) {
                b(this.a, "first start");
                UtilityImpl.setSdkStart(this.a);
                return;
            }
            String str = com.taobao.accs.a.a.a(this.a).a;
            Object packageName = this.a.getPackageName();
            ALog.i("ElectionServiceImpl", "tryElection begin", "isFirstStart", Boolean.valueOf(UtilityImpl.isFirstStart(this.a)), "currentPack", packageName, "currentElectionPack", str);
            if (TextUtils.isEmpty(str)) {
                ALog.i("ElectionServiceImpl", "host is empty, try selectAppToElection", new Object[0]);
                b(this.a, "host null");
            } else if (TextUtils.equals(str, packageName)) {
                ALog.i("ElectionServiceImpl", "curr is host, no need election", new Object[0]);
                a();
            } else {
                try {
                    Intent intent = new Intent(com.taobao.accs.a.a.b());
                    intent.setPackage(str);
                    intent.putExtra("operate", Operate.PING_ELECTION);
                    intent.setClassName(str, com.taobao.accs.utl.a.channelService);
                    intent.putExtra("pingPack", packageName);
                    this.a.startService(intent);
                    ALog.i("ElectionServiceImpl", "tryElection send PING_ELECTION", "to pkg", str);
                    this.d.schedule(new d(this, str), 5, TimeUnit.SECONDS);
                } catch (Throwable th) {
                    ALog.e("ElectionServiceImpl", "tryElection startService error", "currentElectionPack", str, th);
                    this.d.schedule(new d(this, str), 5, TimeUnit.SECONDS);
                }
            }
        } catch (Throwable th2) {
            ALog.e("ElectionServiceImpl", "tryElection error", th2, new Object[0]);
        }
    }

    private void b(Context context, String str) {
        String e = com.taobao.accs.a.a.e(context);
        ALog.i("ElectionServiceImpl", "selectAppToElection", Constants.KEY_ELECTION_PKG, e);
        if (TextUtils.isEmpty(e)) {
            Intent intent = new Intent(com.taobao.accs.a.a.b());
            intent.putExtra("operate", Operate.START_ELECTION);
            intent.putExtra(DownloadManager.COLUMN_REASON, str);
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), com.taobao.accs.utl.a.channelService);
            context.startService(intent);
            return;
        }
        Intent intent2 = new Intent(com.taobao.accs.a.a.b());
        intent2.putExtra("operate", Operate.START_ELECTION);
        intent2.putExtra(DownloadManager.COLUMN_REASON, str);
        intent2.setPackage(e);
        intent2.setClassName(e, com.taobao.accs.utl.a.channelService);
        context.startService(intent2);
    }

    public void a(Context context, String str) {
        try {
            if (this.b) {
                ALog.w("ElectionServiceImpl", "isElectioning return", new Object[0]);
                return;
            }
            this.l = com.taobao.accs.a.a.a(context);
            if (this.l.b > 20) {
                ALog.w("ElectionServiceImpl", "startElection too many times, return", "times", Integer.valueOf(this.l.b));
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_ELECTION_OVER_MAX, str + UtilityImpl.getDeviceId(context), 0.0d);
                return;
            }
            this.i = new ElectionRateMonitor();
            this.i.type = "local";
            this.i.reason = str;
            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_ELECTION_START_TIMES, str, 0.0d);
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(com.taobao.accs.a.a.b()), R.styleable.AppCompatTheme_actionModeCutDrawable);
            this.b = true;
            if (queryIntentServices == null || queryIntentServices.size() < 2) {
                String str2 = "ElectionServiceImpl";
                String str3 = "startElection apps < 2";
                Object[] objArr = new Object[2];
                objArr[0] = "services";
                objArr[1] = queryIntentServices == null ? "null" : queryIntentServices.toString();
                ALog.i(str2, str3, objArr);
                e();
                return;
            }
            e = 0;
            ALog.i("ElectionServiceImpl", "startElection begin", "locallist", queryIntentServices.toString(), "size", Integer.valueOf(queryIntentServices.size()));
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (resolveInfo != null && resolveInfo.serviceInfo != null) {
                    String str4 = resolveInfo.serviceInfo.packageName;
                    if (TextUtils.isEmpty(str4)) {
                        ALog.i("ElectionServiceImpl", "startElection unvailable app", Constants.KEY_ELECTION_PKG, str4);
                    } else {
                        Intent intent = new Intent(com.taobao.accs.a.a.b());
                        intent.putExtra("operate", Operate.ASK_VERSION);
                        intent.setPackage(str4);
                        intent.putExtra(JsInterface.KEY_APK_NAME, context.getPackageName());
                        intent.putExtra(ELECTION_KEY_VERSION, 1);
                        intent.setClassName(str4, com.taobao.accs.utl.a.channelService);
                        ALog.i("ElectionServiceImpl", "startElection askversion", "receive pkg", str4);
                        context.startService(intent);
                        e++;
                    }
                }
            }
            this.m = false;
            this.j = this.d.schedule(new e(this), 3, TimeUnit.SECONDS);
        } catch (Throwable th) {
            ALog.e("ElectionServiceImpl", "startElection error", th, new Object[0]);
            this.b = false;
            if (this.i != null) {
                this.i.errorCode = -901;
                this.i.errorMsg = th.toString();
            }
        }
    }

    private void e() {
        try {
            if (this.j != null) {
                this.j.cancel(true);
                this.j = null;
            }
            if (this.m) {
                ALog.i("ElectionServiceImpl", "reportcompleted, return", new Object[0]);
                this.b = false;
                return;
            }
            this.m = true;
            ALog.i("ElectionServiceImpl", "onReportComplete", new Object[0]);
            if (this.c == null) {
                this.c = new HashMap();
            }
            this.c.put(this.a.getPackageName(), Integer.valueOf(Constants.SDK_VERSION_CODE));
            if (this.c.size() == 1) {
                String str = ((String[]) this.c.keySet().toArray(new String[0]))[0];
                if (this.i != null) {
                    this.i.ret = TextUtils.isEmpty(str) ? 0 : 1;
                    AppMonitor.getInstance().commitStat(this.i);
                }
                a(str);
            } else {
                a(this.c);
                this.n = false;
                this.o = this.d.schedule(new f(this), 20, TimeUnit.SECONDS);
            }
            this.b = false;
        } catch (Throwable th) {
            if (this.i != null) {
                this.i.errorCode = -901;
                this.i.errorMsg = th.toString();
            }
            ALog.e("ElectionServiceImpl", "onReportComplete", th, new Object[0]);
            this.b = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte[] r11, int r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.internal.b.a(byte[], int):void");
        /*
        this = this;
        r2 = 1;
        r1 = 0;
        r0 = r10.n;
        if (r0 == 0) goto L_0x0012;
    L_0x0006:
        r0 = "ElectionServiceImpl";
        r2 = "server election handled, return";
        r1 = new java.lang.Object[r1];
        com.taobao.accs.utl.ALog.i(r0, r2, r1);
    L_0x0011:
        return;
    L_0x0012:
        r10.n = r2;
        r3 = 0;
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 != r0) goto L_0x001b;
    L_0x0019:
        if (r11 != 0) goto L_0x0067;
    L_0x001b:
        r0 = "ElectionServiceImpl";
        r4 = "handleServerElectionResult fail, start local election";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r6 = 0;
        r7 = "error";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r6 = 1;
        r7 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        com.taobao.accs.utl.ALog.e(r0, r4, r5);	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r0 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        if (r12 == r0) goto L_0x012c;
    L_0x0038:
        r4 = r2;
    L_0x0039:
        r0 = r10.h;	 Catch:{ Throwable -> 0x00b3, all -> 0x0110 }
        if (r0 == 0) goto L_0x0129;
    L_0x003d:
        r0 = r10.h;	 Catch:{ Throwable -> 0x00b3, all -> 0x0110 }
        r0.errorCode = r12;	 Catch:{ Throwable -> 0x00b3, all -> 0x0110 }
        r0 = r10.h;	 Catch:{ Throwable -> 0x00b3, all -> 0x0110 }
        r5 = "server status error";
        r0.errorMsg = r5;	 Catch:{ Throwable -> 0x00b3, all -> 0x0110 }
        r0 = r3;
    L_0x0049:
        if (r4 == 0) goto L_0x004f;
    L_0x004b:
        r0 = r10.c();
    L_0x004f:
        r10.a(r0);
        r0 = r10.h;
        if (r0 == 0) goto L_0x0011;
    L_0x0056:
        r3 = r10.h;
        if (r4 == 0) goto L_0x00b1;
    L_0x005a:
        r0 = r1;
    L_0x005b:
        r3.ret = r0;
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();
        r1 = r10.h;
        r0.commitStat(r1);
        goto L_0x0011;
    L_0x0067:
        r0 = new java.lang.String;	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r0.<init>(r11);	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r4 = "ElectionServiceImpl";
        r5 = "handleServerElectionResult";
        r6 = 2;
        r6 = new java.lang.Object[r6];	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r7 = 0;
        r8 = "json";
        r6[r7] = r8;	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r7 = 1;
        r6[r7] = r0;	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        com.taobao.accs.utl.ALog.i(r4, r5, r6);	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r5 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r5.<init>(r0);	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r0 = "host";
        r3 = r5.getString(r0);	 Catch:{ Throwable -> 0x00b3, all -> 0x00ec }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x00b3, all -> 0x0115 }
        if (r0 == 0) goto L_0x0126;
    L_0x0093:
        r4 = r2;
    L_0x0094:
        r0 = "blacklist";
        r0 = r5.getJSONArray(r0);	 Catch:{ Throwable -> 0x00b3, all -> 0x011b }
        if (r0 == 0) goto L_0x00aa;
    L_0x009d:
        r0 = r0.length();	 Catch:{ Throwable -> 0x00b3, all -> 0x011b }
        if (r0 <= 0) goto L_0x00aa;
    L_0x00a3:
        r0 = r10.a;	 Catch:{ Throwable -> 0x00b3, all -> 0x011b }
        com.taobao.accs.a.a.a(r0, r11);	 Catch:{ Throwable -> 0x00b3, all -> 0x011b }
        r0 = r3;
        goto L_0x0049;
    L_0x00aa:
        r0 = r10.a;	 Catch:{ Throwable -> 0x00b3, all -> 0x011b }
        com.taobao.accs.a.a.c(r0);	 Catch:{ Throwable -> 0x00b3, all -> 0x011b }
        r0 = r3;
        goto L_0x0049;
    L_0x00b1:
        r0 = r2;
        goto L_0x005b;
    L_0x00b3:
        r0 = move-exception;
        r4 = "ElectionServiceImpl";
        r5 = "handleServerElectionResult";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x0120 }
        com.taobao.accs.utl.ALog.e(r4, r5, r0, r6);	 Catch:{ all -> 0x0120 }
        r4 = r10.h;	 Catch:{ all -> 0x0120 }
        if (r4 == 0) goto L_0x00d2;
    L_0x00c4:
        r4 = r10.h;	 Catch:{ all -> 0x0120 }
        r5 = -901; // 0xfffffffffffffc7b float:NaN double:NaN;
        r4.errorCode = r5;	 Catch:{ all -> 0x0120 }
        r4 = r10.h;	 Catch:{ all -> 0x0120 }
        r0 = r0.toString();	 Catch:{ all -> 0x0120 }
        r4.errorMsg = r0;	 Catch:{ all -> 0x0120 }
    L_0x00d2:
        r0 = r10.c();
        r10.a(r0);
        r0 = r10.h;
        if (r0 == 0) goto L_0x0011;
    L_0x00dd:
        r0 = r10.h;
        r0.ret = r1;
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();
        r1 = r10.h;
        r0.commitStat(r1);
        goto L_0x0011;
    L_0x00ec:
        r0 = move-exception;
        r4 = r1;
        r9 = r0;
        r0 = r3;
        r3 = r9;
    L_0x00f1:
        if (r4 == 0) goto L_0x00f7;
    L_0x00f3:
        r0 = r10.c();
    L_0x00f7:
        r10.a(r0);
        r0 = r10.h;
        if (r0 == 0) goto L_0x010d;
    L_0x00fe:
        r0 = r10.h;
        if (r4 == 0) goto L_0x010e;
    L_0x0102:
        r0.ret = r1;
        r0 = anet.channel.appmonitor.AppMonitor.getInstance();
        r1 = r10.h;
        r0.commitStat(r1);
    L_0x010d:
        throw r3;
    L_0x010e:
        r1 = r2;
        goto L_0x0102;
    L_0x0110:
        r0 = move-exception;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00f1;
    L_0x0115:
        r0 = move-exception;
        r4 = r1;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00f1;
    L_0x011b:
        r0 = move-exception;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00f1;
    L_0x0120:
        r0 = move-exception;
        r4 = r2;
        r9 = r0;
        r0 = r3;
        r3 = r9;
        goto L_0x00f1;
    L_0x0126:
        r4 = r1;
        goto L_0x0094;
    L_0x0129:
        r0 = r3;
        goto L_0x0049;
    L_0x012c:
        r4 = r1;
        goto L_0x0039;
        */
    }

    private void a(String str) {
        try {
            if (this.l == null) {
                this.l = com.taobao.accs.a.a.a(this.a);
            }
            this.l.a = str;
            a aVar = this.l;
            aVar.b++;
            com.taobao.accs.a.a.a(this.a, this.l);
            ALog.i("ElectionServiceImpl", "handleResult notify result", ELECTION_KEY_HOST, str, "packMap", this.c);
            for (String str2 : this.c.keySet()) {
                if (!TextUtils.isEmpty(str2)) {
                    Intent intent = new Intent(com.taobao.accs.a.a.b());
                    intent.setPackage(str2);
                    intent.putExtra("operate", Operate.RESULT_ELECTION);
                    intent.putExtra("sudoPack", str);
                    intent.setClassName(str2, com.taobao.accs.utl.a.channelService);
                    try {
                        this.a.startService(intent);
                    } catch (Throwable th) {
                        ALog.e("ElectionServiceImpl", "handleResult startService", th, new Object[0]);
                    }
                }
            }
        } catch (Throwable th2) {
            ALog.e("ElectionServiceImpl", "handleResult", th2, new Object[0]);
        }
    }

    public void a(boolean z) {
        ALog.e("ElectionServiceImpl", new StringBuilder("shouldStopSelf, kill:").append(z).toString(), new Object[0]);
        if (this.g != null) {
            this.g.stopSelf();
        }
        if (z) {
            Process.killProcess(Process.myPid());
        }
    }
}
