package com.xunlei.downloadprovider.download.tasklist.list.b;

import com.xunlei.downloadprovider.download.tasklist.a.a;

// compiled from: TaskCardItem.java
public final class e {
    public final int a;
    public long b;
    public Object c;
    protected boolean d;

    public e(int i, Object obj, long j) {
        this.b = -1;
        this.a = i;
        this.c = obj;
        this.b = j;
    }

    public e() {
        this(3, null, -1);
    }

    public final <T> T a(Class<T> cls) {
        if (this.c == null) {
            return null;
        }
        try {
            return cls.cast(this.c);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public final boolean a() {
        return this.d;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final a b() {
        return this.c instanceof a ? (a) this.c : null;
    }
}
