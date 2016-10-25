package com.xunlei.downloadprovider.qrcode;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: CameraActivity.java
final class e implements OnClickListener {
    final /* synthetic */ CameraActivity a;

    e(CameraActivity cameraActivity) {
        this.a = cameraActivity;
    }

    public final void onClick(View view) {
        CameraActivity.h(this.a).dismiss();
        this.a.startActivity(new Intent(this.a, LocalScancodeActivity.class));
        StatReporter.reportQRClick("localScan");
    }
}
