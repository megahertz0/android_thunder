package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.XZBWebviewActivity.DownloadTaskListJsInterface;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XZBWebviewActivity.java
final class bp implements Runnable {
    final /* synthetic */ DownloadTaskListJsInterface a;

    bp(DownloadTaskListJsInterface downloadTaskListJsInterface) {
        this.a = downloadTaskListJsInterface;
    }

    public final void run() {
        LoginHelper.a();
        if (LoginHelper.c()) {
            XLToast.a(this.a.this$0, XLToastType.XLTOAST_TYPE_ALARM, "\u5df2\u767b\u5f55\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u8be5\u9875\u9762");
            return;
        }
        XZBWebviewActivity.a(this.a.this$0, true);
        LoginHelper.a().a(this.a.this$0, new bq(this), SimpleLog.LOG_LEVEL_DEBUG);
    }
}
