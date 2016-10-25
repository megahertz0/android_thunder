package com.xunlei.downloadprovider.member.payment;

import com.tencent.open.GameAppOperation;
import com.tencent.open.utils.SystemUtils;
import com.umeng.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: PayReporter.java
public final class b {
    private static long a;

    static {
        a = System.currentTimeMillis();
    }

    public static void a() {
        a = System.currentTimeMillis();
    }

    public static void a(String str, boolean z, int i, int i2) {
        int i3;
        String str2 = "pay_show";
        g a = g.a("android_pay_event_v2", str2, str2).a("vip_from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str3 = SystemUtils.IS_LOGIN;
        if (z) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        a(a.b(str3, (long) i3).b("is_vip", (long) i).b("vip_type", (long) i2));
    }

    public static void a(String str, int i, OrderType orderType, int i2) {
        String str2 = "pay_show_fail";
        a(g.a("android_pay_event_v2", str2, str2).a("errorcode", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("default_product_type", (long) a(i)).a("default_product", b(orderType), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("tab_type", (long) i2));
    }

    public static void a(int i, OrderType orderType, boolean z, int i2, int i3) {
        int i4;
        String str = "pay_back";
        g a = g.a("android_pay_event_v2", str, str);
        String str2 = SystemUtils.IS_LOGIN;
        if (z) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        a(a.b(str2, (long) i4).b("is_vip", (long) i2).b("vip_type", (long) i3).b("default_product_type", (long) a(i)).a("default_product", b(orderType), (int) XZBDevice.DOWNLOAD_LIST_FAILED));
    }

    public static void b() {
        String str = "pay_backAlert_show";
        a(g.a("android_pay_event_v2", str, str).b("activity_type", -1));
    }

    public static void a(boolean z) {
        String str = "pay_backAlert_click";
        a(g.a("android_pay_event_v2", str, str).b("activity_type", -1).b("clickid", (long) (z ? 1 : 0)));
    }

    public static void a(String str, int i, OrderType orderType, int i2, int i3, int i4, OrderType orderType2, int i5, float f, boolean z, int i6, int i7, int i8, String str2, int i9, int i10) {
        int i11;
        String str3 = "pay_submit";
        g a = g.a("android_pay_event_v2", str3, str3).a("vip_from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("default_product_type", (long) a(i)).b("default_month", (long) i2).b("default_day", (long) i3).a("default_product", b(orderType), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("isrenew", (long) a(orderType2)).b("product_type", (long) a(i4)).b("month", (long) i5).b("day", (long) i8).a("amount", PayUtil.a(f), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str4 = SystemUtils.IS_LOGIN;
        if (z) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        a(a.b(str4, (long) i11).b("is_vip", (long) i6).b("vip_type", (long) i7).a(GameAppOperation.QQFAV_DATALINE_VERSION, str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("voucher", (long) i9).b("pay_auto", (long) i10));
    }

    public static void a(String str, boolean z, int i, int i2, String str2, float f) {
        int i3;
        String str3 = "pay_type_click";
        g a = g.a("android_pay_event_v2", str3, str3).a("vip_from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str4 = SystemUtils.IS_LOGIN;
        if (z) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        a(a.b(str4, (long) i3).a("pay_channel", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("amount", PayUtil.a(f), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("is_vip", (long) i2).b("vip_type", (long) i));
    }

    public static void a(String str, int i, int i2, OrderType orderType, int i3, int i4, int i5, String str2, float f, int i6, int i7, String str3, int i8, int i9, OrderType orderType2, int i10, int i11) {
        String str4 = "pay_success";
        a(g.a("android_pay_event_v2", str4, str4).a("pay_sessonid", a).a("vip_from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("isrenew", (long) a(orderType)).b("product_type", (long) a(i2)).b("month", (long) i3).b("day", (long) i6).b("default_product_type", (long) a(i)).a("default_product", b(orderType2), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("default_month", (long) i8).b("default_day", (long) i9).a("pay_channel", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("amount", PayUtil.a(f), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("is_vip", (long) i5).b("vip_type", (long) i4).b("voucher", (long) i7).a(GameAppOperation.QQFAV_DATALINE_VERSION, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("voucher", (long) i10).b("pay_auto", (long) i11));
    }

    public static void c() {
        String str = "pay_successPage_show";
        a(g.a("android_pay_event_v2", str, str).b("activity_type", -1));
    }

    public static void a(String str, int i, OrderType orderType, int i2, int i3, int i4, String str2, int i5, int i6, int i7, String str3) {
        String str4 = "pay_fail";
        a(g.a("android_pay_event_v2", str4, str4).a("pay_sessonid", a).b("isrenew", (long) a(orderType)).b("product_type", (long) a(i)).b("month", (long) i2).b("day", (long) i6).a("pay_channel", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("vip_from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("failtype", (long) i5).b("is_vip", (long) i4).b("vip_type", (long) i3).b("voucher", (long) i7).a(GameAppOperation.QQFAV_DATALINE_VERSION, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
    }

    public static void a(int i, OrderType orderType, int i2, int i3, int i4, String str, String str2, int i5, int i6) {
        String str3 = "pay_cancel";
        a(g.a("android_pay_event_v2", str3, str3).a("pay_sessonid", a).b("isrenew", (long) a(orderType)).b("product_type", (long) a(i)).b("month", (long) i2).a("pay_channel", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("is_vip", (long) i4).b("vip_type", (long) i3).b("voucher", (long) i5).b("day", (long) i6).a("vip_from", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
    }

    private static int a(OrderType orderType) {
        switch (AnonymousClass_1.a[orderType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return 0;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return 1;
            default:
                return -1;
        }
    }

    private static String b(OrderType orderType) {
        switch (AnonymousClass_1.a[orderType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "\u5f00\u901a";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "\u7eed\u8d39";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "\u5347\u7ea7";
            default:
                return a.d;
        }
    }

    private static int a(int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return XZBDevice.DOWNLOAD_LIST_FAILED;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return 1;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return XZBDevice.DOWNLOAD_LIST_ALL;
            case 204:
                return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
            default:
                return 0;
        }
    }

    private static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }

    public static void a(String str, int i, OrderType orderType, int i2, int i3) {
        String str2 = "pay_show_success";
        a(g.a("android_pay_event_v2", str2, str2).a("vip_from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("default_product_type", (long) a(i)).a("default_product", b(orderType), (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("default_month", (long) i2).b("default_day", (long) i3));
    }

    public static void a(int i, int i2, boolean z, int i3, int i4) {
        int i5 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        int i6 = 1;
        int i7 = i == 5 ? 2 : (i == 3 || i == 4) ? 1 : 3;
        if (i2 != 5) {
            i5 = (i2 == 3 || i2 == 4) ? 1 : 3;
        }
        String str = "pay_tab_alter";
        g b = g.a("android_pay_event_v2", str, str).b("default_tab", (long) i7).b("tab", (long) i5);
        str = SystemUtils.IS_LOGIN;
        if (!z) {
            i6 = 0;
        }
        a(b.b(str, (long) i6).b("is_vip", (long) i3).b("vip_type", (long) i4));
    }

    public static void d() {
        String str = "pay_voucher_click";
        a(g.a("android_pay_event_v2", str, str));
    }

    public static void e() {
        String str = "pay_key_click";
        a(g.a("android_pay_event_v2", str, str));
    }

    public static void f() {
        String str = "pay_agreement_click";
        a(g.a("android_pay_event_v2", str, str));
    }

    public static void g() {
        String str = "pay_key_successPage_show";
        a(g.a("android_pay_event_v2", str, str).b("activity_type", -1));
    }

    public static void a(int i, int i2) {
        String str = "pay_custmer_faq";
        g a = g.a("android_pay_event_v2", str, str);
        String str2 = SystemUtils.IS_LOGIN;
        j.a();
        a(a.b(str2, (long) (LoginHelper.c() ? 1 : 0)).b("is_vip", (long) i).b("vip_type", (long) i2));
    }

    public static void a(int i, int i2, int i3) {
        String str = "pay_cancel_autopay";
        g a = g.a("android_pay_event_v2", str, str);
        String str2 = SystemUtils.IS_LOGIN;
        j.a();
        a(a.b(str2, (long) (LoginHelper.c() ? 1 : 0)).b("is_vip", (long) i).b("vip_type", (long) i2).b("cancel", (long) i3));
    }

    public static void a(String str, String str2, int i, int i2) {
        g a = g.a("android_pay_event_v2", str, str).a("vip_from", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str3 = SystemUtils.IS_LOGIN;
        j.a();
        a(a.b(str3, (long) (LoginHelper.c() ? 1 : 0)).b("is_vip", (long) i).b("vip_type", (long) i2));
    }
}
