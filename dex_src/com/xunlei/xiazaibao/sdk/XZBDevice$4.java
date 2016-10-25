package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateBtTask;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateBtTask.CreateBtTaskInfo;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;

class XZBDevice$4 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ long[] val$indexs;
    final /* synthetic */ String val$infohash;
    final /* synthetic */ String val$name;
    final /* synthetic */ Object val$userData;

    XZBDevice$4(XZBDevice xZBDevice, Object obj, String str, Object obj2, String str2, String str3, long[] jArr, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$name = str2;
        this.val$infohash = str3;
        this.val$indexs = jArr;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        CreateBtTaskInfo createBtTaskInfo = new CreateBtTaskInfo();
        createBtTaskInfo.name = this.val$name;
        createBtTaskInfo.infohash = this.val$infohash;
        createBtTaskInfo.path = this.this$0.getDownloadPath();
        createBtTaskInfo.btSub = new ArrayList();
        long[] jArr = this.val$indexs;
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            createBtTaskInfo.btSub.add(Integer.valueOf(Long.valueOf(jArr[i]).intValue()));
        }
        HttpResponse httpPost = new XZBCreateBtTask(this.this$0.getPid(), createBtTaskInfo).httpPost();
        if (httpPost.getStatusCode() != 200) {
            XZBLog.d(XZBDevice.access$300(), new StringBuilder("createBtDownloadTask error errorcode = ").append(httpPost.getStatusCode()).append(" userData = ").append(this.val$userData).toString());
            fireCallback(new Object[]{Integer.valueOf(httpPost.getStatusCode()), Integer.valueOf(getTaskId()), "create bt request error"});
            return;
        }
        XZBLog.d(XZBDevice.access$300(), new StringBuilder("createBtDownloadTask response = ").append(httpPost.getStringBody()).append(" userData = ").append(this.val$userData).toString());
        if (XZBCreateBtTask.parseResult(httpPost.getStringBody()).rtn != 0) {
            fireCallback(new Object[]{Integer.valueOf(XZBCreateBtTask.parseResult(httpPost.getStringBody()).rtn), Integer.valueOf(getTaskId()), new StringBuilder("create bt request error ").append(XZBCreateBtTask.parseResult(httpPost.getStringBody()).msg).toString(), this.val$userData});
            return;
        }
        fireCallback(new Object[]{Integer.valueOf(0), Integer.valueOf(getTaskId()), "create bt request success ", this.val$userData});
    }
}
