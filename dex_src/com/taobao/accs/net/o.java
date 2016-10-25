package com.taobao.accs.net;

import anet.channel.util.Utils;
import org.android.spdy.AccsSSLCallback;

// compiled from: Taobao
class o implements AccsSSLCallback {
    final /* synthetic */ l a;

    o(l lVar) {
        this.a = lVar;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        return Utils.staticBinarySafeDecryptNoB64(this.a.b, "tnet_pksg_key", bArr);
    }
}
