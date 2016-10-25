package com.xunlei.downloadprovider.qrcode;

import android.hardware.Camera;
import android.hardware.Camera.ErrorCallback;
import com.google.zxing.client.a.a.d;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.tdlive.R;

// compiled from: CameraActivity.java
final class c implements ErrorCallback {
    final /* synthetic */ CameraActivity a;

    c(CameraActivity cameraActivity) {
        this.a = cameraActivity;
    }

    public final void onError(int i, Camera camera) {
        XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u76f8\u673a\u53d1\u751f\u9519\u8bef", 1);
        if (camera != null) {
            camera.release();
        }
        d.a();
        CameraActivity.f(this.a).sendEmptyMessageDelayed(R.styleable.Toolbar_titleMarginBottom, TabsImpl.SYNC_TIME_OUT);
    }
}
