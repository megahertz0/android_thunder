package anet.channel;

import android.text.TextUtils;
import anet.channel.AccsSessionManager.Callback;
import anet.channel.heartbeat.HeartbeatManager;
import anet.channel.heartbeat.IHeartbeatFactory;
import anet.channel.security.b;
import anet.channel.security.c;
import anet.channel.strategy.n;
import anet.channel.util.ALog;

// compiled from: Taobao
public class AwcnConfig {
    public static final String TAG = "AwcnConfig";
    private static boolean isAccsSessionAutoRecreate;
    private static boolean isSSLEnabled;

    static {
        isAccsSessionAutoRecreate = true;
        isSSLEnabled = true;
    }

    public static void setSecurityGuardOff(boolean z) {
        ALog.i(TAG, "setSecurityGuardOff", null, "off", Boolean.valueOf(z));
        SessionCenter.SECURITYGUARD_OFF = z;
        if (z) {
            c.a(new b());
        } else {
            c.a(null);
        }
    }

    public static void setTnetPublicKey(int i) {
        ALog.i(TAG, "setTnetPublicKey", null, "pubkey", Integer.valueOf(i));
        if (i > 0) {
            GlobalAppRuntimeInfo.tnetPubkey = i;
        }
    }

    public static boolean isAccsSessionAutoRecreate() {
        return isAccsSessionAutoRecreate;
    }

    @Deprecated
    public static void setSessionAutoRecreate(boolean z) {
        isAccsSessionAutoRecreate = z;
    }

    public static void setAccsSessionAutoRecreate(boolean z) {
        isAccsSessionAutoRecreate = z;
    }

    public static void setHeartbeatFactory(IHeartbeatFactory iHeartbeatFactory) {
        HeartbeatManager.setHeartbeatFactory(iHeartbeatFactory);
    }

    @Deprecated
    public static void setCallback(Callback callback) {
        AccsSessionManager.getInstance().setCallback(callback);
    }

    public static void setAccsSessionCb(Callback callback) {
        AccsSessionManager.getInstance().setCallback(callback);
    }

    public static void setAccsCenterHosts(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ALog.e(TAG, "setAccsCenterHost null", null, new Object[0]);
            return;
        }
        ALog.i(TAG, "setAccsCenterHosts", "releaseHost", str, "prepareHost", str2, "dailyHost", str3);
        n.a = new String[]{str, str2, str3};
    }

    public static void setAccsCenterIps(String[] strArr, String[] strArr2, String[] strArr3) {
        if (strArr == null || strArr2 == null || strArr3 == null) {
            ALog.e(TAG, "setAccsCenterIps null", null, new Object[0]);
            return;
        }
        ALog.i(TAG, "setAccsCenterIps", null, "releaseIp", strArr, "prepareIp", strArr2, "dailyIp", strArr3);
        n.b = new String[][]{strArr, strArr2, strArr3};
    }

    public static void setUnitHost(String str) {
        if (TextUtils.isEmpty(str)) {
            ALog.e(TAG, "setUnitHost null", null, new Object[0]);
            return;
        }
        ALog.i(TAG, "setUnitHost", null, "unitHost", str);
        n.c = str;
    }

    public static void setUnitIp(String[] strArr) {
        if (strArr == null) {
            ALog.e(TAG, "setUnitIp null", null, new Object[0]);
            return;
        }
        ALog.i(TAG, "setUnitIp", null, "ips", strArr);
        n.d = strArr;
    }

    public static void setUnszHost(String str) {
        if (TextUtils.isEmpty(str)) {
            ALog.e(TAG, "setUnszHost null", null, new Object[0]);
            return;
        }
        ALog.i(TAG, "setUnszHost", null, "unszHost", str);
        n.e = str;
    }

    public static void setUnszIp(String[] strArr) {
        if (strArr == null) {
            ALog.e(TAG, "setUnszIp null", null, new Object[0]);
            return;
        }
        ALog.i(TAG, "setUnszIp", null, "setUnszIp", strArr);
        n.f = strArr;
    }

    public static boolean isSSLEnabled() {
        return isSSLEnabled;
    }

    public static void setSSLEnabled(boolean z) {
        isSSLEnabled = z;
    }
}
