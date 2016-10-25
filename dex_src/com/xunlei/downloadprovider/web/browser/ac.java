package com.xunlei.downloadprovider.web.browser;

import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.browser.InputAutoCompleteView.b;

// compiled from: BrowserTitleBarFragment.java
final class ac implements b {
    final /* synthetic */ a a;

    ac(a aVar) {
        this.a = aVar;
    }

    public final void a() {
        this.a.d.a();
    }

    public final void a(String str, boolean z) {
        StatReporter.reportBrowserPageShow(str, StartFromType.browser_history);
        BrowserUtil.a();
        String a = BrowserUtil.a(str);
        this.a.d.k.setText(a);
        if (z) {
            this.a.a();
            if (this.a.d.g != null) {
                this.a.d.g.a(a);
                return;
            }
            return;
        }
        this.a.d.k.requestFocus();
        this.a.d.k.setSelection(this.a.d.k.getText().length());
    }
}
