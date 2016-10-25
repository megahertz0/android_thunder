package com.google.zxing.client.a.a;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;

// compiled from: PreviewCallback.java
public final class e implements PreviewCallback {
    private static final String a;
    private final b b;
    private final boolean c;
    private Handler d;
    private int e;

    static {
        a = e.class.getSimpleName();
    }

    e(b bVar) {
        this.b = bVar;
        this.c = false;
    }

    public final void a(Handler handler, int i) {
        this.d = handler;
        this.e = i;
    }

    public final void onPreviewFrame(byte[] bArr, Camera camera) {
        Point point = this.b.c;
        camera.setPreviewCallback(null);
        if (this.d != null) {
            this.d.obtainMessage(this.e, point.x, point.y, bArr).sendToTarget();
            this.d = null;
        }
    }
}
