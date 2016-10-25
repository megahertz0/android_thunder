package anet.channel;

import android.text.TextUtils;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import com.taobao.accs.internal.b;
import java.util.Arrays;

// compiled from: Taobao
public class AccsSessionManager {
    private static final String TAG = "awcn.AccsSessionManager";
    public volatile Callback cb;
    public SessionCenter sessionCenter;
    public String[] sessionKeyArray;

    // compiled from: Taobao
    public static interface Callback {
        int getSessionCount();

        String getSessionKey(int i);
    }

    // compiled from: Taobao
    static class a {
        static AccsSessionManager a;

        a() {
        }

        static {
            a = new AccsSessionManager();
        }
    }

    public static AccsSessionManager getInstance() {
        return a.a;
    }

    private AccsSessionManager() {
        this.sessionCenter = SessionCenter.getInstance();
        this.cb = null;
        this.sessionKeyArray = new String[0];
        if (GlobalAppRuntimeInfo.isTargetProcess()) {
            this.cb = new a(this);
        }
    }

    public void setCallback(Callback callback) {
        this.cb = callback;
    }

    public synchronized void checkAndStartAccsSession() {
        if (this.cb == null) {
            ALog.i(TAG, "call back is null", null, new Object[0]);
        } else {
            int sessionCount = this.cb.getSessionCount();
            if (this.sessionKeyArray.length != sessionCount) {
                this.sessionKeyArray = (String[]) Arrays.copyOf(this.sessionKeyArray, sessionCount);
            }
            boolean isNeedCheckSession = isNeedCheckSession();
            for (sessionCount = 0; sessionCount < this.sessionKeyArray.length; sessionCount++) {
                String str = this.sessionKeyArray[sessionCount];
                Object sessionKey = this.cb.getSessionKey(sessionCount);
                if ((sessionKey == null && str != null) || !(sessionKey == null || sessionKey.equalsIgnoreCase(str))) {
                    closeSessions(str);
                    this.sessionKeyArray[sessionCount] = sessionKey;
                }
                if (isNeedCheckSession) {
                    try {
                        if (!TextUtils.isEmpty(sessionKey)) {
                            this.sessionCenter.getInternal(sessionKey, TypeLevel.SPDY, 0);
                        }
                    } catch (Exception e) {
                        ALog.e("start unit session failed", null, b.ELECTION_KEY_HOST, sessionKey);
                    }
                }
            }
        }
    }

    public synchronized void forceReCreateSession() {
        forceCloseSession(true);
    }

    public synchronized void forceCloseSession(boolean z) {
        int i = 0;
        synchronized (this) {
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, "forceCloseSession", null, "reCreate", Boolean.valueOf(z));
            }
            while (i < this.sessionKeyArray.length) {
                closeSessions(this.sessionKeyArray[i]);
                this.sessionKeyArray[i] = null;
                i++;
            }
            if (z) {
                checkAndStartAccsSession();
            }
        }
    }

    private boolean isNeedCheckSession() {
        if (GlobalAppRuntimeInfo.isAppBackground()) {
            ALog.d(TAG, "app is background not need check accs session, return", null, "bg", Boolean.valueOf(true));
            return false;
        } else if (NetworkStatusHelper.e()) {
            return true;
        } else {
            ALog.d(TAG, "network is not available, not need check accs session, return", null, "network", Boolean.valueOf(NetworkStatusHelper.e()));
            return false;
        }
    }

    private void closeSessions(String str) {
        if (!TextUtils.isEmpty(str)) {
            ALog.d(TAG, "closeSessions!!!!!!", null, b.ELECTION_KEY_HOST, str);
            SessionRequest.a(str).a(false);
        }
    }
}
