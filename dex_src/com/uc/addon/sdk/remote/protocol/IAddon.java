package com.uc.addon.sdk.remote.protocol;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public interface IAddon extends IInterface {

    public abstract class Stub extends Binder implements IAddon {

        class Proxy implements IAddon {
            private IBinder a;

            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String getInterfaceDescriptor() {
                return "com.uc.addon.sdk.remote.protocol.IAddon";
            }

            public void onConnected(IApp iApp) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IAddon");
                obtain.writeStrongBinder(iApp != null ? iApp.asBinder() : null);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void onDisConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IAddon");
                this.a.transact(XZBDevice.DOWNLOAD_LIST_RECYCLE, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void request(String str, Bundle bundle, IValueCallback iValueCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IAddon");
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                obtain.writeStrongBinder(iValueCallback != null ? iValueCallback.asBinder() : null);
                this.a.transact(XZBDevice.DOWNLOAD_LIST_FAILED, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, "com.uc.addon.sdk.remote.protocol.IAddon");
        }

        public static IAddon asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.uc.addon.sdk.remote.protocol.IAddon");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IAddon)) ? new Proxy(iBinder) : (IAddon) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IAddon");
                    onConnected(com.uc.addon.sdk.remote.protocol.IApp.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IAddon");
                    onDisConnected();
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IAddon");
                    request(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, com.uc.addon.sdk.remote.protocol.IValueCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.uc.addon.sdk.remote.protocol.IAddon");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onConnected(IApp iApp) throws RemoteException;

    void onDisConnected() throws RemoteException;

    void request(String str, Bundle bundle, IValueCallback iValueCallback) throws RemoteException;
}
