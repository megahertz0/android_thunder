package com.umeng.socialize.editorpage.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

// compiled from: SocializeLocationListener.java
public class c implements LocationListener {
    private a a;

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }

    public void onLocationChanged(Location location) {
        if (this.a != null) {
            this.a.a(location);
            this.a.c().a(this);
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
