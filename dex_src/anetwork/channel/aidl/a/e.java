package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anetwork.channel.aidl.j.a;
import java.io.IOException;
import java.io.InputStream;

// compiled from: Taobao
public final class e extends a {
    private InputStream a;

    public final int a() throws RemoteException {
        try {
            return this.a.available();
        } catch (IOException e) {
            throw new RemoteException("IO Exception");
        }
    }

    public final void b() throws RemoteException {
        try {
            this.a.close();
        } catch (IOException e) {
            throw new RemoteException("IO Exception");
        }
    }

    public final int c() throws RemoteException {
        try {
            return this.a.read();
        } catch (IOException e) {
            throw new RemoteException("IO Exception");
        }
    }

    public final int a(byte[] bArr) throws RemoteException {
        try {
            return this.a.read(bArr);
        } catch (IOException e) {
            throw new RemoteException("IO Exception");
        }
    }

    public final long a(int i) throws RemoteException {
        try {
            return this.a.skip((long) i);
        } catch (IOException e) {
            throw new RemoteException("IO Exception");
        }
    }

    public final int d() throws RemoteException {
        return 0;
    }
}
