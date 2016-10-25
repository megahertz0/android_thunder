package uk.co.senab.photoview.a;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.xunlei.tdlive.im.ChatMessage;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: CupcakeGestureDetector.java
public class a implements e {
    protected f a;
    float b;
    float c;
    final float d;
    final float e;
    private VelocityTracker f;
    private boolean g;

    public final void a(f fVar) {
        this.a = fVar;
    }

    public a(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.e = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.d = (float) viewConfiguration.getScaledTouchSlop();
    }

    float a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    float b(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    public boolean a() {
        return false;
    }

    public boolean c(MotionEvent motionEvent) {
        boolean z = false;
        float yVelocity;
        switch (motionEvent.getAction()) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.f = VelocityTracker.obtain();
                if (this.f != null) {
                    this.f.addMovement(motionEvent);
                }
                this.b = a(motionEvent);
                this.c = b(motionEvent);
                this.g = false;
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                if (this.g && this.f != null) {
                    this.b = a(motionEvent);
                    this.c = b(motionEvent);
                    this.f.addMovement(motionEvent);
                    this.f.computeCurrentVelocity(ChatMessage.FLAG_SYS_NOTIFY);
                    float xVelocity = this.f.getXVelocity();
                    yVelocity = this.f.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.e) {
                        this.a.a(this.b, this.c, -xVelocity, -yVelocity);
                    }
                }
                if (this.f != null) {
                    this.f.recycle();
                    this.f = null;
                }
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                yVelocity = a(motionEvent);
                float b = b(motionEvent);
                float f = yVelocity - this.b;
                float f2 = b - this.c;
                if (!this.g) {
                    if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.d)) {
                        z = true;
                    }
                    this.g = z;
                }
                if (this.g) {
                    this.a.a(f, f2);
                    this.b = yVelocity;
                    this.c = b;
                    if (this.f != null) {
                        this.f.addMovement(motionEvent);
                    }
                }
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.f != null) {
                    this.f.recycle();
                    this.f = null;
                }
                break;
        }
        return true;
    }
}
