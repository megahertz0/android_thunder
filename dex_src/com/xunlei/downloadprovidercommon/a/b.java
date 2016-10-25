package com.xunlei.downloadprovidercommon.a;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.analytics.HubbleAgent;
import com.xunlei.analytics.config.AnalyticsReportConfigurationBuilder;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: NewHubbleReport.java
final class b {
    private static String a;
    private static String b;

    static {
        a = "17";
        b = "ed35b80ab6de3944a96466be405de2fc";
    }

    public static void init(Context context) {
        HubbleAgent.init(context, a, b, com.xunlei.downloadprovider.a.b.d(context));
        HubbleAgent.setReportConfiguration(new AnalyticsReportConfigurationBuilder().reportCheckInterval(180000).batchUploadCount(SpdyProtocol.PUBKEY_SEQ_OPEN).reportRetryCount(MqttConnectOptions.MQTT_VERSION_3_1).deleteExpirationDayTime(604800000).uploadInWifiOnly(true).build());
        HubbleAgent.setReportEventServerMode(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public static void reportEvent(c cVar) {
        if (cVar != null && !TextUtils.isEmpty(cVar.a)) {
            if (cVar.b()) {
                HubbleAgent.onEvent(cVar.a, cVar.a());
            } else {
                HubbleAgent.onEvent(cVar.a);
            }
        }
    }

    public static void onResume(Context context) {
        HubbleAgent.onResume(context);
    }

    public static void onPause(Context context) {
        HubbleAgent.onPause(context);
    }
}
