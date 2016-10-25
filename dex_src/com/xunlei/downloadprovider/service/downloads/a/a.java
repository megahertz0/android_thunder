package com.xunlei.downloadprovider.service.downloads.a;

import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: DownloadReporter.java
public final class a {
    public static void a(String str, String str2, String str3) {
        g a = g.a("android_download", "dl_create", "dl_create").a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a(SocialConstants.PARAM_APP_ID, str2).a("sdkid", str3).a("net_type", com.xunlei.xllib.a.b.a.a(BrothersApplication.a()));
        ThunderReporter.a(a);
        ThunderReporter.a(a, true);
    }

    public static void a(String str) {
        g a = g.a("android_download", "dl_complete", "dl_complete");
        String str2 = "from";
        if (str == null) {
            str = com.umeng.a.d;
        }
        a.a(str2, str);
        ThunderReporter.a(a, true);
    }
}
