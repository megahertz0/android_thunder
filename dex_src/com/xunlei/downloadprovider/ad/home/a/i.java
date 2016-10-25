package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.common.d.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LoadGDTExecutor.java
public final class i extends a implements a, com.xunlei.downloadprovider.ad.common.e.a {
    private d e;

    public i(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        super(context, aVar);
    }

    public final void b() {
        super.b();
        String str = "adv_homeflow_tx_request";
        ThunderReporter.a.a(g.a("android_advertise", str, str).a(AgooConstants.MESSAGE_TYPE, d().getLoadSSPFailHubTypeName(), MqttConnectOptions.MQTT_VERSION_3_1));
        g.a(this.c).a(1, d()).a(this, c());
        this.e = new d(this.b);
        this.e.a(this);
        this.e.a();
    }

    public final void a(com.xunlei.downloadprovider.ad.common.e.d dVar) {
        c.a(this.c).c.a(this.d, dVar);
        c.a(this.c).a();
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
        ThunderReporter.a.a("adv_homeflow_tx_fail", d().getLoadSSPFailHubTypeName(), String.valueOf(i));
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
