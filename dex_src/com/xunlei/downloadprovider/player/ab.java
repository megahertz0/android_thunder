package com.xunlei.downloadprovider.player;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.player.wrapper.PlayerMessageState;
import com.xunlei.downloadprovider.player.wrapper.a;
import com.xunlei.downloadprovider.player.wrapper.a.d;
import com.xunlei.downloadprovider.player.wrapper.a.f;
import com.xunlei.downloadprovider.player.wrapper.a.g;
import com.xunlei.downloadprovider.player.wrapper.a.j;
import com.xunlei.downloadprovider.player.wrapper.a.k;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.h;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;

// compiled from: ThunderMediaPlayer.java
public final class ab {
    public int A;
    private SurfaceView B;
    private boolean C;
    private ViewGroup D;
    private LayoutParams E;
    private long F;
    private MediaPlayerErrorView G;
    private OrientationEventListener H;
    private boolean I;
    public int a;
    public String b;
    long c;
    Context d;
    public a e;
    public View f;
    z g;
    int h;
    int i;
    int j;
    int k;
    public boolean l;
    public n m;
    public boolean n;
    public MediaPlayerControllerView o;
    public MediaPlayerCompletionView p;
    MediaPlayerGestureView q;
    public OnCompletionListener r;
    boolean s;
    long t;
    boolean u;
    int v;
    boolean w;
    boolean x;
    boolean y;
    public boolean z;

    public ab(Context context) {
        this.a = aa.a();
        this.b = new StringBuilder("Player").append(this.a).toString();
        this.c = -1;
        this.n = true;
        this.v = 1;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.d = context;
        this.e = new a();
        a aVar = this.e;
        aVar.f = new ad(this, aVar);
        View inflate = LayoutInflater.from(this.d).inflate(2130968871, null);
        inflate.addOnLayoutChangeListener(new ac(this, inflate));
        this.f = inflate;
        SurfaceView surfaceView = (SurfaceView) this.f.findViewById(2131756543);
        surfaceView.getHolder().setType(XZBDevice.DOWNLOAD_LIST_FAILED);
        surfaceView.setDrawingCacheEnabled(true);
        surfaceView.getHolder().addCallback(new ae(this));
        this.B = surfaceView;
        this.o = (MediaPlayerControllerView) this.f.findViewById(2131756545);
        this.o.setMediaPlayer(this);
        this.e.a(this.o);
        this.G = (MediaPlayerErrorView) this.f.findViewById(2131756546);
        this.G.setMediaPlayer(this);
        this.e.a(this.G);
        this.p = (MediaPlayerCompletionView) this.f.findViewById(2131756547);
        this.p.a = this;
        this.e.a(this.p);
        this.q = (MediaPlayerGestureView) this.f.findViewById(2131756544);
        this.q.a = this;
        this.e.a(this.q);
    }

    public final void a() {
        this.I = true;
        if (this.H == null) {
            this.H = new af(this, q());
        }
    }

    final void a(boolean z) {
        if (this.H == null) {
            return;
        }
        if (z) {
            this.H.enable();
        } else {
            this.H.disable();
        }
    }

    public final void b() {
        MediaPlayerState a = this.e.b.a.a();
        if (i()) {
            if (a == MediaPlayerState.STARTED || a == MediaPlayerState.PAUSED || a == MediaPlayerState.LOADING) {
                b(false);
            }
        }
        o();
        a aVar = this.e;
        aVar.b();
        aVar.a.set(PlayerMessageState.RELEASING);
        aVar.c.post(new d(aVar.b));
        a(false);
        l();
    }

