package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface a extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements a {
        public a() {
            attachInterface(this, "anetwork.channel.aidl.Connection");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    j a = a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a != null ? a.asBinder() : null);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    int b = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    String c = c();
                    parcel2.writeNoException();
                    parcel2.writeString(c);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    Map d = d();
                    parcel2.writeNoException();
                    parcel2.writeMap(d);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    e();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.Connection");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    j a() throws RemoteException;

    int b() throws RemoteException;

    String c() throws RemoteException;

    Map d() throws RemoteException;

    void e() throws RemoteException;
}
