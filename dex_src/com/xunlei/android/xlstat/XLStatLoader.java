package com.xunlei.android.xlstat;

import android.content.Context;
import com.xunlei.android.xlstat.param.XLStatKey;

public class XLStatLoader {
    public native int init(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i, XLStatKey xLStatKey);

    public native int trackEvent(int i, String str, String str2, String str3, int i2, int i3, int i4, int i5, String str4);

    public native int unInit(int i);

    public XLStatLoader(Context context) {
        XLLog.a("XAndroidVipLoader", "---------- XLLoader()");
        a.a(context, "xl_stat");
        a.a(context, "xl_stat_iface");
    }
}
