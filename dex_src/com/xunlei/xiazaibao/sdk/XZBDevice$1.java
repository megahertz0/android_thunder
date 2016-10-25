package com.xunlei.xiazaibao.sdk;

import com.umeng.common.inter.ITagManager;
import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetUsbInfo;

class XZBDevice$1 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ boolean val$forceupdate;
    final /* synthetic */ Object val$userData;

    XZBDevice$1(XZBDevice xZBDevice, Object obj, String str, Object obj2, boolean z, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$forceupdate = z;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        if (this.val$forceupdate) {
            XZBGetUsbInfo xZBGetUsbInfo = new XZBGetUsbInfo(this.this$0.getDeviceId(), this.this$0.getTransitIp(), this.this$0.getTransitPort());
            HttpResponse httpGet = xZBGetUsbInfo.httpGet();
            if (httpGet.getStatusCode() == 200) {
                XZBDevice.access$000(this.this$0, xZBGetUsbInfo.parseResult(httpGet.getStringBody()));
                fireCallback(new Object[]{Integer.valueOf(r0.rtn), Integer.valueOf(getTaskId()), this.this$0.getStorage(), "request success", this.val$userData});
                return;
            }
            fireCallback(new Object[]{Integer.valueOf(httpGet.getStatusCode()), Integer.valueOf(getTaskId()), this.this$0.getStorage(), "request fail", this.val$userData});
            return;
        }
        fireCallback(new Object[]{Integer.valueOf(0), Integer.valueOf(getTaskId()), this.this$0.getStorage(), ITagManager.SUCCESS, this.val$userData});
    }
}
