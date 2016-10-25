package com.xunlei.downloadprovider.frame.user;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: GetUserCountsHelper.java
public final class c implements a {
    final /* synthetic */ a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("postUserLogin onFailure error=").append(wVar.getMessage());
    }
}
