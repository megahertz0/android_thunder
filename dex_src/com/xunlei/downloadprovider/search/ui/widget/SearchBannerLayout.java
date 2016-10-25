package com.xunlei.downloadprovider.search.ui.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.search.ui.home.SearchBannerAdapter;
import com.xunlei.xiazaibao.BuildConfig;

public class SearchBannerLayout extends FrameLayout {
    private SearchBannerAdapter a;
    private DataSetObserver b;
    private int c;
    private TextView d;
    private LinearLayout e;
    private OnItemClickListener f;

    private class a extends DataSetObserver {
        private a() {
        }

        public final void onChanged() {
            super.onChanged();
            if (SearchBannerLayout.this != null) {
                SearchBannerLayout.this.a(SearchBannerLayout.this);
            }
        }

        public final void onInvalidated() {
            super.onInvalidated();
        }
    }

    public SearchBannerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 2;
        a(context);
    }

    public SearchBannerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 2;
        a(context);
    }

    public SearchBannerLayout(Context context) {
        super(context);
        this.c = 2;
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_banner_layout, this, true);
        this.d = (TextView) inflate.findViewById(com.xunlei.xllib.R.id.title);
        this.e = (LinearLayout) inflate.findViewById(R.id.banner_list);
        inflate.findViewById(R.id.switch_next).setOnClickListener(new d(this, (ImageView) inflate.findViewById(R.id.switch_icon)));
    }

    public void setAdapter(SearchBannerAdapter searchBannerAdapter) {
        if (!(this.a == null || this.b == null)) {
            this.a.unregisterDataSetObserver(this.b);
        }
        if (searchBannerAdapter != null) {
            this.b = new a();
            searchBannerAdapter.registerDataSetObserver(this.b);
            this.a = searchBannerAdapter;
        }
        a(searchBannerAdapter);
    }

    public final void setTitle$505cff1c(String str) {
        this.d.setText(str);
        this.d.setCompoundDrawablePadding(0);
    }

    private void a(SearchBannerAdapter searchBannerAdapter) {
        this.e.removeAllViews();
        if (searchBannerAdapter != null) {
            int count = searchBannerAdapter.getCount();
            int i = (count + 1) / 2;
            for (int i2 = 0; i2 < i; i2++) {
                View linearLayout = new LinearLayout(getContext());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                for (int i3 = 0; i3 < this.c; i3++) {
                    int i4 = (this.c * i2) + i3;
                    if (i4 >= count) {
                        new View(getContext()).setBackgroundColor(getResources().getColor(17170445));
                        break;
                    }
                    LayoutParams layoutParams2;
                    View view = searchBannerAdapter.getView(i4, null, linearLayout);
                    view.setOnClickListener(new e(this, view, i4));
                    LayoutParams layoutParams3 = view.getLayoutParams();
                    if (layoutParams3 == null) {
                        layoutParams2 = new LinearLayout.LayoutParams(0, -2);
                    } else {
                        layoutParams2 = new LinearLayout.LayoutParams(layoutParams3);
                    }
                    layoutParams2.width = 0;
                    layoutParams2.weight = 1.0f;
                    if (i3 != 0) {
                        view.setPadding(g.a(getContext(), 13.0f) + view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                        LayoutInflater.from(getContext()).inflate(R.layout.vertical_line, linearLayout, true);
                    }
                    linearLayout.addView(view, layoutParams2);
                }
                this.e.addView(linearLayout, layoutParams);
                LayoutInflater.from(getContext()).inflate(R.layout.line, this.e, true);
            }
        }
    }

    public SearchBannerAdapter getAdapter() {
        return this.a;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f = onItemClickListener;
    }

    public String getIdString() {
        String str = BuildConfig.VERSION_NAME;
        switch (getId()) {
            case R.id.banner_hot:
                return "\u4eca\u65e5\u70ed\u641c";
            case R.id.banner_movie:
                return "\u7535\u5f71";
            case R.id.banner_teleplay:
                return "\u7535\u89c6\u5267";
            case R.id.banner_variety:
                return "\u7efc\u827a";
            case R.id.banner_anime:
                return "\u52a8\u753b";
            default:
                return str;
        }
    }
}
