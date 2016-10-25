package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import android.content.Context;
import com.qq.e.ads.nativ.NativeAD;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: GDTLoader.java
final class j extends b {
    public j(Context context, int i) {
        super(context, i);
    }

    public final void a(a aVar) {
        a.a("adv_downloadplay_finish_tx_request", h.a(this.b));
        new NativeAD(this.a, "1104872693", h.b(this.b), new k(this, aVar)).loadAD(MqttConnectOptions.MQTT_VERSION_3_1);
    }
}
