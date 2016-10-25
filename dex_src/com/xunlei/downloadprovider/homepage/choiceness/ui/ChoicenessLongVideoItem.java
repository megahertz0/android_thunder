package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

public class ChoicenessLongVideoItem extends FrameLayout implements d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a a;

    private class a {
        TextView a;
        RatingBar b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        View h;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        this.a = aVar;
        a aVar2 = (a) getTag();
        aVar2.a.setText(aVar.k);
        aVar2.b.setRating(aVar.q / 2.0f);
        aVar2.c.setText(String.valueOf(aVar.q));
        TextView textView = aVar2.d;
        CharSequence charSequence = aVar.s;
        if (!TextUtils.isEmpty(charSequence)) {
            charSequence = charSequence.trim();
            int length = charSequence.length() - 1;
            int i2 = 0;
            while (i2 <= length && charSequence.charAt(i2) == '\u3000') {
                i2++;
            }
            int i3 = length;
            while (i3 >= i2 && charSequence.charAt(i3) == '\u3000') {
                i3--;
            }
            if (!(i2 == 0 && i3 == length) && i2 <= i3 + 1) {
                charSequence = charSequence.substring(i2, i3 + 1);
            }
        }
        textView.setText(charSequence);
        if (aVar.o == 0) {
            aVar2.e.setText(BuildConfig.VERSION_NAME);
        } else {
            aVar2.e.setText(getResources().getString(R.string.choiceness_like_count, new Object[]{com.xunlei.downloadprovider.homepage.choiceness.a.a(r1)}));
        }
        charSequence = aVar.j;
        if (TextUtils.isEmpty(charSequence)) {
            aVar2.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            aVar2.f.setVisibility(0);
            aVar2.f.setText(charSequence);
        }
        if (!TextUtils.isEmpty(aVar.f)) {
            com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.f, aVar2.g);
        }
        if (aVar.a) {
            aVar2.h.setVisibility(0);
        } else {
            aVar2.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final /* bridge */ /* synthetic */ boolean a(int i, e eVar) {
        return false;
    }

    public ChoicenessLongVideoItem(Context context) {
        super(context);
        a(context);
    }

    public ChoicenessLongVideoItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChoicenessLongVideoItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_long_video_item, this, true);
        a aVar = new a();
        aVar.a = (TextView) inflate.findViewById(R.id.item_title);
        aVar.b = (RatingBar) inflate.findViewById(R.id.item_rating_bar);
        aVar.c = (TextView) inflate.findViewById(R.id.item_rating_text);
        aVar.d = (TextView) inflate.findViewById(R.id.item_introduction);
        aVar.e = (TextView) inflate.findViewById(R.id.item_like_count);
        aVar.f = (TextView) inflate.findViewById(R.id.item_res_type);
        aVar.g = (ImageView) inflate.findViewById(R.id.item_icon);
        aVar.h = inflate.findViewById(R.id.on_the_top_mask);
        setTag(aVar);
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.a;
    }
}
