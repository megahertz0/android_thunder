package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;

// compiled from: LoadSSPExecutor.java
public final class l extends a implements a, d.a {
    private d e;

    public l(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        super(context, aVar);
    }

    public final void b() {
        super.b();
        ThunderReporter.a.b(d().getLoadSSPFailHubTypeName());
        g.a(this.c).a(0, d()).a(this, c());
        this.e = new d(this.b);
        this.e.a(this);
        this.e.a();
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (this.e != null && !this.e.e) {
            this.e.d = true;
        } else if (this.e != null && this.e.e) {
            return;
        }
        if (com.xunlei.xllib.b.d.a(list)) {
            e();
            return;
        }
        for (com.xunlei.downloadprovider.ad.common.a aVar : list) {
            if (!aVar.l() && aVar.b() != null && !aVar.b().trim().equals(BuildConfig.VERSION_NAME) && aVar.d() != null && !aVar.d().trim().equals(BuildConfig.VERSION_NAME)) {
                c.a(this.c).c.a(this.d, aVar);
                c.a(this.c).a();
                return;
            }
        }
    }

    private void e() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public final void a(int i, String str) {
        ThunderReporter.a.a("adv_homeflow_ssp_fail", d().getLoadSSPFailHubTypeName(), String.valueOf(i));
        if (this.e == null || !this.e.e) {
            e();
            if (this.e != null && !this.e.e) {
                this.e.d = true;
            }
        }
    }

    public final void a() {
        new StringBuilder("item timeout ").append(d().name()).append(" resId: ").append(this.d);
        e();
    }
}
