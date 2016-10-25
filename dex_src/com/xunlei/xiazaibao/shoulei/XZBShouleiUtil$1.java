package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager$XZBDeviceMgrCallback;

class XZBShouleiUtil$1 extends XZBDeviceManager$XZBDeviceMgrCallback {
    final /* synthetic */ XZBShouleiUtil this$0;
    final /* synthetic */ XZBShouleiCallback val$callback;

    XZBShouleiUtil$1(XZBShouleiUtil xZBShouleiUtil, XZBShouleiCallback xZBShouleiCallback) {
        this.this$0 = xZBShouleiUtil;
        this.val$callback = xZBShouleiCallback;
    }

    public void cb_QueryDeviceList(int i, int i2, XZBDevice[] xZBDeviceArr, String str, Object obj) {
        if (xZBDeviceArr != null && xZBDeviceArr.length > 0) {
            XZBDevice access$200 = XZBShouleiUtil.access$200(this.this$0);
            if (access$200 != null) {
                XZBShouleiUtil.access$300(this.this$0, access$200);
            } else {
                XZBShouleiUtil.access$300(this.this$0, xZBDeviceArr[0]);
            }
        } else if (i == 0) {
            XZBShouleiUtil.access$300(this.this$0, null);
        }
        XZBShouleiUtil.access$400(this.this$0);
        this.val$callback.cb_UpdateDeviceList(i, i2, xZBDeviceArr, XZBDeviceManager.queryDeviceErrorMsg(i), obj);
    }
}
