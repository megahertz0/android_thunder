package com.xunlei.downloadprovider.download.taskDetail.a;

import com.xunlei.downloadprovider.ad.recommend.a.b.b;
import com.xunlei.downloadprovider.frame.advertisement.b.d.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskDetailXunLeiAdModel.java
final class j extends a {
    final /* synthetic */ b a;
    final /* synthetic */ i b;

    j(i iVar, b bVar) {
        this.b = iVar;
        this.a = bVar;
    }

    protected final void a(List<com.xunlei.downloadprovider.frame.advertisement.a.a> list) {
        List arrayList = new ArrayList(1);
        if (!(list == null || list.size() <= 0 || ((com.xunlei.downloadprovider.frame.advertisement.a.a) list.get(0)) == null)) {
            arrayList.add(new k((com.xunlei.downloadprovider.frame.advertisement.a.a) list.get(0)));
        }
        if (arrayList.isEmpty()) {
            this.a.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.a);
        } else {
            this.a.a(arrayList);
        }
    }

    protected final void a(int i, String str) {
        String valueOf = String.valueOf(i);
        String a = ThunderReporter.a.a();
        new StringBuilder("reportTaskDetailImageAdXunLeiFail errorcode: ").append(valueOf).append(" net_type: ").append(a);
        String str2 = "adv_download_detail_xl_fail";
        ThunderReporter.a.a(g.a("android_advertise", str2, str2).a("errorcode", valueOf, MqttConnectOptions.MQTT_VERSION_3_1).a("net_type", a, MqttConnectOptions.MQTT_VERSION_3_1));
        this.a.a(new com.xunlei.downloadprovider.ad.recommend.a.b.a(i, str));
    }
}
