package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import miui.net.IXiaomiAuthService;

final class XiaomiOAuthorize$5 extends MiuiAuthServiceRunnable<Boolean> {
    XiaomiOAuthorize$5(Context context, Account account, Bundle bundle) {
        super(context, account, bundle);
    }

    protected final Boolean talkWithMiuiV5(IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        return Boolean.valueOf(true);
    }

    protected final Boolean talkWithMiuiV6(com.xiaomi.account.IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        return Boolean.valueOf(true);
    }
}
