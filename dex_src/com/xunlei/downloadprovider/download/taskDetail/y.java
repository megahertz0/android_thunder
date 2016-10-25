package com.xunlei.downloadprovider.download.taskDetail;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: DownloadCenterDetailFragment.java
final class y implements OnPageChangeListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    y(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageSelected(int i) {
        if (i == 0) {
            DownloadCenterDetailFragment.c(this.a).setContentListId(R.id.task_bt_list_view);
            if (DownloadCenterDetailFragment.d(this.a)) {
                a.g("btlist");
                return;
            } else {
                DownloadCenterDetailFragment.e(this.a);
                return;
            }
        }
        DownloadCenterDetailFragment.c(this.a).setContentListId(R.id.task_detail_list_view);
        if (n.c(DownloadCenterDetailFragment.f(this.a))) {
            DownloadCenterDetailFragment.e(this.a);
            a.g("info");
        }
    }

    public final void onPageScrollStateChanged(int i) {
        if (i == 0) {
            DownloadCenterDetailFragment.c(this.a).setScrollEnable(true);
        } else {
            DownloadCenterDetailFragment.c(this.a).setScrollEnable(false);
        }
    }
}
