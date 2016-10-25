package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

// compiled from: XZBTaskControl.java
final class an extends XZBShouleiCallback {
    final /* synthetic */ am a;

    an(am amVar) {
        this.a = amVar;
    }

    public final void cb_UpdateDeviceList(int i, int i2, XZBDevice[] xZBDeviceArr, String str, Object obj) {
        XZBDevice defaultDevice = XZBShouleiUtil.getInstance().getDefaultDevice();
        if (defaultDevice != null) {
            defaultDevice.downloadSettingTask("xzb_download_center", null);
            super.cb_UpdateDeviceList(i, i2, xZBDeviceArr, str, obj);
        }
    }
}
