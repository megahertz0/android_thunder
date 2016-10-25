package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Method;

// compiled from: TrafficTracker.java
public final class e {
    public static bo a(Context context) {
        try {
            bo boVar = new bo();
            Class forName = Class.forName("android.net.TrafficStats");
            Method method = forName.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
            Method method2 = forName.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
            if (context.getApplicationInfo().uid == -1) {
                Object[] objArr = null;
            } else {
                r2 = new long[2];
                r2[0] = ((Long) method.invoke(null, new Object[]{Integer.valueOf(context.getApplicationInfo().uid)})).longValue();
                r2[1] = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(r5)})).longValue();
                long[] jArr = r2;
            }
            if (jArr[0] <= 0 || jArr[1] <= 0) {
                return null;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
            long j = sharedPreferences.getLong("uptr", -1);
            long j2 = sharedPreferences.getLong("dntr", -1);
            sharedPreferences.edit().putLong("uptr", jArr[1]).putLong("dntr", jArr[0]).commit();
            if (j <= 0 || j2 <= 0) {
                return null;
            }
            jArr[0] = jArr[0] - j2;
            jArr[1] = jArr[1] - j;
            if (jArr[0] <= 0 || jArr[1] <= 0) {
                return null;
            }
            boVar.b = (int) jArr[0];
            boVar.b();
            boVar.a = (int) jArr[1];
            boVar.a();
            return boVar;
        } catch (Exception e) {
            v.c("MobclickAgent", "sdk less than 2.2 has get no traffic");
            return null;
        }
    }
}
