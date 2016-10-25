package com.bumptech.glide.load.b;

import android.os.ParcelFileDescriptor;
import java.io.InputStream;

// compiled from: ImageVideoWrapper.java
public class i {
    public final InputStream a;
    public final ParcelFileDescriptor b;

    public i(InputStream inputStream, ParcelFileDescriptor parcelFileDescriptor) {
        this.a = inputStream;
        this.b = parcelFileDescriptor;
    }
}
