package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment;

// compiled from: DownloadCenterActivityFragment.java
final class n implements Runnable {
    final /* synthetic */ DownloadCenterActivityFragment a;

    n(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void run() {
        if (this.a.isVisible() && DownloadCenterActivityFragment.c(this.a) != null && DownloadCenterActivityFragment.c(this.a).a != null) {
            TaskListPageFragment taskListPageFragment = DownloadCenterActivityFragment.c(this.a).a;
            if (taskListPageFragment.d != null) {
                taskListPageFragment.d.m();
            }
        }
    }
}
