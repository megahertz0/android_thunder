package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskDetailImageAdView.java
final class c implements OnClickListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        b.a;
        new StringBuilder("onclick mImageAdInfo != null: ").append(this.a.l != null);
        if (this.a.l != null) {
            if (this.a.b instanceof BaseActivity) {
                this.a.l.onClick((BaseActivity) this.a.b, this.a);
            }
            String d = this.a.l.d();
            String p = this.a.l.p();
            String a = this.a.l.a();
            String j = this.a.l.j();
            new StringBuilder("reportTaskDetailImageAdClick ad_type: ").append(d).append(" advid: ").append(p).append(" material: ").append(a).append(" background: ").append(j);
            String str = "adv_download_detail_click";
            a.a(g.a("android_advertise", str, str).a("ad_type", d, MqttConnectOptions.MQTT_VERSION_3_1).a("advid", p, MqttConnectOptions.MQTT_VERSION_3_1).a("material", a, MqttConnectOptions.MQTT_VERSION_3_1).a("background", j, MqttConnectOptions.MQTT_VERSION_3_1));
        }
    }
}
