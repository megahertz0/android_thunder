package com.xunlei.downloadprovider.commonview;

import com.xunlei.common.register.XLRegErrorCode;

// compiled from: WebpageProgressBar.java
final class j implements Runnable {
    final /* synthetic */ WebpageProgressBar a;

    j(WebpageProgressBar webpageProgressBar) {
        this.a = webpageProgressBar;
    }

    public final void run() {
        int c = WebpageProgressBar.c(this.a);
        int d = WebpageProgressBar.d(this.a);
        if (d < WebpageProgressBar.c(this.a)) {
            c = WebpageProgressBar.e(this.a) == WebpageProgressBar$a.c ? XLRegErrorCode.REG_SUCCEED : 60 / (c / (c - d));
            if (c > 0) {
                this.a.setProgress(c + d);
            }
            if (this.a.isShown()) {
                WebpageProgressBar.f(this.a).postDelayed(this, 16);
                return;
            }
            return;
        }
        this.a.b();
    }
}
