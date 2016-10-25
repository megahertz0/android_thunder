package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.dispatch.a;
import anet.channel.strategy.dispatch.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.LruCache;
import com.alipay.sdk.util.h;
import com.taobao.accs.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

// compiled from: Taobao
class StrategyTable implements Serializable {
    protected String a;
    protected volatile String b;
    private volatile transient int c;
    private HotHostLruCache d;
    private transient Map<String, StrategyCollection> e;
    private Set<String> f;

    // compiled from: Taobao
    private static class HotHostLruCache extends LruCache<String, StrategyCollection> {
        public HotHostLruCache(int i) {
            super(i);
        }

        protected boolean a(Entry<String, StrategyCollection> entry) {
            String str = (String) entry.getKey();
            if (!n.a().equals(str) && !a.a().equals(str)) {
                return true;
            }
            Iterator it = entrySet().iterator();
            while (it.hasNext()) {
                str = (String) ((Entry) it.next()).getKey();
                if (!n.a().equals(str) && !a.a().equals(str)) {
                    it.remove();
                    break;
                }
            }
            return false;
        }
    }

    protected StrategyTable(String str) {
        this.a = str;
        a();
    }

    private void b() {
        if (HttpDispatcher.getInstance().isInitHostsChanged(this.a)) {
            for (String str : HttpDispatcher.getInstance().getInitHosts()) {
                Object strategyCollection;
                if (a.a().equalsIgnoreCase(str)) {
                    strategyCollection = new StrategyCollection(a.a(), ConnStrategyList.createForIDC(a.b(), a.a(R.styleable.AppCompatTheme_panelMenuListTheme, ConnType.HTTP), a.a(Constants.PORT, ConnType.HTTP)));
                } else if (n.a().equalsIgnoreCase(str)) {
                    strategyCollection = new StrategyCollection(n.a(), ConnStrategyList.createForIDC(n.b(), a.a()));
                } else if (n.c.equalsIgnoreCase(str)) {
                    strategyCollection = new StrategyCollection(n.c, ConnStrategyList.createForIDC(n.d, a.a()));
                } else if (n.e.equalsIgnoreCase(str)) {
                    strategyCollection = new StrategyCollection(n.e, ConnStrategyList.createForIDC(n.f, a.a()));
                } else {
                    strategyCollection = new StrategyCollection(str);
                }
                this.d.put(str, strategyCollection);
            }
        }
    }

    protected void a() {
        if (this.d == null) {
            this.d = new HotHostLruCache(32);
            b();
        }
        if (this.e == null) {
            this.e = new LruCache(32);
        }
        if (this.f == null) {
            this.f = new TreeSet();
        }
        this.c = GlobalAppRuntimeInfo.isTargetProcess() ? 0 : -1;
    }

    public String toString() {
        StringBuilder append = new StringBuilder("\nuniqueId : ").append(this.a).append("\n");
        append.append("--------hot domains:------------------------------------");
        synchronized (this.d) {
            for (Entry entry : this.d.entrySet()) {
                append.append("\n").append((String) entry.getKey()).append(" = ").append(((StrategyCollection) entry.getValue()).toString());
            }
        }
        append.append("\n--------cold domains:------------------------------------");
        synchronized (this.e) {
            for (Entry entry2 : this.e.entrySet()) {
                append.append("\n").append((String) entry2.getKey()).append(" = ").append(((StrategyCollection) entry2.getValue()).toString());
            }
        }
        return append.toString();
    }

    public List<IConnStrategy> queryByHost(String str) {
        if (TextUtils.isEmpty(str) || !n.f(str)) {
            return Collections.EMPTY_LIST;
        }
        StrategyCollection strategyCollection;
        c();
        synchronized (this.d) {
            strategyCollection = (StrategyCollection) this.d.get(str);
        }
        if (strategyCollection == null) {
            Object obj = null;
            synchronized (this.e) {
                strategyCollection = (StrategyCollection) this.e.get(str);
                if (strategyCollection == null) {
                    StrategyCollection strategyCollection2 = new StrategyCollection(str);
                    this.e.put(str, strategyCollection2);
                    strategyCollection = strategyCollection2;
                    int i = 1;
                }
            }
            if (obj != null) {
                a(str);
            }
        } else if (strategyCollection.isExpired()) {
            a(this.d);
        }
        return strategyCollection.queryStrategyList();
    }

