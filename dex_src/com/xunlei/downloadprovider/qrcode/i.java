package com.xunlei.downloadprovider.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: LocalScancodeActivity.java
final class i extends Handler {
    final /* synthetic */ LocalScancodeActivity a;

    i(LocalScancodeActivity localScancodeActivity) {
        this.a = localScancodeActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.a.n = message.getData().getString("scancode_result");
                this.a.d.b(XZBDevice.Wait);
                this.a.a.setVisibility(XZBDevice.Wait);
                LocalScancodeActivity.b(this.a, this.a.n);
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.a.l = AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
                this.a.l = this.a.l | 2;
                this.a.a(false);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                Bundle data = message.getData();
                if (data == null) {
                    this.a.a.setVisibility(XZBDevice.Wait);
                    this.a.a("\u83b7\u53d6\u56fe\u7247\u5931\u8d25");
                    return;
                }
                Bitmap bitmap = (Bitmap) data.get("bitmap");
                if (bitmap == null) {
                    this.a.a.setVisibility(XZBDevice.Wait);
                    this.a.a("\u83b7\u53d6\u56fe\u7247\u5931\u8d25");
                    return;
                }
                this.a.i.setImageBitmap(bitmap);
            default:
                break;
        }
    }
}
