package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.util.a;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: DownloadTaskDetailNormalInfoFragment.java
final class aj implements OnClickListener {
    final /* synthetic */ DownloadTaskDetailNormalInfoFragment a;

    aj(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment) {
        this.a = downloadTaskDetailNormalInfoFragment;
    }

    public final void onClick(View view) {
        int i = 1;
        String str;
        String e;
        if (a.a(this.a.g)) {
            if (n.d(this.a.g)) {
                str = "finish_install";
                e = n.e(this.a.g);
                if (!n.c(this.a.g)) {
                    i = 0;
                }
                com.xunlei.downloadprovider.download.report.a.a(str, e, i);
            } else if (n.a(this.a.g)) {
                str = "finish_play";
                e = n.e(this.a.g);
                if (!n.c(this.a.g)) {
                    i = 0;
                }
                com.xunlei.downloadprovider.download.report.a.a(str, e, i);
            } else {
                str = "finish_open";
                e = n.e(this.a.g);
                if (!n.c(this.a.g)) {
                    i = 0;
                }
                com.xunlei.downloadprovider.download.report.a.a(str, e, i);
            }
            if (n.d(this.a.g)) {
                this.a.b.a(this.a.g, BuildConfig.VERSION_NAME);
            } else {
                this.a.b.c(this.a.g);
                return;
            }
        }
        str = "dl_bxbb";
        e = n.e(this.a.g);
        if (!n.c(this.a.g)) {
            i = 0;
        }
        com.xunlei.downloadprovider.download.report.a.a(str, e, i);
        if (this.a.g != null && this.a.g.mTaskStatus == 4) {
            this.a.b.d(this.a.g);
        }
        this.a.b.a(this.a.g);
        if (this.a.e != null) {
            this.a.e.a();
        }
    }
}
