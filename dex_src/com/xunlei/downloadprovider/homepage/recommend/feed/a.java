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
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.ReportActivity;
import com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity;
import com.xunlei.downloadprovider.player.MediaPlayerLoadingView;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.ai;
import com.xunlei.downloadprovider.player.b;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.player.t;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: ChannelFeedVideoItemView.java
public final class a extends RelativeLayout {
    private PopupWindow A;
    private AlphaAnimation B;
    private com.xunlei.downloadprovider.homepage.a C;
    private FrameLayout D;
    private com.xunlei.downloadprovider.homepage.recommend.a.a E;
    private LayoutParams F;
    private b G;
    private t H;
    public ao a;
    public FrameLayout b;
    public ImageView c;
    public DrawableLeftTextView d;
    public TextView e;
    public TextView f;
    public FeedItemShareLayout g;
    public ab h;
    public ViewGroup i;
    public TextView j;
    public TextView k;
    public ImageView l;
    public TextView m;
    public FeedVideoItemBottomView n;
    public ImageView o;
    public int p;
    public List<String> q;
    public TextView r;
    public int s;
    public int t;
    com.xunlei.downloadprovidershare.d.a u;
    private boolean v;
    private FrameLayout w;
    private MediaPlayerLoadingView x;
    private ImageView y;
    private TextView z;

