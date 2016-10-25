package com.xunlei.common.accelerator.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class XLNetworkUtils {
    public static final int MOBILE_2G = 2;
    public static final int MOBILE_3G = 3;
    public static final int MOBILE_4G = 4;
    public static final int NO_NETWORK = 0;
    public static final int UNKNOWN_NETWORK = -1;
    public static final int WIFI = 5;

    public static int getSpecificNetworkType(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo() != null) {
                return getNetworkClass(connectivityManager.getActiveNetworkInfo().getSubtype(), connectivityManager.getActiveNetworkInfo().getTypeName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NO_NETWORK;
    }

    public static int getNetworkClass(int i, String str) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case MOBILE_2G:
            case MOBILE_4G:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                return MOBILE_2G;
            case MOBILE_3G:
            case WIFI:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
            case R.styleable.Toolbar_titleMargins:
            case R.styleable.Toolbar_titleMarginEnd:
            case R.styleable.Toolbar_titleMarginTop:
                return MOBILE_3G;
            case R.styleable.Toolbar_titleMarginStart:
                return MOBILE_4G;
            default:
                return str.equals("WIFI") ? WIFI : UNKNOWN_NETWORK;
        }
    }

    public static boolean isWIFIActive(Context context) {
        return getSpecificNetworkType(context) == 5;
    }
}
