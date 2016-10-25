package com.xiaomi.account;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class IXiaomiAuthResponse$Stub extends Binder implements IXiaomiAuthResponse {
    private static final String DESCRIPTOR = "com.xiaomi.account.IXiaomiAuthResponse";
    static final int TRANSACTION_onCancel = 2;
    static final int TRANSACTION_onResult = 1;

    private static class Proxy implements IXiaomiAuthResponse {
        private IBinder mRemote;

        Proxy(IBinder iBinder) {
            this.mRemote = iBinder;
        }

        public IBinder asBinder() {
            return this.mRemote;
        }

        public String getInterfaceDescriptor() {
            return IXiaomiAuthResponse$Stub.DESCRIPTOR;
        }

        public void onResult(Bundle bundle) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken(IXiaomiAuthResponse$Stub.DESCRIPTOR);
            if (bundle != null) {
                obtain.writeInt(IXiaomiAuthResponse$Stub.TRANSACTION_onResult);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(IXiaomiAuthResponse$Stub.TRANSACTION_onResult, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        }

        public void onCancel() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken(IXiaomiAuthResponse$Stub.DESCRIPTOR);
            this.mRemote.transact(IXiaomiAuthResponse$Stub.TRANSACTION_onCancel, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IXiaomiAuthResponse$Stub() {
        attachInterface(this, DESCRIPTOR);
    }

    public static IXiaomiAuthResponse asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        return (queryLocalInterface == null || !(queryLocalInterface instanceof IXiaomiAuthResponse)) ? new Proxy(iBinder) : (IXiaomiAuthResponse) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case TRANSACTION_onResult:
                Bundle bundle;
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                onResult(bundle);
                parcel2.writeNoException();
                return true;
            case TRANSACTION_onCancel:
                parcel.enforceInterface(DESCRIPTOR);
                onCancel();
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString(DESCRIPTOR);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
