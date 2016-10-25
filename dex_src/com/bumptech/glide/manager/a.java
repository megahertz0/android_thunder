package com.bumptech.glide.manager;

import com.bumptech.glide.h.h;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

// compiled from: ActivityFragmentLifecycle.java
final class a implements h {
    private final Set<i> a;
    private boolean b;
    private boolean c;

    a() {
        this.a = Collections.newSetFromMap(new WeakHashMap());
    }

    public final void a(i iVar) {
        this.a.add(iVar);
        if (this.c) {
            iVar.b_();
        } else if (this.b) {
            iVar.b();
        } else {
            iVar.c();
        }
    }

    final void a() {
        this.b = true;
        for (i iVar : h.a(this.a)) {
            iVar.b();
        }
    }

    final void b() {
        this.b = false;
        for (i iVar : h.a(this.a)) {
            iVar.c();
        }
    }

    final void c() {
        this.c = true;
        for (i iVar : h.a(this.a)) {
            iVar.b_();
        }
    }
}
