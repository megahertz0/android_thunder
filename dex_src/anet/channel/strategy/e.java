package anet.channel.strategy;

import anet.channel.entity.ConnType;
import java.util.Comparator;

// compiled from: Taobao
final class e implements Comparator<RawConnStrategy> {
    e() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return a((RawConnStrategy) obj, (RawConnStrategy) obj2);
    }

    public final int a(RawConnStrategy rawConnStrategy, RawConnStrategy rawConnStrategy2) {
        if (rawConnStrategy.b != rawConnStrategy2.b) {
            return rawConnStrategy.b - rawConnStrategy2.b;
        }
        int compare = ConnType.compare(rawConnStrategy.connType, rawConnStrategy2.connType);
        return compare == 0 ? (int) (rawConnStrategy.c - rawConnStrategy2.c) : compare;
    }
}
