package com.qq.e.comm.managers.status;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import com.umeng.a;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Locale;
import org.android.spdy.SpdyAgent;

public class DeviceStatus {
    private String a;
    private String b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private volatile String k;
    private volatile String l;
    private volatile float m;
    public final String model;
    private Context n;

    class AnonymousClass_1 implements LocationListener {
        private /* synthetic */ LocationManager a;

        AnonymousClass_1(LocationManager locationManager) {
            this.a = locationManager;
        }

        public void onLocationChanged(Location location) {
            try {
                DeviceStatus.this.k = location.getLatitude();
                DeviceStatus.this.l = location.getLongitude();
                this.a.removeUpdates(this);
            } catch (Throwable th) {
            }
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    public DeviceStatus(Context context) {
        this.model = Build.MODEL;
        this.n = context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.e = getVersion() > 3 ? displayMetrics.densityDpi : 120;
        this.c = getVersion() > 3 ? a(displayMetrics.density, displayMetrics.widthPixels) : displayMetrics.widthPixels;
        this.d = getVersion() > 3 ? a(displayMetrics.density, displayMetrics.heightPixels) : displayMetrics.heightPixels;
        try {
            LocationManager locationManager = (LocationManager) this.n.getSystemService(ShareActivity.KEY_LOCATION);
            if (locationManager != null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(1);
                try {
                    String bestProvider = locationManager.getBestProvider(criteria, true);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                    if (lastKnownLocation != null) {
                        this.k = lastKnownLocation.getLatitude();
                        this.l = lastKnownLocation.getLongitude();
                        this.m = lastKnownLocation.getAccuracy();
                        return;
                    }
                    try {
                        locationManager.requestLocationUpdates(bestProvider, 2000, 7000.0f, new AnonymousClass_1(locationManager));
                    } catch (Throwable th) {
                    }
                } catch (Throwable th2) {
                }
            }
        } catch (Throwable th3) {
        }
    }

    private int a(float f, int i) {
        return (this.n.getApplicationInfo().flags & 8192) != 0 ? (int) (((float) i) / f) : i;
    }

    public Carrier getCarrier() {
        String operator = getOperator();
        if (operator == null || (operator.equals("46000") || operator.equals("46002") || operator.equals("46007") || operator.equals("46020"))) {
            return Carrier.CMCC;
        }
        if (operator.equals("46001") || operator.equals("46006")) {
            return Carrier.UNICOM;
        }
        if (operator.equals("46003") || operator.equals("46005")) {
            return Carrier.TELECOM;
        }
        return Carrier.UNKNOWN;
    }

    public String getDataNet() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.n.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return null;
        }
        String str;
        switch (activeNetworkInfo.getType()) {
            case SpdyAgent.ACCS_TEST_SERVER:
                str = "ed";
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str = IXAdRequestInfo.WIFI;
                break;
            default:
                str = "unknow";
                break;
        }
        this.i = str;
        return this.i;
    }

    public int getDeviceDensity() {
        return this.e;
    }

    public int getDeviceHeight() {
        return this.d;
    }

    public int getDeviceWidth() {
        return this.c;
    }

