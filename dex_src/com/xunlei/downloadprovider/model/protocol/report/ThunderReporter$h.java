package com.xunlei.downloadprovider.model.protocol.report;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ThunderReporter$h {
    public static void a(String str) {
        g a = g.a("android_renewTip", "renewTip_click", "renewTip_click").a("from", str, MqttConnectOptions.MQTT_VERSION_3_1);
        ThunderReporter.a(a);
        ThunderReporter.a(a, true);
    }

    public static void a(String str, String str2) {
        g a = g.a("android_renewTip", "renewTip_click", "renewTip_click").a("from", str, MqttConnectOptions.MQTT_VERSION_3_1).a("clickid", str2, MqttConnectOptions.MQTT_VERSION_3_1);
        ThunderReporter.a(a);
        ThunderReporter.a(a, true);
    }
}
