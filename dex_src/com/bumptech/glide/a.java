package com.bumptech.glide;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.e.f;
import com.bumptech.glide.f.b.j;
import com.bumptech.glide.f.d;
import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.n;

// compiled from: DrawableRequestBuilder.java
public class a<ModelType> extends c<ModelType, i, com.bumptech.glide.load.resource.d.a, b> {
    a(Context context, Class<ModelType> cls, f<ModelType, i, com.bumptech.glide.load.resource.d.a, b> fVar, e eVar, n nVar, h hVar) {
        super(context, cls, fVar, b.class, eVar, nVar, hVar);
        super.a(new com.bumptech.glide.f.a.a());
    }

    public final a<ModelType> a() {
        super.h();
        return this;
    }

    public final a<ModelType> b() {
        super.b(2130837804);
        return this;
    }

    public final a<ModelType> c() {
        super.a(2130837804);
        return this;
    }

    public final a<ModelType> a(d<? super ModelType, b> dVar) {
        super.b((d) dVar);
        return this;
    }

    public final a<ModelType> a(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    public final a<ModelType> d() {
        super.a(true);
        return this;
    }

    public final a<ModelType> a(com.bumptech.glide.load.b bVar) {
        super.b(bVar);
        return this;
    }

    public final a<ModelType> a(ModelType modelType) {
        super.b((Object) modelType);
        return this;
    }

    public final j<b> a(ImageView imageView) {
        return super.a(imageView);
    }

    final void e() {
        super.a(this.c.f);
    }

    final void f() {
        super.a(this.c.e);
    }

    public final /* bridge */ /* synthetic */ c g() {
        return (a) super.g();
    }

    public final /* bridge */ /* synthetic */ c a(int i, int i2) {
        super.a(i, i2);
        return this;
    }

    public final /* bridge */ /* synthetic */ c h() {
        super.h();
        return this;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (a) super.g();
    }
}
