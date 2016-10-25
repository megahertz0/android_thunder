package com.xunlei.downloadprovider.search.ui.website;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchWebsiteHelper.java
final class u implements a {
    final /* synthetic */ String a;
    final /* synthetic */ List b;
    final /* synthetic */ s c;

    u(s sVar, String str, List list) {
        this.c = sVar;
        this.a = str;
        this.b = list;
    }

    public final void onErrorResponse(w wVar) {
        int i = MqttConnectOptions.MQTT_VERSION_3_1_1;
        if (!(this.a == null || this.a.equals(BuildConfig.VERSION_NAME))) {
            List a = r.a(this.c.a, this.a);
            if (a.size() != 0) {
                this.b.add(r.a(this.c.a, (int) SimpleLog.LOG_LEVEL_DEBUG));
                if (a.size() <= 4) {
                    i = a.size();
                }
                for (int i2 = 0; i2 < i; i2++) {
                    this.b.add(a.get(i2));
                }
            }
        }
        Collection b = r.b(this.c.a);
        if (b.size() != 0) {
            this.b.add(r.a(this.c.a, (int) SimpleLog.LOG_LEVEL_ERROR));
            this.b.addAll(b);
        }
        this.c.a.a.obtainMessage(0, this.b).sendToTarget();
    }
}
