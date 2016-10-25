package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice$XZBDeviceCallback;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import java.util.List;

class XZBShouleiUtil$2 extends XZBDevice$XZBDeviceCallback {
    final /* synthetic */ XZBShouleiUtil this$0;
    final /* synthetic */ XZBDevice val$xzbDevice;

    XZBShouleiUtil$2(XZBShouleiUtil xZBShouleiUtil, XZBDevice xZBDevice) {
        this.this$0 = xZBShouleiUtil;
        this.val$xzbDevice = xZBDevice;
    }

    public void cb_QueryDownloadTaskList(int i, int i2, XZBDownloadTaskSet xZBDownloadTaskSet, String str, Object obj) {
        if (i == 0 && xZBDownloadTaskSet != null) {
            List tasks = xZBDownloadTaskSet.getTasks();
            if (tasks != null && !tasks.isEmpty()) {
                DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) tasks.get(0);
                if (downloadTaskInfo != null) {
                    this.val$xzbDevice.setLastCreateTime(downloadTaskInfo.getCreateTime());
                }
                super.cb_QueryDownloadTaskList(i, i2, xZBDownloadTaskSet, str, obj);
            }
        }
    }
}
