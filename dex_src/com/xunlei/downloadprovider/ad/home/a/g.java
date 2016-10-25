package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.a.a;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.ad.common.d.a.b;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LoadADFactory.java
public final class g {
    private static g b;
    private final int a;
    private Context c;

    private g(Context context) {
        this.a = 3;
        this.c = context;
    }

    public static g a(Context context) {
        if (b == null) {
            b = new g(context);
        }
        return b;
    }

    public final c a(int i, AD_LAYOUT_TYPE ad_layout_type) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return new b(ad_layout_type.getParseFlag());
            case SimpleLog.LOG_LEVEL_TRACE:
                return new com.xunlei.downloadprovider.ad.common.e.b(this.c, ad_layout_type.getLoadGDTADId(), 3);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new a(this.c, ad_layout_type.getLoadBaiduADId());
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return new com.xunlei.downloadprovider.ad.common.c.a(ad_layout_type.getLoadInmobiADId());
            default:
                return null;
        }
    }
}
