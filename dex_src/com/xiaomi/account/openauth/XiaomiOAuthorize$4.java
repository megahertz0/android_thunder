package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import miui.net.IXiaomiAuthService;

final class XiaomiOAuthorize$4 extends MiuiAuthServiceRunnable<Bundle> {
    XiaomiOAuthorize$4(Context context, Account account, Bundle bundle) {
        super(context, account, bundle);
    }

    protected final Bundle talkWithMiuiV5(IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        iXiaomiAuthService.invalidateAccessToken(this.account, this.options);
        return iXiaomiAuthService.getMiCloudAccessToken(this.account, this.options);
    }

    protected final Bundle talkWithMiuiV6(com.xiaomi.account.IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        return iXiaomiAuthService.getMiCloudAccessToken(this.account, this.options);
    }
}
