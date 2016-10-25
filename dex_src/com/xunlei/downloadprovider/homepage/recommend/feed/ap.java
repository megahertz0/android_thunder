package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.ReportActivity;
import com.xunlei.downloadprovider.homepage.a;
import com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.player.MediaPlayerLoadingView;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.ai;
import com.xunlei.downloadprovider.player.b;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: FeedVideoItemView.java
public final class ap extends RelativeLayout {
    private AlphaAnimation A;
    private a B;
    private FrameLayout C;
    private aj D;
    private LayoutParams E;
    private b F;
    private t G;
    public ao a;
    FrameLayout b;
    ImageView c;
    TextView d;
    TextView e;
    TextView f;
    FeedItemShareLayout g;
    ab h;
    ViewGroup i;
    TextView j;
    TextView k;
    ImageView l;
    public TextView m;
    public FeedVideoItemBottomView n;
    ImageView o;
    public int p;
    List<String> q;
    int r;
    int s;
    d.a t;
    private boolean u;
    private FrameLayout v;
    private MediaPlayerLoadingView w;
    private ImageView x;
    private TextView y;
    private PopupWindow z;

    // compiled from: FeedVideoItemView.java
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

    static /* synthetic */ void a(ap apVar, View view, Context context) {
        if (apVar.a == null) {
            return;
        }
        if (apVar.q.contains(apVar.a.a) || apVar.a.e) {
            XLToast.b(context, XLToastType.XLTOAST_TYPE_ALARM, context.getResources().getString(R.string.feed_bottom_nice));
            return;
        }
        apVar.z.showAsDropDown(view, g.a(context, 37.0f), -g.a(context, 46.0f));
        apVar.y.startAnimation(apVar.A);
        apVar.n.getClickNiceTextView().setSelected(true);
        apVar.n.getClickNiceImageView().setSelected(true);
        apVar.setClickNiceBtnAnimation(context);
        apVar.p++;
        apVar.a.h = apVar.p;
        apVar.a.e = true;
        apVar.n.getClickNiceTextView().setText(com.xunlei.downloadprovider.homepage.choiceness.a.a(apVar.p));
        apVar.q.add(apVar.a.a);
        aa.a();
        aa.a(String.valueOf(apVar.a.a), apVar.p);
    }

    static /* synthetic */ void a(ap apVar, From from) {
        ab abVar = apVar.h;
        int i = -1;
        if (abVar == null) {
            abVar = q.a().b("feed_player");
        }
        if (abVar != null) {
            i = abVar.a;
        }
        apVar.B.a = false;
        if (apVar.D != null) {
            apVar.D.a = true;
        }
        ShortMovieDetailActivity.a(apVar.getContext(), i, from, apVar.a, true);
    }

    static /* synthetic */ void d(ap apVar) {
        VideoFeedReporter.a(apVar.a.a);
        com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(String.valueOf(apVar.a.a), apVar.a.q, com.xunlei.downloadprovider.a.b.d(), null, null);
    }

    public ap(Context context, a aVar) {
        super(context);
        this.u = false;
        this.q = new ArrayList();
        this.t = new ar(this);
        this.F = new at(this);
        this.G = new au(this);
        this.B = aVar;
        this.y = new TextView(getContext());
        this.y.setText("+1");
        this.y.setTextSize(SimpleLog.LOG_LEVEL_DEBUG, 12.0f);
        this.y.setTextColor(-15559434);
        this.z = new PopupWindow(this.y, -2, -2);
        this.z.setFocusable(false);
        this.z.setOutsideTouchable(true);
        this.z.setBackgroundDrawable(new ColorDrawable(0));
        this.A = new AlphaAnimation(1.0f, 0.0f);
        this.A.setDuration(1300);
        Context context2 = getContext();
        View inflate = LayoutInflater.from(context2).inflate(R.layout.short_video_item, this);
        int dimension = (int) context2.getResources().getDimension(R.dimen.feed_video_view_height_top);
        this.r = dimension;
        int dimension2 = (int) context2.getResources().getDimension(R.dimen.feed_video_view_item_spacing);
        this.v = (FrameLayout) inflate.findViewById(R.id.layout_top);
        this.E = new LayoutParams(-1, dimension);
        this.E.topMargin = dimension2;
        this.v.setLayoutParams(this.E);
        this.b = (FrameLayout) this.v.findViewById(R.id.layout_video_container);
        this.c = (ImageView) this.v.findViewById(R.id.iv_video_preview);
        this.w = (MediaPlayerLoadingView) this.v.findViewById(R.id.loading_view);
        this.d = (TextView) this.v.findViewById(R.id.tv_video_title);
        this.x = (ImageView) this.v.findViewById(R.id.play_icon);
        this.C = (FrameLayout) this.v.findViewById(R.id.layout_video_play_count_and_duration);
        this.f = (TextView) this.C.findViewById(R.id.tv_play_count);
        this.e = (TextView) this.C.findViewById(R.id.tv_duration);
        this.g = (FeedItemShareLayout) this.v.findViewById(R.id.layout_share);
        this.i = (ViewGroup) inflate.findViewById(R.id.layout_hot_comment);
        this.j = (TextView) this.i.findViewById(R.id.tv_hot_comment_name);
        this.k = (TextView) this.i.findViewById(R.id.tv_hot_comment_content);
        this.l = (ImageView) this.i.findViewById(R.id.iv_hot_comment_user_avatar);
        this.m = (TextView) this.i.findViewById(R.id.tv_hot_comment_thumb_count);
        this.n = (FeedVideoItemBottomView) inflate.findViewById(R.id.layout_bottom_bar);
        this.o = this.n.getSubjectIconImageView();
        context2 = getContext();
        a();
        this.d.setTextColor(context2.getResources().getColor(R.color.feed_video_title_view_text_color));
        this.d.setBackgroundDrawable(context2.getResources().getDrawable(R.drawable.bg_gradient_top_bottom));
        this.e.setTextColor(context2.getResources().getColor(R.color.feed_video_title_view_text_color));
        this.f.setTextColor(context2.getResources().getColor(R.color.feed_video_title_view_text_color));
        this.f.setBackgroundDrawable(context2.getResources().getDrawable(R.drawable.bg_gradient_bottom_top));
        context2 = getContext();
        this.c.setOnClickListener(new av(this));
        this.i.setOnClickListener(new aw(this));
        this.n.setOnBottomActionBarClickListener(new ax(this, context2));
        o.a().c = new ay(this);
        this.A.setAnimationListener(new az(this));
        this.g.setClickReplayListener(new ba(this));
    }

