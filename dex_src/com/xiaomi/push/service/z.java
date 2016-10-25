package com.xiaomi.push.service;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public abstract class z {
    public static String A;
    public static String B;
    public static String C;
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static String k;
    public static String l;
    public static String m;
    public static String n;
    public static String o;
    public static String p;
    public static String q;
    public static String r;
    public static String s;
    public static String t;
    public static String u;
    public static String v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;

    static {
        a = MessageService.MSG_DB_NOTIFY_REACHED;
        b = MessageService.MSG_DB_NOTIFY_CLICK;
        c = MessageService.MSG_DB_NOTIFY_DISMISS;
        d = "com.xiaomi.push.OPEN_CHANNEL";
        e = "com.xiaomi.push.SEND_MESSAGE";
        f = "com.xiaomi.push.SEND_IQ";
        g = "com.xiaomi.push.BATCH_SEND_MESSAGE";
        h = "com.xiaomi.push.SEND_PRES";
        i = "com.xiaomi.push.CLOSE_CHANNEL";
        j = "com.xiaomi.push.FORCE_RECONN";
        k = "com.xiaomi.push.RESET_CONN";
        l = "com.xiaomi.push.UPDATE_CHANNEL_INFO";
        m = "com.xiaomi.push.SEND_STATS";
        n = "com.xiaomi.push.CHANGE_HOST";
        o = "com.xiaomi.push.PING_TIMER";
        p = "ext_user_id";
        q = "ext_chid";
        r = "ext_sid";
        s = "ext_token";
        t = "ext_auth_method";
        u = "ext_security";
        v = "ext_kick";
        w = "ext_client_attr";
        x = "ext_cloud_attr";
        y = "ext_pkg_name";
        z = "ext_notify_id";
        A = "ext_notify_type";
        B = "ext_session";
        C = "sig";
    }

    public static String a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return "ERROR_OK";
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "ERROR_SERVICE_NOT_INSTALLED";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "ERROR_NETWORK_NOT_AVAILABLE";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "ERROR_NETWORK_FAILED";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "ERROR_ACCESS_DENIED";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "ERROR_AUTH_FAILED";
            case R.styleable.Toolbar_contentInsetEnd:
                return "ERROR_MULTI_LOGIN";
            case R.styleable.Toolbar_contentInsetLeft:
                return "ERROR_SERVER_ERROR";
            case XZBDevice.Wait:
                return "ERROR_RECEIVE_TIMEOUT";
            case XZBDevice.Pause:
                return "ERROR_READ_ERROR";
            case XZBDevice.Stop:
                return "ERROR_SEND_ERROR";
            case XZBDevice.Success:
                return "ERROR_RESET";
            case XZBDevice.Fail:
                return "ERROR_NO_CLIENT";
            case XZBDevice.Upload:
                return "ERROR_SERVER_STREAM";
            case XZBDevice.Predownload:
                return "ERROR_THREAD_BLOCK";
            case XZBDevice.Delete:
                return "ERROR_SERVICE_DESTROY";
            case R.styleable.Toolbar_titleMarginBottom:
                return "ERROR_SESSION_CHANGED";
            case R.styleable.Toolbar_maxButtonHeight:
                return "ERROR_READ_TIMEOUT";
            case R.styleable.Toolbar_collapseIcon:
                return "ERROR_CONNECTIING_TIMEOUT";
            case R.styleable.Toolbar_collapseContentDescription:
                return "ERROR_USER_BLOCKED";
            case R.styleable.Toolbar_navigationIcon:
                return "ERROR_REDIRECT";
            case R.styleable.Toolbar_navigationContentDescription:
                return "ERROR_BIND_TIMEOUT";
            case R.styleable.Toolbar_logoDescription:
                return "ERROR_PING_TIMEOUT";
            default:
                return String.valueOf(i);
        }
    }
}
