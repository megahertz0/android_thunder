package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.utl.UtilityImpl;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class a {
    private static final String b = "00:00:00:00:00:00";
    private static a e;
    public String a;
    private String c;
    private String d;

    static {
        e = null;
    }

    public static a a(Context context) {
        if (e == null) {
            e = new a(context);
        }
        return e;
    }

    private a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            b(telephonyManager.getDeviceId());
            String subscriberId = telephonyManager.getSubscriberId();
            if (subscriberId != null) {
                subscriberId = (subscriberId + "000000000000000").substring(0, XZBDevice.Delete);
            }
            this.c = subscriberId;
            this.a = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getMacAddress();
            if (TextUtils.isEmpty(this.a)) {
                this.a = b;
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                if (TextUtils.isEmpty(this.a)) {
                    this.a = b;
                }
            } catch (Throwable th) {
                if (TextUtils.isEmpty(this.a)) {
                    this.a = b;
                }
            }
        }
    }

    public final String a() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = "000000000000000";
        }
        return this.c;
    }

    public final String b() {
        if (TextUtils.isEmpty(this.d)) {
            this.d = "000000000000000";
        }
        return this.d;
    }

    private void a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, XZBDevice.Delete);
        }
        this.c = str;
    }

    private void b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            int i = 0;
            while (i < bytes.length) {
                if (bytes[i] < (byte) 48 || bytes[i] > 57) {
                    bytes[i] = (byte) 48;
                }
                i++;
            }
            str = (new String(bytes) + "000000000000000").substring(0, XZBDevice.Delete);
        }
        this.d = str;
    }

    private String c() {
        String str = b() + "|";
        Object a = a();
        return TextUtils.isEmpty(a) ? str + "000000000000000" : str + a;
    }

    private String d() {
        return this.a;
    }

    public static d b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
                return (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) ? d.o : d.a;
            } else {
                return d.a(activeNetworkInfo.getSubtype());
            }
        } catch (Exception e) {
            return d.o;
        }
    }

    public static String c(Context context) {
        a a = a(context);
        String str = a.b() + "|";
        Object a2 = a.a();
        return (TextUtils.isEmpty(a2) ? str + "000000000000000" : str + a2).substring(0, XZBDevice.Wait);
    }

    public static String d(Context context) {
        if (context == null) {
            return com.umeng.a.d;
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable th) {
            return com.umeng.a.d;
        }
    }
}
