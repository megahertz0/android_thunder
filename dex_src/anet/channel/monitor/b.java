package anet.channel.monitor;

import anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;

// compiled from: Taobao
class b implements INetworkStatusChangeListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onNetworkStatusChanged(NetworkStatus networkStatus) {
        this.a.o.a();
        a.f = 0;
        this.a.c();
    }
}
