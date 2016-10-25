package com.xunlei.downloadprovider.discovery.a;

import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: DiscoveryUtil.java
public final class b {
    public static boolean a() {
        return BrothersApplication.a().getSharedPreferences("shared_for_kuainiao_display", 0).getBoolean("is_has_showed_kuainiao", false);
    }

    public static void a(boolean z) {
        BrothersApplication.a().getSharedPreferences("shared_for_kuainiao_display", 0).edit().putBoolean("is_has_showed_kuainiao", z).commit();
    }

    public static boolean b() {
        return BrothersApplication.a().getSharedPreferences("find_fragment_KuaiNiao_RedPoint", 0).getBoolean("findNeedShowKuaiNiaoRedPoint", true);
    }
}
