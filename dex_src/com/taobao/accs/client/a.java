package com.taobao.accs.client;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.IProcessName;
import com.taobao.accs.utl.ALog;
import com.taobao.agoo.TaobaoConstants;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public class a {
    public static final int SECURITY_OFF = 2;
    public static final int SECURITY_OPEN = 1;
    public static final int SECURITY_TAOBAO = 0;
    public static int a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static IProcessName f;
    public static AtomicInteger g;
    private static volatile a h;
    private static Context i;
    private String j;
    private IAppReceiver k;
    private ActivityManager l;
    private ConnectivityManager m;

    static {
        a = 0;
        b = null;
        g = new AtomicInteger(-1);
    }

    public static a a(Context context) {
        if (h == null) {
            synchronized (a.class) {
                if (h == null) {
                    h = new a(context);
                }
            }
        }
        return h;
    }

    private a(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        } else if (i == null) {
            i = context.getApplicationContext();
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public String a() {
        return this.j;
    }

    public void a(IAppReceiver iAppReceiver) {
        if (iAppReceiver != null) {
            this.k = iAppReceiver;
        }
    }

    public ActivityManager b() {
        if (this.l == null) {
            this.l = (ActivityManager) i.getSystemService("activity");
        }
        return this.l;
    }

    public ConnectivityManager c() {
        if (this.m == null) {
            this.m = (ConnectivityManager) i.getSystemService("connectivity");
        }
        return this.m;
    }

    public static String b(String str) {
        String str2;
        if (TextUtils.isEmpty(b)) {
            str2 = str + TaobaoConstants.DEFAULT_INTENT_SERVICE_CLASS_NAME;
        } else {
            str2 = b;
        }
        ALog.d("AdapterGlobalClientInfo", "getAgooCustomServiceName", SelectCountryActivity.EXTRA_COUNTRY_NAME, str2);
        return str2;
    }

    public static boolean d() {
        return g.intValue() == 0;
    }
}
