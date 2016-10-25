package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskDownloadCardViewHolder.java
final class q implements OnClickListener {
    final /* synthetic */ g a;

    q(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        a.b("dl_finish_download", "finish", g.d(this.a));
        g.a(this.a, MqttConnectOptions.MQTT_VERSION_3_1);
    }
}
