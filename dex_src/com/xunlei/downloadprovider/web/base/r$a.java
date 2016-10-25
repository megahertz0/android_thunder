package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.xunlei.downloadprovider.d.a;
import com.xunlei.downloadprovidershare.R;
import org.android.spdy.SpdyProtocol;

// compiled from: KandanVideoAdapter.java
private class r$a extends r$c {
    final /* synthetic */ r a;
    private final TextView c;
    private final RatingBar d;
    private final TextView e;
    private final TextView f;
    private final TextView g;
    private final ImageView h;
    private final View i;

    public r$a(r rVar, View view) {
        this.a = rVar;
        super((byte) 0);
        this.i = view;
        this.c = (TextView) view.findViewById(R.id.tv_title);
        this.d = (RatingBar) view.findViewById(com.xunlei.downloadprovider.R.id.rtb_score);
        this.e = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_score);
        this.f = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_introduction);
        this.g = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_like_count);
        this.h = (ImageView) view.findViewById(com.xunlei.downloadprovider.R.id.iv_cover);
    }

    public final void a(s sVar) {
        if (sVar != null) {
            this.c.setText(sVar.i);
            this.d.setRating(sVar.l / 2.0f);
            this.e.setText(String.format("%.1f", new Object[]{Float.valueOf(sVar.l)}));
            this.f.setText(sVar.j);
            if (sVar.p > 0) {
                this.g.setText(String.format("%s\u4eba\u70b9\u8d5e", new Object[]{a.a((long) sVar.p, "\u4e07")}));
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            r.b(this.a).a(sVar.k, this.h, r.a(this.a));
        }
    }
}
