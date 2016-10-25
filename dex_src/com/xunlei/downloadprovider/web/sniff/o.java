package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SnifferResultsResourceAdapter.java
final class o implements OnClickListener {
    final /* synthetic */ m a;

    o(m mVar) {
        this.a = mVar;
    }

    public final void onClick(View view) {
        SniffingResource sniffingResource = (SniffingResource) view.getTag();
        if (m.a(this.a) != null) {
            m.a(this.a).a(SimpleLog.LOG_LEVEL_DEBUG, sniffingResource);
        }
    }
}
