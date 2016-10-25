package com.xunlei.downloadprovider.ucaddon;

import com.uc.addon.sdk.remote.protocol.BannerClickListener;

// compiled from: UcAddonEventRecevier.java
final class b extends BannerClickListener {
    final /* synthetic */ UcAddonEventRecevier a;

    b(UcAddonEventRecevier ucAddonEventRecevier) {
        this.a = ucAddonEventRecevier;
    }

    public final void onBannerDismiss() {
    }

    public final void onBannerClick(int i) {
        if (i == 123) {
            this.a.startToThunder();
        }
    }
}
