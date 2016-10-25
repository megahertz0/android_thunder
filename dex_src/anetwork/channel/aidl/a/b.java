package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anetwork.channel.aidl.g.a;

// compiled from: Taobao
public final class b extends a {
    private anetwork.channel.b a;

    public final int a(byte[] bArr) throws RemoteException {
        return this.a != null ? this.a.a() : 0;
    }

    public final boolean a() throws RemoteException {
        return this.a != null ? this.a.b() : true;
    }

    public final String toString() {
        return super.toString() + " handle:" + this.a;
    }
}
