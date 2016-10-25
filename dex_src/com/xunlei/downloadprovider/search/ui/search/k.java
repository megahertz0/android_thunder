package com.xunlei.downloadprovider.search.ui.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.search.bean.b;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.android.spdy.SpdyProtocol;

// compiled from: SearchHotwordsAdapter.java
public final class k extends com.xunlei.downloadprovider.search.ui.home.a<b> {
    int a;

    // compiled from: SearchHotwordsAdapter.java
    class a {
        TextView a;
        TextView b;

        a() {
        }
    }

    public k(Context context) {
        super(context);
        this.a = -1;
    }

    public final int getCount() {
        int count = super.getCount();
        return this.a < 0 ? count : Math.min(count, this.a);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.e).inflate(R.layout.associate_item_layout, viewGroup, false);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.keyword);
            aVar.b = (TextView) view.findViewById(R.id.keyword_suffix);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        b bVar = (b) getItem(i);
        aVar.a.setText(bVar.a);
        CharSequence charSequence = bVar.e;
        if (TextUtils.isEmpty(charSequence)) {
            aVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            aVar.b.setText(charSequence);
            aVar.b.setVisibility(0);
        }
        return view;
    }

    public final void a(List<b> list) {
        if (!(list == null || list.isEmpty())) {
            ArrayList arrayList = SniffConfigure.a().b().a;
            if (!(arrayList == null || arrayList.isEmpty())) {
                int size = arrayList.size();
                Random random = new Random();
                for (b bVar : list) {
                    bVar.e = (String) arrayList.get(random.nextInt(size));
                }
            }
        }
        super.a(list);
    }
}
