package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: RemoteDownloadContainerFragment.java
final class h implements OnClickListener {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    h(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void onClick(View view) {
        this.a.g.b().f.a();
        RemoteDownloadContainerFragment.a(this.a).a(this.a.g.b().b.d(), BuildConfig.VERSION_NAME, new i(this));
    }
}
