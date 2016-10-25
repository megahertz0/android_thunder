package com.xunlei.downloadprovider.web.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.e;
import java.util.List;

// compiled from: KandanVideoAdapter.java
public final class r extends BaseAdapter {
    List<s> a;
    private final Context b;
    private final d c;
    private final c d;
    private int e;

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    private r(Context context) {
        this.e = -1;
        this.b = context;
        this.e = -1;
        this.c = d.a();
        this.c.a(e.a(context));
        a aVar = new a();
        aVar.a = 2130837804;
        aVar.b = 2130837804;
        aVar.c = 2130837804;
        aVar.m = true;
        aVar.h = true;
        aVar.a();
        this.d = aVar.b();
    }

    public r(Context context, byte b) {
        this(context);
    }

    public final int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    private s a(int i) {
        return (this.a == null || i >= this.a.size()) ? null : (s) this.a.get(i);
    }

    public final int getItemViewType(int i) {
        return (this.a == null || i >= this.a.size()) ? -1 : a(a(i));
    }

    public static int a(s sVar) {
        if (sVar == null) {
            return -1;
        }
        if (sVar.c == 1) {
            return 1;
        }
        return sVar.c == 2 ? 0 : -1;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType;
        c cVar;
        int i2 = this.e;
        if (this.e == -1) {
            itemViewType = getItemViewType(i);
        } else {
            itemViewType = i2;
        }
        if (view != null && itemViewType == ((Integer) view.getTag(2131231635)).intValue()) {
            cVar = (c) view.getTag(2131231634);
        } else if (itemViewType == 1) {
            view = LayoutInflater.from(this.b).inflate(2130968804, null);
            view.setTag(2131231635, Integer.valueOf(1));
            cVar = new a(this, view);
            view.setTag(2131231634, cVar);
        } else if (itemViewType == 0) {
            view = LayoutInflater.from(this.b).inflate(2130968805, null);
            view.setTag(2131231635, Integer.valueOf(0));
            cVar = new b(this, view);
            view.setTag(2131231634, cVar);
        } else {
            throw new IllegalStateException("\u672a\u77e5\u7684item \u7c7b\u578b");
        }
        cVar.a(a(i));
        return view;
    }
}
