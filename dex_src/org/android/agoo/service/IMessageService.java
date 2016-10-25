package org.android.agoo.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

// compiled from: Taobao
public interface IMessageService extends IInterface {

    // compiled from: Taobao
    public static abstract class Stub extends Binder implements IMessageService {
        private static final String DESCRIPTOR = "org.android.agoo.service.IMessageService";
        static final int TRANSACTION_ping = 1;
        static final int TRANSACTION_probe = 2;

        // compiled from: Taobao
        private static class Proxy implements IMessageService {
            private IBinder a;

            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public boolean ping() throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.a.transact(TRANSACTION_ping, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            }

            public void probe() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.a.transact(TRANSACTION_probe, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMessageService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMessageService)) ? new Proxy(iBinder) : (IMessageService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case TRANSACTION_ping:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ping = ping();
                    parcel2.writeNoException();
                    parcel2.writeInt(ping ? 1 : 0);
                    return true;
                case TRANSACTION_probe:
                    parcel.enforceInterface(DESCRIPTOR);
                    probe();
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

    boolean ping() throws RemoteException;

    void probe() throws RemoteException;
}
