package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface d extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements d {
        public a() {
            attachInterface(this, "anetwork.channel.aidl.IRemoteNetworkGetter");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("anetwork.channel.aidl.IRemoteNetworkGetter");
                    n a = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a != null ? a.asBinder() : null);
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.IRemoteNetworkGetter");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    n a(int i) throws RemoteException;
}
