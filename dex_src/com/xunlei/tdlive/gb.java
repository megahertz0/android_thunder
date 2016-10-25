package com.xunlei.tdlive;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.b.a;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.protocol.DNSCache;
import com.xunlei.tdlive.protocol.INetworkHandler;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.t;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: XLLiveApplication.java
public class gb extends Application implements ActivityLifecycleCallbacks, INetworkHandler {
    public static int a;
    private static long b;
    private static String g;
    private Activity c;
    private boolean d;
    private BroadcastReceiver e;
    private c f;

    static {
        b = 0;
        g = "com.xunlei.tdlive.EXTRA_START_FLAG";
        a = 1;
    }

    public static int a(Intent intent) {
        return intent != null ? intent.getIntExtra(g, 0) : 0;
    }

    public static long a() {
        return b;
    }

    public static void b() {
        b = 0;
    }

    public void onCreate() {
        super.onCreate();
        ac.a(this);
        try {
            if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("xllive_enable_log", 0) == 0) {
                XLog.enableLog(false);
            } else {
                XLog.enableLog(true);
            }
            XLLiveRequest.setDNSCahce(DNSCache.getDNSCache(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("xllive_dns_cache", BuildConfig.VERSION_NAME)));
        } catch (Exception e) {
        }
        com.a.a.c.c.a(this);
        f.a(this);
        ApplicationInfo applicationInfo = getApplicationInfo();
        if (!(applicationInfo == null || applicationInfo.packageName == null || !applicationInfo.packageName.equals(ac.i()))) {
            c();
            a((Context) this);
            XLLiveSDK.getInstance(this);
            q.a(this);
            XLLiveRequest.setNetworkHandler(this);
            a.a((Context) this).a();
            registerActivityLifecycleCallbacks(this);
            a(true);
            t.a(true);
            f.a(this).a(false);
        }
        XLog.i("XLLiveApplication", " *** performOnAppCreated *** ");
    }

    public void onTerminate() {
        super.onTerminate();
        a(false);
    }

    private void c() {
    }

    private void a(Context context) {
        String str;
        if ("Test".equals(ac.d("UMENG_CHANNEL"))) {
            str = "900044744";
        } else {
            str = "900044738";
        }
        UserStrategy userStrategy = new UserStrategy(this);
        userStrategy.setAppChannel(ac.d("UMENG_CHANNEL"));
        userStrategy.setAppVersion(ac.d() + com.xunlei.download.proguard.c.q + ac.e());
        userStrategy.setEnableANRCrashMonitor(true);
        userStrategy.setEnableNativeCrashMonitor(true);
        userStrategy.setCrashHandleCallback(new gc(this));
        CrashReport.initCrashReport(context.getApplicationContext(), str, false, userStrategy);
    }

    private void a(boolean z) {
        if (z) {
            try {
                BroadcastReceiver gdVar = new gd(this);
                this.e = gdVar;
                registerReceiver(gdVar, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                return;
            } catch (Exception e) {
            }
        }
        try {
            unregisterReceiver(this.e);
        } catch (Exception e2) {
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        PushAgent.getInstance(this).onAppStart();
        if (b == 0) {
            String str = null;
            Intent intent = activity.getIntent();
            if (intent != null) {
                if ("android.intent.action.MAIN".equals(intent.getAction())) {
                    str = SocializeProtocolConstants.PROTOCOL_NORMAL_SHARE;
                }
                if (a(intent) == a) {
                    str = "push";
                }
            }
            q.e("app_on_desk").a(str).b(activity.getClass().getSimpleName()).b(new String[0]);
            b = SystemClock.elapsedRealtime();
        }
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.c = activity;
    }

    public void onActivityPaused(Activity activity) {
        if (this.c == activity) {
            this.c = null;
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onInvalidVersion(String str, String str2) {
        if (this.c != null) {
            if (this.f == null || !this.f.isShowing()) {
                if (str2 == null || str2.length() <= 0) {
                    this.f = new c(this.c, "\u63d0\u793a", str, "\u9000\u51fa", new String[0]);
                } else {
                    String str3 = str;
                    this.f = new c(this.c, "\u63d0\u793a", str3, "\u53d6\u6d88", new String[]{"\u786e\u5b9a"});
                }
                this.f.b(new ge(this, str2));
            }
        }
    }

    public void onSessionInavlid() {
        q.d("e_400");
        f.a(this).d();
    }

    public void onNoConnection() {
        if (!this.d) {
            this.d = true;
            n.a(this, getResources().getString(R.string.no_connection));
        }
    }
}
