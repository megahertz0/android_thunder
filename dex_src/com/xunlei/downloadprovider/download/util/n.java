package com.xunlei.downloadprovider.download.util;

import android.text.TextUtils;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import org.android.spdy.SpdyAgent;

// compiled from: TaskHelper.java
public final class n {
    public static boolean a(TaskRunningInfo taskRunningInfo) {
        if (taskRunningInfo == null) {
            return false;
        }
        EFileCategoryType a;
        if (TextUtils.isEmpty(taskRunningInfo.mLocalFileName)) {
            a = XLFileTypeUtil.a(taskRunningInfo.mTitle);
        } else {
            a = XLFileTypeUtil.a(taskRunningInfo.mLocalFileName);
        }
        return a == EFileCategoryType.E_VIDEO_CATEGORY;
    }

    public static boolean b(TaskRunningInfo taskRunningInfo) {
        return (taskRunningInfo == null || taskRunningInfo == null || taskRunningInfo.mTaskStatus != 8) ? false : true;
    }

    public static boolean a(a aVar) {
        return aVar != null && aVar.mVipChannelStatusCode == 501;
    }

    public static boolean c(TaskRunningInfo taskRunningInfo) {
        return taskRunningInfo != null && taskRunningInfo.mTaskType == TaskType.BT;
    }

    public static boolean d(TaskRunningInfo taskRunningInfo) {
        if (taskRunningInfo == null) {
            return false;
        }
        EFileCategoryType a;
        if (TextUtils.isEmpty(taskRunningInfo.mLocalFileName)) {
            a = XLFileTypeUtil.a(taskRunningInfo.mTitle);
        } else {
            a = XLFileTypeUtil.a(taskRunningInfo.mLocalFileName);
        }
        return a == EFileCategoryType.E_SOFTWARE_CATEGORY;
    }

    public static boolean b(a aVar) {
        Object obj = null;
        boolean z = aVar.mSeen == 0 && aVar.mTaskStatus == 8;
        if (!z) {
            return z;
        }
        try {
            boolean z2;
            if (new File(aVar.mLocalFileName).exists()) {
                z2 = z;
            }
            return z2;
        } catch (Exception e) {
            return z;
        }
    }

    public static int c(a aVar) {
        EFileCategoryType a;
        int i = 0;
        String str = aVar.mFileName;
        if (aVar.mLocalFileName != null) {
            a = XLFileTypeUtil.a(aVar.mLocalFileName);
            str = aVar.mLocalFileName;
        } else {
            a = XLFileTypeUtil.a(aVar.mTitle);
        }
        if (aVar.mTaskType == TaskType.BT) {
            return R.drawable.ic_dl_bt;
        }
        if (aVar.mTaskType == TaskType.MAGNET) {
            return R.drawable.ic_dl_magnet;
        }
        if (str != null) {
            i = XLFileTypeUtil.d(str.trim());
        }
        if (i != 2130838415 && i != 0) {
            return i;
        }
        switch (AnonymousClass_1.a[a.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return str != null ? XLFileTypeUtil.d(str) : R.drawable.ic_dl_mp4;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return R.drawable.ic_dl_music;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return R.drawable.ic_dl_text;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return R.drawable.ic_dl_image;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return R.drawable.ic_dl_rar;
            case R.styleable.Toolbar_contentInsetEnd:
                return R.drawable.ic_dl_torrent;
            case R.styleable.Toolbar_contentInsetLeft:
                return 2130838415;
            default:
                return 2130838415;
        }
    }

    public static boolean d(a aVar) {
        return (aVar == null || TextUtils.isEmpty(aVar.mFilePath)) ? false : new File(aVar.mFilePath).exists();
    }

    public static String e(a aVar) {
        if (aVar == null) {
            return "dl_unfinish";
        }
        return aVar.mTaskStatus == 8 ? "dl_finish" : "dl_unfinish";
    }

    public static boolean f(a aVar) {
        return g.a().d == aVar.getTaskId() && aVar.mIsEnteredHighSpeedTrial && !a(aVar) && g(aVar) > 0;
    }

    public static long g(a aVar) {
        if (aVar.mTaskId != g.a().d) {
            return -1;
        }
        d.a();
        TaskInfo d = d.d(aVar.getTaskId());
        float h = g.h();
        long j = (long) (((float) d.mFileSize) * h);
        long e = (g.a().e() - d.mDownloadedSize) + j;
        new StringBuilder("downloadSize: ").append(d.mDownloadedSize).append("  beforeTry:  ").append(g.a().e()).append("  canUse :").append(j).append("   factor: ").append(h);
        return e;
    }

    public static boolean a(a aVar, long j) {
        return aVar.mVipChannelStatusCode == 501 || j <= 0;
    }

    public static boolean a(TaskInfo taskInfo) {
        return taskInfo.mVipChannelStatusCode == 501 || ((long) (((float) taskInfo.mFileSize) * g.h())) + (g.a().e() - taskInfo.mDownloadedSize) <= 0;
    }
}
