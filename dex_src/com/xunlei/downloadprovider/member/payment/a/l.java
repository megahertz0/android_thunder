package com.xunlei.downloadprovider.member.payment.a;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.member.payment.a.k.a;

// compiled from: VoucherHelper.java
public final class l implements b<String> {
    final /* synthetic */ a a;
    final /* synthetic */ k b;

    public l(k kVar, a aVar) {
        this.b = kVar;
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        k.a(this.b, (String) obj, this.a);
    }
}
