package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.q;

// compiled from: FileDescriptorStringLoader.java
public final class d extends q<ParcelFileDescriptor> implements b<String> {

    // compiled from: FileDescriptorStringLoader.java
    public static class a implements n<String, ParcelFileDescriptor> {
        public final m<String, ParcelFileDescriptor> a(Context context, c cVar) {
            return new d(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public d(m<Uri, ParcelFileDescriptor> mVar) {
        super(mVar);
    }
}
