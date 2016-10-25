package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SearchHistoryInfo.java
public class i extends w {
    o a;
    private int c;
    private a d;

    // compiled from: SearchHistoryInfo.java
    private class a implements OnClickListener {
        o a;

        a(o oVar) {
            this.a = oVar;
        }

        public final void onClick(View view) {
            if (this.a != null) {
                StartFromType startFromType = StartFromType.browser_history;
                BrowserUtil.a();
                BrowserUtil.a(i.this, i.this, false, startFromType);
                f.d("historylink", "website", i.this);
            }
        }
    }

    // compiled from: SearchHistoryInfo.java
    private class b {
        ImageView a;
        TextView b;
        TextView c;
        View d;

        private b() {
        }
    }

    public i(Context context, o oVar) {
        super(context);
        this.c = 2130838989;
        this.a = oVar;
        this.d = new a(this.a);
    }

    public int a() {
        return SimpleLog.LOG_LEVEL_FATAL;
    }

    public final void a(Object obj) {
        if (obj instanceof b) {
            b bVar = (b) obj;
            bVar.a.setImageDrawable(this.b.getResources().getDrawable(this.c));
            String str = this.a.a;
            if (str == null || str.trim().equals(BuildConfig.VERSION_NAME)) {
                bVar.b.setText(this.a.b.replace("http://", BuildConfig.VERSION_NAME));
            } else {
                bVar.b.setText(this.a.a);
            }
            bVar.c.setText(this.a.b);
            this.d.a = this.a;
            bVar.d.setOnClickListener(new a(this.a));
        }
    }

    public final Object a(View view) {
        b bVar = new b();
        bVar.a = (ImageView) view.findViewById(R.id.record_page_list_item_icon);
        bVar.b = (TextView) view.findViewById(R.id.record_page_list_item_name);
        bVar.c = (TextView) view.findViewById(R.id.record_page_list_item_url);
        bVar.d = view.findViewById(R.id.record_page_list_item_layout);
        return bVar;
    }

    public int b() {
        return R.layout.search_website_item_layout;
    }
}
