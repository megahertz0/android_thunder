package com.xunlei.downloadprovider.member.register.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

// compiled from: MailBoxAdapter.java
public final class c extends BaseAdapter implements Filterable {
    private final String a;
    private String[] b;
    private LayoutInflater c;

    public c(Context context, String[] strArr, String str) {
        this.a = getClass().getSimpleName();
        this.c = LayoutInflater.from(context);
        a(strArr, str);
    }

    public final int getCount() {
        return this.b.length;
    }

    public final Object getItem(int i) {
        return this.b[i];
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.c.inflate(2130968863, null);
            a aVar2 = new a((TextView) view.findViewById(2131756476));
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(this.b[i]);
        return view;
    }

    private void a(String[] strArr, String str) {
        this.b = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.b[i] = str + strArr[i];
        }
    }

    public final Filter getFilter() {
        return new d(this);
    }
}
