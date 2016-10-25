package com.xunlei.xllib.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.umeng.socialize.bean.StatusCode;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class b$a {
    public static String a(Context context) {
        String str = "\u672a\u77e5";
        switch (b(context)) {
            case StatusCode.ST_CODE_SDK_NO_OAUTH:
                return "Wi-Fi";
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
                return "\u65e0";
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return "\u672a\u77e5";
            case SimpleLog.LOG_LEVEL_TRACE:
                return "2G";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "3G";
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return "4G";
            default:
                return str;
        }
    }

    private static int b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            int type;
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                type = activeNetworkInfo.getType();
                if (type == 1) {
                    type = -101;
                } else {
                    if (type == 0) {
                        type = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                    }
                    type = 0;
                }
                switch (type) {
                    case StatusCode.ST_CODE_SDK_NO_OAUTH:
                        return -101;
                    case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
                        return -1;
                    case SimpleLog.LOG_LEVEL_TRACE:
                    case SimpleLog.LOG_LEVEL_DEBUG:
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    case SimpleLog.LOG_LEVEL_OFF:
                    case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                        return 1;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                    case SimpleLog.LOG_LEVEL_ERROR:
                    case SimpleLog.LOG_LEVEL_FATAL:
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                    case R.styleable.Toolbar_titleMargins:
                    case R.styleable.Toolbar_titleMarginEnd:
                    case R.styleable.Toolbar_titleMarginTop:
                        return SimpleLog.LOG_LEVEL_DEBUG;
                    case R.styleable.Toolbar_titleMarginStart:
                        return MqttConnectOptions.MQTT_VERSION_3_1;
                    default:
                        return 0;
                }
            }
            type = -1;
            switch (type) {
                case StatusCode.ST_CODE_SDK_NO_OAUTH:
                    return -101;
                case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
                    return -1;
                case SimpleLog.LOG_LEVEL_TRACE:
                case SimpleLog.LOG_LEVEL_DEBUG:
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                case SimpleLog.LOG_LEVEL_OFF:
                case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                    return 1;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                case SimpleLog.LOG_LEVEL_ERROR:
                case SimpleLog.LOG_LEVEL_FATAL:
                case SpdyProtocol.PUBKEY_SEQ_ADASH:
                case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                case SpdyProtocol.PUBKEY_SEQ_OPEN:
                case R.styleable.Toolbar_titleMargins:
                case R.styleable.Toolbar_titleMarginEnd:
                case R.styleable.Toolbar_titleMarginTop:
                    return SimpleLog.LOG_LEVEL_DEBUG;
                case R.styleable.Toolbar_titleMarginStart:
                    return MqttConnectOptions.MQTT_VERSION_3_1;
                default:
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
