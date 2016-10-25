package com.xunlei.downloadprovider.frame.user;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: GetUserCountsHelper.java
final class e implements a {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("postUserOperation onFailure error=").append(wVar.getMessage());
    }
}
