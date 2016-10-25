package com.aplayer.aplayerandroid;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.alipay.sdk.util.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: APlayerAndroid.java
final class a implements OnTouchListener {
    float a;
    float b;
    float c;
    float d;
    final /* synthetic */ APlayerAndroid e;

    a(APlayerAndroid aPlayerAndroid) {
        this.e = aPlayerAndroid;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                float rawY = motionEvent.getRawY() - this.b;
                this.c = ((motionEvent.getRawX() - this.a) * 0.5625f) + this.c;
                this.d += (rawY * 0.5625f) / 2.0f;
                if (((double) this.d) > 90.0d) {
                    this.d = 90.0f;
                }
                if (((double) this.d) < -90.0d) {
                    this.d = 90.0f;
                }
                APlayerAndroid.access$0();
                new StringBuilder("move anglex = ").append(this.c);
                APlayerAndroid.access$0();
                new StringBuilder("move angley = ").append(this.d);
                String toString = new StringBuilder(String.valueOf(this.c)).append(h.b).append(this.d).toString();
                if (!APlayerAndroid.access$1(this.e)) {
                    APlayerAndroid.access$3(this.e, 2411, toString, APlayerAndroid.access$2(this.e));
                    this.a = (float) ((int) motionEvent.getRawX());
                    this.b = (float) ((int) motionEvent.getRawY());
                }
                break;
            default:
                this.a = (float) ((int) motionEvent.getRawX());
                this.b = (float) ((int) motionEvent.getRawY());
                break;
        }
        return true;
    }
}
