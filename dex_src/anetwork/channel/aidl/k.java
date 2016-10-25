package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public interface k extends IInterface {

    // compiled from: Taobao
    public static abstract class a extends Binder implements k {

        // compiled from: Taobao
        private static class a implements k {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void a(DefaultProgressEvent defaultProgressEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableNetworkListener");
                if (defaultProgressEvent != null) {
                    obtain.writeInt(1);
                    defaultProgressEvent.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public final void a(DefaultFinishEvent defaultFinishEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableNetworkListener");
                if (defaultFinishEvent != null) {
                    obtain.writeInt(1);
                    defaultFinishEvent.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.a.transact(XZBDevice.DOWNLOAD_LIST_RECYCLE, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public final boolean a(int i, ParcelableHeader parcelableHeader) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableNetworkListener");
                obtain.writeInt(i);
                if (parcelableHeader != null) {
                    obtain.writeInt(1);
                    parcelableHeader.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.a.transact(XZBDevice.DOWNLOAD_LIST_FAILED, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            }

            public final void a(j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableNetworkListener");
                obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                this.a.transact(XZBDevice.DOWNLOAD_LIST_ALL, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public final byte a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableNetworkListener");
                this.a.transact(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, obtain, obtain2, 0);
                obtain2.readException();
                byte readByte = obtain2.readByte();
                obtain2.recycle();
                obtain.recycle();
                return readByte;
            }
        }

        public a() {
            attachInterface(this, "anetwork.channel.aidl.ParcelableNetworkListener");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ParcelableHeader parcelableHeader = null;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    DefaultProgressEvent defaultProgressEvent;
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableNetworkListener");
                    if (parcel.readInt() != 0) {
                        defaultProgressEvent = (DefaultProgressEvent) DefaultProgressEvent.CREATOR.createFromParcel(parcel);
                    }
                    a(defaultProgressEvent);
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    DefaultFinishEvent defaultFinishEvent;
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableNetworkListener");
                    if (parcel.readInt() != 0) {
                        defaultFinishEvent = (DefaultFinishEvent) DefaultFinishEvent.CREATOR.createFromParcel(parcel);
                    }
                    a(defaultFinishEvent);
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableNetworkListener");
                    int readInt = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        parcelableHeader = (ParcelableHeader) ParcelableHeader.c.createFromParcel(parcel);
                    }
                    boolean a = a(readInt, parcelableHeader);
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableNetworkListener");
                    a(anetwork.channel.aidl.j.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableNetworkListener");
                    byte a2 = a();
                    parcel2.writeNoException();
                    parcel2.writeByte(a2);
                    return true;
                case 1598968902:
                    parcel2.writeString("anetwork.channel.aidl.ParcelableNetworkListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    byte a() throws RemoteException;

    void a(DefaultFinishEvent defaultFinishEvent) throws RemoteException;

    void a(DefaultProgressEvent defaultProgressEvent) throws RemoteException;

    void a(j jVar) throws RemoteException;

    boolean a(int i, ParcelableHeader parcelableHeader) throws RemoteException;
}
