package com.ta.utdid2.aid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.Base64Helper;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.SharedPreferenceHelper;
import com.ta.utdid2.android.utils.StringUtils;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AidStorageController {
    private static final String KEY_PREF_AID_GEN_TIME = "rKrMJgyAEbVtSQGi";
    private static final String KEY_PREF_AID_VALUE = "EvQwnbilKezpOJey";
    private static final String PREF_AID = "OfJbkLdFbPOMbGyP";
    private static final String TAG;
    private static Map<String, Long> sAidGenTimeMapInSP;
    private static Map<String, String> sAidMapInSP;

    static {
        TAG = AidStorageController.class.getName();
        sAidMapInSP = new ConcurrentHashMap();
        sAidGenTimeMapInSP = new ConcurrentHashMap();
    }

    public static void setAidValueToSP(Context context, String str, String str2, String str3) {
        if (context != null) {
            String encodedAppName = getEncodedAppName(str, str3);
            long currentTimeMillis = System.currentTimeMillis();
            sAidMapInSP.put(encodedAppName, str2);
            sAidGenTimeMapInSP.put(encodedAppName, Long.valueOf(currentTimeMillis));
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_AID, 0);
            if (VERSION.SDK_INT >= 9) {
                SharedPreferenceHelper.apply(sharedPreferences.edit().putString(KEY_PREF_AID_VALUE.concat(encodedAppName), str2));
                SharedPreferenceHelper.apply(sharedPreferences.edit().putLong(KEY_PREF_AID_GEN_TIME.concat(encodedAppName), currentTimeMillis));
                return;
            }
            sharedPreferences.edit().putString(KEY_PREF_AID_VALUE.concat(encodedAppName), str2).commit();
            sharedPreferences.edit().putLong(KEY_PREF_AID_GEN_TIME.concat(encodedAppName), currentTimeMillis).commit();
        }
    }

    public static String getAidValueFromSP(Context context, String str, String str2) {
        if (context == null) {
            return a.d;
        }
        String encodedAppName = getEncodedAppName(str, str2);
        String str3 = (String) sAidMapInSP.get(encodedAppName);
        boolean z = DebugUtils.DBG;
        if (!StringUtils.isEmpty(str3)) {
            return str3;
        }
        str3 = context.getSharedPreferences(PREF_AID, 0).getString(KEY_PREF_AID_VALUE.concat(encodedAppName), a.d);
        sAidMapInSP.put(encodedAppName, str3);
        return str3;
    }

    public static long getAidGenTimeFromSP(Context context, String str, String str2) {
        if (context == null) {
            return 0;
        }
        String encodedAppName = getEncodedAppName(str, str2);
        Long valueOf = Long.valueOf(sAidGenTimeMapInSP.containsKey(encodedAppName) ? ((Long) sAidGenTimeMapInSP.get(encodedAppName)).longValue() : 0);
        if (DebugUtils.DBG) {
            new StringBuilder("cache AIDGenTime:").append(valueOf);
        }
        if (valueOf.longValue() == 0) {
            valueOf = Long.valueOf(context.getSharedPreferences(PREF_AID, 0).getLong(KEY_PREF_AID_GEN_TIME.concat(encodedAppName), 0));
            sAidGenTimeMapInSP.put(encodedAppName, valueOf);
        }
        return valueOf.longValue();
    }

    private static String getEncodedAppName(String str, String str2) {
        String encodeToString;
        if (VERSION.SDK_INT >= 8) {
            encodeToString = Base64Helper.encodeToString(str.concat(str2).getBytes(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        } else {
            encodeToString = Base64.encodeToString(str.concat(str2).getBytes(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
        boolean z = DebugUtils.DBG;
        return encodeToString;
    }
}
