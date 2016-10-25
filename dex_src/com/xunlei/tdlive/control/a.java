package com.xunlei.tdlive.control;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

// compiled from: AutoCompleteArrayAdapter.java
public class a extends BaseAdapter implements Filterable {
    private List<String> a;
    private final Object b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private Context g;
    private ArrayList<String> h;
    private a i;
    private LayoutInflater j;
    private String k;
    private String[] l;

    // compiled from: AutoCompleteArrayAdapter.java
    private class a extends Filter {
        private a() {
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            int i = 0;
            FilterResults filterResults = new FilterResults();
            if (a.this.h == null) {
                synchronized (a.this.b) {
                    a.this.h = new ArrayList(a.this);
                }
            }
            if (charSequence == null || charSequence.length() == 0) {
                ArrayList arrayList;
                synchronized (a.this.b) {
                    arrayList = new ArrayList(a.this.h);
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
            } else {
                ArrayList arrayList2 = new ArrayList();
                String toLowerCase = charSequence.toString().toLowerCase();
                boolean a = a.this.a();
                int length;
                if (a && toLowerCase.endsWith(a.this.k) && a.this.getCount() == 0) {
                    String[] e = a.this.l;
                    length = e.length;
                    while (i < length) {
                        arrayList2.add(toLowerCase + e[i]);
                        synchronized (a.this.b) {
                            a.this.h.clear();
                            a.this.h.addAll(arrayList2);
                        }
                        i++;
                    }
                } else if (!a || toLowerCase.contains(a.this.k)) {
                    ArrayList arrayList3;
                    synchronized (a.this.b) {
                        arrayList3 = new ArrayList(a.this.h);
                    }
                    int size = arrayList3.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        String str = (String) arrayList3.get(i2);
                        String toLowerCase2 = str.toString().toLowerCase();
                        if (toLowerCase2.startsWith(toLowerCase)) {
                            arrayList2.add(str);
                        } else {
                            String[] split = toLowerCase2.split(" ");
                            int length2 = split.length;
                            for (length = 0; length < length2; length++) {
                                if (split[length].startsWith(toLowerCase)) {
                                    arrayList2.add(str);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    arrayList2.clear();
                }
                filterResults.values = arrayList2;
                filterResults.count = arrayList2.size();
            }
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            a.this = (List) filterResults.values;
            if (filterResults.count > 0) {
                a.this.notifyDataSetChanged();
            } else {
                a.this.notifyDataSetInvalidated();
            }
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, int i, int i2) {
        this.b = new Object();
        this.e = 0;
        this.f = true;
        this.k = "@";
        a(context, i, i2, new ArrayList());
    }

    public void a(String str, String[] strArr) {
        this.k = str;
        this.l = strArr;
    }

    public boolean a() {
        return (TextUtils.isEmpty(this.k) || this.l == null || this.l.length <= 0) ? false : true;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.f = true;
    }

    private void a(Context context, int i, int i2, List<String> list) {
        this.g = context;
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
        this.d = i;
        this.c = i;
        this.a = list;
        this.e = i2;
    }

    public int getCount() {
        return this.a.size();
    }

    public String a(int i) {
        return (String) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(i, view, viewGroup, this.c);
    }

    private View a(int i, View view, ViewGroup viewGroup, int i2) {
        View inflate;
        if (view == null) {
            inflate = this.j.inflate(i2, viewGroup, false);
        } else {
            inflate = view;
        }
        try {
            TextView textView;
            if (this.e == 0) {
                textView = (TextView) inflate;
            } else {
                textView = (TextView) inflate.findViewById(this.e);
            }
            Object a = a(i);
            if (a instanceof CharSequence) {
                textView.setText(a);
            } else {
                textView.setText(a.toString());
            }
            return inflate;
        } catch (Throwable e) {
            throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e);
        }
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return a(i, view, viewGroup, this.d);
    }

    public Filter getFilter() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }
}
