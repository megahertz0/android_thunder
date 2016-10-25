package com.xunlei.downloadprovider.ad.home.ui;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UIHelper.java
public class r {
    private static final String a;
    private static r b;

    static {
        a = r.class.getSimpleName();
        b = null;
    }

    private r() {
    }

    public static r a() {
        if (b == null) {
            b = new r();
        }
        return b;
    }

    public static String b() {
        return BrothersApplication.a().getString(R.string.choiceness_ad_download_status_undownload);
    }

    public static String a(a aVar) {
        Object obj = 1;
        new StringBuilder("gdtAD.appStatus: ").append(aVar.f());
        switch (aVar.f()) {
            case SimpleLog.LOG_LEVEL_TRACE:
                obj = MqttConnectOptions.MQTT_VERSION_3_1_1;
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                obj = SimpleLog.LOG_LEVEL_DEBUG;
                break;
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                obj = MqttConnectOptions.MQTT_VERSION_3_1;
                break;
        }
        BrothersApplication a = BrothersApplication.a();
        String b = b();
        switch (obj) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return a.getString(R.string.choiceness_ad_download_status_undownload);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return a.getString(R.string.choiceness_ad_download_status_downloading);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return a.getString(R.string.choiceness_ad_download_status_uninstall);
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return a.getString(R.string.choiceness_ad_download_status_intalled);
            default:
                return b;
        }
    }
}
