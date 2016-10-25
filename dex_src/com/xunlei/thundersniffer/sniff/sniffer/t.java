package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.thundersniffer.sniff.sniffer.SniffingDetailPageTask.LocalCacheOperation.CacheResult;
import com.xunlei.thundersniffer.sniff.sniffer.SniffingDetailPageTask.LocalCacheOperation.OnFinishGetLocalCacheCallback;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

final class t implements OnFinishGetLocalCacheCallback {
    final /* synthetic */ SniffingDetailPageTask a;

    t(SniffingDetailPageTask sniffingDetailPageTask) {
        this.a = sniffingDetailPageTask;
    }

    public final void onFinishGetLocalCacheCallback(String str, CacheResult cacheResult) {
        new StringBuilder("cacheResult --> ").append(cacheResult);
        if (!(cacheResult == null || TextUtils.isEmpty(cacheResult.realUrl) || !cacheResult.isValid)) {
            this.a.m.b = cacheResult.realUrl;
            this.a.m.d = cacheResult.pageTitle;
        }
        if (cacheResult == null || cacheResult.content == null || cacheResult.content.isEmpty()) {
            this.a.r.b(SniffingDetailPageTask.p, "GetLocalCache End");
            if (cacheResult != null && cacheResult.isValid && cacheResult.flags == 404) {
                this.a.B = MqttConnectOptions.MQTT_VERSION_3_1_1;
                this.a.a(SniffingTask.h);
                return;
            }
            this.a.B = SimpleLog.LOG_LEVEL_DEBUG;
            this.a.a(SniffingTask.h);
            return;
        }
        String c = aj.c(str);
        if (c == null) {
            c = SniffingDetailPageTask.a(str);
        }
        this.a.r.b(SniffingDetailPageTask.p, new StringBuilder("GetLocalCache End: Hit ").append(cacheResult.content.size()).append(" at ").append(c).toString());
        this.a.e = cacheResult.content;
        this.a.B = MqttConnectOptions.MQTT_VERSION_3_1_1;
        this.a.a(SniffingTask.h);
    }
}
