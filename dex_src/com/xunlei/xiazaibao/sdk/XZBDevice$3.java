package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.entities.QueryDownloadListResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBDownloadLogin;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetRemoteDownloadList;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;

class XZBDevice$3 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ int val$number;
    final /* synthetic */ int val$position;
    final /* synthetic */ int val$tasktype;
    final /* synthetic */ Object val$userData;

    XZBDevice$3(XZBDevice xZBDevice, Object obj, String str, Object obj2, int i, int i2, int i3, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$position = i;
        this.val$number = i2;
        this.val$tasktype = i3;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        if (!XZBDevice.access$100(this.this$0)) {
            HttpResponse httpGet = new XZBDownloadLogin(this.this$0.getPid()).httpGet();
            XZBDevice.access$200(this.this$0, true);
            XZBLog.d(XZBDevice.access$300(), new StringBuilder("XZBDownloadLogin ").append(httpGet.getStringBody()).toString());
        }
        XZBGetRemoteDownloadList xZBGetRemoteDownloadList = new XZBGetRemoteDownloadList(this.this$0.getPid(), this.val$position, this.val$number, this.val$tasktype, 1);
        HttpResponse httpGet2 = xZBGetRemoteDownloadList.httpGet();
        if (httpGet2.getStatusCode() != 200) {
            XZBLog.d(XZBDevice.access$300(), new StringBuilder("queryDownloadTaskList error errorcode = ").append(httpGet2.getStatusCode()).append(" userData = ").append(this.val$userData).toString());
            fireCallback(new Object[]{Integer.valueOf(httpGet2.getStatusCode()), Integer.valueOf(getTaskId()), null, "query download list error", this.val$userData});
            return;
        }
        QueryDownloadListResponse parseResult = XZBGetRemoteDownloadList.parseResult(xZBGetRemoteDownloadList);
        if (parseResult.getRtn() != 0) {
            fireCallback(new Object[]{Integer.valueOf(parseResult.getRtn()), Integer.valueOf(getTaskId()), null, new StringBuilder("query download list error ").append(parseResult.msg).toString(), this.val$userData});
            return;
        }
        XZBDownloadTaskSet access$400 = XZBDevice.access$400(this.this$0, parseResult);
        fireCallback(new Object[]{Integer.valueOf(0), Integer.valueOf(getTaskId()), access$400, "query download list success", this.val$userData});
    }
}
