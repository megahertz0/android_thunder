package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType;
import org.android.spdy.SpdyProtocol;

// compiled from: HomeChoicenessFragment.java
final class u implements OnClickListener {
    final /* synthetic */ HomeChoicenessFragment a;

    u(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void onClick(View view) {
        this.a.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.n = RefreshType.manul_pull;
        this.a.e();
    }
}
