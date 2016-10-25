package uk.co.senab.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@TargetApi(5)
// compiled from: EclairGestureDetector.java
public class b extends a {
    private int f;
    private int g;

    public b(Context context) {
        super(context);
        this.f = -1;
        this.g = 0;
    }

    final float a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.g);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    final float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.g);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean c(MotionEvent motionEvent) {
        int i = 0;
        switch (motionEvent.getAction() & 255) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.f = motionEvent.getPointerId(0);
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.f = -1;
                break;
            case SimpleLog.LOG_LEVEL_FATAL:
                int action = motionEvent.getAction();
                int i2 = VERSION.SDK_INT;
                action = (action & 65280) >> 8;
                if (motionEvent.getPointerId(action) == this.f) {
                    if (action == 0) {
                        action = 1;
                    } else {
                        action = 0;
                    }
                    this.f = motionEvent.getPointerId(action);
                    this.b = motionEvent.getX(action);
                    this.c = motionEvent.getY(action);
                }
                break;
        }
        if (this.f != -1) {
            i = this.f;
        }
        this.g = motionEvent.findPointerIndex(i);
        return super.c(motionEvent);
    }
}
