package anetwork.channel.entity;

import android.os.RemoteException;
import anetwork.channel.aidl.ParcelableHeader;
import anetwork.channel.aidl.k;
import java.util.Map;

// compiled from: Taobao
final class g implements Runnable {
    final /* synthetic */ k a;
    final /* synthetic */ int b;
    final /* synthetic */ Map c;
    final /* synthetic */ f d;

    g(f fVar, k kVar, int i, Map map) {
        this.d = fVar;
        this.a = kVar;
        this.b = i;
        this.c = map;
    }

    public final void run() {
        try {
            this.a.a(this.b, new ParcelableHeader(this.b, this.c));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
