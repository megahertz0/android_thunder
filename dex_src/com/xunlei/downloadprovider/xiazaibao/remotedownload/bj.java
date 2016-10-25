package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: XZBWebviewActivity.java
final class bj extends Handler {
    final /* synthetic */ XZBWebviewActivity a;

    bj(XZBWebviewActivity xZBWebviewActivity, Looper looper) {
        this.a = xZBWebviewActivity;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                XZBWebviewActivity.a(this.a);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                XZBWebviewActivity.b(this.a);
                break;
        }
        super.handleMessage(message);
    }
}
