package com.baidu.mobads.production.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.webkit.CookieManager;
import com.alipay.sdk.util.h;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.j.m;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.stat.DeviceInfo;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Set;

public class b {
    private Set<String> a;
    private Set<String> b;
    private Set<String> c;
    private CookieManager d;
    private IXAdSystemUtils e;
    private IXAdCommonUtils f;
    private Context g;
    private int h;
    private String i;

    public b(Context context, int i, String str) {
        this.e = m.a().n();
        this.f = m.a().m();
        this.g = context;
        this.h = i;
        this.i = str;
        this.d = CookieManager.getInstance();
        this.d.setAcceptCookie(true);
        b();
    }

    public String a() {
        c();
        return new StringBuilder("http://cpu.baidu.com/").append(this.h).append("/").append(this.i).toString();
    }

    private void b() {
        this.a = new HashSet();
        this.a.add("46000");
        this.a.add("46002");
        this.a.add("46007");
        this.b = new HashSet();
        this.b.add("46001");
        this.b.add("46006");
        this.c = new HashSet();
        this.c.add("46003");
        this.c.add("46005");
    }

    private void c() {
        Object obj;
        int e;
        Object obj2 = null;
        int i = 0;
        Rect screenRect = this.f.getScreenRect(this.g);
        int height = screenRect.height();
        int width = screenRect.width();
        boolean d = d();
        if (d) {
            String a = a(g());
        } else {
            obj = null;
        }
        if (d) {
            e = e();
        } else {
            e = 0;
        }
        if (d) {
            obj2 = f();
        }
        if (d) {
            i = 1;
        }
        String cuid = this.e.getCUID(this.g);
        a(IXAdRequestInfo.V, h());
        a(IXAdRequestInfo.IMSI, this.e.getIMEI(this.g));
        a(DeviceInfo.TAG_ANDROID_ID, this.e.getAndroidId(this.g));
        a("m", a(this.e.getMacAddress(this.g)));
        a("cuid", cuid);
        a("ct", Integer.valueOf(a.a(this.g)));
        a("oi", Integer.valueOf(i()));
        a("src", Integer.valueOf(1));
        a(IXAdRequestInfo.HEIGHT, Integer.valueOf(height));
        a(IXAdRequestInfo.WIDTH, Integer.valueOf(width));
        a("apm", obj);
        a("rssi", Integer.valueOf(e));
        a("apn", obj2);
        a("isc", Integer.valueOf(i));
    }

    private void a(String str, Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("=");
        stringBuffer.append(obj);
        stringBuffer.append(h.b);
        this.d.setCookie("http://cpu.baidu.com/", stringBuffer.toString());
    }

    private boolean d() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.g.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private int e() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.g.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            return connectionInfo == null ? 0 : connectionInfo.getRssi();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private String f() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.g.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            String ssid = connectionInfo == null ? a.d : connectionInfo.getSSID();
            if (ssid.startsWith(h.f) && ssid.endsWith(h.f)) {
                return ssid.substring(1, ssid.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a.d;
    }

    private String g() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.g.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            return connectionInfo == null ? null : connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return a.d;
        }
    }

    private String h() {
        try {
            PackageInfo packageInfo = this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0);
            String str = packageInfo == null ? null : packageInfo.versionName;
            return str != null ? str.replace(".", SocializeConstants.OP_DIVIDER_MINUS) : null;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int i() {
        String networkOperator = this.e.getNetworkOperator(this.g);
        if (networkOperator == null) {
            return 0;
        }
        if (this.a.contains(networkOperator)) {
            return 1;
        }
        if (this.c.contains(networkOperator)) {
            return XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        return this.b.contains(networkOperator) ? XZBDevice.DOWNLOAD_LIST_FAILED : R.styleable.AppCompatTheme_autoCompleteTextViewStyle;
    }

    private String a(String str) {
        return str == null ? null : str.replace(":", SocializeConstants.OP_DIVIDER_MINUS);
    }
}
