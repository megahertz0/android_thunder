package anet.channel.strategy;

import anet.channel.entity.ConnType;

// compiled from: Taobao
class a implements Predicate<IPConnStrategy> {
    final /* synthetic */ anet.channel.strategy.k.a a;
    final /* synthetic */ ConnType b;
    final /* synthetic */ String c;
    final /* synthetic */ CDNStrategyList d;

    a(CDNStrategyList cDNStrategyList, anet.channel.strategy.k.a aVar, ConnType connType, String str) {
        this.d = cDNStrategyList;
        this.a = aVar;
        this.b = connType;
        this.c = str;
    }

    public /* synthetic */ boolean apply(Object obj) {
        return a((IPConnStrategy) obj);
    }

    public boolean a(IPConnStrategy iPConnStrategy) {
        return iPConnStrategy.getPort() == this.a.a && iPConnStrategy.getConnType().equals(this.b) && iPConnStrategy.getIp().equals(this.c);
    }
}
