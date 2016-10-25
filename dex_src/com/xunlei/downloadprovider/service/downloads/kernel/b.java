package com.xunlei.downloadprovider.service.downloads.kernel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

// compiled from: CursorLoaderCompat.java
public final class b extends CursorLoader {
    public b(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        super(context, uri, strArr, str, strArr2, str2);
    }

    public final Cursor loadInBackground() {
        try {
            return super.loadInBackground();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
