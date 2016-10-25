package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: RemoteDownloadContainerFragment.java
final class o implements OnClickListener {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    o(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void onClick(View view) {
        RemoteDownloadContainerFragment.d(this.a).finish();
    }
}
