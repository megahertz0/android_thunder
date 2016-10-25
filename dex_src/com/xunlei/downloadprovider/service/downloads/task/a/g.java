package com.xunlei.downloadprovider.service.downloads.task.a;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Request;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.b.a.b;
import com.xunlei.downloadprovider.service.downloads.task.b.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// compiled from: DownloadCore.java
public abstract class g {
    private com.xunlei.downloadprovider.a.h.a a;
    public final a f;
    public Context g;
    protected DownloadManager h;
    protected Uri i;
    public Handler j;
    public Looper k;
    public a l;
    public Handler m;
    public com.xunlei.downloadprovider.a.h.a n;
    String o;
    ExecutorService p;

    // compiled from: DownloadCore.java
    public abstract class a extends Thread {
        public abstract void a(Message message);

        public a() {
            super("download_engine");
        }

        public void run() {
            Looper.prepare();
            g.this.k = Looper.myLooper();
            g.this.j = new i(this);
            Looper.loop();
        }

        public void start() {
            g.this.j = null;
            super.start();
        }
    }

    public abstract void a(int i, TaskInfo taskInfo, int i2);

    public abstract void a(List<TaskInfo> list);

    public g(DownloadService downloadService) {
        this.f = new a(this);
        this.p = Executors.newCachedThreadPool();
        this.g = downloadService;
        a(com.xunlei.downloadprovider.businessutil.a.a());
        b.a();
        com.xunlei.downloadprovider.model.a.a();
        this.h = DownloadManager.getInstanceFor(this.g);
        this.i = this.h.getDownloadUri();
        this.a = new h(this);
        this.m = new h.b(this.a);
    }

