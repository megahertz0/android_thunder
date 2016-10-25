package com.xunlei.downloadprovidercommon.b.a;

import android.os.Build.VERSION;
import com.xunlei.downloadprovider.a.b;
import java.util.HashMap;

// compiled from: SigRequest.java
final class d extends HashMap<String, String> {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
        put("Peer-Id", b.c());
        put("Product-Id", "37");
        put("channelId", b.g());
        put("Version-Code", String.valueOf(b.x()));
        put("Version-Name", b.w());
        put("Mobile-Type", "android");
        put("Platform-Version", VERSION.RELEASE);
    }
}
