package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice$XZBDeviceCallback;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;

class XZBShouleiUtil$5 extends XZBDevice$XZBDeviceCallback {
    final /* synthetic */ XZBShouleiUtil this$0;
    final /* synthetic */ XZBShouleiCallback val$callback;
    final /* synthetic */ XZBDevice val$xzbDevice;

    XZBShouleiUtil$5(XZBShouleiUtil xZBShouleiUtil, XZBShouleiCallback xZBShouleiCallback, XZBDevice xZBDevice) {
        this.this$0 = xZBShouleiUtil;
        this.val$callback = xZBShouleiCallback;
        this.val$xzbDevice = xZBDevice;
    }

    public void cb_StartDownloadTask(int i, int i2, List<DownloadTaskResult> list, String str, Object obj) {
        this.val$callback.cb_StartTask(i, i2, this.val$xzbDevice, list, str, obj);
        super.cb_StartDownloadTask(i, i2, list, str, obj);
    }
}
