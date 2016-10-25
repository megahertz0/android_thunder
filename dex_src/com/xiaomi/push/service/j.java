package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class j {
    private static final Map<String, byte[]> a;
    private static ArrayList<Pair<String, byte[]>> b;

    static {
        a = new HashMap();
        b = new ArrayList();
    }

    public static void a(Context context, int i, String str) {
        synchronized (a) {
            for (String str2 : a.keySet()) {
                a(context, str2, (byte[]) a.get(str2), i, str);
            }
            a.clear();
        }
    }

    public static void a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, b.a(str));
    }

    public static void a(XMPushService xMPushService) {
        try {
            synchronized (a) {
                for (String str : a.keySet()) {
                    xMPushService.a(str, (byte[]) a.get(str));
                }
                a.clear();
            }
        } catch (Exception e) {
            b.a((Throwable) e);
            xMPushService.a((int) XZBDevice.Stop, e);
        }
    }

    public static void a(String str, byte[] bArr) {
        synchronized (a) {
            a.put(str, bArr);
        }
    }

    public static void b(XMPushService xMPushService) {
        try {
            ArrayList arrayList;
            synchronized (b) {
                arrayList = b;
                b = new ArrayList();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                xMPushService.a((String) pair.first, (byte[]) pair.second);
            }
        } catch (Exception e) {
            b.a((Throwable) e);
            xMPushService.a((int) XZBDevice.Stop, e);
        }
    }

    public static void b(String str, byte[] bArr) {
        synchronized (b) {
            b.add(new Pair(str, bArr));
            if (b.size() > 50) {
                b.remove(0);
            }
        }
    }
}
