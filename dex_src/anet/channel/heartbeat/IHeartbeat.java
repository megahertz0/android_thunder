package anet.channel.heartbeat;

// compiled from: Taobao
public interface IHeartbeat {
    long getInterval();

    void setNextHeartbeat(long j);

    void start();

    void stop();
}
