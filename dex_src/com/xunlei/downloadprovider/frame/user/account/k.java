package com.xunlei.downloadprovider.frame.user.account;

import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: UserAccountReporter.java
public final class k {
    public static void a(String str, String str2) {
        g gVar = null;
        Object obj = -1;
        switch (str.hashCode()) {
            case 379101630:
                if (str.equals("phone_register_login")) {
                    obj = 1;
                }
                break;
            case 397730471:
                if (str.equals("account_center")) {
                    obj = null;
                }
                break;
        }
        String str3;
        switch (obj) {
            case SpdyAgent.ACCS_TEST_SERVER:
                str3 = "account_head_conf_click";
                gVar = g.a("android_personal_account", str3, str3);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str3 = "register_head_conf_click";
                gVar = g.a("android_personal_account", str3, str3);
                break;
        }
        if (gVar != null) {
            gVar.a(SocialConstants.PARAM_SOURCE, str2);
            a(gVar);
        }
    }

    public static void a(String str, String str2, String str3) {
        g gVar = null;
        Object obj = -1;
        switch (str.hashCode()) {
            case 379101630:
                if (str.equals("phone_register_login")) {
                    obj = 1;
                }
                break;
            case 397730471:
                if (str.equals("account_center")) {
                    obj = null;
                }
                break;
        }
        String str4;
        switch (obj) {
            case SpdyAgent.ACCS_TEST_SERVER:
                str4 = "account_head_change_result";
                gVar = g.a("android_personal_account", str4, str4);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str4 = "register_head_change_result";
                gVar = g.a("android_personal_account", str4, str4);
                break;
        }
        if (gVar != null) {
            gVar.a(SocialConstants.PARAM_SOURCE, str2);
            gVar.a("result", str3);
            a(gVar);
        }
    }

    public static void b(String str, String str2) {
        g gVar = null;
        Object obj = -1;
        switch (str.hashCode()) {
            case 379101630:
                if (str.equals("phone_register_login")) {
                    obj = 1;
                }
                break;
            case 397730471:
                if (str.equals("account_center")) {
                    obj = null;
                }
                break;
        }
        String str3;
        switch (obj) {
            case SpdyAgent.ACCS_TEST_SERVER:
                str3 = "account_name_change_result";
                gVar = g.a("android_personal_account", str3, str3);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str3 = "register_name_change_result";
                gVar = g.a("android_personal_account", str3, str3);
                break;
        }
        if (gVar != null) {
            gVar.a("result", str2);
            a(gVar);
        }
    }

    public static void c(String str, String str2) {
        String str3 = "account_gender_change_result";
        g a = g.a("android_personal_account", str3, str3);
        a.a("result", str);
        if (str.equals(MsgConstant.KEY_SUCCESS)) {
            a.a("gender", str2);
        }
        a(a);
    }

    public static void a(int i, String str, int i2) {
        String str2 = "account_third_bind_result";
        g a = g.a("android_personal_account", str2, str2);
        a.a("account_type", a(i));
        a.a("result", str);
        a.a("errorcode", (long) i2);
        a(a);
    }

    public static void a(String str, long j) {
        c a = a.a("android_personal_account", "personal_space_common_click");
        a.b("clickid", str);
        a.b("discussid", String.valueOf(j));
        d.a(a);
    }

    public static void a(String str, boolean z, String str2, long j, long j2) {
        c a = a.a("android_personal_account", "personal_space_discuss_result");
        a.a("result", z ? MsgConstant.KEY_SUCCESS : MsgConstant.KEY_FAIL);
        String str3 = "errorcode";
        if ("-1001".contentEquals(str2)) {
            str2 = "network_err";
        }
        a.a(str3, str2);
        a.a("movieid", str);
        a.a("discussid", String.valueOf(j));
        a.a("new_discussid", String.valueOf(j2));
        a.a("level", j == -1 ? MessageService.MSG_DB_READY_REPORT : MessageService.MSG_DB_NOTIFY_REACHED);
        d.a(a);
    }

    public static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }

    public static String a(int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return Token.WX_TOKEN_PLATFORMID_VALUE;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return Constants.SOURCE_QQ;
            default:
                return "weibo";
        }
    }
}
