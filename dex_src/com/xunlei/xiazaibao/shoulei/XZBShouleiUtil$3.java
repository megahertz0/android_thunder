package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice$XZBDeviceCallback;
import com.xunlei.xiazaibao.sdk.XZBDevice.UrlData;

class XZBShouleiUtil$3 extends XZBDevice$XZBDeviceCallback {
    final /* synthetic */ XZBShouleiUtil this$0;
    final /* synthetic */ XZBShouleiCallback val$callback;
    final /* synthetic */ XZBDevice val$xzbDevice;

    XZBShouleiUtil$3(XZBShouleiUtil xZBShouleiUtil, XZBShouleiCallback xZBShouleiCallback, XZBDevice xZBDevice) {
        this.this$0 = xZBShouleiUtil;
        this.val$callback = xZBShouleiCallback;
        this.val$xzbDevice = xZBDevice;
    }

    public void cb_CreateTask(int i, int i2, UrlData[] urlDataArr, String str, Object obj) {
        if (urlDataArr == null || urlDataArr.length == 0) {
            this.val$callback.cb_CreateTask(-10, i2, urlDataArr, XZBDevice.getCreateTaskErrorMsg(-10), obj);
            return;
        }
        if (i == 0 && XZBShouleiUtil.access$500(this.this$0) != null) {
            XZBShouleiUtil.access$500(this.this$0).postDelayed(new Runnable() {
                public void run() {
                    XZBShouleiUtil.access$600(XZBShouleiUtil$3.this.this$0, XZBShouleiUtil$3.this.val$xzbDevice, "createtask");
                }
            }, 3000);
        }
        this.val$callback.cb_CreateTask(i, i2, urlDataArr, XZBDevice.getCreateTaskErrorMsg(i), obj);
        super.cb_CreateTask(i, i2, urlDataArr, str, obj);
    }
}
