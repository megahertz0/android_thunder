package com.xunlei.downloadprovider.download.create;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: DownloadBtFileExplorerActivity.java
final class r implements OnClickListener {
    final /* synthetic */ DownloadBtFileExplorerActivity a;

    r(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity) {
        this.a = downloadBtFileExplorerActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.mResumeTaskDialog != null) {
            this.a.mResumeTaskDialog.dismiss();
            this.a.mResumeTaskDialog = null;
        }
        BrothersApplication.a(true);
        this.a.startDownload();
    }
}
