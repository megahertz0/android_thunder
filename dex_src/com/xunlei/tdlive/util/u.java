package com.xunlei.tdlive.util;

import android.os.SystemClock;
import com.xunlei.tdlive.util.f.e;
import com.xunlei.tdlive.util.f.h;

// compiled from: StreamDNS.java
final class u extends e<String> {
    u() {
    }

    public final void onSuccess(h<String> hVar) {
        if (hVar != null && hVar.b == 200 && hVar.a != null) {
            t.b(((String) hVar.a).trim());
            t.a(SystemClock.elapsedRealtime());
        }
    }

    public final void onFailure(f$a com_xunlei_tdlive_util_f_a, String str) {
        t.a(0);
    }
}
