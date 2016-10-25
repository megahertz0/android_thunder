package anet.channel.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import anet.channel.c.c;
import anet.channel.util.ALog;

// compiled from: Taobao
final class NetworkStatusMonitor$1 extends BroadcastReceiver {
    NetworkStatusMonitor$1() {
    }

    public final void onReceive(Context context, Intent intent) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.NetworkStatusMonitor", new StringBuilder("receiver:").append(intent.getAction()).toString(), null, new Object[0]);
        }
        c.a(new c(this, context));
    }
}
