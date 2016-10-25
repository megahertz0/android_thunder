package com.xunlei.downloadprovider.frame.user.account;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ActionSheetHelper.java
public final class b implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ com.xunlei.downloadprovider.frame.user.account.a.b b;

    public b(Dialog dialog, com.xunlei.downloadprovider.frame.user.account.a.b bVar) {
        this.a = dialog;
        this.b = bVar;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        this.b.a(XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
