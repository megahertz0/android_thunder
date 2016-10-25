package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.frame.view.XLTabLayout.b;
import com.xunlei.downloadprovider.frame.view.XLTabView;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: MainTabActivity.java
final class h implements b {
    final /* synthetic */ MainTabActivity a;

    h(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void a(XLTabView xLTabView) {
        boolean z;
        String currentTag = MainTabActivity.b(this.a).getCurrentTag();
        String tabTag = xLTabView.getTabTag();
        if (tabTag.equals(currentTag)) {
            z = false;
        } else {
            z = true;
        }
        BaseFragment b = MainTabActivity.e(this.a).b(tabTag);
        if (b instanceof BaseViewPagerFragment) {
            BasePageFragment a = ((BaseViewPagerFragment) b).a();
            if (a != null) {
                a.onMainTabClick(z);
            }
        }
        StatReporter.reportMainTabClick(tabTag);
    }
}
