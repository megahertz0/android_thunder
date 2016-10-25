package com.xunlei.downloadprovider.web.sniff.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.search.ui.widget.AutoListView;
import com.xunlei.downloadprovider.web.sniff.s;
import com.xunlei.downloadprovider.web.x;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import org.android.spdy.SpdyProtocol;

public class SuffixListView extends FrameLayout {
    private static ArrayList<a> h;
    public Context a;
    public LayoutInflater b;
    public View c;
    public TextView d;
    public TextView e;
    public AutoListView f;
    public s g;

    public static interface a {
        void a(x xVar);
    }

    static {
        h = new ArrayList();
    }

    public static void a(a aVar) {
        if (d.a(h) || !h.contains(aVar)) {
            h.add(aVar);
        }
    }

    public static void b(a aVar) {
        if (!d.a(h) && h.contains(aVar)) {
            h.remove(aVar);
        }
    }

    public SuffixListView(Context context) {
        super(context);
        this.a = context;
        if (this.a != null) {
            b();
        }
    }

    public SuffixListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        if (this.a != null) {
            b();
        }
    }

    @TargetApi(11)
    public SuffixListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void b() {
        this.b = LayoutInflater.from(this.a);
        this.c = this.b.inflate(R.layout.suffix_list_view, this);
        this.d = (TextView) this.c.findViewById(R.id.tv_middle_text);
        this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.e = (TextView) this.c.findViewById(R.id.tv_middle_line);
        this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.f = (AutoListView) this.c.findViewById(R.id.suffix_list_view);
        this.g = new s(getContext(), this.f);
        this.f.setAdapter(this.g);
        this.f.setOnItemClickListener(new h(this));
    }

    public void setSuffixListItems(ArrayList<x> arrayList) {
        int i = 0;
        if (!d.a(arrayList)) {
            this.d.setVisibility(0);
            this.e.setVisibility(0);
        }
        s sVar = this.g;
        if (!d.a(arrayList)) {
            sVar.d = arrayList;
            sVar.c.clear();
            if (sVar.d != null) {
                if (sVar.d.size() <= 3) {
                    sVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    sVar.c.addAll(sVar.d);
                } else {
                    sVar.b.setVisibility(0);
                    while (i < 3) {
                        sVar.c.add(sVar.d.get(i));
                        i++;
                    }
                }
            }
            sVar.notifyDataSetChanged();
            s.a(sVar.a);
        }
    }
}