    static /* synthetic */ void a(a aVar, View view, Context context) {
        if (aVar.a == null) {
            return;
        }
        if (aVar.q.contains(aVar.a.a) || aVar.a.e) {
            XLToast.b(context, XLToastType.XLTOAST_TYPE_ALARM, context.getResources().getString(2131231395));
            return;
        }
        aVar.A.showAsDropDown(view, g.a(context, 37.0f), -g.a(context, 46.0f));
        aVar.z.startAnimation(aVar.B);
        aVar.n.getClickNiceTextView().setSelected(true);
        aVar.n.getClickNiceImageView().setSelected(true);
        aVar.setClickNiceBtnAnimation(context);
        aVar.p++;
        aVar.a.h = aVar.p;
        aVar.a.e = true;
        if (aVar.p > 0) {
            aVar.n.getClickNiceTextView().setText(com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.p));
        }
        o.a().a(aVar.a.a, aVar.a.q);
        aVar.q.add(aVar.a.a);
        aa.a();
        aa.a(String.valueOf(aVar.a.a), aVar.p);
    }

    static /* synthetic */ void a(a aVar, From from) {
        ab abVar = aVar.h;
        int i = -1;
        if (abVar == null) {
            abVar = q.a().b("channel_player");
        }
        if (abVar != null) {
            i = abVar.a;
        }
        aVar.C.a = false;
        if (aVar.E != null) {
            aVar.E.h = true;
        }
        ShortMovieDetailActivity.a(aVar.getContext(), i, from, aVar.a, true);
    }

    public a(Context context, com.xunlei.downloadprovider.homepage.a aVar) {
        super(context);
        this.v = false;
        this.q = new ArrayList();
        this.u = new c(this);
        this.G = new e(this);
        this.H = new f(this);
        this.C = aVar;
        this.z = new TextView(getContext());
        this.z.setText("+1");
        this.z.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 12.0f);
        this.z.setTextColor(-15559434);
        this.A = new PopupWindow(this.z, -2, -2);
        this.A.setFocusable(false);
        this.A.setOutsideTouchable(true);
        this.A.setBackgroundDrawable(new ColorDrawable(0));
        this.B = new AlphaAnimation(1.0f, 0.0f);
        this.B.setDuration(1300);
        Context context2 = getContext();
        View inflate = LayoutInflater.from(context2).inflate(2130968658, this);
        int dimension = (int) context2.getResources().getDimension(2131362019);
        this.s = dimension;
        int dimension2 = (int) context2.getResources().getDimension(2131362028);
        this.w = (FrameLayout) inflate.findViewById(2131755487);
        this.F = new LayoutParams(-1, dimension);
        this.F.topMargin = dimension2;
        this.w.setLayoutParams(this.F);
        this.b = (FrameLayout) this.w.findViewById(2131755488);
        this.c = (ImageView) this.w.findViewById(2131755489);
        this.x = (MediaPlayerLoadingView) this.w.findViewById(2131755208);
        this.d = (DrawableLeftTextView) this.w.findViewById(2131755495);
        this.y = (ImageView) this.w.findViewById(2131756758);
        this.D = (FrameLayout) this.w.findViewById(2131755491);
        this.f = (TextView) this.D.findViewById(2131755492);
        this.e = (TextView) this.D.findViewById(2131755494);
        this.r = (TextView) this.D.findViewById(2131755493);
        this.g = (FeedItemShareLayout) this.w.findViewById(2131755490);
        this.i = (ViewGroup) inflate.findViewById(2131755497);
        this.j = (TextView) this.i.findViewById(2131755501);
        this.k = (TextView) this.i.findViewById(2131755503);
        this.l = (ImageView) this.i.findViewById(2131755499);
        this.m = (TextView) this.i.findViewById(2131755502);
        this.n = (FeedVideoItemBottomView) inflate.findViewById(2131755496);
        this.o = this.n.getSubjectIconImageView();
        context2 = getContext();
        a();
        this.d.setTextColor(context2.getResources().getColor(2131689653));
        this.d.setBackgroundDrawable(context2.getResources().getDrawable(2130837629));
        this.e.setTextColor(context2.getResources().getColor(2131689653));
        this.f.setTextColor(context2.getResources().getColor(2131689653));
        this.f.setBackgroundDrawable(context2.getResources().getDrawable(2130837627));
        context2 = getContext();
        this.c.setOnClickListener(new g(this));
        this.i.setOnClickListener(new h(this));
        this.n.setOnBottomActionBarClickListener(new i(this, context2));
        o.a().c = new j(this);
        this.B.setAnimationListener(new k(this));
        this.g.setClickReplayListener(new l(this));
    }

    public static c getSujectIconDisplayOptions() {
        com.nostra13.universalimageloader.core.c.a aVar = new com.nostra13.universalimageloader.core.c.a();
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
        if (this.v != z) {
            this.v = z;
            if (z) {
                this.F.topMargin = 0;
                this.w.setLayoutParams(this.F);
                return;
            }
            this.F.topMargin = (int) getContext().getResources().getDimension(2131362028);
            this.w.setLayoutParams(this.F);
        }
    }

    private String getPlayerTag() {
        return "channel_player";
    }

    private void d() {
        if (this.h != null) {
            q.a().a(this.h);
        }
        this.h = q.a().a(getContext(), this.G, getPlayerTag());
        ab abVar = this.h;
        abVar.A = this.t;
        abVar.d(false);
        abVar.r = new n(this);
        abVar.a();
        ai aiVar = new ai(String.valueOf(this.a.a), this.a.c, this.a.b);
        aiVar.i = "videoChannel";
        aiVar.d = this.a.q;
        this.h.a(aiVar);
        this.a.p = false;
    }

    private void setClickNiceBtnAnimation(Context context) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, 2131034186);
        loadAnimation.setAnimationListener(new d(this, AnimationUtils.loadAnimation(context, 2131034187)));
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

    public final void a() {
        Drawable colorDrawable = new ColorDrawable(getContext().getResources().getColor(2131689652));
        d.a().a(this.c);
        this.c.setImageDrawable(colorDrawable);
        d.a().a(this.o);
        this.o.setImageDrawable(getResources().getDrawable(2130838201));
    }

    public final void b() {
        if (this.a != null) {
            if (this.a.p) {
                this.y.setVisibility(XZBDevice.Wait);
                this.g.setVisibility(0);
                this.d.setVisibility(0);
                this.e.setVisibility(XZBDevice.Wait);
                this.r.setVisibility(XZBDevice.Wait);
                this.f.setVisibility(XZBDevice.Wait);
                return;
            }
            this.g.setVisibility(XZBDevice.Wait);
            this.e.setVisibility(0);
            this.r.setVisibility(0);
            this.f.setVisibility(0);
        }
    }

    public final void c() {
        this.d.setVisibility(0);
        this.c.setVisibility(0);
        this.y.setVisibility(0);
        this.x.b();
        b();
    }

    public final void setIsFeedType(boolean z) {
        this.g.setIsFeedType(z);
    }

    public final void setShortTimeVideoListAdapter(com.xunlei.downloadprovider.homepage.recommend.a.a aVar) {
        this.E = aVar;
    }

    static /* synthetic */ void b(a aVar) {
        if (com.xunlei.xllib.a.b.a(aVar.getContext())) {
            boolean z = true;
            if (com.xunlei.xllib.a.b.e(aVar.getContext())) {
                z = q.a().a(aVar.a == null ? com.umeng.a.d : String.valueOf(aVar.a.a), aVar.getContext(), new m(aVar));
            }
            if (z) {
                aVar.d();
                return;
            }
            return;
        }
        XLToast.b(aVar.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }

    static /* synthetic */ void i(a aVar) {
        if (aVar.a != null) {
            Intent intent = new Intent(aVar.getContext(), ReportActivity.class);
            intent.putExtra("report_target", XZBDevice.DOWNLOAD_LIST_FAILED);
            intent.putExtra("extra_video_res_id", aVar.a.a);
            intent.putExtra("extra_video_gcid", aVar.a.q);
            aVar.getContext().startActivity(intent);
        }
    }

    static /* synthetic */ void s(a aVar) {
        aVar.c.setVisibility(XZBDevice.Wait);
        aVar.c.startAnimation(AnimationUtils.loadAnimation(aVar.getContext(), 2131034162));
    }
}
