package com.xunlei.xiazaibao.sdk;

import android.text.TextUtils;
import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.entities.DownloadSettingResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBDownloadSetting;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;

class XZBDevice$5 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ Object val$userData;

    XZBDevice$5(XZBDevice xZBDevice, Object obj, String str, Object obj2, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        XZBLog.d(XZBDevice.access$300(), new StringBuilder("downloadSettingTask ENTER ").append(this.this$0.getDeviceId()).toString());
        HttpResponse httpGet = new XZBDownloadSetting(this.this$0.getPid()).httpGet();
        if (httpGet.getStatusCode() != 200) {
            XZBLog.d(XZBDevice.access$300(), new StringBuilder("downloadSettingTask error errorcode = ").append(httpGet.getStatusCode()).append(" userData = ").append(this.val$userData).toString());
            fireCallback(new Object[]{Integer.valueOf(httpGet.getStatusCode()), Integer.valueOf(getTaskId()), "settings request error", this.val$userData});
            return;
        }
        XZBLog.d(XZBDevice.access$300(), this.this$0.getDeviceId() + " downloadSettingTask response = " + httpGet.getStringBody() + " userData = " + this.val$userData);
        DownloadSettingResponse parseResult = XZBDownloadSetting.parseResult(httpGet.getStringBody());
        this.this$0.setDownloadPath(TextUtils.isEmpty(parseResult.defaultPath) ? "C:/TDDOWNLOAD/" : parseResult.defaultPath);
        fireCallback(new Object[]{Integer.valueOf(0), Integer.valueOf(getTaskId()), "settings request success", this.val$userData});
    }
}
