package com.xunlei.downloadprovider.homepage.choiceness.ui.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.nostra13.universalimageloader.core.d;

// compiled from: MultiTypeListAdapter.java
public abstract class f<T extends e> extends a<T> {
    private d a;

    public abstract View a(int i);

    public f(Context context) {
        super(context);
        this.a = d.a();
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View a;
        super.getView(i, view, viewGroup);
        int itemViewType = getItemViewType(i);
        e eVar = (e) getItem(i);
        if (view == null) {
            a = a(itemViewType);
        } else {
            a = view;
        }
        if (a instanceof d) {
            ((d) a).a(i, this, a, eVar);
        }
        return a;
    }

    public int getItemViewType(int i) {
        return ((e) getItem(i)).a();
    }
}
