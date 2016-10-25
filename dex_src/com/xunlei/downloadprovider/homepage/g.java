package com.xunlei.downloadprovider.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

// compiled from: HomeFragment.java
final class g implements Runnable {
    final /* synthetic */ HomeFragment a;

    g(HomeFragment homeFragment) {
        this.a = homeFragment;
    }

    public final void run() {
        if (this.a.c) {
            TabHost b = this.a.a;
            int currentTab = b.getCurrentTab();
            int length = this.a.g.b.length;
            for (int i = 0; i < length; i++) {
                View view = (ImageView) b.getCurrentTabView().findViewById(R.id.main_tab_top_point);
                this.a.a(i, view);
                if (currentTab == i) {
                    this.a.d(i);
                    view.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
            }
        }
    }
}
