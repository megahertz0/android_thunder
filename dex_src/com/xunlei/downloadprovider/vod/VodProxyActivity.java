package com.xunlei.downloadprovider.vod;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.i;

public class VodProxyActivity extends Activity implements c {
    private String a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getIntent().getStringExtra("key_url");
        if (this.a == null) {
            finish();
        } else if (DownloadService.a() == null) {
            DownloadService.a((c) this);
        } else {
            a();
        }
    }

    public final void a(DownloadService downloadService) {
        a();
    }

    private void a() {
        LoginHelper a = LoginHelper.a();
        if (!(LoginHelper.c() || LoginHelper.p())) {
            a.u();
        }
        i iVar = new i();
        iVar.e = this.a;
        iVar.h = VodSourceType.normal;
        iVar.g = VodVideoFormat.flv;
        iVar.f = 1;
        VodUtil.a();
        VodUtil.a((Context) this, iVar);
        finish();
    }

    protected void onStop() {
        super.onStop();
        finish();
    }
}
