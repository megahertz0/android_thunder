package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.a.d;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.r;

// compiled from: FileDescriptorUriLoader.java
public final class e extends r<ParcelFileDescriptor> implements b<Uri> {

    // compiled from: FileDescriptorUriLoader.java
    public static class a implements n<Uri, ParcelFileDescriptor> {
        public final m<Uri, ParcelFileDescriptor> a(Context context, c cVar) {
            return new e(context, cVar.a(com.bumptech.glide.load.b.e.class, ParcelFileDescriptor.class));
        }
    }

    public e(Context context, m<com.bumptech.glide.load.b.e, ParcelFileDescriptor> mVar) {
        super(context, mVar);
    }

    protected final com.bumptech.glide.load.a.c<ParcelFileDescriptor> a(Context context, Uri uri) {
        return new com.bumptech.glide.load.a.e(context, uri);
    }

    protected final com.bumptech.glide.load.a.c<ParcelFileDescriptor> a(Context context, String str) {
        return new d(context.getApplicationContext().getAssets(), str);
    }
}
