package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.entities.GetDeviceListResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetDeviceList;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;

class XZBDeviceManager$2 extends AsynTask {
    final /* synthetic */ XZBDeviceManager this$0;
    final /* synthetic */ Object val$userData;

    XZBDeviceManager$2(XZBDeviceManager xZBDeviceManager, Object obj, String str, Object obj2, Object obj3) {
        this.this$0 = xZBDeviceManager;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        XZBGetDeviceList xZBGetDeviceList = new XZBGetDeviceList();
        HttpResponse httpGet = xZBGetDeviceList.httpGet();
        if (httpGet.getStatusCode() == 200) {
            GetDeviceListResponse parseResult = xZBGetDeviceList.parseResult(httpGet.getStringBody());
            XZBLog.d(XZBDeviceManager.access$000(), new StringBuilder("queryDeviceList response = ").append(httpGet.getStringBody()).append(" userData = ").append(this.val$userData).toString());
            XZBDeviceManager.access$100(this.this$0, parseResult, this.val$userData);
            fireCallback(new Object[]{Integer.valueOf(parseResult.ret), Integer.valueOf(getTaskId()), XZBDeviceManager.access$200(this.this$0).toArray(new XZBDevice[XZBDeviceManager.access$200(this.this$0).size()]), "request success", getUserdata()});
            return;
        }
        XZBLog.d(XZBDeviceManager.access$000(), new StringBuilder("queryDeviceList error errorcode = ").append(httpGet.getStatusCode()).append(" userData = ").append(this.val$userData).toString());
        fireCallback(new Object[]{Integer.valueOf(httpGet.getStatusCode()), Integer.valueOf(getTaskId()), null, "request error", getUserdata()});
    }
}
