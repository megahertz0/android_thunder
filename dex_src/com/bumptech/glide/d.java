package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.e.e;
import com.bumptech.glide.g.b;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.n;

// compiled from: GenericTranscodeRequest.java
public final class d<ModelType, DataType, ResourceType> extends c<ModelType, DataType, ResourceType, ResourceType> {
    private final m<ModelType, DataType> g;
    private final Class<DataType> h;
    private final Class<ResourceType> i;
    private final b j;

    public d(Context context, e eVar, Class<ModelType> cls, m<ModelType, DataType> mVar, Class<DataType> cls2, Class<ResourceType> cls3, n nVar, h hVar, b bVar) {
        super(context, cls, new e(mVar, com.bumptech.glide.load.resource.e.e.b(), eVar.a((Class) cls2, (Class) cls3)), cls3, eVar, nVar, hVar);
        this.g = mVar;
        this.h = cls2;
        this.i = cls3;
        this.j = bVar;
    }
}
