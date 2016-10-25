package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.text.TextUtils;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import com.xunlei.xiazaibao.shoulei.XZBShouleiCallback;
import java.util.List;

// compiled from: XZBTaskControl.java
final class as extends XZBShouleiCallback {
    final /* synthetic */ ar a;

    as(ar arVar) {
        this.a = arVar;
    }

    public final void cb_DeleteTask(int i, int i2, XZBDevice xZBDevice, List<DownloadTaskResult> list, String str, Object obj) {
        if (this.a.a != null) {
            this.a.a.a(i, list);
        }
        if (am.b(this.a.e) != null) {
            am.b(this.a.e).dismiss();
        }
        if (am.a(this.a.e) != null) {
            if (list == null || list.isEmpty()) {
                XLToast.a(am.a(this.a.e), XLToastType.XLTOAST_TYPE_ALARM, "\u5220\u9664\u5931\u8d25");
                return;
            }
            DownloadTaskResult downloadTaskResult;
            int result;
            if (list.size() == 1) {
                downloadTaskResult = (DownloadTaskResult) list.get(0);
                if (downloadTaskResult != null) {
                    result = downloadTaskResult.getResult();
                    if (result == 0 || result == 2 || result == 102434) {
                        XLToast.a(am.a(this.a.e), XLToastType.XLTOAST_TYPE_SUC, "\u5220\u9664\u6210\u529f");
                    } else {
                        XLToast.a(am.a(this.a.e), XLToastType.XLTOAST_TYPE_ALARM, "\u5220\u9664\u5931\u8d25");
                    }
                } else {
                    XLToast.a(am.a(this.a.e), XLToastType.XLTOAST_TYPE_ALARM, "\u5220\u9664\u5931\u8d25");
                }
            } else {
                int i3 = 0;
                int i4 = 0;
                for (DownloadTaskResult downloadTaskResult2 : list) {
                    if (downloadTaskResult2 != null) {
                        result = downloadTaskResult2.getResult();
                        if (result == 0 || result == 2 || result == 102434) {
                            i4++;
                        } else {
                            i3++;
                        }
                    }
                }
                String str2 = BuildConfig.VERSION_NAME;
                if (i4 > 0) {
                    str2 = str2 + i4 + "\u4e2a\u4efb\u52a1\u5220\u9664\u6210\u529f";
                }
                if (i3 > 0) {
                    str2 = str2 + i3 + "\u4e2a\u4efb\u52a1\u5220\u9664\u5931\u8d25";
                }
                if (!TextUtils.isEmpty(str2)) {
                    if (i3 > 0) {
                        XLToast.a(am.a(this.a.e), XLToastType.XLTOAST_TYPE_ALARM, str2);
                    } else {
                        XLToast.a(am.a(this.a.e), XLToastType.XLTOAST_TYPE_SUC, str2);
                    }
                }
            }
            super.cb_DeleteTask(i, i2, xZBDevice, list, str, obj);
        }
    }
}
