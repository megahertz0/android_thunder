package anetwork.channel.aidl;

import android.os.RemoteException;
import anetwork.channel.aidl.d.a;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
final class f extends a {
    final /* synthetic */ NetworkService a;

    f(NetworkService networkService) {
        this.a = networkService;
    }

    public final n a(int i) throws RemoteException {
        if (this.a.c[i] == null) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.a.c[i] = new anetwork.channel.d.a(this.a.b);
                    break;
                default:
                    this.a.c[i] = new anetwork.channel.http.a(this.a.b);
                    break;
            }
        }
        return this.a.c[i];
    }
}
