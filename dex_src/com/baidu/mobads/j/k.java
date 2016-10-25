package com.baidu.mobads.j;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.baidu.mobads.a.a;
import com.baidu.mobads.interfaces.utils.IBase64;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils.ApkInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.openad.e.d;
import com.taobao.accs.data.Message;
import com.tencent.bugly.Bugly;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.util.List;

public class k implements IXAdPackageUtils {
    public boolean isInstalled(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            return applicationInfo != null && str.equals(applicationInfo.packageName);
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public boolean isSystemPackage(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & 1) != 0;
    }

    @TargetApi(3)
    public void openApp(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            launchIntentForPackage.addFlags(268435456);
            context.startActivity(launchIntentForPackage);
        } catch (Exception e) {
        }
    }

    public Intent getInstallIntent(String str) {
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(268435456);
                intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
                return intent;
            } catch (Exception e) {
                return intent;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), Message.FLAG_REQ_BIT1).versionCode;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

    public ApkInfo getLocalApkFileInfo(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            return packageArchiveInfo != null ? new ApkInfo(context, packageArchiveInfo) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isForeground(Context context, String str) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo.importance == 100;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendAPOIsSuccess(Context context, boolean z, int i, String str, String str2) {
        d m = m.a().m();
        IXAdURIUitls i2 = m.a().i();
        IXAdSystemUtils n = m.a().n();
        IBase64 e = m.a().e();
        String encodeUrl = i2.encodeUrl(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new StringBuilder("aposuccess=").append(z).toString());
        if (!z) {
            stringBuilder.append(new StringBuilder("&failtime=").append(i).toString());
        }
        stringBuilder.append(new StringBuilder("&sn=").append(n.getEncodedSN(context)).toString());
        stringBuilder.append(new StringBuilder("&mac=").append(e.encode(n.getMacAddress(context))).toString());
        stringBuilder.append(new StringBuilder("&cuid=").append(n.getCUID(context)).toString());
        stringBuilder.append(new StringBuilder("&pack=").append(context.getPackageName()).toString());
        stringBuilder.append(new StringBuilder("&v=").append(new StringBuilder("android_").append(a.c).append("_4.1.30").toString()).toString());
        stringBuilder.append(new StringBuilder("&targetscheme=").append(encodeUrl).toString());
        stringBuilder.append(new StringBuilder("&pk=").append(str2).toString());
        try {
            d dVar = new d(i2.addParameters(m.vdUrl(stringBuilder.toString(), 369), null), com.umeng.a.d);
            dVar.e = 1;
            new com.baidu.mobads.openad.e.a().a(dVar);
        } catch (Exception e2) {
            m.a().f().d("XAdPackageUtils", e2.getMessage());
        }
    }

    public boolean sendAPOInfo(Context context, String str, String str2, int i, int i2) {
        String str3;
        boolean z = false;
        PackageManager packageManager = context.getPackageManager();
        IXAdSystemUtils n = m.a().n();
        IXAdURIUitls i3 = m.a().i();
        d m = m.a().m();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        String encodeUrl = i3.encodeUrl(str);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT);
        encodeUrl = new StringBuilder("&sn=").append(n.getEncodedSN(context)).append("&fb_act=").append(i2).append("&pack=").append(context.getPackageName()).append("&v=").append(new StringBuilder("android_").append(a.c).append("_4.1.30").toString()).append("&targetscheme=").append(encodeUrl).append("&pk=").append(str2).toString();
        String str4 = "&open=";
        if (queryIntentActivities.size() > 0) {
            str3 = (str4 + "true") + "&n=" + queryIntentActivities.size();
            int i4 = 0;
            while (i4 < queryIntentActivities.size()) {
                String str5;
                ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivities.get(i4);
                if (i4 == 0) {
                    str5 = str3 + "&p=" + resolveInfo.activityInfo.packageName;
                } else {
                    str5 = str3 + MiPushClient.ACCEPT_TIME_SEPARATOR + resolveInfo.activityInfo.packageName;
                }
                i4++;
                str3 = str5;
            }
            z = true;
        } else {
            str3 = str4 + Bugly.SDK_IS_DEV;
        }
        try {
            d dVar = new d(i3.addParameters(m.vdUrl(encodeUrl + str3, i), null), com.umeng.a.d);
            dVar.e = 1;
            new com.baidu.mobads.openad.e.a().a(dVar);
        } catch (Exception e) {
            m.a().f().d("XAdPackageUtils", e.getMessage());
        }
        return z;
    }

    public void sendDialerIsSuccess(Context context, boolean z, int i, String str) {
        d m = m.a().m();
        IXAdURIUitls i2 = m.a().i();
        IXAdSystemUtils n = m.a().n();
        IBase64 e = m.a().e();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new StringBuilder("callstate=").append(z).toString());
        if (!z) {
            stringBuilder.append(new StringBuilder("&duration=").append(i).toString());
        }
        stringBuilder.append(new StringBuilder("&sn=").append(n.getEncodedSN(context)).toString());
        stringBuilder.append(new StringBuilder("&mac=").append(e.encode(n.getMacAddress(context))).toString());
        stringBuilder.append(new StringBuilder("&bdr=").append(VERSION.SDK_INT).toString());
        stringBuilder.append(new StringBuilder("&cuid=").append(n.getCUID(context)).toString());
        stringBuilder.append(new StringBuilder("&pack=").append(context.getPackageName()).toString());
        stringBuilder.append(new StringBuilder("&v=").append(new StringBuilder("android_").append(a.c).append("_4.1.30").toString()).toString());
        stringBuilder.append(new StringBuilder("&pk=").append(str).toString());
        try {
            d dVar = new d(i2.addParameters(m.vdUrl(stringBuilder.toString(), 372), null), com.umeng.a.d);
            dVar.e = 1;
            new com.baidu.mobads.openad.e.a().a(dVar);
        } catch (Exception e2) {
            m.a().f().d("XAdPackageUtils", e2.getMessage());
        }
    }
}
