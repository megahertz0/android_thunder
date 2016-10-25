package com.xunlei.downloadprovider.ad.splash.b;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.common.d.a$c;
import com.xunlei.downloadprovider.ad.common.d.c;
import com.xunlei.downloadprovider.ad.splash.a.e;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.j.a;
import com.xunlei.downloadprovider.loading.LoadingActivity;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.downloads.task.b;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.List;

// compiled from: SplashSSPAd.java
public final class ab extends a {
    public ab(int i, BaseActivity baseActivity, String str, ViewGroup viewGroup, l lVar, d dVar) {
        super(i, baseActivity, str, viewGroup, lVar, dVar);
    }

    public final void b() {
        super.b();
        List arrayList = new ArrayList(1);
        arrayList.add(this.b);
        a.a().e().a(new c(new a$c(arrayList), new ac(this), new af(this)));
        c();
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_ssp_request");
    }

    protected final void d(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        String l = aVar.l();
        if (!TextUtils.isEmpty(l)) {
            int e = aVar.e();
            if (e == 2) {
                this.l.i();
                String str = BuildConfig.VERSION_NAME;
                String str2 = BuildConfig.VERSION_NAME;
                if (aVar instanceof e) {
                    str = ((com.xunlei.downloadprovider.ad.a.a) ((e) aVar).b).l;
                    str2 = ((com.xunlei.downloadprovider.ad.a.a) ((e) aVar).b).m;
                }
                if (this.e == 0) {
                    MainTabActivity.a(this.a, l, str, str2, com.xunlei.downloadprovider.service.a.a + aVar.p());
                } else if (this.e == 1 && (this.a instanceof ThunderTask)) {
                    g gVar = new g(3, l, null);
                    gVar.d = com.xunlei.downloadprovider.service.a.a + aVar.p();
                    b bVar = new b();
                    bVar.e = str;
                    bVar.c = str2;
                    bVar.d = true;
                    ((ThunderTask) this.a).createLocalTaskWithAdditionInfo(l, str, 0, null, null, null, 0, gVar, null, false, bVar);
                }
                this.l.e();
                this.a.finish();
            } else if (e != 4) {
                this.l.i();
                if (this.e == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from_key", "adv_launch");
                    if (this.a instanceof LoadingActivity) {
                        bundle.putString(com.xunlei.downloadprovider.thirdpart.a.a, ((LoadingActivity) this.a).c);
                    }
                    bundle.putString("ad_id", aVar.p());
                    BrowserUtil.a();
                    BrowserUtil.a(this.a, l, aVar.h(), R.styleable.AppCompatTheme_dialogTheme, bundle);
                } else if (this.e == 1) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("from_key", "adv_launch_background");
                    bundle2.putString("ad_id", aVar.p());
                    BrowserUtil.a();
                    BrowserUtil.a(this.a, l, aVar.h(), 0, bundle2);
                }
                this.l.e();
                this.a.finish();
            }
        }
    }

    protected final void e(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    public final void a() {
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_ssp_fail", new StringBuilder("timeout_").append(this.g.a).toString());
    }
}
