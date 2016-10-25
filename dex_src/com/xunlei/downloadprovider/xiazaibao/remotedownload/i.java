package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RemoteDownloadContainerFragment.java
final class i implements am$a {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public final void a() {
        this.a.a.b();
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        new StringBuilder("onDeleteTask result = ").append(i).append(" taskInfoList = ").append(list);
        this.a.a.g.b().f.a(MqttConnectOptions.MQTT_VERSION_3_1, i == 0);
    }

    public final void b() {
        this.a.a.g.b().f.a(MqttConnectOptions.MQTT_VERSION_3_1, false);
    }
}
