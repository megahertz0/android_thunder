package com.xunlei.downloadprovidershare;

import android.app.Activity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovidershare.a.a.a;
import com.xunlei.downloadprovidershare.b.d;
import com.xunlei.downloadprovidershare.c.c;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;

// compiled from: ShareHelper.java
final class f implements a {
    final /* synthetic */ Activity a;
    final /* synthetic */ d.a b;
    final /* synthetic */ ShareBean c;
    final /* synthetic */ d d;

    f(d dVar, Activity activity, d.a aVar, ShareBean shareBean) {
        this.d = dVar;
        this.a = activity;
        this.b = aVar;
        this.c = shareBean;
    }

    public final void a() {
        this.d.a(this.a, this.b, this.c, SHARE_MEDIA.WEIXIN);
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(SHARE_MEDIA.WEIXIN, this.c);
            d.a().a(this.c.j, new g(this));
            return;
        }
        com.xunlei.downloadprovidershare.c.d.b(SHARE_MEDIA.WEIXIN, this.a, this.c, this.d.n);
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(SHARE_MEDIA.WEIXIN, this.c);
        }
    }

    public final void b() {
        this.d.a(this.a, this.b, this.c, SHARE_MEDIA.SINA);
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(SHARE_MEDIA.SINA, this.c);
            d.a().a(this.c.j, new i(this));
            return;
        }
        SHARE_MEDIA share_media = SHARE_MEDIA.SINA;
        c.a(this.a, this.c, this.d.n);
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(SHARE_MEDIA.SINA, this.c);
        }
    }

    public final void c() {
        this.d.a(this.a, this.b, this.c, SHARE_MEDIA.QZONE);
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(SHARE_MEDIA.QZONE, this.c);
            d.a().a(this.c.j, new k(this));
            return;
        }
        this.d.k.b(SHARE_MEDIA.QZONE, this.a, this.c, this.d.n);
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(SHARE_MEDIA.QZONE, this.c);
        }
    }

    public final void d() {
        this.d.a(this.a, this.b, this.c, SHARE_MEDIA.QQ);
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(SHARE_MEDIA.QQ, this.c);
            d.a().a(this.c.j, new m(this));
            return;
        }
        this.d.k.b(SHARE_MEDIA.QQ, this.a, this.c, this.d.n);
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(SHARE_MEDIA.QQ, this.c);
        }
    }

    public final void e() {
        this.d.a(this.a, this.b, this.c, SHARE_MEDIA.WEIXIN_CIRCLE);
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(SHARE_MEDIA.WEIXIN_CIRCLE, this.c);
            d.a().a(this.c.j, new o(this));
            return;
        }
        com.xunlei.downloadprovidershare.c.d.b(SHARE_MEDIA.WEIXIN_CIRCLE, this.a, this.c, this.d.n);
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(SHARE_MEDIA.WEIXIN_CIRCLE, this.c);
        }
    }

    public final void f() {
        this.d.a(this.a, this.b, this.c, null);
        this.c.m = OperationType.CopyUrl;
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(null, this.c);
            d.a().a(this.c.j, new q(this));
            return;
        }
        d.a(this.d, this.c);
        d.a(this.d);
    }

    public final void g() {
        this.d.a(this.a, this.b, this.c, null);
        this.c.m = OperationType.SystemShare;
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(null, this.c);
            d.a().a(this.c.j, new v(this));
            return;
        }
        d.a(this.d, this.a, this.c);
        d.a(this.d);
    }

    public final void h() {
        this.d.a(this.a, this.b, this.c, null);
        this.c.m = OperationType.Qr;
        if (this.c.l > 0) {
            this.d.a(this.a, com.umeng.a.d, true);
            this.c.j.k = ba.a(null, this.c);
            d.a().a(this.c.j, new z(this));
            return;
        }
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(null, this.c);
        }
        d.a(this.d);
    }

    public final void i() {
        this.d.a(this.a, this.b, this.c, null);
        this.c.m = OperationType.Upload;
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(null, this.c);
        }
    }

    public final void j() {
        this.d.a(this.a, this.b, this.c, null);
        this.c.m = OperationType.Download;
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(null, this.c);
        }
        d.a(this.d);
    }

    public final void k() {
        this.d.a(this.a, this.b, this.c, null);
        this.c.m = OperationType.Accuse;
        if (this.d.i != null) {
            this.d.i.onShareTargetClicked(null, this.c);
        }
        d.a(this.d);
    }
}
