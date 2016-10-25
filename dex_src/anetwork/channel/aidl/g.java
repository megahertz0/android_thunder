package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface g extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements g {

        // compiled from: Taobao
        private static class a implements g {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final int a(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableBodyHandler");
                obtain.writeByteArray(bArr);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.readByteArray(bArr);
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            }

            public final boolean a() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableBodyHandler");
                this.a.transact(XZBDevice.DOWNLOAD_LIST_RECYCLE, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            }
        }

        public static g a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.ParcelableBodyHandler");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof g)) ? new a(iBinder) : (g) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableBodyHandler");
                    byte[] createByteArray = parcel.createByteArray();
                    int a = a(createByteArray);
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    parcel2.writeByteArray(createByteArray);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableBodyHandler");
                    boolean a2 = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.ParcelableBodyHandler");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int a(byte[] bArr) throws RemoteException;

    boolean a() throws RemoteException;
}
