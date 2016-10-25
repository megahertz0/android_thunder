package com.uc.addon.sdk.remote.protocol;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import org.android.spdy.SpdyAgent;

public interface IDownloadTaskStatusChangeListener extends IInterface {

    public abstract class Stub extends Binder implements IDownloadTaskStatusChangeListener {

        class Proxy implements IDownloadTaskStatusChangeListener {
            private IBinder a;

            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String getInterfaceDescriptor() {
                return "com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener";
            }

            public void onStatusChangeRemote(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener");
                obtain.writeInt(i);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, "com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener");
        }

        public static IDownloadTaskStatusChangeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDownloadTaskStatusChangeListener)) ? new Proxy(iBinder) : (IDownloadTaskStatusChangeListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener");
                    onStatusChangeRemote(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onStatusChangeRemote(int i, Bundle bundle) throws RemoteException;
}
