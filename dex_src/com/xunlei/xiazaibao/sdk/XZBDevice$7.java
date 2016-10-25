package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import com.xunlei.xiazaibao.sdk.entities.DownloadTasks;
import com.xunlei.xiazaibao.sdk.synctasks.XZBStartTask;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.List;

class XZBDevice$7 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ List val$taskInfoList;
    final /* synthetic */ Object val$userData;

    XZBDevice$7(XZBDevice xZBDevice, Object obj, String str, Object obj2, List list, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$taskInfoList = list;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        int i;
        if (this.val$taskInfoList == null || this.val$taskInfoList.isEmpty()) {
            fireCallback(new Object[]{Integer.valueOf(-10), Integer.valueOf(getTaskId()), null, XZBDevice.operateTaskErrorMsg(-10), this.val$userData});
            i = -10;
        } else {
            i = 0;
        }
        XZBStartTask xZBStartTask = new XZBStartTask(this.this$0.getPid());
        for (DownloadTaskInfo downloadTaskInfo : this.val$taskInfoList) {
            xZBStartTask.addDownloadTaskId(downloadTaskInfo.getId(), downloadTaskInfo.getState());
        }
        HttpResponse httpGet = xZBStartTask.httpGet();
        if (httpGet.getStatusCode() != 200) {
            fireCallback(new Object[]{Integer.valueOf(httpGet.getStatusCode()), Integer.valueOf(getTaskId()), null, XZBDevice.operateTaskErrorMsg(httpGet.getStatusCode()), this.val$userData});
            return;
        }
        String stringBody = httpGet.getStringBody();
        XZBLog.d(XZBDevice.access$300(), new StringBuilder("startTask response = ").append(stringBody).toString());
        DownloadTasks parseResult = XZBStartTask.parseResult(stringBody);
        if (parseResult == null) {
            fireCallback(new Object[]{Integer.valueOf(-11), Integer.valueOf(getTaskId()), null, XZBDevice.operateTaskErrorMsg(i), this.val$userData});
            return;
        }
        if (parseResult.getRtn() != 0) {
            fireCallback(new Object[]{Integer.valueOf(parseResult.getRtn()), Integer.valueOf(getTaskId()), null, XZBDevice.operateTaskErrorMsg(parseResult.getRtn()), this.val$userData});
            return;
        }
        fireCallback(new Object[]{Integer.valueOf(parseResult.getRtn()), Integer.valueOf(getTaskId()), parseResult.getTasks(), XZBDevice.operateTaskErrorMsg(parseResult.getRtn()), this.val$userData});
    }
}
