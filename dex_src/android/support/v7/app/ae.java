package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.umeng.socialize.editorpage.ShareActivity;

// compiled from: TwilightManager.java
final class ae {
    static final a a;
    final Context b;
    private final LocationManager c;

    // compiled from: TwilightManager.java
    private static class a {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;

        private a() {
        }
    }

    static {
        a = new a();
    }

    ae(Context context) {
        this.b = context;
        this.c = (LocationManager) context.getSystemService(ShareActivity.KEY_LOCATION);
    }

    final Location a(String str) {
        if (this.c != null) {
            try {
                if (this.c.isProviderEnabled(str)) {
                    return this.c.getLastKnownLocation(str);
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    static boolean a(a aVar) {
        return aVar != null && aVar.f > System.currentTimeMillis();
    }

    static void a(Location location) {
        long j;
        a aVar = a;
        long currentTimeMillis = System.currentTimeMillis();
        if (ad.a == null) {
            ad.a = new ad();
        }
        ad adVar = ad.a;
        adVar.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = adVar.b;
        adVar.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = adVar.d == 1;
        long j3 = adVar.c;
        long j4 = adVar.b;
        adVar.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = adVar.c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            if (currentTimeMillis > j4) {
                j = 0 + j5;
            } else if (currentTimeMillis > j3) {
                j = 0 + j4;
            } else {
                j = 0 + j3;
            }
            j += 60000;
        }
        aVar.a = z;
        aVar.b = j2;
        aVar.c = j3;
        aVar.d = j4;
        aVar.e = j5;
        aVar.f = j;
    }
}
