package com.xunlei.downloadprovider.web.base.a;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.e;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.c.a.n;
import com.xunlei.downloadprovider.web.base.a.ae.a;
import com.xunlei.downloadprovider.web.base.model.t;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CommentItemViewHolder.java
public final class j extends af {
    private final ImageView a;
    private final TextView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final d f;
    private final c g;
    private final a h;
    private final View i;
    private final TextView j;
    private final TextView k;
    private final ImageView l;
    private final View m;
    private TextView n;
    private ImageView o;
    private View p;
    private com.xunlei.downloadprovider.c.a.c q;
    private boolean r;
    private boolean s;

    public j(View view, a aVar) {
        super(view);
        this.r = false;
        this.s = false;
        this.h = aVar;
        this.i = view;
        this.a = (ImageView) view.findViewById(R.id.iv_avatar);
        this.b = (TextView) view.findViewById(R.id.tv_name);
        this.c = (TextView) view.findViewById(R.id.tv_content);
        this.m = view.findViewById(R.id.lyt_target);
        this.k = (TextView) view.findViewById(R.id.tv_target);
        this.d = (TextView) view.findViewById(R.id.tv_time);
        this.j = (TextView) view.findViewById(R.id.tv_location);
        this.l = (ImageView) view.findViewById(R.id.iv_toggle);
        this.k.getViewTreeObserver().addOnPreDrawListener(new k(this));
        this.p = view.findViewById(R.id.lyt_good);
        this.o = (ImageView) view.findViewById(R.id.iv_good_icon);
        this.n = (TextView) view.findViewById(R.id.tv_good_count);
        this.e = (TextView) view.findViewById(R.id.tv_plus_one);
        view.setOnClickListener(new n(this));
        view.setOnLongClickListener(new o(this));
        this.a.setOnClickListener(new p(this));
        this.b.setOnClickListener(new q(this));
        this.k.setOnClickListener(new r(this));
        this.l.setOnClickListener(new s(this));
        this.p.setOnClickListener(new t(this));
        this.f = d.a();
        this.f.a(e.a(view.getContext()));
        c.a aVar2 = new c.a();
        aVar2.a = 2130838392;
        aVar2.b = 2130838392;
        aVar2.c = 2130838392;
        aVar2.m = true;
        aVar2.h = true;
        aVar2.a();
        aVar2.q = new com.nostra13.universalimageloader.core.b.c(Integer.MAX_VALUE);
        this.g = aVar2.b();
    }

    private void a(long j) {
        CharSequence a = com.xunlei.downloadprovider.d.a.a(j, "\u4e07");
        if (a.trim().contentEquals("0")) {
            a = BuildConfig.VERSION_NAME;
        }
        this.n.setText(a);
    }

    public final void a(t tVar) {
        boolean z = true;
        if (tVar == null || !(tVar.b instanceof com.xunlei.downloadprovider.c.a.c)) {
            throw new IllegalArgumentException("itemData should be CommentInfo type");
        } else if (this.q != tVar.b) {
            boolean z2;
            this.q = (com.xunlei.downloadprovider.c.a.c) tVar.b;
            this.r = false;
            this.k.setMaxLines(Integer.MAX_VALUE);
            this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.l.setRotation(0.0f);
            Object obj = this.q.k;
            if (!TextUtils.isEmpty(obj)) {
                this.f.a(obj, this.a, this.g);
            }
            this.b.setText(this.q.j);
            this.b.setVisibility(0);
            CharSequence charSequence = this.q.b;
            if (TextUtils.isEmpty(charSequence)) {
                this.c.setText("    ");
            } else {
                this.c.setText(charSequence);
            }
            ArrayList arrayList = this.q.q;
            if (arrayList == null || arrayList.size() <= 0) {
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                n nVar = (n) arrayList.get(0);
                if (nVar.a > 0) {
                    CharSequence spannableString = new SpannableString(nVar.d + ": " + nVar.b);
                    spannableString.setSpan(new m(this, nVar), 0, nVar.d.length(), com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
                    this.k.setText(spannableString);
                    this.k.setMovementMethod(LinkMovementMethod.getInstance());
                    this.k.setFocusable(true);
                } else {
                    this.k.setText("\u6b64\u6761\u8bc4\u8bba\u5df2\u88ab\u5220\u9664");
                }
                this.m.setVisibility(0);
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(this.q.g) && a(this.q.g)) {
                stringBuilder.append(this.q.g);
            }
            if (!TextUtils.isEmpty(this.q.h)) {
                stringBuilder.append(this.q.h);
            }
            stringBuilder.trimToSize();
            if (TextUtils.isEmpty(stringBuilder)) {
                stringBuilder.append("\u4e2d\u56fd");
            } else if (stringBuilder.length() > 10) {
                stringBuilder.delete(SpdyProtocol.PUBKEY_SEQ_OPEN, stringBuilder.length()).append("...");
            }
            if (!TextUtils.isEmpty(this.q.d)) {
                stringBuilder.append(this.q.d);
            }
            if (TextUtils.isEmpty(stringBuilder)) {
                this.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                stringBuilder.append("\u7528\u6237");
                this.j.setText(stringBuilder);
                this.j.setVisibility(0);
            }
            long j = this.q.c;
            if (j > 0) {
                this.d.setText(com.xunlei.xllib.b.j.a(j));
            } else {
                this.d.setText(BuildConfig.VERSION_NAME);
            }
            if (TextUtils.isEmpty(null)) {
                this.b.setCompoundDrawables(null, null, null, null);
                this.b.setTextColor(Color.parseColor("#666666"));
            } else {
                this.b.setTextColor(Color.parseColor("#f45f00"));
            }
            boolean z3 = this.q.m;
            a(this.q.n);
            TextView textView = this.n;
            if (z3) {
                z2 = false;
            } else {
                z2 = true;
            }
            textView.setEnabled(z2);
            ImageView imageView = this.o;
            if (z3) {
                z = false;
            }
            imageView.setEnabled(z);
        }
    }

    private static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        String[] strArr = new String[]{"\u5317\u4eac", "\u91cd\u5e86", "\u5929\u6d25", "\u4e0a\u6d77", "\u6fb3\u95e8", "\u9999\u6e2f"};
        for (int i = 0; i < 6; i++) {
            if (str.contains(strArr[i])) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ void j(j jVar) {
        boolean z;
        jVar.l.animate().rotation(jVar.s ? 0.0f : 180.0f);
        jVar.k.setMaxLines(jVar.s ? SimpleLog.LOG_LEVEL_DEBUG : Integer.MAX_VALUE);
        if (jVar.s) {
            z = false;
        } else {
            z = true;
        }
        jVar.s = z;
    }

    static /* synthetic */ void k(j jVar) {
        Animation loadAnimation = AnimationUtils.loadAnimation(jVar.p.getContext(), R.anim.scale_in);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(jVar.p.getContext(), R.anim.scale_out);
        Animation loadAnimation3 = AnimationUtils.loadAnimation(jVar.p.getContext(), R.anim.scale_out2);
        loadAnimation.setAnimationListener(new u(jVar, loadAnimation2));
        loadAnimation3.setAnimationListener(new l(jVar));
        jVar.e.setVisibility(0);
        jVar.o.startAnimation(loadAnimation);
        jVar.e.startAnimation(loadAnimation3);
        if (jVar.q != null) {
            jVar.q.n++;
            jVar.q.m = true;
            jVar.a(jVar.q.n);
        }
    }
}
