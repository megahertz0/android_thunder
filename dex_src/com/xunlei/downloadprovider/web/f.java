package com.xunlei.downloadprovider.web;

import android.text.TextUtils;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.core.p;
import java.util.List;

// compiled from: CopyrightIntermediatePageActivity.java
final class f extends p {
    final /* synthetic */ CopyrightIntermediatePageActivity a;

    f(CopyrightIntermediatePageActivity copyrightIntermediatePageActivity) {
        this.a = copyrightIntermediatePageActivity;
    }

    public final void a() {
        if (!TextUtils.isEmpty(this.a.d)) {
            this.a.f.setVisibility(0);
        }
    }

    public final void a(List<DownData> list) {
        this.a.a((List) list);
    }
}