    public String getDid() {
        String plainDid = getPlainDid();
        return StringUtil.isEmpty(plainDid) ? a.d : Md5Util.encode(plainDid);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> getLacAndCeilId() {
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.status.DeviceStatus.getLacAndCeilId():java.util.Map<java.lang.String, java.lang.String>");
        /*
        this = this;
        r5 = 3;
        r1 = 0;
        r0 = r6.getOperator();
        r2 = new java.util.HashMap;
        r2.<init>();
        r3 = com.qq.e.comm.util.StringUtil.isEmpty(r0);
        if (r3 != 0) goto L_0x0063;
    L_0x0011:
        r3 = "null";
        r3 = r3.equalsIgnoreCase(r0);
        if (r3 != 0) goto L_0x0063;
    L_0x001a:
        r3 = 0;
        r4 = 3;
        r3 = r0.substring(r3, r4);	 Catch:{ Throwable -> 0x0075 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Throwable -> 0x0075 }
        r4 = 3;
        r0 = r0.substring(r4);	 Catch:{ Throwable -> 0x0075 }
        r4 = java.lang.Integer.parseInt(r0);	 Catch:{ Throwable -> 0x0075 }
        r0 = 460; // 0x1cc float:6.45E-43 double:2.273E-321;
        if (r3 != r0) goto L_0x0063;
    L_0x0031:
        r0 = r6.n;	 Catch:{ Throwable -> 0x0075 }
        r3 = "phone";
        r0 = r0.getSystemService(r3);	 Catch:{ Throwable -> 0x0075 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Throwable -> 0x0075 }
        if (r4 == r5) goto L_0x0041;
    L_0x003e:
        r3 = 5;
        if (r4 != r3) goto L_0x0064;
    L_0x0041:
        r0 = r0.getCellLocation();	 Catch:{ Throwable -> 0x0075 }
        r0 = (android.telephony.cdma.CdmaCellLocation) r0;	 Catch:{ Throwable -> 0x0075 }
        r1 = r0.getNetworkId();	 Catch:{ Throwable -> 0x0075 }
        r0 = r0.getBaseStationId();	 Catch:{ Throwable -> 0x0075 }
    L_0x004f:
        r3 = "lac";
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x0075 }
        r2.put(r3, r1);	 Catch:{ Throwable -> 0x0075 }
        r1 = "cellid";
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0075 }
        r2.put(r1, r0);	 Catch:{ Throwable -> 0x0075 }
    L_0x0063:
        return r2;
    L_0x0064:
        r0 = r0.getCellLocation();	 Catch:{ Throwable -> 0x0075 }
        r0 = (android.telephony.gsm.GsmCellLocation) r0;	 Catch:{ Throwable -> 0x0075 }
        if (r0 == 0) goto L_0x007a;
    L_0x006c:
        r1 = r0.getLac();	 Catch:{ Throwable -> 0x0075 }
        r0 = r0.getCid();	 Catch:{ Throwable -> 0x0075 }
        goto L_0x004f;
    L_0x0075:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0063;
    L_0x007a:
        r0 = r1;
        goto L_0x004f;
        */
    }

    public String getLanguage() {
        if (this.b == null) {
            this.b = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
            if (this.b.length() == 0) {
                this.b = "en";
            }
        }
        return this.b;
    }

    public String getLat() {
        return this.k;
    }

    public String getLng() {
        return this.l;
    }

    public float getLocationAccuracy() {
        return this.m;
    }

    public NetworkType getNetworkType() {
        String dataNet = getDataNet();
        try {
            int parseInt = Integer.parseInt(getPhoneNet());
        } catch (NumberFormatException e) {
            parseInt = 0;
        }
        if (dataNet != null && dataNet.equals(IXAdRequestInfo.WIFI)) {
            return NetworkType.WIFI;
        }
        switch (parseInt) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return NetworkType.NET_2G;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
            case R.styleable.Toolbar_contentInsetLeft:
            case XZBDevice.Wait:
            case XZBDevice.Pause:
            case XZBDevice.Stop:
            case XZBDevice.Success:
            case XZBDevice.Fail:
            case XZBDevice.Predownload:
                return NetworkType.NET_3G;
            case XZBDevice.Upload:
            case XZBDevice.Delete:
                return NetworkType.NET_4G;
            default:
                return NetworkType.UNKNOWN;
        }
    }

    public String getOperator() {
        try {
            this.g = ((TelephonyManager) this.n.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
        }
        return this.g;
    }

    public String getPhoneNet() {
        try {
            this.h = ((TelephonyManager) this.n.getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
        }
        return this.h;
    }

    public String getPlainDid() {
        if (!StringUtil.isEmpty(this.j)) {
            return this.j;
        }
        try {
            this.j = ((TelephonyManager) this.n.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            GDTLogger.d(new StringBuilder("Get imei encounter error: ").append(e.getMessage()).toString());
        }
        return StringUtil.isEmpty(this.j) ? a.d : this.j;
    }

    public String getScreenOrientation() {
        if (this.n.getResources().getConfiguration().orientation == 2) {
            this.f = "l";
        } else if (this.n.getResources().getConfiguration().orientation == 1) {
            this.f = "p";
        }
        return this.f;
    }

    public String getUid() {
        if (this.a == null) {
            String string = Secure.getString(this.n.getContentResolver(), "android_id");
            this.a = string == null ? Md5Util.encode("emulator") : Md5Util.encode(string);
        }
        return this.a;
    }

    public int getVersion() {
        try {
            return VERSION.SDK_INT;
        } catch (Exception e) {
            return XZBDevice.DOWNLOAD_LIST_FAILED;
        }
    }
}
