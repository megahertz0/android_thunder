package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.e;
import com.bumptech.glide.e.f;
import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.resource.d.a;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.n;
import java.io.InputStream;

// compiled from: DrawableTypeRequest.java
public final class b<ModelType> extends a<ModelType> {
    private final m<ModelType, InputStream> g;
    private final m<ModelType, ParcelFileDescriptor> h;
    private final com.bumptech.glide.g.b i;

    b(Class<ModelType> cls, m<ModelType, InputStream> mVar, m<ModelType, ParcelFileDescriptor> mVar2, Context context, e eVar, n nVar, h hVar, com.bumptech.glide.g.b bVar) {
        f fVar;
        Class cls2 = a.class;
        Class cls3 = com.bumptech.glide.load.resource.a.b.class;
        if (mVar == null && mVar2 == null) {
            fVar = null;
        } else {
            fVar = new e(new com.bumptech.glide.load.b.h(mVar, mVar2), eVar.d.a(cls2, cls3), eVar.a(i.class, cls2));
        }
        super(context, cls, fVar, eVar, nVar, hVar);
        this.g = mVar;
        this.h = mVar2;
        this.i = bVar;
    }
}
