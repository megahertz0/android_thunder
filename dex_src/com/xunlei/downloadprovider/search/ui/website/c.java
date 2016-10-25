package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;

// compiled from: SearchFavorTitleInfo.java
public final class c extends w {
    private int a;
    private int c;
    private int d;

    // compiled from: SearchFavorTitleInfo.java
    private class a {
        ImageView a;
        TextView b;
        TextView c;

        private a() {
        }
    }

    public c(Context context) {
        super(context);
        this.a = 2130838983;
        this.c = 2131232408;
        this.d = 2131232438;
    }

    public final int a() {
        return 0;
    }

    public final void a(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            aVar.b.setText(this.b.getString(this.c));
            aVar.a.setImageDrawable(this.b.getResources().getDrawable(this.a));
            aVar.c.setText(this.d);
            aVar.c.setOnClickListener(new d(this));
        }
    }

    public final Object a(View view) {
        a aVar = new a();
        aVar.a = (ImageView) view.findViewById(R.id.record_page_list_item_icon);
        aVar.b = (TextView) view.findViewById(R.id.search_item_title);
        aVar.c = (TextView) view.findViewById(R.id.search_scan_all);
        return aVar;
    }

    public final int b() {
        return R.layout.search_website_title_item_layout;
    }
}
