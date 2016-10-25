package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

// compiled from: FileDescriptorAssetPathFetcher.java
public final class d extends a<ParcelFileDescriptor> {
    protected final /* synthetic */ void a(Object obj) throws IOException {
        ((ParcelFileDescriptor) obj).close();
    }

    public d(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    protected final /* synthetic */ Object a(AssetManager assetManager, String str) throws IOException {
        return assetManager.openFd(str).getParcelFileDescriptor();
    }
}
