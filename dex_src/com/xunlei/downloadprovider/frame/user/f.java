package com.xunlei.downloadprovider.frame.user;

import com.android.volley.r.b;

// compiled from: GetUserCountsHelper.java
final class f implements b<String> {
    final /* synthetic */ bo$a a;
    final /* synthetic */ a b;

    f(a aVar, bo$a com_xunlei_downloadprovider_frame_user_bo_a) {
        this.b = aVar;
        this.a = com_xunlei_downloadprovider_frame_user_bo_a;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        if (this.a != null) {
            this.a.a(null, "successed_report");
        }
    }
}
