package com.xunlei.tdlive.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.d;

// compiled from: PagerIndicatorAdapter.java
public class r extends BaseAdapter {
    private int a;

    public r() {
        this.a = 0;
    }

    public void a(int i) {
        this.a = i;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.a;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return view;
        }
        view = new ImageView(viewGroup.getContext());
        view.setPadding((int) d.a(viewGroup.getContext(), 6.0f), 0, 0, 0);
        view.setImageResource(R.drawable.xllive_pager_indicator_selector);
        return view;
    }
}
