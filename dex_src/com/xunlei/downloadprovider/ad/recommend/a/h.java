package com.xunlei.downloadprovider.ad.recommend.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a.a;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.ad.common.e.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RecommendAdLoaderFactory.java
public final class h {

    // compiled from: RecommendAdLoaderFactory.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AD_TYPE.values().length];
            try {
                a[AD_TYPE.SOURCE_GDT_FLAG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AD_TYPE.SOURCE_BAIDU_FLAG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AD_TYPE.SOURCE_SSP_FLAG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static c a(AD_TYPE ad_type) {
        switch (AnonymousClass_1.a[ad_type.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return new b(BrothersApplication.a(), "1080211154624038", 2);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new a(BrothersApplication.a(), "2834696");
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return new com.xunlei.downloadprovider.ad.common.d.a.b(3);
            default:
                return null;
        }
    }
}
