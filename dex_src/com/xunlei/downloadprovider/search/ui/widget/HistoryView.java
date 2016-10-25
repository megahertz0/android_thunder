package com.xunlei.downloadprovider.search.ui.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.xunlei.downloadprovider.a.g;

public class HistoryView extends LinearLayout {
    private int a;
    private int b;
    private int c;
    private int d;
    private BaseAdapter e;
    private a f;
    private OnItemClickListener g;

    private class a extends DataSetObserver {
        private a() {
        }

        public final void onChanged() {
            super.onChanged();
            if (HistoryView.this.e != null) {
                HistoryView.this.a(HistoryView.this.e);
            }
        }

        public final void onInvalidated() {
            super.onInvalidated();
        }
    }

    public HistoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 2;
        this.d = 2;
        setOrientation(1);
        this.b = g.a(context, 13.0f);
        this.a = g.a(context, 13.0f);
    }

    public HistoryView(Context context) {
        super(context);
        this.c = 2;
        this.d = 2;
        setOrientation(1);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (!(this.e == null || this.f == null)) {
            this.e.unregisterDataSetObserver(this.f);
        }
        if (baseAdapter != null) {
            this.f = new a();
            baseAdapter.registerDataSetObserver(this.f);
            this.e = baseAdapter;
        }
        a(baseAdapter);
    }

    private void a(BaseAdapter baseAdapter) {
        removeAllViews();
        if (baseAdapter != null) {
            int count = baseAdapter.getCount();
            int i = this.c;
            int min = Math.min(((count + i) - 1) / i, this.d);
            for (int i2 = 0; i2 < min; i2++) {
                View linearLayout = new LinearLayout(getContext());
                for (int i3 = 0; i3 < i; i3++) {
                    View view;
                    LayoutParams layoutParams;
                    int i4 = (i2 * i) + i3;
                    if (i4 >= count) {
                        view = new View(getContext());
                        view.setBackgroundColor(getResources().getColor(17170445));
                    } else {
                        view = baseAdapter.getView(i4, null, linearLayout);
                        view.setOnClickListener(new a(this, view, i4));
                    }
                    LayoutParams layoutParams2 = view.getLayoutParams();
                    if (layoutParams2 == null) {
                        layoutParams = new LinearLayout.LayoutParams(0, -2);
                    } else {
                        layoutParams = new LinearLayout.LayoutParams(layoutParams2);
                    }
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                    if (i3 % i != 0) {
                        view.setPadding(g.a(getContext(), 13.0f) + view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                    }
                    linearLayout.addView(view, layoutParams);
                }
                LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                if (i2 != 0) {
                    layoutParams3.topMargin = this.b;
                }
                addView(linearLayout, layoutParams3);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.g = onItemClickListener;
    }
}
