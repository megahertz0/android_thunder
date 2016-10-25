package com.xunlei.downloadprovider.search.ui.home;

import android.content.Context;
import android.database.DataSetObserver;
import java.util.ArrayList;
import java.util.List;

// compiled from: BaseCustomAdapter.java
public abstract class a<T> extends com.xunlei.downloadprovider.e.a.a.a {
    public Context e;
    public List<T> f;

    public a(Context context) {
        this.f = new ArrayList();
        this.e = context;
    }

    public void a(List<T> list) {
        if (this.f == null) {
            this.f = new ArrayList();
        } else {
            this.f.clear();
        }
        if (list != null) {
            this.f.addAll(list);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f == null ? 0 : this.f.size();
    }

    public T getItem(int i) {
        return this.f.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    protected final int c() {
        return this.f == null ? 0 : this.f.size();
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        try {
            super.unregisterDataSetObserver(dataSetObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void d() {
        this.f.clear();
        notifyDataSetChanged();
    }
}
