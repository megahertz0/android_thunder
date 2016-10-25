package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.f.b;
import android.support.v7.widget.cm;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements b, n, OnItemClickListener {
    private static final int[] a;
    private f b;
    private int c;

    static {
        a = new int[]{16842964, 16843049};
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        cm a = cm.a(context, attributeSet, a, i);
        if (a.e(0)) {
            setBackgroundDrawable(a.a(0));
        }
        if (a.e(1)) {
            setDivider(a.a(1));
        }
        a.a.recycle();
    }

    public final void a(f fVar) {
        this.b = fVar;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public final boolean a(h hVar) {
        return this.b.a((MenuItem) hVar, null, 0);
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((h) getAdapter().getItem(i));
    }

    public final int getWindowAnimations() {
        return this.c;
    }
}
