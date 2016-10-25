package com.xunlei.downloadprovider.search.ui.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.search.bean.d;
import com.xunlei.downloadprovider.search.ui.home.a;
import org.android.spdy.SpdyProtocol;

// compiled from: SearchRecordFixAdapter.java
public final class u extends a<d> {
    public u(Context context) {
        super(context);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.search_record_item_layout, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(com.xunlei.xllib.R.id.title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.keyword_suffix);
        d dVar = (d) getItem(i);
        textView.setText(dVar.a);
        if (TextUtils.isEmpty(dVar.a())) {
            textView.setMaxWidth(Integer.MAX_VALUE);
            textView2.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            textView.setMaxWidth(g.a(this.e, 109.0f));
            textView2.setVisibility(0);
            textView2.setText(dVar.a());
        }
        return inflate;
    }
}
