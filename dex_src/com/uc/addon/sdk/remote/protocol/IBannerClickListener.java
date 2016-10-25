package com.uc.addon.sdk.remote.protocol;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public interface IBannerClickListener extends IInterface {

    public abstract class Stub extends Binder implements IBannerClickListener {

        class Proxy implements IBannerClickListener {
            private IBinder a;

            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String getInterfaceDescriptor() {
                return "com.uc.addon.sdk.remote.protocol.IBannerClickListener";
            }

            public void onBannerClickRemote(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IBannerClickListener");
                obtain.writeInt(i);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void onBannerDismissRemote() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.uc.addon.sdk.remote.protocol.IBannerClickListener");
                this.a.transact(XZBDevice.DOWNLOAD_LIST_RECYCLE, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, "com.uc.addon.sdk.remote.protocol.IBannerClickListener");
        }

        public static IBannerClickListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.uc.addon.sdk.remote.protocol.IBannerClickListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IBannerClickListener)) ? new Proxy(iBinder) : (IBannerClickListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IBannerClickListener");
                    onBannerClickRemote(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("com.uc.addon.sdk.remote.protocol.IBannerClickListener");
                    onBannerDismissRemote();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.uc.addon.sdk.remote.protocol.IBannerClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onBannerClickRemote(int i) throws RemoteException;

    void onBannerDismissRemote() throws RemoteException;
}
