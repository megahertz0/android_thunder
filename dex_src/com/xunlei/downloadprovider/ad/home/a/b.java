package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.xllib.b.d;
import java.util.List;

// compiled from: FillDefaultADExecutor.java
public final class b extends a implements a {
    public b(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        super(context, aVar);
    }

    public final void b() {
        com.xunlei.downloadprovider.ad.common.a aVar = (com.xunlei.downloadprovider.ad.common.a) c.a(this.c).c.a.get(d());
        if (aVar == null) {
            ThunderReporter.a.b(d().getLoadSSPFailHubTypeName());
            g.a(this.c).a(0, d()).a(this, c());
            if (this.a != null) {
                this.a.b();
                return;
            }
            return;
        }
        c.a(this.c).a();
        c.a(this.c).c.a(this.d, aVar);
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (!d.a(list)) {
            for (com.xunlei.downloadprovider.ad.common.a aVar : list) {
                if (aVar.l()) {
                    c.a(this.c).c.a.put(d(), aVar);
                    return;
                }
            }
        }
    }

    public final void a(int i, String str) {
        ThunderReporter.a.a("adv_homeflow_ssp_fail", d().getLoadSSPFailHubTypeName(), String.valueOf(i));
    }
}
