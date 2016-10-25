package com.xunlei.xiazaibao.shoulei.setting;

import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.DownloadPathType;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

public class GetDevicePresenter {
    public IDownloadDeviceSettingFragment mIFragment;

    public GetDevicePresenter(IDownloadDeviceSettingFragment iDownloadDeviceSettingFragment) {
        this.mIFragment = iDownloadDeviceSettingFragment;
    }

    public void getDeviceList() {
        this.mIFragment.setDeviceStatusGetting();
        XZBShouleiUtil.getInstance().updateDeviceList(BuildConfig.VERSION_NAME, new XZBShouleiCallback() {
            public void cb_UpdateDeviceList(int i, int i2, XZBDevice[] xZBDeviceArr, String str, Object obj) {
                GetDevicePresenter.this.mIFragment.setDeviceStatusComplete(i, i2, xZBDeviceArr, str, obj);
            }
        });
    }

    public void storeDownloadPathSettingToPrefs(DownloadPathType downloadPathType, XZBDevice xZBDevice) {
        XZBShouleiUtil.getInstance().storeDownloadPathTypeMsg(downloadPathType, xZBDevice);
    }
}
