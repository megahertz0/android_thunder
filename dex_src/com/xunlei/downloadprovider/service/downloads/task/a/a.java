package com.xunlei.downloadprovider.service.downloads.task.a;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler.Callback;
import android.os.SystemClock;
import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Query;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.b.c;
import com.xunlei.downloadprovider.service.downloads.task.b.b;
import com.xunlei.downloadprovider.service.downloads.task.b.d;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.android.spdy.SpdyAgent;

// compiled from: CoreTaskManager.java
public final class a implements j {
    public final ConcurrentHashMap<Long, TaskInfo> a;
    public final List<TaskInfo> b;
    public final HashSet<TaskInfo> c;
    public final HashSet<TaskInfo> d;
    public final b e;
    public final k f;
    public final m g;
    protected boolean h;
    protected int i;
    g j;
    public o k;
    public final d l;
    Set<Long> m;
    private final Callback n;
    private final com.xunlei.downloadprovider.service.downloads.kernel.a o;
    private int p;
    private com.xunlei.downloadprovider.service.downloads.task.info.b q;
    private long r;
    private long s;

    public a(g gVar) {
        this.a = new ConcurrentHashMap();
        this.b = new ArrayList();
        this.c = new HashSet();
        this.d = new HashSet();
        this.e = new b();
        this.n = new b(this);
        this.f = new k("CoreTaskManager", this.n);
        this.o = new com.xunlei.downloadprovider.service.downloads.kernel.a();
        this.g = new m();
        this.h = false;
        this.i = 0;
        this.p = 0;
        this.q = new com.xunlei.downloadprovider.service.downloads.task.info.b();
        this.r = 0;
        this.k = new o();
        this.s = 0;
        this.l = d.a();
        this.m = new HashSet();
        this.j = gVar;
        this.f.start();
    }

    public final void a() {
        this.p |= 1;
    }

    private void k() {
        this.p |= 2;
    }

    public final long b() {
        return this.s;
    }

    private long l() {
        if (this.a.size() <= 0) {
            return 0;
        }
        long j = 0;
        for (TaskInfo taskInfo : this.a.values()) {
            if (taskInfo.mTaskStatus == 1 || taskInfo.mTaskStatus == 2 || taskInfo.mTaskStatus == 4) {
                j = taskInfo.mDownloadedSize + j;
            } else {
                long j2;
                if (TextUtils.isEmpty(taskInfo.mLocalFileName) || !new File(taskInfo.mLocalFileName).exists()) {
                    j2 = j;
                } else {
                    j2 = j + taskInfo.mDownloadedSize;
                }
                j = j2;
            }
        }
        return j;
    }

    public final void a(long j, TaskInfo taskInfo) {
        this.a.put(Long.valueOf(j), taskInfo);
        a();
    }

    private void b(TaskInfo taskInfo) {
        synchronized (this.d) {
            if (this.d.contains(taskInfo)) {
                this.d.remove(taskInfo);
                a();
            }
        }
        this.c.add(taskInfo);
        a();
    }

    public final void a(TaskInfo taskInfo) {
        if (taskInfo.mTaskStatus == 8) {
            b(taskInfo);
            return;
        }
        if (this.c.contains(taskInfo)) {
            this.c.remove(taskInfo);
            a();
        }
        synchronized (this.d) {
            if (!this.d.contains(taskInfo)) {
                this.d.add(taskInfo);
                a();
            }
            k();
        }
    }

