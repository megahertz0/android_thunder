package com.xunlei.downloadprovider.xiazaibao.view;

import com.xunlei.downloadprovider.xiazaibao.remotedownload.am$a;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: XZBTaskMoreOperationDialog.java
final class n implements am$a {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void a() {
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        if (this.a.a.a != null) {
            this.a.a.a.a(MqttConnectOptions.MQTT_VERSION_3_1, i == 0);
        }
    }

    public final void b() {
        if (this.a.a.a != null) {
            this.a.a.a.a(MqttConnectOptions.MQTT_VERSION_3_1, false);
        }
    }
}
