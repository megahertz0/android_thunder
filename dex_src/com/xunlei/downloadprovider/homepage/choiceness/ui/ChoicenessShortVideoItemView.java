package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyProtocol;

public class ChoicenessShortVideoItemView extends FrameLayout implements d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a a;

    private static class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        View g;
        ImageView h;
        ImageView i;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        this.a = aVar;
        a aVar2 = (a) getTag();
        aVar2.a.setText(aVar.k);
        if (aVar.b != 12) {
            if (aVar.n == 0) {
                aVar2.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar2.b.setVisibility(0);
                aVar2.b.setText(getResources().getString(R.string.choiceness_play_count, new Object[]{com.xunlei.downloadprovider.homepage.choiceness.a.a(r1)}));
            }
            if (aVar.o == 0) {
                aVar2.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar2.c.setVisibility(0);
                aVar2.c.setText(getResources().getString(R.string.choiceness_like_count, new Object[]{com.xunlei.downloadprovider.homepage.choiceness.a.a(r1)}));
            }
            CharSequence charSequence = aVar.p;
            if (charSequence == null || charSequence.trim().equals(BuildConfig.VERSION_NAME)) {
                aVar2.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar2.d.setVisibility(0);
                aVar2.d.setText(charSequence);
            }
            if (TextUtils.isEmpty(aVar.j)) {
                aVar2.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar2.f.setVisibility(0);
                aVar2.f.setText(aVar.j);
            }
        } else {
            aVar2.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar2.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar2.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar2.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (aVar.b == 14) {
            aVar2.i.setVisibility(0);
        } else {
            aVar2.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (aVar.a) {
            aVar2.g.setVisibility(0);
        } else {
            aVar2.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (aVar.c()) {
            aVar2.e.setVisibility(0);
            aVar2.e.setText(j.b((long) (aVar.t * 1000)));
        } else {
            aVar2.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (!TextUtils.isEmpty(aVar.f)) {
            if (aVar.b == 10) {
                getContext();
                com.xunlei.downloadprovider.homepage.choiceness.a.b(aVar.f, aVar2.h);
                return;
            }
            com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.f, aVar2.h);
        }
    }

    public final /* bridge */ /* synthetic */ boolean a(int i, e eVar) {
        return false;
    }

    public ChoicenessShortVideoItemView(Context context) {
        super(context);
        a(context);
    }

    public ChoicenessShortVideoItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChoicenessShortVideoItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_short_video_item, this, true);
        a aVar = new a();
        aVar.a = (TextView) inflate.findViewById(R.id.item_title);
        aVar.b = (TextView) inflate.findViewById(R.id.item_play_count);
        aVar.c = (TextView) inflate.findViewById(R.id.item_like_count);
        aVar.d = (TextView) inflate.findViewById(R.id.item_tag);
        aVar.e = (TextView) inflate.findViewById(R.id.item_duration);
        aVar.f = (TextView) inflate.findViewById(R.id.item_res_type);
        aVar.g = inflate.findViewById(R.id.on_the_top_mask);
        aVar.h = (ImageView) inflate.findViewById(R.id.item_icon);
        aVar.i = (ImageView) inflate.findViewById(R.id.item_play_icon);
        setTag(aVar);
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.a;
    }
}
