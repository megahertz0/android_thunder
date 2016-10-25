package com.xunlei.downloadprovider.qrcode;

import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.zxing.client.a.a.d;
import com.xunlei.downloadprovider.model.protocol.g.k;
import com.xunlei.downloadprovider.qrcode.view.ViewfinderView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: CameraActivity.java
final class a extends Handler {
    final /* synthetic */ CameraActivity a;

    a(CameraActivity cameraActivity) {
        this.a = cameraActivity;
    }

    public final void handleMessage(Message message) {
        Object obj;
        switch (message.what) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (d.b() != null && (message.obj instanceof String)) {
                    String str = (String) message.obj;
                    ViewfinderView.setShowCross(Boolean.valueOf(false));
                    if (CameraActivity.a(this.a)) {
                        CameraActivity.a(this.a, str);
                        CameraActivity.b(this.a, str);
                        return;
                    }
                    CameraActivity.c(this.a, str);
                }
            case R.styleable.Toolbar_titleMarginBottom:
                this.a.runOnUiThread(new b(this));
            case IHost.HOST_NOFITY_PAGE_SELECTED:
                CameraActivity.e(this.a);
            case 1111:
                if (CameraActivity.b(this.a).isShown()) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2131034122);
                    loadAnimation.setDuration(1000);
                    CameraActivity.b(this.a).startAnimation(loadAnimation);
                    CameraActivity.b(this.a).setVisibility(XZBDevice.Wait);
                }
            case 1044493:
                obj = message.obj;
                if (obj instanceof k) {
                    CameraActivity.b(this.a, (k) obj);
                }
            case 1044494:
                CameraActivity.d(this.a);
                this.a.c();
            case 1044495:
                obj = message.obj;
                com.xunlei.downloadprovider.download.report.a.h("click");
                if (obj instanceof String) {
                    CameraActivity.d(this.a, (String) obj);
                } else if (obj instanceof k) {
                    CameraActivity.a(this.a, (k) obj);
                    CameraActivity.d(this.a, CameraActivity.c(this.a).b);
                }
            default:
                break;
        }
    }
}