    public final void a(String str, ImageView imageView, c cVar) {
        com.nostra13.universalimageloader.core.d.a().a(str, imageView, cVar, new aq(this, imageView));
    }

    static c getSujectIconDisplayOptions() {
        c.a aVar = new c.a();
        aVar.a = 2130838201;
        aVar.b = 2130838201;
        aVar.c = 2130838201;
        aVar.m = true;
        aVar.h = true;
        aVar.a();
        aVar.q = new com.nostra13.universalimageloader.core.b.b();
        return aVar.b();
    }

    public final void setIsFirstElement(boolean z) {
        if (this.u != z) {
            this.u = z;
            if (z) {
                this.E.topMargin = 0;
                this.v.setLayoutParams(this.E);
                return;
            }
            this.E.topMargin = (int) getContext().getResources().getDimension(R.dimen.feed_video_view_item_spacing);
            this.v.setLayoutParams(this.E);
        }
    }

    private String getPlayerTag() {
        return "feed_player";
    }

    private void d() {
        if (this.h != null) {
            q.a().a(this.h);
        }
        this.h = q.a().a(getContext(), this.F, getPlayerTag());
        ab abVar = this.h;
        abVar.d(false);
        abVar.r = new bc(this);
        abVar.A = this.s;
        abVar.a();
        ai aiVar = new ai(String.valueOf(this.a.a), this.a.c, this.a.b);
        aiVar.i = "feedflow";
        aiVar.d = this.a.q;
        aiVar.j = this.a.b();
        this.h.a(aiVar);
        this.a.p = false;
    }

    private void setClickNiceBtnAnimation(Context context) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_in);
        loadAnimation.setAnimationListener(new as(this, AnimationUtils.loadAnimation(context, R.anim.scale_out)));
        this.n.getClickNiceImageView().startAnimation(loadAnimation);
    }

    private ShareBean getShareBean() {
        String str;
        if (getContext() instanceof ShortTimeVideoListActivity) {
            str = "channel_flow";
        } else {
            str = "short_video";
        }
        ShareBean shareBean = new ShareBean(str, this.a.a(), this.a.d, this.a.b, null);
        shareBean.g = true;
        return shareBean;
    }

    final void a() {
        Drawable colorDrawable = new ColorDrawable(getContext().getResources().getColor(R.color.feed_video_item_view_default_preview_color));
        com.nostra13.universalimageloader.core.d.a().a(this.c);
        this.c.setImageDrawable(colorDrawable);
        com.nostra13.universalimageloader.core.d.a().a(this.o);
        this.o.setImageDrawable(getResources().getDrawable(R.drawable.feedflow_icon_default));
    }

    final void b() {
        if (this.a != null) {
            if (this.a.p) {
                this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.g.setVisibility(0);
                this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.e.setVisibility(0);
            this.f.setVisibility(0);
        }
    }

    public final void c() {
        this.d.setVisibility(0);
        this.c.setVisibility(0);
        this.x.setVisibility(0);
        this.w.b();
        b();
    }

    public final void setFeedVideoAdapter(aj ajVar) {
        this.D = ajVar;
    }

    static /* synthetic */ void b(ap apVar) {
        if (com.xunlei.xllib.a.b.a(apVar.getContext())) {
            boolean z = true;
            if (com.xunlei.xllib.a.b.e(apVar.getContext())) {
                z = q.a().a(apVar.a == null ? BuildConfig.VERSION_NAME : String.valueOf(apVar.a.a), apVar.getContext(), new bb(apVar));
            }
            if (z) {
                apVar.d();
                return;
            }
            return;
        }
        XLToast.b(apVar.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }

    static /* synthetic */ void i(ap apVar) {
        if (apVar.a != null) {
            Intent intent = new Intent(apVar.getContext(), ReportActivity.class);
            intent.putExtra("report_target", MqttConnectOptions.MQTT_VERSION_3_1);
            intent.putExtra("extra_video_res_id", apVar.a.a);
            intent.putExtra("extra_video_gcid", apVar.a.q);
            apVar.getContext().startActivity(intent);
        }
    }

    static /* synthetic */ void q(ap apVar) {
        apVar.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        apVar.c.startAnimation(AnimationUtils.loadAnimation(apVar.getContext(), R.anim.media_player_poster_hide_animation));
    }
}
