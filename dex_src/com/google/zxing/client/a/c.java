package com.google.zxing.client.a;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import anet.channel.util.HttpConstant;
import com.google.zxing.client.a.a.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ScanCodeActivityHandler.java
public final class c extends Handler {
    private d a;
    private f b;
    private a c;

    // compiled from: ScanCodeActivityHandler.java
    private enum a {
        PREVIEW,
        SUCCESS,
        DONE;

        static {
            a = new a("PREVIEW", 0);
            b = new a(HttpConstant.SUCCESS, 1);
            c = new a("DONE", 2);
            d = new a[]{a, b, c};
        }
    }

    public c(d dVar) {
        this.a = dVar;
        this.b = new f(dVar);
        this.b.start();
        this.c = a.b;
        b();
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (this.c == a.a && d.b() != null) {
                    d b = d.b();
                    if (b.c != null && b.g) {
                        b.i.a(this, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        try {
                            b.c.autoFocus(b.i);
                        } catch (Exception e) {
                        }
                    }
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                b();
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.c = a.b;
                Bundle data = message.getData();
                this.a.a((String) message.obj, data == null ? null : (Bitmap) data.getParcelable("barcode_bitmap"));
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                this.c = a.a;
                if (d.b() != null && this.b != null) {
                    d.b().a(this.b.a());
                }
            default:
                break;
        }
    }

    public final void a() {
        this.c = a.c;
        Message obtain = Message.obtain(this.b.a(), XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        if (obtain != null) {
            obtain.sendToTarget();
        }
        try {
            this.b.join();
        } catch (InterruptedException e) {
        }
        removeMessages(1);
        removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        removeMessages(XZBDevice.DOWNLOAD_LIST_ALL);
        removeMessages(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
    }

    private void b() {
        new StringBuilder("restartPreviewAndDecode state=").append(this.c);
        if (this.c == a.b) {
            this.c = a.a;
            if (d.b() != null) {
                d.b().a(this.b.a());
                removeMessages(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                d.b().c();
                sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_RECYCLE, 2000);
            }
            this.a.a();
        }
    }
}
