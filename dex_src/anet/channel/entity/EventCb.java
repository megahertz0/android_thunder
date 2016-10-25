package anet.channel.entity;

import anet.channel.Session;

// compiled from: Taobao
public interface EventCb {
    void onEvent(Session session, EventType eventType, d dVar);
}
