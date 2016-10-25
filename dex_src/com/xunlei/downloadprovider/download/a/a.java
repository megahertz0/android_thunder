package com.xunlei.downloadprovider.download.a;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.h.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.util.g;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadCenterControl.java
public final class a {
    static a e;
    public Activity a;
    protected Handler b;
    d c;
    d d;

    public a() {
        this.b = new Handler(new b(this));
        this.c = null;
        this.d = null;
    }

    public static void a(o oVar) {
        n.a().a.registerObserver(oVar);
    }

    public final void a(TaskRunningInfo taskRunningInfo, String str) {
        if (taskRunningInfo != null) {
            String str2 = taskRunningInfo.mTitle;
            String str3 = taskRunningInfo.mLocalFileName;
            if (!TextUtils.isEmpty(str3)) {
                File file = new File(str3);
                String absolutePath;
                if (taskRunningInfo.mTaskType == TaskType.BT) {
                    if (!file.exists()) {
                        XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131232684));
                    } else if (file.listFiles().length == 1) {
                        EFileCategoryType a = XLFileTypeUtil.a(file.listFiles()[0].getAbsolutePath());
                        if (a == null || !(a == EFileCategoryType.E_MUSIC_CATEGORY || a == EFileCategoryType.E_VIDEO_CATEGORY)) {
                            com.xunlei.downloadprovider.download.report.a.b("dl_finish_other", "finish", str);
                            a(this.a, taskRunningInfo);
                            return;
                        }
                        absolutePath = file.listFiles()[0].getAbsolutePath();
                        if (a == EFileCategoryType.E_VIDEO_CATEGORY && XLFileTypeUtil.e(absolutePath)) {
                            StatReporter.reportOverallPlay("download_finish", "tnative");
                        }
                        com.xunlei.downloadprovider.download.report.a.b("dl_finish_open_video", "finish", str);
                        b.a(absolutePath, null, true);
                    } else {
                        com.xunlei.downloadprovider.download.report.a.b("dl_finish_other", "finish", str);
                        a(this.a, taskRunningInfo);
                    }
                } else if (!file.exists()) {
                    this.a.getApplicationContext();
                    if (com.xunlei.downloadprovider.businessutil.a.b()) {
                        com.xunlei.downloadprovider.download.report.a.b("dl_finish_download", "finish", str);
                        f fVar = new f(this, taskRunningInfo);
                        if (this.d != null) {
                            this.d.dismiss();
                            this.d = null;
                        }
                        this.d = new d(this.a);
                        this.d.a(this.a.getString(2131231306));
                        this.d.c(this.a.getString(R.string.cancel));
                        this.d.d(this.a.getString(2131231242));
                        this.d.setCanceledOnTouchOutside(true);
                        this.d.a(new g(this, fVar));
                        this.d.b(new h(this, fVar));
                        this.d.show();
                        return;
                    }
                    XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231311));
                } else if (taskRunningInfo.mFileSize == 0) {
                    XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231270));
                } else {
                    taskRunningInfo.mConsumed = true;
                    taskRunningInfo.mRevision++;
                    com.xunlei.downloadprovider.service.downloads.task.b.d.a().c(taskRunningInfo.mTaskId);
                    if (g.a(str2)) {
                        com.xunlei.downloadprovider.a.c.a a2 = c.a(this.a.getBaseContext(), str3);
                        if (c.a(this.a.getBaseContext(), a2) == 4) {
                            str2 = a2.c();
                            if (str2 == null || !str2.equals(this.a.getPackageName())) {
                                com.xunlei.downloadprovider.download.report.a.b("dl_finish_open_app", "finish", str);
                                c.c(this.a.getBaseContext(), str2);
                                return;
                            }
                            MainTabActivity.a(this.a, "thunder");
                        } else if (str3 != null) {
                            com.xunlei.downloadprovider.download.report.a.b("dl_finish_install", "finish", str);
                            b.a(str3, null, true);
                        }
                    } else if (g.b(str2) || taskRunningInfo.mTaskType == TaskType.MAGNET) {
                        com.xunlei.downloadprovider.download.report.a.b("dl_finish_other", "finish", str);
                        absolutePath = Uri.fromFile(new File(str3)).toString();
                        str2 = new StringBuilder("file://").append(str3).toString();
                        com.xunlei.downloadprovider.service.downloads.task.d.a();
                        DownloadBtFileExplorerActivity.startSelf(this.a, absolutePath, com.xunlei.downloadprovider.service.downloads.task.d.d(str2), XZBDevice.Pause, com.umeng.a.d);
                    } else {
                        long taskId = taskRunningInfo.getTaskId();
                        if (XLFileTypeUtil.a(str3) == EFileCategoryType.E_VIDEO_CATEGORY) {
                            com.xunlei.downloadprovider.download.report.a.b("dl_finish_open_video", "finish", str);
                        } else {
                            com.xunlei.downloadprovider.download.report.a.b("dl_finish_other", "finish", str);
                        }
                        String str4 = "download_list";
                        if (XLFileTypeUtil.e(str3)) {
                            StatReporter.reportOpenWithHandleFile(0, com.xunlei.downloadprovider.d.c.c(str3), str4);
                            if ("download_list".equals(str4)) {
                                VodUtil.a();
                                VodUtil.a(BrothersApplication.a(), str3, taskId);
                                return;
                            }
                            return;
                        }
                        b.a(str3, str4, true);
                    }
                }
            }
        }
    }

    public static void a(Context context, TaskRunningInfo taskRunningInfo) {
        Intent intent = new Intent("com.xunLei.downloadCenter.MoreOperate");
        intent.putExtra("taskInfo", taskRunningInfo);
        context.sendBroadcast(intent);
    }

    public static void a(Context context, PayFrom payFrom, String str) {
        PayEntryParam payEntryParam = new PayEntryParam(payFrom);
        payEntryParam.c = com.xunlei.downloadprovider.homepage.a.a.d.a;
        payEntryParam.d = str;
        PaymentEntryActivity.a(context, payEntryParam);
    }

    public final void a(long j, String str) {
        LoginHelper a = LoginHelper.a();
        if (!LoginHelper.d()) {
            a(this.a, PayFrom.DOWNLOAD_TASK_SPEED_UP, str);
            a.a(new k(this, j));
        } else if (a.f() || a.y > 0) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(j);
        } else {
            a(this.a, PayFrom.DOWNLOAD_TASK_SPEED_UP, str);
        }
    }

    public static void b(TaskRunningInfo taskRunningInfo) {
        n a = n.a();
        if (taskRunningInfo.mTaskStatus == 1 || taskRunningInfo.mTaskStatus == 2) {
            taskRunningInfo.mRunningInfo.a(XZBDevice.DOWNLOAD_LIST_ALL);
            taskRunningInfo.mRevision++;
        }
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.d.a(taskRunningInfo.mTaskId);
        com.xunlei.downloadprovider.download.a.n.a aVar = a.a;
        List arrayList = new ArrayList();
        arrayList.add(taskRunningInfo);
        aVar.b(arrayList);
    }

    public static void a(List<TaskRunningInfo> list, boolean z) {
        n a = n.a();
        if (!list.isEmpty()) {
            long[] jArr = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                TaskRunningInfo taskRunningInfo = (TaskRunningInfo) list.get(i);
                jArr[i] = taskRunningInfo.getTaskId();
                taskRunningInfo.mRunningInfo.a(R.styleable.Toolbar_maxButtonHeight);
                taskRunningInfo.mRevision++;
            }
            com.xunlei.downloadprovider.service.downloads.task.d.a().c(z, jArr);
            a.a.c(list);
        }
        com.xunlei.downloadprovider.download.util.g.a().a((List) list);
    }

    public static void a(Context context, String str, String str2, long j, int i, String str3, String str4, String str5) {
        if (e != null) {
            if (!e.isCancelled()) {
                e.cancel(false);
            }
            e = null;
        }
        e eVar = new e(context, str5);
        e = eVar;
        eVar.execute(new b[]{new b(str, str2, j, i, str3, str4)});
    }

    public final void a(TaskRunningInfo taskRunningInfo) {
        Context context = this.a;
        if (taskRunningInfo != null) {
            a(context, taskRunningInfo.mFilePath, taskRunningInfo.mFileName, taskRunningInfo.mTaskId, -1, taskRunningInfo.mCID, taskRunningInfo.mGCID, "download_bxbb");
        }
    }

    public final void a(OnClickListener onClickListener) {
        if (this.c != null) {
            this.c.dismiss();
            this.c = null;
        }
        this.c = new d(this.a);
        this.c.a(this.a.getString(2131231750));
        this.c.c(this.a.getString(2131231753));
        this.c.d(this.a.getString(2131231745));
        this.c.setCanceledOnTouchOutside(true);
        this.c.a(new i(this, onClickListener));
        this.c.b(new j(this));
        this.c.show();
    }

    public final void c(TaskRunningInfo taskRunningInfo) {
        if (!com.xunlei.xllib.a.b.a(this.a)) {
            XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231758));
        } else if (com.xunlei.xllib.a.b.h(this.a)) {
            n.a().b(taskRunningInfo, false);
        } else {
            a(new l(this, taskRunningInfo));
        }
    }

    public final void d(TaskRunningInfo taskRunningInfo) {
        if (!com.xunlei.xllib.a.b.a(this.a)) {
            XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231758));
        } else if (com.xunlei.xllib.a.b.h(this.a)) {
            n.a().a(taskRunningInfo, false);
        } else {
            a(new m(this, taskRunningInfo));
        }
    }
}
