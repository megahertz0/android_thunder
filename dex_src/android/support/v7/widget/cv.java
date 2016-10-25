package android.support.v7.widget;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.t;

// compiled from: ViewInfoStore.java
final class cv {
    final ArrayMap<t, a> a;
    final LongSparseArray<t> b;

    // compiled from: ViewInfoStore.java
    static interface b {
        void a(t tVar);

        void a(t tVar, android.support.v7.widget.RecyclerView.e.b bVar, android.support.v7.widget.RecyclerView.e.b bVar2);

        void b(t tVar, android.support.v7.widget.RecyclerView.e.b bVar, android.support.v7.widget.RecyclerView.e.b bVar2);

        void c(t tVar, android.support.v7.widget.RecyclerView.e.b bVar, android.support.v7.widget.RecyclerView.e.b bVar2);
    }

    // compiled from: ViewInfoStore.java
    static class a {
        static Pool<a> d;
        int a;
        android.support.v7.widget.RecyclerView.e.b b;
        android.support.v7.widget.RecyclerView.e.b c;

        static {
            d = new SimplePool(20);
        }

        private a() {
        }

        static a a() {
            a aVar = (a) d.acquire();
            return aVar == null ? new a() : aVar;
        }

        static void a(a aVar) {
            aVar.a = 0;
            aVar.b = null;
            aVar.c = null;
            d.release(aVar);
        }

        static void b() {
            do {
            } while (d.acquire() != null);
        }
    }

    cv() {
        this.a = new ArrayMap();
        this.b = new LongSparseArray();
    }

    final void a() {
        this.a.clear();
        this.b.clear();
    }

    final void a(t tVar, android.support.v7.widget.RecyclerView.e.b bVar) {
        a aVar = (a) this.a.get(tVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(tVar, aVar);
        }
        aVar.b = bVar;
        aVar.a |= 4;
    }

    final boolean a(t tVar) {
        a aVar = (a) this.a.get(tVar);
        return (aVar == null || (aVar.a & 1) == 0) ? false : true;
    }

    final android.support.v7.widget.RecyclerView.e.b a(t tVar, int i) {
        android.support.v7.widget.RecyclerView.e.b bVar = null;
        int indexOfKey = this.a.indexOfKey(tVar);
        if (indexOfKey >= 0) {
            a aVar = (a) this.a.valueAt(indexOfKey);
            if (!(aVar == null || (aVar.a & i) == 0)) {
                aVar.a &= i ^ -1;
                if (i == 4) {
                    bVar = aVar.b;
                } else if (i == 8) {
                    bVar = aVar.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((aVar.a & 12) == 0) {
                    this.a.removeAt(indexOfKey);
                    a.a(aVar);
                }
            }
        }
        return bVar;
    }

    final void a(long j, t tVar) {
        this.b.put(j, tVar);
    }

    final void b(t tVar, android.support.v7.widget.RecyclerView.e.b bVar) {
        a aVar = (a) this.a.get(tVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(tVar, aVar);
        }
        aVar.c = bVar;
        aVar.a |= 8;
    }

    final void b(t tVar) {
        a aVar = (a) this.a.get(tVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(tVar, aVar);
        }
        r0.a |= 1;
    }

    final void c(t tVar) {
        a aVar = (a) this.a.get(tVar);
        if (aVar != null) {
            aVar.a &= -2;
        }
    }

    final void d(t tVar) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (tVar == this.b.valueAt(size)) {
                this.b.removeAt(size);
                break;
            }
        }
        a aVar = (a) this.a.remove(tVar);
        if (aVar != null) {
            a.a(aVar);
        }
    }
}
