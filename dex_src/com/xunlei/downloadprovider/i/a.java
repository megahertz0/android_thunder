package com.xunlei.downloadprovider.i;

import android.text.TextUtils;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Set;
import org.android.spdy.SpdyAgent;

// compiled from: ThunderConfig.java
public class a {
    private static final String a;
    private static final Set<String> b;
    private static final Set<String> c;
    private static final Set<String> d;
    private static final Set<String> e;
    private static final Set<String> f;

    static {
        a = a.class.getSimpleName();
        b = new HashSet();
        c = new HashSet();
        d = new HashSet();
        e = new HashSet();
        f = new HashSet();
        b.add("0x10800009");
        b.add("0x10810100");
        b.add("0x10810004");
        c.add("0x10800013");
        c.add("0x10800022");
        c.add("0x10800012");
        c.add("0x10800028");
        c.add("0x10800009");
        c.add("0x10810179");
        c.add("0x10810180");
        c.add("0x10800011");
        c.add("0x10800066");
        c.add("0x10810207");
        c.add("0x10800037");
        c.add("0x10810338");
        c.add("0x10810355");
        c.add("0x10810356");
        c.add("0x10810360");
        c.add("0x10800002");
        d.add("0x10800012");
        d.add("0x10800022");
        d.add("0x10800009");
        d.add("0x10810179");
        d.add("0x10810180");
        d.add("0x10800066");
        e.add("0x10800009");
        f.add("0x10800009");
        f.add("0x10810179");
        f.add("0x10810180");
    }

    public static boolean a() {
        return c.contains(b.g());
    }

    public static boolean b() {
        return d.contains(b.g());
    }

    public static boolean c() {
        return e.contains(b.g());
    }

    public static boolean d() {
        return f.contains(b.g());
    }

    public static String e() {
        return "http://dl.k.sogou.com/channel/SogouNovel1575.apk";
    }

    public static int f() {
        String g = b.g();
        if (TextUtils.isEmpty(g)) {
            return -1;
        }
        int i;
        Object obj;
        switch (g.hashCode()) {
            case 2103986216:
                if (g.equals("0x10800009")) {
                    obj = 1;
                }
                i = -1;
                break;
            case 2103986240:
                if (g.equals("0x10800012")) {
                    obj = null;
                }
                i = -1;
                break;
            case 2103986241:
                if (g.equals("0x10800013")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                i = -1;
                break;
            default:
                i = -1;
                break;
        }
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return 2130838616;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return 2130838604;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 2130838603;
            default:
                return -1;
        }
    }
}
