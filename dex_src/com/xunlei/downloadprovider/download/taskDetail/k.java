package com.xunlei.downloadprovider.download.taskDetail;

import android.view.animation.AnimationUtils;
import com.xunlei.downloadprovider.app.BaseActivity;

// compiled from: DownloadCenterDetailFragment.java
public final class k implements Runnable {
    final /* synthetic */ DownloadCenterDetailFragment a;

    public k(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void run() {
        DownloadCenterDetailFragment downloadCenterDetailFragment = this.a;
        if (downloadCenterDetailFragment.k != null) {
            if (downloadCenterDetailFragment.m != null) {
                downloadCenterDetailFragment.m.a();
            }
            downloadCenterDetailFragment.j.setVisibility(0);
            downloadCenterDetailFragment.a(DownloadCenterDetailFragment.b);
            downloadCenterDetailFragment.getView().setVisibility(0);
            ((BaseActivity) downloadCenterDetailFragment.getActivity()).animationBarAlpha(true);
            downloadCenterDetailFragment.q = AnimationUtils.loadAnimation(downloadCenterDetailFragment.getActivity(), 2131034126);
            downloadCenterDetailFragment.q.setDuration(300);
            downloadCenterDetailFragment.q.setAnimationListener(new l(downloadCenterDetailFragment));
            downloadCenterDetailFragment.i.setAnimation(downloadCenterDetailFragment.q);
            downloadCenterDetailFragment.i.animate();
            downloadCenterDetailFragment.r = AnimationUtils.loadAnimation(downloadCenterDetailFragment.getActivity(), 2131034143);
            downloadCenterDetailFragment.r.setAnimationListener(new m(downloadCenterDetailFragment));
            downloadCenterDetailFragment.m.setAnimation(downloadCenterDetailFragment.r);
            downloadCenterDetailFragment.m.animate();
            downloadCenterDetailFragment.e.c(false);
            downloadCenterDetailFragment.g.a(false);
        }
    }
}
