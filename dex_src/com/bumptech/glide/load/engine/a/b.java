package com.bumptech.glide.load.engine.a;

import com.bumptech.glide.h.h;
import com.xunlei.tdlive.R;
import java.util.Queue;

// compiled from: BaseKeyPool.java
abstract class b<T extends h> {
    private final Queue<T> a;

    protected abstract T a();

    b() {
        this.a = h.a((int) R.styleable.Toolbar_navigationIcon);
    }

    protected final T b() {
        h hVar = (h) this.a.poll();
        return hVar == null ? a() : hVar;
    }

    public final void a(T t) {
        if (this.a.size() < 20) {
            this.a.offer(t);
        }
    }
}
