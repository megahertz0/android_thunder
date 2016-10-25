package com.xunlei.downloadprovider.download.taskDetail;

import android.database.Cursor;
import android.support.v4.content.Loader;

// compiled from: DownloadTaskDetailBtListFragment.java
final class ae implements Runnable {
    final /* synthetic */ Loader a;
    final /* synthetic */ Cursor b;
    final /* synthetic */ DownloadTaskDetailBtListFragment c;

    ae(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment, Loader loader, Cursor cursor) {
        this.c = downloadTaskDetailBtListFragment;
        this.a = loader;
        this.b = cursor;
    }

    public final void run() {
        DownloadTaskDetailBtListFragment.a(this.c, this.b);
    }
}
