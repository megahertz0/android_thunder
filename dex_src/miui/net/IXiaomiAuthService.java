package miui.net;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IXiaomiAuthService extends IInterface {

    public static abstract class Stub extends Binder implements IXiaomiAuthService {
        private static final String DESCRIPTOR = "miui.net.IXiaomiAuthService";
        static final int TRANSACTION_getMiCloudAccessToken = 2;
        static final int TRANSACTION_getMiCloudUserInfo = 1;
        static final int TRANSACTION_getSnsAccessToken = 3;
        static final int TRANSACTION_invalidateAccessToken = 4;

        private static class Proxy implements IXiaomiAuthService {
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

            public Bundle getMiCloudUserInfo(Account account, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (account != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    account.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_getMiCloudUserInfo, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                } else {
                    bundle2 = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return bundle2;
            }

            public Bundle getMiCloudAccessToken(Account account, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (account != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    account.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_getMiCloudAccessToken, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                } else {
                    bundle2 = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return bundle2;
            }

            public Bundle getSnsAccessToken(Account account, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (account != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    account.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_getSnsAccessToken, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                } else {
                    bundle2 = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return bundle2;
            }

            public void invalidateAccessToken(Account account, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (account != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    account.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_getMiCloudUserInfo);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_invalidateAccessToken, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXiaomiAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IXiaomiAuthService)) ? new Proxy(iBinder) : (IXiaomiAuthService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Account account;
            Bundle bundle;
            switch (i) {
                case TRANSACTION_getMiCloudUserInfo:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        account = (Account) Account.CREATOR.createFromParcel(parcel);
                    } else {
                        account = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    bundle = getMiCloudUserInfo(account, bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(parcel2, TRANSACTION_getMiCloudUserInfo);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case TRANSACTION_getMiCloudAccessToken:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        account = (Account) Account.CREATOR.createFromParcel(parcel);
                    } else {
                        account = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    bundle = getMiCloudAccessToken(account, bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(parcel2, TRANSACTION_getMiCloudUserInfo);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case TRANSACTION_getSnsAccessToken:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        account = (Account) Account.CREATOR.createFromParcel(parcel);
                    } else {
                        account = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    bundle = getSnsAccessToken(account, bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(parcel2, TRANSACTION_getMiCloudUserInfo);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case TRANSACTION_invalidateAccessToken:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        account = (Account) Account.CREATOR.createFromParcel(parcel);
                    } else {
                        account = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    invalidateAccessToken(account, bundle);
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

    Bundle getMiCloudAccessToken(Account account, Bundle bundle) throws RemoteException;

    Bundle getMiCloudUserInfo(Account account, Bundle bundle) throws RemoteException;

    Bundle getSnsAccessToken(Account account, Bundle bundle) throws RemoteException;

    void invalidateAccessToken(Account account, Bundle bundle) throws RemoteException;
}
