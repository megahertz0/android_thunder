package com.xunlei.downloadprovider.web.base.core;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;

// compiled from: DefaultJsInterface.java
final class i implements a {
    final /* synthetic */ String a;
    final /* synthetic */ DefaultJsInterface b;

    i(DefaultJsInterface defaultJsInterface, String str) {
        this.b = defaultJsInterface;
        this.a = str;
    }

    public final void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        if (share_media != null) {
            this.b.callbackWithOneParam(this.a, "result", Integer.valueOf(i));
        }
    }

    public final void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
    }
}
