package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

// compiled from: BaseAbsAdapter.java
public abstract class a<T> extends BaseAdapter {
    protected List<T> a;
    protected Context b;
    protected LayoutInflater c;

    public abstract int a();

    public abstract void a(T t, j jVar);

    public a(Context context) {
        this.b = context;
        this.a = new ArrayList();
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.a.size();
    }

    public T getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        j jVar;
        Context context = this.b;
        int a = a();
        if (view == null) {
            jVar = new j(context, viewGroup, a);
        } else {
            jVar = (j) view.getTag();
        }
        a(getItem(i), jVar);
        return jVar.a;
    }

    public final void a(List<T> list) {
        if (list != null) {
            this.a.clear();
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }
}
