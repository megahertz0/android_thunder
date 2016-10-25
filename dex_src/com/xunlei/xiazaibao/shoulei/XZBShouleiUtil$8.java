package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice$XZBDeviceCallback;

class XZBShouleiUtil$8 extends XZBDevice$XZBDeviceCallback {
    final /* synthetic */ XZBShouleiUtil this$0;
    final /* synthetic */ XZBShouleiCallback val$callback;
    final /* synthetic */ XZBDevice val$xzbDevice;

    XZBShouleiUtil$8(XZBShouleiUtil xZBShouleiUtil, XZBShouleiCallback xZBShouleiCallback, XZBDevice xZBDevice) {
        this.this$0 = xZBShouleiUtil;
        this.val$callback = xZBShouleiCallback;
        this.val$xzbDevice = xZBDevice;
    }

    public void cb_CreateBtDownloadTask(int i, int i2, String str, Object obj) {
        if (this.val$callback != null) {
            this.val$callback.cb_createBtTask(i, i2, this.val$xzbDevice, XZBDevice.getCreateTaskErrorMsg(i), obj);
            super.cb_CreateBtDownloadTask(i, i2, str, obj);
        }
    }
}
