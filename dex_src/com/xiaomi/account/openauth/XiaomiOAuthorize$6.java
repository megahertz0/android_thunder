package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import miui.net.IXiaomiAuthService;

class XiaomiOAuthorize$6 extends MiuiAuthServiceRunnable<Boolean> {
    final /* synthetic */ XiaomiOAuthorize this$0;

    XiaomiOAuthorize$6(XiaomiOAuthorize xiaomiOAuthorize, Context context, Account account, Bundle bundle) {
        this.this$0 = xiaomiOAuthorize;
        super(context, account, bundle);
    }

    protected Boolean talkWithMiuiV5(IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        return Boolean.valueOf(false);
    }

    protected Boolean talkWithMiuiV6(com.xiaomi.account.IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        return Boolean.valueOf(iXiaomiAuthService.supportResponseWay());
    }
}