    public String querySchemeByHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StrategyCollection strategyCollection;
        synchronized (this.d) {
            strategyCollection = (StrategyCollection) this.d.get(str);
        }
        if (strategyCollection == null) {
            synchronized (this.e) {
                strategyCollection = (StrategyCollection) this.e.get(str);
            }
        }
        return strategyCollection != null ? strategyCollection.d : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(anet.channel.strategy.k.c r14) {
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.strategy.StrategyTable.update(anet.channel.strategy.k$c):void");
        /*
        this = this;
        r12 = 0;
        r11 = 1;
        r3 = 0;
        r0 = "awcn.StrategyTable";
        r1 = "update strategyTable with httpDns response";
        r2 = new java.lang.Object[r3];
        anet.channel.util.ALog.i(r0, r1, r12, r2);
        r0 = r14.a;	 Catch:{ Throwable -> 0x006f }
        r13.b = r0;	 Catch:{ Throwable -> 0x006f }
        r0 = r14.f;	 Catch:{ Throwable -> 0x006f }
        r13.c = r0;	 Catch:{ Throwable -> 0x006f }
        r4 = r14.c;	 Catch:{ Throwable -> 0x006f }
        if (r4 != 0) goto L_0x001b;
    L_0x001a:
        return;
    L_0x001b:
        r5 = r13.d;	 Catch:{ Throwable -> 0x006f }
        monitor-enter(r5);	 Catch:{ Throwable -> 0x006f }
        r6 = r13.e;	 Catch:{ all -> 0x006c }
        monitor-enter(r6);	 Catch:{ all -> 0x006c }
        r2 = r3;
    L_0x0022:
        r0 = r4.length;	 Catch:{ all -> 0x0069 }
        if (r2 >= r0) goto L_0x00c4;
    L_0x0025:
        r7 = r4[r2];	 Catch:{ all -> 0x0069 }
        if (r7 == 0) goto L_0x0042;
    L_0x0029:
        r0 = r7.a;	 Catch:{ all -> 0x0069 }
        if (r0 == 0) goto L_0x0042;
    L_0x002d:
        r0 = r7.m;	 Catch:{ all -> 0x0069 }
        if (r0 == 0) goto L_0x0046;
    L_0x0031:
        r0 = r13.d;	 Catch:{ all -> 0x0069 }
        r1 = r7.a;	 Catch:{ all -> 0x0069 }
        r0 = r0.remove(r1);	 Catch:{ all -> 0x0069 }
        if (r0 != 0) goto L_0x0042;
    L_0x003b:
        r0 = r13.e;	 Catch:{ all -> 0x0069 }
        r1 = r7.a;	 Catch:{ all -> 0x0069 }
        r0.remove(r1);	 Catch:{ all -> 0x0069 }
    L_0x0042:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0022;
    L_0x0046:
        r0 = r13.d;	 Catch:{ all -> 0x0069 }
        r1 = r7.a;	 Catch:{ all -> 0x0069 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0069 }
        r0 = (anet.channel.strategy.StrategyCollection) r0;	 Catch:{ all -> 0x0069 }
        if (r0 == 0) goto L_0x008e;
    L_0x0052:
        r1 = r7.p;	 Catch:{ all -> 0x0069 }
        if (r1 != 0) goto L_0x0065;
    L_0x0056:
        r1 = r13.e;	 Catch:{ all -> 0x0069 }
        r8 = r7.a;	 Catch:{ all -> 0x0069 }
        r9 = r13.d;	 Catch:{ all -> 0x0069 }
        r10 = r7.a;	 Catch:{ all -> 0x0069 }
        r9 = r9.remove(r10);	 Catch:{ all -> 0x0069 }
        r1.put(r8, r9);	 Catch:{ all -> 0x0069 }
    L_0x0065:
        r0.update(r7);	 Catch:{ all -> 0x0069 }
        goto L_0x0042;
    L_0x0069:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x006c }
        throw r0;	 Catch:{ all -> 0x006c }
    L_0x006c:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ Throwable -> 0x006f }
        throw r0;	 Catch:{ Throwable -> 0x006f }
    L_0x006f:
        r0 = move-exception;
        r1 = "awcn.StrategyTable";
        r2 = "fail to update strategyTable";
        r4 = new java.lang.Object[r3];
        anet.channel.util.ALog.e(r1, r2, r12, r0, r4);
    L_0x007b:
        r0 = anet.channel.util.ALog.isPrintLog(r11);
        if (r0 == 0) goto L_0x001a;
    L_0x0081:
        r0 = "awcn.StrategyTable";
        r1 = r13.toString();
        r2 = new java.lang.Object[r3];
        anet.channel.util.ALog.d(r0, r1, r12, r2);
        goto L_0x001a;
    L_0x008e:
        r0 = r13.e;	 Catch:{ all -> 0x0069 }
        r1 = r7.a;	 Catch:{ all -> 0x0069 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0069 }
        r0 = (anet.channel.strategy.StrategyCollection) r0;	 Catch:{ all -> 0x0069 }
        if (r0 == 0) goto L_0x00ae;
    L_0x009a:
        r1 = r7.p;	 Catch:{ all -> 0x0069 }
        if (r1 != r11) goto L_0x0065;
    L_0x009e:
        r1 = r13.d;	 Catch:{ all -> 0x0069 }
        r8 = r7.a;	 Catch:{ all -> 0x0069 }
        r9 = r13.e;	 Catch:{ all -> 0x0069 }
        r10 = r7.a;	 Catch:{ all -> 0x0069 }
        r9 = r9.remove(r10);	 Catch:{ all -> 0x0069 }
        r1.put(r8, r9);	 Catch:{ all -> 0x0069 }
        goto L_0x0065;
    L_0x00ae:
        r0 = new anet.channel.strategy.StrategyCollection;	 Catch:{ all -> 0x0069 }
        r1 = r7.a;	 Catch:{ all -> 0x0069 }
        r0.<init>(r1);	 Catch:{ all -> 0x0069 }
        r1 = r7.p;	 Catch:{ all -> 0x0069 }
        if (r1 != r11) goto L_0x00c1;
    L_0x00b9:
        r1 = r13.d;	 Catch:{ all -> 0x0069 }
    L_0x00bb:
        r8 = r7.a;	 Catch:{ all -> 0x0069 }
        r1.put(r8, r0);	 Catch:{ all -> 0x0069 }
        goto L_0x0065;
    L_0x00c1:
        r1 = r13.e;	 Catch:{ all -> 0x0069 }
        goto L_0x00bb;
    L_0x00c4:
        monitor-exit(r6);	 Catch:{ all -> 0x0069 }
        monitor-exit(r5);	 Catch:{ all -> 0x006c }
        goto L_0x007b;
        */
    }

