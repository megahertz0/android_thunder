package com.bumptech.glide.load.b;

import com.bumptech.glide.h.e;
import com.bumptech.glide.h.h;
import java.util.Queue;

// compiled from: ModelCache.java
public final class k<A, B> {
    public final e<a<A>, B> a;

    // compiled from: ModelCache.java
    static final class a<A> {
        private static final Queue<a<?>> a;
        private int b;
        private int c;
        private A d;

        static {
            a = h.a(0);
        }

        public static <A> a<A> a(A a) {
            a<A> aVar = (a) a.poll();
            if (aVar == null) {
                aVar = new a();
            }
            aVar.d = a;
            aVar.c = 0;
            aVar.b = 0;
            return aVar;
        }

        private a() {
        }

        public final void a() {
            a.offer(this);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.c == aVar.c && this.b == aVar.b && this.d.equals(aVar.d);
        }

        public final int hashCode() {
            return (((this.b * 31) + this.c) * 31) + this.d.hashCode();
        }
    }

    public k() {
        this(250);
    }

    public k(int i) {
        this.a = new l(this, i);
    }
}
