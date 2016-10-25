package com.xunlei.downloadprovider.ad.home.a;

import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import java.util.List;

// compiled from: LoadADClient.java
public final class e implements a {
    final /* synthetic */ c a;
    private AD_LAYOUT_TYPE b;

    public e(c cVar, AD_LAYOUT_TYPE ad_layout_type) {
        this.a = cVar;
        this.b = ad_layout_type;
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        for (com.xunlei.downloadprovider.ad.common.a aVar : list) {
            if (aVar.l()) {
                c.a(c.b(this.a)).c.a.put(this.b, aVar);
                return;
            }
        }
    }

    public final void a(int i, String str) {
        ThunderReporter.a.a("adv_homeflow_ssp_fail", this.b.getLoadSSPFailHubTypeName(), String.valueOf(i));
    }
}
