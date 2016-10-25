package com.ta.utdid2.aid;

import android.content.Context;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.android.utils.TimeUtils;
import com.umeng.a;
import com.ut.device.AidCallback;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.sdk.IHost;

public class AidManager {
    private static final int NUM_DAY_OUT_OF_DATE = 1;
    private static final String TAG;
    private static AidManager sAidManager;
    private Context mContext;

    static {
        sAidManager = null;
        TAG = AidManager.class.getName();
    }

    public static synchronized AidManager getInstance(Context context) {
        AidManager aidManager;
        synchronized (AidManager.class) {
            if (sAidManager == null) {
                sAidManager = new AidManager(context);
            }
            aidManager = sAidManager;
        }
        return aidManager;
    }

    private AidManager(Context context) {
        this.mContext = context;
    }

    public void requestAid(String str, String str2, String str3, AidCallback aidCallback) {
        boolean z = false;
        if (aidCallback != null) {
            if (this.mContext == null || StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
                boolean z2;
                StringBuilder append = new StringBuilder("mContext:").append(this.mContext).append("; callback:").append(aidCallback).append("; has appName:");
                if (StringUtils.isEmpty(str)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                StringBuilder append2 = append.append(z2).append("; has token:");
                if (!StringUtils.isEmpty(str2)) {
                    z = true;
                }
                append2.append(z);
                aidCallback.onAidEventChanged(IHost.HOST_NOFITY_PAGE_DESELECTED, a.d);
                return;
            }
            String aidValueFromSP = AidStorageController.getAidValueFromSP(this.mContext, str, str2);
            if (!StringUtils.isEmpty(aidValueFromSP) && TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, str, str2), NUM_DAY_OUT_OF_DATE)) {
                aidCallback.onAidEventChanged(IHost.HOST_NOFITY_PAGE_SELECTED, aidValueFromSP);
            } else if (NetworkUtils.isConnected(this.mContext)) {
                AidRequester.getInstance(this.mContext).postRestAsync(str, str2, str3, aidValueFromSP, aidCallback);
            } else {
                aidCallback.onAidEventChanged(JsInterface.MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX, aidValueFromSP);
            }
        }
    }

    public String getValue(String str, String str2, String str3) {
        boolean z = false;
        if (this.mContext == null || StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            StringBuilder append = new StringBuilder("mContext:").append(this.mContext).append("; has appName:").append(!StringUtils.isEmpty(str)).append("; has token:");
            if (!StringUtils.isEmpty(str2)) {
                z = true;
            }
            append.append(z);
            return a.d;
        }
        String aidValueFromSP = AidStorageController.getAidValueFromSP(this.mContext, str, str2);
        return ((StringUtils.isEmpty(aidValueFromSP) || !TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, str, str2), NUM_DAY_OUT_OF_DATE)) && NetworkUtils.isConnected(this.mContext)) ? genAidValue(str, str2, str3) : aidValueFromSP;
    }

    private synchronized String genAidValue(String str, String str2, String str3) {
        String str4;
        if (this.mContext == null) {
            str4 = a.d;
        } else {
            str4 = a.d;
            if (NetworkUtils.isConnected(this.mContext)) {
                str4 = AidRequester.getInstance(this.mContext).postRest(str, str2, str3, AidStorageController.getAidValueFromSP(this.mContext, str, str2));
            }
            AidStorageController.setAidValueToSP(this.mContext, str, str4, str2);
        }
        return str4;
    }
}
