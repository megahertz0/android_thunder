package anet.channel.heartbeat;

import anet.channel.Session;

// compiled from: Taobao
public interface IHeartbeatFactory {
    IHeartbeat createHeartbeat(Session session);
}
