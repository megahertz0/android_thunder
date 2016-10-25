package com.xunlei.downloadprovider.web.base.model;

import java.util.ArrayList;
import java.util.Collection;

// compiled from: HistoryRecordList.java
public final class b<T> extends ArrayList<T> {
    public int a;

    public b() {
        this.a = -1;
    }

    public final T a(T t) {
        if (this.a >= 0 && this.a < size() - 1) {
            removeRange(this.a + 1, size());
        }
        int i = this.a + 1;
        this.a = i;
        add(i, t);
        return t;
    }

    public final boolean a() {
        return this.a <= 0;
    }

    public final boolean b() {
        return this.a >= size() + -1;
    }

    public final void clear() {
        super.clear();
        this.a = -1;
    }

    public final boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public final void add(int i, T t) {
        super.add(i, t);
    }

    public final boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public final T remove(int i) {
        throw new UnsupportedOperationException();
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final T set(int i, T t) {
        throw new UnsupportedOperationException();
    }
}
