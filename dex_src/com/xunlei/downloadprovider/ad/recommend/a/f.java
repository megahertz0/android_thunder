package com.xunlei.downloadprovider.ad.recommend.a;

import android.content.Context;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.recommend.a.i.a;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RecommendAdExecutorFactory.java
public final class f {

    // compiled from: RecommendAdExecutorFactory.java
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

    public static a a(Context context, int i, String str, AD_TYPE ad_type, a aVar, String str2) {
        switch (AnonymousClass_1.a[ad_type.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return new g(context, i, str, aVar, str2);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new c(context, i, str, aVar, str2);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return new p(context, i, str, aVar, str2);
            default:
                return new g(context, i, str, aVar, str2);
        }
    }
}
