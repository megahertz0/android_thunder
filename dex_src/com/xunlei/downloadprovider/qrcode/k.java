package com.xunlei.downloadprovider.qrcode;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: LocalScancodeActivity.java
final class k implements OnClickListener {
    final /* synthetic */ LocalScancodeActivity a;

    k(LocalScancodeActivity localScancodeActivity) {
        this.a = localScancodeActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.finish();
    }
}
