package com.xunlei.downloadprovidershare;

import com.taobao.accs.net.a;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ShareHelper.java
final class af implements UMShareListener {
    final /* synthetic */ d a;

    af(d dVar) {
        this.a = dVar;
    }

    public final void onResult(SHARE_MEDIA share_media) {
        this.a.a(0, (int) Impl.STATUS_SUCCESS);
    }

    public final void onError(SHARE_MEDIA share_media, Throwable th) {
        this.a.a(1, 0);
    }

    public final void onCancel(SHARE_MEDIA share_media) {
        this.a.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, (int) a.ACCS_RECEIVE_TIMEOUT);
    }
}
