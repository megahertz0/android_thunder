package com.xunlei.downloadprovider.service.downloads.task;

import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.download.DownloadManager.Property;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.DownloadEngine;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.kernel.c;
import com.xunlei.downloadprovider.service.downloads.task.a.f;
import com.xunlei.downloadprovider.service.downloads.task.a.n;
import com.xunlei.downloadprovider.service.downloads.task.a.o;
import com.xunlei.downloadprovider.service.downloads.task.b.b;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

// compiled from: DownloadTaskManger.java
public final class d implements k {
    private static d c;
    public o a;
    public ExecutorService b;

    public d() {
        this.a = new o();
        this.b = Executors.newCachedThreadPool();
    }

    static {
        c = new d();
    }

    public static d a() {
        return c;
    }

    public static boolean b() {
        return DownloadService.a() != null;
    }

    public static boolean c() {
        DownloadService a = DownloadService.a();
        return a != null && a.d.a() > 0;
    }

    public static long d() {
        DownloadService a = DownloadService.a();
        if (a == null) {
            return 0;
        }
        b bVar = a.d.f.e;
        if (bVar.c > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime > bVar.c) {
                elapsedRealtime = (elapsedRealtime - bVar.c) / 1000;
                if (elapsedRealtime >= 5) {
                    if (elapsedRealtime >= 10) {
                        bVar.a.a = 0;
                    }
                    return bVar.a.a / elapsedRealtime;
                }
            }
        }
        return bVar.a.a;
    }

    public static long e() {
        DownloadService a = DownloadService.a();
        return a != null ? a.d.f.e.a.b : 0;
    }

    public static long f() {
        DownloadService a = DownloadService.a();
        return a != null ? a.d.f.e.a.c : 0;
    }

    public static void a(String str) {
        DownloadService a = DownloadService.a();
        if (a != null) {
            a.d.a(str);
        }
    }

    @Deprecated
    public static void g() {
        DownloadService a = DownloadService.a();
        if (a != null && a.d != null) {
            DownloadEngine downloadEngine = a.d;
            if (downloadEngine.g == null || com.xunlei.xllib.a.b.a(downloadEngine.g)) {
                BrothersApplication.a(true);
            } else {
                BrothersApplication.a(false);
            }
            downloadEngine.a(new n(2));
        }
    }

    public static boolean a(List<Long> list) {
        DownloadService a = DownloadService.a();
        if (a != null) {
            DownloadEngine downloadEngine = a.d;
            n nVar = new n(1);
            nVar.c = null;
            nVar.b = list;
            downloadEngine.a(nVar);
        }
        return false;
    }

    public static boolean a(Handler handler, boolean z) {
        DownloadService a = DownloadService.a();
        return (a == null || a.d == null) ? false : a.d.a(handler, z);
    }

    private static boolean c(Collection<Long> collection) {
        DownloadService a = DownloadService.a();
        if (a == null) {
            return c.a().a(com.xunlei.downloadprovider.service.downloads.b.c.a((Collection) collection)) > 0;
        } else {
            if (a.d == null) {
                return false;
            }
            DownloadEngine downloadEngine = a.d;
            n nVar = new n(4);
            nVar.c = null;
            nVar.b = collection;
            nVar.f = true;
            return downloadEngine.a(nVar);
        }
    }

    public static boolean a(long... jArr) {
        Collection hashSet = new HashSet();
        if (jArr.length > 0) {
            for (long j : jArr) {
                hashSet.add(Long.valueOf(j));
            }
        }
        return c(hashSet);
    }

    public static boolean a(boolean z, long... jArr) {
        DownloadService a = DownloadService.a();
        if (a == null) {
            return c.a().c(z, jArr) > 0;
        } else {
            Collection hashSet = new HashSet();
            for (int i = 0; i <= 0; i++) {
                hashSet.add(Long.valueOf(jArr[0]));
            }
            n nVar = new n(3);
            nVar.b = hashSet;
            nVar.e = z;
            return a.a(nVar);
        }
    }

    public static boolean b(boolean z, long... jArr) {
        int i = 0;
        DownloadService a = DownloadService.a();
        if (a == null) {
            return c.a().a(z, jArr) > 0;
        } else {
            Collection hashSet = new HashSet();
            if (jArr.length > 0) {
                int length = jArr.length;
                while (i < length) {
                    hashSet.add(Long.valueOf(jArr[i]));
                    i++;
                }
            }
            n nVar = new n(1);
            nVar.b = hashSet;
            nVar.e = z;
            return a.a(nVar);
        }
    }

    public static boolean a(boolean z) {
        DownloadService a = DownloadService.a();
        if (a == null) {
            return false;
        }
        n nVar = new n(2);
        nVar.e = z;
        return a.a(nVar);
    }

    public final boolean c(boolean z, long... jArr) {
        boolean z2 = false;
        DownloadService a = DownloadService.a();
        Collection hashSet = new HashSet();
        if (jArr.length > 0) {
            int length = jArr.length;
            for (int i = 0; i < length; i++) {
                hashSet.add(Long.valueOf(jArr[i]));
            }
        }
        if (a != null) {
            n nVar = new n(6);
            nVar.b = hashSet;
            nVar.d = z;
            return a.a(nVar);
        }
        if (c.a().b(z, jArr) > 0) {
            z2 = true;
        }
        this.a.a(hashSet);
        return z2;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        DownloadService a = DownloadService.a();
        if (a == null) {
            return false;
        }
        n nVar = new n(7);
        nVar.g = str;
        nVar.d = false;
        return a.a(nVar);
    }

    public static void h() {
        DownloadService a = DownloadService.a();
        if (a != null && a.d != null) {
            DownloadEngine downloadEngine = a.d;
            if (downloadEngine.j != null) {
                downloadEngine.j.sendEmptyMessage(123);
            }
        }
    }

    public static void a(long j) {
        c.a().c(j);
        c.a().b(j);
    }

    public static String c(String str) {
        return c.a().a(str);
    }

    public final void b(long j) {
        try {
            this.b.execute(new e(this, Long.valueOf(j)));
        } catch (RejectedExecutionException e) {
            c.a().a(j);
        }
    }

    public final void c(long j) {
        this.b.execute(new g(this, Long.valueOf(j)));
    }

    public static long i() {
        return c.a().c();
    }

    public static void a(int i) {
        if (i <= 0 || i > 5) {
            i = 3;
        }
        c a = c.a();
        if (i <= 0 || i > 5) {
            i = 3;
        }
        if (a.a != null) {
            a.a.setRecommandMaxConcurrentDownloads(i);
        }
    }

    public static void a(long j, String str) {
        c a = c.a();
        if (a.a != null) {
            new StringBuilder("DownloadSDK: setUserLoginInfo - UserId = ").append(j).append(" jumpKey = ").append(str);
            a.a.setProperty(Property.PROP_USER_ID, j == 0 ? a.d : String.valueOf(j));
            a.a.setProperty(Property.PROP_JUMP_KEY, str == null ? a.d : str);
        }
        if (j == 0 || TextUtils.isEmpty(str)) {
            DownloadService a2 = DownloadService.a();
            if (a2 != null && a2.d != null) {
                a2.d.j.obtainMessage(139).sendToTarget();
            }
        }
    }

    public static TaskInfo d(long j) {
        DownloadService a = DownloadService.a();
        return (a == null || j == -1) ? null : (TaskInfo) a.d.f.a.get(Long.valueOf(j));
    }

    public static long d(String str) {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? -1 : a.g().b(str);
    }

    public static long e(String str) {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? -1 : a.g().a(str);
    }

    public static int j() {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? 0 : a.g().i();
    }

    public static int k() {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? 0 : a.g().f();
    }

    public static int l() {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? 0 : a.g().h();
    }

    public static com.xunlei.downloadprovider.service.downloads.task.info.b m() {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? new com.xunlei.downloadprovider.service.downloads.task.info.b() : a.g().g();
    }

    public static long n() {
        DownloadService a = DownloadService.a();
        return (a == null || a.g() == null) ? 0 : a.g().b();
    }

    public static void o() {
        DownloadService a = DownloadService.a();
        if (a != null) {
            DownloadEngine downloadEngine = a.d;
            downloadEngine.e = downloadEngine.d;
        }
    }

    public static void a(TaskRunningInfo taskRunningInfo) {
        int i = 0;
        DownloadService a = DownloadService.a();
        if (a != null) {
            TaskInfo taskInfo;
            int i2;
            DownloadEngine downloadEngine = a.d;
            com.xunlei.downloadprovider.service.downloads.task.a.a aVar = downloadEngine.f;
            long j = taskRunningInfo.mTaskId;
            if (j == -1) {
                taskInfo = null;
            } else {
                taskInfo = (TaskInfo) aVar.a.get(Long.valueOf(j));
            }
            if (taskInfo != null && taskInfo.mTaskId == taskRunningInfo.mTaskId) {
                taskInfo.mSeen = 1;
                aVar.f.execute(new f(aVar, taskInfo));
            }
            int d = downloadEngine.d();
            int a2 = downloadEngine.f.a(false).a();
            if (downloadEngine.e >= downloadEngine.d) {
                i2 = 0;
            } else if (d <= 0 || a2 != 0) {
                i2 = 0;
            } else {
                downloadEngine.e = downloadEngine.d;
                i2 = d;
            }
            if (downloadEngine.a != null) {
                while (i < downloadEngine.a.size()) {
                    ((Handler) downloadEngine.a.get(i)).obtainMessage(114, i2, a2).sendToTarget();
                    i++;
                }
            }
        }
    }

    public static boolean a(Handler handler) {
        DownloadService a = DownloadService.a();
        if (a != null) {
            DownloadEngine downloadEngine = a.d;
            if (downloadEngine.a == null) {
                downloadEngine.a = new ArrayList();
            }
            if (!downloadEngine.a.contains(handler)) {
                downloadEngine.a.add(handler);
            }
        }
        return false;
    }

    public static boolean b(Handler handler) {
        DownloadService a = DownloadService.a();
        if (a != null) {
            DownloadEngine downloadEngine = a.d;
            if (downloadEngine.a != null && downloadEngine.a.contains(handler)) {
                downloadEngine.a.remove(handler);
            }
        }
        return false;
    }

    public static boolean p() {
        DownloadService a = DownloadService.a();
        if (a == null) {
            return false;
        }
        DownloadEngine downloadEngine = a.d;
        return downloadEngine.e < downloadEngine.d && downloadEngine.d() != 0;
    }

    public final void a(Collection<Long> collection) {
        this.a.a(collection);
    }

    public final void b(Collection<Long> collection) {
        this.a.b(collection);
    }

    public static int e(long j) {
        LoginHelper.a();
        return (LoginHelper.c() && LoginHelper.a().f()) ? 0 : c.a().a.getHighSpeedTrialTimes(j);
    }

    public static boolean f(long j) {
        return c.a().a.isEnteredHighSpeedTrial(j);
    }

    public static int g(long j) {
        LoginHelper.a();
        return (LoginHelper.c() && LoginHelper.a().f()) ? 0 : c.a().a.getHighSpeedDuration(j);
    }

    public static void h(long j) {
        c.a().a.stopHighSpeedTrial(j);
    }

    public static boolean q() {
        c a = c.a();
        return (a == null || a.a == null) ? false : a.a.getHighSpeedTrialEnable();
    }

    public static void b(boolean z) {
        c a = c.a();
        if (a != null && a.a != null) {
            a.a.setHighSpeedTrialEnable(z);
        }
    }

    public static com.xunlei.downloadprovider.service.downloads.task.b.c i(long j) {
        DownloadService a = DownloadService.a();
        if (a == null || a.d == null) {
            return null;
        }
        com.xunlei.downloadprovider.service.downloads.task.b.c a2 = a.d.f.e.a(j);
        if (a2 != null) {
            return a2;
        }
        com.xunlei.downloadprovider.service.downloads.task.info.c a3 = com.xunlei.downloadprovider.service.downloads.task.b.d.a().a(j);
        if (a3 == null) {
            return a2;
        }
        a2 = new com.xunlei.downloadprovider.service.downloads.task.b.c();
        a2.g = j;
        a2.a = a3.k;
        return a2;
    }
}
