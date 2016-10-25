package com.xunlei.downloadprovider.web.base.model;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.p;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.e;
import com.xunlei.downloadprovider.c.a.f;
import com.xunlei.downloadprovider.c.a.h;
import com.xunlei.downloadprovider.c.a.i;
import com.xunlei.downloadprovider.c.a.k;
import com.xunlei.downloadprovider.c.l;
import com.xunlei.downloadprovider.c.m;
import com.xunlei.downloadprovider.c.n;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.android.spdy.SpdyAgent;
import org.json.JSONObject;

// compiled from: ShortMovieDetailDataLoader.java
public final class d {
    public final com.xunlei.downloadprovider.c.a a;
    final a b;
    public final f c;
    public final ExecutorService d;
    public a e;
    public final Handler f;
    u g;
    public ArrayList<com.xunlei.downloadprovider.web.base.model.a.a> h;
    ArrayList<com.xunlei.downloadprovider.web.base.model.a.a> i;
    private final String j;
    private final p k;
    private final Context l;
    private final d m;
    private final c n;
    private final e o;
    private final com.xunlei.downloadprovider.a.h.a p;

    // compiled from: ShortMovieDetailDataLoader.java
    public static interface a {
        void a(int i, SHARE_MEDIA share_media);

        void a(int i, f fVar);

        void a(int i, String str);

        void a(long j);

        void a(c cVar);

        void a(f fVar);

        void a(String str, u uVar);

        void a(List<u> list);

        void a(boolean z, Message message);

        void b(c cVar);

        void b(f fVar);

        void c();
    }

