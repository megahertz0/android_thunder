package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.common.pay.XLContractResp;
import com.xunlei.downloadprovider.member.payment.external.f;
import org.android.spdy.SpdyProtocol;

// compiled from: PayOpenFragment.java
final class ae extends f {
    final /* synthetic */ PayOpenFragment a;

    ae(PayOpenFragment payOpenFragment) {
        this.a = payOpenFragment;
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
        if (this.a.y != i2) {
            return;
        }
        if (i != 0) {
            this.a.u.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.a.z = false;
            this.a.A = false;
        } else if (xLContractResp.mContractStatus == 12290) {
            this.a.u.setVisibility(0);
            this.a.z = true;
            this.a.A = true;
        } else {
            this.a.u.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.a.z = false;
            this.a.A = false;
        }
    }
}
