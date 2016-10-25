package com.xunlei.downloadprovider.web.base.model;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;

// compiled from: ShortMovieDetailDataLoader.java
final class j implements a {
    final /* synthetic */ d a;

    j(d dVar) {
        this.a = dVar;
    }

    public final void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        if (this.a.e != null) {
            this.a.e.a(i, share_media);
        }
    }

    public final void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
    }
}
