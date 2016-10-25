package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus;

/* synthetic */ class RemoteDownloadContainerFragment$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[TasksStatus.values().length];
        try {
            a[TasksStatus.Init.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TasksStatus.NoTasks.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TasksStatus.TasksPaused.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[TasksStatus.TasksFinished.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[TasksStatus.TasksDownloading.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[TasksStatus.TasksError.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}
