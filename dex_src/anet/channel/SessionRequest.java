package anet.channel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.c.c;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.entity.EventType;
import anet.channel.session.AccsSession;
import anet.channel.session.e;
import anet.channel.statist.AlarmObject;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.i;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.android.agoo.intent.IntentUtil;

// compiled from: Taobao
public class SessionRequest {
    private static Map<String, SessionRequest> c;
    volatile boolean a;
    volatile Session b;
    private String d;
    private volatile Future e;
    private Object f;

    // compiled from: Taobao
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[EventType.values().length];
            try {
                a[EventType.AUTH_SUCC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EventType.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EventType.CONNECT_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    // compiled from: Taobao
    private static interface IConnCb {
        void onDisConnect(Session session, long j, EventType eventType);

        void onFailed(Session session, long j, EventType eventType, int i);

        void onSuccess(Session session, long j);
    }

    // compiled from: Taobao
    class a implements IConnCb {
        boolean a;
        private Context c;
        private List<anet.channel.entity.a> d;
        private anet.channel.entity.a e;

        a(Context context, List<anet.channel.entity.a> list, anet.channel.entity.a aVar) {
            this.a = false;
            this.c = context;
            this.d = list;
            this.e = aVar;
        }

        public void onFailed(Session session, long j, EventType eventType, int i) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "Connect failed", this.e.i(), "session", session, com.taobao.accs.internal.b.ELECTION_KEY_HOST, SessionRequest.this.a(), "isHandleFinish", Boolean.valueOf(this.a));
            }
            if (!this.a) {
                this.a = true;
                a.a.b(SessionRequest.this, session);
                if (!session.tryNextWhenFail) {
                    return;
                }
                if (!NetworkStatusHelper.e()) {
                    SessionRequest.this.b();
                } else if (this.d.size() > 0) {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.SessionRequest", "use next strategy to create session", this.e.i(), com.taobao.accs.internal.b.ELECTION_KEY_HOST, SessionRequest.this.a());
                    }
                    anet.channel.entity.a aVar = (anet.channel.entity.a) this.d.remove(0);
                    SessionRequest.this.a(this.c, aVar, new a(this.c, this.d, aVar), aVar.i());
                } else {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.SessionRequest", "strategy has used up,finish", this.e.i(), com.taobao.accs.internal.b.ELECTION_KEY_HOST, SessionRequest.this.a());
                    }
                    SessionRequest.this.b();
                    if (EventType.CONNECT_FAIL.equals(eventType) && i != -2613 && i != -2601) {
                        AlarmObject alarmObject = new AlarmObject();
                        alarmObject.module = "networkPrefer";
                        alarmObject.modulePoint = "policy";
                        alarmObject.arg = SessionRequest.this.d;
                        alarmObject.errorCode = String.valueOf(i);
                        alarmObject.isSuccess = false;
                        AppMonitor.getInstance().commitAlarm(alarmObject);
                    }
                }
            }
        }

        public void onSuccess(Session session, long j) {
            ALog.d("awcn.SessionRequest", "Connect Success", this.e.i(), "session", session, com.taobao.accs.internal.b.ELECTION_KEY_HOST, SessionRequest.this.a());
            try {
                a.a.a(SessionRequest.this, session);
                if (session != null && (session instanceof AccsSession)) {
                    ((AccsSession) session).setFrameCb(SessionCenter.getInstance().getDataChannelCb());
                    ALog.d("awcn.SessionRequest", "set Framecb success", null, "session", session);
                }
                AlarmObject alarmObject = new AlarmObject();
                alarmObject.module = "networkPrefer";
                alarmObject.modulePoint = "policy";
                alarmObject.arg = SessionRequest.this.d;
                alarmObject.isSuccess = true;
                AppMonitor.getInstance().commitAlarm(alarmObject);
                SessionRequest.this.b();
            } catch (Throwable e) {
                ALog.e("awcn.SessionRequest", "[onSuccess]:", this.e.i(), e, new Object[0]);
                SessionRequest.this.b();
            }
        }

        public void onDisConnect(Session session, long j, EventType eventType) {
            boolean isAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            ALog.d("awcn.SessionRequest", "Connect Disconnect", this.e.i(), "session", session, com.taobao.accs.internal.b.ELECTION_KEY_HOST, SessionRequest.this.a(), "appIsBg", Boolean.valueOf(isAppBackground), "isHandleFinish", Boolean.valueOf(this.a));
            a.a.b(SessionRequest.this, session);
            if (!this.a) {
                this.a = true;
                if (!session.autoReCreate) {
                    return;
                }
                if (isAppBackground) {
                    ALog.e("awcn.SessionRequest", "app background,return", this.e.i(), "session", session);
                } else if (NetworkStatusHelper.e()) {
                    try {
                        ALog.d("awcn.SessionRequest", "session disconnected, try to recreate session", this.e.i(), new Object[0]);
                        c.a(new h(this, session), (long) ((Math.random() * 10.0d) * 1000.0d), TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                    }
                } else {
                    ALog.e("awcn.SessionRequest", "no network,return", this.e.i(), "session", session);
                }
            }
        }
    }

    // compiled from: Taobao
    private class b implements Runnable {
        String a;

        b(String str) {
            this.a = null;
            this.a = str;
        }

        public void run() {
            if (SessionRequest.this.a) {
                ALog.e("awcn.SessionRequest", "Connecting timeout!!! reset status!", this.a, new Object[0]);
                if (SessionRequest.this != null) {
                    SessionRequest.this.tryNextWhenFail = false;
                    SessionRequest.this.close();
                }
                SessionRequest.this.b(false);
            }
        }
    }

    static {
        c = new HashMap();
    }

    private SessionRequest(String str) {
        this.a = false;
        this.f = new Object();
        this.d = str;
    }

    protected static SessionRequest a(String str) {
        SessionRequest sessionRequest;
        ALog.d("awcn.SessionRequest", "SessionRequest build", null, "key", str);
        String toLowerCase = str.toLowerCase(Locale.US);
        synchronized (c) {
            sessionRequest = (SessionRequest) c.get(toLowerCase);
            if (sessionRequest == null) {
                sessionRequest = new SessionRequest(toLowerCase);
                c.put(toLowerCase, sessionRequest);
            } else {
                ALog.d("awcn.SessionRequest", "hit cached SessionRequest", null, "key", toLowerCase, "info", sessionRequest);
            }
        }
        return sessionRequest;
    }

    protected static void a(SessionRequest sessionRequest) {
        synchronized (c) {
            c.remove(sessionRequest);
        }
    }

    protected String a() {
        return this.d;
    }

    private void b(boolean z) {
        this.a = z;
        if (!z) {
            if (this.e != null) {
                this.e.cancel(true);
                this.e = null;
            }
            this.b = null;
        }
    }

    protected synchronized void a(Context context, TypeLevel typeLevel, String str) throws NoNetworkException, NoAvailStrategyException {
        if (a.a.a(this, typeLevel) != null) {
            ALog.d("awcn.SessionRequest", "Available Session exist!!!", str, new Object[0]);
        } else {
            if (TextUtils.isEmpty(str)) {
                str = i.a();
            }
            ALog.d("awcn.SessionRequest", "SessionRequest start", str, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.d, JsInterface.FUNPLAY_AD_TRPE, typeLevel);
            if (this.a) {
                ALog.d("awcn.SessionRequest", "session is connecting, return", str, com.taobao.accs.internal.b.ELECTION_KEY_HOST, a());
            } else {
                b(true);
                this.e = c.a(new b(str), 45, TimeUnit.SECONDS);
                if (NetworkStatusHelper.e()) {
                    List a = a(typeLevel, str);
                    if (a.isEmpty()) {
                        ALog.e("awcn.SessionRequest", "no strategy\uff0ccan't create session", str, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.d, JsInterface.FUNPLAY_AD_TRPE, typeLevel);
                        b();
                        throw new NoAvailStrategyException(this);
                    }
                    if (typeLevel == TypeLevel.HTTP) {
                        ListIterator listIterator = a.listIterator();
                        while (listIterator.hasNext()) {
                            IConnStrategy iConnStrategy = (IConnStrategy) listIterator.next();
                            if (e.a(this.d, iConnStrategy.getIp(), iConnStrategy.getPort())) {
                                listIterator.remove();
                            }
                        }
                        if (a.isEmpty()) {
                            ALog.i("awcn.SessionRequest", "all http strategies are removed.", null, new Object[0]);
                            b();
                        }
                    }
                    a = a(a, str);
                    try {
                        anet.channel.entity.a aVar = (anet.channel.entity.a) a.remove(0);
                        a(context, aVar, new a(context, a, aVar), aVar.i());
                    } catch (Throwable th) {
                        b();
                    }
                } else {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.SessionRequest", "network is not available\uff0ccan't create session", str, "NetworkStatusHelper.isConnected()", Boolean.valueOf(NetworkStatusHelper.e()));
                    }
                    b();
                    throw new NoNetworkException(this);
                }
            }
        }
    }

    private List<IConnStrategy> a(TypeLevel typeLevel, String str) {
        List list = Collections.EMPTY_LIST;
        try {
            String[] parseURL = StringUtils.parseURL(a());
            if (parseURL == null) {
                return Collections.EMPTY_LIST;
            }
            List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(parseURL[1]);
            if (!connStrategyListByHost.isEmpty()) {
                boolean equalsIgnoreCase = com.alipay.sdk.cons.b.a.equalsIgnoreCase(parseURL[0]);
                ListIterator listIterator = connStrategyListByHost.listIterator();
                while (listIterator.hasNext()) {
                    IConnStrategy iConnStrategy = (IConnStrategy) listIterator.next();
                    if (iConnStrategy.getConnType().isSSL() != equalsIgnoreCase || (typeLevel != null && iConnStrategy.getConnType().getTypeLevel() != typeLevel)) {
                        listIterator.remove();
                    }
                }
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "[getAvailStrategy]", str, "strategies", connStrategyListByHost);
            }
            return connStrategyListByHost;
        } catch (Throwable th) {
            Throwable th2 = th;
            List<IConnStrategy> list2 = connStrategyListByHost;
            ALog.e("awcn.SessionRequest", com.umeng.a.d, str, th2, new Object[0]);
            return list2;
        }
    }

    private List<anet.channel.entity.a> a(List<IConnStrategy> list, String str) {
        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<anet.channel.entity.a> arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            IConnStrategy iConnStrategy = (IConnStrategy) list.get(i2);
            int retryTimes = iConnStrategy.getRetryTimes();
            int i3 = 0;
            while (i3 <= retryTimes) {
                int i4 = i + 1;
                anet.channel.entity.a aVar = new anet.channel.entity.a(a(), str + "_" + i4, iConnStrategy);
                aVar.b = i3;
                aVar.c = retryTimes;
                arrayList.add(aVar);
                i3++;
                i = i4;
            }
        }
        return arrayList;
    }

    private void a(Context context, anet.channel.entity.a aVar, IConnCb iConnCb, String str) {
        if (aVar.c().isHttpType()) {
            this.b = new e(context, aVar);
        } else if (aVar.f()) {
            this.b = new AccsSession(context, aVar);
        } else {
            this.b = new anet.channel.session.i(context, aVar);
        }
        ALog.d("awcn.SessionRequest", "create connection...", str, HttpConstant.HOST, a(), "Type", aVar.c(), "IP", aVar.a(), "Port", Integer.valueOf(aVar.b()), "heartbeat", Integer.valueOf(aVar.h()), "session", this.b);
        a(this.b, iConnCb, System.currentTimeMillis());
        this.b.connect();
    }

    private void a(Session session, IConnCb iConnCb, long j) {
        if (iConnCb != null) {
            session.registerEventcb(EventType.ALL.getType(), new f(this, iConnCb, j));
            session.registerEventcb(((EventType.CONNECTED.getType() | EventType.CONNECT_FAIL.getType()) | EventType.AUTH_SUCC.getType()) | EventType.AUTH_FAIL.getType(), new g(this, session));
        }
    }

    protected void a(boolean z) {
        ALog.d("awcn.SessionRequest", "closeSessions", null, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.d, "autoCreate", Boolean.valueOf(z));
        List<Session> a = a.a.a(this);
        if (a != null) {
            for (Session session : a) {
                if (session != null) {
                    session.close(z);
                }
            }
        }
    }

    protected void b(String str) {
        ALog.d("awcn.SessionRequest", "reCreateSession", str, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.d);
        a(true);
    }

    protected void a(long j) throws InterruptedException, TimeoutException {
        ALog.d("awcn.SessionRequest", "[await]", null, "timeoutMs", Long.valueOf(j));
        if (j > 0) {
            synchronized (this.f) {
                long currentTimeMillis = System.currentTimeMillis() + j;
                while (this.a) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 < currentTimeMillis) {
                        this.f.wait(currentTimeMillis - currentTimeMillis2);
                    }
                }
                break;
                if (this.a) {
                    throw new TimeoutException();
                }
            }
        }
    }

    protected void b() {
        b(false);
        synchronized (this.f) {
            this.f.notifyAll();
        }
    }

    protected void a(String str, boolean z, int i, String str2) {
        Context context = GlobalAppRuntimeInfo.getContext();
        if (context != null && n.c(n.e(str))) {
            String str3 = Constants.ACTION_RECEIVE;
            String str4 = IntentUtil.AGOO_COMMAND;
            String str5 = Constants.KEY_ERROR_CODE;
            String str6 = Constants.KEY_ERROR_DETAIL;
            String str7 = Constants.KEY_CONNECT_AVAILABLE;
            String str8 = com.taobao.accs.internal.b.ELECTION_KEY_HOST;
            String str9 = Constants.KEY_TYPE_INAPP;
            String str10 = Constants.KEY_CENTER_HOST;
            try {
                URL url = new URL(str);
                Intent intent = new Intent(str3);
                intent.setPackage(context.getPackageName());
                intent.setClassName(context, com.taobao.accs.utl.a.msgService);
                intent.putExtra(str4, R.styleable.AppCompatTheme_checkedTextViewStyle);
                intent.putExtra(str8, n.a(url.getProtocol(), url.getHost()));
                intent.putExtra(str10, n.a(url.getHost()));
                if (!z) {
                    intent.putExtra(str5, i);
                    intent.putExtra(str6, str2);
                }
                intent.putExtra(str7, z);
                intent.putExtra(str9, true);
                context.startService(intent);
            } catch (Throwable th) {
                ALog.e("awcn.SessionRequest", "sendConnectInfoBroadCastToAccs", null, th, new Object[0]);
            }
        }
    }
}
