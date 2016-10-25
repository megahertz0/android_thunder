package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.c.a;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LoadBaiduExecutor.java
public final class h extends a implements a, d.a {
    private d e;

    public h(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        super(context, aVar);
    }

    public final void b() {
        super.b();
        String str = "adv_homeflow_baidu_request";
        ThunderReporter.a.a(g.a("android_advertise", str, str).a(AgooConstants.MESSAGE_TYPE, d().getLoadSSPFailHubTypeName(), MqttConnectOptions.MQTT_VERSION_3_1));
        g.a(this.c).a(SimpleLog.LOG_LEVEL_DEBUG, d()).a(this, c());
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
        if (this.e == null || !this.e.e) {
            e();
            if (!(this.e == null || this.e.e)) {
                this.e.d = true;
            }
            ThunderReporter.a.a("adv_homeflow_baidu_fail", d().getLoadSSPFailHubTypeName(), str);
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