    final void b(boolean z) {
        v();
        com.xunlei.downloadprovider.download.c.a.a().c();
        String str = MessageService.MSG_DB_NOTIFY_REACHED;
        if (z) {
            str = MessageService.MSG_DB_NOTIFY_CLICK;
        } else if (this.e.b.a.a() == MediaPlayerState.PLAYBACK_COMPLETED) {
            str = MessageService.MSG_DB_READY_REPORT;
        }
        if (this.g != null) {
            ai aiVar = this.g.d;
            if (!"homepage_ad".equals(aiVar.i)) {
                String str2;
                a.a aVar = new a.a();
                aVar.a = "online_shortvideo";
                aVar.g = "online_url";
                aVar.b = str;
                aVar.c = (long) (g() / 1000);
                aVar.d = (long) (h() / 1000);
                aVar.f = this.c;
                aVar.h = aiVar.c;
                aVar.i = aiVar.a;
                aVar.l = aiVar.j;
                aVar.m = b.q();
                aVar.n = com.xunlei.xllib.a.b.d(this.d);
                new StringBuilder("reportPlayEnd fileDuration=").append(aVar.c).append(",playDuration=").append(aVar.d);
                if (g() == h()) {
                    str2 = "end_part";
                } else {
                    str2 = "end_all";
                }
                com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(aiVar.c, aiVar.d, b.d(), g(), str2, h());
                a.a(aVar);
            }
        }
    }

    public final void a(ai aiVar) {
        if (aiVar != null) {
            new StringBuilder("play--state=").append(this.e.b.a.a()).append("|movieId=").append(aiVar.c).append("|title=").append(aiVar.b);
            if (this.e != null) {
                this.o.setPoster(aiVar.k);
                if (!TextUtils.isEmpty(aiVar.a) && !TextUtils.isEmpty(aiVar.c)) {
                    z zVar = this.g;
                    this.g = new z(aiVar);
                    boolean z = true;
                    if (com.xunlei.xllib.a.b.e(this.d)) {
                        z = q.a().a(aiVar == null ? com.umeng.a.d : aiVar.c, this.d, new ag(this, aiVar, zVar));
                    }
                    if (z) {
                        a(aiVar, zVar);
                    }
                }
            }
        }
    }

