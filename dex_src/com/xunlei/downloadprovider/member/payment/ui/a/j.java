package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// compiled from: ViewHolder.java
public final class j {
    View a;
    private final SparseArray<View> b;

    j(Context context, ViewGroup viewGroup, int i) {
        this.b = new SparseArray();
        this.a = LayoutInflater.from(context).inflate(i, viewGroup, false);
        this.a.setTag(this);
    }

    public final <T extends View> T a(int i) {
        View view = (View) this.b.get(i);
        if (view != null) {
            return view;
        }
        T findViewById = this.a.findViewById(i);
        this.b.put(i, findViewById);
        return findViewById;
    }
}
