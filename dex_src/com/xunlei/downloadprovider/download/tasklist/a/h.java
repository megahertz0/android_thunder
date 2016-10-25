package com.xunlei.downloadprovider.download.tasklist.a;

import android.database.Cursor;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.util.c;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.kernel.a;
import com.xunlei.downloadprovider.service.downloads.task.a.k;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskBasicInfo;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.vod.b.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.android.spdy.SpdyAgent;

// compiled from: TaskListManager.java
public class h {
    private static h g;
    protected final ConcurrentHashMap<Long, a> a;
    protected b b;
    protected b c;
    protected b d;
    public final a e;
    public final b f;
    private final k h;
    private ExecutorService i;
    private int j;

    static /* synthetic */ void b(a aVar) {
        int i = aVar.mRunningInfo.c;
        if (i != -1 && aVar.mRunningInfo.a()) {
            aVar.mRunningInfo.a(-1);
            aVar.mRevision++;
            i = -1;
        }
        switch (aVar.mTaskStatus) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (i == 2 || i == 1) {
                    aVar.mRunningInfo.c = -1;
                    aVar.mRevision++;
                }
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (i == 4) {
                    aVar.mRunningInfo.c = -1;
                    aVar.mRevision++;
                }
            default:
                break;
        }
    }

    public final void a(int i) {
        boolean z;
        boolean z2 = true;
        this.j = i;
        b bVar = this.c;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        bVar.f = z;
        bVar = this.d;
        if (i == 2) {
            z = true;
        } else {
            z = false;
        }
        bVar.f = z;
        b bVar2 = this.b;
        if (i != 0) {
            z2 = false;
        }
        bVar2.f = z2;
    }

    private h() {
        this.h = new k("TaskListManager", new i(this));
        this.i = Executors.newSingleThreadExecutor();
        this.a = new ConcurrentHashMap(1);
        this.b = new b(0);
        this.c = new b(1);
        this.d = new b(2);
        this.e = new a();
        this.j = 0;
        this.f = new j(this);
        this.h.start();
    }

    public static h a() {
        if (g == null) {
            synchronized (h.class) {
                g = new h();
            }
        }
        return g;
    }

    public final a a(long j) {
        return (a) this.a.get(Long.valueOf(j));
    }

    public final b b(int i) {
        if (i == 1) {
            return this.c;
        }
        if (i == 2) {
            return this.d;
        }
        return i == 0 ? this.b : null;
    }

    public final boolean b() {
        return (!this.e.g || ((Boolean) this.e.i.a()).booleanValue()) ? this.e.g : false;
    }

    public final boolean a(Cursor cursor, boolean z) {
        if (cursor == null) {
            return false;
        }
        c cVar;
        b bVar = this.f;
        long nanoTime = System.nanoTime();
        a aVar = new a();
        aVar.a(cursor);
        List arrayList = new ArrayList();
        c cVar2 = (c) bVar.c.a();
        if (cVar2 == null) {
            cVar = new c(arrayList, z);
        } else {
            cVar2.c = arrayList;
            cVar2.b = z;
            cVar = cVar2;
        }
        while (cursor.moveToNext()) {
            try {
                long j = cursor.getLong(aVar.a);
                if (j > 0) {
                    boolean z2;
                    TaskBasicInfo taskBasicInfo = (TaskBasicInfo) cVar.a.a(Long.valueOf(j));
                    if (taskBasicInfo == null) {
                        taskBasicInfo = new TaskBasicInfo();
                    }
                    taskBasicInfo.mTaskId = j;
                    if (taskBasicInfo == null) {
                        taskBasicInfo = new TaskBasicInfo();
                    }
                    taskBasicInfo.mTaskId = cursor.getLong(aVar.a);
                    taskBasicInfo.mTitle = cursor.getString(aVar.d);
                    taskBasicInfo.mFileName = taskBasicInfo.mTitle;
                    taskBasicInfo.mLocalFileName = cursor.getString(aVar.i);
                    taskBasicInfo.mFilePath = taskBasicInfo.mLocalFileName;
                    taskBasicInfo.mFileSize = cursor.getLong(aVar.f);
                    taskBasicInfo.mCID = cursor.getString(aVar.b);
                    taskBasicInfo.mGCID = cursor.getString(aVar.c);
                    taskBasicInfo.mUrl = cursor.getString(aVar.e);
                    taskBasicInfo.mCreateTime = cursor.getLong(aVar.j);
                    taskBasicInfo.mLastModifiedTime = cursor.getLong(aVar.k);
                    taskBasicInfo.mDownloadDurationTime = cursor.getLong(aVar.l);
                    taskBasicInfo.mTaskType = TaskType.values()[cursor.getInt(aVar.h)];
                    switch (AnonymousClass_1.a[taskBasicInfo.mTaskType.ordinal()]) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            taskBasicInfo.mTaskOldType = 4;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            taskBasicInfo.mTaskOldType = 1;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            taskBasicInfo.mTaskOldType = 3;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            taskBasicInfo.mTaskOldType = 2;
                            break;
                        default:
                            taskBasicInfo.mTaskOldType = 0;
                            break;
                    }
                    if (taskBasicInfo.mTaskType == TaskType.BT) {
                        taskBasicInfo.mInfoHash = cursor.getString(aVar.K);
                    }
                    int i = cursor.getInt(aVar.g);
                    taskBasicInfo.mOriginalStatusCode = i;
                    taskBasicInfo.mTaskStatus = DownloadManager.translateStatus(i);
                    taskBasicInfo.mFailureReason = DownloadManager.getReason(i);
                    taskBasicInfo.mErrorMsg = cursor.getString(aVar.m);
                    taskBasicInfo.mResLinkTotal = (long) cursor.getInt(aVar.I);
                    taskBasicInfo.mResLinkUsed = (long) cursor.getInt(aVar.J);
                    taskBasicInfo.mDownloadedSize = cursor.getLong(aVar.o);
                    taskBasicInfo.mDownloadSpeed = cursor.getLong(aVar.p);
                    taskBasicInfo.mOriginSpeed = cursor.getLong(aVar.r);
                    taskBasicInfo.mOriginReceivedSize = cursor.getLong(aVar.q);
                    taskBasicInfo.mIsXunleiSpdy = cursor.getInt(aVar.s) == 1;
                    taskBasicInfo.mP2pSpeed = cursor.getLong(aVar.v);
                    taskBasicInfo.mP2pReceivedSize = cursor.getLong(aVar.w);
                    taskBasicInfo.mP2sSpeed = cursor.getLong(aVar.t);
                    taskBasicInfo.mP2sReceivedSize = cursor.getLong(aVar.u);
                    if (cursor.getInt(aVar.x) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    taskBasicInfo.mHasLixianSpeedup = z2;
                    taskBasicInfo.mLixianSpeed = cursor.getLong(aVar.z);
                    taskBasicInfo.mLixianStatusCode = cursor.getInt(aVar.y);
                    taskBasicInfo.mLixianStatus = DownloadManager.translateStatus(taskBasicInfo.mLixianStatusCode);
                    taskBasicInfo.mLixianProgress = (long) cursor.getInt(aVar.B);
                    taskBasicInfo.mLixianReceivedSize = cursor.getLong(aVar.A);
                    if (cursor.getInt(aVar.C) == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    taskBasicInfo.mHasVipChannelSpeedup = z2;
                    taskBasicInfo.mVipChannelSpeed = cursor.getLong(aVar.E);
                    taskBasicInfo.mVipChannelStatusCode = cursor.getInt(aVar.D);
                    taskBasicInfo.mVipChannelStatus = DownloadManager.translateStatus(taskBasicInfo.mVipChannelStatusCode);
                    taskBasicInfo.mVipChannelReceivedSize = cursor.getLong(aVar.F);
                    taskBasicInfo.mAppName = cursor.getString(aVar.G);
                    taskBasicInfo.mAppVersion = cursor.getString(aVar.H);
                    arrayList.add(taskBasicInfo);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        new StringBuilder("LoadTaskListFromCursor: cost = ").append((System.nanoTime() - nanoTime) / 1000000).append("ms, size =  ").append(arrayList.size());
        synchronized (bVar.b) {
            bVar.b.add(cVar);
        }
        try {
            bVar.d.execute(new n(bVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }

    public final void b(long j) {
        try {
            m mVar = new m(this);
            if (j > 0) {
                Handler handler = this.h.a;
                if (handler == null) {
                    throw new RejectedExecutionException();
                }
                handler.postDelayed(mVar, j);
                return;
            }
            this.h.execute(mVar);
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        }
    }

    private static boolean a(a aVar, long j, boolean z) {
        long j2 = j - aVar.l;
        if (!(aVar.mLocalFileName == null || !TextUtils.isEmpty(aVar.e) || aVar.mTaskStatus == 16 || aVar.c || aVar.mDownloadedSize <= 0)) {
            if (j - aVar.f >= 5000 || aVar.e == null) {
                d.a();
                aVar.e = d.c(aVar.mLocalFileName);
                if (aVar.e == null) {
                    aVar.e = com.umeng.a.d;
                }
                aVar.f = j;
            }
        }
        if (aVar.mTaskStatus != 8 || aVar.mLocalFileName == null || (!z && j2 >= 0 && j2 < 5000 && aVar.l > 0)) {
            return false;
        }
        try {
            b.a b = b.a().b(aVar.mLocalFileName);
            Object obj = aVar.e;
            b.a b2 = TextUtils.isEmpty(obj) ? null : b.a().b(obj);
            if (b == null || (b.k < 500 && b2 != null)) {
                b = b2;
            }
            if (b != null) {
                aVar.k = b.l;
                aVar.j = b.k;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        aVar.l = j;
        return true;
    }

    private static boolean b(a aVar, long j, boolean z) {
        if (aVar.mTaskStatus == 8) {
            long j2 = j - aVar.d;
            if (z || aVar.d <= 0 || j2 < 0 || j2 >= 3000) {
                aVar.c = false;
                try {
                    if (!new File(aVar.mLocalFileName).exists()) {
                        aVar.c = true;
                    }
                    if (!aVar.mConsumed) {
                        com.xunlei.downloadprovider.service.downloads.task.b.d.a();
                        aVar.mConsumed = com.xunlei.downloadprovider.service.downloads.task.b.d.b(aVar.mTaskId);
                    }
                } catch (Exception e) {
                }
                aVar.d = j;
                return true;
            }
        }
        aVar.c = false;
        return false;
    }

    public final void c() {
        this.b.b();
        this.c.b();
        this.d.b();
    }

    public final void d() {
        this.b.e = false;
        this.c.e = false;
        this.d.e = false;
    }

    public final void a(List<TaskRunningInfo> list) {
        if (!list.isEmpty()) {
            this.b.a(list);
            this.c.a(list);
            this.d.a(list);
        }
    }

    public final void a(Collection<Long> collection) {
        if (collection != null && !collection.isEmpty()) {
            List arrayList = new ArrayList();
            for (Long l : collection) {
                TaskRunningInfo taskRunningInfo = (TaskRunningInfo) this.a.remove(l);
                if (taskRunningInfo != null) {
                    arrayList.add(taskRunningInfo);
                }
            }
            if (!arrayList.isEmpty()) {
                a(arrayList);
            }
            this.b.a(collection);
            this.c.a(collection);
            this.d.a(collection);
        }
    }

    static /* synthetic */ void a(h hVar, List list, boolean z) {
        List arrayList = new ArrayList();
        for (TaskBasicInfo taskBasicInfo : list) {
            long j = taskBasicInfo.mTaskId;
            if (j > 0) {
                int i = -1;
                TaskRunningInfo a = hVar.a(j);
                if (a == null) {
                    a = new a();
                    a.mTaskId = j;
                    hVar.a.put(Long.valueOf(j), a);
                } else {
                    i = a.mTaskStatus;
                }
                a.mTitle = taskBasicInfo.mTitle;
                a.mFileName = a.mTitle;
                a.mLocalFileName = taskBasicInfo.mLocalFileName;
                a.mFilePath = a.mLocalFileName;
                a.mFileSize = taskBasicInfo.mFileSize;
                a.mCID = taskBasicInfo.mCID;
                a.mGCID = taskBasicInfo.mGCID;
                a.mUrl = taskBasicInfo.mUrl;
                a.mCreateTime = taskBasicInfo.mCreateTime;
                a.mLastModifiedTime = taskBasicInfo.mLastModifiedTime;
                a.mDownloadDurationTime = taskBasicInfo.mDownloadDurationTime;
                a.mTaskType = taskBasicInfo.mTaskType;
                switch (AnonymousClass_1.a[a.mTaskType.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        a.mTaskOldType = 4;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        a.mTaskOldType = 1;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        a.mTaskOldType = 3;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        a.mTaskOldType = 2;
                        break;
                    default:
                        a.mTaskOldType = 0;
                        break;
                }
                if (a.mTaskType == TaskType.BT) {
                    a.mInfoHash = taskBasicInfo.mInfoHash;
                }
                a.mOriginalStatusCode = taskBasicInfo.mOriginalStatusCode;
                a.mTaskStatus = taskBasicInfo.mTaskStatus;
                a.mFailureReason = taskBasicInfo.mFailureReason;
                a.mErrorMsg = taskBasicInfo.mErrorMsg;
                a.mResLinkTotal = taskBasicInfo.mResLinkTotal;
                a.mResLinkUsed = taskBasicInfo.mResLinkUsed;
                a.mDownloadedSize = taskBasicInfo.mDownloadedSize;
                a.mDownloadSpeed = taskBasicInfo.mDownloadSpeed;
                a.mOriginSpeed = taskBasicInfo.mOriginSpeed;
                a.mOriginReceivedSize = taskBasicInfo.mOriginReceivedSize;
                a.mIsXunleiSpdy = taskBasicInfo.mIsXunleiSpdy;
                a.mP2pSpeed = taskBasicInfo.mP2pSpeed;
                a.mP2pReceivedSize = taskBasicInfo.mP2pReceivedSize;
                a.mP2sSpeed = taskBasicInfo.mP2sSpeed;
                a.mP2sReceivedSize = taskBasicInfo.mP2sReceivedSize;
                a.mHasLixianSpeedup = taskBasicInfo.mHasLixianSpeedup;
                a.mLixianSpeed = taskBasicInfo.mLixianSpeed;
                a.mLixianStatusCode = taskBasicInfo.mLixianStatusCode;
                a.mLixianStatus = taskBasicInfo.mLixianStatus;
                a.mLixianProgress = taskBasicInfo.mLixianProgress;
                a.mLixianReceivedSize = taskBasicInfo.mLixianReceivedSize;
                a.mHasVipChannelSpeedup = taskBasicInfo.mHasVipChannelSpeedup;
                a.mVipChannelSpeed = taskBasicInfo.mVipChannelSpeed;
                a.mVipChannelStatusCode = taskBasicInfo.mVipChannelStatusCode;
                a.mVipChannelStatus = taskBasicInfo.mVipChannelStatus;
                a.mVipChannelReceivedSize = taskBasicInfo.mVipChannelReceivedSize;
                a.mAppName = taskBasicInfo.mAppName;
                a.mAppVersion = taskBasicInfo.mAppVersion;
                a.mAcceleratedChannelSpeed = a.mP2pSpeed + a.mP2sSpeed;
                a.mVipAcceleratedChannelSpeed = 0;
                if (a.mHasLixianSpeedup) {
                    a.mAcceleratedChannelSpeed += a.mLixianSpeed;
                    a.mVipAcceleratedChannelSpeed += a.mLixianSpeed;
                }
                a.mAcceleratedChannelSpeed += a.mVipChannelSpeed;
                a.mVipAcceleratedChannelSpeed += a.mVipChannelSpeed;
                a.mAcceleratedChannelDownloadedSize = a.mDownloadedSize - a.mOriginReceivedSize;
                a.mVipAcceleratedChannelDownloadedSize = a.mVipChannelReceivedSize + a.mLixianReceivedSize;
                if (!a.mHasSpeed && a.mDownloadSpeed > 0) {
                    a.mHasSpeed = true;
                }
                if (!a.mHasOriginSpeed && a.mOriginSpeed > 0) {
                    a.mHasOriginSpeed = true;
                }
                Object obj = 1;
                if (i == a.mTaskStatus) {
                    if (i == 8 || i == 4 || i == 1 || i == 16) {
                        obj = null;
                    }
                }
                if (obj != null) {
                    a.mRevision++;
                }
                arrayList.add(a);
            }
        }
        k kVar = new k(hVar, arrayList, z);
        if (hVar.h.isAlive()) {
            hVar.h.execute(kVar);
        } else {
            kVar.run();
        }
    }

    static /* synthetic */ void a(a aVar) {
        int i;
        TaskInfo d;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = aVar.mDownloadSpeed;
        if (j <= 0 || aVar.mFileSize <= 0) {
            aVar.mDownloadRemainTime = -1;
        } else {
            long j2 = aVar.mFileSize - aVar.mDownloadedSize;
            if (j2 > 0) {
                aVar.mDownloadRemainTime = j2 / j;
            }
        }
        if (aVar.a == null) {
            aVar.a = com.umeng.a.d;
            c.a a = c.a(aVar.mFileName);
            if (!(a == null || aVar.mFileName.contains("magnet") || aVar.mTaskType == TaskType.MAGNET)) {
                Object obj;
                String str = com.umeng.a.d;
                if (a.b > 0) {
                    str = new StringBuilder("\u7b2c").append(a.b).append("\u96c6").toString();
                }
                if (!TextUtils.isEmpty(a.d)) {
                    obj = a.d + " " + obj;
                } else if (a.a > 0) {
                    obj = new StringBuilder("\u7b2c").append(com.xunlei.xllib.b.c.a(a.a)).append("\u5b63 ").append(obj).toString();
                }
                if (!TextUtils.isEmpty(obj)) {
                    aVar.a = obj;
                    i = 1;
                    if (aVar.g == null || aVar.g == EFileCategoryType.E_OTHER_CATEGORY) {
                        if (TextUtils.isEmpty(aVar.mLocalFileName)) {
                            aVar.g = XLFileTypeUtil.a(aVar.mLocalFileName);
                        } else {
                            aVar.g = XLFileTypeUtil.a(aVar.mTitle);
                        }
                    }
                    if (b(aVar, elapsedRealtime, false)) {
                        i++;
                    }
                    d.a();
                    d = d.d(aVar.mTaskId);
                    if (!(d == null || aVar.mSeen != 0 || aVar.mSeen == d.mSeen)) {
                        aVar.mSeen = d.mSeen;
                        i++;
                    }
                    if (!(d == null || TextUtils.isEmpty(d.mRefUrl) || !TextUtils.isEmpty(aVar.mRefUrl))) {
                        aVar.mRefUrl = d.mRefUrl;
                    }
                    if (!(d == null || TextUtils.isEmpty(d.mIconUrl) || !TextUtils.isEmpty(aVar.mIconUrl))) {
                        aVar.mIconUrl = d.mIconUrl;
                    }
                    if (!(d == null || TextUtils.isEmpty(d.mDisplayName) || !TextUtils.isEmpty(aVar.mDisplayName))) {
                        aVar.mDisplayName = d.mDisplayName;
                    }
                    if (i > 0) {
                        aVar.mRevision++;
                    }
                }
            }
        }
        i = 0;
        if (TextUtils.isEmpty(aVar.mLocalFileName)) {
            aVar.g = XLFileTypeUtil.a(aVar.mTitle);
        } else {
            aVar.g = XLFileTypeUtil.a(aVar.mLocalFileName);
        }
        if (b(aVar, elapsedRealtime, false)) {
            i++;
        }
        d.a();
        d = d.d(aVar.mTaskId);
        aVar.mSeen = d.mSeen;
        i++;
        aVar.mRefUrl = d.mRefUrl;
        aVar.mIconUrl = d.mIconUrl;
        aVar.mDisplayName = d.mDisplayName;
        if (i > 0) {
            aVar.mRevision++;
        }
    }

    static /* synthetic */ void c(a aVar) {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (aVar.g == null || aVar.g == EFileCategoryType.E_OTHER_CATEGORY) {
            if (TextUtils.isEmpty(aVar.mLocalFileName)) {
                aVar.g = XLFileTypeUtil.a(aVar.mTitle);
            } else {
                aVar.g = XLFileTypeUtil.a(aVar.mLocalFileName);
            }
        }
        if (b(aVar, elapsedRealtime, false)) {
            i = 1;
        } else {
            i = 0;
        }
        if (aVar.g == EFileCategoryType.E_VIDEO_CATEGORY) {
            if (!(aVar.e != null || aVar.mLocalFileName == null || aVar.mTaskStatus == 16 || aVar.c || aVar.mDownloadedSize <= 0)) {
                d.a();
                aVar.e = d.c(aVar.mLocalFileName);
                if (aVar.e == null) {
                    aVar.e = com.umeng.a.d;
                }
            }
            if (a(aVar, elapsedRealtime, false)) {
                i++;
            }
        }
        if (aVar.mFreeTrialLMT == 0 || elapsedRealtime - aVar.mFreeTrialLMT >= 3000) {
            aVar.mFreeTrialLMT = elapsedRealtime;
            d.a();
            aVar.mIsEnteredHighSpeedTrial = d.f(aVar.getTaskId());
            d.a();
            aVar.mFreeTrialTimes = d.e(aVar.getTaskId());
            i++;
        }
        if (i > 0) {
            aVar.mRevision++;
        }
    }

    static /* synthetic */ void c(h hVar) {
        if (hVar.b.f) {
            hVar.b.d.b();
        }
        if (hVar.c.f) {
            hVar.c.d.b();
        }
        if (hVar.d.f) {
            hVar.d.d.b();
        }
    }
}
