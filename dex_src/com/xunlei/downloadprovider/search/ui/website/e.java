package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: SearchFavorWebsite.java
public class e extends w {
    b a;
    private int c;

    // compiled from: SearchFavorWebsite.java
    private class a {
        ImageView a;
        TextView b;
        TextView c;
        View d;

        private a() {
        }
    }

    public e(Context context, b bVar) {
        super(context);
        this.c = 2130838989;
        this.a = bVar;
    }

    public int a() {
        return 1;
    }

    public final void a(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            aVar.a.setImageDrawable(this.b.getResources().getDrawable(this.c));
            String str = this.a.b;
            if (str == null || str.trim().equals(BuildConfig.VERSION_NAME)) {
                aVar.b.setText(this.a.c.replace("http://", BuildConfig.VERSION_NAME));
            } else {
                aVar.b.setText(this.a.b);
            }
            aVar.c.setText(this.a.c);
            aVar.d.setOnClickListener(new f(this));
        }
    }

    public final Object a(View view) {
        a aVar = new a();
        aVar.a = (ImageView) view.findViewById(R.id.record_page_list_item_icon);
        aVar.b = (TextView) view.findViewById(R.id.record_page_list_item_name);
        aVar.c = (TextView) view.findViewById(R.id.record_page_list_item_url);
        aVar.d = view.findViewById(R.id.record_page_list_item_layout);
        return aVar;
    }

    public int b() {
        return R.layout.search_website_item_layout;
    }
}
