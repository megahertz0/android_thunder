package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

// compiled from: StreamAssetPathFetcher.java
public final class h extends a<InputStream> {
    protected final /* synthetic */ void a(Object obj) throws IOException {
        ((InputStream) obj).close();
    }

    public h(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    protected final /* synthetic */ Object a(AssetManager assetManager, String str) throws IOException {
        return assetManager.open(str);
    }
}
