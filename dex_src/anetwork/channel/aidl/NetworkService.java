package anetwork.channel.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import anet.channel.util.ALog;
import anetwork.channel.aidl.d.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Taobao
public class NetworkService extends Service {
    a a;
    private Context b;
    private n.a[] c;

    public NetworkService() {
        this.c = new n.a[2];
        this.a = new f(this);
    }

    public IBinder onBind(Intent intent) {
        this.b = getApplicationContext();
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i("ANet.NetworkService", new StringBuilder("onBind:").append(intent.getAction()).toString(), null, new Object[0]);
        }
        return d.class.getName().equals(intent.getAction()) ? this.a : null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public void onDestroy() {
    }
}