    final void a(ai aiVar, z zVar) {
        x();
        String str = aiVar.c;
        if (zVar == null || !str.equals(zVar.d.c)) {
            if (this.e.b.a.a() != MediaPlayerState.IDLE) {
                this.e.a();
            }
            b(aiVar);
        } else if (i()) {
            MediaPlayerState a = this.e.b.a.a();
            if (!(a == MediaPlayerState.PAUSED && this.s)) {
                if (a == MediaPlayerState.PLAYBACK_COMPLETED) {
                    a(0);
                } else if (a == MediaPlayerState.ERROR) {
                    this.e.a();
                    b(aiVar);
                }
                c();
            }
        } else {
            if (this.e.b.a.a() != MediaPlayerState.IDLE) {
                this.e.a();
            }
            b(aiVar);
        }
        if (!TextUtils.isEmpty(aiVar.b)) {
            this.o.setTitle(aiVar.b);
        }
        if (aiVar.e) {
            this.e.a((float) AutoScrollHelper.RELATIVE_UNSPECIFIED, (float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.o.setShouldShowVoiceAnimationOnPlaying(true);
            return;
        }
        this.o.setShouldShowVoiceAnimationOnPlaying(false);
        w();
        if (i()) {
            a aVar = this.e;
            aVar.c.post(new com.xunlei.downloadprovider.player.wrapper.a.a(aVar.b));
        }
    }

    private void w() {
        this.e.a(1.0f, 1.0f);
    }

    public final void c() {
        a aVar = this.e;
        aVar.a.set(PlayerMessageState.STARTING);
        aVar.c.post(new j(aVar.b));
        Context applicationContext = this.d.getApplicationContext();
        if (applicationContext != null && VERSION.SDK_INT > 8) {
            ((AudioManager) applicationContext.getSystemService("audio")).requestAudioFocus(null, XZBDevice.DOWNLOAD_LIST_FAILED, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        if (this.I) {
            a(true);
        }
    }

    public final void a(int i) {
        a aVar = this.e;
        if (com.xunlei.xllib.a.b.a(BrothersApplication.a()) || i < aVar.e.get()) {
            aVar.d.set(i);
            aVar.c.post(new f(aVar.b, i));
        }
    }

    public final void c(boolean z) {
        if (!i() || j()) {
            this.e.a();
        } else if (t()) {
            this.s = z;
            a aVar = this.e;
            aVar.a.set(PlayerMessageState.PAUSING);
            aVar.c.post(new com.xunlei.downloadprovider.player.wrapper.a.b(aVar.b));
        }
        v();
    }

    public final void d() {
        if (!com.xunlei.xllib.a.b.a(this.d)) {
            XLToast.b(this.d, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        } else if (this.g != null && !TextUtils.isEmpty(this.g.d.a) && this.e.b.a.a() != MediaPlayerState.RELEASE) {
            x();
            a(0);
            this.e.b.a.a();
            MediaPlayerState mediaPlayerState = MediaPlayerState.PLAYBACK_COMPLETED;
            a(this.g.d);
        }
    }

    private void x() {
        boolean b = com.xunlei.downloadprovider.download.c.a.a().b();
        if (b) {
            this.u = true;
        }
        com.xunlei.downloadprovider.download.c.a.a().a(b);
    }

    private void b(ai aiVar) {
        this.t = SystemClock.elapsedRealtime();
        this.c = System.currentTimeMillis();
        a aVar = this.e;
        String str = aiVar.a;
        if (aVar.b.a.a() != MediaPlayerState.IDLE) {
            aVar.a();
        }
        aVar.a.set(PlayerMessageState.SETTING_DATA_SOURCE);
        aVar.c.post(new g(aVar.b, str));
    }

    public final void e() {
        if (i() && this.e.b.a.a() != MediaPlayerState.ERROR && this.e.b.a.a() != MediaPlayerState.STOPPED) {
            a aVar = this.e;
            aVar.b();
            aVar.a.set(PlayerMessageState.STOPPING);
            aVar.c.post(new k(aVar.b));
            b(false);
        }
    }

    public final MediaPlayerState f() {
        return this.e.b.a.a();
    }

    public final int g() {
        if (this.g == null) {
            return 0;
        }
        if (this.g.c <= 0) {
            this.g.c = this.e.c();
        }
        return this.g.c;
    }

    public final int h() {
        return this.e.d.get();
    }

    public final boolean i() {
        return this.e.b.a.a;
    }

    final boolean j() {
        return f() == MediaPlayerState.ERROR;
    }

    final void k() {
        if (!this.l) {
            boolean z;
            this.l = true;
            this.F = SystemClock.elapsedRealtime();
            this.q.setShouldDetectorGestureMove(true);
            if (p() || !this.w) {
                z = true;
            } else {
                int i = 0;
            }
            Activity q = q();
            if (q != null) {
                Activity q2 = q();
                if (q2 != null) {
                    int systemUiVisibility = q2.getWindow().getDecorView().getSystemUiVisibility();
                    if (VERSION.SDK_INT >= 14) {
                        systemUiVisibility |= 2;
                    }
                    if (VERSION.SDK_INT >= 16) {
                        systemUiVisibility |= 4;
                    }
                    if (VERSION.SDK_INT >= 19) {
                        systemUiVisibility |= 4096;
                    }
                    q2.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
                }
                if (z) {
                    this.C = true;
                    q.setRequestedOrientation(R.styleable.Toolbar_contentInsetEnd);
                }
                q.getWindow().addFlags(JsInterface.MSG_JS_COLLECT_WEBSITE);
                if (this.n) {
                    o();
                    a((FrameLayout) q.getWindow().getDecorView(), -1, -1);
                }
            }
            w();
            MediaPlayerControllerView mediaPlayerControllerView = this.o;
            mediaPlayerControllerView.d.setVisibility(0);
            mediaPlayerControllerView.c.setVisibility(0);
            mediaPlayerControllerView.h.setImageResource(2130838509);
            if (this.m != null) {
                this.m.a();
            }
        }
    }

    final void l() {
        if (this.l) {
            this.l = false;
            this.y = false;
            this.F = SystemClock.elapsedRealtime();
            this.q.a();
            this.q.setShouldDetectorGestureMove(false);
            Activity q = q();
            if (q != null) {
                this.C = false;
                q.setRequestedOrientation(1);
                q.getWindow().clearFlags(JsInterface.MSG_JS_COLLECT_WEBSITE);
                Activity q2 = q();
                if (q2 != null) {
                    int systemUiVisibility = q2.getWindow().getDecorView().getSystemUiVisibility();
                    if (VERSION.SDK_INT >= 14) {
                        systemUiVisibility &= -3;
                    }
                    if (VERSION.SDK_INT >= 16) {
                        systemUiVisibility &= -5;
                    }
                    if (VERSION.SDK_INT >= 19) {
                        systemUiVisibility &= -4097;
                    }
                    q2.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
                }
                if (this.n) {
                    ViewGroup viewGroup = this.D;
                    LayoutParams layoutParams = this.E;
                    if (viewGroup != null) {
                        o();
                        a(viewGroup, layoutParams.width, layoutParams.height);
                    }
                }
            }
            MediaPlayerControllerView mediaPlayerControllerView = this.o;
            mediaPlayerControllerView.d();
            mediaPlayerControllerView.c.setVisibility(XZBDevice.Wait);
            mediaPlayerControllerView.h.setImageResource(2130838443);
            if (this.m != null) {
                this.m.b();
            }
        }
    }

    public final boolean m() {
        return SystemClock.elapsedRealtime() - this.F < 1000;
    }

    final void n() {
        a(true);
        this.x = true;
        this.w = false;
        l();
    }

    public final void o() {
        if (this.f != null && this.f.getParent() != null) {
            this.D = (ViewGroup) this.f.getParent();
            this.E = this.f.getLayoutParams();
            this.D.removeView(this.f);
        }
    }

    public final void a(ViewGroup viewGroup, int i, int i2) {
        viewGroup.addView(this.f, new LayoutParams(i, i2));
    }

    public final boolean p() {
        return this.j >= this.k;
    }

    final Activity q() {
        return (this.d == null || !(this.d instanceof Activity)) ? null : (Activity) this.d;
    }

    public final boolean r() {
        if (!this.l) {
            return false;
        }
        n();
        return true;
    }

    public final void d(boolean z) {
        if (z) {
            this.e.a(this.p);
            return;
        }
        this.e.b(this.p);
        this.p.setVisibility(XZBDevice.Wait);
    }

    public final void s() {
        this.o.setTitleVisiableInSmallScreen(false);
    }

    public final boolean t() {
        return f() == MediaPlayerState.STARTED || f() == MediaPlayerState.LOADING;
    }

    public final void e(boolean z) {
        this.o.setPrevPlayBtnVisiable(z);
        this.p.setPrevPlayBtnVisiable(z);
    }

    public final void f(boolean z) {
        this.o.setNextPlayBtnVisiable(z);
        this.p.setNextPlayBtnVisiable(z);
    }

    public final ai u() {
        return this.g == null ? null : this.g.d;
    }

    public final void a(MediaPlayerGestureView.a aVar) {
        this.q.setClickToDetailListener(aVar);
    }

    public final void v() {
        if (this.g != null && this.g.d.f && this.g.b >= 3000) {
            ai aiVar = this.g.d;
            int h = h();
            int i = this.g.c;
            String str = aiVar.c;
            String str2 = aiVar.d;
            String str3 = aiVar.b;
            String str4 = aiVar.a;
            h hVar = new h();
            hVar.n = str;
            hVar.e = str2;
            hVar.a = str3;
            hVar.c = str4;
            hVar.s = h;
            hVar.r = i;
            hVar.m = "shortVideo";
            com.xunlei.downloadprovider.vod.b.b a = com.xunlei.downloadprovider.vod.b.b.a();
            a.b.execute(new com.xunlei.downloadprovider.vod.b.d(a, hVar, VodSourceType.shortVideo));
            this.g.a = true;
        }
    }

    public final void b(int i) {
        this.o.setViewType(i);
    }

    public final void a(t tVar) {
        this.e.a(tVar);
    }

    public final void b(t tVar) {
        this.e.b(tVar);
    }

    public final String toString() {
        String str = null;
        if (u() != null) {
            str = u().c;
        }
        return new StringBuilder("id=").append(this.a).append("|tag=").append(this.b).append("|movieId=").append(str).toString();
    }

    public final void a(OnClickListener onClickListener) {
        this.p.setReplayClickListener(onClickListener);
    }

    static /* synthetic */ void a(ab abVar, int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            float f = ((float) i3) / ((float) i4);
            if (f > ((float) i) / ((float) i2)) {
                i2 = (int) (((float) i) / f);
            } else {
                i = (int) (f * ((float) i2));
            }
            LayoutParams layoutParams = abVar.B.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = i;
                layoutParams.height = i2;
                abVar.B.setLayoutParams(layoutParams);
            }
        }
    }
}
