package com.xunlei.downloadprovider.ucaddon;

import com.uc.addon.sdk.remote.protocol.ButtonClickListener;

// compiled from: UcAddonEventRecevier.java
final class c extends ButtonClickListener {
    final /* synthetic */ UcAddonEventRecevier a;

    c(UcAddonEventRecevier ucAddonEventRecevier) {
        this.a = ucAddonEventRecevier;
    }

    public final void onClick() {
        this.a.startToThunder();
    }
}