    private Set<String> b(Map<String, StrategyCollection> map) {
        Set<String> hashSet = new HashSet();
        long currentTimeMillis = System.currentTimeMillis();
        if (map == this.d) {
            int i = 1;
        } else {
            Object obj = null;
        }
        for (StrategyCollection strategyCollection : map.values()) {
            if (obj != null || currentTimeMillis >= strategyCollection.c) {
                hashSet.add(strategyCollection.getHostWithEtag());
                strategyCollection.c = 30000 + currentTimeMillis;
            }
        }
        return hashSet;
    }

    protected void a(String str) {
        if (!GlobalAppRuntimeInfo.isAppBackground() && !TextUtils.isEmpty(str) && NetworkStatusHelper.e()) {
            int amdcLimitLevel = GlobalAppRuntimeInfo.getAmdcLimitLevel();
            if (amdcLimitLevel != 3) {
                Set hashSet;
                if (amdcLimitLevel == 2) {
                    hashSet = new HashSet();
                    hashSet.add(str);
                } else {
                    StrategyCollection strategyCollection;
                    Set b;
                    synchronized (this.d) {
                        strategyCollection = (StrategyCollection) this.d.get(str);
                        if (strategyCollection != null) {
                            b = b(this.d);
                            b.add(strategyCollection.getHostWithEtag());
                        } else {
                            b = null;
                        }
                    }
                    if (strategyCollection == null) {
                        synchronized (this.e) {
                            StrategyCollection strategyCollection2;
                            strategyCollection = (StrategyCollection) this.e.get(str);
                            if (strategyCollection == null) {
                                strategyCollection = new StrategyCollection(str);
                                this.e.put(str, strategyCollection);
                                strategyCollection2 = strategyCollection;
                            } else {
                                strategyCollection2 = strategyCollection;
                            }
                            hashSet = b(this.e);
                            hashSet.add(strategyCollection2.getHostWithEtag());
                        }
                    } else {
                        hashSet = b;
                    }
                }
                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    ALog.i("awcn.StrategyTable", "sendAmdcRequest", null, "hosts:", hashSet.toString());
                }
                HttpDispatcher.getInstance().sendHttpDispatchRequest(hashSet, d(), this.c);
            }
        }
    }

    protected void a(Map<String, StrategyCollection> map) {
        if (!GlobalAppRuntimeInfo.isAppBackground() && GlobalAppRuntimeInfo.getAmdcLimitLevel() <= 0 && NetworkStatusHelper.e()) {
            Set b;
            synchronized (map) {
                b = b(map);
            }
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i("awcn.StrategyTable", "sendAmdcRequest", null, "hosts:", b.toString());
            }
            if (!b.isEmpty()) {
                HttpDispatcher.getInstance().sendHttpDispatchRequest(b, d(), this.c);
            }
        }
    }

    private void c() {
        try {
            if (HttpDispatcher.getInstance().isInitHostsChanged(this.a)) {
                int i;
                synchronized (this.d) {
                    try {
                        synchronized (this.e) {
                            i = 0;
                            for (String str : HttpDispatcher.getInstance().getInitHosts()) {
                                int i2;
                                if (this.d.containsKey(str) || this.e.containsKey(str)) {
                                    i2 = i;
                                } else {
                                    this.e.put(str, new StrategyCollection(str));
                                    Object obj = 1;
                                }
                                i = i2;
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
                if (i != 0) {
                    a(this.e);
                }
            }
        } catch (Throwable e) {
            ALog.e("awcn.StrategyTable", "checkInitHost failed", null, e, new Object[0]);
        }
    }

    public void notifyConnEvent(String str, IConnStrategy iConnStrategy, EventType eventType, d dVar) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.StrategyTable", "[notifyConnEvent]", null, HttpConstant.HOST, str, "IConnStrategy", iConnStrategy, "eventType", eventType);
        }
        c.a(this.a, this.b, str, iConnStrategy, eventType, dVar);
        a(eventType, iConnStrategy.getIp());
        synchronized (this.d) {
            try {
                synchronized (this.e) {
                    StrategyCollection strategyCollection = (StrategyCollection) this.d.get(str);
                    if (strategyCollection == null) {
                        strategyCollection = (StrategyCollection) this.e.get(str);
                    }
                }
            } catch (Throwable th) {
            }
        }
        if (strategyCollection != null) {
            strategyCollection.notifyConnEvent(iConnStrategy, eventType, dVar);
        }
    }

    public void fillLastHorseRideTime(Map<String, HorseRideStrategy> map) {
        if (map != null && !map.isEmpty()) {
            synchronized (this.d) {
                try {
                    synchronized (this.e) {
                        for (Entry entry : map.entrySet()) {
                            StrategyCollection strategyCollection = (StrategyCollection) this.d.get(entry.getKey());
                            if (strategyCollection == null) {
                                strategyCollection = (StrategyCollection) this.e.get(entry.getKey());
                            }
                            if (strategyCollection != null) {
                                ((HorseRideStrategy) entry.getValue()).lastHorseRideTime = strategyCollection.f;
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    private void a(EventType eventType, String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f) {
                if (eventType == EventType.AUTH_SUCC || eventType == EventType.CONNECTED) {
                    this.f.add(str);
                } else if (eventType == EventType.AUTH_FAIL || eventType == EventType.CONNECT_FAIL) {
                    this.f.remove(str);
                }
            }
        }
    }

    private String d() {
        StringBuilder stringBuilder = new StringBuilder();
        synchronized (this.f) {
            for (String str : this.f) {
                stringBuilder.append(str).append(h.b);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
