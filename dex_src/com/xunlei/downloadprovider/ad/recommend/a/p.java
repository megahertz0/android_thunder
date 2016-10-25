package com.xunlei.downloadprovider.ad.recommend.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.List;

// compiled from: RecommendAdSSPExecutor.java
public final class p extends a implements a {
    public p(Context context, int i, String str, i.a aVar, String str2) {
        super(context, i, str, aVar, str2);
    }

    public final void b() {
        h hVar = new h();
        h.a(AD_TYPE.SOURCE_SSP_FLAG).a(this, this.e);
        com.xunlei.downloadprovider.ad.recommend.c.a.a(this.d, AD_TYPE.SOURCE_SSP_FLAG.getSourceName(), this.g);
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (list != null && !list.isEmpty()) {
            this.f.a((List) list, this.d, this.e);
        } else if (this.a != null) {
            this.a.b();
        } else {
            i.a aVar = this.f;
            int i = b.a.a.e;
            aVar.a(b.a.a.f, this.d, this.e);
        }
    }

    public final void a(int i, String str) {
        com.xunlei.downloadprovider.ad.recommend.c.a.a(this.d, AD_TYPE.SOURCE_SSP_FLAG.getSourceName(), String.valueOf(i), this.g);
        if (this.a != null) {
            this.a.b();
        } else {
            this.f.a(str, this.d, this.e);
        }
    }
}
