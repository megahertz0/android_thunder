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
import com.xunlei.downloadprovider.homepage.choiceness.a.a.c;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class ChoicenessMovieSetItemView extends FrameLayout implements d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a a;

    private class a {
        View a;
        ImageView b;
        TextView c;
        RatingBar d;
        TextView e;

        private a() {
        }
    }

    private class b {
        TextView a;
        TextView b;
        View c;
        a d;
        a e;
        a f;

        private b() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        int i2;
        c cVar;
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        this.a = aVar;
        b bVar = (b) getTag();
        bVar.a.setText(aVar.k);
        if (aVar.a) {
            bVar.c.setVisibility(0);
        } else {
            bVar.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        List list = aVar.l;
        if (list == null) {
            i2 = 0;
        } else {
            i2 = list.size();
        }
        bVar.b.setText(aVar.m + "\u90e8");
        if (i2 > 0) {
            cVar = (c) list.get(0);
            bVar.d.a.setVisibility(0);
            if (!TextUtils.isEmpty(cVar.c)) {
                com.xunlei.downloadprovider.homepage.choiceness.a.a(cVar.c, bVar.d.b);
            }
            bVar.d.c.setText(cVar.a);
            bVar.d.d.setRating(cVar.f / 2.0f);
            bVar.d.e.setText(String.valueOf(cVar.f));
            bVar.d.a.setOnClickListener(new d(this, cVar, aVar));
        } else {
            bVar.d.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (i2 >= 2) {
            cVar = (c) list.get(1);
            bVar.e.a.setVisibility(0);
            if (!TextUtils.isEmpty(cVar.c)) {
                com.xunlei.downloadprovider.homepage.choiceness.a.a(cVar.c, bVar.e.b);
            }
            bVar.e.c.setText(cVar.a);
            bVar.e.d.setRating(cVar.f / 2.0f);
            bVar.e.e.setText(String.valueOf(cVar.f));
            bVar.e.a.setOnClickListener(new e(this, cVar, aVar));
        } else {
            bVar.e.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (i2 >= 3) {
            cVar = (c) list.get(SimpleLog.LOG_LEVEL_DEBUG);
            bVar.f.a.setVisibility(0);
            if (!TextUtils.isEmpty(cVar.c)) {
                com.xunlei.downloadprovider.homepage.choiceness.a.a(cVar.c, bVar.f.b);
            }
            bVar.f.c.setText(cVar.a);
            bVar.f.d.setRating(cVar.f / 2.0f);
            bVar.f.e.setText(String.valueOf(cVar.f));
            bVar.f.a.setOnClickListener(new f(this, cVar, aVar));
            return;
        }
        bVar.f.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final /* bridge */ /* synthetic */ boolean a(int i, e eVar) {
        return false;
    }

    public ChoicenessMovieSetItemView(Context context) {
        super(context);
        a(context);
    }

    public ChoicenessMovieSetItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChoicenessMovieSetItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_movie_set_item, this, true);
        b bVar = new b();
        bVar.a = (TextView) inflate.findViewById(R.id.item_title);
        bVar.b = (TextView) inflate.findViewById(R.id.item_more);
        bVar.c = inflate.findViewById(R.id.on_the_top_mask);
        bVar.d = new a();
        bVar.d.a = inflate.findViewById(R.id.item_movie1);
        bVar.d.b = (ImageView) bVar.d.a.findViewById(R.id.item_sub_icon);
        bVar.d.c = (TextView) bVar.d.a.findViewById(R.id.item_sub_title);
        bVar.d.d = (RatingBar) bVar.d.a.findViewById(R.id.item_sub_rating_bar);
        bVar.d.e = (TextView) bVar.d.a.findViewById(R.id.item_sub_rating_text);
        bVar.e = new a();
        bVar.e.a = inflate.findViewById(R.id.item_movie2);
        bVar.e.b = (ImageView) bVar.e.a.findViewById(R.id.item_sub_icon);
        bVar.e.c = (TextView) bVar.e.a.findViewById(R.id.item_sub_title);
        bVar.e.d = (RatingBar) bVar.e.a.findViewById(R.id.item_sub_rating_bar);
        bVar.e.e = (TextView) bVar.e.a.findViewById(R.id.item_sub_rating_text);
        bVar.f = new a();
        bVar.f.a = inflate.findViewById(R.id.item_movie3);
        bVar.f.b = (ImageView) bVar.f.a.findViewById(R.id.item_sub_icon);
        bVar.f.c = (TextView) bVar.f.a.findViewById(R.id.item_sub_title);
        bVar.f.d = (RatingBar) bVar.f.a.findViewById(R.id.item_sub_rating_bar);
        bVar.f.e = (TextView) bVar.f.a.findViewById(R.id.item_sub_rating_text);
        setTag(bVar);
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.a;
    }
}