    public d(Context context) {
        this.j = getClass().getSimpleName();
        this.p = new e(this);
        this.f = new b(this.p);
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.k = com.xunlei.downloadprovider.j.a.c();
        this.a = new com.xunlei.downloadprovider.c.a();
        this.a.b = this.k;
        this.a.d = 20;
        this.b = new a(context);
        this.m = new d(this, (byte) 0);
        this.c = new f(this, (byte) 0);
        this.n = new c(this, (byte) 0);
        this.o = new e(this, (byte) 0);
        this.d = Executors.newFixedThreadPool(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.l = context;
    }

    public final void a(u uVar) {
        if (this.g == null || uVar == null || !TextUtils.equals(this.g.g, uVar.g)) {
            this.g = uVar;
            e eVar = new e();
            eVar.a = 1;
            eVar.b = uVar.g;
            eVar.c = uVar.a;
            this.a.a(eVar);
        }
    }

    public final void a() {
        this.m.a = this.g.g;
        this.d.submit(this.m);
    }

    public final void a(String str) {
        if (this.e != null) {
            Request aVar = new com.xunlei.downloadprovidercommon.b.a.a(new StringBuilder("http://api-shoulei-ssl.xunlei.com/ivideo/info?peerid=").append(com.xunlei.downloadprovider.a.b.d()).append("&movieid=").append(str).append("&time=").append(System.currentTimeMillis()).toString(), new k(this), new l(this), (byte) 0);
            aVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
            aVar.setTag(this.j);
            this.k.a(aVar);
        }
    }

    public final void b(String str) {
        String str2 = String.format("http://api-shoulei-ssl.xunlei.com/ivideo/relation?movieid=%s&size=%d&offset=%d", new Object[]{str, Integer.valueOf(R.styleable.Toolbar_navigationIcon), Integer.valueOf(0)}) + "&time=" + System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(5);
        Request aVar = new com.xunlei.downloadprovidercommon.b.a.a(str2, new m(this), new n(this), (byte) 0);
        aVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
        aVar.setTag(this.j);
        this.k.a(aVar);
    }

    public final void b() {
        this.a.a(new o(this), "new", "refresh", 0);
    }

    public final void c() {
        this.a.a(new q(this));
    }

    public final void a(String str, String str2, c cVar) {
        long j;
        long j2 = -1;
        if (cVar == null) {
            j = -1;
        } else {
            j = cVar.a;
        }
        if (cVar != null) {
            j2 = cVar.i;
        }
        com.xunlei.downloadprovider.c.a aVar = this.a;
        r rVar = new r(this, str, cVar);
        if (aVar.c == null) {
            throw new IllegalStateException("no comment resource attached");
        }
        k kVar = new k();
        kVar.c = aVar.c.c;
        kVar.a = aVar.c.b;
        kVar.b = aVar.c.a;
        kVar.e = str;
        kVar.f = str2;
        kVar.d = j;
        kVar.g = j2;
        com.xunlei.downloadprovider.c.k kVar2 = new com.xunlei.downloadprovider.c.k(aVar, rVar);
        l lVar = new l(aVar, rVar);
        JSONObject f = kVar.f();
        new StringBuilder("json obj=>").append(f.toString());
        Request hVar = new h(1, "https://comment-shoulei-ssl.xunlei.com/comment/api/comment", f, kVar2, lVar);
        hVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
        hVar.setTag(com.xunlei.downloadprovider.c.a.a);
        aVar.b.a(hVar);
    }

    public final void a(long j) {
        com.xunlei.downloadprovider.c.a aVar = this.a;
        f fVar = new f(this, j);
        if (aVar.c == null) {
            throw new IllegalStateException("no comment resource attached");
        }
        i iVar = new i();
        iVar.d = j;
        iVar.a = aVar.c.b;
        iVar.b = aVar.c.c;
        iVar.c = aVar.c.a;
        Request hVar = new h(0, new StringBuilder("https://comment-shoulei-ssl.xunlei.com/comment/api/comment_del?").append(iVar.f()).toString(), null, new com.xunlei.downloadprovider.c.d(aVar, fVar), new com.xunlei.downloadprovider.c.e(aVar, fVar));
        hVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
        hVar.setTag(com.xunlei.downloadprovider.c.a.a);
        aVar.b.a(hVar);
    }

    public final void a(c cVar) {
        if (cVar != null) {
            com.xunlei.downloadprovider.web.base.model.a.a aVar = new com.xunlei.downloadprovider.web.base.model.a.a();
            aVar.e = this.g.g;
            aVar.f = this.g.a;
            aVar.a = cVar.a;
            aVar.g = cVar.i;
            aVar.d = false;
            aVar.b = true;
            LoginHelper.a();
            aVar.c = LoginHelper.c();
            this.h.add(aVar);
            this.i.add(aVar);
            com.xunlei.downloadprovider.c.a aVar2 = this.a;
            long j = cVar.a;
            long j2 = cVar.i;
            i iVar = new i(this, cVar, aVar);
            if (aVar2.c == null) {
                throw new IllegalStateException("no comment resource attached");
            }
            com.xunlei.downloadprovider.c.a.l lVar = new com.xunlei.downloadprovider.c.a.l();
            lVar.c = aVar2.c.c;
            lVar.a = aVar2.c.b;
            lVar.b = aVar2.c.a;
            lVar.d = j;
            lVar.e = j2;
            Request a = lVar.a(new m(aVar2, iVar), new n(aVar2, iVar));
            a.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
            a.setTag(com.xunlei.downloadprovider.c.a.a);
            aVar2.b.a(a);
        }
    }

    public final void d() {
        new StringBuilder("cancel all request with TAG=>").append(this.j);
        this.k.a(this.j);
        this.a.b.a(com.xunlei.downloadprovider.c.a.a);
    }

    public final void e() {
        this.d.submit(this.n);
        if (System.currentTimeMillis() - aa.a(this.l, "comment_sync_time") > 7200000 && com.xunlei.xllib.a.b.a(this.l)) {
            aa.a(this.l, "comment_sync_time", System.currentTimeMillis());
            this.d.submit(this.o);
        }
    }

    public final void a(Context context, int i, u uVar) {
        String str = uVar.b;
        String str2 = com.umeng.a.d;
        String str3 = "detail_shortvideo";
        ShareBean shareBean = new ShareBean(str3, uVar.a(), uVar.c, str, str2);
        shareBean.g = true;
        SHARE_MEDIA share_media = null;
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                share_media = SHARE_MEDIA.WEIXIN;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                share_media = SHARE_MEDIA.QZONE;
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                share_media = SHARE_MEDIA.SINA;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                share_media = SHARE_MEDIA.QQ;
                break;
        }
        if (share_media != null) {
            com.xunlei.downloadprovidershare.d.b().a((Activity) context, shareBean, share_media, new j(this));
        }
    }
}
