package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.analytics.AnalyticsConfig;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// compiled from: SessionTracker.java
public final class b {
    private static String c;
    private final String a;
    private final String b;

    public b() {
        this.a = "a_start_time";
        this.b = "a_end_time";
    }

    static {
        c = null;
    }

    public static String b(Context context) {
        String c = t.c(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(appkey).append(c);
        c = u.a(stringBuilder.toString());
        c = c;
        return c;
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        if (sharedPreferences != null) {
            if (sharedPreferences.getLong("a_start_time", 0) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                v.c("onPause called before onResume");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Editor edit = sharedPreferences.edit();
            edit.putLong("a_start_time", 0);
            edit.putLong("a_end_time", currentTimeMillis);
            edit.putLong("session_end_time", currentTimeMillis);
            edit.commit();
        }
    }

    public static boolean a(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j == 0 || currentTimeMillis - j >= AnalyticsConfig.kContinueSessionMillis) {
            return currentTimeMillis - j2 > AnalyticsConfig.kContinueSessionMillis;
        } else {
            v.c("onResume called before onPause");
            return false;
        }
    }

    public static boolean d(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        if (!(sharedPreferences == null || sharedPreferences.getString("session_id", null) == null)) {
            long j = sharedPreferences.getLong("a_start_time", 0);
            long j2 = sharedPreferences.getLong("a_end_time", 0);
            if (j > 0 && j2 == 0) {
                int i = 1;
                c(context);
            }
            ct a = ct.a(context);
            dc a2 = a(context);
            if (a2 != null) {
                a.b(a2);
            }
        }
        return z;
    }

    public static void e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        if (sharedPreferences != null) {
            String b = b(context);
            Editor edit = sharedPreferences.edit();
            edit.putString("session_id", b);
            edit.putLong("session_start_time", System.currentTimeMillis());
            edit.putLong("session_end_time", 0);
            edit.putLong("a_start_time", System.currentTimeMillis());
            edit.putLong("a_end_time", 0);
            edit.commit();
            v.a(new StringBuilder("Restart session: ").append(b).toString());
        }
    }

    public static String f(Context context) {
        if (c == null) {
            c = context.getSharedPreferences("umeng_general_config", 0).getString("session_id", null);
        }
        return c;
    }

    public static l a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        String string = sharedPreferences.getString("session_id", null);
        if (string == null) {
            return null;
        }
        long j = sharedPreferences.getLong("session_start_time", 0);
        long j2 = sharedPreferences.getLong("session_end_time", 0);
        long j3 = 0;
        if (j2 != 0) {
            j3 = j2 - j;
            if (Math.abs(j3) > 86400000) {
                j3 = 0;
            }
        }
        bn lVar = new l();
        lVar.a = string;
        lVar.a(j);
        lVar.b(j2);
        lVar.c(j3);
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            bg bgVar = new bg(location[0], location[1], System.currentTimeMillis());
            if (lVar.e()) {
                if (lVar.f == null) {
                    lVar.f = new ArrayList();
                }
                lVar.f.add(bgVar);
            } else {
                lVar.f = Arrays.asList(new bg[]{bgVar});
            }
        }
        bo a = e.a(context);
        if (a != null) {
            lVar.g = a;
        }
        List a2 = f.a(sharedPreferences);
        if (a2 != null && a2.size() > 0) {
            lVar.e = a2;
        }
        Editor edit = sharedPreferences.edit();
        edit.remove("session_start_time");
        edit.remove("session_end_time");
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.putString("activities", BuildConfig.VERSION_NAME);
        edit.commit();
        return lVar;
    }
}
