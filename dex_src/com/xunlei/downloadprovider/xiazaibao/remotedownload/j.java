package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RemoteDownloadContainerFragment.java
final class j implements RemoteDownloadListFragment$a {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    j(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void a(int i, XZBDownloadTaskSet xZBDownloadTaskSet) {
        Object obj;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        long j = 0;
        long j2 = 0;
        if (xZBDownloadTaskSet != null) {
            long j3;
            int dowloadingNum = xZBDownloadTaskSet.getDowloadingNum();
            i3 = xZBDownloadTaskSet.getCompleteNum();
            int serverFailNum = xZBDownloadTaskSet.getServerFailNum();
            int i6 = (dowloadingNum + i3) + serverFailNum;
            long speedCount = xZBDownloadTaskSet.getSpeedCount();
            j = xZBDownloadTaskSet.getSpeedupCount();
            List<DownloadTaskInfo> tasks = xZBDownloadTaskSet.getTasks();
            if (tasks != null) {
                for (DownloadTaskInfo downloadTaskInfo : tasks) {
                    if (downloadTaskInfo.getState() != 9) {
                        if (downloadTaskInfo.getState() == 10) {
                        }
                    }
                    obj = 1;
                    i2 = serverFailNum;
                    j3 = j;
                    j = speedCount;
                    i5 = i6;
                    i4 = i3;
                    j2 = j3;
                    i3 = dowloadingNum;
                    break;
                }
            }
            obj = null;
            i2 = serverFailNum;
            j3 = j;
            j = speedCount;
            i5 = i6;
            i4 = i3;
            j2 = j3;
            i3 = dowloadingNum;
        } else {
            i2 = 0;
            obj = null;
        }
        if (RemoteDownloadContainerFragment.e(this.a) != null) {
            StatusInfo statusInfo = RemoteDownloadContainerFragment.e(this.a).getStatusInfo();
            if (i != 0) {
                statusInfo.d = TasksStatus.TasksError;
                if (i == -4) {
                    statusInfo.b = "\u8bbe\u5907\u79bb\u7ebf";
                    statusInfo.c = "\u8bf7\u68c0\u67e5\u4e0b\u8f7d\u5b9d\u7f51\u7edc\u8fde\u63a5\u540e\u91cd\u8bd5";
                } else if (i == -3 || i == 45) {
                    statusInfo.b = "\u83b7\u53d6\u4efb\u52a1\u5931\u8d25";
                    statusInfo.c = "\u8bf7\u68c0\u67e5\u4e0b\u8f7d\u5b9d\u7ed1\u5b9a\u72b6\u6001\u540e\u91cd\u8bd5";
                } else {
                    statusInfo.b = "\u83b7\u53d6\u4efb\u52a1\u5931\u8d25";
                    statusInfo.c = "\u8bf7\u68c0\u67e5\u7f51\u7edc\u8bbe\u7f6e\u540e\u91cd\u8bd5";
                }
                RemoteDownloadContainerFragment.e(this.a).a();
            } else {
                if (i5 == 0) {
                    statusInfo.d = TasksStatus.NoTasks;
                } else {
                    if (i3 > 0) {
                        if (j > 0) {
                            if (j2 > 0) {
                                statusInfo.a = true;
                            } else {
                                statusInfo.a = false;
                            }
                            statusInfo.d = TasksStatus.TasksDownloading;
                        } else if (obj == null) {
                            statusInfo.a = false;
                            statusInfo.d = TasksStatus.TasksDownloading;
                        }
                    } else if (i2 <= 0 && i4 > 0) {
                        statusInfo.d = TasksStatus.TasksFinished;
                    }
                    statusInfo.d = TasksStatus.TasksPaused;
                }
                RemoteDownloadContainerFragment.e(this.a).setDownloadSpeed(j);
            }
        }
        RemoteDownloadContainerFragment.f(this.a);
        if (RemoteDownloadContainerFragment.g(this.a) != null) {
            RemoteDownloadContainerFragment.g(this.a).a(0).a(String.format(RemoteDownloadContainerFragment.c()[0], new Object[]{Integer.valueOf(i5)}));
            RemoteDownloadContainerFragment.g(this.a).a(1).a(String.format(RemoteDownloadContainerFragment.c()[1], new Object[]{Integer.valueOf(i2 + i3)}));
            RemoteDownloadContainerFragment.g(this.a).a(SimpleLog.LOG_LEVEL_DEBUG).a(String.format(RemoteDownloadContainerFragment.c()[2], new Object[]{Integer.valueOf(i4)}));
        }
    }
}
