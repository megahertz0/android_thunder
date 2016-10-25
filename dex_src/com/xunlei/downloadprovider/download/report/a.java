package com.xunlei.downloadprovider.download.report;

import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.SystemUtils;
import com.umeng.message.MsgConstant;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.service.downloads.task.info.b;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: DLCenterReporter.java
public final class a {
    public static String a;

    private static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }

    public static void a(long j) {
        String str = "dl_try_1_show";
        g a = g.a("android_dl_center_action", str, str);
        a.a("speed", j);
        a(a);
    }

    public static void a(String str) {
        String str2 = "push_pop";
        g a = g.a("android_push", str2, str2);
        a.a(JsInterface.FUNPLAY_AD_TRPE, str);
        a(a);
    }

    public static void a() {
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_dl_center_action", "dl_center_taskDetail_full_screen"));
    }

    public static void a(String str, String str2) {
        String str3 = "push_click";
        g a = g.a("android_push", str3, str3);
        a.a(JsInterface.FUNPLAY_AD_TRPE, str);
        a.a("clickid", str2);
        a(a);
    }

    public static void b(String str) {
        String str2 = "dl_task_bar_show";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a(JsInterface.FUNPLAY_AD_TRPE, str);
        a(a);
    }

    public static void c(String str) {
        String str2 = "dl_task_bar_click";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a(JsInterface.FUNPLAY_AD_TRPE, str);
        a(a);
    }

    public static void b() {
        String str = "dl_try_2_click";
        g a = g.a("android_dl_center_action", str, str);
        String str2 = SystemUtils.IS_LOGIN;
        LoginHelper.a();
        a.b(str2, LoginHelper.c() ? 1 : 0);
        a(a);
    }

    public static void d(String str) {
        String str2 = "dl_try_4_tipclose";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a(Impl.COLUMN_STATUS, str);
        String str3 = SystemUtils.IS_LOGIN;
        LoginHelper.a();
        a.b(str3, LoginHelper.c() ? 1 : 0);
        a(a);
    }

    public static void e(String str) {
        String str2 = "dl_try_5_openvip";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a(Impl.COLUMN_STATUS, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str3 = SystemUtils.IS_LOGIN;
        LoginHelper.a();
        a.b(str3, LoginHelper.c() ? 1 : 0);
        a(a);
    }

    public static void c() {
        String str = "dl_try_6_toast_alert";
        a(g.a("android_dl_center_action", str, str));
    }

    public static void d() {
        String str = "dl_try_push_alert";
        a(g.a("android_dl_center_action", str, str));
    }

    public static void e() {
        String str = "dl_try_push_click";
        a(g.a("android_dl_center_action", str, str));
    }

    public static void a(String str, String str2, String str3) {
        String str4;
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        b m = com.xunlei.downloadprovider.service.downloads.task.d.m();
        String str5 = "dl_center_show";
        g a = g.a("android_dl_center_action", str5, str5);
        String str6 = "from";
        if (str == null) {
            str4 = "other";
        } else {
            str4 = str.toString();
        }
        a.a(str6, str4, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = SocialConstants.PARAM_APP_ID;
        if (str3 == null) {
            str3 = com.umeng.a.d;
        }
        a.a(str4, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = "sdkid";
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str4, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a(Constants.KEY_IMEI, com.xunlei.downloadprovider.a.b.c(BrothersApplication.a()), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("mac", com.xunlei.downloadprovider.a.b.e(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str6 = SystemUtils.IS_LOGIN;
        LoginHelper.a();
        a.a(str6, LoginHelper.c() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("finish_tasknum", m.b, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("downloading_tasknum", m.c, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("fail_tasknum", m.e, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("pause_tasknum", m.d, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("net_type", com.xunlei.xllib.a.b.a.a(BrothersApplication.a()));
        a(a);
    }

    public static void f(String str) {
        String str2 = "dl_center_act_click";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a("clickid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        if ("top_kuainiao".equals(str)) {
            a.a("is_vip", LoginHelper.a().f() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.b("vip_type", (long) LoginHelper.a().h);
        }
        a(a);
    }

    public static void b(String str, String str2, String str3) {
        String str4 = "dl_center_list_click";
        g a = g.a("android_dl_center_action", str4, str4);
        a.a("clickid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = Impl.COLUMN_STATUS;
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str4, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = "tabid";
        if (str3 == null) {
            str3 = com.umeng.a.d;
        }
        a.a(str4, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void f() {
        String str = "dl_alert_opentip_show";
        g a = g.a("android_dl_center_action", str, str);
        a.a("from", "top_vip", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void g() {
        String str = "dl_center_kuaniao_show";
        g a = g.a("android_dl_center_action", str, str);
        a.a("is_vip", LoginHelper.a().f() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.b("vip_type", (long) LoginHelper.a().h);
        a(a);
    }

    public static void g(String str) {
        String str2 = "dl_center_taskDetail_tab";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a("tabid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void b(String str, String str2) {
        String str3 = "dl_center_taskAlert_show";
        g a = g.a("android_dl_center_action", str3, str3);
        str3 = Impl.COLUMN_STATUS;
        if (str == null) {
            str = com.umeng.a.d;
        }
        a.a(str3, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str3 = "tabid";
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str3, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void c(String str, String str2, String str3) {
        String str4 = "dl_center_taskAlert_click";
        g a = g.a("android_dl_center_action", str4, str4);
        a.a("clickid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = Impl.COLUMN_STATUS;
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str4, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = "tabid";
        if (str3 == null) {
            str3 = com.umeng.a.d;
        }
        a.a(str4, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static String a(TaskRunningInfo taskRunningInfo) {
        if (taskRunningInfo == null) {
            return com.umeng.a.d;
        }
        String str = com.umeng.a.d;
        switch (taskRunningInfo.mTaskStatus) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "wait";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return (taskRunningInfo.mHasLixianSpeedup || taskRunningInfo.mHasVipChannelSpeedup) ? "downloading_speed" : "downloading";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "pause";
            case XZBDevice.Wait:
                return "finish";
            case R.styleable.Toolbar_titleMarginBottom:
                return MsgConstant.KEY_FAIL;
            default:
                return str;
        }
    }

    public static void a(String str, String str2, int i) {
        String str3 = "dl_center_taskDetail_click";
        g a = g.a("android_dl_center_action", str3, str3);
        str3 = "clickid";
        if (str == null) {
            str = com.umeng.a.d;
        }
        a.a(str3, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str3 = Impl.COLUMN_STATUS;
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str3, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.b("if_bt", (long) i);
        a(a);
    }

    static {
        a = "download_detail_top";
    }

    public static void c(String str, String str2) {
        String str3 = "download_share_to";
        g a = g.a("android_dl_center_action", str3, str3);
        str3 = "to";
        if (str == null) {
            str = com.umeng.a.d;
        }
        a.a(str3, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str3 = "from";
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str3, str2, 1);
        ThunderReporter.a(a, false);
    }

    public static void d(String str, String str2, String str3) {
        String str4 = "download_share_result";
        g a = g.a("android_dl_center_action", str4, str4);
        str4 = "to";
        if (str == null) {
            str = com.umeng.a.d;
        }
        a.a(str4, str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = "result";
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        a.a(str4, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        str4 = "from";
        if (str3 == null) {
            str3 = com.umeng.a.d;
        }
        a.a(str4, str3, 1);
        ThunderReporter.a(a, false);
    }

    public static void a(String str, int i) {
        String str2 = "dl_center_tabshow";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a("tabid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.a("tasknum", (long) i);
        a(a);
    }

    public static void h(String str) {
        String str2 = "dl_qrcode_click";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a("step", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void i(String str) {
        String str2 = "dl_newtask_click";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a("step", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void j(String str) {
        String str2 = "dl_newbt_click";
        g a = g.a("android_dl_center_action", str2, str2);
        a.a("step", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void k(String str) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_dl_center_action", "dl_delete_alert");
        String str2 = "from";
        if (str == null) {
            str = com.umeng.a.d;
        }
        d.a(a.b(str2, str));
    }

    public static void l(String str) {
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_dl_center_action", "dl_delete_alert_click").b("clickid", str));
    }

    public static void a(String str, boolean z) {
        new StringBuilder("dl_delete_alert_click:clickid = ").append(str).append(",if_choose = ").append(z);
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_dl_center_action", "dl_delete_alert_click").b("clickid", str).b("if_choose", z ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT));
    }
}
