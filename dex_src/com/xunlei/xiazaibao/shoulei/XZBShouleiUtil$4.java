package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice$XZBDeviceCallback;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;

class XZBShouleiUtil$4 extends XZBDevice$XZBDeviceCallback {
    final /* synthetic */ XZBShouleiUtil this$0;
    final /* synthetic */ XZBShouleiCallback val$callback;
    final /* synthetic */ XZBDevice val$xzbDevice;

    XZBShouleiUtil$4(XZBShouleiUtil xZBShouleiUtil, XZBShouleiCallback xZBShouleiCallback, XZBDevice xZBDevice) {
        this.this$0 = xZBShouleiUtil;
        this.val$callback = xZBShouleiCallback;
        this.val$xzbDevice = xZBDevice;
    }

    public void cb_QueryDownloadTaskList(int i, int i2, XZBDownloadTaskSet xZBDownloadTaskSet, String str, Object obj) {
        this.val$callback.cb_QueryTaskList(i, i2, this.val$xzbDevice, xZBDownloadTaskSet, XZBDevice.getQueryTaskErrorMsg(i), obj);
        super.cb_QueryDownloadTaskList(i, i2, xZBDownloadTaskSet, str, obj);
    }
}
