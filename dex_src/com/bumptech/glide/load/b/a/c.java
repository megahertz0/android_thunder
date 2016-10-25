package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;

// compiled from: FileDescriptorResourceLoader.java
public final class c extends o<ParcelFileDescriptor> implements b<Integer> {

    // compiled from: FileDescriptorResourceLoader.java
    public static class a implements n<Integer, ParcelFileDescriptor> {
        public final m<Integer, ParcelFileDescriptor> a(Context context, com.bumptech.glide.load.b.c cVar) {
            return new c(context, cVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public c(Context context, m<Uri, ParcelFileDescriptor> mVar) {
        super(context, (m) mVar);
    }
}
