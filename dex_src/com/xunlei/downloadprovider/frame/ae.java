package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.discovery.DiscoveryFragment;
import com.xunlei.downloadprovider.frame.user.UserCenterFragment;
import com.xunlei.downloadprovider.frame.view.a;
import com.xunlei.downloadprovider.homepage.HomeFragment;
import com.xunlei.downloadprovider.search.ui.home.SearchHomeFragment;
import java.util.HashMap;
import java.util.Map;

// compiled from: MainTabFragmentBuilder.java
public final class ae extends a {
    Map<String, BaseFragment> a;

    public ae() {
        this.a = null;
    }

    public final BaseFragment a(String str) {
        BaseFragment b = b(str);
        if (b != null) {
            return b;
        }
        if (str.equals("thunder")) {
            HomeFragment homeFragment = new HomeFragment();
            a(str, homeFragment);
            return homeFragment;
        } else if (str.equals("search")) {
            b = new SearchHomeFragment();
            a(str, b);
            return b;
        } else if (str.equals("find")) {
            b = new DiscoveryFragment();
            a(str, b);
            return b;
        } else if (!str.equals("user")) {
            return null;
        } else {
            b = new UserCenterFragment();
            a(str, b);
            return b;
        }
    }

    private void a(String str, BaseFragment baseFragment) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, baseFragment);
    }

    public final BaseFragment b(String str) {
        return this.a == null ? null : (BaseFragment) this.a.get(str);
    }
}