    public final void a(String str) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        this.o = str;
        c.a(str);
    }

    private long a(String str, String str2, String str3, String str4, String str5) {
        long enqueue;
        new StringBuilder("OnCreateDownloadTask: Task(").append(str5).append(") url = ").append(str).append(" fileName = ").append(str3).append(" referer = ").append(str4);
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            DownloadManager instanceFor = DownloadManager.getInstanceFor(this.g);
            Request request = new Request(Uri.parse(str));
            if (str5 == null) {
                str5 = com.umeng.a.d;
            }
            request.setDownloadTaskXLOrigin(str5);
            request.setAllowedOverRoaming(true);
            request.setAllowedNetworkTypes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            request.setAllowedAutoResume(false);
            request.setNotificationVisibility(1);
            if (!TextUtils.isEmpty(str4)) {
                request.addRequestHeader(Impl.COLUMN_REFERER, str4);
            }
            if (TextUtils.isEmpty(str3)) {
                request.setDestinationUri(str2, com.umeng.a.d);
            } else if (XLFileTypeUtil.a(str3) != EFileCategoryType.E_OTHER_CATEGORY) {
                request.setDestinationUri(str2, str3);
            } else {
                request.setDestinationUri(str2, com.umeng.a.d);
            }
            if (!TextUtils.isEmpty(str3)) {
                request.setTitle(str3);
            }
            request.setDownloadSpdy(true);
            request.setDownloadDelay(false);
            if (instanceFor != null) {
                enqueue = instanceFor.enqueue(request);
            } else {
                enqueue = -1;
            }
            if (enqueue > -1) {
                try {
                    this.f.e.a(enqueue, true);
                } catch (Exception e) {
                    Exception e2 = e;
                    e2.printStackTrace();
                    return enqueue;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            enqueue = -1;
            e2 = exception;
            e2.printStackTrace();
            return enqueue;
        }
        return enqueue;
    }

    private long a(Uri uri, long[] jArr, String str, String str2) {
        long enqueue;
        if (!(TextUtils.isEmpty(str2) || str2.contains("(bt)"))) {
            str2 = str2 + "(bt)";
        }
        new StringBuilder("OnCreateDownloadTask: BTTask(").append(str2).append(") url = ").append(uri).append(" infoHash = ").append(str);
        try {
            DownloadManager instanceFor = DownloadManager.getInstanceFor(this.g);
            Request request = new Request(uri);
            request.setDownloadTaskXLOrigin(str2);
            request.setAllowedOverRoaming(true);
            request.setAllowedNetworkTypes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            request.setAllowedAutoResume(false);
            request.setDestinationUri(com.xunlei.downloadprovider.businessutil.a.a(), null);
            request.setDownloadSpdy(true);
            request.setDownloadDelay(false);
            request.setBtSelectSet(jArr);
            request.setBtInfoHash(str);
            request.setNotificationVisibility(1);
            if (instanceFor != null) {
                enqueue = instanceFor.enqueue(request);
            } else {
                enqueue = -1;
            }
            if (enqueue > -1) {
                try {
                    this.f.e.a(enqueue, true);
                } catch (Exception e) {
                    Exception e2 = e;
                    e2.printStackTrace();
                    return enqueue;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            enqueue = -1;
            e2 = exception;
            e2.printStackTrace();
            return enqueue;
        }
        return enqueue;
    }

    public final int a(long j, long[] jArr) {
        new StringBuilder("OnCreateDownloadTask: BTSubTask[").append(j).append("]:").append(Arrays.toString(jArr));
        try {
            return DownloadManager.getInstanceFor(this.g).selectBtSubTask(j, jArr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static boolean a() {
        return !com.xunlei.downloadprovider.businessutil.b.a().b() ? false : e();
    }

    protected static boolean e() {
        LoginHelper.a();
        return LoginHelper.c() && (LoginHelper.a().f() || LoginHelper.a().y > 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.xunlei.downloadprovider.service.downloads.task.c r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.service.downloads.task.a.g.a(com.xunlei.downloadprovider.service.downloads.task.c):long");
        /*
        this = this;
        r0 = new java.lang.StringBuilder;
        r1 = "commitCoreTask  ";
        r0.<init>(r1);
        r0.append(r13);
        if (r13 != 0) goto L_0x0010;
    L_0x000d:
        r2 = -1;
    L_0x000f:
        return r2;
    L_0x0010:
        r7 = r13.a();
        r2 = r7.i;
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r2 = r12.o;
    L_0x001e:
        r0 = r7.a();
        r8 = r0.c;
        r9 = new com.xunlei.downloadprovider.service.downloads.TaskInfo;
        r9.<init>();
        r0 = r7.b;
        r9.mUrl = r0;
        r0 = r7.a;
        r9.mFileName = r0;
        r0 = r7.c;
        r9.mRefUrl = r0;
        r0 = r7.d;
        r9.mCookie = r0;
        r0 = r7.h;
        r9.mCreateOrigin = r0;
        r0 = r7.a();
        r0 = r0.b;
        r9.mTaskReportType = r0;
        r0 = r7.a();
        r0 = r0.a;
        r9.mIsManualStart = r0;
        r0 = r7.a();
        r0 = r0.d;
        r9.mDisplayName = r0;
        r0 = r7.e;
        r9.mCID = r0;
        r0 = r7.f;
        r9.mGCID = r0;
        r0 = r7.g;
        r9.mFileSize = r0;
        r0 = r9.mUrl;
        r0 = com.xunlei.downloadprovider.service.downloads.b.c.c(r0);
        r9.mUrlEigenvalue = r0;
        r0 = r7.a();
        r0 = r0.g;
        r9.mSniffKeyword = r0;
        r0 = r7.a();
        r0 = r0.h;
        r9.mWebsiteName = r0;
        r0 = r7.a();
        r0 = r0.e;
        r9.mIconUrl = r0;
        r0 = r7.a();
        r0 = r0.f;
        r9.mIsToastForTask = r0;
        r0 = com.xunlei.downloadprovider.businessutil.b.a();
        r0 = r0.b();
        if (r0 == 0) goto L_0x009f;
    L_0x0093:
        com.xunlei.downloadprovider.member.login.LoginHelper.a();
        r0 = com.xunlei.downloadprovider.member.login.LoginHelper.c();
        if (r0 == 0) goto L_0x009f;
    L_0x009c:
        r0 = 1;
        r9.mShouldAutoSpeedup = r0;
    L_0x009f:
        r6 = 0;
        r0 = r13.b();
        r1 = 2;
        if (r0 != r1) goto L_0x00f0;
    L_0x00a7:
        r0 = r9.mCID;
        r4 = r9.mFileSize;
        r1 = r9.mGCID;
        r3 = r9.mFileName;
        r1 = com.xunlei.downloadprovider.service.downloads.b.c.a(r0, r4, r1, r3);
        r3 = r9.mFileName;
        r4 = r9.mRefUrl;
        r5 = r7.h;
        r0 = r12;
        r0 = r0.a(r1, r2, r3, r4, r5);
        r2 = r0;
        r0 = r6;
    L_0x00c0:
        r9.mTaskId = r2;
        r1 = (int) r2;
        if (r0 == 0) goto L_0x0154;
    L_0x00c5:
        r1 = 102409; // 0x19009 float:1.43506E-40 double:5.0597E-319;
        r0 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x00ca:
        r4 = r13.c;
        if (r4 == 0) goto L_0x00d5;
    L_0x00ce:
        r4 = r4.obtainMessage(r0, r1, r8, r9);
        r4.sendToTarget();
    L_0x00d5:
        r4 = r13.d;
        if (r4 != 0) goto L_0x00e5;
    L_0x00d9:
        r4 = r12.p;
        if (r4 == 0) goto L_0x01d3;
    L_0x00dd:
        r5 = new com.xunlei.downloadprovider.service.downloads.task.j;
        r5.<init>(r13, r1, r9);
        r4.execute(r5);
    L_0x00e5:
        r4 = r12.m;
        r0 = r4.obtainMessage(r0, r1, r8, r9);
        r0.sendToTarget();
        goto L_0x000f;
    L_0x00f0:
        r0 = r9.mUrl;
        r0 = r0.trim();
        r9.mUrl = r0;
        r0 = r9.mUrl;
        r0 = r0.trim();
        r1 = " ";
        r3 = "%20";
        r0 = r0.replace(r1, r3);
        r1 = "thunder://";
        r1 = r0.startsWith(r1);
        if (r1 == 0) goto L_0x01d8;
    L_0x0111:
        r1 = "/";
        r1 = r0.endsWith(r1);
        if (r1 == 0) goto L_0x0127;
    L_0x011a:
        r1 = 0;
        r3 = r0.length();
        r3 = r3 + -1;
        r0 = r0.subSequence(r1, r3);
        r0 = (java.lang.String) r0;
    L_0x0127:
        r1 = com.xunlei.xllib.b.k.b(r0);
        r1 = com.xunlei.xllib.b.k.a(r1);
        r3 = android.text.TextUtils.isEmpty(r1);
        if (r3 != 0) goto L_0x01d8;
    L_0x0135:
        r0 = r12.f;
        r4 = r0.a(r1);
        r10 = -1;
        r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r0 != 0) goto L_0x0150;
    L_0x0141:
        r3 = r9.mFileName;
        r4 = r9.mRefUrl;
        r5 = r7.h;
        r0 = r12;
        r0 = r0.a(r1, r2, r3, r4, r5);
        r2 = r0;
        r0 = r6;
        goto L_0x00c0;
    L_0x0150:
        r0 = 1;
        r2 = r4;
        goto L_0x00c0;
    L_0x0154:
        r4 = -1;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x015e;
    L_0x015a:
        r0 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x00ca;
    L_0x015e:
        r0 = r12.f;
        r0.a(r2, r9);
        r0 = r12.f;
        r0.a(r9);
        r0 = 100;
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0190;
    L_0x0170:
        r4 = a();
        if (r4 == 0) goto L_0x0190;
    L_0x0176:
        r4 = r12.h;
        if (r4 == 0) goto L_0x0190;
    L_0x017a:
        r4 = r12.h;
        r5 = 1;
        r5 = new long[r5];
        r6 = 0;
        r5[r6] = r2;
        r4.openVIPSpeedUp(r5);
        r4 = r12.h;
        r5 = 1;
        r5 = new long[r5];
        r6 = 0;
        r5[r6] = r2;
        r4.openLXSpeedUp(r5);
    L_0x0190:
        r9.syncExtraInfo();	 Catch:{ Exception -> 0x01c7 }
        r4 = com.xunlei.downloadprovider.service.downloads.task.b.d.a();	 Catch:{ Exception -> 0x01c7 }
        r5 = r9.mExtraInfo;	 Catch:{ Exception -> 0x01c7 }
        r4.a(r5);	 Catch:{ Exception -> 0x01c7 }
    L_0x019c:
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x01bb;
    L_0x01a2:
        r4 = r9.mUrl;
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 == 0) goto L_0x01bb;
    L_0x01aa:
        r4 = new com.xunlei.downloadprovider.service.downloads.b.b;	 Catch:{ Exception -> 0x01cc }
        r5 = r9.mCID;	 Catch:{ Exception -> 0x01cc }
        r6 = r9.mFileSize;	 Catch:{ Exception -> 0x01cc }
        r10 = r9.mGCID;	 Catch:{ Exception -> 0x01cc }
        r4.<init>(r5, r6, r10);	 Catch:{ Exception -> 0x01cc }
        r4 = r4.toString();	 Catch:{ Exception -> 0x01cc }
        r9.mUrl = r4;	 Catch:{ Exception -> 0x01cc }
    L_0x01bb:
        r4 = r12.f;
        r4.a();
        r4 = r12.f;
        r4.g();
        goto L_0x00ca;
    L_0x01c7:
        r4 = move-exception;
        r4.printStackTrace();
        goto L_0x019c;
    L_0x01cc:
        r4 = move-exception;
        r4 = "";
        r9.mUrl = r4;
        goto L_0x01bb;
    L_0x01d3:
        r13.a(r9);
        goto L_0x00e5;
    L_0x01d8:
        r1 = r0;
        goto L_0x0135;
        */
    }

    public final long a(Uri uri, long[] jArr, String str, String str2, String str3) {
        int i;
        long a = a(uri, jArr, str, str3);
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.mTaskId = a;
        taskInfo.mInfoHash = str;
        taskInfo.mCreateOrigin = str3;
        taskInfo.mFileName = str2;
        taskInfo.mTitle = str2;
        int i2 = (int) a;
        if (this.f.a.containsKey(Long.valueOf(a))) {
            i2 = 102409;
            i = R.styleable.AppCompatTheme_buttonStyleSmall;
        } else if (a == -1) {
            i = R.styleable.AppCompatTheme_buttonStyleSmall;
        } else {
            this.f.a(a, taskInfo);
            this.f.a(taskInfo);
            i = R.styleable.AppCompatTheme_buttonStyle;
            try {
                taskInfo.syncExtraInfo();
                d.a().a(taskInfo.mExtraInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (a() && a != -1) {
            com.xunlei.downloadprovider.service.downloads.kernel.c.a().c(a);
            com.xunlei.downloadprovider.service.downloads.kernel.c.a().b(a);
        }
        this.m.obtainMessage(i, i2, 0, taskInfo).sendToTarget();
        return a;
    }

    public final void a(Collection<Long> collection, boolean z, Handler handler) {
        if (collection != null && !collection.isEmpty()) {
            if (collection.size() > 0) {
                this.f.m.removeAll(collection);
                com.xunlei.downloadprovider.service.downloads.kernel.c.a().c(z, com.xunlei.downloadprovider.service.downloads.b.c.a((Collection) collection));
            }
            if (a() && collection.size() > 0) {
                for (Long l : collection) {
                    long longValue = l.longValue();
                    com.xunlei.downloadprovider.service.downloads.kernel.c.a().c(longValue);
                    com.xunlei.downloadprovider.service.downloads.kernel.c.a().b(longValue);
                }
            }
            if (handler != null) {
                handler.obtainMessage(R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle).sendToTarget();
            }
        }
    }

    public final void b(Handler handler, boolean z) {
        com.xunlei.downloadprovider.service.downloads.task.a aVar;
        if (handler != null) {
            aVar = new com.xunlei.downloadprovider.service.downloads.task.a();
        } else {
            aVar = null;
        }
        if (!(this.f.a == null || this.f.a.isEmpty())) {
            Collection arrayList = new ArrayList();
            for (TaskInfo taskInfo : this.f.a.values()) {
                if (2 == taskInfo.mTaskStatus || 1 == taskInfo.mTaskStatus) {
                    arrayList.add(Long.valueOf(taskInfo.mTaskId));
                }
            }
            this.f.a(arrayList, z);
            if (aVar != null) {
                aVar.a = arrayList;
            }
        }
        if (handler != null) {
            handler.obtainMessage(R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle, aVar).sendToTarget();
        }
    }

    public final void a(Collection<Long> collection, Handler handler, boolean z) {
        if (collection != null && !collection.isEmpty()) {
            com.xunlei.downloadprovider.service.downloads.kernel.c.a().b(z, com.xunlei.downloadprovider.service.downloads.b.c.a((Collection) collection));
            Collection<TaskInfo> a = this.f.a((Collection) collection);
            if (!a.isEmpty()) {
                for (TaskInfo taskInfo : a) {
                    com.xunlei.downloadprovider.notification.a.a(this.g).a(taskInfo);
                    a((int) R.styleable.Toolbar_maxButtonHeight, taskInfo, (int) XZBDevice.Wait);
                }
            }
            if (handler != null) {
                handler.obtainMessage(R.styleable.AppCompatTheme_autoCompleteTextViewStyle).sendToTarget();
            }
        }
    }

    public final void a(String str, Handler handler, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            Collection hashSet = new HashSet();
            Object hashSet2 = new HashSet(this.f.a.values());
            if (!com.xunlei.xllib.b.d.a(hashSet2)) {
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    TaskInfo taskInfo = (TaskInfo) it.next();
                    if (str.equals(taskInfo.mLocalFileName)) {
                        new StringBuilder("remove Task :").append(taskInfo.mTaskId).append(" by FilePath = ").append(str);
                        hashSet.add(Long.valueOf(taskInfo.mTaskId));
                        break;
                    }
                }
                a(hashSet, handler, z);
            }
        }
    }
}
