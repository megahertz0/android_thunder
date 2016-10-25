package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.XZBDevice.UrlData;
import com.xunlei.xiazaibao.sdk.base.AsynTask;

class XZBDevice$2 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ UrlData[] val$urls;
    final /* synthetic */ Object val$userData;

    XZBDevice$2(XZBDevice xZBDevice, Object obj, String str, Object obj2, UrlData[] urlDataArr, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$urls = urlDataArr;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        new XZBDevice$TaskCreator().createTask(this.this$0, this.val$urls);
        fireCallback(new Object[]{Integer.valueOf(r0.result), Integer.valueOf(getTaskId()), r0.urlRetDatas, r0.message, this.val$userData});
    }
}
