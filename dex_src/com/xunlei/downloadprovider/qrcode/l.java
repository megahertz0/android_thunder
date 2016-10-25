package com.xunlei.downloadprovider.qrcode;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.MediaStore.Images.Media;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LocalScancodeActivity.java
final class l implements OnClickListener {
    final /* synthetic */ LocalScancodeActivity a;

    l(LocalScancodeActivity localScancodeActivity) {
        this.a = localScancodeActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        this.a.startActivityForResult(intent, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
