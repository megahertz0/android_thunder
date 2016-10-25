package com.xunlei.downloadprovider.member.register.view;

import android.widget.Filter;
import android.widget.Filter.FilterResults;

// compiled from: MailBoxAdapter.java
final class d extends Filter {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    protected final void publishResults(CharSequence charSequence, FilterResults filterResults) {
        c.a(this.a, (String[]) filterResults.values);
        if (filterResults.count > 0) {
            this.a.notifyDataSetChanged();
        } else {
            this.a.notifyDataSetInvalidated();
        }
    }

    protected final FilterResults performFiltering(CharSequence charSequence) {
        new StringBuilder("constraint:").append(charSequence);
        FilterResults filterResults = new FilterResults();
        filterResults.values = c.a(this.a);
        filterResults.count = c.a(this.a).length;
        return filterResults;
    }
}
