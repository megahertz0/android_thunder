package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationSideChannel extends IInterface {

    public static abstract class Stub extends Binder implements INotificationSideChannel {
        private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        private static class Proxy implements INotificationSideChannel {
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

            public void notify(String str, int i, String str2, Notification notification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                obtain.writeInt(i);
                obtain.writeString(str2);
                if (notification != null) {
                    obtain.writeInt(TRANSACTION_notify);
                    notification.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_notify, obtain, null, TRANSACTION_notify);
                obtain.recycle();
            }

            public void cancel(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                obtain.writeInt(i);
                obtain.writeString(str2);
                this.mRemote.transact(TRANSACTION_cancel, obtain, null, TRANSACTION_notify);
                obtain.recycle();
            }

            public void cancelAll(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                this.mRemote.transact(TRANSACTION_cancelAll, obtain, null, TRANSACTION_notify);
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationSideChannel asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof INotificationSideChannel)) ? new Proxy(iBinder) : (INotificationSideChannel) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case TRANSACTION_notify:
                    Notification notification;
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    int readInt = parcel.readInt();
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        notification = (Notification) Notification.CREATOR.createFromParcel(parcel);
                    } else {
                        notification = null;
                    }
                    notify(readString, readInt, readString2, notification);
                    return true;
                case TRANSACTION_cancel:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancel(parcel.readString(), parcel.readInt(), parcel.readString());
                    return true;
                case TRANSACTION_cancelAll:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAll(parcel.readString());
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void cancel(String str, int i, String str2) throws RemoteException;

    void cancelAll(String str) throws RemoteException;

    void notify(String str, int i, String str2, Notification notification) throws RemoteException;
}
