package com.xunlei.downloadprovider.web;

import android.os.Message;
import com.xunlei.downloadprovider.b.c.a.a;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PublicReportClient.java
final class u implements a {
    final /* synthetic */ w$a a;
    final /* synthetic */ t b;

    u(t tVar) {
        this.b = tVar;
        this.a = null;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        this.b.setStatus(i == 0 ? MqttConnectOptions.MQTT_VERSION_3_1 : MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.a == null && this.b.mListener != null) {
            Message message = new Message();
            message.what = 453674;
            message.arg1 = i;
            this.b.mListener.sendMessage(message);
        }
    }
}
