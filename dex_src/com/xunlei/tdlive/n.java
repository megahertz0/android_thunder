package com.xunlei.tdlive;

import com.xunlei.tdlive.user.f.b;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: LiveGiftDialog.java
class n implements b {
    final /* synthetic */ i a;

    n(i iVar) {
        this.a = iVar;
    }

    public void a(boolean z) {
        if (z) {
            RechargeActivity.a(this.a.getContext(), SimpleLog.LOG_LEVEL_DEBUG);
        }
    }
}
