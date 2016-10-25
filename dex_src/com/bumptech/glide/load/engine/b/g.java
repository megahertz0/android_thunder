package com.bumptech.glide.load.engine.b;

import android.content.Context;
import com.bumptech.glide.load.engine.b.d.a;
import java.io.File;

// compiled from: InternalCacheDiskCacheFactory.java
final class g implements a {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    g(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final File a() {
        File cacheDir = this.a.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return this.b != null ? new File(cacheDir, this.b) : cacheDir;
    }
}
