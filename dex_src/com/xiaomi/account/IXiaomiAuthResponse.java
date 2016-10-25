package com.xiaomi.account;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface IXiaomiAuthResponse extends IInterface {
    void onCancel() throws RemoteException;

    void onResult(Bundle bundle) throws RemoteException;
}
