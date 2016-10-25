package com.xunlei.downloadprovider.i.a;

import com.xunlei.downloadprovider.b.c.g;
import com.xunlei.downloadprovider.b.c.g.a;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Update.java
final class h implements a {
    final /* synthetic */ c a;

    h(c cVar) {
        this.a = cVar;
    }

    public final void a(int i, Map<String, List<String>> map, g gVar) {
        if (200 != i) {
            if (!(c.l(this.a) || this.a.b == null || c.e(this.a).isFinishing())) {
                this.a.b.dismiss();
            }
            c.v(this.a).obtainMessage(20008, MqttConnectOptions.MQTT_VERSION_3_1, 0).sendToTarget();
            gVar.cancel();
            return;
        }
        if (map != null) {
            List list = null;
            for (String str : map.keySet()) {
                if (str != null) {
                    List list2;
                    new StringBuilder("[").append(str).append("]:").append(map.get(str));
                    if (str.equalsIgnoreCase("content-length")) {
                        list2 = (List) map.get(str);
                    } else {
                        list2 = list;
                    }
                    list = list2;
                }
            }
            if (list != null && list.size() > 0) {
                if (!(c.l(this.a) || this.a.b == null || c.e(this.a).isFinishing())) {
                    c.a(this.a, (long) Integer.parseInt((String) list.get(0)));
                    this.a.b.a(c.w(this.a));
                }
                c.x(this.a);
            }
        }
        new StringBuilder("mApkFileLength = ").append(c.w(this.a));
    }
}
