package com.tencent.open.b;

import com.tencent.open.utils.Global;
import com.tencent.open.utils.OpenConfig;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ProGuard
public class e {
    public static int a(String str) {
        int i = OpenConfig.getInstance(Global.getContext(), str).getInt("Common_BusinessReportFrequency");
        return i == 0 ? R.styleable.AppCompatTheme_buttonStyle : i;
    }

    public static int a() {
        int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_HttpRetryCount");
        return i == 0 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : i;
    }
}
