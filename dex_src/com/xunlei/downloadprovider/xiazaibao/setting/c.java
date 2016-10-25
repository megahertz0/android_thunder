package com.xunlei.downloadprovider.xiazaibao.setting;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;

// compiled from: GetDevicePresenter.java
final class c extends XZBShouleiCallback {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void cb_UpdateDeviceList(int i, int i2, XZBDevice[] xZBDeviceArr, String str, Object obj) {
        this.a.a.a(i, xZBDeviceArr);
    }
}
