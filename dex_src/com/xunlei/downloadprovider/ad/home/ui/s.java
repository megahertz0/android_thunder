package com.xunlei.downloadprovider.ad.home.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.web.base.a.af;
import com.xunlei.downloadprovider.web.base.model.t;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import com.xunlei.xllib.b.j;
import java.util.List;
import java.util.Random;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: VideoInfoViewHolder.java
public final class s extends af implements OnClickListener {
    private final TextView a;
    private final ImageView b;
    private final LinearLayout c;
    private final TextView d;
    private final ImageView e;
    private final HorizontalScrollView f;
    private final LinearLayout g;
    private final RelativeLayout h;
    private final ImageView i;
    private final TextView j;
    private final TextView k;
    private final TextView l;
    private final ImageView m;
    private final TextView n;
    private final com.xunlei.downloadprovider.web.base.a.ae.a o;
    private final c p;
    private u q;
    private boolean r;
    private boolean s;

    // compiled from: VideoInfoViewHolder.java
    private class a {
        private final String b;

        private a() {
            this.b = "ad_good_count";
        }

        public final int a() {
            int nextInt = new Random().nextInt(R.styleable.AppCompatTheme_controlBackground) + 10;
            int i = s.this.j.getContext().getSharedPreferences("ad_good_count", 0).getInt(s.this, -1);
            if (i == -1) {
                a(nextInt);
            }
            return i == -1 ? nextInt : i;
        }

        public final void a(int i) {
            s.this.j.getContext().getSharedPreferences("ad_good_count", 0).edit().putInt(s.this, i).apply();
        }

        public final boolean b() {
            return s.this.j.getContext().getSharedPreferences("ad_good_count", 0).getBoolean(s.this + "_done", false);
        }
    }

