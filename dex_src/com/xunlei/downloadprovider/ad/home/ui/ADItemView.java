package com.xunlei.downloadprovider.ad.home.ui;

import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public interface ADItemView {

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AD_LAYOUT_TYPE.values().length];
            try {
                a[AD_LAYOUT_TYPE.IMAGE_TYPE_VIEW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AD_LAYOUT_TYPE.SHORT_VOD_TYPE_VIEW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AD_LAYOUT_TYPE.LONG_VOD_TYPE_VIEW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[AD_LAYOUT_TYPE.PLAY_VOD_TYPE_VIEW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum AD_LAYOUT_TYPE {
        IMAGE_TYPE_VIEW,
        SHORT_VOD_TYPE_VIEW,
        LONG_VOD_TYPE_VIEW,
        PLAY_VOD_TYPE_VIEW;

        static {
            IMAGE_TYPE_VIEW = new com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE("IMAGE_TYPE_VIEW", 0);
            SHORT_VOD_TYPE_VIEW = new com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE("SHORT_VOD_TYPE_VIEW", 1);
            LONG_VOD_TYPE_VIEW = new com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE("LONG_VOD_TYPE_VIEW", 2);
            PLAY_VOD_TYPE_VIEW = new com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE("PLAY_VOD_TYPE_VIEW", 3);
            a = new com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE[]{IMAGE_TYPE_VIEW, SHORT_VOD_TYPE_VIEW, LONG_VOD_TYPE_VIEW, PLAY_VOD_TYPE_VIEW};
        }

        public final String getLoadGDTADId() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return "5060315428025381";
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return "5040308996507332";
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return "3070210150056511";
                default:
                    return "9080605987491174";
            }
        }

        public final String getLoadBaiduADId() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return "2842127";
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return "2557559";
                default:
                    return "2799614";
            }
        }

        public final int getParseFlag() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return 1;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    return SimpleLog.LOG_LEVEL_DEBUG;
                default:
                    return 0;
            }
        }

        public final String getLoadBaiduFailHubName() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return "adv_homeflow_pic_baidu_fail";
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return "adv_homeflow_video_baidu_fail";
                default:
                    return "adv_homeflow_pic_baidu_fail";
            }
        }

        public final String getLoadInmobiFailHubName() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return "adv_homeflow_pic_inmobi_fail";
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return "adv_homeflow_video_inmobi_fail";
                default:
                    return "adv_homeflow_video_inmobi_fail";
            }
        }

        public final String getLoadSSPFailHubTypeName() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return "pic";
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return WeiXinShareContent.TYPE_VIDEO;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    return "bigvideo";
                default:
                    return "pic";
            }
        }

        public final String getLoadGDTFailHubName() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return "adv_homeflow_pic_tx_fail";
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return "adv_homeflow_video_tx_fail";
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return "adv_homeflow_movie_tx_fail";
                default:
                    return "adv_homeflow_video_tx_fail";
            }
        }

        public final long getLoadInmobiADId() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return 1470405294651L;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return 1467635628351L;
                default:
                    return 1468432592662L;
            }
        }
    }

    String a(String str);

    void a(a aVar);

    AD_LAYOUT_TYPE getADType();

    String getViewPositionKey();
}
