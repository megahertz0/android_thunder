package com.xunlei.tdlive.play.a;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.xunlei.tdlive.util.d;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ReplayDialogPresenter.java
private class aa$e implements OnGestureListener, OnTouchListener {
    private PointF a;
    final /* synthetic */ aa b;
    private int c;
    private boolean d;
    private GestureDetector e;

    public aa$e(aa aaVar) {
        this.b = aaVar;
        this.c = 0;
        this.d = false;
        this.e = new GestureDetector(aaVar.e(), this);
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.a = new PointF(motionEvent.getRawX(), motionEvent.getRawY());
        this.c = 0;
        this.d = false;
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        aa.g(this.b);
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int i = SimpleLog.LOG_LEVEL_DEBUG;
        if (!aa.h(this.b) && motionEvent.getPointerCount() <= 1 && motionEvent2.getPointerCount() <= 1) {
            float rawX = motionEvent2.getRawX() - motionEvent.getRawX();
            float rawY = motionEvent2.getRawY() - motionEvent.getRawY();
            float abs = Math.abs(rawX);
            rawY = Math.abs(rawY);
            View i2 = aa.i(this.b);
            if (this.c == 0) {
                if (abs > 5.0f || rawY > 5.0f) {
                    int i3;
                    if (abs > rawY) {
                        i3 = 1;
                    } else {
                        i3 = 2;
                    }
                    this.c = i3;
                }
                if (abs > rawY) {
                    i = 1;
                }
                this.c = i;
            } else if (this.c == 1) {
                if (rawX < 0.0f && aa.j(this.b)) {
                    i2.setTranslationX(rawX);
                }
                if (rawX > 0.0f && !aa.j(this.b)) {
                    i2.setTranslationX(rawX);
                }
            }
        }
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3 = 9999.0f;
        if (!aa.h(this.b) && motionEvent.getPointerCount() <= 1) {
            float rawX = motionEvent2.getRawX() - motionEvent.getRawX();
            float rawY = motionEvent2.getRawY() - motionEvent.getRawY();
            if (rawX > 0.0f) {
                rawX = 9999.0f;
            } else if (rawX < 0.0f) {
                rawX = -9999.0f;
            }
            if (rawY <= 0.0f) {
                if (rawX < 0.0f) {
                    f3 = -9999.0f;
                } else {
                    f3 = rawY;
                }
            }
            a(this.c, rawX, f3);
        }
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (5 == actionMasked) {
            this.d = true;
            a(0, 0.0f, 0.0f);
        }
        if (this.d && actionMasked != 0) {
            return true;
        }
        boolean onTouchEvent = this.e.onTouchEvent(motionEvent);
        if (onTouchEvent) {
            return true;
        }
        switch (actionMasked) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (!aa.h(this.b)) {
                    a(this.c, motionEvent.getRawX() - this.a.x, motionEvent.getRawY() - this.a.y);
                    return true;
                }
        }
        return onTouchEvent;
    }

    private void a(int i, float f, float f2) {
        float f3 = ((float) d.a(this.b.e()).x) / 4.0f;
        if (i == 1) {
            if (f > f3) {
                aa.b(this.b, true);
            } else if (f < (-f3)) {
                aa.b(this.b, false);
            }
        }
        View i2 = aa.i(this.b);
        i2.setTranslationX(0.0f);
        i2.setTranslationY(0.0f);
    }
}
