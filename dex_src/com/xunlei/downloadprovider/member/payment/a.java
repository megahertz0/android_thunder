package com.xunlei.downloadprovider.member.payment;

import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: H5PayReporter.java
public final class a {
    private static long a;

    static {
        a = System.currentTimeMillis();
    }

    public static void a() {
        a = System.currentTimeMillis();
    }

    public static void a(String str, boolean z, int i, String str2, String str3) {
        int i2;
        String str4 = "pay_show_H5";
        g a = g.a("android_pay_event_H5_v2", str4, str4).a("pay_sessonid", a).a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str5 = "is_vip";
        if (z) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        a(a.b(str5, (long) i2).b("load_time", (long) i).a("net_type", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a(JsInterface.KEY_PAGE, str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
    }

    public static void a(String str, boolean z) {
        int i;
        String str2 = "pay_submit_H5";
        g a = g.a("android_pay_event_H5_v2", str2, str2).a("pay_sessonid", a).a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        String str3 = "is_vip";
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        a(a.b(str3, (long) i));
    }

    public static void a(String str, int i, int i2) {
        g b;
        String str2;
        switch (i2) {
            case SpdyAgent.ACCS_TEST_SERVER:
                str2 = "pay_success_H5";
                b = g.a("android_pay_event_H5_v2", str2, str2).a("pay_sessonid", a).a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("month", (long) i);
                break;
            case R.styleable.AppCompatTheme_buttonBarButtonStyle:
            case R.styleable.AppCompatTheme_buttonStyleSmall:
            case 1000001:
                str2 = "pay_fail_H5";
                b = g.a("android_pay_event_H5_v2", str2, str2).a("pay_sessonid", a).a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("month", (long) i).a("fail_reason", "cancel", (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("fail_type", (long) i2);
                break;
            default:
                str2 = "pay_fail_H5";
                b = g.a("android_pay_event_H5_v2", str2, str2).a("pay_sessonid", a).a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("month", (long) i).a("fail_reason", MsgConstant.KEY_FAIL, (int) XZBDevice.DOWNLOAD_LIST_FAILED).b("fail_type", (long) i2);
                break;
        }
        a(b);
    }

    private static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }
}
