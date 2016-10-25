package com.umeng.socialize.editorpage.location;

import android.location.LocationListener;

// compiled from: SocializeLocationManager.java
class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ long b;
    final /* synthetic */ float c;
    final /* synthetic */ LocationListener d;
    final /* synthetic */ d e;

    e(d dVar, String str, long j, float f, LocationListener locationListener) {
        this.e = dVar;
        this.a = str;
        this.b = j;
        this.c = f;
        this.d = locationListener;
    }

    public void run() {
        this.e.a.requestLocationUpdates(this.a, this.b, this.c, this.d);
    }
}
