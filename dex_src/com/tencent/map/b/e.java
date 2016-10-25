package com.tencent.map.b;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import com.tencent.map.b.e.b;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

public final class e {
    private static LocationManager b;
    private static float d;
    private Context a;
    private a c;
    private c e;
    private b f;
    private boolean g;
    private byte[] h;
    private int i;
    private long j;
    private boolean k;
    private int l;
    private int m;

    class a implements Listener, LocationListener {
        private a() {
        }

        public final void onGpsStatusChanged(int i) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    e.a(e.this, 1);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    e.a(e.this, 0);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    e.a(e.this, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    break;
            }
            e.this.b();
        }

        public final void onLocationChanged(Location location) {
            Object obj = null;
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (latitude != 29.999998211860657d && longitude != 103.99999916553497d && Math.abs(latitude) >= 1.0E-8d && Math.abs(longitude) >= 1.0E-8d && latitude >= -90.0d && latitude <= 90.0d && longitude >= -180.0d && longitude <= 180.0d) {
                    obj = 1;
                }
                if (obj != null) {
                    e.this.j = System.currentTimeMillis();
                    e.this.b();
                    e.a(e.this, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    e.this.f = new b(e.this, location, e.this.l, e.this.m, e.this.i, e.this.j);
                    if (e.this.e != null) {
                        e.this.e.a(e.this.f);
                    }
                }
            }
        }

        public final void onProviderDisabled(String str) {
            if (str != null) {
                try {
                    if (str.equals("gps")) {
                        e.this.l = e.this.m = 0;
                        e.this.i = 0;
                        if (e.this.e != null) {
                            e.this.e.a(e.this.i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        public final void onProviderEnabled(String str) {
            if (str != null) {
                try {
                    if (str.equals("gps")) {
                        e.this.i = XZBDevice.DOWNLOAD_LIST_ALL;
                        if (e.this.e != null) {
                            e.this.e.a(e.this.i);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    public class b implements Cloneable {
        private Location a;
        private long b;
        private int c;

        public b(e eVar, Location location, int i, int i2, int i3, long j) {
            this.a = null;
            this.b = 0;
            this.c = 0;
            if (location != null) {
                this.a = new Location(location);
                this.c = i2;
                this.b = j;
            }
        }

        public final boolean a() {
            return this.a == null ? false : (this.c <= 0 || this.c >= 3) && System.currentTimeMillis() - this.b <= 30000;
        }

        public final Location b() {
            return this.a;
        }

        public final Object clone() {
            com.tencent.map.b.e.b bVar;
            try {
                bVar = (com.tencent.map.b.e.b) super.clone();
            } catch (Exception e) {
                bVar = null;
            }
            if (this.a != null) {
                bVar.a = new Location(this.a);
            }
            return bVar;
        }
    }

    public static interface c {
        void a(int i);

        void a(b bVar);
    }

    static {
        b = null;
        d = 0.0f;
    }

    public e() {
        this.a = null;
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = new byte[0];
        this.i = 1024;
        this.j = 0;
        this.k = false;
        this.l = 0;
        this.m = 0;
    }

    static /* synthetic */ int a(e eVar, int i) {
        int i2 = eVar.i | i;
        eVar.i = i2;
        return i2;
    }

    private void b() {
        this.m = 0;
        this.l = 0;
        GpsStatus gpsStatus = b.getGpsStatus(null);
        if (gpsStatus != null) {
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator it = gpsStatus.getSatellites().iterator();
            if (it != null) {
                while (it.hasNext() && this.l <= maxSatellites) {
                    this.l++;
                    if (((GpsSatellite) it.next()).usedInFix()) {
                        this.m++;
                    }
                }
            }
        }
    }

    public final void a() {
        synchronized (this.h) {
            if (this.g) {
                if (!(b == null || this.c == null)) {
                    b.removeGpsStatusListener(this.c);
                    b.removeUpdates(this.c);
                }
                this.g = false;
                return;
            }
        }
    }

    public final boolean a(c cVar, Context context) {
        synchronized (this.h) {
            if (this.g) {
                return true;
            } else if (context == null || cVar == null) {
                return false;
            } else {
                this.a = context;
                this.e = cVar;
                try {
                    b = (LocationManager) this.a.getSystemService(ShareActivity.KEY_LOCATION);
                    this.c = new a();
                    if (b == null || this.c == null) {
                        return false;
                    }
                    try {
                        b.requestLocationUpdates("gps", 1000, AutoScrollHelper.RELATIVE_UNSPECIFIED, this.c);
                        b.addGpsStatusListener(this.c);
                        if (b.isProviderEnabled("gps")) {
                            this.i = 4;
                        } else {
                            this.i = 0;
                        }
                        this.g = true;
                        return this.g;
                    } catch (Exception e) {
                        return false;
                    }
                } catch (Exception e2) {
                    return false;
                }
            }
        }
    }
}
