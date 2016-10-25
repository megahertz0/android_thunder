package com.xiaomi.smack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.common.Constants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.f;
import com.xiaomi.push.providers.a;
import com.xiaomi.push.service.XMPushService;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class k {
    private static f a;
    private static int b;
    private static final Object c;
    private static List<a> d;
    private static String e;
    private static a f;

    static {
        a = new f(true);
        b = -1;
        c = new Object();
        d = Collections.synchronizedList(new ArrayList());
        e = com.umeng.a.d;
        f = null;
    }

    private static int a(Context context) {
        if (b == -1) {
            b = b(context);
        }
        return b;
    }

    public static int a(String str) {
        try {
            return str.getBytes(GameManager.DEFAULT_CHARSET).length;
        } catch (UnsupportedEncodingException e) {
            return str.getBytes().length;
        }
    }

    private static long a(int i, long j) {
        return (((long) (i == 0 ? XZBDevice.Upload : XZBDevice.Success)) * j) / 10;
    }

    public static void a(XMPushService xMPushService, String str, long j, boolean z, long j2) {
        if (xMPushService != null && !TextUtils.isEmpty(str) && "com.xiaomi.xmsf".equals(xMPushService.getPackageName()) && !"com.xiaomi.xmsf".equals(str)) {
            int a = a((Context) xMPushService);
            if (-1 != a) {
                boolean isEmpty;
                synchronized (c) {
                    isEmpty = d.isEmpty();
                    a(new a(str, j2, a, z ? 1 : 0, a == 0 ? c(xMPushService) : com.umeng.a.d, a(a, j)));
                }
                if (isEmpty) {
                    a.a(new l(xMPushService), 5000);
                }
            }
        }
    }

    private static void a(a aVar) {
        for (a aVar2 : d) {
            if (aVar2.a(aVar)) {
                aVar2.f += aVar.f;
                return;
            }
        }
        d.add(aVar);
    }

    private static int b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    private static void b(Context context, List<a> list) {
        try {
            synchronized (a.a) {
                try {
                    SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
                    writableDatabase.beginTransaction();
                    for (a aVar : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("package_name", aVar.a);
                        contentValues.put("message_ts", Long.valueOf(aVar.b));
                        contentValues.put("network_type", Integer.valueOf(aVar.c));
                        contentValues.put("bytes", Long.valueOf(aVar.f));
                        contentValues.put("rcv", Integer.valueOf(aVar.d));
                        contentValues.put(Constants.KEY_IMSI, aVar.e);
                        writableDatabase.insert("traffic", null, contentValues);
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                }
            }
        } catch (Throwable e) {
            b.a(e);
        }
    }

    private static synchronized String c(Context context) {
        String str;
        synchronized (k.class) {
            if (TextUtils.isEmpty(e)) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        e = telephonyManager.getSubscriberId();
                    }
                } catch (Exception e) {
                }
                str = e;
            } else {
                str = e;
            }
        }
        return str;
    }

    private static a d(Context context) {
        if (f != null) {
            return f;
        }
        a aVar = new a(context);
        f = aVar;
        return aVar;
    }
}
