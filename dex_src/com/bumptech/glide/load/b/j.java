package com.bumptech.glide.load.b;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.a;
import java.io.InputStream;
import java.io.OutputStream;

// compiled from: ImageVideoWrapperEncoder.java
public final class j implements a<i> {
    private final a<InputStream> a;
    private final a<ParcelFileDescriptor> b;
    private String c;

    public final /* bridge */ /* synthetic */ boolean a(Object obj, OutputStream outputStream) {
        i iVar = (i) obj;
        return iVar.a != null ? this.a.a(iVar.a, outputStream) : this.b.a(iVar.b, outputStream);
    }

    public j(a<InputStream> aVar, a<ParcelFileDescriptor> aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    public final String a() {
        if (this.c == null) {
            this.c = this.a.a() + this.b.a();
        }
        return this.c;
    }
}
