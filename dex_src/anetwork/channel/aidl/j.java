package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface j extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements j {

        // compiled from: Taobao
        private static class a implements j {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final int a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            }

            public final void b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                this.a.transact(XZBDevice.DOWNLOAD_LIST_RECYCLE, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public final int c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                this.a.transact(XZBDevice.DOWNLOAD_LIST_FAILED, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            }

            public final int a(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                if (bArr == null) {
                    obtain.writeInt(-1);
                } else {
                    obtain.writeInt(bArr.length);
                }
                this.a.transact(XZBDevice.DOWNLOAD_LIST_ALL, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.readByteArray(bArr);
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            }

            public final long a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                obtain.writeInt(i);
                this.a.transact(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, obtain, obtain2, 0);
                obtain2.readException();
                long readLong = obtain2.readLong();
                obtain2.recycle();
                obtain.recycle();
                return readLong;
            }

            public final int d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                this.a.transact(R.styleable.Toolbar_contentInsetEnd, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            }
        }

        public a() {
            attachInterface(this, "anetwork.channel.aidl.ParcelableInputStream");
        }

        public static j a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.ParcelableInputStream");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof j)) ? new a(iBinder) : (j) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int a;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    a = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    b();
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    a = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    byte[] bArr;
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    a = parcel.readInt();
                    if (a < 0) {
                        bArr = null;
                    } else {
                        bArr = new byte[a];
                    }
                    int a2 = a(bArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(a2);
                    parcel2.writeByteArray(bArr);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    long a3 = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(a3);
                    return true;
                case R.styleable.Toolbar_contentInsetEnd:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    a = d();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.ParcelableInputStream");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int a() throws RemoteException;

    int a(byte[] bArr) throws RemoteException;

    long a(int i) throws RemoteException;

    void b() throws RemoteException;

    int c() throws RemoteException;

    int d() throws RemoteException;
}
