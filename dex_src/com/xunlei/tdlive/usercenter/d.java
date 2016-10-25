package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

// compiled from: ExtendAdapter.java
public abstract class d<T> extends BaseAdapter {
    List<T> a;
    Context b;
    b c;
    private boolean d;

    // compiled from: ExtendAdapter.java
    public static interface a {
        void a();
    }

    // compiled from: ExtendAdapter.java
    public static interface b {
        void b();
    }

    public abstract View a(Context context, T t, ViewGroup viewGroup);

    public abstract void a(int i);

    public abstract void a(View view, Context context, T t);

    public abstract void b(int i);

    public abstract void d();

    public d(Context context) {
        this.d = false;
        this.b = context;
    }

    public Context a() {
        return this.b;
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

    public void c(int i) {
        if (this.a != null && i >= 0 && i < this.a.size()) {
            this.a.remove(i);
            notifyDataSetChanged();
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(List<T> list) {
        if (list != null) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void b(List<T> list) {
        if (list == null) {
            this.a = null;
        } else {
            this.a = new ArrayList(list);
        }
        notifyDataSetChanged();
    }

    public void e() {
        if (this.c != null) {
            this.c.b();
        }
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public T getItem(int i) {
        return (this.a == null || i < 0 || i >= this.a.size()) ? null : this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Object item = getItem(i);
        if (view == null) {
            view = a(this.b, item, viewGroup);
        }
        a(view, this.b, item);
        return view;
    }
}
