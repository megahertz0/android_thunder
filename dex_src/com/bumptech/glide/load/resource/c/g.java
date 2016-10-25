package com.bumptech.glide.load.resource.c;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.b.m;

// compiled from: GifFrameModelLoader.java
final class g implements m<com.bumptech.glide.b.a, com.bumptech.glide.b.a> {

    // compiled from: GifFrameModelLoader.java
    private static class a implements c<com.bumptech.glide.b.a> {
        private final com.bumptech.glide.b.a a;

        public a(com.bumptech.glide.b.a aVar) {
            this.a = aVar;
        }

        public final void a() {
        }

        public final String b() {
            return String.valueOf(this.a.c);
        }

        public final void c() {
        }

        public final /* bridge */ /* synthetic */ Object a(Priority priority) throws Exception {
            return this.a;
        }
    }

    g() {
    }

    public final /* synthetic */ c a(Object obj, int i, int i2) {
        return new a((com.bumptech.glide.b.a) obj);
    }
}
