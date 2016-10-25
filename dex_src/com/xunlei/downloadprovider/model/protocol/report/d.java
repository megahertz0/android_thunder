package com.xunlei.downloadprovider.model.protocol.report;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import com.xunlei.b.a;
import com.xunlei.b.b;

@Deprecated
// compiled from: NewReportBox.java
public class d {
    private static final String b;
    b a;

    static {
        b = d.class.getName();
    }

    public d(Context context) {
        this.a = new b(context);
        MobclickAgent.updateOnlineConfig(context);
    }

    public final void a(a aVar) {
        this.a.a(aVar);
    }

    final void b(a aVar) {
        this.a.a(aVar);
    }
}
