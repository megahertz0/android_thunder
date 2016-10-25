package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface h extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements h {
        public a() {
            attachInterface(this, "anetwork.channel.aidl.ParcelableFuture");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            boolean z;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableFuture");
                    if (parcel.readInt() != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    z = a(z);
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableFuture");
                    z = a();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableFuture");
                    z = b();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableFuture");
                    NetworkResponse a = a(parcel.readLong());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.ParcelableFuture");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    NetworkResponse a(long j) throws RemoteException;

    boolean a() throws RemoteException;

    boolean a(boolean z) throws RemoteException;

    boolean b() throws RemoteException;
}
