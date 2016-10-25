package anet.channel.session;

import anet.channel.util.Utils;
import org.android.spdy.AccsSSLCallback;

// compiled from: Taobao
class l implements AccsSSLCallback {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        return Utils.staticBinarySafeDecryptNoB64(k.access$000(this.a), "tnet_pksg_key", bArr);
    }
}
