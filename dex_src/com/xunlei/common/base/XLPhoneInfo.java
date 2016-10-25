package com.xunlei.common.base;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;

public final class XLPhoneInfo {
    private static XLPhoneInfo mPhoneInfo;
    private String IMSI;
    private TelephonyManager telephonyManager;

    static {
        mPhoneInfo = null;
    }

    private XLPhoneInfo(Context context) {
        this.telephonyManager = null;
        this.IMSI = null;
        this.telephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    public static void init(Context context) {
        if (mPhoneInfo == null) {
            mPhoneInfo = new XLPhoneInfo(context);
        }
    }

    public static final XLPhoneInfo getInstance() {
        return mPhoneInfo;
    }

    public final String getNativePhoneNumber() {
        return this.telephonyManager.getLine1Number();
    }

    public final String getProvidersName() {
        this.IMSI = this.telephonyManager.getSubscriberId();
        if (this.IMSI == null) {
            return null;
        }
        if (this.IMSI.startsWith("46000") || this.IMSI.startsWith("46002")) {
            return "\u4e2d\u56fd\u79fb\u52a8";
        }
        if (this.IMSI.startsWith("46001")) {
            return "\u4e2d\u56fd\u8054\u901a";
        }
        return this.IMSI.startsWith("46003") ? "\u4e2d\u56fd\u7535\u4fe1" : null;
    }

    public final String getSIMIMEI() {
        this.IMSI = this.telephonyManager.getSubscriberId();
        return this.IMSI;
    }

    public static String getRawDeviceId(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android-");
        try {
            String macAddress = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getMacAddress();
            if (macAddress == null || macAddress.equals(a.d)) {
                stringBuilder.append(UtilityImpl.NET_TYPE_WIFI);
            } else {
                stringBuilder.append(macAddress.replace(":", a.d));
            }
            stringBuilder.append(SocializeConstants.OP_DIVIDER_MINUS);
            macAddress = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (macAddress == null || macAddress.equals(a.d)) {
                stringBuilder.append(Constants.KEY_IMEI);
            } else {
                stringBuilder.append(macAddress);
            }
            stringBuilder.append(SocializeConstants.OP_DIVIDER_MINUS);
            try {
                Class forName = Class.forName("android.os.SystemProperties");
                macAddress = (String) forName.getMethod("get", new Class[]{String.class}).invoke(forName, new Object[]{"ro.serialno"});
            } catch (Exception e) {
                e.printStackTrace();
                macAddress = null;
            }
            if (macAddress != null) {
                if (!macAddress.equals(a.d)) {
                    stringBuilder.append(macAddress);
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(IXAdRequestInfo.SN);
        } catch (Exception e2) {
            stringBuilder.append("global-phone-identify");
        }
        return stringBuilder.toString();
    }
}
