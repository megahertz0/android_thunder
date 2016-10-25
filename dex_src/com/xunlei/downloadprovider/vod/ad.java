package com.xunlei.downloadprovider.vod;

import android.content.Intent;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.util.ag.b;

// compiled from: VodPlayerActivity.java
final class ad extends b {
    final /* synthetic */ VodPlayerActivity a;

    ad(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a(Intent intent) {
        VodPlayerActivity.access$500();
        if (VodPlayerActivity.access$700(this.a) != null && !VodUtil.a(VodPlayerActivity.access$700(this.a).b) && VodPlayerActivity.access$800(this.a)) {
            if (!"dataEnabled".equals(intent.getStringExtra(DownloadManager.COLUMN_REASON))) {
                VodPlayerActivity.access$400(this.a).post(new ae(this));
            }
        }
    }

    public final void b(Intent intent) {
        VodPlayerActivity.access$500();
        if (intent != null && intent.getExtras() != null) {
            int i = intent.getExtras().getInt("level");
            int i2 = intent.getExtras().getInt("scale");
            if (i2 > 0) {
                VodPlayerActivity.access$1000(this.a).setPower((i * 100) / i2);
            }
        }
    }
}
