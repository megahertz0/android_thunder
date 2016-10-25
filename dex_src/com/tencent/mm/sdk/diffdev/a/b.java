package com.tencent.mm.sdk.diffdev.a;

import com.tencent.mm.sdk.diffdev.OAuthErrCode;
import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.util.ArrayList;
import java.util.List;

final class b implements OAuthListener {
    final /* synthetic */ a ag;

    b(a aVar) {
        this.ag = aVar;
    }

    public final void onAuthFinish(OAuthErrCode oAuthErrCode, String str) {
        String.format("onAuthFinish, errCode = %s, authCode = %s", new Object[]{oAuthErrCode.toString(), str});
        this.ag.ae = null;
        List<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.ag.ad);
        for (OAuthListener oAuthListener : arrayList) {
            oAuthListener.onAuthFinish(oAuthErrCode, str);
        }
    }

    public final void onAuthGotQrcode(String str, byte[] bArr) {
        List<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.ag.ad);
        for (OAuthListener oAuthListener : arrayList) {
            oAuthListener.onAuthGotQrcode(str, bArr);
        }
    }

    public final void onQrcodeScanned() {
        if (this.ag.ac != null) {
            this.ag.ac.post(new c(this));
        }
    }
}
