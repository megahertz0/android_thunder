package com.bumptech.glide.f;

// compiled from: ThumbnailRequestCoordinator.java
public final class f implements b, c {
    private b a;
    private b b;
    private c c;

    public f() {
        this(null);
    }

    public f(c cVar) {
        this.c = cVar;
    }

    public final void a(b bVar, b bVar2) {
        this.a = bVar;
        this.b = bVar2;
    }

    public final boolean a(b bVar) {
        boolean z;
        if (this.c == null || this.c.a(this)) {
            z = true;
        } else {
            Object obj = null;
        }
        return z && (bVar.equals(this.a) || !this.a.g());
    }

    public final boolean b(b bVar) {
        boolean z;
        if (this.c == null || this.c.b(this)) {
            z = true;
        } else {
            Object obj = null;
        }
        return z && bVar.equals(this.a) && !i();
    }

    public final boolean i() {
        boolean z;
        if (this.c == null || !this.c.i()) {
            z = false;
        } else {
            int i = 1;
        }
        return z || g();
    }

    public final void c(b bVar) {
        if (!bVar.equals(this.b)) {
            if (this.c != null) {
                this.c.c(this);
            }
            if (!this.b.f()) {
                this.b.c();
            }
        }
    }

    public final void b() {
        if (!this.b.e()) {
            this.b.b();
        }
        if (!this.a.e()) {
            this.a.b();
        }
    }

    public final void d() {
        this.a.d();
        this.b.d();
    }

    public final void c() {
        this.b.c();
        this.a.c();
    }

    public final boolean e() {
        return this.a.e();
    }

    public final boolean f() {
        return this.a.f() || this.b.f();
    }

    public final boolean g() {
        return this.a.g() || this.b.g();
    }

    public final boolean h() {
        return this.a.h();
    }

    public final void a() {
        this.a.a();
        this.b.a();
    }
}
