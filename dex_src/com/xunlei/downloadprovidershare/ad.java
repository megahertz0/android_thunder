package com.xunlei.downloadprovidershare;

import android.app.Activity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.b.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;

// compiled from: ShareHelper.java
final class ad implements a {
    final /* synthetic */ Activity a;
    final /* synthetic */ ShareBean b;
    final /* synthetic */ SHARE_MEDIA c;
    final /* synthetic */ d.a d;
    final /* synthetic */ d e;

    ad(d dVar, Activity activity, ShareBean shareBean, SHARE_MEDIA share_media, d.a aVar) {
        this.e = dVar;
        this.a = activity;
        this.b = shareBean;
        this.c = share_media;
        this.d = aVar;
    }

    public final void a(String str) {
        this.a.runOnUiThread(new ae(this, str));
    }
}
