package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.a.a;
import java.text.DecimalFormat;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: DownloadListADUtils.java
public final class h {
    public static boolean[] a;

    static {
        a = new boolean[3];
    }

    public static String a(int i) {
        String str = "total";
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return "total";
            case SimpleLog.LOG_LEVEL_TRACE:
                return "downloading";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "finish";
            default:
                return str;
        }
    }

    public static String b(int i) {
        String str = "5020511018089707";
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return "5020511018089707";
            case SimpleLog.LOG_LEVEL_TRACE:
                return "1050912110223719";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "7030911110927870";
            default:
                return str;
        }
    }

    public static String c(int i) {
        String str = "2749821";
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return "2749821";
            case SimpleLog.LOG_LEVEL_TRACE:
                return "2749822";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "2749824";
            default:
                return str;
        }
    }

    public static boolean d(int i) {
        if (r.c().e != null) {
            a a = r.c().e.a();
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    return a.e;
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a.f;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return a.g;
            }
        }
        return true;
    }

    public static String a(String str) {
        long longValue = Long.valueOf(str).longValue();
        if (longValue < 10000) {
            return "0";
        }
        return longValue < 100000000 ? (longValue / 10000) + BrothersApplication.a().getString(R.string.choiceness_ad_download_count_min_unit) : new DecimalFormat("#.0").format((((double) longValue) * 1.0d) / 1.0E8d) + BrothersApplication.a().getString(R.string.choiceness_ad_download_count_max_unit);
    }

    public static String a(long j) {
        if (j < 10000) {
            return "0";
        }
        return j < 100000000 ? (j / 10000) + BrothersApplication.a().getString(R.string.choiceness_ad_download_count_min_unit) : new DecimalFormat("#.0").format((((double) j) * 1.0d) / 1.0E8d) + BrothersApplication.a().getString(R.string.choiceness_ad_download_count_max_unit);
    }

    public static String a(m$a com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a) {
        return com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a.b() + "," + com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a.d();
    }
}
