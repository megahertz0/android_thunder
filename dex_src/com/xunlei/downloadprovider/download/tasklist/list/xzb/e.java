package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.j;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.RemoteDownloadListActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice.UrlData;
import com.xunlei.xiazaibao.shoulei.DownloadPathMsg;
import com.xunlei.xiazaibao.shoulei.DownloadPathType;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.util.ArrayList;

// compiled from: XiaZaiBaoUtil.java
public class e {
    private static final String b;
    private static e c;
    public ArrayList<a> a;

    // compiled from: XiaZaiBaoUtil.java
    public static interface a {
        void a();
    }

    static {
        b = e.class.getSimpleName();
    }

    private e() {
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = new e();
            }
            eVar = c;
        }
        return eVar;
    }

    public final void a(Context context, a aVar, SaveToXZBEntry saveToXZBEntry) {
        a();
        a(true);
        if (!saveToXZBEntry.equals(SaveToXZBEntry.other)) {
            XZBReporter.a(saveToXZBEntry, 0);
        }
        LoginHelper.a();
        boolean z = LoginHelper.c() && XZBShouleiUtil.getInstance().getDefaultDevice() != null;
        if (!z) {
            new b(context, saveToXZBEntry).show();
        }
        UrlData[] urlDataArr = new UrlData[]{new UrlData()};
        urlDataArr[0].downloadUrl = aVar.a;
        urlDataArr[0].refrenceUrl = aVar.a;
        if (!TextUtils.isEmpty(aVar.b)) {
            urlDataArr[0].name = aVar.b;
        }
        XZBShouleiUtil.getInstance().createTask(context, XZBShouleiUtil.getInstance().getDefaultDevice(), urlDataArr, "shoulei_xzb", new f(this, context, z, saveToXZBEntry));
    }

    public final void a(Context context, String str, String str2, long[] jArr, SaveToXZBEntry saveToXZBEntry) {
        a();
        a(true);
        if (!saveToXZBEntry.equals(SaveToXZBEntry.other)) {
            XZBReporter.a(saveToXZBEntry, 0);
        }
        LoginHelper.a();
        boolean z = LoginHelper.c() && XZBShouleiUtil.getInstance().getDefaultDevice() != null;
        if (!z) {
            new b(context, saveToXZBEntry).show();
        }
        XZBShouleiUtil.getInstance().createBtTask(XZBShouleiUtil.getInstance().getDefaultDevice(), str, str2, jArr, "shoulei_xzb", new g(this, context, z, saveToXZBEntry));
    }

    public static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        RemoteDownloadListActivity.a(context);
    }

    public final void a(Context context, SaveToXZBEntry saveToXZBEntry, a... aVarArr) {
        boolean z = true;
        int i = 0;
        a();
        a(true);
        LoginHelper.a();
        if (!LoginHelper.c() || XZBShouleiUtil.getInstance().getDefaultDevice() == null) {
            z = false;
        }
        if (!z) {
            new b(context, saveToXZBEntry).show();
        }
        int length = aVarArr.length;
        if (!saveToXZBEntry.equals(SaveToXZBEntry.other)) {
            XZBReporter.a(saveToXZBEntry, length);
        }
        UrlData[] urlDataArr = new UrlData[length];
        while (i < length) {
            String str = aVarArr[i].a;
            if (!TextUtils.isEmpty(str)) {
                urlDataArr[i] = new UrlData();
                urlDataArr[i].downloadUrl = str;
                urlDataArr[i].refrenceUrl = str;
                if (!TextUtils.isEmpty(aVarArr[i].b)) {
                    urlDataArr[i].name = aVarArr[i].b;
                }
            }
            i++;
        }
        XZBShouleiUtil.getInstance().createTask(context, XZBShouleiUtil.getInstance().getDefaultDevice(), urlDataArr, "shoulei_xzb", new h(this, context, z, saveToXZBEntry, length));
    }

    public static int b() {
        DownloadPathMsg downloadPathTypeMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg();
        if (downloadPathTypeMsg == null || downloadPathTypeMsg.getDownloadPathType() == null || downloadPathTypeMsg.getDownloadPathType().equals(DownloadPathType.MOBILE)) {
            return 0;
        }
        if (downloadPathTypeMsg.getDownloadPathType().equals(DownloadPathType.MOBILE_XZB)) {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        return downloadPathTypeMsg.getDownloadPathType().equals(DownloadPathType.XZB) ? 1 : 0;
    }

    public static void a(String str) {
        BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).edit().putString("last_closed_xzb_expand_tip_download_url", str).commit();
    }

    public static void c() {
        BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).edit().putLong("last_closed_xzb_expand_tip_time", System.currentTimeMillis()).commit();
    }

    public static boolean a(int i) {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0);
        LoginHelper.a();
        boolean z = LoginHelper.c() && XZBShouleiUtil.getInstance().getDefaultDevice() != null;
        StringBuilder stringBuilder = new StringBuilder("can_show_xzb_card");
        if (z) {
            stringBuilder.append("_has_device");
            return sharedPreferences.getBoolean(stringBuilder.toString(), true);
        }
        if (r.c().h.a == null) {
            z = true;
        } else {
            z = false;
        }
        if (z || i < r.c().h.a()) {
            return false;
        }
        if (sharedPreferences.getBoolean("xzb_exhibition_for_the_first_time", true)) {
            sharedPreferences.edit().putBoolean("xzb_exhibition_for_the_first_time", false).commit();
            return true;
        }
        stringBuilder.append("_no_device");
        if (sharedPreferences.getBoolean(stringBuilder.toString(), true)) {
            return true;
        }
        int optInt;
        j jVar = r.c().h;
        if (jVar.a != null) {
            optInt = jVar.a.optInt("xzb_exhibition_type", -1);
        } else {
            optInt = -1;
        }
        if (optInt < 0) {
            optInt = sharedPreferences.getInt("xzb_exhibition_closed", 1);
        } else {
            sharedPreferences.edit().putInt("xzb_exhibition_closed", optInt).commit();
        }
        if (optInt != 1) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("last_closed_xzb_card_time", -1);
        jVar = r.c().h;
        if (jVar.a != null) {
            optInt = jVar.a.optInt("xzb_exhibition_gap", -1);
        } else {
            optInt = -1;
        }
        if (optInt < 0) {
            optInt = sharedPreferences.getInt("xzb_exhibition_gap", XZBDevice.Predownload);
        } else {
            sharedPreferences.edit().putInt("xzb_exhibition_gap", optInt).commit();
        }
        if (currentTimeMillis - j >= ((((long) optInt) * 24) * 3600) * 1000) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        sharedPreferences.edit().putBoolean(stringBuilder.toString(), true).commit();
        return true;
    }

    public static void a(boolean z) {
        int i = 0;
        Editor edit = BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).edit();
        LoginHelper.a();
        if (LoginHelper.c() && XZBShouleiUtil.getInstance().getDefaultDevice() != null) {
            i = 1;
        }
        StringBuilder stringBuilder = new StringBuilder("can_show_xzb_card");
        if (i != 0) {
            stringBuilder.append("_has_device");
            edit.putBoolean(stringBuilder.toString(), z).commit();
            return;
        }
        stringBuilder.append("_no_device");
        if (!z) {
            edit.putLong("last_closed_xzb_card_time", System.currentTimeMillis()).putBoolean(stringBuilder.toString(), z).commit();
        }
    }

    public static int d() {
        return BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).getInt("xzb_exhibition_task_count", -1);
    }

    public static void b(int i) {
        BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).edit().putInt("xzb_exhibition_task_count", i).commit();
    }

    public static int e() {
        return BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).getInt("xzb_exhibition_position", -1);
    }

    public static void c(int i) {
        BrothersApplication.a().getSharedPreferences("shared_for_show_xzb", 0).edit().putInt("xzb_exhibition_position", i).commit();
    }

    public static boolean f() {
        return !BrothersApplication.a().getSharedPreferences("shared_for_show_download_device_select", 0).getBoolean("is_has_showed_download_device_select", false);
    }

    public static void g() {
        BrothersApplication.a().getSharedPreferences("shared_for_show_download_device_select", 0).edit().putBoolean("is_has_showed_download_device_select", true).commit();
    }

    public static void a(Context context, XLToastType xLToastType, String str, String str2) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            View inflate = LayoutInflater.from(context).inflate(2130968708, null);
            TextView textView = (TextView) inflate.findViewById(2131755689);
            TextView textView2 = (TextView) inflate.findViewById(2131755690);
            if (TextUtils.isEmpty(str)) {
                textView.setVisibility(XZBDevice.Wait);
            } else {
                textView.setVisibility(0);
                textView.setText(str);
            }
            if (TextUtils.isEmpty(str2)) {
                textView2.setVisibility(XZBDevice.Wait);
            } else {
                textView2.setVisibility(0);
                textView2.setText(str2);
            }
            Toast toast = new Toast(context);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_icon);
            int i = xLToastType == XLToastType.XLTOAST_TYPE_ALARM ? R.drawable.toast_alarm_icon : xLToastType == XLToastType.XLTOAST_TYPE_SUC ? R.drawable.toast_success_icon : 0;
            if (i != 0) {
                imageView.setImageResource(i);
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(XZBDevice.Wait);
            }
            toast.setView(inflate);
            toast.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
            toast.setDuration(0);
            toast.show();
        }
    }
}
