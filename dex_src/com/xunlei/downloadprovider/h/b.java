package com.xunlei.downloadprovider.h;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.xunlei.downloadprovider.a.c.a;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.List;

// compiled from: LocalFileOpenHelper.java
public final class b {
    public static int a(String str, String str2, boolean z) {
        return a(BrothersApplication.a.getApplicationContext(), str, str2, z);
    }

    public static int a(Context context, String str, String str2, boolean z) {
        if (new File(str).exists()) {
            EFileCategoryType a = XLFileTypeUtil.a(str);
            String c = c.c(str);
            if (a.equals(EFileCategoryType.E_SOFTWARE_CATEGORY)) {
                try {
                    a a2 = com.xunlei.downloadprovider.a.c.a(context, str);
                    if (com.xunlei.downloadprovider.a.c.a(context, a2) == 4) {
                        com.xunlei.downloadprovider.a.c.c(context, a2.c());
                    } else {
                        com.xunlei.downloadprovider.a.c.d(context, str);
                    }
                    if (a2 == null) {
                        return -1;
                    }
                    com.xunlei.downloadprovider.notification.a.a(context).a(j.a(context, "clearApkNotification", a2.c()));
                    StatReporter.reportOpenWithHandleFile(0, c, str2);
                    return 0;
                } catch (Exception e) {
                    XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                    return -1;
                }
            } else if (a.equals(EFileCategoryType.E_VIDEO_CATEGORY) && XLFileTypeUtil.e(str)) {
                StatReporter.reportOpenWithHandleFile(0, c.c(str), str2);
                if ("download_list".equals(str2)) {
                    VodUtil.a();
                    VodUtil.a(context, str, "download_detail", VodSourceType.download_detail);
                } else {
                    VodUtil.a();
                    VodUtil.a(context, str);
                }
                return 0;
            } else {
                try {
                    String string = context.getSharedPreferences("default_app_setting", 0).getString(c, "null");
                    List a3 = a.a(context).a(str);
                    if (a3 != null && a3.size() == 1) {
                        a(context, (ResolveInfo) a3.get(0), str);
                        StatReporter.reportOpenWithHandleFile(0, c, str2);
                        return 0;
                    } else if (string.equals(context.getString(2131231808))) {
                        VodUtil.a();
                        VodUtil.a(context, str);
                        StatReporter.reportOpenWithHandleFile(0, c, str2);
                        return 0;
                    } else if (a3 == null || a3.size() <= 1) {
                        XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                        StatReporter.reportOpenWithHandleFile(1, c, str2);
                        return -1;
                    } else {
                        a(context, null, str);
                        StatReporter.reportOpenWithHandleFile(0, c, str2);
                        return 0;
                    }
                } catch (IllegalArgumentException e2) {
                    XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                    StatReporter.reportOpenWithHandleFile(1, c, str2);
                    return XZBDevice.DOWNLOAD_LIST_RECYCLE;
                } catch (ActivityNotFoundException e3) {
                    XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                    StatReporter.reportOpenWithHandleFile(1, c, str2);
                    return 1;
                }
            }
        } else if (!z) {
            return -1;
        } else {
            XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u8be5\u6587\u4ef6\u4e0d\u5b58\u5728");
            return -1;
        }
    }

    public static int a(Context context, String str, String str2) {
        if (new File(str).exists()) {
            EFileCategoryType a = XLFileTypeUtil.a(str);
            String c = c.c(str);
            if (a.equals(EFileCategoryType.E_SOFTWARE_CATEGORY)) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                    a a2 = com.xunlei.downloadprovider.a.c.a(context, str);
                    if (a2 == null) {
                        return -1;
                    }
                    com.xunlei.downloadprovider.notification.a.a(context).a(j.a(context, "clearApkNotification", a2.c()));
                    StatReporter.reportOpenWithHandleFile(0, c, str2);
                    return 0;
                } catch (Exception e) {
                    XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                    return -1;
                }
            }
            try {
                String string = context.getSharedPreferences("default_app_setting", 0).getString(c, "null");
                List a3 = a.a(context).a(str);
                if (a3 != null && a3.size() == 1) {
                    a(context, (ResolveInfo) a3.get(0), str);
                    StatReporter.reportOpenWithHandleFile(0, c, str2);
                    return 0;
                } else if (string.equals(context.getString(2131231808))) {
                    VodUtil.a();
                    VodUtil.a(context, str);
                    StatReporter.reportOpenWithHandleFile(0, c, str2);
                    return 0;
                } else if (a3 == null || a3.size() <= 1) {
                    XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                    StatReporter.reportOpenWithHandleFile(1, c, str2);
                    return -1;
                } else {
                    a(context, null, str);
                    StatReporter.reportOpenWithHandleFile(0, c, str2);
                    return 0;
                }
            } catch (IllegalArgumentException e2) {
                XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                StatReporter.reportOpenWithHandleFile(1, c, str2);
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            } catch (ActivityNotFoundException e3) {
                XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                StatReporter.reportOpenWithHandleFile(1, c, str2);
                return 1;
            }
        }
        XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u8be5\u6587\u4ef6\u4e0d\u5b58\u5728");
        return -1;
    }

    private static void a(Context context, ResolveInfo resolveInfo, String str) {
        Intent a = a.a(context).a(str, resolveInfo);
        if (a != null) {
            a.addFlags(268435456);
            context.startActivity(a);
        }
    }
}