    public s(View view, com.xunlei.downloadprovider.web.base.a.ae.a aVar) {
        super(view);
        this.r = false;
        this.s = false;
        this.o = aVar;
        this.g = (LinearLayout) view.findViewById(com.xunlei.downloadprovider.R.id.ll_video_tag_container);
        this.f = (HorizontalScrollView) view.findViewById(com.xunlei.downloadprovider.R.id.hsv_video_tag);
        this.a = (TextView) view.findViewById(com.xunlei.downloadprovidershare.R.id.tv_title);
        this.b = (ImageView) view.findViewById(com.xunlei.downloadprovider.R.id.iv_toggle_title);
        this.l = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_ad_title);
        this.m = (ImageView) view.findViewById(com.xunlei.downloadprovider.R.id.iv_ad_icon);
        this.n = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.btn_ad_download);
        com.xunlei.downloadprovider.ad.common.a aVar2 = com.xunlei.downloadprovider.ad.home.a.c.a(this.n.getContext()).c.d;
        if (aVar2 != null) {
            this.n.setOnClickListener(new u(this, aVar2));
            d.a().a(aVar2.n(), this.m);
            this.l.setText(aVar2.m());
        }
        this.a.getViewTreeObserver().addOnPreDrawListener(new t(this));
        this.c = (LinearLayout) view.findViewById(com.xunlei.downloadprovider.R.id.lyt_channel);
        this.d = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_channel_title);
        this.e = (ImageView) view.findViewById(com.xunlei.downloadprovider.R.id.iv_channel_icon);
        this.h = (RelativeLayout) view.findViewById(com.xunlei.downloadprovider.R.id.lyt_good);
        this.i = (ImageView) view.findViewById(com.xunlei.downloadprovider.R.id.iv_good_icon);
        this.j = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_good_count);
        this.k = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_plus_one);
        com.nostra13.universalimageloader.core.c.a aVar3 = new com.nostra13.universalimageloader.core.c.a();
        aVar3.c = 2130838201;
        aVar3.m = true;
        aVar3.h = true;
        aVar3.a();
        aVar3.q = new com.nostra13.universalimageloader.core.b.c(Integer.MAX_VALUE);
        this.p = aVar3.b();
    }

    public final void a(t tVar) {
        if (tVar == null || !(tVar.b instanceof u)) {
            throw new IllegalArgumentException("itemData should be ShortMovieInfo type");
        } else if (this.q != tVar.b) {
            this.q = (u) tVar.b;
            this.s = false;
            this.a.setMaxLines(Integer.MAX_VALUE);
            this.s = false;
            this.a.setMaxLines(Integer.MAX_VALUE);
            this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            Object obj = this.q.b;
            if (TextUtils.isEmpty(obj)) {
                this.a.setText("   ");
            } else {
                this.a.setText(obj);
                a(obj, r.c().l.a());
            }
            if (this.q != null) {
                a aVar = new a();
                a(aVar.a());
                if (aVar.b()) {
                    a(false);
                }
                this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            this.b.setOnClickListener(this);
            this.a.setOnClickListener(this);
            this.b.setRotation(0.0f);
            this.c.setOnClickListener(this);
            this.h.setOnClickListener(this);
        }
    }

    private void a(String str, boolean z) {
        if (z && !TextUtils.isEmpty(str)) {
            if (this.g != null) {
                this.g.removeAllViews();
            }
            List a = j.a(str);
            if (a.isEmpty()) {
                this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            this.f.setVisibility(0);
            for (int i = 0; i < a.size(); i++) {
                String str2 = (String) a.get(i);
                View textView = new TextView(this.a.getContext());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 16;
                layoutParams.rightMargin = g.a(BrothersApplication.a(), 13.0f);
                textView.setText(str2);
                textView.setBackgroundResource(com.xunlei.downloadprovider.R.drawable.mark_select);
                textView.setGravity(SpdyProtocol.CUSTOM);
                textView.setTextSize(12.0f);
                textView.setTextColor(-15559434);
                textView.setOnClickListener(new v(this, str2));
                this.g.addView(textView, layoutParams);
            }
        }
    }

    private void a(int i) {
        String a = com.xunlei.downloadprovider.d.a.a((long) i, "\u4e07");
        if (a.trim().contentEquals("0")) {
            a = BuildConfig.VERSION_NAME;
        }
        this.j.setText(a + "\u8d5e");
    }

    private void a(boolean z) {
        if (z) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.a.getContext(), com.xunlei.downloadprovider.R.anim.scale_in);
            Animation loadAnimation2 = AnimationUtils.loadAnimation(this.a.getContext(), com.xunlei.downloadprovider.R.anim.scale_out);
            Animation loadAnimation3 = AnimationUtils.loadAnimation(this.a.getContext(), com.xunlei.downloadprovider.R.anim.scale_out2);
            loadAnimation.setAnimationListener(new w(this, loadAnimation2));
            loadAnimation3.setAnimationListener(new x(this));
            this.k.setVisibility(0);
            this.i.startAnimation(loadAnimation);
            this.k.startAnimation(loadAnimation3);
            if (this.q != null) {
                a aVar = new a();
                int a = aVar.a();
                this.q.l = a + 1;
                a(a + 1);
                aVar.a(a + 1);
                aVar.a.j.getContext().getSharedPreferences("ad_good_count", 0).edit().putBoolean(aVar.a.q.a + "_done", true).apply();
                return;
            }
            return;
        }
        this.j.setEnabled(false);
        this.i.setEnabled(false);
    }

    public final void onClick(View view) {
        if (this.o != null) {
            int id = view.getId();
            a aVar = new a();
            if (id == 2131755628) {
                if (this.q == null || aVar.b()) {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u60a8\u5df2\u70b9\u8d5e\u8fc7");
                } else {
                    a(true);
                }
            } else if (id == 2131756170 || id == 2131755473) {
                boolean z;
                this.b.animate().rotation(this.r ? 0.0f : 180.0f);
                this.a.setMaxLines(this.r ? SimpleLog.LOG_LEVEL_DEBUG : Integer.MAX_VALUE);
                if (this.r) {
                    z = false;
                } else {
                    z = true;
                }
                this.r = z;
            } else if (id == 2131756174) {
                this.o.a(this.c, SimpleLog.LOG_LEVEL_FATAL, this.q);
            }
        }
    }
}
