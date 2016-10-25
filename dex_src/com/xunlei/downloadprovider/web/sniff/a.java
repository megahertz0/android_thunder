package com.xunlei.downloadprovider.web.sniff;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.List;

// compiled from: SniffAnimListAdapter.java
public abstract class a extends com.xunlei.downloadprovider.e.a.a.a {
    protected Context a;
    int b;
    boolean c;
    int d;
    b e;
    private int f;
    private a g;
    private int h;
    private int i;
    private List<Animation> j;

    protected abstract View a(int i, View view);

    static /* synthetic */ int a(a aVar) {
        int i = aVar.d;
        aVar.d = i + 1;
        return i;
    }

    public a(Context context) {
        this.c = true;
        this.f = 2131362269;
        this.a = context;
        this.g = new a(this, (byte) 0);
        this.j = new ArrayList();
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        try {
            super.unregisterDataSetObserver(dataSetObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a() {
        this.h = 0;
        this.i = 0;
        this.b = 0;
        this.c = true;
        this.d = 0;
        if (this.j != null) {
            for (Animation animation : this.j) {
                animation.cancel();
            }
        }
        this.b = 0;
        this.c = true;
        this.d = 0;
        this.j.clear();
        this.e = null;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return a(i, view);
        }
        View a = a(i, view);
        if (this.i == 0) {
            int i2;
            this.h = this.a.getResources().getDimensionPixelOffset(this.f);
            this.i = viewGroup.getHeight();
            if (this.i % this.h > 0) {
                i2 = (this.i / this.h) + 1;
            } else {
                i2 = this.i / this.h;
            }
            this.b = i2;
        }
        Object obj = 1;
        if (getViewTypeCount() >= 2 && getItemViewType(i) > 0) {
            obj = null;
        }
        if (i < this.b && this.c && r0 != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.a, 2131034197);
            loadAnimation.setAnimationListener(this.g);
            if (i - 1 >= 0) {
                loadAnimation.setStartOffset((long) (a.a(this.g) * 200));
                loadAnimation.setDuration(loadAnimation.getDuration() * ((long) (1 - (this.d / this.b))));
            }
            a.b(this.g);
            this.j.add(loadAnimation);
            a.startAnimation(loadAnimation);
        }
        return a;
    }
}
