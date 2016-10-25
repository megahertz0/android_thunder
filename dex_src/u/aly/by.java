package u.aly;

import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TProtocolUtil.java
public final class by {
    private static int a;

    static {
        a = Integer.MAX_VALUE;
    }

    public static void a(bu buVar, byte b) throws ac {
        a(buVar, b, a);
    }

    private static void a(bu buVar, byte b, int i) throws ac {
        int i2 = 0;
        if (i <= 0) {
            throw new ac("Maximum skip depth exceeded");
        }
        switch (b) {
            case SimpleLog.LOG_LEVEL_DEBUG:
                buVar.j();
            case MqttConnectOptions.MQTT_VERSION_3_1:
                buVar.k();
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                buVar.o();
            case SimpleLog.LOG_LEVEL_FATAL:
                buVar.l();
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                buVar.m();
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                buVar.n();
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                buVar.q();
            case R.styleable.Toolbar_titleMargins:
                buVar.d();
                while (true) {
                    br f = buVar.f();
                    if (f.b != null) {
                        a(buVar, f.b, i - 1);
                    } else {
                        buVar.e();
                        return;
                    }
                }
            case R.styleable.Toolbar_titleMarginStart:
                bt g = buVar.g();
                while (i2 < g.c) {
                    a(buVar, g.a, i - 1);
                    a(buVar, g.b, i - 1);
                    i2++;
                }
            case R.styleable.Toolbar_titleMarginEnd:
                bz i3 = buVar.i();
                while (i2 < i3.b) {
                    a(buVar, i3.a, i - 1);
                    i2++;
                }
            case R.styleable.Toolbar_titleMarginTop:
                bs h = buVar.h();
                while (i2 < h.b) {
                    a(buVar, h.a, i - 1);
                    i2++;
                }
            default:
                break;
        }
    }
}
