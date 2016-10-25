package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.d;

// compiled from: Taobao
public interface IConnStrategy {
    ConnType getConnType();

    int getConnectionTimeout();

    int getHeartbeat();

    String getIp();

    int getPort();

    int getReadTimeout();

    int getRetryTimes();

    boolean isAvailable();

    boolean isNeedAuth();

    void notifyEvent(EventType eventType, d dVar);
}
