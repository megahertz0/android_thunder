package com.xunlei.downloadprovider.player;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.b;
import com.bumptech.glide.e;
import com.bumptech.glide.g;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyAgent;

public class MediaPlayerControllerView extends FrameLayout implements t {
    private Handler A;
    ab a;
    boolean b;
    View c;
    TextView d;
    ImageView e;
    SeekBar f;
    ProgressBar g;
    ImageView h;
    TextView i;
    int j;
    boolean k;
    private View l;
    private int m;
    private MediaPlayerLoadingView n;
    private boolean o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private View s;
    private ImageView t;
    private TextView u;
    private boolean v;
    private a w;
    private y x;
    private boolean y;
    private boolean z;

    public static interface a {
        void a(boolean z);
    }

    public MediaPlayerControllerView(Context context) {
        super(context);
        this.v = true;
        this.k = true;
        this.A = new f(this);
        a(context);
    }

    public MediaPlayerControllerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = true;
        this.k = true;
        this.A = new f(this);
        a(context);
    }

    public MediaPlayerControllerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = true;
        this.k = true;
        this.A = new f(this);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2130968869, this, true);
        this.c = inflate.findViewById(2131756531);
        this.c.setOnClickListener(new g(this));
        this.d = (TextView) inflate.findViewById(R.id.tv_title);
        d();
        this.s = inflate.findViewById(2131756530);
        this.n = (MediaPlayerLoadingView) inflate.findViewById(2131756541);
        this.e = (ImageView) inflate.findViewById(2131756529);
        this.p = (ImageView) inflate.findViewById(2131756532);
        this.p.setOnClickListener(new h(this));
        this.q = (ImageView) inflate.findViewById(2131756526);
        this.q.setOnClickListener(new i(this));
        this.r = (ImageView) inflate.findViewById(2131756527);
        this.r.setOnClickListener(new j(this));
        this.f = (SeekBar) inflate.findViewById(2131756534);
        this.f.setOnSeekBarChangeListener(new k(this));
        this.g = (ProgressBar) inflate.findViewById(R.id.progress_bar);
        this.i = (TextView) inflate.findViewById(2131756533);
        this.u = (TextView) inflate.findViewById(2131755494);
        this.h = (ImageView) inflate.findViewById(R.id.full_screen_btn);
        this.h.setOnClickListener(new l(this));
        this.t = (ImageView) inflate.findViewById(2131756528);
        g b = e.b(getContext());
        ((b) ((b) b.a(Integer.class).a(com.bumptech.glide.g.a.a(b.a))).a(Integer.valueOf(2130837607))).a(this.t);
        this.l = inflate;
    }

    public void setViewType(int i) {
        this.m = i;
        switch (this.m) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                g();
                c();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                h();
                if (this.a == null || !this.a.t()) {
                    a();
                } else {
                    c();
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                h();
                if (this.a == null || !this.a.t()) {
                    a();
                } else {
                    c();
                }
            default:
                break;
        }
    }

    private void f() {
        if (this.a != null) {
            if (this.a.e.b.a.a() == MediaPlayerState.STARTED) {
                this.p.setImageResource(2130838466);
            } else {
                this.p.setImageResource(2130838480);
            }
        }
    }

    public void setMediaPlayer(ab abVar) {
        this.a = abVar;
        f();
        setSeekBarStatus(this.a.e.b.a.a());
        if (abVar.t()) {
            c();
        } else {
            a();
        }
    }

    private void g() {
        this.t.setVisibility(0);
    }

    private void h() {
        this.t.setVisibility(XZBDevice.Wait);
    }

    public final void a() {
        new StringBuilder("show--isShowing=").append(this.b);
        i();
        this.s.setVisibility(0);
        this.g.setVisibility(XZBDevice.Wait);
        this.b = true;
        if (this.w != null) {
            this.w.a(true);
        }
    }

    private void i() {
        if (this.A.hasMessages(1)) {
            this.A.removeMessages(1);
        }
    }

    final void b() {
        i();
        this.A.sendEmptyMessageDelayed(1, 2000);
    }

    final void c() {
        new StringBuilder("hide--isShowing=").append(this.b);
        i();
        this.s.setVisibility(XZBDevice.Wait);
        if (this.m != 1) {
            this.g.setVisibility(0);
        }
        this.b = false;
        if (this.w != null) {
            this.w.a(false);
        }
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        new StringBuilder("onStateChange() oldState=").append(mediaPlayerState).append(" newState=").append(mediaPlayerState2);
        setSeekBarStatus(mediaPlayerState2);
        h();
        switch (AnonymousClass_1.a[mediaPlayerState2.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                j();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.k = true;
                j();
                this.n.a();
                if (this.o) {
                    this.e.setVisibility(0);
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.k = true;
                j();
                this.n.a();
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                this.k = true;
                long g = (long) this.a.g();
                if (g > 0) {
                    int i = (int) g;
                    this.j = i;
                    this.g.setMax(i);
                    this.f.setMax(i);
                    this.u.setText(j.b((long) i));
                }
                f();
                k();
                c();
                this.n.b();
                if (this.y) {
                    g();
                }
            case R.styleable.Toolbar_contentInsetEnd:
            case R.styleable.Toolbar_contentInsetLeft:
                this.k = false;
                this.n.b();
                if (this.a.i()) {
                    this.e.setVisibility(XZBDevice.Wait);
                    this.e.setImageDrawable(null);
                }
                if (this.m != 1) {
                    f();
                    k();
                    i();
                    a();
                }
            case XZBDevice.Wait:
                if (this.z) {
                    a();
                }
                this.n.b();
                this.g.setProgress(0);
                this.g.setSecondaryProgress(0);
                this.i.setText(j.b(0));
                this.u.setText(j.b(0));
                this.j = 0;
                this.f.setProgress(0);
                this.f.setSecondaryProgress(0);
                this.e.setImageDrawable(null);
                f();
                k();
            case XZBDevice.Pause:
                this.n.b();
                j();
            default:
                break;
        }
    }

    private void j() {
        this.p.setVisibility(XZBDevice.Wait);
    }

    private void k() {
        this.p.setVisibility(0);
    }

    void setTitleVisiableInSmallScreen(boolean z) {
        this.v = z;
        d();
    }

    final void d() {
        if (this.v) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(XZBDevice.Wait);
        }
    }

    void setTitle(String str) {
        this.d.setText(str);
    }

    public void setVisiableListener(a aVar) {
        this.w = aVar;
    }

    public void setPrevPlayBtnVisiable(boolean z) {
        if (z) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(XZBDevice.Wait);
        }
    }

    public void setNextPlayBtnVisiable(boolean z) {
        if (z) {
            this.r.setVisibility(0);
        } else {
            this.r.setVisibility(XZBDevice.Wait);
        }
    }

    private void setSeekBarStatus(MediaPlayerState mediaPlayerState) {
        SeekBar seekBar = this.f;
        boolean z = (mediaPlayerState == MediaPlayerState.IDLE || mediaPlayerState == MediaPlayerState.PREPARING) ? false : true;
        seekBar.setEnabled(z);
    }

    public void setOnControllerClickListener(y yVar) {
        this.x = yVar;
    }

    public void setShouldShowVoiceAnimationOnPlaying(boolean z) {
        this.y = z;
    }

    public void setShowControllerOnIdle(boolean z) {
        this.z = z;
    }

    public void setShouldShowLoadingBackground(boolean z) {
        this.o = z;
    }

    public void setPoster(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e.setImageDrawable(null);
        } else {
            com.xunlei.downloadprovider.homepage.choiceness.a.a(str, this.e);
        }
        this.e.setVisibility(0);
    }

    static /* synthetic */ void e() {
        com.xunlei.downloadprovider.download.c.a a = com.xunlei.downloadprovider.download.c.a.a();
        if (a.a == a.c) {
            a.a(a.b());
        }
    }
}
