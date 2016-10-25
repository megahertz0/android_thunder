package com.xunlei.downloadprovider.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import com.xunlei.downloadprovider.aidl.ExternTaskInfo;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.DownloadService.b;
import com.xunlei.downloadprovider.service.DownloadService.e;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.TorrentSeedInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.j;
import com.xunlei.downloadprovider.service.downloads.task.a.n;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DownloadService extends Service {
    private static final ServiceConnection f;
    private static DownloadService h;
    private static c i;
    private static ArrayList<c> j;
    private static final Class<?>[] p;
    private static final Class<?>[] q;
    private static final Class<?>[] r;
    public long a;
    public boolean b;
    public com.xunlei.downloadprovider.web.DetailPageBrowserActivity.a c;
    public DownloadEngine d;
    private com.xunlei.downloadprovider.a.h.b e;
    private com.xunlei.downloadprovider.a.h.a g;
    private boolean k;
    private a l;
    private d m;
    private ScreenStateReceiver n;
    private NetworkMonitorReceiver o;
    private Method s;
    private Method t;
    private Method u;
    private Object[] v;
    private Object[] w;
    private Object[] x;
    private int y;
    private com.xunlei.downloadprovider.notification.a z;

    public static interface c {
        void a(DownloadService downloadService);
    }

    public class NetworkMonitorReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            new StringBuilder("Get Network Action:").append(intent.getAction());
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                new StringBuilder("Target Action:").append(intent.getAction());
            }
        }
    }

    public class ScreenStateReceiver extends BroadcastReceiver {
        e a;

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                this.a.a(true);
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                this.a.a(false);
            }
        }
    }

    public class a extends com.xunlei.downloadprovider.aidl.b.a {
        b a;

        public a() {
            this.a = new b();
        }

        public final boolean a(String str, String str2, String str3, String str4) throws RemoteException {
            new StringBuilder("createTaskByUrl fileUrl = ").append(str).append(" fileName = ").append(str2).append(" mainApkId = ").append(str3).append(" subApkId = ").append(str4);
            return DownloadService.this.a(str, str2, (int) XZBDevice.DOWNLOAD_LIST_ALL, "BHO/BHO", null);
        }

        public final ExternTaskInfo a(String str) throws RemoteException {
            ExternTaskInfo externTaskInfo = new ExternTaskInfo();
            DownloadEngine downloadEngine = DownloadService.this.d;
            List arrayList;
            if (downloadEngine.c) {
                arrayList = new ArrayList(downloadEngine.f.a.values());
            } else {
                arrayList = null;
            }
            for (TaskInfo taskInfo : r0) {
                if (taskInfo.mUrl.equals(str)) {
                    externTaskInfo.a = taskInfo.mTaskStatus;
                    DownloadService.this = taskInfo.mUrl;
                    externTaskInfo.c = taskInfo.mFileName;
                    externTaskInfo.d = taskInfo.mFileSize;
                    externTaskInfo.e = taskInfo.mFilePath;
                    return externTaskInfo;
                }
            }
            return null;
        }
    }

    public class b extends Binder {
    }

    public static interface e {
        void a(boolean z);
    }

    private class d implements e {
        private d() {
        }

        public final void a(boolean z) {
            int i = 1;
            DownloadEngine d = DownloadService.this.d;
            if (d.j != null) {
                if (!z) {
                    i = 0;
                }
                d.j.obtainMessage(131, i, 0).sendToTarget();
            }
        }
    }

    public DownloadService() {
        this.a = -1;
        this.b = false;
        this.c = null;
        this.e = null;
        this.g = new e(this);
        this.d = null;
        this.k = false;
        this.l = new a();
        this.v = new Object[1];
        this.w = new Object[2];
        this.x = new Object[1];
        this.y = 111;
    }

    static {
        f = new d();
        h = null;
        i = null;
        j = new ArrayList();
        p = new Class[]{Boolean.TYPE};
        q = new Class[]{Integer.TYPE, Notification.class};
        r = new Class[]{Boolean.TYPE};
    }

    public static DownloadService a() {
        return h;
    }

    public static void a(c cVar) {
        Context a = BrothersApplication.a();
        if (a != null) {
            i = cVar;
            Intent intent = new Intent();
            intent.setClass(a, DownloadService.class);
            a.bindService(intent, f, 1);
        }
    }

    public static void c() {
        Intent intent = new Intent();
        intent.setClass(BrothersApplication.a(), com.android.providers.downloads.DownloadService.class);
        BrothersApplication.a().stopService(intent);
    }

    public final boolean a(Handler handler) {
        synchronized (this) {
            if (this.k) {
                return false;
            }
            this.k = true;
            DownloadEngine downloadEngine = this.d;
            if (downloadEngine.j != null) {
                downloadEngine.j.obtainMessage(IHost.HOST_NOFITY_REFRESH_LIST, 0, 0, handler).sendToTarget();
            }
            downloadEngine.f.c();
            g.a();
            g.g();
            LoginHelper.a();
            if (!LoginHelper.c()) {
                com.xunlei.downloadprovider.service.downloads.task.d.a();
                com.xunlei.downloadprovider.service.downloads.task.d.a(0, null);
            }
            return true;
        }
    }

    public final void b(Handler handler) {
        if (handler != null) {
            this.d.a(handler);
        }
    }

    public final void c(Handler handler) {
        if (handler != null) {
            this.d.b(handler);
        }
    }

    public final boolean a(com.xunlei.downloadprovider.service.downloads.task.c cVar, Handler handler) {
        com.xunlei.downloadprovider.notification.a.a((Context) this).g = true;
        return this.d.a(cVar, handler);
    }

    public final boolean a(Uri uri, long[] jArr, String str, String str2, String str3) {
        com.xunlei.downloadprovider.notification.a.a((Context) this).g = true;
        DownloadEngine downloadEngine = this.d;
        downloadEngine.c();
        return downloadEngine.a(uri, jArr, str, str3, str2) != -1;
    }

    @Deprecated
    public static int d() {
        return 0;
    }

    public static TorrentSeedInfo[] e() {
        return null;
    }

    @Deprecated
    public static String f() {
        return null;
    }

    public final j g() {
        return this.d != null ? this.d.f : null;
    }

    public final long a(String str) {
        long a = this.d.f.a(str);
        if (a != -1) {
            this.b = true;
        }
        this.a = a;
        return a;
    }

    public void onCreate() {
        super.onCreate();
        if (this.d == null) {
            this.d = new DownloadEngine(this);
        }
        if (o()) {
            try {
                this.t = getClass().getMethod("startForeground", q);
                this.u = getClass().getMethod("stopForeground", r);
            } catch (NoSuchMethodException e) {
                this.t = null;
                this.u = null;
                try {
                    this.s = getClass().getMethod("setForeground", p);
                } catch (NoSuchMethodException e2) {
                    this.s = null;
                }
            }
            int i = this.y;
            Notification notification = new Notification();
            if (this.t != null) {
                this.w[0] = Integer.valueOf(i);
                this.w[1] = notification;
                a(this.t, this.w);
            } else {
                this.v[0] = Boolean.TRUE;
                a(this.s, this.v);
            }
        }
        this.o = new NetworkMonitorReceiver();
        registerReceiver(this.o, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.m = new d();
        this.n = new ScreenStateReceiver();
        this.n.a = this.m;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.n, intentFilter);
        if (this.z == null) {
            this.z = com.xunlei.downloadprovider.notification.a.a((Context) this);
            com.xunlei.downloadprovider.notification.a aVar = this.z;
            DownloadEngine downloadEngine = this.d;
            aVar.e = downloadEngine;
            downloadEngine.a(aVar.h);
            aVar = this.z;
            this.d.b = aVar.h;
        }
    }

    public static String b(String str) {
        return com.xunlei.downloadprovider.service.downloads.b.c.a(str);
    }

    private static boolean o() {
        return com.xunlei.downloadprovider.a.b.i() < 18;
    }

    private boolean a(Method method, Object[] objArr) {
        boolean z = false;
        try {
            method.invoke(this, objArr);
            z = true;
            return true;
        } catch (InvocationTargetException e) {
            return z;
        } catch (IllegalAccessException e2) {
            return z;
        }
    }

    public static void h() {
        new Thread(new f()).start();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.o);
        unregisterReceiver(this.n);
        this.d.b(this.z.h);
        if (!o()) {
            return;
        }
        if (this.u != null) {
            this.x[0] = Boolean.TRUE;
            a(this.u, this.x);
            return;
        }
        this.v[0] = Boolean.FALSE;
        a(this.s, this.v);
    }

    public IBinder onBind(Intent intent) {
        new StringBuilder("onBind<Action: ").append(intent.getAction()).append(">");
        return this.l;
    }

    @Deprecated
    public static void i() {
    }

    public final boolean a(n nVar) {
        return this.d != null ? this.d.a(nVar) : false;
    }

    public static void b() {
        DownloadService downloadService = h;
        downloadService.z.d.a();
        DownloadEngine downloadEngine = downloadService.d;
        if (downloadEngine.j != null) {
            downloadEngine.j.obtainMessage(IHost.HOST_NOFITY_PAGE_SELECTED, 0, 0, null).sendToTarget();
            downloadEngine.k.quit();
        }
        com.xunlei.downloadprovider.service.downloads.task.a.a aVar = downloadEngine.f;
        aVar.g.a();
        com.xunlei.downloadprovider.service.downloads.task.b.b bVar = aVar.e;
        a aVar2 = bVar.a;
        aVar2.e = 0;
        aVar2.f = 0;
        aVar2.d = 0.0d;
        aVar2.a = 0;
        aVar2.b = 0;
        aVar2.c = 0;
        bVar.c = 0;
        synchronized (aVar.b) {
            for (TaskInfo taskInfo : aVar.b) {
                if (taskInfo != null && taskInfo.mTaskStatus == 2) {
                    taskInfo.syncExtraInfo();
                    aVar.l.a(taskInfo.mExtraInfo);
                }
            }
        }
        downloadService.k = false;
        Intent intent = new Intent();
        intent.setClass(BrothersApplication.a(), DownloadService.class);
        try {
            BrothersApplication.a().unbindService(f);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        BrothersApplication.a().stopService(intent);
        h = null;
    }

    public final boolean a(String str, String str2, int i, String str3, Handler handler) {
        String a = com.xunlei.downloadprovider.service.downloads.b.c.a(str2);
        com.xunlei.downloadprovider.notification.a.a((Context) this).g = true;
        DownloadEngine downloadEngine = this.d;
        com.xunlei.downloadprovider.service.downloads.task.c cVar = new com.xunlei.downloadprovider.service.downloads.task.c();
        cVar.a(str, a, 0, null, null, false, i, str3, 1);
        cVar.c = handler;
        return downloadEngine.a(cVar, handler);
    }

    public final boolean a(String str, String str2, long j, String str3, String str4, boolean z, int i, String str5, int i2, Handler handler, com.xunlei.downloadprovider.service.downloads.task.b bVar) {
        String a = com.xunlei.downloadprovider.service.downloads.b.c.a(str2);
        com.xunlei.downloadprovider.notification.a.a((Context) this).g = true;
        DownloadEngine downloadEngine = this.d;
        com.xunlei.downloadprovider.service.downloads.task.c cVar = new com.xunlei.downloadprovider.service.downloads.task.c();
        cVar.a(str, a, j, str3, str4, z, i, str5, i2);
        cVar.c = handler;
        cVar.a(bVar);
        return downloadEngine.a(cVar, handler);
    }

    public final boolean a(String str, String str2, String str3, long j, String str4, int i, String str5, int i2, Handler handler) {
        String a = com.xunlei.downloadprovider.service.downloads.b.c.a(str);
        com.xunlei.downloadprovider.notification.a.a((Context) this).g = true;
        DownloadEngine downloadEngine = this.d;
        com.xunlei.downloadprovider.service.downloads.task.c cVar = new com.xunlei.downloadprovider.service.downloads.task.c();
        cVar.a(a, str2, str3, str4, j, i, str5, i2);
        cVar.c = handler;
        return downloadEngine.a(cVar, handler);
    }

    public final boolean a(String str, String str2, String str3, long j, int i, String str4, int i2, Handler handler) {
        String a = com.xunlei.downloadprovider.service.downloads.b.c.a(str);
        com.xunlei.downloadprovider.notification.a.a((Context) this).g = true;
        DownloadEngine downloadEngine = this.d;
        com.xunlei.downloadprovider.service.downloads.task.c cVar = new com.xunlei.downloadprovider.service.downloads.task.c();
        cVar.a(a, str2, com.umeng.a.d, str3, j, i, str4, i2);
        cVar.c = handler;
        return downloadEngine.a(cVar, handler);
    }

    static /* synthetic */ void k() {
        new StringBuilder().append(BrothersApplication.c()).append(" delayInit start");
        LoginHelper.a();
        if (!LoginHelper.c()) {
            LoginHelper.a().u();
        }
    }
}
