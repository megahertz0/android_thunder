package com.xunlei.downloadprovider.frame.user;

import com.android.volley.r.a;
import com.android.volley.w;

// compiled from: GetUserCountsHelper.java
final class g implements a {
    final /* synthetic */ bo$a a;
    final /* synthetic */ a b;

    g(a aVar, bo$a com_xunlei_downloadprovider_frame_user_bo_a) {
        this.b = aVar;
        this.a = com_xunlei_downloadprovider_frame_user_bo_a;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("postUserOperation NX onFailure error =").append(wVar.getMessage());
        if (this.a != null) {
            this.a.a(wVar.toString(), "failed_report");
        }
    }
}
