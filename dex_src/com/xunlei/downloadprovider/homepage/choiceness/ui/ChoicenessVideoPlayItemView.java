package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.player.MediaPlayerLoadingView;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.a.b;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.ai;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class ChoicenessVideoPlayItemView extends FrameLayout implements OnClickListener, d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a>, b, com.xunlei.downloadprovider.player.b {
    protected a a;
    protected boolean b;
    protected Rect c;
    private ab d;
    private com.xunlei.downloadprovider.player.a.a e;
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a f;
    private n g;
    private int h;
    private t i;

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

    protected class a {
        int a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        View h;
        FrameLayout i;
        ImageView j;
        ImageView k;
        MediaPlayerLoadingView l;

        protected a() {
        }
    }

    static /* synthetic */ void a(ChoicenessVideoPlayItemView choicenessVideoPlayItemView) {
        if (choicenessVideoPlayItemView.a != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(choicenessVideoPlayItemView.getContext(), R.anim.media_player_poster_hide_animation);
            choicenessVideoPlayItemView.a.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            choicenessVideoPlayItemView.a.j.startAnimation(loadAnimation);
            choicenessVideoPlayItemView.a.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            choicenessVideoPlayItemView.a.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        if (this.f != aVar) {
            this.f = aVar;
            if (this.d != null) {
                this.d.e.a();
                this.d = null;
            }
            a();
        }
        this.a = (a) getTag();
        this.a.a = i;
        this.a.b.setText(aVar.k);
        if (TextUtils.isEmpty(aVar.j)) {
            this.a.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.a.g.setVisibility(0);
            this.a.g.setText(aVar.j);
        }
        CharSequence charSequence = aVar.p;
        if (charSequence == null || charSequence.trim().equals(BuildConfig.VERSION_NAME)) {
            this.a.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.a.c.setVisibility(0);
            this.a.c.setText(charSequence);
        }
        if (aVar.n == 0) {
            this.a.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.a.d.setVisibility(0);
            this.a.d.setText(getResources().getString(R.string.choiceness_play_count, new Object[]{com.xunlei.downloadprovider.homepage.choiceness.a.a(r0)}));
        }
        if (aVar.o == 0) {
            this.a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.a.e.setVisibility(0);
            this.a.e.setText(getResources().getString(R.string.choiceness_like_count, new Object[]{com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.o)}));
        }
        if (this.f.a) {
            this.a.h.setVisibility(0);
        } else {
            this.a.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        this.a.f.setText(com.xunlei.downloadprovider.homepage.choiceness.a.b(this.f.t));
        if (!TextUtils.isEmpty(this.f.f)) {
            com.xunlei.downloadprovider.homepage.choiceness.a.a(com.xunlei.downloadprovider.util.a.a(aVar.f, aVar.g, aVar.h, this.a.j, com.xunlei.xllib.a.d.a(BrothersApplication.a), this.h), this.a.j, aVar.g, aVar.h);
        }
        this.a.j.setOnClickListener(this);
    }

    public ChoicenessVideoPlayItemView(Context context, com.xunlei.downloadprovider.player.a.a aVar, n nVar) {
        super(context);
        this.c = new Rect();
        this.i = new i(this);
        this.e = aVar;
        this.g = nVar;
        a(context);
    }

    public ChoicenessVideoPlayItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new Rect();
        this.i = new i(this);
        a(context);
    }

    public ChoicenessVideoPlayItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Rect();
        this.i = new i(this);
        a(context);
    }

    private void a(Context context) {
        this.h = (int) context.getResources().getDimension(R.dimen.home_choice_feed_view_height);
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_video_play_item, this, true);
        a aVar = new a();
        aVar.i = (FrameLayout) inflate.findViewById(R.id.item_player_container);
        aVar.j = (ImageView) inflate.findViewById(R.id.item_poster);
        aVar.k = (ImageView) inflate.findViewById(R.id.play_icon);
        aVar.b = (TextView) inflate.findViewById(R.id.item_title);
        aVar.c = (TextView) inflate.findViewById(R.id.item_tag);
        aVar.d = (TextView) inflate.findViewById(R.id.item_play_count);
        aVar.e = (TextView) inflate.findViewById(R.id.item_like_count);
        aVar.f = (TextView) inflate.findViewById(R.id.item_duration);
        aVar.g = (TextView) inflate.findViewById(R.id.item_res_type);
        aVar.h = inflate.findViewById(R.id.on_the_top_mask);
        aVar.l = (MediaPlayerLoadingView) inflate.findViewById(R.id.loading_view);
        setTag(aVar);
    }

    public boolean a(com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        b(aVar);
        ChoicenessReporter.a(aVar.d, aVar.b, SetKey.TITLE, aVar.f());
        return true;
    }

    private void b(com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        ab abVar = this.d;
        int i = -1;
        if (abVar == null) {
            abVar = q.a().b("home_player");
        }
        if (abVar != null) {
            i = abVar.a;
        }
        this.g.b = false;
        ShortMovieDetailActivity.a(getContext(), i, From.HOME_PAGE, aVar.e(), false);
    }

    private void a() {
        if (this.a != null) {
            this.a.j.setVisibility(0);
            this.a.k.setVisibility(0);
            this.a.f.setVisibility(0);
            this.a.l.b();
        }
    }

    protected final void a(boolean z) {
        if (com.xunlei.xllib.a.b.a(getContext())) {
            boolean z2 = true;
            if (com.xunlei.xllib.a.b.e(getContext())) {
                z2 = q.a().a(this.f == null ? BuildConfig.VERSION_NAME : this.f.d, getContext(), new h(this, z));
            }
            if (z2) {
                b(z);
                return;
            }
            return;
        }
        XLToast.b(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }

    private void b(boolean z) {
        if (this.d != null) {
            q.a().a(this.d);
        }
        this.d = q.a().a(getContext(), this, "home_player");
        this.d.A = this.a.a;
        ai aiVar = new ai(this.f.d, this.f.v, this.f.k);
        aiVar.i = getPlayerFrom();
        aiVar.d = this.f.e;
        aiVar.e = z;
        aiVar.j = this.f.f();
        this.d.a(aiVar);
    }

    protected String getPlayerFrom() {
        return "homepage";
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.f;
    }

    public void a(ab abVar) {
        this.d = abVar;
        abVar.b(SimpleLog.LOG_LEVEL_DEBUG);
        abVar.a(this.i);
        abVar.s();
        abVar.a(this.a.i, -1, -1);
        this.a.l.a();
        this.a.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void b(ab abVar) {
        this.d = null;
        this.a.i.removeAllViews();
        abVar.b(this.i);
        a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_poster:
                if (this.f == null) {
                    return;
                }
                if (8 == this.f.b) {
                    b bVar = this.e.f;
                    if (bVar != null) {
                        bVar.d();
                    }
                    if (!this.a.l.a) {
                        a(false);
                        ChoicenessReporter.a(this.f.d, this.f.b, "pic", this.f.f());
                        return;
                    }
                    return;
                }
                b(this.f);
                ChoicenessReporter.a(this.f.d, this.f.b, "pic", this.f.f());
            default:
                break;
        }
    }

    public int getVisibilityPercents() {
        return com.xunlei.downloadprovider.player.a.d.a(getContext(), this.c, getHolder().i);
    }

    public int getPosition() {
        return this.a.a;
    }

    public void c() {
        if (!this.b) {
            this.b = true;
            a(true);
        }
    }

    public void d() {
        this.b = false;
        if (getThunderMediaPlayer() != null) {
            getThunderMediaPlayer().c(false);
        }
    }

    public View getLayout() {
        return getHolder().i;
    }

    public boolean e() {
        return (getThunderMediaPlayer() == null || getThunderMediaPlayer().e.b.a.a() == MediaPlayerState.IDLE) ? this.b : true;
    }

    public boolean f() {
        return false;
    }

    protected ab getThunderMediaPlayer() {
        return this.d;
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getChoicenessInfo() {
        return this.f;
    }

    public a getHolder() {
        return this.a;
    }

    public n getAdapter() {
        return this.g;
    }
}
