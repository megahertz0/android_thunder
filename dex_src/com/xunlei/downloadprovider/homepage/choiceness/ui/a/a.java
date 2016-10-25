package com.xunlei.downloadprovider.homepage.choiceness.ui.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

// compiled from: BaseExposureAdapter.java
public abstract class a<T> extends com.xunlei.downloadprovider.search.ui.home.a<T> {
    private int a;
    private boolean b;
    public long d;

    public a(Context context) {
        super(context);
        this.a = 0;
    }

    private boolean e() {
        return this.a == 2;
    }

    public final void b(int i) {
        if (e() && this.a != i) {
            b();
        }
        this.a = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!e() && this.b) {
            boolean z = false;
            if (System.currentTimeMillis() - this.d <= 1000) {
                z = true;
            }
            a(i, z);
        }
        return null;
    }

    public void notifyDataSetChanged() {
        this.d = System.currentTimeMillis();
        super.notifyDataSetChanged();
    }

    public void a(int i, boolean z) {
    }

    public void b() {
    }

    public void a(boolean z) {
        this.b = true;
    }

    public void a() {
        this.b = false;
    }
}
