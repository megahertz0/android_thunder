package com.xunlei.downloadprovider.web.core.b;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;

// compiled from: PageLoadReport.java
public final class a {
    public HashMap<String, String> a;
    public Handler b;

    public a() {
        this.b = new b(this, Looper.getMainLooper());
        this.a = new HashMap();
    }
}
