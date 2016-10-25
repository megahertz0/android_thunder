package com.xunlei.downloadprovider.ad.home.ui;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.home.a.c;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.downloadprovider.player.MediaPlayerLoadingView;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.a.b;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.ai;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Set;
import org.android.spdy.SpdyProtocol;

// compiled from: ADPlayVodItem.java
public final class i extends FrameLayout implements ADItemView, d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a>, b, com.xunlei.downloadprovider.player.b {
    protected boolean a;
    protected Rect b;
    private com.xunlei.downloadprovider.player.a.a c;
    private final String d;
    private n e;
    private ao f;
    private ab g;
    private a h;
    private int i;
    private com.xunlei.downloadprovider.ad.common.a j;
    private String k;
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a l;
    private t m;

    // compiled from: ADPlayVodItem.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[MediaPlayerState.values().length];
            try {
                a[MediaPlayerState.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[MediaPlayerState.PREPARING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[MediaPlayerState.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    // compiled from: ADPlayVodItem.java
    private class a {
        View a;
        TextView b;
        TextView c;
        FrameLayout d;
        TextView e;
        ImageView f;
        ImageView g;
        MediaPlayerLoadingView h;

        private a() {
        }
    }

    static /* synthetic */ void e(i iVar) {
        if (iVar.h != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(iVar.getContext(), R.anim.media_player_poster_hide_animation);
            iVar.h.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            iVar.h.f.startAnimation(loadAnimation);
            iVar.h.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            iVar.h.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        if (fVar instanceof n) {
            this.e = (n) fVar;
        }
        this.i = i;
        this.l = aVar;
        setTag(R.id.position_layout, Integer.valueOf(i));
        if (view instanceof ADItemView) {
            ((ADItemView) view).a(aVar.d);
            com.xunlei.downloadprovider.ad.common.a a = c.a(getContext().getApplicationContext()).c.a(aVar.d);
            if (this.j != a || a == null) {
                a aVar2 = (a) getTag();
                if (aVar2 != null) {
                    aVar2.b.setText(BuildConfig.VERSION_NAME);
                    com.xunlei.downloadprovider.homepage.choiceness.a.a(BuildConfig.VERSION_NAME, aVar2.f);
                }
            }
            c.a(getContext()).a(aVar, (ADItemView) view, (n) fVar);
        }
    }

    public final /* synthetic */ boolean a(int i, e eVar) {
        if (this.j != null) {
            com.xunlei.downloadprovider.ad.home.a.a(com.xunlei.downloadprovider.ad.common.b.a(this.j), this.j.o().getSourceName(), WeiXinShareContent.TYPE_VIDEO, Utility.NETWORK_OTHER, this.j.j());
            a();
        }
        return true;
    }

    public i(Context context, com.xunlei.downloadprovider.player.a.a aVar) {
        super(context);
        this.d = c.a;
        this.b = new Rect();
        this.m = new n(this);
        this.c = aVar;
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_ad_play_vod_item, this, true);
        a aVar2 = new a();
        aVar2.d = (FrameLayout) inflate.findViewById(R.id.item_player_container);
        aVar2.f = (ImageView) inflate.findViewById(R.id.item_poster);
        aVar2.g = (ImageView) inflate.findViewById(R.id.play_icon);
        aVar2.b = (TextView) inflate.findViewById(R.id.item_title);
        aVar2.e = (TextView) inflate.findViewById(R.id.ad_download_btn);
        aVar2.c = (TextView) inflate.findViewById(R.id.item_duration);
        aVar2.a = inflate.findViewById(R.id.ad_download_btn_container);
        aVar2.h = (MediaPlayerLoadingView) inflate.findViewById(R.id.loading_view);
        setTag(aVar2);
    }

    private void a() {
        int i;
        c.a(getContext()).c.d = this.j;
        ab abVar = this.g;
        if (abVar == null) {
            abVar = q.a().b("home_player");
        }
        if (abVar != null) {
            i = abVar.a;
        } else {
            i = -1;
        }
        this.e.b = false;
        ShortMovieDetailActivity.a(getContext(), -1, BuildConfig.VERSION_NAME, From.HOME_PAGE_AD, b(this.j), b(this.j), this.j.b(), BuildConfig.VERSION_NAME, this.j.i(), this.j.d(), -1, i);
    }

    private static String b(com.xunlei.downloadprovider.ad.common.a aVar) {
        return new StringBuilder("ad_").append(aVar.s()).toString();
    }

    private void b() {
        if (this.h != null) {
            this.h.f.setVisibility(0);
            this.h.g.setVisibility(0);
            this.h.c.setVisibility(0);
            this.h.h.b();
        }
    }

    private void g() {
        if (this.j != null) {
            ai u;
            String b = b(this.j);
            if (this.g != null) {
                u = this.g.u();
                if (u != null && b.equals(u.c)) {
                    return;
                }
            }
            this.g = q.a().a(getContext(), this, "home_player");
            this.g.A = this.i;
            u = new ai(b, this.j.i(), this.j.a());
            u.i = "homepage_ad";
            u.h = new j(this);
            this.g.r = new k(this);
            u.d = this.l.e;
            u.e = true;
            this.g.a(u);
        }
    }

    public final com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return null;
    }

    public final String getViewPositionKey() {
        return this.k;
    }

    public final String a(String str) {
        this.k = str;
        return str;
    }

    public final AD_LAYOUT_TYPE getADType() {
        return AD_LAYOUT_TYPE.PLAY_VOD_TYPE_VIEW;
    }

    public final void a(com.xunlei.downloadprovider.ad.common.a aVar) {
        this.h = (a) getTag();
        this.h.b.setText(aVar.b());
        if (this.j != aVar) {
            this.j = aVar;
            ao aoVar = new ao();
            if (!(aVar.i() == null || aVar.i().trim().equals(BuildConfig.VERSION_NAME))) {
                aoVar.c = aVar.i();
            }
            aoVar.b = aVar.i();
            this.f = aoVar;
            if (this.j == null || this.j.k() == null || this.j.k().equals(BuildConfig.VERSION_NAME)) {
                this.h.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.h.c.setVisibility(0);
                this.h.c.setText(this.j.k());
            }
            if (this.a) {
                g();
            } else {
                if (this.c != null) {
                    this.c.b(null);
                }
                if (this.g != null) {
                    this.g.b(this.m);
                    this.g.e.a();
                    this.g = null;
                }
                if (this.h != null) {
                    this.h.d.removeAllViews();
                }
                b();
            }
        }
        Set set = c.a(getContext()).c.c;
        if (!(set == null || set.contains(this.k))) {
            com.xunlei.downloadprovider.ad.home.a.a(com.xunlei.downloadprovider.ad.common.b.a(aVar), aVar.o().getSourceName(), WeiXinShareContent.TYPE_VIDEO, aVar.j());
            set.add(this.k);
            aVar.a((View) this);
        }
        if (aVar.e()) {
            this.h.e.setText("\u7acb\u5373\u4e0b\u8f7d");
        } else {
            this.h.e.setText("\u67e5\u770b\u8be6\u60c5");
        }
        this.h.a.setOnClickListener(new l(this, aVar));
        if (!TextUtils.isEmpty(aVar.d())) {
            com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.d(), this.h.f);
        }
    }

    public final View getContainer() {
        return this;
    }

    public final void a(ab abVar) {
        if (this.f != null) {
            abVar.a(this.m);
            abVar.s();
            abVar.a(this.h.d, -1, -1);
            abVar.a(new m(this, abVar));
            this.g = abVar;
            this.h.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.h.h.a();
            this.h.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (this.c != null) {
                this.c.b(this);
            }
        }
    }

    public final void b(ab abVar) {
        if (abVar != null) {
            abVar.a(null);
        }
        if (this.c != null) {
            this.c.b(null);
        }
        if (this.g != null) {
            this.g.b(this.m);
            this.g = null;
        }
        if (this.h != null) {
            this.h.d.removeAllViews();
        }
        b();
    }

    public final int getVisibilityPercents() {
        return com.xunlei.downloadprovider.player.a.d.a(getContext(), this.b, ((a) getTag()).d);
    }

    public final int getPosition() {
        return this.i;
    }

    public final void c() {
        if (!this.a) {
            this.a = true;
            g();
        }
    }

    public final void d() {
        this.a = false;
        if (this.g != null) {
            q.a().a(this.g);
        }
    }

    public final View getLayout() {
        return ((a) getTag()).d;
    }

    public final boolean e() {
        return (this.g == null || this.g.e.b.a.a() == MediaPlayerState.IDLE) ? this.a : true;
    }

    public final boolean f() {
        return true;
    }
}
