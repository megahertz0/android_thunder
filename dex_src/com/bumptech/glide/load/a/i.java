package com.bumptech.glide.load.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

// compiled from: StreamLocalUriFetcher.java
public final class i extends g<InputStream> {
    protected final /* synthetic */ void a(Object obj) throws IOException {
        ((InputStream) obj).close();
    }

    public i(Context context, Uri uri) {
        super(context, uri);
    }

    protected final /* synthetic */ Object a(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return contentResolver.openInputStream(uri);
    }
}
