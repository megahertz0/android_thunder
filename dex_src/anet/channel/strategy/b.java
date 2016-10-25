package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.strategy.k.a;

// compiled from: Taobao
class b implements Predicate<RawConnStrategy> {
    final /* synthetic */ a a;
    final /* synthetic */ ConnType b;
    final /* synthetic */ IDCStrategyList c;

    b(IDCStrategyList iDCStrategyList, a aVar, ConnType connType) {
        this.c = iDCStrategyList;
        this.a = aVar;
        this.b = connType;
    }

    public /* synthetic */ boolean apply(Object obj) {
        return a((RawConnStrategy) obj);
    }

    public boolean a(RawConnStrategy rawConnStrategy) {
        return rawConnStrategy.port == this.a.a && rawConnStrategy.connType.equals(this.b);
    }
}
