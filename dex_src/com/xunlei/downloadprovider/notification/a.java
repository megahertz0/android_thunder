package com.xunlei.downloadprovider.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import com.umeng.message.proguard.k;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.frame.user.ag;
import com.xunlei.downloadprovider.frame.user.bo;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.service.DownloadEngine;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.service.downloads.task.info.b;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

@SuppressLint({"HandlerLeak"})
// compiled from: DownloadNotification.java
public final class a {
    private static List<Long> m;
    private static a o;
    final long a;
    boolean b;
    boolean c;
    public com.xunlei.downloadprovider.a.a.a d;
    public DownloadEngine e;
    public int f;
    public boolean g;
    public Handler h;
    PendingIntent i;
    protected boolean j;
    BroadcastReceiver k;
    private final LoginHelper l;
    private long n;
    private Context p;
    private List<TaskInfo> q;
    private List<TaskInfo> r;
    private List<TaskInfo> s;
    private DownloadService t;
    private boolean u;
    private boolean v;
    private b w;
    private int x;
    private int y;
    private int z;

    static /* synthetic */ void a(a aVar, TaskInfo taskInfo, int i) {
        aVar.g = false;
        switch (taskInfo.mTaskStatus) {
            case XZBDevice.Wait:
                if (i != 8 && i != 17 && i != -1) {
                    Object a;
                    String c;
                    Builder builder;
                    CharSequence toString;
                    CharSequence charSequence;
                    CharSequence toString2;
                    Notification build;
                    Intent intent;
                    PendingIntent broadcast;
                    String substring;
                    aVar.e();
                    if (taskInfo != null) {
                        if (m.indexOf(Long.valueOf(taskInfo.mTaskId)) == -1) {
                            m.add(Long.valueOf(taskInfo.mTaskId));
                        }
                        if (taskInfo.mFileName != null && taskInfo.mFileName.toLowerCase().endsWith(".apk")) {
                            com.xunlei.downloadprovider.a.c.a a2 = c.a(aVar.p, ai.a(taskInfo.mFilePath) + taskInfo.mFileName);
                            a = a2 != null ? a2.a() : null;
                            if (a2 != null) {
                                c = a2.c();
                                long j = taskInfo.mTaskId;
                                Editor edit = aVar.p.getSharedPreferences("clearApkNotification", 0).edit();
                                edit.putLong(c, j);
                                edit.commit();
                            }
                            if (m.size() > 1) {
                                aVar.a(true);
                            } else if (taskInfo != null) {
                                c = a == null ? a + ".apk" : taskInfo.mFileName;
                                com.xunlei.downloadprovider.d.a.a(taskInfo.mFileSize, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                                builder = new Builder(aVar.p);
                                toString = new StringBuilder("\u4e0b\u8f7d\u5b8c\u6210:").append(a(c, (int) R.styleable.Toolbar_navigationIcon)).toString();
                                charSequence = "\u7acb\u5373\u67e5\u770b";
                                toString2 = new StringBuilder("\u4e0b\u8f7d\u5b8c\u6210:").append(a(c, (int) R.styleable.Toolbar_navigationIcon)).toString();
                                build = builder.build();
                                builder.setSmallIcon(2130837711);
                                builder.setTicker(toString2);
                                build.flags |= 16;
                                builder.setAutoCancel(true);
                                builder.setNumber(0);
                                builder.setVibrate(new long[]{500, 100, 500});
                                if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
                                    builder.setSound(null);
                                } else {
                                    builder.setSound(Uri.parse(new StringBuilder("android.resource://").append(aVar.p.getPackageName()).append("/2131165186").toString()));
                                }
                                if (a != null) {
                                    intent = new Intent("com.xunlei.action.COMMON_FILE_CLICK");
                                    intent.putExtra(k.l, taskInfo.mTaskId);
                                    broadcast = PendingIntent.getBroadcast(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
                                    builder.setContentTitle(toString);
                                    builder.setContentText(charSequence);
                                    builder.setContentIntent(broadcast);
                                    builder.setDeleteIntent(PendingIntent.getBroadcast(aVar.p, 8000, new Intent("com.xunlei.action.COMMON_DELETE_NOTI_CLICK"), 134217728));
                                } else {
                                    try {
                                        substring = taskInfo.mFileName.substring(0, taskInfo.mFileName.indexOf(".apk"));
                                        bo.a();
                                        for (ag agVar : bo.b()) {
                                            if (agVar.e.equals(substring)) {
                                                bo.a();
                                                bo.b(substring, taskInfo.mFilePath + taskInfo.mFileName);
                                                intent = new Intent("com.xunlei.action.APK_CLICK");
                                                intent.putExtra("filePath", taskInfo.mFilePath + taskInfo.mFileName);
                                                intent.setFlags(268435456);
                                                aVar.i = PendingIntent.getBroadcast(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
                                            }
                                        }
                                        intent = new Intent("com.xunlei.action.APK_CLICK");
                                        intent.putExtra("filePath", taskInfo.mFilePath + taskInfo.mFileName);
                                        intent.setFlags(268435456);
                                        aVar.i = PendingIntent.getBroadcast(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
                                    } catch (Exception e) {
                                    }
                                }
                                aVar.d.a(((int) taskInfo.mTaskId) + 27862, builder.build());
                            }
                        }
                    }
                    a = null;
                    if (m.size() > 1) {
                        aVar.a(true);
                    } else if (taskInfo != null) {
                        if (a == null) {
                        }
                        com.xunlei.downloadprovider.d.a.a(taskInfo.mFileSize, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        builder = new Builder(aVar.p);
                        toString = new StringBuilder("\u4e0b\u8f7d\u5b8c\u6210:").append(a(c, (int) R.styleable.Toolbar_navigationIcon)).toString();
                        charSequence = "\u7acb\u5373\u67e5\u770b";
                        toString2 = new StringBuilder("\u4e0b\u8f7d\u5b8c\u6210:").append(a(c, (int) R.styleable.Toolbar_navigationIcon)).toString();
                        build = builder.build();
                        builder.setSmallIcon(2130837711);
                        builder.setTicker(toString2);
                        build.flags |= 16;
                        builder.setAutoCancel(true);
                        builder.setNumber(0);
                        builder.setVibrate(new long[]{500, 100, 500});
                        if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
                            builder.setSound(null);
                        } else {
                            builder.setSound(Uri.parse(new StringBuilder("android.resource://").append(aVar.p.getPackageName()).append("/2131165186").toString()));
                        }
                        if (a != null) {
                            substring = taskInfo.mFileName.substring(0, taskInfo.mFileName.indexOf(".apk"));
                            bo.a();
                            while (r2.hasNext()) {
                                if (agVar.e.equals(substring)) {
                                    bo.a();
                                    bo.b(substring, taskInfo.mFilePath + taskInfo.mFileName);
                                    intent = new Intent("com.xunlei.action.APK_CLICK");
                                    intent.putExtra("filePath", taskInfo.mFilePath + taskInfo.mFileName);
                                    intent.setFlags(268435456);
                                    aVar.i = PendingIntent.getBroadcast(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
                                }
                            }
                            intent = new Intent("com.xunlei.action.APK_CLICK");
                            intent.putExtra("filePath", taskInfo.mFilePath + taskInfo.mFileName);
                            intent.setFlags(268435456);
                            aVar.i = PendingIntent.getBroadcast(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
                        } else {
                            intent = new Intent("com.xunlei.action.COMMON_FILE_CLICK");
                            intent.putExtra(k.l, taskInfo.mTaskId);
                            broadcast = PendingIntent.getBroadcast(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
                            builder.setContentTitle(toString);
                            builder.setContentText(charSequence);
                            builder.setContentIntent(broadcast);
                            builder.setDeleteIntent(PendingIntent.getBroadcast(aVar.p, 8000, new Intent("com.xunlei.action.COMMON_DELETE_NOTI_CLICK"), 134217728));
                        }
                        aVar.d.a(((int) taskInfo.mTaskId) + 27862, builder.build());
                    }
                }
            default:
                break;
        }
    }

    static /* synthetic */ int g(a aVar) {
        int i = aVar.f;
        aVar.f = i - 1;
        return i;
    }

    static {
        m = new ArrayList();
    }

    private a(Context context) {
        this.a = 1234567890;
        this.l = LoginHelper.a();
        this.b = false;
        this.c = false;
        this.f = -1;
        this.q = new ArrayList();
        this.r = new ArrayList();
        this.s = new ArrayList();
        this.t = DownloadService.a();
        this.u = false;
        this.g = false;
        this.h = new b(this);
        this.k = new c(this);
        d.a();
        this.w = d.m();
        this.x = 0;
        this.y = 0;
        this.z = 0;
        try {
            this.p = context;
            this.d = com.xunlei.downloadprovider.a.a.b.a(context);
        } catch (Exception e) {
        }
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            new StringBuilder("DownloadNotification---getInstance---").append(Thread.currentThread().getId());
            if (o == null) {
                o = new a(context);
            }
            aVar = o;
        }
        return aVar;
    }

    public final void a(List<TaskInfo> list) {
        long j = g.a().d;
        if (BrothersApplication.a().g()) {
            if (!(BrothersApplication.a().n instanceof DownloadCenterActivity)) {
                for (TaskInfo taskInfo : list) {
                    boolean z = taskInfo.mIsEnteredHighSpeedTrial;
                    boolean d = g.a().d(taskInfo.getTaskId());
                    if (z && j == taskInfo.getTaskId() && n.a(taskInfo) && d) {
                        XLToast.d(this.p, XLToastType.XLTOAST_TYPE_NORMAL, "\u60a8\u7684\u514d\u8d39\u4f1a\u5458\u8bd5\u7528\u5df2\u7ed3\u675f,\u8bf7\u5230\u4e0b\u8f7d\u4e2d\u5fc3\u67e5\u770b");
                        g.a().e(taskInfo.getTaskId());
                        com.xunlei.downloadprovider.download.report.a.c();
                        return;
                    }
                }
            }
        } else if (!com.xunlei.xllib.b.d.a(list)) {
            for (TaskInfo taskInfo2 : list) {
                boolean z2 = taskInfo2.mIsEnteredHighSpeedTrial;
                if (g.a().d(taskInfo2.getTaskId()) && z2 && n.a(taskInfo2)) {
                    if (taskInfo2 != null) {
                        Builder builder = new Builder(this.p);
                        CharSequence charSequence = "\u8fc5\u96f7\u63d0\u793a";
                        CharSequence charSequence2 = "\u60a8\u7684\u514d\u8d39\u4f1a\u5458\u8bd5\u7528\u5df2\u7ed3\u675f,\u70b9\u51fb\u67e5\u770b";
                        CharSequence charSequence3 = com.umeng.a.d;
                        builder.setSmallIcon(2130837711);
                        builder.setAutoCancel(true);
                        builder.setNumber(0);
                        if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
                            builder.setDefaults(1);
                        } else {
                            builder.setDefaults(0);
                        }
                        Intent intent = new Intent(this.p, DownloadCenterActivity.class);
                        intent.setFlags(67108864);
                        intent.putExtra(k.l, taskInfo2.getTaskId());
                        intent.putExtra("from", "task_free_trial_notify");
                        intent.putExtra("extra_key_should_open_detailpage", false);
                        PendingIntent activity = PendingIntent.getActivity(this.p, (int) taskInfo2.mTaskId, intent, 134217728);
                        builder.setContentTitle(charSequence);
                        builder.setContentText(charSequence2);
                        builder.setTicker(charSequence3);
                        builder.setContentIntent(activity);
                        this.d.a(27866, builder.build());
                        com.xunlei.downloadprovider.download.report.a.d();
                    }
                    g.a().e(taskInfo2.getTaskId());
                    return;
                }
            }
        }
    }

    public final void a(TaskInfo taskInfo, boolean z) {
        if (taskInfo.getTaskId() != g.a().d) {
            return;
        }
        if (z) {
            this.d.a(27866);
        } else if (taskInfo.mTaskStatus == 8) {
            this.d.a(27866);
        }
    }

    private void a(boolean z) {
        e();
        if (z && m.size() > 1) {
            b(z);
        } else if (!z && m.size() > 0) {
            b(z);
        }
    }

    private void b(boolean z) {
        List<Long> list = m;
        if (!(list == null || list.isEmpty())) {
            for (Long l : list) {
                this.d.a((int) (l.longValue() + 27862));
            }
        }
        String string = this.p.getString(2131231781, new Object[]{Integer.valueOf(m.size())});
        Builder builder = new Builder(this.p);
        CharSequence a = a(string, (int) R.styleable.Toolbar_navigationIcon);
        CharSequence charSequence = "\u7acb\u5373\u67e5\u770b";
        builder.setSmallIcon(2130837711);
        builder.setTicker(string);
        builder.setAutoCancel(true);
        builder.setNumber(0);
        builder.setVibrate(new long[]{500, 100, 500});
        if (com.xunlei.downloadprovider.businessutil.b.a().h() && z) {
            builder.setSound(Uri.parse(new StringBuilder("android.resource://").append(this.p.getPackageName()).append("/2131165186").toString()));
        } else {
            builder.setSound(null);
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(this.p, 8000, new Intent("com.xunlei.action.COMMON_MERGE_FILES_CLICK"), 134217728);
        builder.setContentTitle(a);
        builder.setContentText(charSequence);
        builder.setContentIntent(broadcast);
        builder.setDeleteIntent(PendingIntent.getBroadcast(this.p, 8000, new Intent("com.xunlei.action.COMMON_DELETE_NOTI_CLICK"), 134217728));
        this.d.a(35862, builder.build());
    }

    public final void a(int i) {
        this.d.a(i + 27862);
        int indexOf = m.indexOf(Integer.valueOf(i));
        if (indexOf != -1) {
            m.remove(indexOf);
            if (m.size() == 0) {
                d();
            } else {
                a(false);
            }
        }
    }

    public final void a(TaskInfo taskInfo) {
        a(taskInfo, true);
        if (taskInfo != null && taskInfo.mTaskStatus == 8) {
            new StringBuilder("cancelDownloadSucNoti taskId=").append(taskInfo.mTaskId);
            this.d.a(((int) taskInfo.mTaskId) + 27862);
            int indexOf = m.indexOf(Long.valueOf(taskInfo.mTaskId));
            if (indexOf != -1) {
                m.remove(indexOf);
                new StringBuilder("after remove size=").append(m.size());
                if (m.size() == 0) {
                    d();
                } else {
                    a(false);
                }
            }
        }
    }

    public static void a() {
        m.clear();
    }

    public static int b() {
        return m.size();
    }

    private void d() {
        this.d.a(35862);
    }

    private void e() {
        this.d.a(27856);
        this.d.a(27860);
    }

    private void f() {
        d.a();
        if (d.j() <= 0) {
            this.r.clear();
        }
        e();
        this.d.a(27857);
    }

    private void g() {
        this.y = 0;
        this.u = false;
        d.a();
        if (d.j() <= 0) {
            this.s.clear();
            e();
            this.d.a(27868);
            return;
        }
        e();
        this.d.a(27868);
    }

    private static String a(String str, int i) {
        if (str == null) {
            return com.umeng.a.d;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            if (str.charAt(i2) < '\u0080') {
                i3++;
            } else {
                i3 += 2;
            }
            if (i3 > i) {
                Object obj = 1;
                break;
            }
            i2++;
        }
        i3 = 0;
        return i3 != 0 ? str.substring(0, i2) + "..." : str.substring(0, i2);
    }

    private static Intent c(Context context) {
        PayEntryParam payEntryParam = new PayEntryParam(PayFrom.DOWNLOAD_NOTIFICATION);
        payEntryParam.e = new com.xunlei.downloadprovider.member.payment.external.b(DownloadCenterActivity.class);
        Intent b = PaymentEntryActivity.b(context, payEntryParam);
        b.putExtra("from", "download_noti");
        return b;
    }

    private static boolean h() {
        LoginHelper.a();
        return LoginHelper.c() && LoginHelper.a().y > 0;
    }

    static /* synthetic */ void a(a aVar, List list) {
        aVar.n = System.currentTimeMillis();
        if (list == null || list.isEmpty()) {
            aVar.x = 0;
            aVar.f();
            aVar.g();
            return;
        }
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        long j = 0;
        long j2 = 0;
        int i = 0;
        boolean z = false;
        for (TaskInfo taskInfo : list) {
            TaskInfo taskInfo2;
            if (taskInfo2.mTaskStatus == 2 || taskInfo2.mTaskStatus == 1 || taskInfo2.mTaskStatus == 0) {
                j += taskInfo2.mFileSize;
                j2 += taskInfo2.mDownloadedSize;
                i = (int) (((long) i) + taskInfo2.mDownloadSpeed);
                arrayList.add(taskInfo2);
                if (!aVar.r.contains(taskInfo2)) {
                    aVar.r.add(taskInfo2);
                    com.xunlei.downloadprovider.download.report.a.a("download_in");
                }
                if (taskInfo2.mHasVipChannelSpeedup || taskInfo2.mHasLixianSpeedup) {
                    Object obj;
                    LoginHelper.a();
                    if (LoginHelper.c() && LoginHelper.a().f()) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null || h()) {
                        z = true;
                    }
                }
                if (com.xunlei.downloadprovider.download.tasklist.list.c.g.c.a(taskInfo2)) {
                    if ((taskInfo2.mDownloadedSize * 100) / taskInfo2.mFileSize >= 1) {
                        arrayList2.add(taskInfo2);
                    }
                    if (!aVar.s.contains(taskInfo2)) {
                        aVar.s.add(taskInfo2);
                        com.xunlei.downloadprovider.download.report.a.a("download_in_bxbb");
                    }
                }
            }
            i = i;
            z = z;
        }
        int size = arrayList.size();
        int size2 = arrayList2.size();
        if (size > 0) {
            int i2;
            Notification build;
            Intent intent;
            if (size >= 0 && size != aVar.x) {
                aVar.x = size;
            }
            Object obj2 = null;
            if (size2 >= 0 && size2 != aVar.y) {
                obj2 = 1;
                aVar.y = size2;
            }
            Object obj3 = obj2;
            if (j > 52428800) {
                i2 = 1;
            } else {
                i2 = 3;
            }
            int i3 = 0;
            if (j > 0) {
                i3 = j2 == j ? R.styleable.AppCompatTheme_buttonStyle : (int) ((100 * j2) / j);
            }
            if (Math.abs(aVar.z - i3) >= i2) {
                aVar.z = i3;
            }
            taskInfo2 = (TaskInfo) arrayList.get(0);
            if (VERSION.SDK_INT >= 16) {
                build = new Notification.Builder(aVar.p).setPriority(XZBDevice.DOWNLOAD_LIST_RECYCLE).build();
            } else {
                build = new Notification();
            }
            build.tickerText = com.umeng.a.d;
            build.when = 1234567890;
            switch (size) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    build.icon = 2130837712;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    build.icon = 2130837714;
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    build.icon = 2130837713;
                    break;
                default:
                    build.icon = 2130837711;
                    break;
            }
            build.flags |= 32;
            build.flags |= 2;
            build.number = 0;
            build.defaults = 0;
            Intent intent2 = new Intent();
            intent2.setClass(aVar.p, DownloadCenterActivity.class);
            intent2.setFlags(67108864);
            intent2.putExtra(k.l, taskInfo2.mTaskId);
            intent2.putExtra("from", "from_running_noti");
            PendingIntent activity = PendingIntent.getActivity(aVar.p, 27857, intent2, 268435456);
            build.contentIntent = activity;
            RemoteViews remoteViews = new RemoteViews(aVar.p.getPackageName(), 2130968887);
            if (j > 0) {
                remoteViews.setProgressBar(2131756689, R.styleable.AppCompatTheme_buttonStyle, (int) ((j2 * 100) / j), false);
            }
            CharSequence string = aVar.p.getString(2131231782, new Object[]{Integer.valueOf(size)});
            d.a();
            j2 = d.f();
            CharSequence charSequence = com.xunlei.downloadprovider.d.a.a((long) i) + "/s";
            remoteViews.setTextViewText(2131756688, string);
            remoteViews.setTextViewText(2131756679, charSequence);
            if (j2 > ((long) i) && i > 1) {
                j2 = (long) (i - 1);
            }
            Boolean valueOf = Boolean.valueOf(z);
            Intent intent3 = new Intent();
            LoginHelper.a();
            if (!LoginHelper.c()) {
                remoteViews.setViewVisibility(2131756678, XZBDevice.Wait);
                remoteViews.setViewVisibility(2131756690, 0);
                remoteViews.setViewVisibility(2131756691, XZBDevice.Wait);
                intent3.setFlags(67108864);
                aVar.i = PendingIntent.getActivity(aVar.p, 27863, c(aVar.p), 268435456);
            } else if (LoginHelper.a().f() || h()) {
                if (valueOf.booleanValue()) {
                    remoteViews.setViewVisibility(2131756678, 0);
                    remoteViews.setViewVisibility(2131756690, XZBDevice.Wait);
                    remoteViews.setViewVisibility(2131756691, 0);
                } else {
                    remoteViews.setViewVisibility(2131756678, XZBDevice.Wait);
                    remoteViews.setViewVisibility(2131756690, 0);
                    remoteViews.setViewVisibility(2131756691, XZBDevice.Wait);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("accelerate_this_time");
                    aVar.p.registerReceiver(aVar.k, intentFilter);
                    intent = new Intent("accelerate_this_time");
                    intent.putExtra("bar_or_button", "button");
                    aVar.i = PendingIntent.getBroadcast(aVar.p, 27865, intent, 268435456);
                    if (aVar.j) {
                        remoteViews.setViewVisibility(2131756690, XZBDevice.Wait);
                        remoteViews.setViewVisibility(2131756691, 0);
                    }
                }
                StringBuilder stringBuilder = new StringBuilder("DownloadNotification---DownloadService.getInstance().getDownloadingTotalSpeed()---DownloadService.getInstance().getDownloadingOriginalSpeed()---");
                d.a();
                stringBuilder = stringBuilder.append(d.d()).append("---");
                d.a();
                stringBuilder.append(d.e()).append("---").append(Thread.currentThread().getId());
                d.a();
                if (d.f() != 0) {
                    new StringBuilder("DownloadNotification---accelerateSpeedOnceIsNotZero = true---").append(Thread.currentThread().getId());
                    aVar.v = true;
                }
                if (aVar.v) {
                    remoteViews.setViewVisibility(2131756678, 0);
                } else {
                    remoteViews.setViewVisibility(2131756678, XZBDevice.Wait);
                }
            } else {
                remoteViews.setViewVisibility(2131756678, XZBDevice.Wait);
                remoteViews.setViewVisibility(2131756690, 0);
                remoteViews.setViewVisibility(2131756691, XZBDevice.Wait);
                intent3.setFlags(67108864);
                aVar.i = PendingIntent.getActivity(aVar.p, 27863, c(aVar.p), 268435456);
            }
            remoteViews.setOnClickPendingIntent(2131756690, aVar.i);
            remoteViews.setTextViewText(2131756678, new StringBuilder(" (+").append(com.xunlei.downloadprovider.d.a.a(j2)).append("/s)").toString());
            remoteViews.setOnClickPendingIntent(2131756674, activity);
            build.contentView = remoteViews;
            aVar.d.a(27857, build);
            if (size2 <= 0) {
                aVar.g();
            } else if (obj3 != null) {
                Notification build2;
                if (VERSION.SDK_INT >= 16) {
                    build2 = new Notification.Builder(aVar.p).setPriority(XZBDevice.DOWNLOAD_LIST_RECYCLE).build();
                } else {
                    build2 = new Notification();
                }
                build2.tickerText = com.umeng.a.d;
                build2.when = 1234567890;
                build2.icon = 2130837711;
                build2.flags |= 16;
                build2.number = 0;
                build2.defaults = 0;
                if (arrayList2.size() > 0) {
                    RemoteViews remoteViews2 = new RemoteViews(aVar.p.getPackageName(), 2130968884);
                    remoteViews2.setTextViewText(2131756676, com.xunlei.downloadprovider.download.util.a.a((TaskInfo) arrayList2.get(0), BrothersApplication.a().getApplicationContext()));
                    remoteViews2.setTextViewText(2131756677, arrayList2.size() + "\u4e2a\u89c6\u9891\u53ef\u8fb9\u4e0b\u8fb9\u64ad");
                    remoteViews2.setImageViewResource(2131756681, XLFileTypeUtil.c(((TaskInfo) arrayList2.get(0)).mFileName.trim()));
                    IntentFilter intentFilter2 = new IntentFilter();
                    intentFilter2.addAction("bxbb_click");
                    aVar.p.registerReceiver(aVar.k, intentFilter2);
                    intent = new Intent("bxbb_click");
                    intent.putExtra("bxbb_task", (Serializable) arrayList2.get(0));
                    activity = PendingIntent.getBroadcast(aVar.p, 27867, intent, 268435456);
                    remoteViews2.setOnClickPendingIntent(2131756680, activity);
                    remoteViews2.setOnClickPendingIntent(2131756674, activity);
                    build2.contentView = remoteViews2;
                    aVar.d.a(27868, build2);
                }
            }
            aVar.b = true;
            aVar.c = z;
            return;
        }
        aVar.x = 0;
        aVar.f();
        aVar.g();
    }

    static /* synthetic */ void b(a aVar, List list) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            String str = ((TaskInfo) list.get(0)).mFileName;
            Builder builder = new Builder(aVar.p);
            CharSequence charSequence = a(str, (int) R.styleable.AppCompatTheme_actionModeSplitBackground) + "\u7b49" + size + "\u4e2a\u4efb\u52a1";
            CharSequence charSequence2 = size + "\u4e2a\u4e0b\u8f7d\u4efb\u52a1\u521b\u5efa\u6210\u529f";
            CharSequence charSequence3 = a(str, (int) R.styleable.Toolbar_navigationIcon) + "\u7b49" + size + "\u4e2a\u4efb\u52a1\u6210\u529f\u521b\u5efa\u4e0b\u8f7d";
            builder.setSmallIcon(2130837711);
            builder.setTicker(charSequence3);
            builder.setAutoCancel(true);
            builder.setNumber(0);
            if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
                builder.setDefaults(1);
            } else {
                builder.setDefaults(0);
            }
            Intent intent = new Intent();
            intent.setClass(aVar.p, DownloadCenterActivity.class);
            PendingIntent activity = PendingIntent.getActivity(aVar.p, 27860, intent, 134217728);
            builder.setContentTitle(charSequence);
            builder.setContentText(charSequence2);
            builder.setContentIntent(activity);
            aVar.d.a(27860, builder.build());
            aVar.f = -1;
            aVar.q.clear();
        }
    }

