package com.xunlei.downloadprovider.web.base.a;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.e;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.web.base.a.ae.a;
import com.xunlei.downloadprovider.web.base.model.t;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyProtocol;

// compiled from: RecommendItemViewHolder.java
public final class ac extends af {
    private final ImageView a;
    private final TextView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final d f;
    private final c g;
    private final a h;
    private final View i;

    public ac(View view, a aVar) {
        super(view);
        this.h = aVar;
        this.i = view;
        this.a = (ImageView) view.findViewById(R.id.iv_poster);
        this.b = (TextView) view.findViewById(R.id.tv_duration);
        this.c = (TextView) view.findViewById(com.xunlei.downloadprovidershare.R.id.tv_title);
        this.d = (TextView) view.findViewById(R.id.tv_good);
        this.e = (TextView) view.findViewById(R.id.tv_play);
        this.f = d.a();
        this.f.a(e.a(view.getContext()));
        c.a aVar2 = new c.a();
        aVar2.a = 2130839160;
        aVar2.b = 2130839160;
        aVar2.c = 2130839160;
        aVar2.m = true;
        aVar2.h = true;
        aVar2.a();
        this.g = aVar2.b();
    }

    public final void a(t tVar) {
        if (tVar == null || !(tVar.b instanceof u)) {
            throw new IllegalArgumentException("itemData should be ShortMovieInfo type");
        }
        CharSequence charSequence;
        u uVar = (u) tVar.b;
        if (this.h != null) {
            this.i.setOnClickListener(new ad(this, uVar));
        }
        Object obj = uVar.c;
        if (!TextUtils.isEmpty(obj)) {
            this.f.a(obj, this.a, this.g);
        }
        long j = uVar.o;
        if (j > 0) {
            this.b.setText(j.b(j * 1000));
            this.b.setVisibility(0);
        } else {
            charSequence = uVar.d;
            if (TextUtils.isEmpty(charSequence)) {
                this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.b.setText(charSequence);
                this.b.setVisibility(0);
            }
        }
        charSequence = uVar.b;
        if (TextUtils.isEmpty(charSequence)) {
            this.c.setText("    ");
        } else {
            this.c.setText(charSequence);
        }
        String a = com.xunlei.downloadprovider.d.a.a((long) uVar.l, "\u4e07");
        if (TextUtils.isEmpty(a) || a.contentEquals("0")) {
            this.d.setText("0\u8d5e");
            this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.d.setText(a + "\u8d5e");
            this.d.setVisibility(0);
        }
        int i = uVar.m;
        if (i > 0) {
            this.e.setText(com.xunlei.downloadprovider.d.a.a((long) i, "\u4e07") + "\u6b21\u64ad\u653e");
            this.e.setVisibility(0);
            return;
        }
        this.e.setText("0");
        this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
