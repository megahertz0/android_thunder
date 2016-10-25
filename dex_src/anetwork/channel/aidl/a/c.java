package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anet.channel.util.ALog;
import anetwork.channel.aidl.NetworkResponse;
import anetwork.channel.aidl.h.a;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
public final class c extends a {
    Future<Object> a;
    NetworkResponse b;

    public c(Future<Object> future) {
        this.a = future;
    }

    public final boolean a(boolean z) throws RemoteException {
        return this.a == null ? true : this.a.cancel(z);
    }

    public final boolean a() throws RemoteException {
        return this.a == null ? true : this.a.isCancelled();
    }

    public final boolean b() throws RemoteException {
        return this.a == null ? true : this.a.isDone();
    }

    public final NetworkResponse a(long j) throws RemoteException {
        if (this.a == null) {
            return this.b != null ? this.b : new NetworkResponse((byte) 0);
        } else {
            try {
                return (NetworkResponse) this.a.get(j, TimeUnit.MILLISECONDS);
            } catch (Throwable e) {
                if ("NO SUPPORT".equalsIgnoreCase(e.getMessage())) {
                    ALog.e("ANet.ParcelableFutureResponse", "[get]\u6709listener\u5c06\u4e0d\u652f\u6301future.get()\u65b9\u6cd5\uff0c\u5982\u6709\u9700\u8981\u8bf7listener\u4f20\u5165null", null, e, new Object[0]);
                }
                return new NetworkResponse((byte) 0);
            }
        }
    }
}
