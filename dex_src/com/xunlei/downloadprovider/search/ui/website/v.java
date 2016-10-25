package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SearchWebsiteHistoryTimeTitle.java
public final class v extends w {
    private String a;

    // compiled from: SearchWebsiteHistoryTimeTitle.java
    private class a {
        TextView a;

        private a() {
        }
    }

    public v(Context context, String str) {
        super(context);
        this.a = str;
    }

    public final int a() {
        return SimpleLog.LOG_LEVEL_ERROR;
    }

    public final void a(Object obj) {
        if (obj instanceof a) {
            ((a) obj).a.setText(this.a);
        }
    }

    public final Object a(View view) {
        a aVar = new a();
        aVar.a = (TextView) view.findViewById(R.id.tv_date_title);
        return aVar;
    }

    public final int b() {
        return R.layout.search_website_his_date_title_layout;
    }
}
