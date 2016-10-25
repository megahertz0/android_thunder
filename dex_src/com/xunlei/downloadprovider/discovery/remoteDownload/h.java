package com.xunlei.downloadprovider.discovery.remoteDownload;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.xllib.R;
import java.util.List;

// compiled from: RemoteDownloadActivity.java
final class h implements c {
    final /* synthetic */ List a;
    final /* synthetic */ a b;

    h(a aVar, List list) {
        this.b = aVar;
        this.a = list;
    }

    public final void a(DownloadService downloadService) {
        if (downloadService != null) {
            this.b.a.createTasks(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu, this.a, this.b.a.m, 41, null);
        }
    }
}
