package com.xunlei.downloadprovider.personal.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: AboutBoxActivity.java
final class f implements OnClickListener {
    final /* synthetic */ AboutBoxActivity a;

    f(AboutBoxActivity aboutBoxActivity) {
        this.a = aboutBoxActivity;
    }

    public final void onClick(View view) {
        try {
            BrowserUtil.a();
            BrowserUtil.a(this.a, "http://weibo.com/shoujixunleixiazai", true, StartFromType.unknow);
            StatReporter.reportClick(5005, "weibo", null);
        } catch (Exception e) {
            XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231802));
        }
    }
}
