package com.xunlei.downloadprovider.web.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.o;
import org.android.spdy.SpdyProtocol;

private final class RecordPageView$b extends com.xunlei.downloadprovider.e.a.a.a {
    final /* synthetic */ RecordPageView a;

    class a {
        public View a;
        public TextView b;
        public TextView c;
        public ImageView d;

        a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    private RecordPageView$b(RecordPageView recordPageView) {
        this.a = recordPageView;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getCount() {
        return RecordPageView.f(this.a).size();
    }

    public final Object getItem(int i) {
        return RecordPageView.f(this.a).get(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        t tVar = (t) RecordPageView.f(this.a).get(i);
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(BrothersApplication.a().getApplicationContext()).inflate(R.layout.record_page_item_ly, null);
            aVar = new a();
            aVar.c = (TextView) view.findViewById(R.id.record_page_list_item_url);
            aVar.b = (TextView) view.findViewById(R.id.record_page_list_item_name);
            aVar.d = (ImageView) view.findViewById(R.id.record_page_list_item_select_icon);
            aVar.a = view;
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (!RecordPageView.m(this.a)) {
            if (RecordPageView.n(this.a)) {
                aVar.d.setVisibility(0);
                if (tVar == null || !tVar.a) {
                    aVar.d.setImageResource(R.drawable.big_unselected);
                } else {
                    aVar.d.setImageResource(R.drawable.big_selected);
                }
            } else {
                aVar.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (tVar != null) {
                CharSequence charSequence;
                CharSequence charSequence2;
                if ("favor".equals(RecordPageView.d(this.a))) {
                    charSequence = ((b) tVar.b).b;
                    charSequence2 = ((b) tVar.b).c;
                } else {
                    charSequence = ((o) tVar.b).a;
                    charSequence2 = ((o) tVar.b).b;
                }
                aVar.b.setText(charSequence);
                aVar.c.setText(charSequence2);
            }
            if (tVar != null) {
                aVar.a.setOnClickListener(new y(this, tVar));
                aVar.a.setOnLongClickListener(new z(this, tVar));
            }
        }
        return view;
    }
}
