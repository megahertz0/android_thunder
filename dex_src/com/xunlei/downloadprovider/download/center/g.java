package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView.b;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment;

// compiled from: DownloadCenterActivityFragment.java
final class g implements b {
    final /* synthetic */ DownloadCenterActivityFragment a;

    g(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void a(boolean z) {
        TaskListPageFragment taskListPageFragment;
        if (z) {
            DownloadCenterActivityFragment$b c = DownloadCenterActivityFragment.c(this.a);
            taskListPageFragment = (TaskListPageFragment) c.getItem(DownloadCenterActivityFragment.q(c.g).getCurrentItem());
            if (taskListPageFragment.d != null) {
                taskListPageFragment.d.i();
                if (taskListPageFragment.e != null) {
                    taskListPageFragment.e.a(taskListPageFragment.d.k());
                    return;
                }
                return;
            }
            return;
        }
        c = DownloadCenterActivityFragment.c(this.a);
        taskListPageFragment = (TaskListPageFragment) c.getItem(DownloadCenterActivityFragment.q(c.g).getCurrentItem());
        if (taskListPageFragment.d != null) {
            taskListPageFragment.d.j();
            if (taskListPageFragment.e != null) {
                taskListPageFragment.e.a(taskListPageFragment.d.k());
            }
        }
        DownloadCenterActivityFragment.g(this.a).setTitle(this.a.getActivity().getResources().getString(R.string.download_list_select_title));
    }
}
