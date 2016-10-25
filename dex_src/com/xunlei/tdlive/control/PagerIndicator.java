package com.xunlei.tdlive.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import org.android.spdy.SpdyProtocol;

public class PagerIndicator extends LinearLayout {
    private boolean a;
    private int b;
    private int c;
    private BaseAdapter d;
    private a e;

    private class a extends DataSetObserver {
        private a() {
        }

        public void onChanged() {
            int i = 0;
            PagerIndicator.this.removeAllViews();
            for (int i2 = 0; i2 < PagerIndicator.this.d.getCount(); i2++) {
                View childAt = PagerIndicator.this.getChildAt(i2);
                View view = PagerIndicator.this.d.getView(i2, childAt, PagerIndicator.this);
                if (view != null && view != childAt) {
                    if (childAt != null) {
                        PagerIndicator.this.removeViewAt(i2);
                    }
                    PagerIndicator.this.addView(view, i2);
                }
            }
            if (PagerIndicator.this.c >= 0 && PagerIndicator.this.c < PagerIndicator.this.getChildCount()) {
                if (PagerIndicator.this.b >= 0 && PagerIndicator.this.b < PagerIndicator.this.getChildCount()) {
                    PagerIndicator.this.getChildAt(PagerIndicator.this.b).setSelected(false);
                }
                PagerIndicator.this.getChildAt(PagerIndicator.this.b = PagerIndicator.this.c).setSelected(true);
            }
            if (PagerIndicator.this) {
                PagerIndicator pagerIndicator = PagerIndicator.this;
                if (PagerIndicator.this.d.getCount() <= 1) {
                    i = SpdyProtocol.PUBKEY_SEQ_ADASH;
                }
                pagerIndicator.setVisibility(i);
                return;
            }
            PagerIndicator.this.setVisibility(0);
        }

        public void onInvalidated() {
            PagerIndicator.this.removeAllViews();
            onChanged();
        }
    }

    @SuppressLint({"NewApi"})
    public PagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        this.e = new a();
    }

    public PagerIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
        this.e = new a();
    }

    public PagerIndicator(Context context) {
        super(context);
        this.a = false;
        this.e = new a();
    }

    public void setSingleVisible(boolean z) {
        int i = 0;
        this.a = !z;
        if (this.d == null || !this.a) {
            setVisibility(0);
            return;
        }
        if (this.d.getCount() <= 1) {
            i = SpdyProtocol.PUBKEY_SEQ_ADASH;
        }
        setVisibility(i);
    }

    public void select(int i) {
        if (this.d != null && i >= 0 && i < this.d.getCount()) {
            this.c = i;
            this.d.notifyDataSetChanged();
        }
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (this.d != null) {
            this.d.unregisterDataSetObserver(this.e);
        }
        this.b = -1;
        this.c = 0;
        this.d = baseAdapter;
        this.d.registerDataSetObserver(this.e);
        this.d.notifyDataSetChanged();
    }

    public BaseAdapter getAdapter() {
        return this.d;
    }
}
