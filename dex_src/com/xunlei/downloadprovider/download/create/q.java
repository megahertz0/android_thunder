package com.xunlei.downloadprovider.download.create;

import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.xllib.a.b;

// compiled from: DownloadBtFileExplorerActivity.java
final class q implements OnClickListener {
    final /* synthetic */ DownloadBtFileExplorerActivity a;

    q(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity) {
        this.a = downloadBtFileExplorerActivity;
    }

    public final void onClick(View view) {
        if (this.a.mSelected.size() == 0) {
            XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u6587\u4ef6");
        } else if (!b.a(this.a)) {
            XLToast.a(this.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231758));
            this.a.finish();
            DownloadCenterActivity.a(this.a, 0, a.d);
        } else if (b.g(this.a)) {
            BrothersApplication.a(true);
            this.a.showResumeTaskAlarmDlg();
        } else {
            this.a.startDownload();
        }
    }
}
