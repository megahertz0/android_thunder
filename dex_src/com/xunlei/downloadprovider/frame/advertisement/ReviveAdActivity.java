package com.xunlei.downloadprovider.frame.advertisement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.splash.b.i;
import com.xunlei.downloadprovider.ad.splash.b.k;
import com.xunlei.downloadprovider.ad.splash.b.l;
import com.xunlei.downloadprovider.ad.splash.c.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;

public class ReviveAdActivity extends ThunderTask implements OnClickListener, c {
    public static final String a;
    private String b;
    private ViewGroup c;
    private View d;
    private boolean e;

    public ReviveAdActivity() {
        this.b = "key_localpath_for_ad";
        this.e = false;
    }

    static {
        a = ReviveAdActivity.class.getSimpleName();
    }

    public static void a(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ReviveAdActivity.class);
        intent.setFlags(1073741824);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = false;
        setContentView(2130968617);
        this.c = (ViewGroup) findViewById(2131755275);
        this.d = findViewById(2131755231);
        this.d.setVisibility(0);
        if (this.e) {
            a.a(1);
            return;
        }
        if (r.c().e != null) {
            r.a.a a = r.c().e.a();
            new StringBuilder("ReviveProcess adSwitch.canLoadLaunchAD: ").append(a.b);
            if (!a.b) {
                ThunderReporter.a.a("launch ad switch was closed");
                finish();
                return;
            }
        }
        a.a(1);
        if (b.a(this)) {
            AD_TYPE a2 = com.xunlei.downloadprovider.ad.common.b.a(i.a().b());
            new StringBuilder("ReviveProcess show adType: ").append(a2.name());
            k kVar = new k();
            d dVar = new d(Math.max(r.c().e.b(), XZBDevice.DOWNLOAD_LIST_FAILED));
            l aVar = new a(this, dVar);
            dVar.a(new b(this));
            com.xunlei.downloadprovider.ad.splash.b.a a3 = k.a(1, a2, this, this.c, aVar, dVar);
            if (a2 == AD_TYPE.SOURCE_SSP_FLAG) {
                a3.a(k.a(1, AD_TYPE.SOURCE_GDT_FLAG, this, this.c, aVar, dVar));
            }
            a3.b();
            dVar.a();
            return;
        }
        finish();
    }

    protected void onResume() {
        super.onResume();
        new StringBuilder("DownloadService.getInstance() == null: ").append(DownloadService.a() == null);
        if (DownloadService.a() == null) {
            DownloadService.a((c) this);
        } else {
            DownloadService.a();
        }
    }

    protected void onStop() {
        super.onStop();
        com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().e = -1;
        com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().d = -1;
    }

    protected void onDestroy() {
        this.e = true;
        super.onDestroy();
    }

    public void onBackPressed() {
    }

    public void onClick(View view) {
    }

    public final void a(DownloadService downloadService) {
    }

    static /* synthetic */ void a(ReviveAdActivity reviveAdActivity) {
        reviveAdActivity.c.setVisibility(0);
        reviveAdActivity.d.setVisibility(XZBDevice.Wait);
    }
}
