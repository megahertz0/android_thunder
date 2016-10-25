package com.umeng.socialize;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

// compiled from: ShareAction.java
class c implements ShareBoardlistener {
    final /* synthetic */ ShareAction a;

    c(ShareAction shareAction) {
        this.a = shareAction;
    }

    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        int indexOf = this.a.g.indexOf(share_media);
        int size = this.a.i.size();
        if (size != 0) {
            ShareContent shareContent;
            if (indexOf < size) {
                shareContent = (ShareContent) this.a.i.get(indexOf);
            } else {
                shareContent = (ShareContent) this.a.i.get(size - 1);
            }
            this.a.a = shareContent;
        }
        size = this.a.j.size();
        if (size != 0) {
            if (indexOf < size) {
                this.a.d = (UMShareListener) this.a.j.get(indexOf);
            } else {
                this.a.d = (UMShareListener) this.a.j.get(size - 1);
            }
        }
        this.a.setPlatform(share_media);
        this.a.share();
    }
}
