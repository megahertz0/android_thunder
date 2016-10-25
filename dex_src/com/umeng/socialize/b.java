package com.umeng.socialize;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

// compiled from: ShareAction.java
class b implements ShareBoardlistener {
    final /* synthetic */ ShareAction a;

    b(ShareAction shareAction) {
        this.a = shareAction;
    }

    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        this.a.setPlatform(share_media);
        this.a.share();
    }
}