    public final Collection<TaskInfo> a(Collection<Long> collection) {
        Collection<TaskInfo> hashSet = new HashSet();
        if (this.a != null) {
            for (Long l : collection) {
                TaskInfo taskInfo = (TaskInfo) this.a.remove(l);
                if (taskInfo != null) {
                    hashSet.add(taskInfo);
                    d dVar = this.l;
                    long longValue = l.longValue();
                    synchronized (dVar.a) {
                        if (dVar.a.containsKey(Long.valueOf(longValue))) {
                            dVar.a.remove(Long.valueOf(longValue));
                        }
                    }
                    com.xunlei.downloadprovider.service.downloads.task.b.a.b.a().a(longValue);
                    com.xunlei.downloadprovider.service.downloads.task.b.a.b.a().b(longValue);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            this.b.removeAll(hashSet);
            this.d.removeAll(hashSet);
            this.c.removeAll(hashSet);
            a();
        }
        this.k.a(collection);
        return hashSet;
    }

    public final void c() {
        this.g.a(this.j.g, this.j.i, new c(this));
    }

    private void a(List<TaskInfo> list) {
        b bVar;
        a aVar;
        double d;
        a aVar2;
        a aVar3;
        long nanoTime = System.nanoTime();
        a aVar4 = this.e.b;
        aVar4.a = 0;
        aVar4.b = 0;
        aVar4.c = 0;
        aVar4.e = 0;
        aVar4.f = 0;
        Collection hashSet = new HashSet();
        boolean z = false;
        try {
            List arrayList = new ArrayList();
            if (!list.isEmpty()) {
                g.a().b((List) list);
                boolean z2 = false;
                for (TaskInfo taskInfo : list) {
                    try {
                        Object obj;
                        long taskId = taskInfo.getTaskId();
                        int i = taskInfo.mTaskStatus;
                        int i2 = taskInfo.mRunningInfo.b;
                        if (TextUtils.isEmpty(taskInfo.mUrlEigenvalue)) {
                            taskInfo.mUrlEigenvalue = c.c(taskInfo.mUrl);
                        }
                        c(taskInfo);
                        if (taskInfo.mRunningInfo.a) {
                            if (i == 8) {
                                b(taskInfo);
                            } else {
                                a(taskInfo);
                            }
                            obj = 1;
                            k();
                        }
                        if (taskInfo.mShouldAutoSpeedup && !taskInfo.mHasLixianSpeedup && !taskInfo.mHasVipChannelSpeedup && g.e()) {
                            com.xunlei.downloadprovider.service.downloads.kernel.c.a().c(taskInfo.mTaskId);
                            com.xunlei.downloadprovider.service.downloads.kernel.c.a().b(taskInfo.mTaskId);
                        }
                        if (taskInfo.mTaskStatus == 2 && this.e.a(taskInfo.mTaskId) == null) {
                            com.xunlei.downloadprovider.service.downloads.task.info.c a = d.a().a(taskInfo.mTaskId);
                            this.e.a(taskInfo.mTaskId, false);
                            com.xunlei.downloadprovider.service.downloads.task.b.c a2 = this.e.a(taskInfo.mTaskId);
                            if (!(a == null || a2 == null)) {
                                a2.a = Math.max(a.k, a2.a);
                            }
                        }
                        if (taskInfo.mTaskStatus == 8) {
                            b(taskInfo);
                        } else {
                            a(taskInfo);
                        }
                        this.e.a(taskInfo);
                        taskInfo.mRunningInfo.a = false;
                        if (i2 != i) {
                            if (i2 == 8) {
                                a(taskInfo);
                            }
                            k();
                            obj = 1;
                            if (i == 8 || i == 4 || i == 16) {
                                a(taskInfo, i, i2);
                                hashSet.add(Long.valueOf(taskId));
                            }
                        }
                        arrayList.add(taskInfo);
                    } catch (Exception e) {
                        Exception exception = e;
                        z = z2;
                        Exception exception2 = exception;
                    }
                }
                this.j.a(arrayList);
                z = z2;
            }
        } catch (Exception e2) {
            exception2 = e2;
            exception2.printStackTrace();
            bVar = this.e;
            aVar = bVar.b;
            if (aVar.f != 0) {
                d = (((double) aVar.e) * 1.0d) / ((double) aVar.f);
            } else {
                d = 0.0d;
            }
            aVar.d = d;
            bVar.c = SystemClock.elapsedRealtime();
            aVar2 = bVar.a;
            aVar3 = bVar.b;
            aVar2.e = aVar3.e;
            aVar2.f = aVar3.f;
            aVar2.d = aVar3.d;
            aVar2.a = aVar3.a;
            aVar2.b = aVar3.b;
            aVar2.c = aVar3.c;
            this.s = l();
            a(z);
            if (!hashSet.isEmpty()) {
                this.k.b(hashSet);
            }
            new StringBuilder("UpdateTaskList: cost = ").append((System.nanoTime() - nanoTime) / 1000000).append("ms, size =  ").append(list.size());
        }
        bVar = this.e;
        aVar = bVar.b;
        if (aVar.f != 0) {
            d = 0.0d;
        } else {
            d = (((double) aVar.e) * 1.0d) / ((double) aVar.f);
        }
        aVar.d = d;
        bVar.c = SystemClock.elapsedRealtime();
        aVar2 = bVar.a;
        aVar3 = bVar.b;
        aVar2.e = aVar3.e;
        aVar2.f = aVar3.f;
        aVar2.d = aVar3.d;
        aVar2.a = aVar3.a;
        aVar2.b = aVar3.b;
        aVar2.c = aVar3.c;
        this.s = l();
        a(z);
        if (hashSet.isEmpty()) {
            this.k.b(hashSet);
        }
        new StringBuilder("UpdateTaskList: cost = ").append((System.nanoTime() - nanoTime) / 1000000).append("ms, size =  ").append(list.size());
    }

    private void c(TaskInfo taskInfo) {
        try {
            if (taskInfo.mExtraInfo == null) {
                com.xunlei.downloadprovider.service.downloads.task.info.c a = this.l.a(taskInfo.mTaskId);
                if (a != null) {
                    taskInfo.mSeen = a.i;
                    taskInfo.mTaskReportType = a.g;
                    taskInfo.mCreateOrigin = a.h;
                    taskInfo.mSniffKeyword = a.l;
                    taskInfo.mWebsiteName = a.m;
                    taskInfo.mIconUrl = a.n;
                    if (TextUtils.isEmpty(taskInfo.mDisplayName)) {
                        taskInfo.mDisplayName = a.o;
                    }
                    if (TextUtils.isEmpty(taskInfo.mRefUrl)) {
                        taskInfo.mRefUrl = a.c;
                    }
                    taskInfo.mExtraInfo = a;
                    taskInfo.syncExtraInfo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final void d() {
        e eVar = new e(this, new ArrayList(this.a.values()));
        if (this.f.isAlive()) {
            this.f.execute(eVar);
        } else {
            new Thread(eVar).start();
        }
    }

    public final void a(TaskInfo taskInfo, int i, int i2) {
        synchronized (this.b) {
            if (i != 1 && i != 2) {
                this.b.remove(taskInfo);
            } else if (!this.b.contains(taskInfo)) {
                this.b.add(taskInfo);
            }
        }
        taskInfo.mTaskStatus = i;
        String str;
        switch (i) {
            case XZBDevice.Wait:
                g gVar = this.j;
                if (com.xunlei.downloadprovider.a.a.a(gVar.g)) {
                    if (com.xunlei.downloadprovider.util.g.a(taskInfo.mFileName) && taskInfo.mFileSize != 0 && com.xunlei.downloadprovider.businessutil.b.a().e()) {
                        d.a().c(taskInfo.mTaskId);
                        com.xunlei.downloadprovider.h.b.a(com.xunlei.downloadprovider.download.util.a.b((TaskRunningInfo) taskInfo), null, false);
                    } else if (com.xunlei.downloadprovider.util.g.b(taskInfo.mFilePath)) {
                        d.a().c(taskInfo.mTaskId);
                        File file = new File(com.xunlei.downloadprovider.download.util.a.b((TaskRunningInfo) taskInfo));
                        String toString = Uri.fromFile(file).toString();
                        if (file.exists()) {
                            DownloadBtFileExplorerActivity.startSelf(gVar.g, toString, -1, XZBDevice.Stop, "manual/manual_downloadedlist(bt)");
                        }
                    }
                }
                try {
                    Context context = gVar.g;
                    if (!(taskInfo == null || context == null)) {
                        Intent intent = new Intent("com.xunlei.downloadprovider.ACTION_DOWNLOAD_STATUS");
                        intent.putExtra(Impl.COLUMN_STATUS, "STATUS_FINISHED");
                        intent.putExtra(SocialConstants.PARAM_URL, taskInfo.getTaskDownloadUrl());
                        intent.putExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME, taskInfo.mTitle);
                        intent.putExtra("path", taskInfo.mLocalFileName);
                        intent.putExtra("return", 0);
                        context.sendBroadcast(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                str = taskInfo.mFilePath;
                if (str != null) {
                    List arrayList = new ArrayList(1);
                    arrayList.add(str);
                    com.xunlei.downloadprovider.filemanager.model.b.a(arrayList);
                }
                com.xunlei.downloadprovider.service.downloads.a.a.a(taskInfo.mCreateOrigin);
                break;
            case R.styleable.Toolbar_titleMarginBottom:
                int i3 = taskInfo.mOriginalStatusCode;
                str = taskInfo.mErrorMsg;
                new StringBuilder("report_dl_fail: ").append(i3).append(" errorMsg: ").append(str);
                ThunderReporter.g a = ThunderReporter.g.a("android_download", "dl_fail", "dl_fail");
                if (i3 == 0) {
                    String str2 = "fail_type";
                    if (str == null) {
                        str = String.valueOf(i3);
                    }
                    a.a(str2, str);
                } else {
                    a.a("fail_type", (long) i3);
                }
                ThunderReporter.a(a);
                ThunderReporter.a(a, true);
                break;
        }
        if (8 == i) {
            b(taskInfo);
        } else if (16 == i || 4 == i) {
            a(taskInfo);
        }
        new StringBuilder("taskId = ").append(taskInfo.mTaskId).append(",taskState = ").append(i);
        this.j.a(i, taskInfo, i2);
        taskInfo.mRunningInfo.b = i;
    }

    private static void a(TaskRunningInfo taskRunningInfo, Cursor cursor, com.xunlei.downloadprovider.service.downloads.kernel.a aVar) {
        boolean z;
        boolean z2 = false;
        taskRunningInfo.mTaskId = cursor.getLong(aVar.a);
        taskRunningInfo.mTitle = cursor.getString(aVar.d);
        taskRunningInfo.mFileName = taskRunningInfo.mTitle;
        taskRunningInfo.mLocalFileName = cursor.getString(aVar.i);
        taskRunningInfo.mFilePath = taskRunningInfo.mLocalFileName;
        taskRunningInfo.mFileSize = cursor.getLong(aVar.f);
        taskRunningInfo.mCID = cursor.getString(aVar.b);
        taskRunningInfo.mGCID = cursor.getString(aVar.c);
        taskRunningInfo.mUrl = cursor.getString(aVar.e);
        taskRunningInfo.mCreateTime = cursor.getLong(aVar.j);
        taskRunningInfo.mLastModifiedTime = cursor.getLong(aVar.k);
        taskRunningInfo.mDownloadDurationTime = cursor.getLong(aVar.l);
        taskRunningInfo.mTaskType = TaskType.values()[cursor.getInt(aVar.h)];
        switch (AnonymousClass_1.a[taskRunningInfo.mTaskType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                taskRunningInfo.mTaskOldType = 4;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                taskRunningInfo.mTaskOldType = 1;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                taskRunningInfo.mTaskOldType = 3;
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                taskRunningInfo.mTaskOldType = 2;
                break;
            default:
                taskRunningInfo.mTaskOldType = 0;
                break;
        }
        if (taskRunningInfo.mTaskType == TaskType.BT) {
            taskRunningInfo.mInfoHash = cursor.getString(aVar.K);
        }
        int i = cursor.getInt(aVar.g);
        taskRunningInfo.mOriginalStatusCode = i;
        taskRunningInfo.mTaskStatus = DownloadManager.translateStatus(i);
        taskRunningInfo.mFailureReason = DownloadManager.getReason(i);
        taskRunningInfo.mErrorMsg = cursor.getString(aVar.m);
        taskRunningInfo.mResLinkTotal = (long) cursor.getInt(aVar.I);
        taskRunningInfo.mResLinkUsed = (long) cursor.getInt(aVar.J);
        taskRunningInfo.mDownloadedSize = cursor.getLong(aVar.o);
        taskRunningInfo.mDownloadSpeed = cursor.getLong(aVar.p);
        taskRunningInfo.mOriginSpeed = cursor.getLong(aVar.r);
        taskRunningInfo.mOriginReceivedSize = cursor.getLong(aVar.q);
        taskRunningInfo.mIsXunleiSpdy = cursor.getInt(aVar.s) == 1;
        taskRunningInfo.mP2pSpeed = cursor.getLong(aVar.v);
        taskRunningInfo.mP2pReceivedSize = cursor.getLong(aVar.w);
        taskRunningInfo.mP2sSpeed = cursor.getLong(aVar.t);
        taskRunningInfo.mP2sReceivedSize = cursor.getLong(aVar.u);
        if (cursor.getInt(aVar.x) == 1) {
            z = true;
        } else {
            z = false;
        }
        taskRunningInfo.mHasLixianSpeedup = z;
        taskRunningInfo.mLixianSpeed = cursor.getLong(aVar.z);
        taskRunningInfo.mLixianStatusCode = cursor.getInt(aVar.y);
        taskRunningInfo.mLixianStatus = DownloadManager.translateStatus(taskRunningInfo.mLixianStatusCode);
        taskRunningInfo.mLixianProgress = (long) cursor.getInt(aVar.B);
        taskRunningInfo.mLixianReceivedSize = cursor.getLong(aVar.A);
        if (cursor.getInt(aVar.C) == 1) {
            z2 = true;
        }
        taskRunningInfo.mHasVipChannelSpeedup = z2;
        taskRunningInfo.mVipChannelSpeed = cursor.getLong(aVar.E);
        taskRunningInfo.mVipChannelStatusCode = cursor.getInt(aVar.D);
        taskRunningInfo.mVipChannelStatus = DownloadManager.translateStatus(taskRunningInfo.mVipChannelStatusCode);
        taskRunningInfo.mVipChannelReceivedSize = cursor.getLong(aVar.F);
        taskRunningInfo.mAppName = cursor.getString(aVar.G);
        taskRunningInfo.mAppVersion = cursor.getString(aVar.H);
        taskRunningInfo.mAcceleratedChannelSpeed = taskRunningInfo.mP2pSpeed + taskRunningInfo.mP2sSpeed;
        taskRunningInfo.mVipAcceleratedChannelSpeed = 0;
        if (taskRunningInfo.mHasLixianSpeedup) {
            taskRunningInfo.mAcceleratedChannelSpeed += taskRunningInfo.mLixianSpeed;
            taskRunningInfo.mVipAcceleratedChannelSpeed += taskRunningInfo.mLixianSpeed;
        }
        if (taskRunningInfo.mHasVipChannelSpeedup) {
            taskRunningInfo.mAcceleratedChannelSpeed += taskRunningInfo.mVipChannelSpeed;
            taskRunningInfo.mVipAcceleratedChannelSpeed += taskRunningInfo.mVipChannelSpeed;
        }
        taskRunningInfo.mAcceleratedChannelDownloadedSize = taskRunningInfo.mDownloadedSize - taskRunningInfo.mOriginReceivedSize;
        taskRunningInfo.mVipAcceleratedChannelDownloadedSize = taskRunningInfo.mVipChannelReceivedSize + taskRunningInfo.mLixianReceivedSize;
        if (!taskRunningInfo.mHasSpeed && taskRunningInfo.mDownloadSpeed > 0) {
            taskRunningInfo.mHasSpeed = true;
        }
        if (!taskRunningInfo.mHasOriginSpeed && taskRunningInfo.mOriginSpeed > 0) {
            taskRunningInfo.mHasOriginSpeed = true;
        }
    }

    public final void e() {
        this.h = false;
        try {
            com.xunlei.downloadprovider.service.downloads.kernel.a aVar = new com.xunlei.downloadprovider.service.downloads.kernel.a();
            Query query = new Query();
            query.setFilterByStatus(R.styleable.AppCompatTheme_actionModeCloseDrawable);
            query.orderBy(DownloadManager.COLUMN_ID, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            String[] projection = query.getProjection();
            String selection = query.getSelection();
            String[] selectionArgs = query.getSelectionArgs();
            String sortOrder = query.getSortOrder();
            Cursor query2 = this.j.g.getContentResolver().query(this.j.i, projection, selection, selectionArgs, sortOrder);
            aVar.a(query2);
            if (query2 != null) {
                while (query2.moveToNext()) {
                    long j = query2.getLong(aVar.a);
                    TaskRunningInfo taskRunningInfo = (TaskInfo) this.a.get(Long.valueOf(j));
                    if (taskRunningInfo != null) {
                        a(taskRunningInfo, query2, aVar);
                    } else {
                        taskRunningInfo = new TaskInfo();
                        taskRunningInfo.mTaskId = j;
                        a(taskRunningInfo.mTaskId, (TaskInfo) taskRunningInfo);
                        a(taskRunningInfo, query2, aVar);
                        taskRunningInfo.mRunningInfo.a = false;
                        k();
                    }
                    c(r0);
                    a(r0);
                }
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        d();
        a(false);
        this.h = true;
    }

    public final long a(String str) {
        if (!TextUtils.isEmpty(str)) {
            String trim = str.trim();
            CharSequence c = c.c(trim);
            if (TextUtils.isEmpty(c)) {
                String str2 = trim;
            } else {
                CharSequence charSequence = c;
            }
            for (TaskInfo taskInfo : this.a.values()) {
                if (taskInfo != null) {
                    if (trim.equals(taskInfo.mUrl) || r1.equals(taskInfo.mUrlEigenvalue)) {
                    }
                    return taskInfo.mTaskId;
                }
            }
        }
        return -1;
    }

    public final long b(String str) {
        if (!TextUtils.isEmpty(str)) {
            String trim = str.trim();
            CharSequence c = c.c(trim);
            if (TextUtils.isEmpty(c)) {
                String str2 = trim;
            } else {
                CharSequence charSequence = c;
            }
            for (TaskInfo taskInfo : this.a.values()) {
                if (taskInfo.mTaskType == TaskType.BT && taskInfo != null) {
                    if (trim.equals(taskInfo.mUrl) || r1.equals(taskInfo.mUrlEigenvalue)) {
                    }
                    return taskInfo.mTaskId;
                }
            }
        }
        return -1;
    }

    public final com.xunlei.downloadprovider.service.downloads.task.info.b g() {
        return a(false);
    }

    public final com.xunlei.downloadprovider.service.downloads.task.info.b a(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.r;
        if (z || this.p != 0 || this.q == null || this.r == 0 || elapsedRealtime >= 10000) {
            com.xunlei.downloadprovider.service.downloads.task.info.b bVar = new com.xunlei.downloadprovider.service.downloads.task.info.b();
            bVar.a = this.a.size();
            if (this.a.size() > 0) {
                for (TaskInfo taskInfo : this.a.values()) {
                    if (taskInfo != null) {
                        if (taskInfo.mTaskStatus == 16) {
                            bVar.e++;
                        } else if (taskInfo.mTaskStatus == 4) {
                            bVar.d++;
                        } else if (taskInfo.mTaskStatus == 8) {
                            bVar.b++;
                        } else if (taskInfo.mTaskStatus == 2 || taskInfo.mTaskStatus == 1) {
                            bVar.c++;
                        }
                    }
                }
            }
            this.r = SystemClock.elapsedRealtime();
            this.q = bVar;
            this.p = 0;
        }
        return this.q;
    }

    public final int h() {
        return this.c.size();
    }

    public final List<TaskInfo> j() {
        return this.b;
    }

    public final void a(boolean z, Collection<Long> collection) {
        this.m.removeAll(collection);
        com.xunlei.downloadprovider.service.downloads.kernel.c.a().a(z, c.a((Collection) collection));
    }

    public final void a(Collection<Long> collection, boolean z) {
        if (!z) {
            this.m.addAll(collection);
        }
        com.xunlei.downloadprovider.service.downloads.kernel.c.a().a(c.a((Collection) collection));
    }

    public final int f() {
        return a(false).a();
    }

    public final int i() {
        return a(false).c;
    }

    static /* synthetic */ void a(a aVar, Cursor cursor) {
        try {
            Collection hashSet = new HashSet();
            List arrayList = new ArrayList();
            aVar.o.a(cursor);
            while (cursor.moveToNext()) {
                TaskRunningInfo taskRunningInfo;
                int i;
                long j = cursor.getLong(aVar.o.a);
                int translateStatus = DownloadManager.translateStatus(cursor.getInt(aVar.o.g));
                TaskRunningInfo taskRunningInfo2 = (TaskInfo) aVar.a.get(Long.valueOf(j));
                hashSet.add(Long.valueOf(j));
                if (taskRunningInfo2 == null) {
                    taskRunningInfo2 = new TaskInfo();
                    taskRunningInfo2.mTaskId = j;
                    taskRunningInfo2.mTaskStatus = translateStatus;
                    aVar.a(j, (TaskInfo) taskRunningInfo2);
                    taskRunningInfo2.mRunningInfo.a = true;
                    taskRunningInfo = taskRunningInfo2;
                    i = -1;
                } else {
                    taskRunningInfo = taskRunningInfo2;
                    i = taskRunningInfo2.mTaskStatus;
                }
                if ((!aVar.h || aVar.i <= 1) && r0 == -1) {
                    i = taskRunningInfo.mTaskStatus;
                }
                taskRunningInfo.mRunningInfo.b = i;
                a(taskRunningInfo, cursor, aVar.o);
                arrayList.add(taskRunningInfo);
            }
            if (!aVar.a.isEmpty()) {
                Collection hashSet2 = new HashSet(aVar.a.keySet());
                hashSet2.removeAll(hashSet);
                if (!hashSet2.isEmpty()) {
                    aVar.a(hashSet2);
                }
            }
            if (aVar.f.isAlive()) {
                aVar.f.execute(new d(aVar, arrayList));
            } else {
                aVar.a(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
