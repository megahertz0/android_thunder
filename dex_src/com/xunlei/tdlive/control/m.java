package com.xunlei.tdlive.control;

import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: MagnetView.java
class m extends Handler {
    final /* synthetic */ MagnetView a;
    private Matrix b;
    private float c;

    m(MagnetView magnetView) {
        this.a = magnetView;
        this.b = new Matrix();
        this.c = 0.0f;
    }

    public void handleMessage(Message message) {
        float f = 0.0f;
        super.handleMessage(message);
        this.b.set(this.a.getImageMatrix());
        MagnetView magnetView;
        Matrix matrix;
        float f2;
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.c = 0.0f;
                magnetView = this.a;
                matrix = this.b;
                if (this.a.a) {
                    f2 = this.c;
                } else {
                    f2 = 0.0f;
                }
                if (!this.a.a) {
                    f = this.c;
                }
                magnetView.a(matrix, f2, f);
                this.a.p.sendEmptyMessage(SimpleLog.LOG_LEVEL_DEBUG);
            case SimpleLog.LOG_LEVEL_DEBUG:
                magnetView = this.a;
                matrix = this.b;
                f2 = this.a.a ? this.c : 0.0f;
                if (!this.a.a) {
                    f = this.c;
                }
                magnetView.a(matrix, f2, f);
                if (this.c < ((float) this.a.getDegree())) {
                    this.a.p.sendEmptyMessage(SimpleLog.LOG_LEVEL_DEBUG);
                } else {
                    this.a.l = true;
                }
                this.c += 1.0f;
                this.c += 1.0f;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.a.a(this.b, this.a.a ? this.c : 0.0f, this.a.a ? 0.0f : this.c);
                if (this.c > 0.0f) {
                    this.a.p.sendEmptyMessage(MqttConnectOptions.MQTT_VERSION_3_1);
                } else {
                    this.a.l = true;
                    if (!(this.a.m || this.a.d == null)) {
                        this.a.d.a();
                    }
                }
                this.c -= 1.0f;
                this.c -= 1.0f;
            case SimpleLog.LOG_LEVEL_FATAL:
                this.c = (float) this.a.getDegree();
                magnetView = this.a;
                matrix = this.b;
                if (this.a.a) {
                    f2 = this.c;
                } else {
                    f2 = 0.0f;
                }
                if (!this.a.a) {
                    f = this.c;
                }
                magnetView.a(matrix, f2, f);
                this.a.p.sendEmptyMessage(MqttConnectOptions.MQTT_VERSION_3_1);
            default:
                break;
        }
    }
}
