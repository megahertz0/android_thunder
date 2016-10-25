package com.xunlei.tdlive.base;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ListScrollEventUtil.java
public class l {
    private float a;
    private float b;
    private int c;
    private boolean d;
    private int e;
    private int f;
    private MarginLayoutParams g;

    public <T> boolean a(T t, T t2) {
        return b(t, t2);
    }

    public <T> boolean a(T t, T t2, boolean z) {
        a(z);
        return a((Object) t, (Object) t2);
    }

    private void a(boolean z) {
        this.d = z;
    }

    private <T> boolean b(T t, T t2) {
        b((View) t2);
        return a((MotionEvent) t, (View) t2);
    }

    private boolean a(MotionEvent motionEvent, View view) {
        switch (motionEvent.getAction()) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.b = b(motionEvent);
                a();
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                e();
                a();
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                a(motionEvent);
                d();
                a(view, c());
                break;
        }
        return false;
    }

    private void a(View view, int i) {
        if (this.f != i) {
            a(-i);
            a(view);
        }
    }

    private void a() {
        this.f = b();
    }

    private void a(View view) {
        if (view.getVisibility() != 0) {
            view.setVisibility(0);
        }
        view.setLayoutParams(this.g);
    }

    private int b() {
        return this.d ? b(this.g.topMargin) : b(this.g.bottomMargin);
    }

    private void a(MotionEvent motionEvent) {
        this.a = b(motionEvent);
        this.e = (int) (this.a - this.b);
    }

    private int c() {
        int i = this.f - this.e;
        if (i < 0) {
            i = 0;
        }
        return i > this.c ? this.c : i;
    }

    private void a(int i) {
        if (this.d) {
            this.g.topMargin = i;
        } else {
            this.g.bottomMargin = i;
        }
    }

    private int b(int i) {
        return Math.abs(i);
    }

    private void d() {
        if (b(this.e) <= this.c) {
            return;
        }
        if (this.e > 0) {
            this.b = this.a - ((float) this.c);
        } else {
            this.b = ((float) this.c) + this.a;
        }
    }

    private float b(MotionEvent motionEvent) {
        return motionEvent.getRawY();
    }

    private void e() {
        this.b = 0.0f;
        this.a = 0.0f;
    }

    private void b(View view) {
        this.g = (MarginLayoutParams) view.getLayoutParams();
        this.c = view.getHeight();
    }
}
