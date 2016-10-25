package com.xunlei.downloadprovider.ad.recommend.c;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.xllib.a.b;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RecommendAdReporter.java
public final class a {
    public static void a(int i, String str, String str2) {
        new StringBuilder("reportRecommendAdStartLoadAd attribute1: adv_downloadin_request tagId: ").append(i).append(" adType: ").append(str).append(" type: ").append(str2);
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_advertise", "adv_downloadin_request").b("tabid", a(i)).b("ad_type", str).b(AgooConstants.MESSAGE_TYPE, str2));
    }

    public static void a(int i, String str, String str2, String str3) {
        new StringBuilder("reportRecommendAdLoadAdFail attribute1: adv_downloadin_fail tagId: ").append(i).append(" adType: ").append(str).append(" errCode: ").append(str2).append(" type: ").append(str3);
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_advertise", "adv_downloadin_fail").b("tabid", a(i)).b("ad_type", str).b("errorcode", str2).b(AgooConstants.MESSAGE_TYPE, str3));
    }

    public static String a() {
        String c = b.c(BrothersApplication.a());
        return (c == null || !c.equals("null")) ? c : "0";
    }

    public static String a(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return "total";
            case SimpleLog.LOG_LEVEL_TRACE:
                return "downloading";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "finish";
            default:
                return "total";
        }
    }
}
