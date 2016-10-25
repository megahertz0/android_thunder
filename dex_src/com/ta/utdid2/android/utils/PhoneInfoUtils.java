package com.ta.utdid2.android.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Random;

public class PhoneInfoUtils {
    public static final String getUniqueID() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        Object bytes = IntUtils.getBytes(currentTimeMillis);
        Object bytes2 = IntUtils.getBytes(nanoTime);
        Object bytes3 = IntUtils.getBytes(nextInt);
        Object bytes4 = IntUtils.getBytes(nextInt2);
        Object obj = new Object[16];
        System.arraycopy(bytes, 0, obj, 0, XZBDevice.DOWNLOAD_LIST_ALL);
        System.arraycopy(bytes2, 0, obj, XZBDevice.DOWNLOAD_LIST_ALL, XZBDevice.DOWNLOAD_LIST_ALL);
        System.arraycopy(bytes3, 0, obj, XZBDevice.Wait, XZBDevice.DOWNLOAD_LIST_ALL);
        System.arraycopy(bytes4, 0, obj, XZBDevice.Fail, XZBDevice.DOWNLOAD_LIST_ALL);
        return Base64.encodeToString(obj, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public static String getImei(Context context) {
        String deviceId;
        if (context != null) {
            TelephonyManager telephonyManager;
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Exception e) {
            }
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
                return StringUtils.isEmpty(deviceId) ? getUniqueID() : deviceId;
            }
        }
        deviceId = null;
        if (StringUtils.isEmpty(deviceId)) {
        }
    }

    public static String getImsi(Context context) {
        String subscriberId;
        if (context != null) {
            TelephonyManager telephonyManager;
            try {
                telephonyManager = (TelephonyManager) context.getSystemService("phone");
            } catch (Exception e) {
            }
            if (telephonyManager != null) {
                subscriberId = telephonyManager.getSubscriberId();
                return StringUtils.isEmpty(subscriberId) ? getUniqueID() : subscriberId;
            }
        }
        subscriberId = null;
        if (StringUtils.isEmpty(subscriberId)) {
        }
    }
}
