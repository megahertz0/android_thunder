package com.sina.sso;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface RemoteSSO extends IInterface {

    public static abstract class Stub extends Binder implements RemoteSSO {
        private static final String DESCRIPTOR = "com.sina.sso.RemoteSSO";
        static final int TRANSACTION_getActivityName = 2;
        static final int TRANSACTION_getLoginUserName = 3;
        static final int TRANSACTION_getPackageName = 1;

        private static class Proxy implements RemoteSSO {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            public String getPackageName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getPackageName, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            }

            public String getActivityName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getActivityName, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            }

            public String getLoginUserName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getLoginUserName, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RemoteSSO asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof RemoteSSO)) ? new Proxy(iBinder) : (RemoteSSO) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String packageName;
            switch (i) {
                case TRANSACTION_getPackageName:
                    parcel.enforceInterface(DESCRIPTOR);
                    packageName = getPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case TRANSACTION_getActivityName:
                    parcel.enforceInterface(DESCRIPTOR);
                    packageName = getActivityName();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case TRANSACTION_getLoginUserName:
                    parcel.enforceInterface(DESCRIPTOR);
                    packageName = getLoginUserName();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String getActivityName() throws RemoteException;

    String getLoginUserName() throws RemoteException;

    String getPackageName() throws RemoteException;
}
