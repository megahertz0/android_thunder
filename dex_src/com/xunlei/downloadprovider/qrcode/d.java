package com.xunlei.downloadprovider.qrcode;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: CameraActivity.java
final class d implements OnClickListener {
    final /* synthetic */ CameraActivity a;

    d(CameraActivity cameraActivity) {
        this.a = cameraActivity;
    }

    public final void onClick(View view) {
        if (CameraActivity.g(this.a) == null || !CameraActivity.g(this.a).isShowing()) {
            this.a.a(0, -1);
        } else {
            this.a.c();
        }
    }
}
