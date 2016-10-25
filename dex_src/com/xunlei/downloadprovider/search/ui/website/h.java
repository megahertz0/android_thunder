package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.o;
import org.android.spdy.SpdyProtocol;

// compiled from: SearchHistoryEndInfo.java
public final class h extends i {
    public h(Context context, o oVar) {
        super(context, oVar);
    }

    public final int b() {
        return R.layout.search_website_end_item_layout;
    }

    public final int a() {
        return SpdyProtocol.PUBKEY_PSEQ_ADASH;
    }
}
