package com.tencent.mm.sdk.diffdev.a;

import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.util.ArrayList;
import java.util.List;

final class c implements Runnable {
    final /* synthetic */ b ah;

    c(b bVar) {
        this.ah = bVar;
    }

    public final void run() {
        List<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.ah.ag.ad);
        for (OAuthListener oAuthListener : arrayList) {
            oAuthListener.onQrcodeScanned();
        }
    }
}
