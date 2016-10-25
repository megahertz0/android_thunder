package anet.channel.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Pair;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Locale;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
class b {
    static Context a;
    static volatile NetworkStatus b;
    static volatile String c;
    static volatile String d;
    static volatile String e;
    static volatile String f;
    static volatile Pair<String, Integer> g;
    private static volatile boolean h;
    private static ConnectivityManager i;
    private static WifiManager j;
    private static BroadcastReceiver k;

    b() {
    }

    static {
        a = null;
        b = NetworkStatus.NONE;
        c = UtilityImpl.NET_TYPE_UNKNOWN;
        d = UtilityImpl.NET_TYPE_UNKNOWN;
        e = a.d;
        f = a.d;
        g = null;
        h = false;
        i = null;
        j = null;
        k = new NetworkStatusMonitor$1();
    }

    static void a() {
        if (!h && a != null) {
            synchronized (a) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                try {
                    a.registerReceiver(k, intentFilter);
                } catch (Exception e) {
                    ALog.e("awcn.NetworkStatusMonitor", "registerReceiver failed", null, new Object[0]);
                }
            }
            b(a);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(android.content.Context r12) {
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.status.b.b(android.content.Context):void");
        /*
        r11 = 1;
        r10 = 0;
        r9 = 0;
        r0 = "awcn.NetworkStatusMonitor";
        r1 = "[checkNetworkStatus]";
        r2 = new java.lang.Object[r9];
        anet.channel.util.ALog.d(r0, r1, r10, r2);
        r0 = b;
        r1 = d;
        if (r12 == 0) goto L_0x0082;
    L_0x0014:
        r2 = b();	 Catch:{ Exception -> 0x00e8 }
        if (r2 == 0) goto L_0x0020;
    L_0x001a:
        r3 = r2.isConnected();	 Catch:{ Exception -> 0x00e8 }
        if (r3 != 0) goto L_0x0083;
    L_0x0020:
        r2 = anet.channel.status.NetworkStatusHelper.NetworkStatus.NO;	 Catch:{ Exception -> 0x00e8 }
        b = r2;	 Catch:{ Exception -> 0x00e8 }
        r2 = "unknown";
        c = r2;	 Catch:{ Exception -> 0x00e8 }
        r2 = "awcn.NetworkStatusMonitor";
        r3 = "checkNetworkStatus";
        r4 = 0;
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x00e8 }
        r6 = 0;
        r7 = "NO NETWORK";
        r5[r6] = r7;	 Catch:{ Exception -> 0x00e8 }
        anet.channel.util.ALog.i(r2, r3, r4, r5);	 Catch:{ Exception -> 0x00e8 }
    L_0x003c:
        r2 = b;	 Catch:{ Exception -> 0x00e8 }
        if (r2 != r0) goto L_0x004c;
    L_0x0040:
        r2 = d;	 Catch:{ Exception -> 0x00e8 }
        if (r2 == 0) goto L_0x0082;
    L_0x0044:
        r2 = d;	 Catch:{ Exception -> 0x00e8 }
        r1 = r2.equalsIgnoreCase(r1);	 Catch:{ Exception -> 0x00e8 }
        if (r1 != 0) goto L_0x0082;
    L_0x004c:
        r1 = 2;
        r1 = anet.channel.util.ALog.isPrintLog(r1);	 Catch:{ Exception -> 0x00e8 }
        if (r1 == 0) goto L_0x007d;
    L_0x0053:
        r1 = "awcn.NetworkStatusMonitor";
        r2 = "Network Status Change";
        r3 = 0;
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x00e8 }
        r5 = 0;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e8 }
        r6.<init>();	 Catch:{ Exception -> 0x00e8 }
        r0 = r6.append(r0);	 Catch:{ Exception -> 0x00e8 }
        r6 = " ===>>> ";
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x00e8 }
        r6 = b;	 Catch:{ Exception -> 0x00e8 }
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x00e8 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00e8 }
        r4[r5] = r0;	 Catch:{ Exception -> 0x00e8 }
        anet.channel.util.ALog.i(r1, r2, r3, r4);	 Catch:{ Exception -> 0x00e8 }
    L_0x007d:
        r0 = b;	 Catch:{ Exception -> 0x00e8 }
        anet.channel.status.NetworkStatusHelper.a(r0);	 Catch:{ Exception -> 0x00e8 }
    L_0x0082:
        return;
    L_0x0083:
        r3 = "awcn.NetworkStatusMonitor";
        r4 = "checkNetworkStatus";
        r5 = 0;
        r6 = 4;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x00e8 }
        r7 = 0;
        r8 = "info.isConnected";
        r6[r7] = r8;	 Catch:{ Exception -> 0x00e8 }
        r7 = 1;
        r8 = r2.isConnected();	 Catch:{ Exception -> 0x00e8 }
        r8 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x00e8 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x00e8 }
        r7 = 2;
        r8 = "info.isAvailable";
        r6[r7] = r8;	 Catch:{ Exception -> 0x00e8 }
        r7 = 3;
        r8 = r2.isAvailable();	 Catch:{ Exception -> 0x00e8 }
        r8 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x00e8 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x00e8 }
        anet.channel.util.ALog.i(r3, r4, r5, r6);	 Catch:{ Exception -> 0x00e8 }
        r3 = r2.getType();	 Catch:{ Exception -> 0x00e8 }
        if (r3 != 0) goto L_0x00f5;
    L_0x00b8:
        r3 = r2.getSubtype();	 Catch:{ Exception -> 0x00e8 }
        r3 = a(r3);	 Catch:{ Exception -> 0x00e8 }
        b = r3;	 Catch:{ Exception -> 0x00e8 }
        r3 = r2.getSubtypeName();	 Catch:{ Exception -> 0x00e8 }
        c = r3;	 Catch:{ Exception -> 0x00e8 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x00e8 }
        if (r3 != 0) goto L_0x00dc;
    L_0x00ce:
        r3 = c;	 Catch:{ Exception -> 0x00e8 }
        r4 = " ";
        r5 = "";
        r3 = r3.replace(r4, r5);	 Catch:{ Exception -> 0x00e8 }
        c = r3;	 Catch:{ Exception -> 0x00e8 }
    L_0x00dc:
        r2 = r2.getExtraInfo();	 Catch:{ Exception -> 0x00e8 }
        r2 = a(r2);	 Catch:{ Exception -> 0x00e8 }
        d = r2;	 Catch:{ Exception -> 0x00e8 }
        goto L_0x003c;
    L_0x00e8:
        r0 = move-exception;
        r1 = "awcn.NetworkStatusMonitor";
        r2 = "checkNetworkStatus";
        r3 = new java.lang.Object[r9];
        anet.channel.util.ALog.e(r1, r2, r10, r0, r3);
        goto L_0x0082;
    L_0x00f5:
        r2 = r2.getType();	 Catch:{ Exception -> 0x00e8 }
        if (r2 != r11) goto L_0x003c;
    L_0x00fb:
        r2 = anet.channel.status.NetworkStatusHelper.NetworkStatus.WIFI;	 Catch:{ Exception -> 0x00e8 }
        b = r2;	 Catch:{ Exception -> 0x00e8 }
        r2 = "wifi";
        c = r2;	 Catch:{ Exception -> 0x00e8 }
        r2 = c();	 Catch:{ Exception -> 0x00e8 }
        if (r2 == 0) goto L_0x0116;
    L_0x010a:
        r3 = r2.getBSSID();	 Catch:{ Exception -> 0x00e8 }
        f = r3;	 Catch:{ Exception -> 0x00e8 }
        r2 = r2.getSSID();	 Catch:{ Exception -> 0x00e8 }
        e = r2;	 Catch:{ Exception -> 0x00e8 }
    L_0x0116:
        r2 = d();	 Catch:{ Exception -> 0x00e8 }
        g = r2;	 Catch:{ Exception -> 0x00e8 }
        goto L_0x003c;
        */
    }

    private static NetworkStatus a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case R.styleable.Toolbar_contentInsetLeft:
            case XZBDevice.Success:
                return NetworkStatus.G2;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
            case XZBDevice.Wait:
            case XZBDevice.Pause:
            case XZBDevice.Stop:
            case XZBDevice.Fail:
            case XZBDevice.Predownload:
            case XZBDevice.Delete:
                return NetworkStatus.G3;
            case XZBDevice.Upload:
                return NetworkStatus.G4;
            default:
                return NetworkStatus.NONE;
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return UtilityImpl.NET_TYPE_UNKNOWN;
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        if (toLowerCase.contains("cmwap")) {
            return "cmwap";
        }
        if (toLowerCase.contains("uniwap")) {
            return "uniwap";
        }
        if (toLowerCase.contains("3gwap")) {
            return "3gwap";
        }
        if (toLowerCase.contains("ctwap")) {
            return "ctwap";
        }
        if (toLowerCase.contains("cmnet")) {
            return "cmnet";
        }
        if (toLowerCase.contains("uninet")) {
            return "uninet";
        }
        if (toLowerCase.contains("3gnet")) {
            return "3gnet";
        }
        return toLowerCase.contains("ctnet") ? "ctnet" : UtilityImpl.NET_TYPE_UNKNOWN;
    }

    static NetworkInfo b() {
        try {
            if (i == null) {
                i = (ConnectivityManager) a.getSystemService("connectivity");
            }
            return i.getActiveNetworkInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getNetworkInfo", null, th, new Object[0]);
            return null;
        }
    }

    private static WifiInfo c() {
        try {
            if (j == null) {
                j = (WifiManager) a.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            }
            return j.getConnectionInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getWifiInfo", null, th, new Object[0]);
            return null;
        }
    }

    private static Pair<String, Integer> d() {
        try {
            CharSequence property = System.getProperty("http.proxyHost");
            if (!TextUtils.isEmpty(property)) {
                return Pair.create(property, Integer.valueOf(Integer.parseInt(System.getProperty("http.proxyPort"))));
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
