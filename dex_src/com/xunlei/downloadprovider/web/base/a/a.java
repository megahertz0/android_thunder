package com.xunlei.downloadprovider.web.base.a;

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
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.recommend.feed.aa;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.web.base.model.t;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.j;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: BaseInfoViewHolder.java
public final class a extends af implements OnClickListener {
    private final TextView a;
    private final ImageView b;
    private final View c;
    private final TextView d;
    private final ImageView e;
    private final HorizontalScrollView f;
    private final LinearLayout g;
    private final RelativeLayout h;
    private final ImageView i;
    private final TextView j;
    private final TextView k;
    private final View l;
    private final View m;
    private final View n;
    private final View o;
    private final com.xunlei.downloadprovider.web.base.a.ae.a p;
    private final c q;
    private final ImageView r;
    private u s;
    private boolean t;
    private boolean u;

    public a(View view, com.xunlei.downloadprovider.web.base.a.ae.a aVar) {
        super(view);
        this.t = false;
        this.u = false;
        this.p = aVar;
        this.g = (LinearLayout) view.findViewById(R.id.ll_video_tag_container);
        this.f = (HorizontalScrollView) view.findViewById(R.id.hsv_video_tag);
        this.a = (TextView) view.findViewById(com.xunlei.downloadprovidershare.R.id.tv_title);
        this.b = (ImageView) view.findViewById(R.id.iv_toggle_title);
        this.a.getViewTreeObserver().addOnPreDrawListener(new b(this));
        this.c = view.findViewById(R.id.lyt_channel);
        this.d = (TextView) view.findViewById(R.id.tv_channel_title);
        this.e = (ImageView) view.findViewById(R.id.iv_channel_icon);
        this.r = (ImageView) view.findViewById(R.id.iv_icon_extra);
        this.h = (RelativeLayout) view.findViewById(R.id.lyt_good);
        this.i = (ImageView) view.findViewById(R.id.iv_good_icon);
        this.j = (TextView) view.findViewById(R.id.tv_good_count);
        this.k = (TextView) view.findViewById(R.id.tv_plus_one);
        this.l = view.findViewById(R.id.tv_qzone);
        this.m = view.findViewById(R.id.tv_wxfriend);
        this.n = view.findViewById(R.id.tv_qq);
        this.o = view.findViewById(R.id.tv_weixin);
        com.nostra13.universalimageloader.core.c.a aVar2 = new com.nostra13.universalimageloader.core.c.a();
        aVar2.c = 2130838201;
        aVar2.m = true;
        aVar2.h = true;
        aVar2.a();
        aVar2.q = new com.nostra13.universalimageloader.core.b.c(Integer.MAX_VALUE);
        this.q = aVar2.b();
    }

    public final void a(t tVar) {
        if (tVar == null || !(tVar.b instanceof u)) {
            throw new IllegalArgumentException("itemData should be ShortMovieInfo type");
        } else if (this.s != tVar.b) {
            this.s = (u) tVar.b;
            this.u = false;
            this.a.setMaxLines(Integer.MAX_VALUE);
            this.u = false;
            this.a.setMaxLines(Integer.MAX_VALUE);
            this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            Object obj = this.s.b;
            if (TextUtils.isEmpty(obj)) {
                this.a.setText("   ");
            } else {
                this.a.setText(obj);
                a(obj, r.c().l.a());
            }
            if (this.s != null) {
                aa.a();
                int b = aa.b(this.s.a);
                if ((TextUtils.isEmpty(this.s.a) || !com.xunlei.downloadprovider.homepage.recommend.c.c.a().a(this.s.a)) && b <= this.s.l) {
                    a(this.s.l);
                } else {
                    a(b);
                }
                if (this.s.k) {
                    a(false);
                } else if (TextUtils.isEmpty(this.s.a) || !com.xunlei.downloadprovider.homepage.recommend.c.c.a().a(this.s.a)) {
                    this.j.setEnabled(true);
                    this.i.setEnabled(true);
                } else {
                    this.j.setEnabled(false);
                    this.i.setEnabled(false);
                }
                if (TextUtils.isEmpty(this.s.i)) {
                    this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    this.c.setVisibility(0);
                    this.d.setText(this.s.i);
                    if (!TextUtils.isEmpty(this.s.h)) {
                        d.a().a(this.s.h, this.e, this.q);
                    }
                    if (this.s.r > 0) {
                        this.r.setVisibility(0);
                        if (TextUtils.isEmpty(this.s.s)) {
                            this.r.setImageResource(R.drawable.big_img_v);
                        } else {
                            d.a().a(this.s.s, this.r, this.q);
                        }
                    } else {
                        this.r.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    }
                }
            }
            this.b.setOnClickListener(this);
            this.a.setOnClickListener(this);
            this.b.setRotation(0.0f);
            this.c.setOnClickListener(this);
            this.l.setOnClickListener(this);
            this.o.setOnClickListener(this);
            this.m.setOnClickListener(this);
            this.n.setOnClickListener(this);
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
                textView.setBackgroundResource(R.drawable.mark_select);
                textView.setGravity(SpdyProtocol.CUSTOM);
                textView.setTextSize(12.0f);
                textView.setTextColor(-15559434);
                textView.setOnClickListener(new c(this, str2));
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
            Animation loadAnimation = AnimationUtils.loadAnimation(this.a.getContext(), R.anim.scale_in);
            Animation loadAnimation2 = AnimationUtils.loadAnimation(this.a.getContext(), R.anim.scale_out);
            Animation loadAnimation3 = AnimationUtils.loadAnimation(this.a.getContext(), R.anim.scale_out2);
            loadAnimation.setAnimationListener(new d(this, loadAnimation2));
            loadAnimation3.setAnimationListener(new e(this));
            this.k.setVisibility(0);
            this.i.startAnimation(loadAnimation);
            this.k.startAnimation(loadAnimation3);
            if (this.s != null) {
                this.s.l++;
                a(this.s.l);
                return;
            }
            return;
        }
        this.j.setEnabled(false);
        this.i.setEnabled(false);
    }

    public final void onClick(View view) {
        if (this.p != null) {
            int id = view.getId();
            if (id == 2131755628) {
                if (!(this.s == null || this.s.k)) {
                    a(true);
                }
                this.p.a(this.h, 1, this.s);
            } else if (id == 2131756178) {
                this.p.a(this.o, SimpleLog.LOG_LEVEL_DEBUG, this.s);
            } else if (id == 2131755319) {
                this.p.a(this.n, MqttConnectOptions.MQTT_VERSION_3_1_1, this.s);
            } else if (id == 2131756180) {
                this.p.a(this.l, MqttConnectOptions.MQTT_VERSION_3_1, this.s);
            } else if (id == 2131756179) {
                this.p.a(this.m, SimpleLog.LOG_LEVEL_ERROR, this.s);
            } else if (id == 2131756170 || id == 2131755473) {
                this.b.animate().rotation(this.t ? 0.0f : 180.0f);
                this.a.setMaxLines(this.t ? 2 : Integer.MAX_VALUE);
                this.t = !this.t;
            } else if (id == 2131756174) {
                this.p.a(this.c, SimpleLog.LOG_LEVEL_FATAL, this.s);
            }
        }
    }
}
