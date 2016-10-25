package anet.channel;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.Utils;
import anet.channel.util.f;
import com.umeng.a;

// compiled from: Taobao
public class GlobalAppRuntimeInfo {
    private static final String TAG = "awcn.GlobalAppRuntimeInfo";
    private static volatile int amdcLimitLevel;
    private static volatile long amdcLimitTime;
    private static String appKey;
    private static String appSecret;
    private static String authCode;
    private static Context context;
    private static String currentProcess;
    private static ENV env;
    public static volatile boolean isBackground;
    public static String mConnToken;
    private static volatile f proxySetting;
    private static String targetProcess;
    public static int tnetPubkey;
    private static String ttid;
    private static String userId;
    private static String utdid;

    static {
        env = ENV.ONLINE;
        targetProcess = a.d;
        currentProcess = a.d;
        isBackground = true;
        mConnToken = null;
        amdcLimitLevel = 0;
        amdcLimitTime = 0;
        proxySetting = null;
        tnetPubkey = -1;
    }

    public static void setContext(Context context) {
        context = context;
        if (context != null) {
            currentProcess = Utils.getProcessName(context, Process.myPid());
            if (TextUtils.isEmpty(targetProcess)) {
                targetProcess = Utils.getMainProcessName(context);
            }
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void setTargetProcess(String str) {
        targetProcess = str;
    }

    public static boolean isTargetProcess() {
        return (TextUtils.isEmpty(targetProcess) || TextUtils.isEmpty(currentProcess)) ? true : targetProcess.equalsIgnoreCase(currentProcess);
    }

    public static String getCurrentProcess() {
        return currentProcess;
    }

    public static void setEnv(ENV env) {
        env = env;
    }

    public static ENV getEnv() {
        return env;
    }

    public static void setAuthCode(String str) {
        authCode = str;
    }

    public static String getAuthCode() {
        return authCode;
    }

    public static void setAppKey(String str) {
        appKey = str;
    }

    public static String getAppKey() {
        return appKey;
    }

    public static void setTtid(String str) {
        ttid = str;
    }

    public static String getTtid() {
        return ttid;
    }

    public static void setUserId(String str) {
        if (userId == null || !userId.equals(str)) {
            userId = str;
            StrategyCenter.getInstance().forceRefreshStrategy(n.a());
            AccsSessionManager.getInstance().checkAndStartAccsSession();
        }
    }

    public static String getUserId() {
        return userId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String str) {
        ALog.i(TAG, "setAppSecret", null, "secret", str);
        if (!TextUtils.isEmpty(str)) {
            appSecret = str;
        }
    }

    public static void setUtdid(String str) {
        if (utdid == null || !utdid.equals(str)) {
            utdid = str;
            StrategyCenter.getInstance().forceRefreshStrategy(n.a());
            AccsSessionManager.getInstance().checkAndStartAccsSession();
        }
    }

    public static String getUtdid() {
        return utdid;
    }

    public static void setBackground(boolean z) {
        isBackground = z;
    }

    public static boolean isAppBackground() {
        return context == null ? true : isBackground;
    }

    public static void setAmdcLimit(int i, int i2) {
        ALog.i(TAG, "set amdc limit", null, "level", Integer.valueOf(i), "time", Integer.valueOf(i2));
        if (amdcLimitLevel != i) {
            amdcLimitLevel = i;
            amdcLimitTime = System.currentTimeMillis() + (((long) i2) * 1000);
        }
    }

    public static int getAmdcLimitLevel() {
        if (amdcLimitLevel > 0 && System.currentTimeMillis() - amdcLimitTime > 0) {
            amdcLimitTime = 0;
            amdcLimitLevel = 0;
        }
        return amdcLimitLevel;
    }

    public static void setProxySetting(f fVar) {
        proxySetting = fVar;
    }

    public static f getProxySetting() {
        return proxySetting;
    }
}
