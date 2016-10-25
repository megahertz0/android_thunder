package anet.channel.entity;

// compiled from: Taobao
public enum EventType {
    CONNECTED(1),
    DISCONNECTED(2),
    HEADER_SEND(4),
    DATA_SEND(8),
    HEADER_RECEIVE(16),
    DATA_RECEIVE(32),
    PING_SEND(64),
    PIND_RECEIVE(128),
    CONNECT_FAIL(256),
    AUTH_SUCC(512),
    AUTH_FAIL(1024),
    DATA_TIMEOUT(2048),
    HORSE_RIDE(4096),
    ALL(8191);
    private int value;

    private EventType(int i) {
        this.value = i;
    }

    public final int getType() {
        return this.value;
    }
}
