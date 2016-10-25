package com.inmobi.signals;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.commons.core.utilities.info.e;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class LocationInfo implements LocationListener {
    private static final String a;
    private static LocationInfo b;
    private static Object c;
    private static boolean d;
    private static LocationManager e;
    private static Object f;
    private static a g;
    private static boolean h;

    public enum LocationConsentStatus {
        AUTHORISED,
        DENIED,
        UNDETERMINED
    }

    private static class a implements InvocationHandler {
        private a() {
        }

        public void a(Bundle bundle) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Successfully connected to Google API client.");
            h = true;
        }

        public void a(int i) {
            h = false;
            Logger.a(InternalLogLevel.INTERNAL, a, "Google API client connection suspended");
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr != null) {
                if (method.getName().equals("onConnected")) {
                    a((Bundle) objArr[0]);
                    return null;
                } else if (method.getName().equals("onConnectionSuspended")) {
                    a(((Integer) objArr[0]).intValue());
                    return null;
                }
            }
            return method.invoke(this, objArr);
        }
    }

    static {
        a = LocationInfo.class.getSimpleName();
        c = new Object();
        d = false;
        f = null;
        g = null;
        h = false;
    }

    public static LocationInfo a() {
        LocationInfo locationInfo = b;
        if (locationInfo == null) {
            synchronized (c) {
                locationInfo = b;
                if (locationInfo == null) {
                    locationInfo = new LocationInfo();
                    b = locationInfo;
                }
            }
        }
        return locationInfo;
    }

    private LocationInfo() {
        e = (LocationManager) com.inmobi.commons.a.a.b().getSystemService(ShareActivity.KEY_LOCATION);
    }

    void b() {
        if (d && h.a()) {
            a(com.inmobi.commons.a.a.b());
        }
        d();
    }

    void c() {
        if (d && i() && k() && e != null) {
            e.removeUpdates(this);
        }
    }

    private boolean i() {
        return d.a("signals", "android.permission.ACCESS_FINE_LOCATION") || d.a("signals", "android.permission.ACCESS_COARSE_LOCATION");
    }

    public void d() {
        if (d && i() && k() && e != null) {
            Criteria criteria = new Criteria();
            criteria.setBearingAccuracy(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            criteria.setPowerRequirement(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            criteria.setCostAllowed(false);
            String bestProvider = e.getBestProvider(criteria, true);
            if (bestProvider != null) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Trying to get location fix. Provider being used:").append(bestProvider).toString());
                e.requestSingleUpdate(bestProvider, this, null);
                return;
            }
            Logger.a(InternalLogLevel.INTERNAL, a, "No enabled providers found matching the supplied criteria");
            Logger.a(InternalLogLevel.INTERNAL, a, "Skipping the location fix");
        }
    }

    public void onLocationChanged(Location location) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("location changed. ts:").append(location.getTime()).append(" lat:").append(location.getLatitude()).append(":").append(location.getLongitude()).append(" accu:").append(location.getAccuracy()).toString());
        if (i()) {
            e.removeUpdates(this);
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }

    private void a(Context context) {
        if (f == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Connecting Google API client for location.");
            g = new a();
            Object a = h.a(context, g, g, "com.google.android.gms.location.LocationServices");
            f = a;
            h.a(a);
        }
    }

    public synchronized HashMap<String, Object> e() {
        return a(l(), true);
    }

    public HashMap<String, String> f() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("loc-consent-status", j().toString().toLowerCase(Locale.ENGLISH));
        return hashMap;
    }

    private LocationConsentStatus j() {
        if (i()) {
            return k() ? LocationConsentStatus.AUTHORISED : LocationConsentStatus.DENIED;
        } else {
            return LocationConsentStatus.DENIED;
        }
    }

    public synchronized HashMap<String, String> g() {
        HashMap<String, String> hashMap;
        hashMap = new HashMap();
        Location l = l();
        HashMap a;
        if (l != null) {
            a = a(l, true);
        } else {
            a = a(e.f(), false);
        }
        for (Entry entry : r0.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }

    public void a(boolean z) {
        d = z;
    }

    @TargetApi(19)
    private boolean k() {
        Context b = com.inmobi.commons.a.a.b();
        if (VERSION.SDK_INT >= 19) {
            int i;
            try {
                i = Secure.getInt(b.getContentResolver(), "location_mode");
            } catch (SettingNotFoundException e) {
                e.printStackTrace();
                i = 0;
            }
            return i != 0;
        } else {
            if (e != null) {
                boolean z;
                if (d.a("signals", "android.permission.ACCESS_FINE_LOCATION")) {
                    boolean isProviderEnabled = e.isProviderEnabled("gps");
                    z = false;
                } else if (d.a("signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = e.isProviderEnabled("network");
                    r3 = null;
                } else {
                    z = false;
                    r3 = null;
                }
                if (z || r3) {
                    return true;
                }
            }
            return false;
        }
    }

    private Location l() {
        boolean z = true;
        Location location = null;
        if (d && k()) {
            if (h) {
                Location m = m();
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Location info provided by Google Api client:").append(m != null).toString());
                location = m;
            }
            if (location == null && e != null) {
                Criteria criteria = new Criteria();
                if (d.a("signals", "android.permission.ACCESS_FINE_LOCATION")) {
                    criteria.setAccuracy(1);
                } else if (d.a("signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                    criteria.setAccuracy(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
                criteria.setCostAllowed(false);
                String bestProvider = e.getBestProvider(criteria, true);
                if (bestProvider != null) {
                    try {
                        location = e.getLastKnownLocation(bestProvider);
                    } catch (SecurityException e) {
                        Logger.a(InternalLogLevel.INTERNAL, a, "Failed to acquire a location fix; access seems to be disabled");
                        Map hashMap = new HashMap();
                        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "SecurityException");
                        hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e.getMessage());
                        com.inmobi.commons.core.c.a.a().a("signals", "ExceptionCaught", hashMap);
                    }
                    if (location == null) {
                        location = n();
                    }
                }
                InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
                String str = a;
                StringBuilder stringBuilder = new StringBuilder("Location info provided by Location manager:");
                if (location == null) {
                    z = false;
                }
                Logger.a(internalLogLevel, str, stringBuilder.append(z).toString());
            }
        }
        if (location == null) {
            com.inmobi.commons.core.c.a.a().a(new com.inmobi.commons.core.c.e("signals", "LocationFixFailed"));
        }
        return location;
    }

    private Location m() {
        try {
            Field declaredField = Class.forName("com.google.android.gms.location.LocationServices").getDeclaredField("FusedLocationApi");
            Class forName = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
            return (Location) Class.forName("com.google.android.gms.location.FusedLocationProviderApi").getMethod("getLastLocation", new Class[]{forName}).invoke(declaredField.get(null), new Object[]{f});
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e);
            return null;
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e2);
            return null;
        } catch (Throwable e22) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e22);
            return null;
        } catch (Throwable e222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e222);
            return null;
        } catch (Throwable e2222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e2222);
            return null;
        }
    }

    private Location n() {
        if (e == null) {
            return null;
        }
        List providers = e.getProviders(true);
        int size = providers.size() - 1;
        Location location = null;
        while (size >= 0) {
            Location lastKnownLocation;
            String str = (String) providers.get(size);
            if (e.isProviderEnabled(str)) {
                try {
                    lastKnownLocation = e.getLastKnownLocation(str);
                } catch (SecurityException e) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Failed to acquire a location fix; access seems to be disabled");
                    Map hashMap = new HashMap();
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "SecurityException");
                    hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e.getMessage());
                    com.inmobi.commons.core.c.a.a().a("signals", "ExceptionCaught", hashMap);
                    lastKnownLocation = location;
                }
                if (lastKnownLocation != null) {
                    return lastKnownLocation;
                }
            } else {
                lastKnownLocation = location;
            }
            size--;
            location = lastKnownLocation;
        }
        return location;
    }

    private HashMap<String, Object> a(Location location, boolean z) {
        int i = 1;
        HashMap<String, Object> hashMap = new HashMap();
        if (location != null) {
            if (location.getTime() > 0) {
                hashMap.put("u-ll-ts", Long.valueOf(location.getTime()));
            }
            hashMap.put("u-latlong-accu", a(location));
            hashMap.put("sdk-collected", Integer.valueOf(z ? 1 : 0));
        }
        if (d) {
            String str = "loc-allowed";
            if (!k()) {
                i = 0;
            }
            hashMap.put(str, Integer.valueOf(i));
        }
        if (k() && i()) {
            if (d.a("signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                hashMap.put("loc-granularity", "coarse");
            }
            if (d.a("signals", "android.permission.ACCESS_FINE_LOCATION")) {
                hashMap.put("loc-granularity", "fine");
            }
        } else {
            hashMap.put("loc-granularity", IXAdSystemUtils.NT_NONE);
        }
        return hashMap;
    }

    private String a(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuilder.append(location.getLongitude());
        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        stringBuilder.append((int) location.getAccuracy());
        return stringBuilder.toString();
    }
}
