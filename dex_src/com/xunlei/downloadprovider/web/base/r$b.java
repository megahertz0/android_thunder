package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.d.a;
import com.xunlei.downloadprovidershare.R;
import org.android.spdy.SpdyProtocol;

// compiled from: KandanVideoAdapter.java
private class r$b extends r$c {
    final /* synthetic */ r a;
    private final TextView c;
    private final TextView d;
    private final ImageView e;
    private final TextView f;
    private final TextView g;
    private final View h;
    private final View i;

    public r$b(r rVar, View view) {
        this.a = rVar;
        super((byte) 0);
        this.h = view;
        this.c = (TextView) view.findViewById(R.id.tv_title);
        this.f = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_category);
        this.d = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_like_count);
        this.e = (ImageView) view.findViewById(com.xunlei.downloadprovider.R.id.iv_cover);
        this.g = (TextView) view.findViewById(com.xunlei.downloadprovider.R.id.tv_duration);
        this.i = view.findViewById(com.xunlei.downloadprovider.R.id.v_divider);
    }

    public final void a(s sVar) {
        if (sVar != null) {
            this.c.setText(sVar.i);
            this.f.setText(sVar.e);
            if (sVar.p > 0) {
                this.d.setText(String.format("%s\u4eba\u70b9\u8d5e", new Object[]{a.a((long) sVar.p, "\u4e07")}));
                this.d.setVisibility(0);
                this.i.setVisibility(0);
            } else {
                this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            r.b(this.a).a(sVar.k, this.e, r.a(this.a));
            if (sVar.m > 0) {
                this.g.setText(String.format("%02d:%02d", new Object[]{Long.valueOf(sVar.m / 60), Long.valueOf(sVar.m % 60)}));
                this.g.setVisibility(0);
                return;
            }
            this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }
}
