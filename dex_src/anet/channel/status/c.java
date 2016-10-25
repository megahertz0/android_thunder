package anet.channel.status;

import android.content.Context;

// compiled from: Taobao
class c implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ NetworkStatusMonitor$1 b;

    c(NetworkStatusMonitor$1 networkStatusMonitor$1, Context context) {
        this.b = networkStatusMonitor$1;
        this.a = context;
    }

    public void run() {
        b.a(this.a);
    }
}
