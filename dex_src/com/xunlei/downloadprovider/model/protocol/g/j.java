package com.xunlei.downloadprovider.model.protocol.g;

import android.os.Message;
import com.xunlei.downloadprovider.b.b.a.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: XunleiScanCodeBox.java
public final class j implements a {
    final /* synthetic */ e a;

    public j(e eVar) {
        this.a = eVar;
    }

    public final void a(int i, Object obj) {
        this.a.setStatus(MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.a.mListener != null && i != 0) {
            Message message = new Message();
            message.what = 0;
            message.arg1 = i;
            this.a.mListener.sendMessage(message);
        }
    }
}
