package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.c.c;
import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.strategy.k.b;
import anet.channel.util.ALog;
import anet.channel.util.LruCache;
import anet.channel.util.StringUtils;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// compiled from: Taobao
class StrategyInfoHolder implements INetworkStatusChangeListener {
    Map<String, StrategyTable> a;
    UnitMap b;
    SafeAislesMap c;
    HorseRideStrategyMap d;
    final c e;
    final ConcurrentHashMap<String, String> f;
    private final StrategyTable g;
    private final Object h;
    private final Set<String> i;
    private volatile String j;

    // compiled from: Taobao
    private static class ConfigInfoWrapper implements Serializable {
        UnitMap a;
        SafeAislesMap b;
        HorseRideStrategyMap c;

        ConfigInfoWrapper(StrategyInfoHolder strategyInfoHolder) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.a = strategyInfoHolder.b;
            this.b = strategyInfoHolder.c;
            this.c = strategyInfoHolder.d;
        }

        void a(StrategyInfoHolder strategyInfoHolder) {
            strategyInfoHolder.b = this.a;
            strategyInfoHolder.c = this.b;
            strategyInfoHolder.d = this.c;
        }
    }

    // compiled from: Taobao
    private static class LURStrategyMap extends LruCache<String, StrategyTable> {
        public LURStrategyMap() {
            super(3);
        }

        protected boolean a(Entry<String, StrategyTable> entry) {
            new h(this, entry).execute(new Void[0]);
            return true;
        }
    }

    public static StrategyInfoHolder a() {
        return new StrategyInfoHolder();
    }

    private StrategyInfoHolder() {
        this.a = new LURStrategyMap();
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = new c();
        this.f = new ConcurrentHashMap();
        this.g = new StrategyTable("Unknown");
        this.h = new Object();
        this.i = new HashSet();
        this.j = a.d;
        try {
            d();
            f();
            e();
        } catch (Exception e) {
            e();
        }
    }

    private void d() {
        NetworkStatusHelper.a((INetworkStatusChangeListener) this);
        this.j = a(NetworkStatusHelper.a());
    }

    private void e() {
        for (Entry entry : this.a.entrySet()) {
            ((StrategyTable) entry.getValue()).a();
        }
        if (this.b == null) {
            this.b = new UnitMap();
        } else {
            this.b.a();
        }
        if (this.c == null) {
            this.c = new SafeAislesMap();
        } else {
            this.c.a();
        }
        if (this.d == null) {
            this.d = new HorseRideStrategyMap();
        } else {
            this.d.a();
        }
    }

    private void f() {
        String b = b(this.j);
        if (!TextUtils.isEmpty(this.j)) {
            a(b, this.j);
        }
        ConfigInfoWrapper configInfoWrapper = (ConfigInfoWrapper) l.b("config");
        if (configInfoWrapper != null) {
            configInfoWrapper.a(this);
        }
        c.a(new f(this, b));
    }

    private void a(String str, String str2) {
        synchronized (this.i) {
            boolean contains = this.i.contains(str);
            if (!contains) {
                this.i.add(str);
            }
        }
        if (!contains) {
            StrategyTable strategyTable = (StrategyTable) l.b(str);
            if (strategyTable != null) {
                strategyTable.a();
            } else if (!TextUtils.isEmpty(str2)) {
                strategyTable = new StrategyTable(str2);
            }
            if (strategyTable != null) {
                synchronized (this.a) {
                    this.a.put(strategyTable.a, strategyTable);
                }
            }
            synchronized (this.i) {
                this.i.remove(str);
            }
        }
    }

    void b() {
        synchronized (this.a) {
            for (StrategyTable strategyTable : this.a.values()) {
                l.a(strategyTable, b(strategyTable.a));
            }
        }
        synchronized (this.h) {
            l.a(new ConfigInfoWrapper(this), "config");
        }
    }

    StrategyTable c() {
        StrategyTable strategyTable = this.g;
        if (TextUtils.isEmpty(this.j)) {
            return strategyTable;
        }
        StrategyTable strategyTable2;
        synchronized (this.a) {
            strategyTable2 = (StrategyTable) this.a.get(this.j);
            if (strategyTable2 == null) {
                if (this.a.isEmpty()) {
                    strategyTable2 = strategyTable;
                } else {
                    strategyTable2 = (StrategyTable) this.a.values().iterator().next();
                }
            }
        }
        return strategyTable2;
    }

    private static String b(String str) {
        Object md5ToHex = StringUtils.md5ToHex(str);
        return !TextUtils.isEmpty(md5ToHex) ? md5ToHex : "DefaultStrategy";
    }

    private String a(NetworkStatus networkStatus) {
        String str = a.d;
        if (!networkStatus.isWifi()) {
            return networkStatus.isMobile() ? networkStatus.getType() : str;
        } else {
            if (TextUtils.isEmpty(NetworkStatusHelper.d())) {
                return str;
            }
            return StringUtils.buildString(networkStatus.getType(), "$", NetworkStatusHelper.d());
        }
    }

    void a(k.c cVar) {
        if (cVar.g != 0) {
            GlobalAppRuntimeInfo.setAmdcLimit(cVar.g, cVar.h);
        }
        b(cVar);
        c().update(cVar);
        synchronized (this.h) {
            this.c.a(cVar);
            this.b.a(cVar);
            this.d.a(cVar);
        }
        a(cVar.c);
    }

    private void b(k.c cVar) {
        if (cVar.c != null) {
            for (int i = 0; i < cVar.c.length; i++) {
                b bVar = cVar.c[i];
                if (TextUtils.isEmpty(bVar.d)) {
                    this.f.remove(bVar.a);
                } else {
                    this.f.put(bVar.a, bVar.d);
                }
            }
        }
    }

    private void a(b[] bVarArr) {
        for (int i = 0; i < bVarArr.length; i++) {
            b bVar = bVarArr[i];
            if (bVar.q) {
                ALog.i("awcn.StrategyInfoHolder", "find effectNow", null, com.taobao.accs.internal.b.ELECTION_KEY_HOST, bVar.a);
                k.a[] aVarArr = bVar.f;
                String[] strArr = bVar.e;
                for (Session session : a.a.a(n.a(bVar.c, bVar.a))) {
                    if (!session.getConnType().isHttpType()) {
                        int i2;
                        Object obj;
                        for (Object obj2 : strArr) {
                            if (session.getIp().equals(obj2)) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                                ALog.i("awcn.StrategyInfoHolder", "ip not match", null, "session ip", session.getIp(), "ips", Arrays.toString(strArr));
                            }
                            session.close(true);
                        } else {
                            i2 = 0;
                            while (i2 < aVarArr.length) {
                                if (session.getPort() == aVarArr[i2].a && session.getConnType().equals(ConnType.valueOf(aVarArr[i2]))) {
                                    obj = 1;
                                    break;
                                }
                                i2++;
                            }
                            obj = null;
                            if (obj == null) {
                                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                                    ALog.i("awcn.StrategyInfoHolder", "aisle not match", null, "port", Integer.valueOf(session.getPort()), "connType", session.getConnType(), "aisle", Arrays.toString(aVarArr));
                                }
                                session.close(true);
                            } else {
                                ALog.i("awcn.StrategyInfoHolder", "session matches, do nothing", null, new Object[0]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onNetworkStatusChanged(NetworkStatus networkStatus) {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            NetworkStatusHelper.i();
        }
        this.j = a(networkStatus);
        if (!TextUtils.isEmpty(this.j)) {
            synchronized (this.a) {
                if (!this.a.containsKey(this.j)) {
                    c.a(new g(this, this.j));
                }
            }
        }
    }
}
