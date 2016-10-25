package com.xunlei.downloadprovider.search.ui.website;

import com.android.volley.toolbox.t;
import com.xunlei.downloadprovider.search.a.b;
import com.xunlei.downloadprovider.search.b.c;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchWebsiteHelper.java
public final class s extends Thread {
    final /* synthetic */ r a;

    public s(r rVar) {
        this.a = rVar;
    }

    public final void run() {
        int i = MqttConnectOptions.MQTT_VERSION_3_1_1;
        if (this.a.a != null) {
            int size;
            List arrayList = new ArrayList();
            List a = r.a(this.a);
            if (a.size() != 0) {
                size = a.size() > 4 ? 4 : a.size();
                arrayList.add(r.a(this.a, 0));
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(a.get(i2));
                }
            }
            String string = b.a().a.getString("hot_website_json_key", BuildConfig.VERSION_NAME);
            if (!this.a.c || string.equals(BuildConfig.VERSION_NAME)) {
                c a2 = c.a();
                t tVar = new t("http://m.sjzhushou.com/xlconfig/hotsites", new t(this, arrayList), new u(this, string, arrayList));
                tVar.setShouldCache(false);
                a2.a(tVar);
                return;
            }
            List a3 = r.a(this.a, string);
            if (a3.size() != 0) {
                arrayList.add(r.a(this.a, (int) SimpleLog.LOG_LEVEL_DEBUG));
                if (a3.size() <= 4) {
                    i = a3.size();
                }
                for (size = 0; size < i; size++) {
                    arrayList.add(a3.get(size));
                }
            }
            Collection b = r.b(this.a);
            if (b.size() != 0) {
                arrayList.add(r.a(this.a, (int) SimpleLog.LOG_LEVEL_ERROR));
                arrayList.addAll(b);
            }
            this.a.a.obtainMessage(0, arrayList).sendToTarget();
        }
    }
}
