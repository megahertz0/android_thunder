package com.baidu.mobads.openad.b;

import android.webkit.CookieManager;

final class c implements Runnable {
    c() {
    }

    public final void run() {
        CookieManager.getInstance().removeExpiredCookie();
    }
}
