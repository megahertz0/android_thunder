package com.xunlei.downloadprovider.web.sniff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.web.x;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import org.android.spdy.SpdyProtocol;

// compiled from: SuffixListViewAdapter.java
public final class s extends BaseAdapter {
    public ListView a;
    public TextView b;
    public ArrayList<x> c;
    public ArrayList<x> d;
    private LayoutInflater e;
    private LinearLayout f;
    private boolean g;

    // compiled from: SuffixListViewAdapter.java
    public final class a {
        public TextView a;
        public TextView b;
        public ImageView c;
    }

    static /* synthetic */ void a(s sVar) {
        sVar.c.clear();
        if (sVar.g) {
            sVar.g = false;
            sVar.c.addAll(sVar.d);
            sVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            sVar.g = true;
            for (int i = 0; i < 3; i++) {
                sVar.c.add(sVar.d.get(i));
            }
            sVar.b.setVisibility(0);
            sVar.b.setText("\u5c55\u5f00\u66f4\u591a");
        }
        sVar.notifyDataSetChanged();
        a(sVar.a);
    }

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public s(Context context, ListView listView) {
        this.c = new ArrayList();
        this.d = null;
        this.g = true;
        this.a = listView;
        this.e = LayoutInflater.from(context);
        this.f = (LinearLayout) this.e.inflate(R.layout.suffix_list_foot_view, null);
        this.b = (TextView) this.f.findViewById(R.id.tv_load_more);
        this.b.setOnClickListener(new t(this));
        listView.addFooterView(this.f, null, false);
    }

    public final int getCount() {
        return d.a(this.c) ? 0 : this.c.size();
    }

    private x a(int i) {
        return d.a(this.c) ? null : (x) this.c.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = this.e.inflate(R.layout.suffix_list_item_view, null, false);
            aVar2.a = (TextView) view.findViewById(R.id.tv_all_search_item_name);
            aVar2.b = (TextView) view.findViewById(R.id.tv_all_search_item_suffix_name);
            aVar2.c = (ImageView) view.findViewById(R.id.iv_all_search_item_go);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        x a = a(i);
        aVar.a.setText(a.a);
        aVar.b.setText(a.b);
        return view;
    }

    public static void a(ListView listView) {
        if (listView != null) {
            ListAdapter adapter = listView.getAdapter();
            if (adapter != null) {
                int i = 0;
                for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                    View view = adapter.getView(i2, null, listView);
                    view.measure(0, 0);
                    i += view.getMeasuredHeight();
                }
                LayoutParams layoutParams = listView.getLayoutParams();
                layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
                listView.setLayoutParams(layoutParams);
            }
        }
    }
}
