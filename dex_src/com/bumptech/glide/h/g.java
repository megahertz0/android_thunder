package com.bumptech.glide.h;

// compiled from: MultiClassKey.java
public final class g {
    private Class<?> a;
    private Class<?> b;

    public g(Class<?> cls, Class<?> cls2) {
        a(cls, cls2);
    }

    public final void a(Class<?> cls, Class<?> cls2) {
        this.a = cls;
        this.b = cls2;
    }

    public final String toString() {
        return new StringBuilder("MultiClassKey{first=").append(this.a).append(", second=").append(this.b).append('}').toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        if (this.a.equals(gVar.a)) {
            return this.b.equals(gVar.b);
        } else {
            return false;
        }
    }

    public final int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }
}
