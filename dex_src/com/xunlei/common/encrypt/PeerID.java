package com.xunlei.common.encrypt;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.math.BigInteger;
import java.util.UUID;

public class PeerID {
    public static String getPeerId(Context context) {
        int length;
        String str = "00000000";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Utility.NETWORK_WIFI);
        if (wifiManager != null) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (!(connectionInfo == null || connectionInfo.getMacAddress() == null)) {
                str = str.replaceAll(":", BuildConfig.VERSION_NAME);
            }
        }
        if (new BigInteger(str, 16).longValue() == 0) {
            String str2;
            TelephonyManager telephonyManager;
            Object simSerialNumber;
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string.compareToIgnoreCase("9774d56d682e549c") == 0) {
                str2 = BuildConfig.VERSION_NAME;
            } else {
                str2 = string;
            }
            CharSequence deviceId;
            if (TextUtils.isEmpty(deviceId)) {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                }
            }
            if (TextUtils.isEmpty(simSerialNumber)) {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    simSerialNumber = telephonyManager.getSimSerialNumber();
                }
            }
            if (!TextUtils.isEmpty(simSerialNumber)) {
                UUID nameUUIDFromBytes = UUID.nameUUIDFromBytes(simSerialNumber.getBytes());
                Long valueOf = Long.valueOf(nameUUIDFromBytes.getLeastSignificantBits());
                Long valueOf2 = Long.valueOf(nameUUIDFromBytes.getMostSignificantBits());
                str = Integer.toHexString(valueOf.hashCode());
                length = str.length();
                str2 = str;
                while (length < 8) {
                    length++;
                    str2 = new StringBuilder("0").append(str2).toString();
                }
                string = Integer.toHexString(valueOf2.hashCode());
                for (int length2 = string.length(); length2 < 8; length2++) {
                    string = new StringBuilder("0").append(string).toString();
                }
                str = string + str2;
            }
        }
        for (length = str.length(); length < 16; length++) {
            str = str + "0";
        }
        return str.toUpperCase();
    }
}
