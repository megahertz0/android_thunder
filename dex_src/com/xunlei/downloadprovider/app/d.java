package com.xunlei.downloadprovider.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import com.xunlei.downloadprovider.util.ai;

// compiled from: BaseActivity.java
final class d implements OnClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ BaseActivity b;

    d(BaseActivity baseActivity, String[] strArr) {
        this.b = baseActivity;
        this.a = strArr;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.b.mRequiredPermissionDialog = null;
        if (VERSION.SDK_INT >= 23) {
            this.b.requestPermissions(this.a, BaseActivity.REQUESTCODE_REQUIRED_PERMISSIONS_FOR_LAUNCH);
        }
        ai.a(this.b);
    }
}