    static /* synthetic */ void a(a aVar, TaskInfo taskInfo) {
        if (taskInfo != null) {
            Builder builder = new Builder(aVar.p);
            CharSequence a = a(taskInfo.mFileName, (int) R.styleable.AppCompatTheme_actionModeSplitBackground);
            CharSequence charSequence = "\u6210\u529f\u521b\u5efa\u4e0b\u8f7d";
            CharSequence charSequence2 = a(taskInfo.mFileName, (int) R.styleable.Toolbar_navigationIcon) + "\u6210\u529f\u521b\u5efa\u4e0b\u8f7d";
            builder.setSmallIcon(2130837711);
            builder.setTicker(charSequence2);
            builder.setAutoCancel(true);
            builder.setNumber(0);
            if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
                builder.setDefaults(1);
            } else {
                builder.setDefaults(0);
            }
            Intent intent = new Intent();
            intent.setClass(aVar.p, DownloadCenterActivity.class);
            intent.putExtra(k.l, taskInfo.mTaskId);
            PendingIntent activity = PendingIntent.getActivity(aVar.p, (int) taskInfo.mTaskId, intent, 134217728);
            builder.setContentTitle(a);
            builder.setContentText(charSequence);
            builder.setContentIntent(activity);
            aVar.d.a(27856, builder.build());
            aVar.q.clear();
            aVar.f = -1;
        }
    }

    static /* synthetic */ void b(a aVar, TaskInfo taskInfo) {
        if (taskInfo != null && aVar.p != null) {
            String str = taskInfo.mFileName;
            XLToast.a(aVar.p, XLToastType.XLTOAST_TYPE_NORMAL, String.format(aVar.p.getString(2131231160), new Object[]{str}));
        }
    }

    static /* synthetic */ void a(a aVar, TaskRunningInfo taskRunningInfo, String str) {
        Context context = aVar.p;
        int i = VERSION.SDK_INT;
        try {
            Object systemService = context.getSystemService("statusbar");
            Class forName = Class.forName("android.app.StatusBarManager");
            if (systemService != null) {
                Method method;
                if (i <= 16) {
                    method = forName.getMethod("collapse", new Class[0]);
                } else {
                    method = forName.getMethod("collapsePanels", new Class[0]);
                }
                method.setAccessible(true);
                method.invoke(systemService, new Object[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadCenterActivity.a(aVar.p, taskRunningInfo.mTaskId, str, taskRunningInfo, "download_push_bxbb");
    }

    static /* synthetic */ void a(a aVar, TaskInfo taskInfo, String str) {
        Activity activity = BrothersApplication.a().n;
        Context context = aVar.p;
        if (!(activity == null || (activity instanceof DownloadCenterActivity))) {
            context = activity.getApplicationContext();
        }
        DownloadCenterActivity.b(context, taskInfo.getTaskId(), str);
    }
}
