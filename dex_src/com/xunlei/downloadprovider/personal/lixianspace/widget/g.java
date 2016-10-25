package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.c.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import org.android.spdy.SpdyProtocol;

// compiled from: LixianSpaceListWidget.java
final class g implements OnClickListener {
    final /* synthetic */ LixianSpaceListWidget a;

    g(LixianSpaceListWidget lixianSpaceListWidget) {
        this.a = lixianSpaceListWidget;
    }

    public final void onClick(View view) {
        if (b.a(BrothersApplication.a())) {
            LixianSpaceListWidget.y(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            LixianSpaceListWidget.z(this.a).onClick(view);
            return;
        }
        LixianSpaceListWidget.y(this.a).setVisibility(0);
    }
}
