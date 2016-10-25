package com.xunlei.downloadprovider.frame.user.account;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import com.xunlei.downloadprovider.frame.user.account.a.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: SetAccountPortraitHelper.java
final class i implements b {
    final /* synthetic */ String a;
    final /* synthetic */ h b;

    i(h hVar, String str) {
        this.b = hVar;
        this.a = str;
    }

    public final void a(int i) {
        if (i == 0) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", Uri.fromFile(this.b.a));
            this.b.b.startActivityForResult(intent, 1);
            this.b.c = "photo";
            k.a(this.a, this.b.c);
        }
        if (i == 1) {
            intent = new Intent("android.intent.action.PICK", null);
            intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
            this.b.b.startActivityForResult(intent, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.b.c = "album";
            k.a(this.a, this.b.c);
        }
    }
}
