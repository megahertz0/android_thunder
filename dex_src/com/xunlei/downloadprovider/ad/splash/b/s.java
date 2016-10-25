package com.xunlei.downloadprovider.ad.splash.b;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.android.volley.f;
import com.android.volley.p;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.j.a;
import com.xunlei.downloadprovider.loading.LoadingActivity;
import com.xunlei.downloadprovider.loading.a.b;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.xllib.R;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// compiled from: SplashInMobiAd.java
public final class s extends a {
    public s(int i, BaseActivity baseActivity, String str, ViewGroup viewGroup, l lVar, d dVar) {
        super(i, baseActivity, str, viewGroup, lVar, dVar);
    }

    public final void b() {
        super.b();
        a.a().e().a(new com.xunlei.downloadprovider.loading.a.a(this.a, new t(this), new v(this)));
        c();
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_inmobi_request");
    }

    protected final void d(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        if (aVar instanceof com.xunlei.downloadprovider.ad.splash.a.d) {
            BaseActivity baseActivity = this.a;
            b bVar = (b) ((com.xunlei.downloadprovider.ad.splash.a.d) aVar).b;
            String str = bVar.b;
            if (!TextUtils.isEmpty(str)) {
                if (bVar.c.equals("install") || bVar.c.equals("\u4e0b\u8f7d")) {
                    this.l.i();
                    if (this.e == 0) {
                        MainTabActivity.a((Activity) baseActivity, str, com.xunlei.downloadprovider.service.a.a + "inmobi");
                    } else if (this.e == 1 && (this.a instanceof ThunderTask)) {
                        g gVar = new g(3, str, null);
                        gVar.d = com.xunlei.downloadprovider.service.a.a + "inmobi";
                        ((ThunderTask) this.a).createLocalTask(str, null, 0, null, null, null, 0, gVar, null, false);
                    }
                    this.l.e();
                    this.a.finish();
                } else {
                    this.l.i();
                    if (this.e == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("from_key", "adv_launch");
                        if (this.a instanceof LoadingActivity) {
                            bundle.putString(com.xunlei.downloadprovider.thirdpart.a.a, ((LoadingActivity) this.a).c);
                        }
                        bundle.putString("ad_id", "inmobi");
                        BrowserUtil.a();
                        BrowserUtil.a(baseActivity, str, bVar.a, R.styleable.AppCompatTheme_dialogTheme, bundle);
                    } else if (this.e == 1) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("from_key", "adv_launch_background");
                        bundle2.putString("ad_id", "inmobi");
                        BrowserUtil.a();
                        BrowserUtil.a(this.a, str, bVar.a, 0, bundle2);
                    }
                    this.l.e();
                    this.a.finish();
                }
            }
            a(((b) ((com.xunlei.downloadprovider.ad.splash.a.d) aVar).b).a());
        }
    }

    protected final void e(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        if (aVar instanceof com.xunlei.downloadprovider.ad.splash.a.d) {
            b bVar = (b) ((com.xunlei.downloadprovider.ad.splash.a.d) aVar).b;
            if (bVar.f != null) {
                Set<String> hashSet = new HashSet();
                for (String str : bVar.f) {
                    if (str != null) {
                        hashSet.add(str.replace("$TS", String.valueOf(System.currentTimeMillis())));
                    }
                }
                bVar.f.clear();
                for (String str2 : hashSet) {
                    bVar.f.add(str2);
                }
            }
            a(bVar.f);
        }
    }

    final void a(List<String> list) {
        String defaultUserAgent;
        if (VERSION.SDK_INT >= 17) {
            defaultUserAgent = WebSettings.getDefaultUserAgent(this.a);
        } else {
            defaultUserAgent = new WebView(BrothersApplication.a()).getSettings().getUserAgentString();
        }
        if (list != null && list.size() != 0) {
            for (String str : list) {
                p e = a.a().e();
                y yVar = new y(this, str, new w(this, str), new x(this, str), defaultUserAgent);
                yVar.setRetryPolicy(new f(10000, 1, 1.0f));
                e.a(yVar);
            }
        }
    }

    public final void a() {
        com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_inmobi_fail", new StringBuilder("timeout_").append(this.g.a).toString());
    }
}
