package com.xunlei.downloadprovider.download.a;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.vod.VodUtil;

// compiled from: DownloadCenterControl.java
final class e extends a$a {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    e(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a$b com_xunlei_downloadprovider_download_a_a_b = (a$b) obj;
        a.e = null;
        String str = com_xunlei_downloadprovider_download_a_a_b.e;
        if (TextUtils.isEmpty(str)) {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u6682\u65f6\u65e0\u6cd5\u8fb9\u4e0b\u8fb9\u64ad\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            return;
        }
        d.a();
        d.d(com_xunlei_downloadprovider_download_a_a_b.c);
        new StringBuilder("DownloadingPlay: ").append(com_xunlei_downloadprovider_download_a_a_b.a).append("\nplayUrl = ").append(str);
        VodUtil.a();
        VodUtil.a(this.a, str, com_xunlei_downloadprovider_download_a_a_b.b, com_xunlei_downloadprovider_download_a_a_b.c, com_xunlei_downloadprovider_download_a_a_b.d, com_xunlei_downloadprovider_download_a_a_b.f, com_xunlei_downloadprovider_download_a_a_b.g, this.b);
        com.xunlei.downloadprovider.service.downloads.task.b.d.a().c(com_xunlei_downloadprovider_download_a_a_b.c);
    }

    protected final void onCancelled() {
        super.onCancelled();
        a.e = null;
    }
}
