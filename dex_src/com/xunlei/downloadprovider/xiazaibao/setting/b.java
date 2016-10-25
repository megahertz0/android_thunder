package com.xunlei.downloadprovider.xiazaibao.setting;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.DownloadPathType;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

// compiled from: GetDevicePresenter.java
public final class b {
    public d a;

    public b(d dVar) {
        this.a = dVar;
    }

    public static void a(DownloadPathType downloadPathType, XZBDevice xZBDevice) {
        XZBShouleiUtil.getInstance().storeDownloadPathTypeMsg(downloadPathType, xZBDevice);
    }
}
