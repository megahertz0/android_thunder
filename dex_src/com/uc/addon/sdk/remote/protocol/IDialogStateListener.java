package com.uc.addon.sdk.remote.protocol;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import org.android.spdy.SpdyAgent;

public interface IDialogStateListener extends IInterface {

    public abstract class Stub extends Binder implements IDialogStateListener {

        class Proxy implements IDialogStateListener {
            private IBinder a;

            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String getInterfaceDescriptor() {
                return "com.uc.addon.sdk.remote.protocol.IDialogStateListener";
            }

            public void onDialogStateChangedRemote(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IDialogStateListener");
                obtain.writeInt(i);
                obtain.writeInt(i2);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, "com.uc.addon.sdk.remote.protocol.IDialogStateListener");
        }

        public static IDialogStateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.uc.addon.sdk.remote.protocol.IDialogStateListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDialogStateListener)) ? new Proxy(iBinder) : (IDialogStateListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IDialogStateListener");
                    onDialogStateChangedRemote(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.uc.addon.sdk.remote.protocol.IDialogStateListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onDialogStateChangedRemote(int i, int i2) throws RemoteException;
}
