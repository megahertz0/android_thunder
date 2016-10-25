package com.xunlei.downloadprovider.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import android.os.SystemClock;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.pushmessage.umeng.UmengPushHandleService;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.downloadprovider.util.h;
import com.xunlei.downloadprovider.util.i;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.s;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BrothersApplication extends Application implements com.xunlei.downloadprovidershare.a {
    public static BrothersApplication a;
    public static long b;
    static List<Long> f;
    public static boolean h;
    public static com.xunlei.downloadprovider.a.h.b j;
    public static com.xunlei.downloadprovider.discovery.kuainiao.a.b k;
    private static String o;
    private static BrothersApplication p;
    private static com.xunlei.downloadprovider.a.h.a u;
    private static long v;
    private static long w;
    private static boolean y;
    public boolean c;
    public HeadsetPlugBroadcastReceiver d;
    public w e;
    Stack<WeakReference<Activity>> g;
    boolean i;
    protected String l;
    final b m;
    public Activity n;
    private BroadcastReceiver q;
    private c r;
    private a s;
    private List<d> t;
    private int x;
    private BroadcastReceiver z;

    private class HeadsetPlugBroadcastReceiver extends BroadcastReceiver {
        public boolean a;

        private HeadsetPlugBroadcastReceiver() {
            this.a = false;
        }

        public final void onReceive(Context context, Intent intent) {
            o;
            new StringBuilder("HeadsetPlugBroadcastReceiver-state:").append(intent.getIntExtra("state", 0));
            if (intent.getIntExtra("state", 0) == 0) {
                this.a = false;
            } else {
                this.a = true;
            }
        }
    }

    public class NetBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) BrothersApplication.this.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && com.xunlei.xllib.a.b.a(context)) {
                    if (BrothersApplication.this.x == -1) {
                        BrothersApplication.this.x = activeNetworkInfo.getType();
                    }
                    if (activeNetworkInfo.getType() == 0) {
                        BrothersApplication.this.f();
                    } else if (f != null && f.size() > 0) {
                        BrothersApplication.this.x;
                        activeNetworkInfo.getType();
                    }
                    BrothersApplication.this.x = activeNetworkInfo.getType();
                }
            }
        }
    }

    private class a extends BroadcastReceiver {
        private a() {
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_CHANGED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) && com.xunlei.downloadprovider.businessutil.b.a().f()) {
                action = com.umeng.a.d;
                String dataString = intent.getDataString();
                if (dataString != null && dataString.length() > 8) {
                    action = dataString.substring(XZBDevice.Wait);
                }
                h a = h.a(context);
                if (DownloadService.a() != null && !com.umeng.a.d.equals(action)) {
                    i iVar = new i(a, "^.*\\.(?i:apk)$");
                    BrothersApplication.this = action;
                    h.a(com.xunlei.downloadprovider.businessutil.a.a(), iVar);
                    dataString = BrothersApplication.this.getSharedPreferences("apk_path", 0).getString(action, null);
                    if (dataString == null) {
                        j.b(BrothersApplication.this, "apk_path", action);
                        return;
                    }
                    if (new File(dataString).exists()) {
                        com.xunlei.downloadprovider.service.downloads.task.d.a();
                        com.xunlei.downloadprovider.service.downloads.task.d.b(dataString);
                    }
                    j.b(BrothersApplication.this, "apk_path", action);
                }
            }
        }
    }

    public class b {
        public final ActivityLifecycleCallbacks a;

        public b() {
            this.a = new i(this);
        }

        final synchronized void a(boolean z, boolean z2, Activity activity) {
            if (z && !z2) {
                BrothersApplication.this.j();
                com.xunlei.downloadprovider.frame.advertisement.b.c.a((Context) activity);
                com.xunlei.downloadprovider.util.a.a.a();
                com.xunlei.downloadprovider.util.a.a.d();
            } else if (!z && z2) {
                BrothersApplication.this.a(com.umeng.a.d);
                com.xunlei.downloadprovider.frame.advertisement.b.c.b(activity);
            }
        }
    }

    private class c extends BroadcastReceiver {
        private c() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onReceive(android.content.Context r7, android.content.Intent r8) {
            throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.app.BrothersApplication.c.onReceive(android.content.Context, android.content.Intent):void");
            /*
            this = this;
            r4 = -1;
            r0 = "com.xunlei.downloadprovider.ACTION_OPEN_FILE";
            r1 = r8.getAction();
            r0 = r0.equals(r1);
            if (r0 == 0) goto L_0x0040;
        L_0x000e:
            r0 = "taskid";
            r0 = r8.getIntExtra(r0, r4);
            r1 = "filepath";
            r1 = r8.getStringExtra(r1);
            r2 = "isbttask";
            r3 = 0;
            r2 = r8.getBooleanExtra(r2, r3);
            if (r0 == r4) goto L_0x0040;
        L_0x0026:
            if (r1 == 0) goto L_0x0040;
        L_0x0028:
            r3 = com.xunlei.downloadprovider.service.downloads.task.b.d.a();
            r4 = (long) r0;
            r3.c(r4);
            if (r2 == 0) goto L_0x0053;
        L_0x0032:
            r0 = new java.io.File;	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            r0.<init>(r1);	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            r0 = r0.isDirectory();	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            if (r0 == 0) goto L_0x0041;
        L_0x003d:
            com.xunlei.downloadprovider.app.FileExplorerActivity.a(r7, r1);	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
        L_0x0040:
            return;
        L_0x0041:
            r0 = com.xunlei.downloadprovider.util.g.a();	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            r0.a(r1, r7);	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            goto L_0x0040;
        L_0x0049:
            r0 = move-exception;
            r0 = com.xunlei.downloadprovider.commonview.XLToast.XLToastType.XLTOAST_TYPE_ALARM;
            r1 = "\u627e\u4e0d\u5230\u7a0b\u5e8f\u6253\u5f00\u8be5\u6587\u4ef6";
            com.xunlei.downloadprovider.commonview.XLToast.a(r7, r0, r1);
            goto L_0x0040;
        L_0x0053:
            r0 = com.xunlei.downloadprovider.util.g.a();	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            r0.a(r1, r7);	 Catch:{ ActivityNotFoundException -> 0x0049, Exception -> 0x005b }
            goto L_0x0040;
        L_0x005b:
            r0 = move-exception;
            r0 = r0.getMessage();
            r1 = com.xunlei.downloadprovider.commonview.XLToast.XLToastType.XLTOAST_TYPE_ALARM;
            com.xunlei.downloadprovider.commonview.XLToast.a(r7, r1, r0);
            goto L_0x0040;
            */
        }
    }

    public static interface d {
        void a();
    }

    public BrothersApplication() {
        this.e = new w();
        this.g = new Stack();
        this.t = new ArrayList();
        this.i = false;
        this.x = -1;
        this.z = new g(this);
        this.m = new b();
    }

    static {
        o = BrothersApplication.class.getSimpleName();
        p = null;
        h = false;
        u = new e();
        j = new com.xunlei.downloadprovider.a.h.b(u);
        v = SystemClock.uptimeMillis();
        w = SystemClock.elapsedRealtime();
        y = true;
    }

    public static BrothersApplication a() {
        return p;
    }

    public static void b() {
        StatReporter.reportExitApplication();
        try {
            p.d();
        } catch (Exception e) {
        }
        i();
        XLAccelUtil.getAccelerator().stopAccel();
        g a = g.a();
        if (a.f != null) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : a.f.entrySet()) {
                com.xunlei.downloadprovider.download.util.g.a aVar = (com.xunlei.downloadprovider.download.util.g.a) entry.getValue();
                if (aVar.b > g.a) {
                    aVar.d = g.c;
                }
                jSONArray.put(aVar.a());
            }
            try {
                jSONObject.put("tasks", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            aa.a(p, "trialed_tasks", jSONObject.toString());
        }
        com.xunlei.downloadprovider.model.protocol.report.b.a();
        DownloadService.b();
        DownloadService.c();
        Process.killProcess(Process.myPid());
    }

    public static String c() {
        long uptimeMillis = SystemClock.uptimeMillis() - v;
        long elapsedRealtime = SystemClock.elapsedRealtime() - w;
        v += uptimeMillis;
        return new StringBuilder(" deltats:[").append(uptimeMillis).append("] elapsed:[").append(elapsedRealtime).append("]").toString();
    }

    public void onTerminate() {
        if (this.m != null) {
            b bVar = this.m;
            new StringBuilder("[applicationOnTerminate]").append(this);
            unregisterActivityLifecycleCallbacks(bVar.a);
        }
        super.onTerminate();
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        android.support.multidex.a.a((Context) this);
        new StringBuilder("MultiDex install in ").append(SystemClock.elapsedRealtime() - elapsedRealtime).append("ms");
    }

    public void onCreate() {
        super.onCreate();
        if (this.m != null) {
            b bVar = this.m;
            new StringBuilder("[applicationOnCreate]").append(this);
            registerActivityLifecycleCallbacks(bVar.a);
        }
        new StringBuilder().append(c()).append(" onCreate super");
        b = SystemClock.uptimeMillis();
        String a = com.xunlei.downloadprovider.a.i.a(this);
        p = this;
        a = this;
        Thread thread = new Thread(new com.xunlei.downloadprovider.app.a.d(getApplicationContext().getApplicationContext()));
        thread.setPriority(XZBDevice.Stop);
        thread.start();
        MobclickAgent.setCatchUncaughtExceptions(false);
        com.xunlei.downloadprovider.app.a.a.a.a();
        com.xunlei.downloadprovider.businessutil.b a2 = com.xunlei.downloadprovider.businessutil.b.a();
        if (this != null) {
            a2.a = this;
        }
        com.xunlei.downloadprovider.a.b.a(getApplicationContext());
        com.xunlei.downloadprovider.a.c.a(getApplicationContext());
        k.a(getApplicationContext());
        StatReporter.init(this);
        com.xunlei.downloadprovider.util.a.a a3 = com.xunlei.downloadprovider.util.a.a.a();
        r c = r.c();
        com.xunlei.downloadprovider.j.a.a((Context) this);
        r.c.execute(new s(c));
        com.xunlei.downloadprovider.util.a.a.a.execute(new com.xunlei.downloadprovider.util.a.b(a3));
        com.xunlei.downloadprovider.businessutil.a.a(getApplicationContext());
        com.xunlei.downloadprovider.j.a.a((Context) this);
        com.nostra13.universalimageloader.core.e.a a4 = new com.nostra13.universalimageloader.core.e.a(getApplicationContext()).a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        a4.d = true;
        a4 = a4.a(new com.nostra13.universalimageloader.a.a.b.c());
        QueueProcessingType queueProcessingType = QueueProcessingType.LIFO;
        if (!(a4.b == null && a4.c == null)) {
            com.nostra13.universalimageloader.b.c.c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
        }
        a4.e = queueProcessingType;
        com.nostra13.universalimageloader.core.d.a().a(a4.a());
        if ("com.xunlei.downloadprovider".equals(a)) {
            com.xunlei.downloadprovidercommon.a.d.a(getApplicationContext());
            com.xunlei.downloadprovider.model.protocol.report.b.a(getApplicationContext(), LoginHelper.a().j);
            ThunderReporter.g gVar = new ThunderReporter.g();
            gVar.a = "android_launch";
            gVar.c = "launch";
            gVar.b = "launch";
            ThunderReporter.a(gVar, true);
            if ("com.xunlei.downloadprovider".equals(a)) {
                new Thread(new f(this)).start();
            }
            if (getPackageName().equals(a) && ai.a() && com.xunlei.downloadprovider.pushmessage.b.a.a(a)) {
                MiPushClient.registerPush(this, "2882303761517301192", "5171730196192");
                Logger.setLogger(this, new h(this));
            }
            WXAPIFactory.createWXAPI(this, "wx3e6556568beeebdd", false).registerApp("wx3e6556568beeebdd");
            if ("com.xunlei.downloadprovider".equals(a)) {
                XLPayUtil.getInstance().init(a, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, getResources().getString(R.string.version), com.xunlei.downloadprovider.a.b.d(), "34a062aaa22f906fca4fefe9fb3a3021");
            }
            com.xunlei.downloadprovidershare.d.a((Application) this);
            this.q = new NetBroadcastReceiver();
            registerReceiver(this.q, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.r = new c();
            IntentFilter intentFilter = new IntentFilter("com.xunlei.downloadprovider.ACTION_OPEN_FILE");
            intentFilter.addAction("com.xunlei.downloadprovider.ACTION_PC_REQUEST_CONNECT");
            registerReceiver(this.r, intentFilter);
            this.s = new a();
            intentFilter = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addDataScheme("package");
            registerReceiver(this.s, intentFilter);
            this.d = new HeadsetPlugBroadcastReceiver();
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
            registerReceiver(this.d, intentFilter);
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
            intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
            intentFilter.addDataScheme("file");
            registerReceiver(this.z, intentFilter);
            if ("com.xunlei.downloadprovider".equals(a)) {
                com.xunlei.downloadprovider.util.a.a.a();
                com.xunlei.downloadprovider.util.a.a.b();
            }
            new StringBuilder("current process name:").append(com.xunlei.downloadprovider.a.i.a(this));
            XZBShouleiUtil.getInstance().init(getApplicationContext(), com.xunlei.downloadprovider.a.b.d(), com.xunlei.downloadprovider.a.b.w());
            XLLiveSDK.getInstance(this).init(com.xunlei.downloadprovider.a.b.b(this));
        }
        new StringBuilder().append(c()).append(" onCreate end");
        if (("com.xunlei.downloadprovider".equals(a) || "com.xunlei.downloadprovider:umeng".equals(a)) && !ai.a()) {
            try {
                PushAgent instance = PushAgent.getInstance(this);
                instance.register(new com.xunlei.downloadprovider.pushmessage.b());
                instance.setPushIntentServiceClass(UmengPushHandleService.class);
            } catch (Exception e) {
            }
        }
    }

    public final void a(int i) {
        if (i == 0) {
            com.xunlei.downloadprovider.frame.user.a.a().a(String.valueOf(LoginHelper.a().j));
        }
    }

    public final void d() {
        while (!this.g.empty()) {
            WeakReference weakReference = (WeakReference) this.g.pop();
            if (weakReference.get() != null && !((Activity) weakReference.get()).isFinishing()) {
                ((Activity) weakReference.get()).finish();
            }
        }
    }

    public static boolean e() {
        return y;
    }

    public static void a(boolean z) {
        y = z;
    }

    public final void f() {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        boolean c = com.xunlei.downloadprovider.service.downloads.task.d.c();
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.d.a(j, false);
        if (c) {
            NotificationActivity.a(getApplicationContext(), j);
        }
    }

    public final boolean g() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            String packageName = getPackageName();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String h() {
        try {
            return String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void a(d dVar) {
        this.t.add(dVar);
    }

    public final void b(d dVar) {
        this.t.remove(dVar);
    }

    private static synchronized void l() {
        synchronized (BrothersApplication.class) {
            if (k == null) {
                k = new com.xunlei.downloadprovider.discovery.kuainiao.a.b(p, p.g);
            }
        }
    }

    public static com.xunlei.downloadprovider.discovery.kuainiao.a.b i() {
        if (k == null) {
            l();
        }
        return k;
    }

    public final void a(String str) {
        this.l = str;
    }

    public final String j() {
        return this.l == null ? com.umeng.a.d : this.l;
    }

    public void onLowMemory() {
        if (k != null) {
            com.xunlei.downloadprovider.discovery.kuainiao.a.b bVar = k;
            XLAccelUtil.getAccelerator().saveAccelState(bVar.b);
            bVar.b = null;
            XLAccelUtil.getAccelerator().detachListener(bVar.f);
            XLAccelUtil.getAccelerator().uninit();
            k = null;
        }
        super.onLowMemory();
    }

    static /* synthetic */ boolean c(BrothersApplication brothersApplication) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) brothersApplication.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            Object obj;
            Object obj2;
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(brothersApplication.getPackageName())) {
                    if (runningAppProcessInfo.importance == 100) {
                        new StringBuilder("\u624b\u96f7\u5904\u4e8e\u524d\u53f0").append(runningAppProcessInfo.processName);
                        int i = 1;
                        obj = null;
                        break;
                    }
                    new StringBuilder("\u624b\u96f7\u5904\u4e8e\u540e\u53f0").append(runningAppProcessInfo.processName);
                } else if (!runningAppProcessInfo.processName.equals("com.xunlei.downloadprovider:xllive")) {
                    continue;
                } else if (runningAppProcessInfo.importance == 100) {
                    new StringBuilder("\u76f4\u64ad\u5904\u4e8e\u524d\u53f0").append(runningAppProcessInfo.processName);
                    obj2 = null;
                    int i2 = 1;
                    break;
                } else {
                    new StringBuilder("\u76f4\u64ad\u5904\u4e8e\u540e\u53f0").append(runningAppProcessInfo.processName);
                }
            }
            obj2 = null;
            obj = null;
            if (!(obj2 == null && r3 == null)) {
                return true;
            }
        }
        return false;
    }
}
