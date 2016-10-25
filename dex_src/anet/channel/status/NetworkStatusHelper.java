package anet.channel.status;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Pair;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.c.c;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.umeng.a;
import java.util.concurrent.CopyOnWriteArraySet;

// compiled from: Taobao
public class NetworkStatusHelper {
    private static CopyOnWriteArraySet<INetworkStatusChangeListener> a;

    // compiled from: Taobao
    public static interface INetworkStatusChangeListener {
        void onNetworkStatusChanged(NetworkStatus networkStatus);
    }

    // compiled from: Taobao
    public enum NetworkStatus {
        NONE,
        NO,
        G2,
        G3,
        G4,
        WIFI;

        public final boolean isMobile() {
            return this == G2 || this == G3 || this == G4;
        }

        public final boolean isWifi() {
            return this == WIFI;
        }

        public final String getType() {
            if (this == G2) {
                return "2G";
            }
            if (this == G3) {
                return "3G";
            }
            return this == G4 ? "4G" : toString();
        }
    }

    static {
        a = new CopyOnWriteArraySet();
    }

    public static synchronized void a(Context context) {
        synchronized (NetworkStatusHelper.class) {
            b.a = context;
            b.a();
        }
    }

    public static void a(INetworkStatusChangeListener iNetworkStatusChangeListener) {
        a.add(iNetworkStatusChangeListener);
    }

    static void a(NetworkStatus networkStatus) {
        c.a(new a(networkStatus));
    }

    public static NetworkStatus a() {
        return b.b;
    }

    public static String b() {
        return b.c;
    }

    public static String c() {
        return b.d;
    }

    public static String d() {
        return b.f;
    }

    public static boolean e() {
        if (b.b != NetworkStatus.NO) {
            return true;
        }
        NetworkInfo b = b.b();
        return b != null && b.isConnected();
    }

    public static boolean f() {
        NetworkStatus networkStatus = b.b;
        return (networkStatus == NetworkStatus.WIFI && b.g != null) || (networkStatus.isMobile() && (b.d.contains("wap") || GlobalAppRuntimeInfo.getProxySetting() != null));
    }

    public static String g() {
        NetworkStatus networkStatus = b.b;
        if (networkStatus == NetworkStatus.WIFI && b.g != null) {
            return "proxy";
        }
        if (networkStatus.isMobile() && b.d.contains("wap")) {
            return "wap";
        }
        return (!networkStatus.isMobile() || GlobalAppRuntimeInfo.getProxySetting() == null) ? a.d : BaseMonitor.ALARM_POINT_AUTH;
    }

    public static Pair<String, Integer> h() {
        return b.g;
    }

    public static void i() {
        try {
            NetworkStatus networkStatus = b.b;
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("\n\nNetwork detail***********************\n");
            stringBuilder.append("status: ").append(networkStatus.getType()).append('\n');
            if (networkStatus.isMobile()) {
                stringBuilder.append(" apn: ").append(b.d).append('\n');
            } else {
                stringBuilder.append(" BSSID: ").append(b.f).append('\n');
                stringBuilder.append(" SSID: ").append(b.e).append('\n');
            }
            if (f()) {
                stringBuilder.append(" proxy: ").append(g()).append('\n');
                Pair pair = b.g;
                if (pair != null) {
                    stringBuilder.append(" proxyHost: ").append((String) pair.first).append('\n');
                    stringBuilder.append(" proxyPort: ").append(pair.second).append('\n');
                }
            }
            stringBuilder.append("******************************************");
            ALog.i("awcn.NetworkStatusHelper", stringBuilder.toString(), null, new Object[0]);
        } catch (Exception e) {
        }
    }
}
