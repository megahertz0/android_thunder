package com.xunlei.downloadprovider.g;

import android.location.LocationManager;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.io.BufferedReader;
import java.io.FileReader;

// compiled from: LocationUtil.java
public final class a {
    public static boolean a() {
        try {
            LocationManager locationManager = (LocationManager) BrothersApplication.a().getSystemService("location");
            return locationManager == null ? false : locationManager.isProviderEnabled("gps");
        } catch (Exception e) {
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.g.a.b():java.lang.String");
        /*
        r0 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1 = "/proc/cpuinfo";
        r0.<init>(r1);	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r0 = r1.readLine();	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r2 = r1.readLine();	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1.close();	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1 = ":\\s+";
        r3 = 2;
        r0 = r0.split(r1, r3);	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1 = ":\\s+";
        r3 = 2;
        r2.split(r1, r3);	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
        r1 = 1;
        r0 = r0[r1];	 Catch:{ FileNotFoundException -> 0x002b, IOException -> 0x0033, Exception -> 0x0038 }
    L_0x002a:
        return r0;
    L_0x002b:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x002f:
        r0 = "";
        goto L_0x002a;
    L_0x0033:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002f;
    L_0x0038:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002f;
        */
    }

    public static long c() {
        Object obj = 1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                new StringBuilder().append(split[i]).append("\t");
            }
            long intValue = (long) Integer.valueOf(split[1]).intValue();
            try {
                bufferedReader.close();
            } catch (Exception e) {
                obj = null;
                return obj != null ? 0 : intValue / 1024;
            }
        } catch (Exception e2) {
            intValue = 0;
            obj = null;
            if (obj != null) {
            }
        }
        if (obj != null) {
        }
    }
}
