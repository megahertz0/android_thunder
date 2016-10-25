package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.b;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import java.io.File;

// compiled from: FileDescriptorFileLoader.java
public final class a extends b<ParcelFileDescriptor> implements b<File> {

    // compiled from: FileDescriptorFileLoader.java
    public static class a implements n<File, ParcelFileDescriptor> {
        public final m<File, ParcelFileDescriptor> a(Context context, c cVar) {
            return new a(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public a(m<Uri, ParcelFileDescriptor> mVar) {
        super(mVar);
    }
}
