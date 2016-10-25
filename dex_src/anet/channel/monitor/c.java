package anet.channel.monitor;

import anet.channel.util.ALog;
import java.util.Iterator;

// compiled from: Taobao
class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void run() {
        Iterator it = this.a.n.iterator();
        while (it.hasNext()) {
            INetworkQualityChangeListener iNetworkQualityChangeListener = (INetworkQualityChangeListener) it.next();
            long currentTimeMillis = System.currentTimeMillis();
            iNetworkQualityChangeListener.onNetworkQualityChanged(NetworkSpeed.valueOfCode(this.a.l));
            if (System.currentTimeMillis() - currentTimeMillis > 500) {
                ALog.e("awcn.BandWidthSampler", "call back cost too much time", null, new Throwable().fillInStackTrace(), new Object[0]);
            }
        }
    }
}
