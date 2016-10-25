package anet.channel;

import android.content.Context;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.a;
import anet.channel.entity.d;
import anet.channel.entity.e;
import anet.channel.session.AccsSession;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.IHRStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.i;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: Taobao
public class b {
    public static final String HR_SERIAL = "serial";
    public static final String HR_SERIAL_CONN = "serialConn";
    public static final String HR_SERIAL_ONLY = "serialOnly";
    private static final int a;

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
                a[EventType.AUTH_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EventType.CONNECT_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        a = (EventType.AUTH_SUCC.getType() | EventType.AUTH_FAIL.getType()) | EventType.CONNECT_FAIL.getType();
    }

    public static void a() {
        Map hRStrategyMap = StrategyCenter.getInstance().getHRStrategyMap();
        if (hRStrategyMap != null && !hRStrategyMap.isEmpty()) {
            for (Entry entry : hRStrategyMap.entrySet()) {
                IHRStrategy iHRStrategy = (IHRStrategy) entry.getValue();
                String hRStrategy = iHRStrategy.getHRStrategy();
                long currentTimeMillis = System.currentTimeMillis();
                if ((HR_SERIAL.equals(hRStrategy) || HR_SERIAL_ONLY.equals(hRStrategy)) && currentTimeMillis - iHRStrategy.getLastHRTime() > iHRStrategy.getHRInterval() && iHRStrategy.getHrNum() > 0) {
                    if (ALog.isPrintLog(1)) {
                        ALog.d("awcn.HorseRide", "horse ride for this host", null, com.taobao.accs.internal.b.ELECTION_KEY_HOST, entry.getKey());
                    }
                    a((String) entry.getKey(), iHRStrategy.getHrNum());
                }
            }
        }
    }

    private static void a(String str, int i) {
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str);
        if (connStrategyListByHost != null && !connStrategyListByHost.isEmpty()) {
            if (connStrategyListByHost.size() > i) {
                Collections.shuffle(connStrategyListByHost);
            }
            int i2 = 0;
            for (IConnStrategy iConnStrategy : connStrategyListByHost) {
                int i3;
                Session a = a(iConnStrategy, str);
                if (a != null) {
                    b(a);
                    a.connect();
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }

    private static void b(Session session) {
        session.registerEventcb(a, new c(System.currentTimeMillis()));
    }

    private static void b(e eVar, d dVar) {
        eVar.a = false;
        if (dVar != null) {
            eVar.d = dVar.d;
            eVar.e = dVar.e;
        }
    }

    private static Session a(IConnStrategy iConnStrategy, String str) {
        Session eVar;
        Context context = GlobalAppRuntimeInfo.getContext();
        a aVar = new a(n.a(iConnStrategy.getConnType().isSSL() ? com.alipay.sdk.cons.b.a : HttpConstant.HTTP, str), i.a(), iConnStrategy);
        ConnType connType = iConnStrategy.getConnType();
        if (connType.equals(ConnType.HTTP) || connType.equals(ConnType.HTTPS)) {
            eVar = new anet.channel.session.e(context, aVar);
        } else if (aVar.f()) {
            eVar = new AccsSession(context, aVar);
        } else {
            eVar = new anet.channel.session.i(context, aVar);
        }
        eVar.setIsHorseRide(true);
        return eVar;
    }

    private static void c(Session session) {
        List<Session> a = a.a.a(session.getHost());
        if (a != null) {
            for (Session session2 : a) {
                if (session2.sameSession(session)) {
                    return;
                }
            }
        }
        session.close();
    }
}
