package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface n extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements n {
        public a() {
            attachInterface(this, "anetwork.channel.aidl.RemoteNetwork");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            ParcelableRequest parcelableRequest;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("anetwork.channel.aidl.RemoteNetwork");
                    if (parcel.readInt() != 0) {
                        parcelableRequest = (ParcelableRequest) ParcelableRequest.CREATOR.createFromParcel(parcel);
                    } else {
                        parcelableRequest = null;
                    }
                    NetworkResponse a = a(parcelableRequest);
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    ParcelableRequest parcelableRequest2;
                    k kVar;
                    parcel.enforceInterface("anetwork.channel.aidl.RemoteNetwork");
                    if (parcel.readInt() != 0) {
                        parcelableRequest2 = (ParcelableRequest) ParcelableRequest.CREATOR.createFromParcel(parcel);
                    } else {
                        parcelableRequest2 = null;
                    }
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        kVar = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("anetwork.channel.aidl.ParcelableNetworkListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof k)) {
                            kVar = new a(readStrongBinder);
                        } else {
                            kVar = (k) queryLocalInterface;
                        }
                    }
                    h a2 = a(parcelableRequest2, kVar);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        iBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.RemoteNetwork");
                    if (parcel.readInt() != 0) {
                        parcelableRequest = (ParcelableRequest) ParcelableRequest.CREATOR.createFromParcel(parcel);
                    } else {
                        parcelableRequest = null;
                    }
                    a b = b(parcelableRequest);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(b.asBinder());
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.RemoteNetwork");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    NetworkResponse a(ParcelableRequest parcelableRequest) throws RemoteException;

    h a(ParcelableRequest parcelableRequest, k kVar) throws RemoteException;

    a b(ParcelableRequest parcelableRequest) throws RemoteException;
}
