package com.xunlei.downloadprovider.ad.common.d.a;

import com.android.volley.r.b;
import com.xunlei.common.accelerator.utils.ErrorCodeUtils;
import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.ad.common.d.a.a.g;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SSPADLoader.java
final class c implements b<String> {
    final /* synthetic */ a a;
    final /* synthetic */ b b;

    c(b bVar, a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        a bVar;
        f fVar = null;
        String str = (String) obj;
        a aVar = new a();
        switch (this.b.a) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                bVar = new b();
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                bVar = new e();
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                bVar = new c();
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                bVar = new g();
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                bVar = new d();
                break;
            default:
                bVar = null;
                break;
        }
        if (bVar != null) {
            fVar = bVar.a(str);
        }
        if (fVar == null) {
            this.a.a(ErrorCodeUtils.XLA_PAY_SUCCESS, "ad no fill");
        } else if (fVar.a != 0) {
            this.a.a(fVar.a, fVar.a);
        } else {
            this.a.a(fVar.b);
        }
    }
}
