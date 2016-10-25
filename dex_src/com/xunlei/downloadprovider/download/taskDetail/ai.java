package com.xunlei.downloadprovider.download.taskDetail;

// compiled from: DownloadTaskDetailBtListFragment.java
final class ai implements Runnable {
    final /* synthetic */ DownloadTaskDetailBtListFragment a;

    ai(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment) {
        this.a = downloadTaskDetailBtListFragment;
    }

    public final void run() {
        if (this.a.t != null) {
            this.a.t.setSelection(0);
        }
    }
}
