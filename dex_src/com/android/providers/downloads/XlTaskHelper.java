package com.android.providers.downloads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Property;
import com.xunlei.download.proguard.aa;
import com.xunlei.download.proguard.ab;
import com.xunlei.download.proguard.aj;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.l;
import com.xunlei.download.proguard.y;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.InitParam;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XlTaskHelper extends BroadcastReceiver {
    public static final int a = 111141;
    public static final int b = 111085;
    private static final int c = 10000;
    private static XlTaskHelper d;
    private long e;
    private boolean f;
    private boolean g;
    private Set<Long> h;

    public XlTaskHelper() {
        this.e = 0;
        this.f = true;
        this.g = false;
        this.h = new HashSet();
    }

    public static synchronized XlTaskHelper a() {
        XlTaskHelper xlTaskHelper;
        synchronized (XlTaskHelper.class) {
            if (d == null) {
                d = new XlTaskHelper();
            }
            xlTaskHelper = d;
        }
        return xlTaskHelper;
    }

    public void a(Context context) {
        b();
        context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void b(Context context) {
        context.unregisterReceiver(this);
    }

    public void b() {
        this.f = true;
        this.g = false;
    }

    public static String a(int i) {
        return new StringBuilder("xl engine error: ").append(i).toString();
    }

    public static int b(int i) {
        return i < 9000 ? y.a : i;
    }

    public void c(Context context) {
        synchronized (this) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("initXLEngine() mInitRef = ").append(this.e).toString());
            long j = this.e;
            this.e = 1 + j;
            if (j == 0) {
                PackageInfo packageInfo;
                XLDownloadManager instance = XLDownloadManager.getInstance(context);
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                    packageInfo = null;
                }
                InitParam initParam = new InitParam();
                initParam.mAppKey = ab.a(context, DownloadManager.KEY_APP_KEY, a.d);
                String str = (packageInfo == null || packageInfo.versionName == null) ? MsgConstant.PROTOCOL_VERSION : packageInfo.versionName;
                initParam.mAppVersion = str;
                initParam.mStatSavePath = context.getFilesDir().getPath();
                initParam.mStatCfgSavePath = context.getFilesDir().getPath();
                initParam.mPermissionLevel = 2;
                am.b(DownloadManager.LOG_TAG, new StringBuilder("initXLEngine() ret = ").append(instance.init(context, initParam)).toString());
                instance.setOSVersion(VERSION.INCREMENTAL);
                l.a(context);
                XLDownloadManager.getInstance(context).setUserId(DownloadManager.getInstanceFor(context).getProperty(Property.PROP_USER_ID, a.d));
            }
        }
        e(context);
    }

    private void e(Context context) {
        DownloadManager instanceFor = DownloadManager.getInstanceFor(context);
        long downloadSpeedLimit = instanceFor.getDownloadSpeedLimit();
        long uploadSpeedLimit = instanceFor.getUploadSpeedLimit();
        if (downloadSpeedLimit > 0) {
            downloadSpeedLimit *= 1024;
        }
        if (uploadSpeedLimit > 0) {
            uploadSpeedLimit *= 1024;
        }
        XLDownloadManager.getInstance(context).setSpeedLimit(downloadSpeedLimit, uploadSpeedLimit);
    }

    public void d(Context context) {
        synchronized (this) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("uninitXLEngine() mInitRef = ").append(this.e).toString());
            long j = this.e - 1;
            this.e = j;
            if (j == 0) {
                l.b(context);
                XLDownloadManager instance = XLDownloadManager.getInstance(context);
                if (instance != null) {
                    try {
                        instance.uninit();
                    } catch (Throwable e) {
                        am.b(DownloadManager.LOG_TAG, new StringBuilder("uninitXLEngine(), e=").append(e.toString()).toString());
                        am.a(e);
                    }
                }
                am.b(DownloadManager.LOG_TAG, "uninitXLEngine()");
            }
            if (this.e < 0) {
                this.e = 0;
            }
        }
    }

    public boolean a(NetworkInfo networkInfo) {
        am.b(DownloadManager.LOG_TAG, new StringBuilder("isCaptivePortal() info = ").append(networkInfo).toString());
        if (networkInfo.getType() != 1) {
            return false;
        }
        synchronized (this) {
            if (this.f) {
                this.g = a("http://ping.xlmc.sandai.net/generate_204", 204);
                this.f = false;
                return this.g;
            }
            boolean z = this.g;
            return z;
        }
    }

    private boolean a(String str, int i) {
        Throwable th;
        boolean z = false;
        am.b(DownloadManager.LOG_TAG, new StringBuilder("isCaptivePortal() mUrl = ").append(str).append(", expactRespCode = ").append(i).toString());
        boolean z2 = false;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            z2 = false;
            try {
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setConnectTimeout(c);
                httpURLConnection.setReadTimeout(c);
                httpURLConnection.setUseCaches(false);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                httpURLConnection.getInputStream();
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                int responseCode = httpURLConnection.getResponseCode();
                am.b(DownloadManager.LOG_TAG, new StringBuilder("isCaptivePortal() rspCode = ").append(responseCode).append(", elapsed=").append(elapsedRealtime2 - elapsedRealtime).toString());
                if (responseCode != i) {
                    z = true;
                }
                if (httpURLConnection != null) {
                    new aa(this, httpURLConnection).start();
                }
            } catch (Throwable e) {
                Throwable th2 = e;
                r2 = httpURLConnection;
                th = th2;
                th.printStackTrace();
                am.a(th);
                if (r2 != null) {
                    new aa(this, r2).start();
                }
                return z;
            } catch (Throwable th3) {
                r2 = httpURLConnection;
                th = th3;
                if (r2 != null) {
                    new aa(this, r2).start();
                }
                throw th;
            }
        } catch (IOException e2) {
            th = e2;
            try {
                th.printStackTrace();
                am.a(th);
                if (r2 != null) {
                    new aa(this, r2).start();
                }
            } catch (Throwable th4) {
                th = th4;
                HttpURLConnection httpURLConnection2;
                if (httpURLConnection2 != null) {
                    new aa(this, httpURLConnection2).start();
                }
                throw th;
            }
            return z;
        }
        return z;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            this.f = true;
            if (!f(context)) {
                c();
            }
        }
    }

    public static int a(long j, String str) {
        return (j + str.trim()).hashCode();
    }

    public void a(long... jArr) {
        am.b(DownloadManager.LOG_TAG, new StringBuilder("addMobileOnceTask").append(Arrays.toString(jArr)).toString());
        if (jArr != null) {
            synchronized (this.h) {
                for (long j : jArr) {
                    this.h.add(Long.valueOf(j));
                }
            }
        }
    }

    public void a(List<Long> list) {
        if (list != null) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("addMobileOnceTask").append(Arrays.toString(list.toArray())).toString());
            synchronized (this.h) {
                this.h.addAll(list);
            }
        }
    }

    public void b(long... jArr) {
        am.b(DownloadManager.LOG_TAG, new StringBuilder("removeMobileOnceTask").append(Arrays.toString(jArr)).toString());
        if (jArr != null && !this.h.isEmpty()) {
            synchronized (this.h) {
                if (this.h.isEmpty()) {
                    return;
                }
                for (long j : jArr) {
                    this.h.remove(Long.valueOf(j));
                }
            }
        }
    }

    public void b(List<Long> list) {
        if (list != null) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("removeMobileOnceTask").append(Arrays.toString(list.toArray())).toString());
            synchronized (this.h) {
                if (this.h.isEmpty()) {
                    return;
                }
                this.h.removeAll(list);
            }
        }
    }

    public boolean a(long j) {
        boolean contains;
        synchronized (this.h) {
            contains = this.h.contains(Long.valueOf(j));
        }
        am.b(DownloadManager.LOG_TAG, new StringBuilder("isMobileOnceTask id=").append(j).append(", res = ").append(contains).toString());
        return contains;
    }

    public void c() {
        am.b(DownloadManager.LOG_TAG, "clearAllMobileOnceTask");
        synchronized (this.h) {
            this.h.clear();
        }
    }

    private boolean f(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo == null ? false : aj.a(activeNetworkInfo.getType());
    }
}
