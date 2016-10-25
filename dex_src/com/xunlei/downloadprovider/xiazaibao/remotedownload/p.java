package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBottomView;

// compiled from: RemoteDownloadContainerFragment.java
final class p implements OnClickListener {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    p(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void onClick(View view) {
        RemoteDownloadContainerFragment remoteDownloadContainerFragment = this.a;
        remoteDownloadContainerFragment.e = true;
        remoteDownloadContainerFragment.c.b(true);
        remoteDownloadContainerFragment.c.setTitle(remoteDownloadContainerFragment.getResources().getString(R.string.download_list_select_title));
        remoteDownloadContainerFragment.a.setTabLayoutEnable(false);
        remoteDownloadContainerFragment.b.setCanScroll(false);
        int a = g.a(remoteDownloadContainerFragment.getActivity(), 74.0f) - Math.abs(remoteDownloadContainerFragment.f);
        RemoteDownloadListFragment b = remoteDownloadContainerFragment.g.b();
        if (b.b != null) {
            b.b.h = a;
        }
        remoteDownloadContainerFragment.a(false);
        remoteDownloadContainerFragment.g.b(true);
        remoteDownloadContainerFragment.g.b().b.e = remoteDownloadContainerFragment.h;
        XZBDownloadBottomView xZBDownloadBottomView = remoteDownloadContainerFragment.d;
        xZBDownloadBottomView.o = true;
        xZBDownloadBottomView.setVisibility(0);
        xZBDownloadBottomView.startAnimation(xZBDownloadBottomView.m);
        xZBDownloadBottomView.a();
        remoteDownloadContainerFragment.d.a();
    }
}
