package com.umeng.socialize.editorpage.location;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.utils.DeviceConfig;

// compiled from: SocializeLocationManager.java
public class d {
    LocationManager a;

    public d() {
        this.a = null;
    }

    public void a(Context context) {
        if (DeviceConfig.checkPermission(context, "android.permission.ACCESS_FINE_LOCATION") || DeviceConfig.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            this.a = (LocationManager) context.getApplicationContext().getSystemService(ShareActivity.KEY_LOCATION);
        }
    }

    public String a(Criteria criteria, boolean z) {
        return this.a == null ? null : this.a.getBestProvider(criteria, z);
    }

    public Location a(String str) {
        return this.a == null ? null : this.a.getLastKnownLocation(str);
    }

    public boolean b(String str) {
        return this.a == null ? false : this.a.isProviderEnabled(str);
    }

    public void a(Activity activity, String str, long j, float f, LocationListener locationListener) {
        if (this.a != null) {
            activity.runOnUiThread(new e(this, str, j, f, locationListener));
        }
    }

    public void a(LocationListener locationListener) {
        if (this.a != null) {
            this.a.removeUpdates(locationListener);
        }
    }
}
