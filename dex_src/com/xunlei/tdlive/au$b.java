package com.xunlei.tdlive;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.d;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: LivePlayerDialog.java
private class au$b implements OnGestureListener, OnTouchListener {
    private PointF a;
    final /* synthetic */ au b;
    private int c;
    private boolean d;
    private GestureDetector e;

    public au$b(au auVar) {
        this.b = auVar;
        this.a = new PointF();
        this.c = 0;
        this.d = false;
        this.e = new GestureDetector(auVar.getContext(), this);
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.a = new PointF(motionEvent.getRawX(), motionEvent.getRawY());
        this.c = 0;
        this.d = false;
        au.w(this.b);
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        au.x(this.b);
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!au.y(this.b) && motionEvent.getPointerCount() <= 1 && motionEvent2.getPointerCount() <= 1) {
            float rawX = motionEvent2.getRawX() - motionEvent.getRawX();
            float rawY = motionEvent2.getRawY() - motionEvent.getRawY();
            float abs = Math.abs(rawX);
            rawY = Math.abs(rawY);
            if (this.c == 0) {
                this.c = abs > rawY ? 1 : SimpleLog.LOG_LEVEL_DEBUG;
            } else if (this.c == 1) {
                if (rawX < 0.0f && au.z(this.b)) {
                    au.A(this.b).setTranslationX(rawX);
                }
                if (rawX > 0.0f && !au.z(this.b)) {
                    au.A(this.b).setTranslationX(rawX);
                }
            }
        }
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3 = 9999.0f;
        if (!au.y(this.b) && motionEvent.getPointerCount() <= 1) {
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
                if (!au.y(this.b)) {
                    a(this.c, motionEvent.getRawX() - this.a.x, motionEvent.getRawY() - this.a.y);
                    return true;
                }
        }
        return onTouchEvent;
    }

    private void a(int i, float f, float f2) {
        float f3 = ((float) d.a(this.b.getContext()).x) / 4.0f;
        XLog.d("LivePlayerDialog", new StringBuilder("performAnim ").append(i).append(" ").append(f).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(f2).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(f3).toString());
        if (i == 1) {
            if (f > f3) {
                au.e(this.b, true);
            } else if (f < (-f3)) {
                au.e(this.b, false);
            }
        }
        au.A(this.b).setTranslationX(0.0f);
        au.A(this.b).setTranslationY(0.0f);
    }
}
