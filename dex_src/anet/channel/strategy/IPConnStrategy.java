package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import java.io.Serializable;

// compiled from: Taobao
class IPConnStrategy implements IConnStrategy, Serializable, Comparable<IPConnStrategy> {
    public final String ip;
    public transient boolean isToRemove;
    public final RawConnStrategy rawConnStrategy;

    // compiled from: Taobao
    static class a {
        a() {
        }

        static IPConnStrategy a(String str, anet.channel.strategy.k.a aVar) {
            return a(str, a.a(aVar));
        }

        static IPConnStrategy a(String str, RawConnStrategy rawConnStrategy) {
            return rawConnStrategy == null ? null : new IPConnStrategy(rawConnStrategy, null);
        }
    }

    private IPConnStrategy(String str, RawConnStrategy rawConnStrategy) {
        this.rawConnStrategy = rawConnStrategy;
        this.ip = str;
    }

    public String toString() {
        return String.format("{%s:%s}", new Object[]{this.ip, this.rawConnStrategy.toString()});
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.rawConnStrategy.port;
    }

    public ConnType getConnType() {
        return this.rawConnStrategy.connType;
    }

    public int getConnectionTimeout() {
        return this.rawConnStrategy.cto;
    }

    public int getReadTimeout() {
        return this.rawConnStrategy.rto;
    }

    public int getRetryTimes() {
        return this.rawConnStrategy.retry;
    }

    public int getHeartbeat() {
        return this.rawConnStrategy.heartbeat;
    }

    public boolean isNeedAuth() {
        return this.rawConnStrategy.isAuth;
    }

    public boolean isAvailable() {
        return this.rawConnStrategy.isAvailable();
    }

    public void notifyEvent(EventType eventType, d dVar) {
        this.rawConnStrategy.notifyEvent(eventType, dVar);
    }

    public void resetConnStatus() {
        this.rawConnStrategy.resetConnStatus();
    }

    public int compareTo(IPConnStrategy iPConnStrategy) {
        return this.rawConnStrategy.compareTo(iPConnStrategy.rawConnStrategy);
    }
}
