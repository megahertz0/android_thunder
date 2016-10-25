package com.xunlei.downloadprovider.player;

import android.provider.Settings.System;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: MediaPlayerGestureView.java
final class o extends SimpleOnGestureListener {
    final /* synthetic */ MediaPlayerGestureView a;

    o(MediaPlayerGestureView mediaPlayerGestureView) {
        this.a = mediaPlayerGestureView;
    }

    public final boolean onDown(MotionEvent motionEvent) {
        this.a.d = motionEvent.getRawX();
        this.a.e = motionEvent.getRawY();
        if (this.a.a != null) {
            int h = this.a.a.h();
            if (h >= 0) {
                this.a.setTouchDownPosition(h);
            }
        }
        new StringBuilder("onDown--mState=").append(this.a.c).append("|X=").append(this.a.d).append("|Y=").append(this.a.e);
        return true;
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int i = 0;
        if (this.a.x) {
            float c = this.a.d;
            float d = this.a.e;
            if (MediaPlayerGestureView.a(this.a, c, d)) {
                int x = (int) (motionEvent2.getX() - c);
                int y = (int) (motionEvent2.getY() - d);
                int i2;
                switch (AnonymousClass_1.a[this.a.c.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (Math.abs(f) > Math.abs(f2)) {
                            MediaPlayerGestureView.b(this.a, x);
                        } else if (c > ((float) (this.a.getWidth() / 2))) {
                            MediaPlayerGestureView.f(this.a);
                        } else {
                            MediaPlayerGestureView.g(this.a);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (this.a.f > 0) {
                            i2 = ((int) (((float) x) * this.a.i)) + this.a.g;
                            if (i2 >= 0) {
                                if (i2 > this.a.f) {
                                    i = this.a.f;
                                } else {
                                    i = i2;
                                }
                            }
                            this.a.a(x);
                            MediaPlayerGestureView.a(this.a, i, this.a.f);
                            this.a.l.setProgress(i);
                            this.a.h = i;
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (this.a.n > 0) {
                            i2 = (-((this.a.n * y) / this.a.getHeight())) + this.a.o;
                            if (i2 >= 0) {
                                if (i2 > this.a.n) {
                                    i = this.a.n;
                                } else {
                                    i = i2;
                                }
                            }
                            this.a.b(i);
                            this.a.s.setProgress(i);
                            this.a.p.setStreamVolume(MqttConnectOptions.MQTT_VERSION_3_1, i, SpdyProtocol.PUBKEY_SEQ_ADASH);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (this.a.t != null) {
                            int q = (-((y * 255) / this.a.getHeight())) + this.a.u;
                            if (q >= 0) {
                                if (q > 255) {
                                    i = 255;
                                } else {
                                    i = q;
                                }
                            }
                            this.a.w.setProgress(i);
                            System.putInt(this.a.t.a.getContentResolver(), "screen_brightness", i);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return true;
    }
}
