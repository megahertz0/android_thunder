package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: PayOpenFragment.java
final class ab implements OnClickListener {
    final /* synthetic */ PayOpenFragment a;

    ab(PayOpenFragment payOpenFragment) {
        this.a = payOpenFragment;
    }

    public final void onClick(View view) {
        if (this.a.v.isChecked()) {
            this.a.v.setChecked(false);
            this.a.B.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.a.x = 0;
            return;
        }
        this.a.v.setChecked(true);
        this.a.i.setDefaultSelectType(1);
        this.a.j = 1;
        this.a.B.setVisibility(0);
        this.a.x = 1;
    }
}
