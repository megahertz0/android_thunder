package com.xunlei.downloadprovider.homepage.choiceness.ui.a;

import android.graphics.Bitmap;
import android.util.LruCache;

// compiled from: BitmapCache.java
final class c extends LruCache<String, Bitmap> {
    final /* synthetic */ b a;

    c(b bVar, int i) {
        this.a = bVar;
        super(i);
    }

    protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
        Bitmap bitmap = (Bitmap) obj2;
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
