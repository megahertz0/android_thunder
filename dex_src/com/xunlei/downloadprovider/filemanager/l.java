package com.xunlei.downloadprovider.filemanager;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.businessutil.a;
import com.xunlei.downloadprovider.filemanager.model.b;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PathChooserActivity.java
final class l implements OnClickListener {
    final /* synthetic */ PathChooserActivity a;

    l(PathChooserActivity pathChooserActivity) {
        this.a = pathChooserActivity;
    }

    public final void onClick(View view) {
        int i;
        String substring;
        String str = this.a.a;
        new StringBuilder("choosed path:").append(this.a.e.getCurrentPath());
        String currentPath = this.a.e.getCurrentPath();
        if (b.a(currentPath)) {
            i = 1;
            substring = currentPath.substring(k.b().length());
            str = "primarySDCard";
        } else {
            i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            substring = currentPath.substring(k.c().length());
            str = "slaverSDCard";
        }
        this.a.getApplicationContext();
        a.a(i, substring);
        d.a();
        d.a(currentPath);
        com.xunlei.downloadprovider.businessutil.b.a().g();
        StatReporter.reportClick(5006, str, substring);
        this.a.finish();
    }
}
