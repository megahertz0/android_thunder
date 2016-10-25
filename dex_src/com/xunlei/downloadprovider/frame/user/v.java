package com.xunlei.downloadprovider.frame.user;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.frame.user.ah.a;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: HistoryCommentItemViewHolder.java
public final class v extends aj<c> {
    private final View a;
    private TextView b;
    private View c;
    private a d;
    private TextView e;
    private c f;
    private final TextView g;
    private final ImageView h;
    private final View i;
    private TextView j;
    private ImageView k;
    private View l;
    private final TextView m;
    private final ImageView n;
    private final TextView o;
    private final TextView p;
    private final TextView q;
    private final TextView r;
    private boolean s;
    private boolean t;

    public v(View view, a aVar) {
        super(view);
        this.s = false;
        this.t = false;
        this.d = aVar;
        this.e = (TextView) view.findViewById(R.id.tv_time);
        this.j = (TextView) view.findViewById(R.id.tv_good_count);
        this.a = this.itemView.findViewById(R.id.lyt_content);
        this.b = (TextView) this.itemView.findViewById(R.id.tv_content);
        this.i = this.itemView.findViewById(R.id.lyt_target);
        this.g = (TextView) this.itemView.findViewById(R.id.tv_target);
        this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.h = (ImageView) this.itemView.findViewById(R.id.iv_toggle);
        this.l = this.itemView.findViewById(R.id.lyt_good);
        this.k = (ImageView) this.itemView.findViewById(R.id.iv_good_icon);
        this.j = (TextView) this.itemView.findViewById(R.id.tv_good_count);
        this.m = (TextView) this.itemView.findViewById(R.id.tv_plus_one);
        this.c = view.findViewById(R.id.lyt_video);
        this.c.setOnClickListener(new w(this));
        this.itemView.setOnClickListener(new x(this));
        this.itemView.setOnLongClickListener(new y(this));
        this.g.setOnClickListener(new z(this));
        this.h.setOnClickListener(new aa(this));
        this.l.setOnClickListener(new ab(this));
        this.n = (ImageView) this.itemView.findViewById(R.id.iv_poster);
        this.o = (TextView) this.itemView.findViewById(R.id.tv_duration);
        this.p = (TextView) this.itemView.findViewById(com.xunlei.downloadprovidershare.R.id.tv_title);
        this.q = (TextView) this.itemView.findViewById(R.id.tv_good);
        this.r = (TextView) this.itemView.findViewById(R.id.tv_play);
    }

    private void a(long j) {
        CharSequence a = com.xunlei.downloadprovider.d.a.a(j, "\u4e07");
        if (a.trim().contentEquals("0")) {
            a = BuildConfig.VERSION_NAME;
        }
        this.j.setText(a);
    }

    public final void a(ai<c> aiVar) {
        boolean z = true;
        if (this.f != aiVar.b) {
            this.f = (c) aiVar.b;
            this.s = false;
            this.g.setMaxLines(Integer.MAX_VALUE);
            this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.h.setRotation(0.0f);
            this.e.setText(j.a(this.f.c));
            boolean z2 = this.f.m;
            a(this.f.n);
            this.j.setEnabled(!z2);
            ImageView imageView = this.k;
            if (z2) {
                z = false;
            }
            imageView.setEnabled(z);
            CharSequence charSequence = this.f.b;
            if (TextUtils.isEmpty(charSequence)) {
                this.b.setText("    ");
            } else {
                this.b.setText(charSequence);
            }
            u uVar = this.f.s;
            if (uVar == null) {
                this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            CharSequence charSequence2;
            this.c.setVisibility(0);
            Object obj = uVar.c;
            if (!TextUtils.isEmpty(obj)) {
                d.a().a(obj, this.n, new ae(this));
            }
            long j = uVar.o;
            if (j > 0) {
                this.o.setText(j.b(j * 1000));
                this.o.setVisibility(0);
            } else {
                charSequence2 = uVar.d;
                if (TextUtils.isEmpty(charSequence2)) {
                    this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    this.o.setText(charSequence2);
                    this.o.setVisibility(0);
                }
            }
            charSequence2 = uVar.b;
            if (TextUtils.isEmpty(charSequence2)) {
                this.p.setText("    ");
            } else {
                this.p.setText(charSequence2);
            }
            String a = com.xunlei.downloadprovider.d.a.a((long) uVar.l, "\u4e07");
            if (TextUtils.isEmpty(a) || a.contentEquals("0")) {
                this.q.setText("0\u8d5e");
                this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.q.setText(a + "\u8d5e");
                this.q.setVisibility(0);
            }
            int i = uVar.m;
            if (i > 0) {
                this.r.setText(com.xunlei.downloadprovider.d.a.a((long) i, "\u4e07") + "\u6b21\u64ad\u653e");
                this.r.setVisibility(0);
                return;
            }
            this.r.setText("0");
            this.r.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    static /* synthetic */ void d(v vVar) {
        boolean z;
        vVar.h.animate().rotation(vVar.t ? 0.0f : 180.0f);
        vVar.g.setMaxLines(vVar.t ? SimpleLog.LOG_LEVEL_DEBUG : Integer.MAX_VALUE);
        if (vVar.t) {
            z = false;
        } else {
            z = true;
        }
        vVar.t = z;
    }

    static /* synthetic */ void e(v vVar) {
        Animation loadAnimation = AnimationUtils.loadAnimation(vVar.l.getContext(), R.anim.scale_in);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(vVar.l.getContext(), R.anim.scale_out);
        Animation loadAnimation3 = AnimationUtils.loadAnimation(vVar.l.getContext(), R.anim.scale_out2);
        loadAnimation.setAnimationListener(new ac(vVar, loadAnimation2));
        loadAnimation3.setAnimationListener(new ad(vVar));
        vVar.m.setVisibility(0);
        vVar.k.startAnimation(loadAnimation);
        vVar.m.startAnimation(loadAnimation3);
        if (vVar.f != null) {
            vVar.f.n++;
            vVar.f.m = true;
            vVar.a(vVar.f.n);
        }
    }
}
