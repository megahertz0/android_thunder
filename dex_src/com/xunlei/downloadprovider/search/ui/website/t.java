package com.xunlei.downloadprovider.search.ui.website;

import android.content.SharedPreferences.Editor;
import com.android.volley.r.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchWebsiteHelper.java
final class t implements b<String> {
    final /* synthetic */ List a;
    final /* synthetic */ s b;

    t(s sVar, List list) {
        this.b = sVar;
        this.a = list;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        int i = MqttConnectOptions.MQTT_VERSION_3_1_1;
        String str = (String) obj;
        if (str != null && !str.equals(BuildConfig.VERSION_NAME)) {
            this.b.a.c = true;
            Editor edit = com.xunlei.downloadprovider.search.a.b.a().a.edit();
            edit.putString("hot_website_json_key", str);
            edit.apply();
            List a = r.a(this.b.a, str);
            if (a.size() != 0) {
                this.a.add(r.a(this.b.a, (int) SimpleLog.LOG_LEVEL_DEBUG));
                if (a.size() <= 4) {
                    i = a.size();
                }
                for (int i2 = 0; i2 < i; i2++) {
                    this.a.add(a.get(i2));
                }
            }
            Collection b = r.b(this.b.a);
            if (b.size() != 0) {
                this.a.add(r.a(this.b.a, (int) SimpleLog.LOG_LEVEL_ERROR));
                this.a.addAll(b);
            }
            this.b.a.a.obtainMessage(0, this.a).sendToTarget();
        }
    }
}
