package org.apache.thrift.protocol;

import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.f;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class i {
    private static int a;

    static {
        a = Integer.MAX_VALUE;
    }

    public static void a(f fVar, byte b) {
        a(fVar, b, a);
    }

    public static void a(f fVar, byte b, int i) {
        int i2 = 0;
        if (i <= 0) {
            throw new f("Maximum skip depth exceeded");
        }
        switch (b) {
            case SimpleLog.LOG_LEVEL_DEBUG:
                fVar.q();
            case MqttConnectOptions.MQTT_VERSION_3_1:
                fVar.r();
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                fVar.v();
            case SimpleLog.LOG_LEVEL_FATAL:
                fVar.s();
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                fVar.t();
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                fVar.u();
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                fVar.x();
            case R.styleable.Toolbar_titleMargins:
                fVar.g();
                while (true) {
                    c i3 = fVar.i();
                    if (i3.b != null) {
                        a(fVar, i3.b, i - 1);
                        fVar.j();
                    } else {
                        fVar.h();
                        return;
                    }
                }
            case R.styleable.Toolbar_titleMarginStart:
                e k = fVar.k();
                while (i2 < k.c) {
                    a(fVar, k.a, i - 1);
                    a(fVar, k.b, i - 1);
                    i2++;
                }
                fVar.l();
            case R.styleable.Toolbar_titleMarginEnd:
                j o = fVar.o();
                while (i2 < o.b) {
                    a(fVar, o.a, i - 1);
                    i2++;
                }
                fVar.p();
            case R.styleable.Toolbar_titleMarginTop:
                d m = fVar.m();
                while (i2 < m.b) {
                    a(fVar, m.a, i - 1);
                    i2++;
                }
                fVar.n();
            default:
                break;
        }
    }
}
