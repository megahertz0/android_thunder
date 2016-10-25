package com.xunlei.downloadprovidershare;

import android.app.Activity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.a.b.a;
import com.xunlei.downloadprovidershare.b.d;
import com.xunlei.downloadprovidershare.c.c;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;

// compiled from: ShareHelper.java
public final class ag implements a {
    final /* synthetic */ Activity a;
    final /* synthetic */ d.a b;
    final /* synthetic */ ShareBean c;
    final /* synthetic */ d d;

    public ag(d dVar, Activity activity, d.a aVar, ShareBean shareBean) {
        this.d = dVar;
        this.a = activity;
        this.b = aVar;
        this.c = shareBean;
    }

    public final void a() {
        d.a(this.d, this.a, this.b, this.c, SHARE_MEDIA.WEIXIN);
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(SHARE_MEDIA.WEIXIN, this.c);
            d.a().a(this.c.j, new ah(this));
            return;
        }
        com.xunlei.downloadprovidershare.c.d.b(SHARE_MEDIA.WEIXIN, this.a, this.c, d.b(this.d));
        if (d.c(this.d) != null) {
            d.c(this.d).onShareTargetClicked(SHARE_MEDIA.WEIXIN, this.c);
        }
    }

    public final void b() {
        d.a(this.d, this.a, this.b, this.c, SHARE_MEDIA.SINA);
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(SHARE_MEDIA.SINA, this.c);
            d.a().a(this.c.j, new aj(this));
            return;
        }
        SHARE_MEDIA share_media = SHARE_MEDIA.SINA;
        c.a(this.a, this.c, d.b(this.d));
        if (d.c(this.d) != null) {
            d.c(this.d).onShareTargetClicked(SHARE_MEDIA.SINA, this.c);
        }
    }

    public final void c() {
        d.a(this.d, this.a, this.b, this.c, SHARE_MEDIA.QZONE);
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(SHARE_MEDIA.QZONE, this.c);
            d.a().a(this.c.j, new al(this));
            return;
        }
        d.d(this.d).b(SHARE_MEDIA.QZONE, this.a, this.c, d.b(this.d));
        if (d.c(this.d) != null) {
            d.c(this.d).onShareTargetClicked(SHARE_MEDIA.QZONE, this.c);
        }
    }

    public final void d() {
        d.a(this.d, this.a, this.b, this.c, SHARE_MEDIA.QQ);
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(SHARE_MEDIA.QQ, this.c);
            d.a().a(this.c.j, new an(this));
            return;
        }
        d.d(this.d).b(SHARE_MEDIA.QQ, this.a, this.c, d.b(this.d));
        if (d.c(this.d) != null) {
            d.c(this.d).onShareTargetClicked(SHARE_MEDIA.QQ, this.c);
        }
    }

    public final void e() {
        d.a(this.d, this.a, this.b, this.c, SHARE_MEDIA.WEIXIN_CIRCLE);
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(SHARE_MEDIA.WEIXIN_CIRCLE, this.c);
            d.a().a(this.c.j, new ap(this));
            return;
        }
        com.xunlei.downloadprovidershare.c.d.b(SHARE_MEDIA.WEIXIN_CIRCLE, this.a, this.c, d.b(this.d));
        if (d.c(this.d) != null) {
            d.c(this.d).onShareTargetClicked(SHARE_MEDIA.WEIXIN_CIRCLE, this.c);
        }
    }

    public final void f() {
        d.a(this.d, this.a, this.b, this.c, null);
        this.c.m = OperationType.CopyUrl;
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(null, this.c);
            d.a().a(this.c.j, new ar(this));
            return;
        }
        d.a(this.d, this.c);
        d.a(this.d);
    }

    public final void g() {
        d.a(this.d, this.a, this.b, this.c, null);
        this.c.m = OperationType.SystemShare;
        if (this.c.l > 0) {
            d.a(this.d, this.a, com.umeng.a.d);
            this.c.j.k = ba.a(null, this.c);
            d.a().a(this.c.j, new av(this));
            return;
        }
        d.a(this.d, this.a, this.c);
        d.a(this.d);
    }
}
