package com.xunlei.tdlive;

import com.sina.weibo.sdk.constant.WBConstants;
import com.umeng.a;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.util.y;

// compiled from: WebBrowserActivity.java
class fk extends a {
    final /* synthetic */ WebBrowserActivity a;

    fk(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
        super();
    }

    public String a(String str, String str2) {
        JsonWrapper jsonWrapper = new JsonWrapper(str);
        int i = jsonWrapper.getInt("sharePlatform", 0);
        String string = jsonWrapper.getString(WBConstants.SDK_WEOYOU_SHARETITLE, a.d);
        String string2 = jsonWrapper.getString("shareText", a.d);
        String string3 = jsonWrapper.getString("shareImageUrl", a.d);
        String string4 = jsonWrapper.getString(WBConstants.SDK_WEOYOU_SHAREURL, a.d);
        SHARE_MEDIA share_media = SHARE_MEDIA.WEIXIN;
        if (i == 1) {
            share_media = SHARE_MEDIA.WEIXIN;
        }
        if (i == 2) {
            share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
        }
        if (i == 3) {
            share_media = SHARE_MEDIA.QZONE;
        }
        if (i == 4) {
            share_media = SHARE_MEDIA.SINA;
        }
        if (i == 5) {
            share_media = SHARE_MEDIA.QQ;
        }
        y.a(this.a, share_media, string3, string4, string, string2, new fl(this, str2));
        return null;
    }
}
