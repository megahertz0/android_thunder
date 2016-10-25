package com.ut.device;

import android.content.Context;
import com.ta.utdid2.aid.AidManager;

public class UTDevice {
    public static String getUtdid(Context context) {
        return com.ta.utdid2.device.UTDevice.getUtdid(context);
    }

    public static String getAid(String str, String str2, Context context) {
        return AidManager.getInstance(context).getValue(str, str2, getUtdid(context));
    }

    public static void getAidAsync(String str, String str2, Context context, AidCallback aidCallback) {
        AidManager.getInstance(context).requestAid(str, str2, getUtdid(context), aidCallback);
    }
}
