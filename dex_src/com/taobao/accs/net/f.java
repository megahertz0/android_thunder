package com.taobao.accs.net;

import anet.channel.entity.ConnType;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.dispatch.HttpDispatcher;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import java.util.ArrayList;
import java.util.List;

// compiled from: Taobao
public class f {
    private int a;
    private List<IConnStrategy> b;

    public f(String str) {
        this.a = 0;
        this.b = new ArrayList();
        HttpDispatcher.getInstance().addListener(new g(this));
        a(str);
    }

    public List<IConnStrategy> a(String str) {
        if (this.a == 0 || this.b.isEmpty()) {
            List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str);
            if (!(connStrategyListByHost == null || connStrategyListByHost.isEmpty())) {
                this.b.clear();
                for (IConnStrategy iConnStrategy : connStrategyListByHost) {
                    if (ConnType.ACCS_0RTT.equals(iConnStrategy.getConnType()) || ConnType.HTTP2.equals(iConnStrategy.getConnType())) {
                        this.b.add(iConnStrategy);
                    }
                }
            }
        }
        return this.b;
    }

    public IConnStrategy a() {
        return a(this.b);
    }

    public IConnStrategy a(List<IConnStrategy> list) {
        if (list == null || list.isEmpty()) {
            ALog.d("HttpDnsProvider", "strategys null or 0", new Object[0]);
            return null;
        }
        if (this.a < 0 || this.a >= list.size()) {
            this.a = 0;
        }
        return (IConnStrategy) list.get(this.a);
    }

    public void b() {
        this.a++;
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("HttpDnsProvider", new StringBuilder("updateStrategyPos StrategyPos:").append(this.a).toString(), new Object[0]);
        }
    }

    public int c() {
        return this.a;
    }

    public void b(String str) {
        StrategyCenter.getInstance().forceRefreshStrategy(str);
    }
}
