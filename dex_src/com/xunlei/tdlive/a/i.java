package com.xunlei.tdlive.a;

import android.widget.BaseAdapter;
import com.xunlei.tdlive.a.j.a;
import com.xunlei.tdlive.modal.JsonWrapper;
import java.util.HashSet;

// compiled from: JsonArrayAdapter.java
public abstract class i<T> extends BaseAdapter implements j<T> {
    protected a a;
    private JsonWrapper b;
    private Object c;
    private boolean d;
    private boolean e;
    private HashSet<Integer> f;

    public i() {
        this.d = false;
        this.e = false;
        this.f = new HashSet();
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public boolean b() {
        if (this.d) {
            return false;
        }
        this.d = true;
        return true;
    }

    public void c() {
        this.d = false;
    }

    public void a(JsonWrapper jsonWrapper) {
        if (jsonWrapper != null && jsonWrapper.isArray()) {
            this.b = jsonWrapper;
            notifyDataSetChanged();
        }
    }

    public void b(JsonWrapper jsonWrapper) {
        if (jsonWrapper != null && jsonWrapper.isArray() && jsonWrapper.getLength() > 0) {
            if (this.b != null) {
                this.b.add(jsonWrapper);
            } else {
                this.b = jsonWrapper;
            }
            notifyDataSetChanged();
        }
    }

    public void a(JsonWrapper jsonWrapper, int i, int i2) {
        if (jsonWrapper != null && jsonWrapper.isArray()) {
            if (this.b != null) {
                this.b.replace(jsonWrapper, i, i2);
            } else {
                this.b = jsonWrapper;
            }
            notifyDataSetChanged();
        }
    }

    public void a(T t) {
        a(t, true, false);
    }

    public void b(T t) {
        a(t, false, false);
    }

    public void c(T t) {
        a(t, false, true);
    }

    public int getCount() {
        if (this.b != null) {
            return this.c == null ? this.b.getLength() : this.b.getLength() + 1;
        } else {
            return 0;
        }
    }

    public JsonWrapper a(int i) {
        if (this.b == null || !this.b.isArray() || i >= this.b.getLength()) {
            return null;
        }
        JsonWrapper object = this.b.getObject(i);
        return object == null ? this.b.getArray(i) : object;
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
