package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SearchHotWebsiteTitleInfo.java
public final class o extends w {
    private int a;
    private int c;
    private int d;
    private a e;

    // compiled from: SearchHotWebsiteTitleInfo.java
    private class a {
        ImageView a;
        TextView b;
        TextView c;
        View d;

        private a() {
        }
    }

    public o(Context context) {
        super(context);
        this.a = 2130838990;
        this.c = 2131232420;
        this.d = 2131232438;
    }

    public final int a() {
        return SimpleLog.LOG_LEVEL_DEBUG;
    }

    public final void a(boolean z) {
        if (this.e == null) {
            return;
        }
        if (z) {
            this.e.d.setVisibility(0);
        } else {
            this.e.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final void a(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            this.e = aVar;
            aVar.b.setText(this.b.getString(this.c));
            aVar.a.setImageDrawable(this.b.getResources().getDrawable(this.a));
            aVar.c.setText(this.d);
            aVar.c.setOnClickListener(new p(this));
        }
    }

    public final Object a(View view) {
        a aVar = new a();
        aVar.a = (ImageView) view.findViewById(R.id.record_page_list_item_icon);
        aVar.b = (TextView) view.findViewById(R.id.search_item_title);
        aVar.c = (TextView) view.findViewById(R.id.search_scan_all);
        aVar.d = view.findViewById(R.id.view_gap);
        return aVar;
    }

    public final int b() {
        return R.layout.search_website_info_title;
    }
}
