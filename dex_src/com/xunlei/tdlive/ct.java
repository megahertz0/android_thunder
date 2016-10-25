package com.xunlei.tdlive;

import android.app.Activity;
import android.view.View;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.tdlive.util.y.a;

// compiled from: LivePublishDialog.java
class ct implements a {
    final /* synthetic */ SHARE_MEDIA a;
    final /* synthetic */ Activity b;
    final /* synthetic */ View c;
    final /* synthetic */ co d;

    ct(co coVar, SHARE_MEDIA share_media, Activity activity, View view) {
        this.d = coVar;
        this.a = share_media;
        this.b = activity;
        this.c = view;
    }

    public void a(int i, String str) {
        if (this.a == SHARE_MEDIA.SINA) {
            co.a(this.d, co.a(this.d) + "weibo|");
        } else if (this.a == SHARE_MEDIA.WEIXIN) {
            co.a(this.d, co.a(this.d) + "webchat|");
        } else if (this.a == SHARE_MEDIA.WEIXIN_CIRCLE) {
            co.a(this.d, co.a(this.d) + "frends|");
        } else if (this.a == SHARE_MEDIA.QZONE) {
            co.a(this.d, co.a(this.d) + "qqzone|");
        } else if (this.a == SHARE_MEDIA.QQ) {
            co.a(this.d, co.a(this.d) + "qqfrends|");
        }
        co.a(this.d, this.b, this.c, str);
    }
}
