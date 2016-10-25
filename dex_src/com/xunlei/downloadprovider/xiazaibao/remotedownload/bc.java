package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: XZBTaskInfoDetailFragment.java
final class bc implements am$a {
    final /* synthetic */ bb a;

    bc(bb bbVar) {
        this.a = bbVar;
    }

    public final void a() {
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        this.a.a.b();
        if (XZBTaskInfoDetailFragment.d(this.a.a) != null) {
            XZBTaskInfoDetailFragment.d(this.a.a).a(MqttConnectOptions.MQTT_VERSION_3_1, i == 0);
        }
    }

    public final void b() {
        if (XZBTaskInfoDetailFragment.d(this.a.a) != null) {
            XZBTaskInfoDetailFragment.d(this.a.a).a(MqttConnectOptions.MQTT_VERSION_3_1, false);
        }
    }
}
