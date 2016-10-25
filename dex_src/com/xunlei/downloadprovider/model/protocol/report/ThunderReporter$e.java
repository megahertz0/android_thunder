package com.xunlei.downloadprovider.model.protocol.report;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ThunderReporter$e {
    private static String a;
    private static String b;
    private static String c;

    static {
        a = "android_kandan";
        b = "kandan_show";
        c = "kandan_click";
    }

    public static void a(String str, String str2) {
        String str3 = b;
        g a = g.a(a, str3, str3);
        a.a("from", str2, MqttConnectOptions.MQTT_VERSION_3_1);
        a.a(AgooConstants.MESSAGE_ID, str);
        a(a);
    }

    public static void a(String str, String str2, String str3, String str4) {
        String str5 = c;
        g a = g.a(a, str5, str5);
        a.a(AgooConstants.MESSAGE_TYPE, str2, MqttConnectOptions.MQTT_VERSION_3_1);
        a.a("clickid", str4, MqttConnectOptions.MQTT_VERSION_3_1);
        a.a("movieid", str3);
        a.a(AgooConstants.MESSAGE_ID, str);
        a(a);
    }

    private static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }
}
