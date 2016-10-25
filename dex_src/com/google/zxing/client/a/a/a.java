package com.google.zxing.client.a.a;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import android.os.Message;
import com.xunlei.tdlive.R;

// compiled from: AutoFocusCallback.java
public final class a implements AutoFocusCallback {
    private static final String a;
    private Handler b;
    private int c;
    private b d;

    static {
        a = a.class.getSimpleName();
    }

    public a(b bVar) {
        this.d = bVar;
    }

    public final void a(Handler handler, int i) {
        this.b = handler;
        this.c = i;
    }

    public final void onAutoFocus(boolean z, Camera camera) {
        if (this.b != null) {
            this.b.obtainMessage(R.styleable.Toolbar_contentInsetEnd, Boolean.valueOf(z)).sendToTarget();
            Message obtainMessage = this.b.obtainMessage(this.c, Boolean.valueOf(z));
            if (this.d == null || this.d.g) {
                this.b.sendMessageDelayed(obtainMessage, 500);
            } else {
                this.b.sendMessageDelayed(obtainMessage, 2000);
            }
            this.b = null;
        }
    }
}
