package anet.channel.status;

import anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import java.util.Iterator;

// compiled from: Taobao
final class a implements Runnable {
    final /* synthetic */ NetworkStatus a;

    a(NetworkStatus networkStatus) {
        this.a = networkStatus;
    }

    public final void run() {
        try {
            Iterator it = NetworkStatusHelper.a.iterator();
            while (it.hasNext()) {
                INetworkStatusChangeListener iNetworkStatusChangeListener = (INetworkStatusChangeListener) it.next();
                long currentTimeMillis = System.currentTimeMillis();
                iNetworkStatusChangeListener.onNetworkStatusChanged(this.a);
                if (System.currentTimeMillis() - currentTimeMillis > 500) {
                    ALog.e("awcn.NetworkStatusHelper", "call back cost too much time", null, new Throwable().fillInStackTrace(), new Object[0]);
                }
            }
        } catch (Exception e) {
        }
    }
}
