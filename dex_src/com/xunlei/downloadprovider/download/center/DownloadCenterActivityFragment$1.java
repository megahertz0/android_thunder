package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus;

/* synthetic */ class DownloadCenterActivityFragment$1 {
    static final /* synthetic */ int[] a;

    static {
        a = new int[TasksStatus.values().length];
        try {
            a[TasksStatus.NoTasks.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TasksStatus.TasksPaused.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TasksStatus.TasksFailed.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[TasksStatus.TasksFinished.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
