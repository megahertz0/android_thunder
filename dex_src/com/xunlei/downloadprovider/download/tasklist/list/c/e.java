package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.b;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.xllib.R;

// compiled from: DownloadFreeTrialBanner.java
final class e implements OnClickListener {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void onClick(View view) {
        if (this.a.b == 1) {
            d.a(this.a);
            a.a(this.a.f, PayFrom.DOWNLOAD_TASK_FREE_TRIAL, "v_an_shoulei_hytq_dlcenter_kthy");
        } else if (this.a.b == 0) {
            this.a.b = 1;
            com.xunlei.downloadprovider.download.report.a.b();
            LoginHelper.a();
            if (LoginHelper.c()) {
                b b = this.a.g;
                g.a();
                b.b(g.n());
                String a = com.xunlei.downloadprovider.d.a.a((long) (((double) this.a.c.mFileSize) * 0.2d));
                d dVar = this.a;
                g.a();
                dVar.a(g.b(a));
                if (this.a.c.mTaskStatus == 4) {
                    this.a.h.d(this.a.c);
                }
                this.a.c.mIsEnteredHighSpeedTrial = true;
                g.a().f(this.a.c.getTaskId());
                g.a().c(this.a.c.getTaskId());
                g.a().b(this.a.c.mDownloadedSize);
                g.a().c();
                return;
            }
            g.a().g = true;
            LoginHelper.a().a(this.a.f, null, R.styleable.Toolbar_collapseIcon);
        } else if (this.a.b == 2) {
            if (this.a.d != null) {
                this.a.d.onClick(view);
            }
            g.a().a(this.a.c.getTaskId(), g.c, true);
            d.f(this.a);
        }
    }
}
