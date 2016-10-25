package com.xunlei.downloadprovider.download.create;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: DownloadBtFileExplorerActivity.java
final class s implements OnClickListener {
    final /* synthetic */ DownloadBtFileExplorerActivity a;

    s(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity) {
        this.a = downloadBtFileExplorerActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.mResumeTaskDialog != null) {
            this.a.mResumeTaskDialog.dismiss();
            this.a.mResumeTaskDialog = null;
        }
    }
}
