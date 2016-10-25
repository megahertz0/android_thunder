package com.xunlei.downloadprovider.download.tasklist.list.a;

import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m$a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xllib.a.b;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: CommendADShowPVReporter.java
public final class a implements o {
    private String a;
    private String b;
    private int c;

    public a(m$a com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a) {
        this.a = h.a(com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a.b());
        this.b = com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a.d();
        this.c = com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a.b();
    }

    public final int a() {
        return this.c;
    }

    public final boolean b() {
        return !this.b.equals(c.f);
    }

    public final void c() {
        String str = this.a;
        String c = b.c(BrothersApplication.a());
        String str2 = this.b;
        new StringBuilder("reportDownloadADTabShow tabid: ").append(str).append(" netState: ").append(c).append(" position: ").append(str2);
        if (c != null && c.equals("null")) {
            c = "0";
        }
        String str3 = "adv_download_ad_tab_show";
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(g.a("android_advertise", str3, str3).a("tabid", str, MqttConnectOptions.MQTT_VERSION_3_1).a("net_type", c, MqttConnectOptions.MQTT_VERSION_3_1).a("ad_position", str2, MqttConnectOptions.MQTT_VERSION_3_1));
    }
}
