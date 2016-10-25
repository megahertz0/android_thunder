package com.xunlei.downloadprovider.download.center;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment;
import com.xunlei.downloadprovider.download.tasklist.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.n;

// compiled from: DownloadCenterActivityFragment.java
final class ac implements OnPageChangeListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    ac(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageSelected(int i) {
        n a = n.a();
        a.e = i;
        a.b();
        h.a().a(i);
        DownloadCenterActivityFragment.b(i);
        if (DownloadCenterActivityFragment.c(this.a) != null) {
            TaskListPageFragment a2 = DownloadCenterActivityFragment.c(this.a).a();
            a2.k = true;
            a2.j.a(false);
            h.a().b(0);
            if (i == 2 || i == 1) {
                a2 = DownloadCenterActivityFragment.c(this.a).a();
                if (a2.c != null) {
                    a2.c.stopNestedScroll();
                }
            }
        }
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
