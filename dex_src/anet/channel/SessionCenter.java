package anet.channel;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.c.c;
import anet.channel.entity.ConnType;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.entity.ENV;
import anet.channel.session.AccsSession;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.a;
import anet.channel.util.i;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import java.net.ConnectException;
import java.security.InvalidParameterException;
import java.util.List;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

// compiled from: Taobao
public class SessionCenter implements INetworkStatusChangeListener {
    public static final boolean ALIYUN_OPEN = false;
    public static boolean SECURITYGUARD_OFF = false;
    public static final String TAG = "awcn.SessionCenter";
    private static Context mContext;
    private static AccsFrameCb mDataChannelCb;
    private static boolean mInit;
    long enterBackgroundTime;
    boolean foreGroundCheckRunning;

    // compiled from: Taobao
    private static class SingletonHolder {
        static SessionCenter instance;

        private SingletonHolder() {
        }

        static {
            instance = new SessionCenter();
        }
    }

    static {
        SECURITYGUARD_OFF = false;
        mInit = false;
    }

    public static synchronized void init(Context context, String str) {
        synchronized (SessionCenter.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    Context applicationContext = context.getApplicationContext();
                    mContext = applicationContext;
                    GlobalAppRuntimeInfo.setContext(applicationContext);
                    GlobalAppRuntimeInfo.setAppKey(str);
                    if (mInit) {
                        ALog.d(TAG, "SessionCenter has init", null, new Object[0]);
                    } else {
                        StrategyCenter.getInstance().initialize();
                        a.a();
                        NetworkStatusHelper.a(getInstance());
                        AppMonitor.getInstance().register();
                        mInit = true;
                        ALog.d(TAG, "init start", null, new Object[0]);
                    }
                }
            }
            ALog.e(TAG, "init failed,input param null or empty !", null, "context", context, Constants.SP_KEY_APPKEY, str);
            throw new RuntimeException("init failed,input param null or empty ");
        }
    }

    public static synchronized void init(Context context, String str, String str2) {
        synchronized (SessionCenter.class) {
            GlobalAppRuntimeInfo.setTtid(str2);
            init(context, str);
        }
    }

    public static synchronized void init(Context context, String str, String str2, String str3) {
        synchronized (SessionCenter.class) {
            if (context != null) {
                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str3))) {
                    GlobalAppRuntimeInfo.setTtid(str2);
                    GlobalAppRuntimeInfo.setAppSecret(str3);
                    init(context, str);
                }
            }
            ALog.e(TAG, "init failed,input param null or empty !", null, "context", context, Constants.SP_KEY_APPKEY, str, "secret", str3);
            throw new RuntimeException("init failed,input param null or empty ");
        }
    }

    private SessionCenter() {
        this.enterBackgroundTime = 0;
        this.foreGroundCheckRunning = false;
    }

    public synchronized void switchEnv(ENV env) {
        int i = 0;
        synchronized (this) {
            try {
                if (GlobalAppRuntimeInfo.getEnv() != env) {
                    ALog.i(TAG, "switch env", null, "old", GlobalAppRuntimeInfo.getEnv(), "new", env);
                    GlobalAppRuntimeInfo.setEnv(env);
                    StrategyCenter.getInstance().switchEnv();
                    SpdyAgent instance = SpdyAgent.getInstance(mContext, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
                    if (env != ENV.TEST) {
                        i = 1;
                    }
                    instance.switchAccsServer(i);
                    AccsSessionManager.getInstance().forceCloseSession(true);
                }
            } catch (Throwable th) {
                ALog.e(TAG, "switch env error.", null, th, new Object[0]);
            }
        }
    }

    public static SessionCenter getInstance() {
        return SingletonHolder.instance;
    }

    public Session getThrowsException(String str, long j) throws Exception {
        return getInternal(str, j);
    }

    public Session getThrowsException(String str, TypeLevel typeLevel, long j) throws Exception {
        return getInternal(str, typeLevel, j);
    }

    public Session get(String str, long j) {
        return get(str, null, j);
    }

    public Session get(String str, TypeLevel typeLevel, long j) {
        try {
            return getInternal(str, typeLevel, j);
        } catch (Throwable e) {
            ALog.e(TAG, "[Get]param url is invaild, return null", null, e, SocialConstants.PARAM_URL, str);
            return null;
        } catch (Throwable e2) {
            ALog.e(TAG, "[Get]get session timeout exception return null", null, e2, SocialConstants.PARAM_URL, str);
            return null;
        } catch (NoNetworkException e3) {
            ALog.e(TAG, "[Get]get session no network return null", null, SocialConstants.PARAM_URL, str);
            return null;
        } catch (NoAvailStrategyException e4) {
            ALog.e(TAG, "[Get]get session no strategy", null, SocialConstants.PARAM_URL, str);
            return null;
        } catch (Throwable e22) {
            ALog.e(TAG, "[Get]get session exception return null", null, e22, SocialConstants.PARAM_URL, str);
            return null;
        }
    }

    public boolean setDataReceiveCb(AccsFrameCb accsFrameCb) {
        ALog.i(TAG, "setDataReceiveCb", null, "AccsFrameCb", accsFrameCb);
        mDataChannelCb = accsFrameCb;
        updateAccsFrameCb(ConnType.H2_ACCS_0RTT);
        updateAccsFrameCb(ConnType.H2_ACCS_1RTT);
        updateAccsFrameCb(ConnType.ACCS_0RTT);
        updateAccsFrameCb(ConnType.ACCS_1RTT);
        return true;
    }

    public AccsFrameCb getDataChannelCb() {
        return mDataChannelCb;
    }

    private void updateAccsFrameCb(ConnType connType) {
        List a = a.a.a(connType);
        if (a != null) {
            ALog.i(TAG, new StringBuilder("sessions:").append(a.size()).toString(), null, new Object[0]);
            for (int i = 0; i < a.size(); i++) {
                Session session = (Session) a.get(i);
                if (session instanceof AccsSession) {
                    ((AccsSession) session).setFrameCb(mDataChannelCb);
                }
            }
            return;
        }
        ALog.i(TAG, "sessions:null", null, new Object[0]);
    }

    public void onNetworkStatusChanged(NetworkStatus networkStatus) {
        ALog.d(TAG, "onNetworkStatusChanged", null, "networkStatus", networkStatus);
        reCreateSession();
    }

    protected Session getInternal(String str, long j) throws Exception {
        return getInternal(str, null, j);
    }

    protected Session getInternal(String str, TypeLevel typeLevel, long j) throws Exception {
        if (mInit) {
            ALog.d(TAG, "getInternal", null, "u", str, "TypeClass", typeLevel, com.alipay.sdk.data.a.f, Long.valueOf(j));
            String[] parseURL = StringUtils.parseURL(str);
            if (parseURL == null) {
                throw new InvalidParameterException(new StringBuilder("param url invalid. url:").append(str).toString());
            }
            String schemeByHost = AwcnConfig.isSSLEnabled() ? StrategyCenter.getInstance().getSchemeByHost(parseURL[1]) : HttpConstant.HTTP;
            String cNameByHost = StrategyCenter.getInstance().getCNameByHost(parseURL[1]);
            SessionRequest a = SessionRequest.a(n.a(schemeByHost != null ? schemeByHost : parseURL[0], cNameByHost != null ? cNameByHost : parseURL[1]));
            Session a2 = a.a.a(a, typeLevel);
            if (a2 != null) {
                ALog.d(TAG, "get internal hit cache session", null, "session", a2);
                return a2;
            }
            a.a(mContext, typeLevel, i.a());
            if (j <= 0) {
                return a2;
            }
            a.a(j);
            a2 = a.a.a(a, typeLevel);
            if (a2 != null) {
                return a2;
            }
            throw new ConnectException();
        }
        ALog.e(TAG, "getInternal not inited!", null, new Object[0]);
        return null;
    }

    private void reCreateSession() {
        ALog.d(TAG, "[reCreateSession]", null, new Object[0]);
        List<SessionRequest> a = a.a.a();
        if (a.isEmpty()) {
            ALog.i(TAG, "recreate session failed: infos is empty", null, new Object[0]);
        } else {
            for (SessionRequest sessionRequest : a) {
                ALog.d(TAG, "network change, try re create ", null, com.umeng.a.d);
                sessionRequest.b(null);
            }
        }
        AccsSessionManager.getInstance().checkAndStartAccsSession();
    }

    @Deprecated
    public void enterBackground() {
        background();
    }

    @Deprecated
    public void enterForeground() {
        forground();
    }

    public void forground() {
        if (GlobalAppRuntimeInfo.isAppBackground()) {
            ALog.i(TAG, "[enterForeground]", null, new Object[0]);
            GlobalAppRuntimeInfo.setBackground(ALIYUN_OPEN);
            if (mContext != null && !this.foreGroundCheckRunning) {
                this.foreGroundCheckRunning = true;
                if (mInit) {
                    try {
                        c.a(new Runnable() {
                            public void run() {
                                try {
                                    if (SessionCenter.this.enterBackgroundTime == 0 || System.currentTimeMillis() - SessionCenter.this.enterBackgroundTime <= 300000) {
                                        AccsSessionManager.getInstance().checkAndStartAccsSession();
                                    } else {
                                        AccsSessionManager.getInstance().forceCloseSession(true);
                                    }
                                    SessionCenter.this.foreGroundCheckRunning = false;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    SessionCenter.this.foreGroundCheckRunning = false;
                                }
                            }
                        });
                        return;
                    } catch (Exception e) {
                    }
                }
                ALog.e(TAG, "enterForeground not inited!", null, new Object[0]);
            }
        }
    }

    public void background() {
        if (!GlobalAppRuntimeInfo.isAppBackground()) {
            ALog.d(TAG, "[enterBackground]", null, new Object[0]);
            GlobalAppRuntimeInfo.setBackground(true);
            this.enterBackgroundTime = System.currentTimeMillis();
            if (mInit) {
                try {
                    c.a(new Runnable() {
                        public void run() {
                            ALog.d(TAG, "horse serial ride start", null, new Object[0]);
                            b.a();
                        }
                    });
                    StrategyCenter.getInstance().saveData();
                    if ("OPPO".equalsIgnoreCase(Build.BRAND)) {
                        AccsSessionManager.getInstance().forceCloseSession(ALIYUN_OPEN);
                        return;
                    }
                    return;
                } catch (Exception e) {
                }
            }
            ALog.e(TAG, "enterBackground not inited!", null, new Object[0]);
        }
    }
}
