package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.account.IXiaomiAuthResponse;
import miui.net.IXiaomiAuthService;

final class XiaomiOAuthorize$3 extends MiuiAuthServiceRunnable<Bundle> {
    final /* synthetic */ IXiaomiAuthResponse val$resp;

    XiaomiOAuthorize$3(Context context, Account account, Bundle bundle, IXiaomiAuthResponse iXiaomiAuthResponse) {
        this.val$resp = iXiaomiAuthResponse;
        super(context, account, bundle);
    }

    protected final Bundle talkWithMiuiV5(IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        throw new IllegalStateException("should not be here");
    }

    protected final Bundle talkWithMiuiV6(com.xiaomi.account.IXiaomiAuthService iXiaomiAuthService) throws RemoteException {
        iXiaomiAuthService.getAccessTokenInResponse(this.val$resp, this.options, 1, 0);
        return null;
    }
}
