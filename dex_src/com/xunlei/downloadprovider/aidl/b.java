package com.xunlei.downloadprovider.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: IDownloadService.java
public interface b extends IInterface {

    // compiled from: IDownloadService.java
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.xunlei.downloadprovider.aidl.IDownloadService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    parcel.enforceInterface("com.xunlei.downloadprovider.aidl.IDownloadService");
                    boolean a = a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    parcel.enforceInterface("com.xunlei.downloadprovider.aidl.IDownloadService");
                    ExternTaskInfo a2 = a(parcel.readString());
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.xunlei.downloadprovider.aidl.IDownloadService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    ExternTaskInfo a(String str) throws RemoteException;

    boolean a(String str, String str2, String str3, String str4) throws RemoteException;
}
