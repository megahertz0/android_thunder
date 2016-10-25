package com.bumptech.glide.load.b;

import android.net.Uri;
import com.bumptech.glide.load.a.c;
import java.io.File;

// compiled from: FileLoader.java
public class b<T> implements m<File, T> {
    private final m<Uri, T> a;

    public final /* synthetic */ c a(Object obj, int i, int i2) {
        return this.a.a(Uri.fromFile((File) obj), i, i2);
    }

    public b(m<Uri, T> mVar) {
        this.a = mVar;
    }
}
