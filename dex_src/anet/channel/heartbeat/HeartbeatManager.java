package anet.channel.heartbeat;

import anet.channel.Session;

// compiled from: Taobao
public class HeartbeatManager {
    private static volatile IHeartbeatFactory heartbeatFactory;

    static {
        heartbeatFactory = new IHeartbeatFactory() {
            public final IHeartbeat createHeartbeat(Session session) {
                return (session == null || session.getConnStrategy() == null || session.getConnStrategy().getHeartbeat() <= 0) ? null : new DefaultHeartbeatImpl(session);
            }
        };
    }

    public static IHeartbeatFactory getHeartbeatFactory() {
        return heartbeatFactory;
    }

    public static void setHeartbeatFactory(IHeartbeatFactory iHeartbeatFactory) {
        heartbeatFactory = iHeartbeatFactory;
    }
}
