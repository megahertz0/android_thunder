package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.e.a.a.a;
import java.util.ArrayList;
import java.util.List;

// compiled from: SearchWebsiteAdapter.java
public final class q extends a {
    public List<w> a;
    public ArrayList<Integer> b;
    private Context c;
    private int d;

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public q(Context context) {
        this.c = context;
        this.d = 10;
        this.b = new ArrayList();
    }

    public final int getViewTypeCount() {
        return this.d;
    }

    public final int getItemViewType(int i) {
        if (this.a == null || this.a.get(i) == null) {
            return 0;
        }
        return this.b.indexOf(Integer.valueOf(((w) this.a.get(i)).a()));
    }

    public final int getCount() {
        return this.a != null ? this.a.size() : 0;
    }

    private w a(int i) {
        return (this.a == null || this.a.size() == 0) ? null : (w) this.a.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final boolean isEmpty() {
        return this.a == null || this.a.size() == 0;
    }

    public final boolean isEnabled(int i) {
        if (this.a != null) {
            w wVar = (w) this.a.get(i);
            if (wVar.a() == 0 || wVar.a() == 5 || wVar.a() == 4 || wVar.a() == 2) {
                return false;
            }
        }
        return true;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        w a = a(i);
        if (a != null) {
            Object a2;
            if (view == null) {
                view = LayoutInflater.from(this.c).inflate(a.b(), viewGroup, false);
                a2 = a.a(view);
                view.setTag(a2);
            } else {
                a2 = view.getTag();
            }
            a.a(a2);
            if (a instanceof o) {
                if (i != 0) {
                    ((o) a).a(true);
                } else {
                    ((o) a).a(false);
                }
            }
        }
        return view;
    }
}
