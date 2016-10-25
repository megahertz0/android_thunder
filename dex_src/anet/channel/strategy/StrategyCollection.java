package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.b.b;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

// compiled from: Taobao
class StrategyCollection implements Serializable {
    String a;
    ConnStrategyList b;
    volatile long c;
    volatile String d;
    volatile String e;
    volatile long f;

    public StrategyCollection() {
        this.b = null;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = 0;
    }

    protected StrategyCollection(String str) {
        this.b = null;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.a = str;
    }

    protected StrategyCollection(String str, ConnStrategyList connStrategyList) {
        this.b = null;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.a = str;
        this.b = connStrategyList;
    }

    public synchronized List<IConnStrategy> queryStrategyList() {
        List<IConnStrategy> list;
        if (this.b == null) {
            list = Collections.EMPTY_LIST;
        } else {
            list = this.b.getStrategyList();
        }
        return list;
    }

    public synchronized void notifyConnEvent(IConnStrategy iConnStrategy, EventType eventType, d dVar) {
        if (eventType == EventType.HORSE_RIDE) {
            this.f = System.currentTimeMillis();
        }
        if (this.b != null) {
            this.b.notifyConnEvent(iConnStrategy, eventType, dVar);
            if ((eventType == EventType.CONNECT_FAIL || eventType == EventType.AUTH_FAIL) && this.b.isUnavailable()) {
                b.a().a(1, this.a);
            }
        }
    }

    public String getHostWithEtag() {
        if (TextUtils.isEmpty(this.e)) {
            return this.a;
        }
        return StringUtils.buildString(this.a, ":", this.e);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.c;
    }

    public synchronized void update(k.b bVar) {
        this.c = System.currentTimeMillis() + (((long) bVar.b) * 1000);
        if (!bVar.a.equalsIgnoreCase(this.a)) {
            ALog.e("StrategyCollection", "update error!", null, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.a, "dnsInfo.host", bVar.a);
        } else if (bVar.o) {
            if (this.b != null) {
                this.b.resetStatus();
            }
        } else if (TextUtils.isEmpty(bVar.d)) {
            this.e = bVar.n;
            if (HttpConstant.HTTP.equalsIgnoreCase(bVar.c) || com.alipay.sdk.cons.b.a.equalsIgnoreCase(bVar.c)) {
                this.d = bVar.c;
            }
            if (bVar.e == null || bVar.e.length == 0 || bVar.f == null || bVar.f.length == 0) {
                this.b = null;
                if (n.a(this.a)) {
                    this.b = ConnStrategyList.createForIDC(n.b(), a.a());
                }
            } else {
                if (this.b == null) {
                    this.b = n.d(bVar.a) ? ConnStrategyList.createForIDC() : ConnStrategyList.createForCDN();
                }
                this.b.update(bVar);
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("\nStrategyList = ").append(this.c);
        if (this.b == null) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append(this.b.toString());
        }
        return stringBuilder.toString();
    }
}
