package com.xunlei.downloadprovider.model.protocol.networkcheck;

import android.os.Message;
import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.tdlive.sdk.IHost;

// compiled from: IPAddressErrorActivity.java
final class f implements a {
    f() {
    }

    public final void onErrorResponse(w wVar) {
        IPAddressErrorActivity.a;
        Message.obtain(IPAddressErrorActivity.g, IHost.HOST_NOFITY_PAGE_DESELECTED).sendToTarget();
    }
}
