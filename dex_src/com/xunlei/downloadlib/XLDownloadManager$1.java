package com.xunlei.downloadlib;

import com.xunlei.downloadlib.XLUtil.GUID_TYPE;
import com.xunlei.downloadlib.XLUtil.GuidInfo;
import java.util.TimerTask;

class XLDownloadManager$1 extends TimerTask {
    final /* synthetic */ XLDownloadManager this$0;

    XLDownloadManager$1(XLDownloadManager xLDownloadManager) {
        this.this$0 = xLDownloadManager;
    }

    public void run() {
        if (XLDownloadManager.access$200(this.this$0) < 5) {
            XLDownloadManager.access$208(this.this$0);
            GuidInfo guidInfo = new GuidInfo();
            guidInfo = XLUtil.generateGuid(XLDownloadManager.access$300(this.this$0));
            if (guidInfo.mType == GUID_TYPE.ALL) {
                XLDownloadManager.access$400(this.this$0);
            }
            if (guidInfo.mType != GUID_TYPE.DEFAULT) {
                XLDownloadManager.access$000(this.this$0).setLocalProperty("Guid", guidInfo.mGuid);
                return;
            }
            return;
        }
        XLDownloadManager.access$400(this.this$0);
    }
}
