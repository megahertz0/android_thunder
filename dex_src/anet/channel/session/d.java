package anet.channel.session;

import anet.channel.request.Request;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

// compiled from: Taobao
final class d implements HostnameVerifier {
    final /* synthetic */ Request a;

    d(Request request) {
        this.a = request;
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.a.getHost(), sSLSession);
    }
}
