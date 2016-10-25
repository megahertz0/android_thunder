package com.xunlei.downloadprovider.download.create;

import android.net.Uri;
import com.xunlei.downloadprovider.app.ui.FileManageView.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

// compiled from: CreateBtTask.java
final class b implements c {
    final /* synthetic */ CreateBtTask a;

    b(CreateBtTask createBtTask) {
        this.a = createBtTask;
    }

    public final boolean a(String str) {
        if (CreateBtTask.e(this.a) != 2) {
            File file = new File(str);
            if (file.exists()) {
                String toString = Uri.fromFile(new File(str)).toString();
                if (CreateBtTask.e(this.a) == 0) {
                    DownloadBtFileExplorerActivity.startSelf(this.a, toString, XZBDevice.Pause, null);
                    CreateBtTask.a(file.getParent());
                } else {
                    DownloadBtFileExplorerActivity.startSelf(this.a, toString, XZBDevice.Stop, null);
                }
            } else {
                XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, this.a.getString(2131230909));
            }
        }
        return true;
    }
}
