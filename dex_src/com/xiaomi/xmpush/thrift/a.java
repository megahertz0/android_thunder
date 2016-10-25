package com.xiaomi.xmpush.thrift;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public enum a {
    Registration(1),
    UnRegistration(2),
    Subscription(3),
    UnSubscription(4),
    SendMessage(5),
    AckMessage(6),
    SetConfig(7),
    ReportFeedback(8),
    Notification(9),
    Command(10),
    MultiConnectionBroadcast(11),
    MultiConnectionResult(12);
    private final int m;

    static {
        a = new a("Registration", 0, 1);
        b = new a("UnRegistration", 1, 2);
        c = new a("Subscription", 2, 3);
        d = new a("UnSubscription", 3, 4);
        e = new a("SendMessage", 4, 5);
        f = new a("AckMessage", 5, 6);
        g = new a("SetConfig", 6, 7);
        h = new a("ReportFeedback", 7, 8);
        i = new a("Notification", 8, 9);
        j = new a("Command", 9, 10);
        k = new a("MultiConnectionBroadcast", 10, 11);
        l = new a("MultiConnectionResult", 11, 12);
        n = new a[]{a, b, c, d, e, f, g, h, i, j, k, l};
    }

    private a(int i) {
        this.m = i;
    }

    public static a a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return a;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return b;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return c;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return d;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return e;
            case R.styleable.Toolbar_contentInsetEnd:
                return f;
            case R.styleable.Toolbar_contentInsetLeft:
                return g;
            case XZBDevice.Wait:
                return h;
            case XZBDevice.Pause:
                return i;
            case XZBDevice.Stop:
                return j;
            case XZBDevice.Success:
                return k;
            case XZBDevice.Fail:
                return l;
            default:
                return null;
        }
    }

    public final int a() {
        return this.m;
    }
}
