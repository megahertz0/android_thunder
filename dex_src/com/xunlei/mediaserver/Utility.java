package com.xunlei.mediaserver;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xunlei.xllib.R;
import java.io.File;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class Utility {
    public static final String NETWORK_2G_NET = "2g_net";
    public static final String NETWORK_2G_WAP = "2g_wap";
    public static final String NETWORK_3G = "3g";
    public static final String NETWORK_4G = "4g";
    public static final int NETWORK_CLASS_2G = 1;
    public static final int NETWORK_CLASS_3G = 2;
    public static final int NETWORK_CLASS_4G = 3;
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final String NETWORK_NONE = "none";
    public static final String NETWORK_OTHER = "other";
    public static final String NETWORK_WIFI = "wifi";

    public static boolean ensureDir(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return false;
        }
        try {
            return file.mkdirs();
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getSDCardDir(Context context) {
        return ("mounted".equals(Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory().getPath() : context.getCacheDir().getPath()) + File.separator;
    }

    public static String getIMEI(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        return deviceId == null ? "000000000000000" : deviceId;
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return NETWORK_NONE;
        }
        int networkClass = getNetworkClass(activeNetworkInfo.getSubtype());
        if (activeNetworkInfo.getType() == 1) {
            return NETWORK_WIFI;
        }
        if (networkClass == 1) {
            try {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                    return NETWORK_2G_WAP;
                }
                Cursor query = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                if (query != null) {
                    query.moveToFirst();
                    Object string = query.getString(query.getColumnIndex("user"));
                    if (!TextUtils.isEmpty(string) && string.startsWith("ctwap")) {
                        query.close();
                        return NETWORK_2G_WAP;
                    }
                }
                return NETWORK_2G_NET;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (networkClass == 2) {
            return NETWORK_3G;
        } else {
            return networkClass == 3 ? NETWORK_4G : NETWORK_OTHER;
        }
    }

    private static int getNetworkClass(int i) {
        switch (i) {
            case NETWORK_CLASS_2G:
            case NETWORK_CLASS_3G:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                return NETWORK_CLASS_2G;
            case NETWORK_CLASS_4G:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
            case R.styleable.Toolbar_titleMargins:
            case R.styleable.Toolbar_titleMarginEnd:
            case R.styleable.Toolbar_titleMarginTop:
                return NETWORK_CLASS_3G;
            case R.styleable.Toolbar_titleMarginStart:
                return NETWORK_CLASS_4G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }
}
