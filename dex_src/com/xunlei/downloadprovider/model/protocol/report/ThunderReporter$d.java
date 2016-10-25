package com.xunlei.downloadprovider.model.protocol.report;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ThunderReporter$d {
    public static void a(HashMap<String, String> hashMap) {
        g a = g.a("android_find", "find_show", "find_show");
        for (String str : hashMap.keySet()) {
            a.a(str, (String) hashMap.get(str), MqttConnectOptions.MQTT_VERSION_3_1);
        }
        ThunderReporter.a(a);
        ThunderReporter.a(a, true);
    }

    public static void a(String str, String str2) {
        g a = g.a("android_find", "find_click", "find_click").a(AgooConstants.MESSAGE_FLAG, str2, MqttConnectOptions.MQTT_VERSION_3_1).a("clickid", str, MqttConnectOptions.MQTT_VERSION_3_1);
        ThunderReporter.a(a);
        ThunderReporter.a(a, true);
    }
}
