package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LoadInmobiExecutor.java
public final class k extends a implements a, d.a {
    private d e;

    public k(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        super(context, aVar);
    }

    public final void b() {
        super.b();
        String str = "adv_homeflow_inmobi_request";
        ThunderReporter.a.a(g.a("android_advertise", str, str).a(AgooConstants.MESSAGE_TYPE, d().getLoadSSPFailHubTypeName(), MqttConnectOptions.MQTT_VERSION_3_1));
        g.a(this.c).a(MqttConnectOptions.MQTT_VERSION_3_1, d()).a(this, c());
        this.e = new d(this.b);
        this.e.a(this);
        this.e.a();
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (this.e != null && !this.e.e) {
            this.e.d = true;
        } else if (this.e != null && this.e.e) {
            return;
        }
        if (com.xunlei.xllib.b.d.a(list)) {
            e();
            return;
        }
        c.a(this.c).c.a(this.d, (com.xunlei.downloadprovider.ad.common.a) list.get(0));
        c.a(this.c).a();
    }

    public final void a(int i, String str) {
        ThunderReporter.a.a("adv_homeflow_inmobi_fail", d().getLoadSSPFailHubTypeName(), str);
        if (this.e == null || !this.e.e) {
            e();
            if (this.e != null && !this.e.e) {
                this.e.d = true;
            }
        }
    }

    private void e() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public final void a() {
        e();
    }
}
