package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView.b;

// compiled from: RemoteDownloadContainerFragment.java
final class n implements b {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    n(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void a(boolean z) {
        if (z) {
            q qVar = this.a.g.b().b;
            for (al alVar : qVar.c) {
                if (alVar != qVar.a && alVar != qVar.b) {
                    alVar.a = true;
                }
            }
            qVar.b();
            qVar.c();
            return;
        }
        RemoteDownloadContainerFragment.b(this.a).setTitle(this.a.getResources().getString(R.string.download_list_select_title));
        this.a.g.a();
    }
}
