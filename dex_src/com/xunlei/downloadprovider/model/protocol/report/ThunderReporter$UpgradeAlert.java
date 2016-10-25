package com.xunlei.downloadprovider.model.protocol.report;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ThunderReporter$UpgradeAlert {
    private static String a;
    private static String b;
    private static String c;

    public enum From {
        REC_ALERT("rec_alert"),
        REC_BAR("rec_bar"),
        CONFIG_UPDATE("config_update"),
        FORCE_UPDATE("force_update");
        private final String a;

        static {
            String str = "rec_alert";
            REC_ALERT = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From("REC_ALERT", 0, "rec_alert");
            str = "rec_bar";
            REC_BAR = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From("REC_BAR", 1, "rec_bar");
            str = "config_update";
            CONFIG_UPDATE = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From("CONFIG_UPDATE", 2, "config_update");
            str = "force_update";
            FORCE_UPDATE = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From("FORCE_UPDATE", 3, "force_update");
            b = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From[]{REC_ALERT, REC_BAR, CONFIG_UPDATE, FORCE_UPDATE};
        }

        private From(String str) {
            this.a = str;
        }
    }

    static {
        a = "android_alert";
        b = "update_show";
        c = "update_click";
    }

    public static void a(From from) {
        String str = b;
        g a = g.a(a, str, str);
        a.a("from", From.a(from), MqttConnectOptions.MQTT_VERSION_3_1);
        a(a);
    }

    public static void b(From from) {
        String str = c;
        g a = g.a(a, str, str);
        a.a("from", From.a(from), MqttConnectOptions.MQTT_VERSION_3_1);
        a(a);
    }

    private static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }
}
