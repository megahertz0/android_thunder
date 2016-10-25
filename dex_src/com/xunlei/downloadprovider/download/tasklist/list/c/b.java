package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;

// compiled from: DownloadExceptionBanner.java
final class b implements OnClickListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (!this.a.e && !this.a.g) {
            if (this.a.f) {
                a.c("dl_fail");
                SearchActivity.a(this.a.i, "download_dl_fail_bar", this.a.d);
                return;
            }
            a.c("low_speed");
            SearchActivity.a(this.a.i, "download_low_speed_bar", this.a.d);
        }
    }
}
