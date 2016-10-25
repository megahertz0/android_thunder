package com.xunlei.downloadprovider.homepage.choiceness.ui.a;

import android.graphics.Bitmap;
import android.util.LruCache;

// compiled from: BitmapCache.java
public final class b implements com.android.volley.toolbox.i.b {
    private LruCache<String, Bitmap> a;

    public b() {
        this.a = new c(this, ((int) Runtime.getRuntime().maxMemory()) / 8);
    }

    public final Bitmap a(String str) {
        return (Bitmap) this.a.get(str);
    }

    public final void a(String str, Bitmap bitmap) {
        this.a.put(str, bitmap);
    }
}
